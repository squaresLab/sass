package ecj.operators;

import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;
import ecj.StateData;
import main.OmnetStateData;

public class SequenceOperator extends GPNode {

	public String toString() { return ";"; }

	public int expectedChildren() { return 2;}

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
		
		children[1].eval(state,thread,input,stack,individual,problem);
		
//		o.getAllFinalStates(this);

//		children[0].eval(state,thread,input,stack,individual,problem);
//
//		//System.out.println("middle: "+sd.toString()+"\n");
//		children[1].eval(state,thread,input,stack,individual,problem);
		//System.out.println("after: "+sd.toString()+"\n");
		//the result information should now be held in input
		


	}


}







