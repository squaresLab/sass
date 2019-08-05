package bullseye.tactics.defender;

import bullseye.System;

public class Wait extends DefenderTactic {
	
	public double getCost() {;
		return 0.0;
	}

	@Override
	public void visit(System system) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isApplicable(System system) {
		return true;
	}


}
