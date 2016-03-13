package actions;

import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;
import main.StateData;

public class IfThenElseOperator extends GPNode {

	//TODO: improve this return once you see the output
	public String toString() { return "if then:"; }
	
	public int expectedChildren() { return 3;}
	
	public void eval(final EvolutionState state,
            final int thread,
            final GPData input,
            final ADFStack stack,
            final GPIndividual individual,
            final Problem problem)
	{
		//double result;
		StateData sd = ((StateData)(input));
		//no need to evaluate any more if an invalid state has been reached
    	if(sd.getReachedInvalidState()){
    		return;
    	}

		
		if(!(children[0] instanceof ActionTemplate)){
			((StateData)input).setReachedInvalidState(true);
		}
		else {
		  //System.out.println("beginning: "+sd.toString()+"\n");
		  children[0].eval(state,thread,input,stack,individual,problem);
		  if(((ActionTemplate)children[0]).hasSucceeded()){
			  children[1].eval(state,thread,input,stack,individual,problem); 
		  } else  {
			  children[2].eval(state,thread,input,stack,individual,problem);
		  }

		  //System.out.println("middle: "+sd.toString()+"\n");
		  
		  //System.out.println("after: "+sd.toString()+"\n");
		  //the result information should now be held in input

		}
	}
	
}
