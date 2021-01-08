package bullseye.actions;

import bullseye.System;
import bullseye.tactics.Conditional;
import bullseye.tactics.Tactic;
import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;

public class IfStatement extends GPNode implements Tactic {

//	@Override
//	public String toString() {
//		return "(I "+children[0]+" "+children[1]+")";
//	}
	
	@Override
	public String toString() {
		return "I";
	}

	@Override
	public void eval(EvolutionState state, int thread, GPData input, ADFStack stack, GPIndividual individual,
			Problem problem) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Tactic visit(System system) {
		Conditional cond = (Conditional) children[0];
		
		if (cond.testCondition(system)) {
			return ((Tactic) children[1]).visit(system);
		}else {
			return ((Tactic) children[2]).visit(system);
		}

	}

	@Override
	public boolean isApplicable(System system) {
		return true;
	}

}
