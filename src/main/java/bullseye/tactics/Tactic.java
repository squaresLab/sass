package bullseye.tactics;

import bullseye.System;

public interface Tactic {

	public Tactic visit(System system);
	public boolean isApplicable(System system);

}
