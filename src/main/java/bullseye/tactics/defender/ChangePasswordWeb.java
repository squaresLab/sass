package bullseye.tactics.defender;

import bullseye.System;

public class ChangePasswordWeb implements DefenderTactic {
	
	public double getCost() {;
		return 0.5;
	}

	@Override
	public void visit(System system) {
		system.setAttackerHasWebPassword(false);		
	}

	@Override
	public boolean isApplicable(System system) {
		return true;
	}


}
