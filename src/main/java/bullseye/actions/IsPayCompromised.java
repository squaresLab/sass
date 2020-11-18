package bullseye.actions;

import bullseye.System;
import bullseye.tactics.Conditional;
import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;

public class IsPayCompromised extends GPNode implements Conditional {

	@Override
	public String toString() {
		return "isPayHacked";
	}

	@Override
	public void eval(EvolutionState state, int thread, GPData input, ADFStack stack, GPIndividual individual,
			Problem problem) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean testCondition(System system) {
		return system.isAttackerHasPaymentExploited() || system.isAttackerHasPaymentPassword();
	}

}
