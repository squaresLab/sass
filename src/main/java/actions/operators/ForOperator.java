package actions.operators;

import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;
import main.OmnetStateData;

/*
 * Currently I am removing for loops.  The handling of if then statements
 * inside for loops are not handled correctly (to allow partially failing
 * plans to remain the population - otherwise I think this could be included.
 * The problem is that you have to save both branches from the if then
 * statement and then start the for loop again, which leads to branch
 * explosion.
 */

public class ForOperator extends GPNode {

	int forCount;
	int maxLoops=5;




	public int getForCount() {
		return forCount;
	}

	public void setForCount(int forCount) {
		this.forCount = forCount;
	}

	public String toString() { return "Loop for "+forCount+" times"; }

	public int expectedChildren() { return 1;}

	public void eval(final EvolutionState state,
			final int thread,
			final GPData input,
			final ADFStack stack,
			final GPIndividual individual,
			final Problem problem)
	{
		OmnetStateData o = (OmnetStateData)input;
		boolean onPossiblePlanEndPath = o.isPossiblePlanEnd();
		o.setPossiblePlanEnd(false);
		for(int i = 0; i < forCount; i++){
			//System.out.println("beginning: "+sd.toString()+"\n");
			if(onPossiblePlanEndPath && i==forCount-1){
				o.setPossiblePlanEnd(true);
			}
			children[0].eval(state,thread,input,stack,individual,problem);

		}

	}

	/** creates a unique forCount for each new for node */

	public void resetNode(final EvolutionState state, final int thread) { 
		forCount = state.random[thread].nextInt(maxLoops)+1;	
	}

}



