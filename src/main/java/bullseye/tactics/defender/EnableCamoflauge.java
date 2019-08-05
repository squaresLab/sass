package bullseye.tactics.defender;

import bullseye.System;

public class EnableCamoflauge extends DefenderTactic {
	
	public double getCost() {;
		return 0.5;
	}

	@Override
	public void visit(System system) {
		system.setCamoflauge(true);		
	}

	@Override
	public boolean isApplicable(System system) {
		return !system.isCamoflauge();
	}


}
