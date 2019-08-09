package omnet.tactics;

import java.util.Arrays;
import java.util.List;

import omnet.Omnet;
import omnet.Omnet.Seams2018Scenario;
import omnet.Scenario;
import system.Fitness;
import tactics.FailableTactic;
import tactics.Plan;
import tactics.Tactic;
import tactics.TryCatchFinallyTactic;

public class OmnetPlan extends Plan {
	
	public OmnetPlan(){
		super();
	}
	
	public OmnetPlan(List<Tactic> asList) {
		super(asList);
	}
	
	public static void main(String[] args){
		OmnetPlan plan = new OmnetPlan();
		
		//TryCatchFinallyTactic tcf = new TryCatchFinallyTactic(new ShutdownServer("A"), new OmnetPlan(Arrays.asList(new ShutdownServer("A"),new StartServer("C"),new StartServer("C"),new StartServer("C"),new StartServer("C"),new StartServer("C"),new StartServer("B"),new StartServer("B"),new StartServer("B"),new StartServer("B"))),new OmnetPlan(Arrays.asList(new ShutdownServer("A"))));
		
		//plan.tactics.add(tcf);
		
		/*
		for (int count = 0; count < 3; count++)
			plan.tactics.add(new ShutdownServer("A"));
		

		
		for (int count = 0; count < 5; count++)
			plan.tactics.add(new StartServer("B"));
			
		for (int count = 0; count < 4; count++)
			plan.tactics.add(new StartServer("C"));
	*/
		
		for (int count = 0; count < 1; count++)
			plan.tactics.add(new StartServer("C"));
		
		for (int count = 0; count < 1; count++)
			plan.tactics.add(new StartServer("B"));
		
		for (int count = 0; count < 1; count++)
			plan.tactics.add(new ShutdownServer("A"));
		
		
		
		Fitness f = plan.evaluate(new Omnet(new Scenario()));
		
		System.out.println(f.get("Profit"));
		
	}
	
	/*
	private double evaluate(Omnet system, int currentStep){

		if (currentStep == tactics.size()){
			processLeaf(system);
			return system.calculateProfit() * system.getProbability();
		}else if (system.getProbability() * EST_MAX_FITNESS < MIN_POSSIBLE_IMPROVEMENT){
			return MINIMUM_POSSIBLE_FITNESS;
		}else{
			
			double onSuccess,onFail;
			
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
	
	
	
	private void processLeaf(Omnet system) {
		
		profit += system.calculateProfit() * system.getProbability();
		responses += (system.getDimmedResponses() + system.getNormalResponses())* system.getProbability();
		dimmedResponses += system.getDimmedResponses()* system.getProbability();
		normalResponses += system.getNormalResponses()* system.getProbability();
		income += system.getIncome() * system.getProbability();
		latency += system.getLatency() * system.getProbability();
		//time += 
		cost += system.getCost() * system.getProbability();
		
	}
	
	*/

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
