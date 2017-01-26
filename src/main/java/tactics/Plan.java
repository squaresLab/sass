package tactics;

import java.util.ArrayList;
import java.util.List;

import system.Fitness;
import system.SystemState;

public abstract class Plan implements Cloneable {
	
	// TODO these must be defined in the plan implementation
	private static double estMaxFitness = 10000;
	private static double minPossibleImprovement = 0.01;
	
	protected ArrayList<Tactic> tactics;
	
	public int invalidActions;
	
	public Plan(){
		tactics = new ArrayList<Tactic>();
	}
	
	public Plan(List<Tactic> tactics){
		this.tactics = new ArrayList<Tactic>(tactics);
	}
	
	public Fitness evaluate(SystemState system){
		
		return evaluate(system, 0);
	
	}
	
	private Fitness evaluate(SystemState system, int currentStep){

		if (currentStep == tactics.size()){
			return system.calculateFitness();
			// TODO reconsider how branch pruning works, is it really doing the right thing?
		}else if (system.getProbability() * estMaxFitness < minPossibleImprovement){
			return Fitness.MINIMUM_POSSIBLE_FITNESS;
		}else{
			
			Fitness onSuccess,onFail;
			
			Tactic current = (Tactic) tactics.get(currentStep).clone();
			
			system.accept(current);
			
			// first, check for a TryCatchFinally
			if (current instanceof TryCatchFinallyTactic){
				
				TryCatchFinallyTactic tcf = (TryCatchFinallyTactic) current;
				
				// if we are one, and the if part suceeded, then we must add the finally part
				if(!tcf.getFailed()){
					for (int count = 0; count < tcf.getFinally().getTactics().size(); count++){
						tactics.add(currentStep+1+count, (Tactic) tcf.getFinally().getTactics().get(count).clone());
					}
				}
			}
			
			onSuccess = evaluate(system,currentStep + 1);
			
			// first, check for a TryCatchFinally
						if (current instanceof TryCatchFinallyTactic){
							
							TryCatchFinallyTactic tcf = (TryCatchFinallyTactic) current;
							
							// if we are one, and the if part suceeded, then we must add the finally part
							if(!tcf.getFailed()){
								for (int count = 0; count < tcf.getFinally().getTactics().size(); count++){
									tactics.remove(currentStep+1);
								}
							}
						}
			
			system.undo();
			
			// if failable
			if (current instanceof FailableTactic){
				
				FailableTactic failable = (FailableTactic) current;
				// and succeeded
				if (!failable.getFailed()){
					
					// then also compute the fail branch
					system.accept(failable.getFail());
					
					// first, check for a TryCatchFinally
					if (failable instanceof TryCatchFinallyTactic){
						
						TryCatchFinallyTactic tcf = (TryCatchFinallyTactic) failable;
						
						// if we are one, and the if part failed, then we must add the catch part
						for (int count = 0; count < tcf.getCatch().getTactics().size(); count++){
							tactics.add(currentStep+1+count, (Tactic) tcf.getCatch().getTactics().get(count).clone());
						}
					}
					
					onFail = evaluate(system,currentStep + 1);
					
					system.undo();
					
					// first, check for a TryCatchFinally
					if (failable instanceof TryCatchFinallyTactic){
						
						TryCatchFinallyTactic tcf = (TryCatchFinallyTactic) failable;
						
						// if we are one, and the if part failed, then we must take away the fail block
						for (int count = 0; count < tcf.getCatch().getTactics().size(); count++){
							tactics.remove(currentStep+1);
						}
					
					}
					
					return onSuccess.or(onFail);
					
				}else{
					// if the tactic failed, then we do not need to compute the fail branch, since it already failed
					invalidActions++;
					return onSuccess;
				}
			}else{
				
				// if the tactic is not failable, then we are also done
				return onSuccess;
				
			}
		
			
		}
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
	
	public double getTime(){
		int ans = 0;
		for (int count = 0; count < tactics.size(); count++){
			ans += tactics.get(count).getTime();
		}
		return ans;
	}
	
	public void setMinAcceptedImprovment(double minAcceptedImprovement){
		this.minPossibleImprovement = minAcceptedImprovement;
	}
	
	public abstract Object clone();
	
}
