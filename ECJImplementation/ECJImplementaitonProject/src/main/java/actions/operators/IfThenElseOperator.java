package main.java.actions.operators;

import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;
import main.java.actions.FailableTactic;
import main.java.main.OmnetStateData;

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
		
		//reset the path probability at the end of the if statement for correct handling in loops
		double originalPathProbabiliy = o.getPathProbability();
		boolean hasValidFailProbability=true;
		if(!(children[0] instanceof FailableTactic)){
			o.setInvalidActionCount(o.getInvalidActionCount()+1);
			o.setAllStatesValid(false, "if statement must "
					+ "test a tactic that can fail.  Currently"
					+ "testing "+children[0].toStringForHumans());
			hasValidFailProbability=false;
			
		}
		  //System.out.println("beginning: "+sd.toString()+"\n");
		 
		  //evaluate both paths - copy o and then set the path weights for each finally, 
		  //bring the values back together
		  
		  //o.copy twice
		  //multiply inputCopy1 path probability times 1-failureWeight
		  boolean possiblyPartOfPlanEndPath=o.isPossiblePlanEnd();
		  OmnetStateData inputCopy=o.deepCopy();
		  double failureProbability = 0;
		  if(hasValidFailProbability){
			  failureProbability=((FailableTactic)children[0]).getFailureWeight();
		  } else {
			  failureProbability=0.5;
		  }
		  o.setPathProbability(o.getPathProbability()*
				  (1-failureProbability));
		  if(possiblyPartOfPlanEndPath){
			  o.setPossiblePlanEnd(false);
		  }
		  
		  children[0].eval(state,thread,input,stack,individual,problem);
		  if(possiblyPartOfPlanEndPath){
			  o.setPossiblePlanEnd(true);
		  }
		  children[1].eval(state,thread,input,stack,individual,problem); 
		  //have to reset to zero to avoid double counting the invalid
		  //states before the data structure was copied.
		  inputCopy.setInvalidActionCount(0);
		  inputCopy.setPathProbability(inputCopy.getPathProbability()
				  *failureProbability);
		  //multiply inputCopy2 path probability times failureWeight
		  children[2].eval(state,thread,inputCopy,stack,individual,problem);

		  if(!inputCopy.areAllStatesValid()){
			  o.setAllStatesValid(false, inputCopy.getReasonForAllStatesValidSetting());
		  }
		  o.setInvalidActionCount(o.getInvalidActionCount()+inputCopy.getInvalidActionCount());
		  o.setTotalScore(o.getTotalScore()+
				  inputCopy.getPathScore()*inputCopy.getPathProbability());
		  o.setPathProbability(originalPathProbabiliy);
		  //System.out.println("middle: "+sd.toString()+"\n");
		  
		  //System.out.println("after: "+sd.toString()+"\n");
		  //the result information should now be held in input

		}
	
}
