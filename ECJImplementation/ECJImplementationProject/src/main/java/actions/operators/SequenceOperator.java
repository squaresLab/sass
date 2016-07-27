package main.java.actions.operators;

import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;
import main.java.main.OmnetStateData;

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
		o.countPossibleStates(this);
		
//		if(o.isPlanTooLarge()){
//			return;
//		}
//		boolean onPossiblePlanEndPath = o.isPossiblePlanEnd();
//		//no need to evaluate any more if an invalid state has been reached
//		o.setPossiblePlanEnd(false);
//		//System.out.println("beginning: "+sd.toString()+"\n");
//		
//		children[0].eval(state,thread,input,stack,individual,problem);
//		
//		if(o.isPlanTooLarge()){
//			return;
//		}
//		//no need to evaluate any more if an invalid state has been reached
//		if(onPossiblePlanEndPath){
//		  o.setPossiblePlanEnd(true);
//		}
//		//System.out.println("middle: "+sd.toString()+"\n");
//		if(this.children.length > 1){
//			children[1].eval(state,thread,input,stack,individual,problem);
//		}
//		//System.out.println("after: "+sd.toString()+"\n");
//		//the result information should now be held in input
		
	}
	

	

	
}

		

