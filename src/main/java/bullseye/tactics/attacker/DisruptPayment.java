package bullseye.tactics.attacker;

import bullseye.System;
import bullseye.attackerTypes.AttackerType;
import bullseye.attackerTypes.Criminal;
import bullseye.attackerTypes.Intelligence;
import bullseye.attackerTypes.Terrorist;

public class DisruptPayment extends AttackerTactic {

	public DisruptPayment() {
		obs = 0.9;
	}
	
	@Override
	public void visit(System system) {
		system.setPaymentServerDisrupted(true);
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
		return system.isAttackerHasPaymentPresence();
	}

}
