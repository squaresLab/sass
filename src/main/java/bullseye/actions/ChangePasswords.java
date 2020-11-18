package bullseye.actions;

import bullseye.System;
import bullseye.tactics.Tactic;
import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;

public class ChangePasswords extends GPNode implements Tactic {

	@Override
	public String toString() {
		return "ChangePword";
	}

	@Override
	public void eval(EvolutionState state, int thread, GPData input, ADFStack stack, GPIndividual individual,
			Problem problem) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Tactic visit(System system) {
		system.setAttackerHasPaymentPassword(false);
		system.setAttackerHasWebPassword(false);
		
		return null;
	}

	@Override
	public boolean isApplicable(System system) {
		return true;
	}

}
