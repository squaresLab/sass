package system;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

import tactics.FailableTactic;
import tactics.Tactic;
import tactics.TryCatchFinallyTactic;

public abstract class Simulator {
	
	long time,step;
	
	double probability;
		
	ArrayList<Event> events;
	
	Hashtable<Long,Fitness> fitnessLog;
	
	ArrayList<Double> probabilityLog;
	
	public Simulator(){
		
		time = 0;
		probability = 1;
		events = new ArrayList<Event>();
		fitnessLog = new Hashtable<Long,Fitness>();
		probabilityLog = new ArrayList<Double>();
		
		probabilityLog.add(probability);
	}
	
	public Hashtable<Long, Fitness> runSim(){
		
		Collections.sort(events);
				
		return runSim(0, new Hashtable<Long,Fitness>());
		
	}
	
	public Hashtable<Long, Fitness> runSim(int eventIndex, Hashtable<Long, Fitness> fitnessLog){
		
		
		if (!(eventIndex < events.size())){
			return mult(fitnessLog,probability);
		}
		
		
			long timeBackup = time;
		
			Event e = events.get(eventIndex);
			
		    procEvent(e);
		    
		    boolean failed = e.tactic.getFailed();
			
			fitnessLog.put(time, calculateInstFitness());
			
			// finish the rest of the timeline after this event succeeds
			Hashtable<Long, Fitness> onSuccess = runSim(eventIndex+1,fitnessLog);
			
			// undo the event
			undo(e);
			
			time = timeBackup;
			
			if (e.type != Event.EventType.START && 1 - e.probability < 1.0 && !failed){
			
				procFail(e);

				fitnessLog.put(time, calculateInstFitness());
			
				// finish the rest of the timeline after this event fails
				Hashtable<Long, Fitness> onFail = runSim(eventIndex+1,fitnessLog);
			
				undo(e);
				
				time = timeBackup;
						
				return add(onSuccess,onFail);
			
			}else{
				
				return onSuccess;
			}
		
	}

	private void procEvent(Event e){
		procEvent(e,false);
	}

	private void procFail(Event e) {
		
		procEvent(e,true);
		
	}

	private void undo(Event e) {
		// if start, put an end event on the timeline
				if (e.type == Event.EventType.START){
					
					// if this is a start event, then we have to remove the generated end event
					recursiveRemove(e.getLinked());
					//events.remove(e.getLinked());
					
				}else{
					
					if (e.tactic instanceof TryCatchFinallyTactic){
						recursiveRemove(e.getLinked());
					}
					
					undo();
					//time -= e.tactic.getExecutionTime();
				}
		
				probabilityLog.remove(probabilityLog.size()-1);
				
				probability = probabilityLog.get(probabilityLog.size()-1);
				
	}

	private void recursiveRemove(ArrayList<Event> linked) {
		
		events.removeAll(linked);
		
		for (int count = 0; count < linked.size(); count++){
			//recursiveRemove(linked.get(count).getLinked());
		}
		
	}

	private void procEvent(Event e, boolean forceFail) {
		
		time = e.time;
		
		// if start, put an end event on the timeline
		if (e.type == Event.EventType.START){
			
			Event end = new Event(time + e.tactic.getExecutionTime(),e.tactic,Event.EventType.END);
			
			e.getLinked().add(end);
						
			events.add(end);
			
			Collections.sort(events);
			
			// trying a tactic always works
			probability *= 1.0;
			
			probabilityLog.add(probability);
			
		}else{
			// if an end, then execute the tactic
			
			FailableTactic acceptedTactic = null;
			
			if (forceFail){
				acceptedTactic = ((FailableTactic)e.getTactic()).getFail();
			}else{
				acceptedTactic = (FailableTactic) e.getTactic();
			}
			
			accept(acceptedTactic);
			//time += e.tactic.getExecutionTime();
			
			if (!acceptedTactic.getFailed()){
			
				probability *= 1.0 - e.probability;

			}else if (forceFail){
				
				probability *= e.probability;
			}
			
			probabilityLog.add(probability);
			
			// if try catch finally, special behavior for adding new events to the timeline
			if(acceptedTactic instanceof TryCatchFinallyTactic){
				
				TryCatchFinallyTactic tcf = (TryCatchFinallyTactic) acceptedTactic;
				
				// determine if the try failed or not
				if (tcf.getFailed()){
					// if it did fail, then we need to add the catch tactics
					ArrayList<Tactic> tactics = tcf.getCatch().getTactics();
					for (int count = 0; count < tactics.size(); count++){
						FailableTactic t = (FailableTactic) tactics.get(count).clone();
						Event c = new Event(time,t,Event.EventType.START);
						events.add(c);
						
						e.getLinked().add(c);
					}
				}else{
					ArrayList<Tactic> tactics = tcf.getFinally().getTactics();
					for (int count = 0; count < tactics.size(); count++){
						FailableTactic t = (FailableTactic) tactics.get(count).clone();
						Event c = new Event(time,t,Event.EventType.START);	
					    events.add(c);
						
						e.getLinked().add(c);
					}
				}
				
				Collections.sort(events);
				
							
			}
			
		}
				
	}

	abstract void accept(Tactic fail);

	abstract Fitness calculateInstFitness();

	abstract void undo();

	private Hashtable<Long, Fitness> add(Hashtable<Long, Fitness> onSuccess, Hashtable<Long, Fitness> onFail) {
		// first, fill in missing values
		
		Hashtable<Long,Fitness> ans = new Hashtable<Long,Fitness>();
		
		ArrayList<Long> timesA = new ArrayList<Long>(onSuccess.keySet());
		ArrayList<Long> timesB = new ArrayList<Long>(onFail.keySet());
		
		int ai = 0;
		int bi = 0;
		
		Collections.sort(timesA);
		Collections.sort(timesB);
		
		boolean done = false;
		
		while (!done){
			
			// take the lowest one
			long a; 
			
			if (ai < timesA.size()){
				a = timesA.get(ai);
			}else{
				a = Long.MAX_VALUE;
			}
			
			long b; 
			
			if (bi < timesB.size()){
				b = timesB.get(bi);
			}else{
				b = Long.MAX_VALUE;
			}
			
			long c = Math.min(a, b);
			
			// now, if they both have it, add them, otherwise fill in from prev and then add them
			if(a==b){
				ans.put(c, onSuccess.get(timesA.get(ai)).or(onFail.get(timesB.get(bi))));
				ai++;
				bi++;
			}else if (c == a){
				
				ans.put(c, onSuccess.get(timesA.get(ai)).or(onFail.get(timesB.get(bi-1))));
				ai++;
				
			}else if (c == b){
				ans.put(c, onSuccess.get(timesA.get(ai-1)).or(onFail.get(timesB.get(bi))));
				bi++;
			}
			
			if (ai >= timesA.size() && bi >= timesB.size()){
				done = true;
			}
			
			if (ai > timesA.size()){
				ai = timesA.size();
			}
			
			if (bi > timesB.size()){
				bi = timesB.size();
			}
			
		}
	
		return ans;
	}

	private Hashtable<Long, Fitness> mult(Hashtable<Long, Fitness> fitnessLog2, double probability2) {
		
		Hashtable<Long, Fitness> ans = new Hashtable<Long,Fitness>();
		
		for (Long x : fitnessLog2.keySet()){
			
			ans.put(x,fitnessLog2.get(x).mult(probability2));
			
		}
		
		return ans;
	}
	
	public ArrayList<Event> getEvents(){
		return events;
	}

}
