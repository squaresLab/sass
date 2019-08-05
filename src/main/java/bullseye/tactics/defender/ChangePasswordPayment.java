package bullseye.tactics.defender;

import bullseye.System;

public class ChangePasswordPayment extends DefenderTactic {
	
	public double getCost() {;
		return 0.75;
	}

	@Override
	public void visit(System system) {
		system.setAttackerHasPaymentPassword(false);	
	}

	@Override
	public boolean isApplicable(System system) {
		return true;
	}

}