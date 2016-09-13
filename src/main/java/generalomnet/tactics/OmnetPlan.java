package generalomnet.tactics;

import generalomnet.Omnet;
import tactics.FailableTactic;
import tactics.Plan;
import tactics.Tactic;

public class OmnetPlan extends Plan {
	
	public int invalidActions;
	
	public double evaluate(Omnet system){
		
		return evaluate(system, 0);
	
	}
	
	public static void main(String[] args){
		OmnetPlan plan = new OmnetPlan();
		
	//	plan.tactics.add(new StartServer("A"));
		plan.tactics.add(new ShutdownServer("B"));
	//	plan.tactics.add(new StartServer("C"));
		
		System.out.println(plan.evaluate(new Omnet()));
		
	}
	
	private double evaluate(Omnet system, int currentStep){
		
		if (currentStep == tactics.size()){
			return system.calculateProfit() * system.getProbability();
		}else{
			
			double onSuccess,onFail;
			
			Tactic current = tactics.get(currentStep);
			
			system.accept(tactics.get(currentStep));
			
			onSuccess = evaluate(system,currentStep + 1);
			
			system.undo();
			
			// if failable
			if (current instanceof FailableTactic){
				
				FailableTactic failable = (FailableTactic) current;
				// and succeeded
				if (!failable.getFailed()){
					// then also compute the fail branch
					system.accept(failable.getFail());
					onFail = evaluate(system,currentStep + 1);
					
					system.undo();
					
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
