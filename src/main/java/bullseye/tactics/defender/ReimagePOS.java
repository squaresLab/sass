package bullseye.tactics.defender;

import bullseye.System;

public class ReimagePOS implements DefenderTactic {
	
	public double getCost() {;
		return 3.0;
	}

	@Override
	public void visit(System system) {
		system.setPosFirmwareCompromised(false);
	}

	@Override
	public boolean isApplicable(System system) {
		return true;
	}


}
