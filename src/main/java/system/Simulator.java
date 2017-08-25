package system;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

import tactics.FailableTactic;
import tactics.Tactic;

public abstract class Simulator {
	
	long time;
	
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
		
			
			Event e = events.get(eventIndex);
			
			accept(e.getTactic());
			
			time += e.tactic.getExecutionTime();
			
			probability *= 1.0 - e.probability;
			
			probabilityLog.add(probability);
			
			fitnessLog.put(time, calculateInstFitness());
			
			// finish the rest of the timeline after this event succeeds
			Hashtable<Long, Fitness> onSuccess = runSim(eventIndex+1,fitnessLog);
			
			// undo the event
			undo();
			
			time -= e.tactic.getExecutionTime();
			
			probabilityLog.remove(probabilityLog.size()-1);
			
			probability = probabilityLog.get(probabilityLog.size()-1);
			
			accept(e.tactic.getFail());
			
			time += e.tactic.getExecutionTime();
			
			probability *= e.probability;
			
			probabilityLog.add(probability);
			
			fitnessLog.put(time, calculateInstFitness());
			
			// finish the rest of the timeline after this event fails
			Hashtable<Long, Fitness> onFail = runSim(eventIndex+1,fitnessLog);
			
			undo();
			
			probabilityLog.remove(probabilityLog.size()-1);
			
			probability = probabilityLog.get(probabilityLog.size()-1);
			
			time -= e.tactic.getExecutionTime();
			
			return add(onSuccess,onFail);
		
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
