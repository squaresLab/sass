package system;

import java.util.ArrayList;
import java.util.Hashtable;

import tactics.FailableTactic;
import tactics.ParallelTactic;
import tactics.Plan;
import tactics.Tactic;
import tactics.TryCatchFinallyTactic;

public abstract class SystemState extends Simulator {
	
	protected ArrayList<Event> timeline;
	
	protected ArrayList<Tactic> queue;
	
	int step;
	
	protected ArrayList<Fitness> aggFitness;
	
	protected ArrayList<Tactic> history;
	
	protected ArrayList<Double> probabilityHistory;
	
	long time;
	
	double pathProbability;

	public Fitness MINIMUM_POSSIBLE_FITNESS;
	
	public SystemState(){
		
		time = 0;
		
		step = 0;
		
		history = new ArrayList<Tactic>();
		queue = new ArrayList<Tactic>();
		probabilityHistory = new ArrayList<Double>();
		timeline = new ArrayList<Event>();
		
		pathProbability = 1;
		
		probabilityHistory.add(pathProbability);

	}

	private void updateProbability(double p){
		
		pathProbability *= p;
		
		probabilityHistory.add(pathProbability);
		
	}
	
	public void accept(Tactic tactic){
		
		if(aggFitness == null){
			aggFitness = new ArrayList<Fitness>();
			aggFitness.add(calculateInstFitness());
		}
		
		history.add(tactic);
		//historyTime.put(tactic,time);
		
		// only actually execute tactic if it did not fail
		// (would be set to fail if it was intentional)
		if (tactic instanceof FailableTactic){
			FailableTactic ft = (FailableTactic) tactic;
			// if not already set to fail, i.e. intentionally failing
			if (!ft.getFailed()){
				
				tactic.visit(this);
				
				// after we execute the failable tactic, check to see if it succeeded or not
				if (!ft.getFailed()){
				// only if we succeeded, then adjust probability
				// if failed because of impossible request, then we should not modify it
				updateProbability(1 - ft.getFailChance());
				}
				
			}else{
				// if it WAS set to intentionaly fail
				// then we do not execute the tactic, but still adjust probability
				updateProbability(ft.getFailChance());
			}
			
			
			
		}else{
			// if tactic is not failable, then just run it
			// no need to adjust probablitity
			tactic.visit(this);
		}
		
		// regardless of if the tactic succeeds or fails, we have to wait to see what happens
		time += tactic.getExecutionTime();
		
		// we must also update the aggregate fitness to reflect the systems quality as we waited
		aggFitness.add(aggFitness.get(aggFitness.size()-1).or(calculateInstFitness().mult(tactic.getExecutionTime())));
		
	}
	
	public void undo(){
		
		Tactic last = history.get(history.size()-1);
		
		if (last instanceof FailableTactic){
			
			FailableTactic ft = (FailableTactic) last;
			
			double chance = ft.getFailed() ? ft.getFailChance() : 1 - ft.getFailChance();
			
			// undo the probability change
			if (ft.getIntentionalFailed() || !ft.getFailed()){
				
				probabilityHistory.remove(probabilityHistory.size() - 1);
				
				pathProbability = probabilityHistory.get(probabilityHistory.size() - 1);
				
			}
		}
		
		// now undo the effect on the system state if needed
		if (last instanceof FailableTactic){
			
			FailableTactic ft = (FailableTactic) last;
			
			// if the tactic failed, then it does not need to be undone
			if (!ft.getFailed())
			last.undo(this);
			
		}else{
			last.undo(this);
		}
		// and now remove the tactic from the history
		history.remove(last);
		// we will also forget about the fitness accrued over the latency period
		aggFitness.remove(aggFitness.size()-1);
		// and we will of course turn back the clock
		time -= last.getExecutionTime();
		
	}
	
	public double getProbability(){
		return pathProbability;
	}
	
	public abstract boolean isStateValid();

	// evaluate the fitness of a single instance
	public abstract Fitness calculateInstFitness();
	
	public long getTime() {
		return time;
	}
	

}
