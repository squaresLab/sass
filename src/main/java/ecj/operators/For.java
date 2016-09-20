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

public class For extends GPNode {
	
	public String toString() { return "F"; }

	public int expectedChildren() { return 2;}

	public void eval(final EvolutionState state,
			final int thread,
			final GPData input,
			final ADFStack stack,
			final GPIndividual individual,
			final Problem problem)
	{
		//get the constant value
		Int constant = new Int();
		
		children[0].eval(state, thread, constant, stack, individual, problem);

		//now handling with a runtime exception
		StateData o = (StateData)input;
		
		for (int count = 0; count < constant.value; count++){
			children[1].eval(state,thread,input,stack,individual,problem);
		}

	}

}
