package tactics;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;
import java.util.List;

import system.Event;
import system.Fitness;
import system.SystemState;

public abstract class Plan implements Cloneable {
	
	// TODO these must be defined in the plan implementation
	private static double estMaxFitness = 10000;
	private static double minPossibleImprovement = 0.01;
	public static long window = 10000;
	
	protected ArrayList<Tactic> tactics;
	
	public int invalidActions;
	
	public Plan(){
		tactics = new ArrayList<Tactic>();
	}
	
	public Plan(List<Tactic> tactics){
		this.tactics = new ArrayList<Tactic>(tactics);
	}
	
	
	public Fitness evaluate(SystemState system){
		return evaluate(system,0l);
	}
	
	public Fitness evaluate(SystemState system, long planningTime){

		ArrayList<Event> events = system.getEvents();
		
		// convert tactics to events
		for (int i = 0; i < tactics.size(); i++){
			
			Tactic t = tactics.get(i);
			
			Event s = new Event((FailableTactic) t);
			
			events.add(s);
			
		}

		long start = System.currentTimeMillis();
		Hashtable<Long,Fitness> record = system.runSim();
		long duration = (long) Math.ceil((System.currentTimeMillis() - start) / 1000.0);
		
		if (record != null){
		
			return aggregate(record,planningTime);
		
		}else{
			
			return null;
			
		}
		/*
		ArrayList<Long> times = new ArrayList<Long>(record.keySet());
		
		Collections.sort(times);
		
		return record.get(times.get(times.size()-1)); 
		
		//return evaluate(system, 0);
		 * 
		 */
	
	}
	
	private Fitness aggregate(Hashtable<Long, Fitness> record, long planningTime) {
		
		// subtract time from window based on how long planning took
		long effectiveWindow = window - planningTime;
		
		Fitness ans = new Fitness();
		
		ArrayList<Long> times = new ArrayList<Long>(record.keySet());
		
		Collections.sort(times);
		
		// first copy time 0
		Fitness cur = null;
		long time = 0;
		long lastTime = 1;
		for (int count = 0; count < times.size(); count++){
			
			if (count == 0){
				
				cur = record.get(0l);
				
				if (effectiveWindow < 0){
					cur = cur.mult(window);
					return cur;
				}else{

					cur = cur.mult(planningTime);
				
				}
				
				ans = cur;
				cur = record.get(0l);
				

			}else{
				time = times.get(count);
				
				if (time > effectiveWindow){
					time = effectiveWindow;
				}
				
				long duration = time - lastTime;
				
				cur = cur.mult(duration);
				
				ans = ans.or(cur);
				
				cur = record.get(time);
				
				lastTime = time;
				
				if (time == effectiveWindow){
					break;
				}
				
			}
			
		}
		
		// give extra weight to the last state
		//long last = times.get(times.size()-1);
		
		//cur = record.get(last);
		/*
		// weight each by 50%
		ans = ans.mult(0.5*(1.0/last));
		cur = cur.mult(0.5);
		
		ans = ans.or(cur);
		
		*/
		
		// assume that the system runs for 10000 
		long remaining = effectiveWindow - time;
		
		if (remaining > 0){
		
			// add this profit
			cur = cur.mult(remaining);
			ans = ans.or(cur);
		
		}
		
		if (ans.get("Profit") > 60000000){
			System.out.println("too high");
		}
		
		return ans;
	}

	private Fitness evaluate(SystemState system, int currentStep){

		if (currentStep == tactics.size()){
			return system.calculateInstFitness();
			// TODO reconsider how branch pruning works, is it really doing the right thing?
		}else if (system.MINIMUM_POSSIBLE_FITNESS != null && system.getProbability() * estMaxFitness < minPossibleImprovement){
			return system.MINIMUM_POSSIBLE_FITNESS;
		}else{
			
			
			
			Tactic current = (Tactic) tactics.get(currentStep).clone();
			
			if (current.getStartTime() == null){
					current.setStartTime(system.getTime());
			}
			
			
			// behavior depends on tactic type, if regular tactic, do the regular stuff
			if(current instanceof FailableTactic){
				
				FailableTactic failable = (FailableTactic) current;
				
				Fitness onSuccess,onFail;
				
				system.accept(current);
				onSuccess = evaluate(system,currentStep + 1);
				system.undo();
				
				
				
				if (!failable.getFailed()){
					
					// then also compute the fail branch
					system.accept(failable.getFail());

					onFail = evaluate(system,currentStep + 1);
					
					system.undo();
					
					return onSuccess.or(onFail);
					
				}else{
					// if the tactic failed, then we do not need to compute the fail branch, since it already failed
					invalidActions++;
					return onSuccess;
				}
				
			}else if (current instanceof TryCatchFinallyTactic){
				
				TryCatchFinallyTactic tcf = (TryCatchFinallyTactic) current;
				
				// first we try
				FailableTactic inTry = tcf.inTry;
				
				Fitness onSuccess,onFail;
				onSuccess = null;
				
				system.accept(inTry);
				
				// now check for success / failure
				if(!inTry.getFailed()){
					// not failed, we do the finally
					
					for (int count = 0; count < tcf.getFinally().getTactics().size(); count++){
						tactics.add(currentStep+1+count, (Tactic) tcf.getFinally().getTactics().get(count).clone());
					}
					// and continue
					onSuccess = evaluate(system,currentStep + 1);
					
					// then undo
					for (int count = 0; count < tcf.getFinally().getTactics().size(); count++){
						tactics.remove(currentStep+1);
					}
					system.undo();
					
					// add the fail case
					system.accept(inTry.getFail());
				}
				
				// do the catch
				for (int count = 0; count < tcf.getCatch().getTactics().size(); count++){
					tactics.add(currentStep+1+count, (Tactic) tcf.getCatch().getTactics().get(count).clone());
				}
				// and continue
				onFail = evaluate(system,currentStep + 1);
				system.undo();
				
				if(onSuccess != null){
					return onSuccess.or(onFail);
				}else{
					invalidActions++;
					return onFail;
				}
			
			}else if (current instanceof ParallelTactic){
				
				ParallelTactic p = (ParallelTactic) current;
				
				// we will annotate the tactics with their revised start times
				for (int count = 0; count < p.getTactics().size(); count++){
					Tactic c = (Tactic) p.getTactics().get(count).clone();
					
					c.setStartTime(p.getStartTime());
					
					tactics.add(currentStep+1+count,c);
					
				}
				
				// continue evaluation
				Fitness ans = evaluate(system,currentStep + 1);
				
				// clean up
				for (int count = 0; count < p.getTactics().size(); count++){
					tactics.remove(currentStep+1);	
				}
				
				return ans;
				
			}
					
		}
		return null;
	}
	
	public ArrayList<Tactic> getTactics(){
		return tactics;
	}
	
	public int getInvalidActions() {
		return invalidActions;
	}
	
	public int size(){
		int ans = 0;
		for (int count = 0; count < tactics.size(); count++){
			ans += tactics.get(count).size();
		}
		return ans;
	}
	
	public long getTime(){
		int ans = 0;
		for (int count = 0; count < tactics.size(); count++){
			ans += tactics.get(count).getExecutionTime();
		}
		return ans;
	}
	
	public void setMinAcceptedImprovment(double minAcceptedImprovement){
		this.minPossibleImprovement = minAcceptedImprovement;
	}
	
	public abstract Object clone();
	
}
