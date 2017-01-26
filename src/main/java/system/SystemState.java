package system;

import java.util.ArrayList;

import tactics.FailableTactic;
import tactics.Tactic;

public abstract class SystemState {
	
//	public ArrayList<Attribute> getAttributes();
	protected ArrayList<Tactic> history;
	
	protected ArrayList<Double> probabilityHistory;
	
	double pathProbability;
	
	public SystemState(){
		history = new ArrayList<Tactic>();
		probabilityHistory = new ArrayList<Double>();
		
		pathProbability = 1;
		
		probabilityHistory.add(pathProbability);
	}
	
	private void updateProbability(double p){
		
		pathProbability *= p;
		
		probabilityHistory.add(pathProbability);
		
	}
	
	public void accept(Tactic tactic){
		
		history.add(tactic);
		
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
		
	}
	
	public double getProbability(){
		return pathProbability;
	}
	
	public abstract boolean isStateValid();

	public double calculateFitness() {
		// TODO Auto-generated method stub
		return 0;
	}
	

}
