package bullseye.tactics.attacker;

import bullseye.System;
import bullseye.attackerTypes.AttackerType;
import bullseye.attackerTypes.Criminal;
import bullseye.attackerTypes.Intelligence;
import bullseye.attackerTypes.Terrorist;

public class DisruptWeb implements AttackerTactic {
	
	@Override
	public void visit(System system) {
		system.setWebDisrupted(true);
	}

	@Override
	public double getApplicability(Criminal criminal) {
		return 0.1;
	}

	@Override
	public double getApplicability(Intelligence intelligence) {
		return .05;
	}

	@Override
	public double getApplicability(Terrorist terrorist) {
		return 1;
	}

	@Override
	public boolean isApplicable(System system) {
		return system.isAttackerHasWebPresence();
	}

	@Override
	public double getObs() {
		return  0.9;
	}

}
