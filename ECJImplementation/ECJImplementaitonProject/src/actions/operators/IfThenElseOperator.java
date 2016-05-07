package actions.operators;

import actions.ActionTemplate;
import actions.FailableTactic;
import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;
import main.OmnetStateData;

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
		//Now handling with a runtime exception
		//double result;
		//StateData sd = ((StateData)(input));
		//no need to evaluate any more if an invalid state has been reached
		OmnetStateData o = (OmnetStateData)input;
		//no need to evaluate any more if an invalid state has been reached
		if(!o.areAllStatesValid()){
			return;
		}

		
		if(!(children[0] instanceof FailableTactic)){
			o.setAllStatesValid(false, "if statement must "
					+ "test a tactic that can fail.  Currently"
					+ "testing "+children[0].toStringForHumans());
			return;
		}
		else {
		  //System.out.println("beginning: "+sd.toString()+"\n");
		  children[0].eval(state,thread,input,stack,individual,problem);
		  if(!o.areAllStatesValid()){
			  return;
		  }
		  if(((FailableTactic)children[0]).hasActionSucceeded()){
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
