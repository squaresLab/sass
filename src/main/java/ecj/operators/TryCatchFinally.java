package ecj.operators;

import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;
import ecj.StateData;
import tactics.FailableTactic;
import tactics.TryCatchFinallyTactic;

public class TryCatchFinally extends GPNode {
	
	public String toString() { return ";"; }

	public int expectedChildren() { return 3;}

	public void eval(final EvolutionState state,
			final int thread,
			final GPData input,
			final ADFStack stack,
			final GPIndividual individual,
			final Problem problem)
	{
		//double result;

		//now handling with a runtime exception
		StateData o = (StateData)input;
		
		children[0].eval(state,thread,input,stack,individual,problem);
		
		FailableTactic last = (FailableTactic) o.plan.getTactics().remove(o.plan.getTactics().size() - 1);
		
		StateData catchPlan = new StateData();
		catchPlan.initializeState();
		
		children[1].eval(state,thread,catchPlan,stack,individual,problem);
		
		StateData finallyPlan = new StateData();
		finallyPlan.initializeState();
		
		children[2].eval(state,thread,finallyPlan,stack,individual,problem);
		
		TryCatchFinallyTactic tcf = new TryCatchFinallyTactic(last,catchPlan.plan,finallyPlan.plan);
		
		o.plan.getTactics().add(tcf);
		
//		o.getAllFinalStates(this);

//		children[0].eval(state,thread,input,stack,individual,problem);
//
//		//System.out.println("middle: "+sd.toString()+"\n");
//		children[1].eval(state,thread,input,stack,individual,problem);
		//System.out.println("after: "+sd.toString()+"\n");
		//the result information should now be held in input
		


	}

}
