package generalomnet.tactics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import generalomnet.Omnet;
import tactics.FailableTactic;
import tactics.Plan;
import tactics.Tactic;
import tactics.TryCatchFinallyTactic;

public class OmnetPlan extends Plan {
	
	public int invalidActions;

	public OmnetPlan(){
		super();
	}
	
	public OmnetPlan(List<Tactic> asList) {
		super(asList);
	}

	public double evaluate(Omnet system){
		
		return evaluate(system, 0);
	
	}
	
	public static void main(String[] args){
		OmnetPlan plan = new OmnetPlan();
		
		TryCatchFinallyTactic tcf = new TryCatchFinallyTactic(new StartServer("A"), new OmnetPlan(Arrays.asList(new StartServer("B"))),new OmnetPlan(Arrays.asList(new DecreaseDimmer("A"), new DecreaseDimmer("B"))));
		
	//	plan.tactics.add(new StartServer("A"));
	//	plan.tactics.add(new ShutdownServer("B"));
	//	plan.tactics.add(new StartServer("C"));
		plan.tactics.add(tcf);
	//	plan.tactics.add(new ShutdownServer("C"));
		
		System.out.println(plan.evaluate(new Omnet()));
		
	}
	
	private double evaluate(Omnet system, int currentStep){
		
		if (currentStep == tactics.size()){
			return system.calculateProfit() * system.getProbability();
		}else{
			
			double onSuccess,onFail;
			
			Tactic current = tactics.get(currentStep);
			
			system.accept(tactics.get(currentStep));
			
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
					
					return onSuccess + onFail;
					
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
	
	public String toString(){
		String ans = "";
		
		for (int count = 0; count < tactics.size(); count++)
			ans = ans + tactics.get(count) + " ";
		
		return ans;
		
	}
	
	public Object clone(){
		
		OmnetPlan copy = new OmnetPlan();
		
		for (int count = 0; count < tactics.size(); count++){
			copy.tactics.add(tactics.get(count));
		}
		
		return copy;
		
	}

}
