package bullseye.tactics.defender;

import bullseye.System;

public class ReimagePayment extends DefenderTactic {
	
	public double getCost() {;
		return 2.0;
	}

	@Override
	public void visit(System system) {
		system.setAttackerHasPaymentExploited(false);
	}

	@Override
	public boolean isApplicable(System system) {
		return true;
	}


}
