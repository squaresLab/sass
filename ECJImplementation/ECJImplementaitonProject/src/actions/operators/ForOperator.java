package actions.operators;

import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;
import ec.util.MersenneTwister;
import ec.util.MersenneTwisterFast;
import main.OmnetStateData;
import main.StateData;

public class ForOperator extends GPNode {

	int forCount;
	int maxLoops=10;




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
		if(!o.areAllStatesValid()){
			return;
		}
		for(int i = 0; i < forCount; i++){
			//System.out.println("beginning: "+sd.toString()+"\n");
			children[0].eval(state,thread,input,stack,individual,problem);
			if(!o.areAllStatesValid()){
				return;
			}

		}

	}

	/** creates a unique forCount for each new for node */

	public void resetNode(final EvolutionState state, final int thread) { 
		forCount = state.random[thread].nextInt(maxLoops)+1;	
	}

}



