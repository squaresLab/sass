package bullseye.tactics.attacker;

import bullseye.System;
import bullseye.attackerTypes.AttackerType;
import bullseye.attackerTypes.Criminal;
import bullseye.attackerTypes.Intelligence;
import bullseye.attackerTypes.Terrorist;

public class KeylogWeb extends AttackerTactic {

	public KeylogWeb() {
		obs = 0.05;
	}
	
	@Override
	public void visit(System system) {
		system.setWebServerKeylogged(true);
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
	public boolean isApplicable(System system) {
		return system.isAttackerHasWebPresence();
	}

}
