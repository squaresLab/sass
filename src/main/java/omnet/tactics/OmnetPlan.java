package omnet.tactics;

import java.util.Arrays;
import java.util.List;

import omnet.Omnet;
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
		
		for (int count = 0; count < 4; count++)
			plan.tactics.add(new StartServer("C"));
		
		for (int count = 0; count < 10; count++)
			plan.tactics.add(new StartServer("B"));
		
		for (int count = 0; count < 6; count++)
			plan.tactics.add(new ShutdownServer("A"));
		
		System.out.println(plan.evaluate(new Omnet()));
		//System.out.println(plan.profit);
		
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
