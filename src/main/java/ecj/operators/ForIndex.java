package ecj.operators;

import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.ERC;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;
import ec.util.Code;

public class ForIndex extends ERC {
	
	int value;

	@Override
	public void resetNode(EvolutionState state, int thread) {
		//value = state.random[thread].nextInt(9) + 2;
		value = 2;
	}
	
	@Override
	public void mutateERC(EvolutionState state, int thread)  {
		if (value == 2){
			value++;
		}else if (value == 10){
			value--;
		}else{
			if (state.random[thread].nextBoolean()){
				value++;
			}else{
				value--;
			}
		}
	}

	@Override
	public boolean nodeEquals(GPNode node) {
		return (node.getClass() == this.getClass() && ((ForIndex)node).value == value); 
	}

	@Override
	public String encode() {
		return Code.encode(value);
	}

	@Override
	public void eval(EvolutionState state, int thread, GPData input, ADFStack stack, GPIndividual individual,
			Problem problem) {
		
		Int intData = (Int) input;
		
		intData.value = this.value;
		
	}
	
	public String toStringForHumans(){
		return "" + value;
	}

}
