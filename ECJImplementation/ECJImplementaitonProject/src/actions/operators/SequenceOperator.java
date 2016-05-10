package actions.operators;

import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;
import main.OmnetStateData;
import main.StateData;

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
		OmnetStateData o = (OmnetStateData)input;
		boolean onPossiblePlanEndPath = o.isPossiblePlanEnd();
		//no need to evaluate any more if an invalid state has been reached
		if(!o.areAllStatesValid()){
			return;
		}
		o.setPossiblePlanEnd(false);
		//System.out.println("beginning: "+sd.toString()+"\n");
		children[0].eval(state,thread,input,stack,individual,problem);
		//no need to evaluate any more if an invalid state has been reached
		if(!o.areAllStatesValid()){
			return;
		}
		if(onPossiblePlanEndPath){
		  o.setPossiblePlanEnd(true);
		}
		//System.out.println("middle: "+sd.toString()+"\n");
		children[1].eval(state,thread,input,stack,individual,problem);
		//System.out.println("after: "+sd.toString()+"\n");
		//the result information should now be held in input
		
	}
	
	
}

		

