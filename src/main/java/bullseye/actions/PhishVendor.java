package bullseye.actions;

import bullseye.System;
import bullseye.attackerTypes.Criminal;
import bullseye.attackerTypes.Intelligence;
import bullseye.attackerTypes.Terrorist;
import bullseye.tactics.Tactic;
import bullseye.tactics.attacker.AttackerTactic;
import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;

public class PhishVendor extends GPNode implements AttackerTactic {

	@Override
	public String toString() {
		return "PhishVendor";
	}

	@Override
	public void eval(EvolutionState state, int thread, GPData input, ADFStack stack, GPIndividual individual,
			Problem problem) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Tactic visit(System system) {
		
		system.setAttackerHasWebPassword(true);
		
		return null;
	}

	@Override
	public boolean isApplicable(System system) {
		return true;
	}

	@Override
	public double getApplicability(Criminal criminal) {
		return 1;
	}

	@Override
	public double getApplicability(Intelligence intelligence) {
		return 1;
	}

	@Override
	public double getApplicability(Terrorist terrorist) {
		return 1;
	}

	@Override
	public double getObs() {
		return 0.1;
	}

}
