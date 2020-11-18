package bullseye.tactics.defender;

import bullseye.System;

public class ThrottleConnection implements DefenderTactic {
	
	public double getCost() {;
		return 0.5;
	}

	@Override
	public void visit(System system) {
		system.setThrottle(true);
		
	}

	@Override
	public boolean isApplicable(System system) {
		return !system.isThrottle();
	}


}
