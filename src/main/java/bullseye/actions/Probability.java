package bullseye.actions;

import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.ERC;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;
import ec.util.Code;
import ec.util.DecodeReturn;

public class Probability extends ERC {
	
	public double value;

	@Override
	public void resetNode(EvolutionState state, int thread) {
		
		value = state.random[thread].nextDouble();
		
	}
	
	@Override
	public void mutateERC(EvolutionState state, int thread) {
		double v;
		do {
			v = value + state.random[thread].nextGaussian()*.1;
		}while(v < 0 || v > 1.0);
		
		value = v;
		
	}

	@Override
	public boolean nodeEquals(GPNode node) {
		return node.getClass() == this.getClass() && ((Probability) node).value == this.value;
	}

	@Override
	public String encode() {
		return Code.encode(value);
	}
	
	@Override
	public boolean decode(final DecodeReturn dret)
    {
	
		Code.decode(dret);
		
		if(dret.type == dret.T_DOUBLE){
			
			this.value = (double) dret.d;
			
			return true;
		
		}else{
			return false;
		}
    }

	@Override
	public void eval(EvolutionState state, int thread, GPData input, ADFStack stack, GPIndividual individual,
			Problem problem) {
		// TODO Auto-generated method stub
		
	}

}
