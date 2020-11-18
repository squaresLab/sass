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

	@Override
	public String toString() {
		return "(; "+children[0] + " "+children[1]+")";
	}

	@Override
	public void eval(EvolutionState state, int thread, GPData input, ADFStack stack, GPIndividual individual,
			Problem problem) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Tactic visit(System system) {
		
		Tactic t = ((Tactic) children[0]);
		
		if (t != null) {
			t = t.visit(system);
			if (t != null) {
				children[0] = (GPNode) t;
			}else {
				children[0] = null;
			}
			return this;
		}else {
			t = ((Tactic) children[1]);
			return t.visit(system);
		}
		
	}

	@Override
	public boolean isApplicable(System system) {
		return true;
	}

}
