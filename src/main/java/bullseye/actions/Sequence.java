package bullseye.actions;

import bullseye.System;
import bullseye.tactics.Tactic;
import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;

public class Sequence extends GPNode implements Tactic {

	GPNode pointer = null;
	
	@Override
	public String toString() {
		return "(; "+children[0] + " "+children[1]+")";
	}

	@Override
	public void eval(EvolutionState state, int thread, GPData input, ADFStack stack, GPIndividual individual,
			Problem problem) {
		
		pointer = children[0];
		
		children[0].eval(state, thread, input, stack, individual, problem);
		children[1].eval(state, thread, input, stack, individual, problem);
		
	}

	@Override
	public Tactic visit(System system) {
		
		if (pointer != null) {
			Tactic t = ((Tactic) pointer);
			
			pointer = (GPNode) t.visit(system);
			
			return this;
			
		}else{
			return ((Tactic) children[1]).visit(system);
		}
		
	}

	@Override
	public boolean isApplicable(System system) {
		return true;
	}

}
