package bullseye.tactics;

import bullseye.System;

public abstract class Tactic {

	public abstract void visit(System system);
	public abstract boolean isApplicable(System system);

}
