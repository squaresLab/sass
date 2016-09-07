package actions.operators;

import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;
import actions.FailableTactic;
import main.OmnetStateData;
import omnet.tactics.ServerTactic;

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
		if(o.isPlanTooLarge()){
			return;
		}
		
		boolean hasValidFailProbability=true;
		if(!(children[0] instanceof ServerTactic)){
			o.setPathsInvalid(children[0].toStringForHumans());
			hasValidFailProbability=false;
			
		} else {
			((ServerTactic)children[0]).setInIfStatementTest(true);
		}
		  //System.out.println("beginning: "+sd.toString()+"\n");
		 
		  //evaluate both paths - copy o and then set the path weights for each finally, 
		  //bring the values back together
		  
		  //o.copy twice
		  boolean possiblyPartOfPlanEndPath=o.isPossiblePlanEnd();
		  double failureProbability = 0;
		  if(hasValidFailProbability){
			  failureProbability=((FailableTactic)children[0]).getFailureWeight();
		  } else {
			  failureProbability=0.5;
		  }
		  OmnetStateData failBranch = o.createFailureBranch(failureProbability);
		  if(o.isPlanTooLarge()){
				return;
			}
		  if(possiblyPartOfPlanEndPath){
			  o.setPossiblePlanEnd(false);
		  }
		  
		  children[0].eval(state,thread,input,stack,individual,problem);
		  if(o.isPlanTooLarge()){
				return;
			}
		  if(possiblyPartOfPlanEndPath){
			  o.setPossiblePlanEnd(true);
		  }
		  children[1].eval(state,thread,input,stack,individual,problem); 
		  //have to reset to zero to avoid double counting the invalid
		  //states before the data structure was copied.
		  //multiply inputCopy2 path probability times failureWeight
		  children[2].eval(state,thread,failBranch,stack,individual,problem);

		  //the result information should now be held in input
		  o.mergeStateDatea(failBranch);
		  if(o.isPlanTooLarge()){
				return;
			}
		}
	
}
