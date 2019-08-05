package bullseye.tactics.defender;

import bullseye.System;

public class ReimageWeb extends DefenderTactic {
	
	public double getCost() {;
		return 1.0;
	}

	@Override
	public void visit(System system) {
		system.setAttackerHasWebExploited(false);
		
	}

	@Override
	public boolean isApplicable(System system) {
		return true;
	}


}
