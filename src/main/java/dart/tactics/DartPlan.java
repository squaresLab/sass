package dart.tactics;

import java.util.Arrays;
import java.util.List;

import dart.Dart;
import omnet.Omnet;
import omnet.Omnet.Scenario;
import system.Fitness;
import system.SystemState;
import tactics.FailableTactic;
import tactics.Plan;
import tactics.Tactic;
import tactics.TryCatchFinallyTactic;

public class DartPlan extends Plan {
	
	public DartPlan(){
		super();
	}
	
	public DartPlan(List<Tactic> asList) {
		super(asList);
	}
	
	// TODO Warning, this is not right, must be updated to properly use the given plan!
	public Fitness evaluate(SystemState system){
		return (new Dart()).calculateInstFitness(this.toString());
	}

	public String toString(){
		
		String ans = "";
		
		for (int count = 0; count < tactics.size(); count++)
			ans = ans + tactics.get(count).getClass().getSimpleName() + " ";
		
		return ans;
		
		//return tactics.get(0).getClass().getSimpleName();
	}
	
	public Object clone(){
		
		DartPlan copy = new DartPlan();
		
		for (int count = 0; count < tactics.size(); count++){
			copy.tactics.add(tactics.get(count));
		}
		
		return copy;
		
	}

}
