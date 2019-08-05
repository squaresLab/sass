package bullseye.tactics.attacker;

import bullseye.attackerTypes.AttackerTypeVisitor;
import bullseye.tactics.Tactic;

public abstract class AttackerTactic extends Tactic implements AttackerTypeVisitor{
	
	double obs;
	
	public void setObs(double obs) {
		this.obs = obs;
	}
	
	public double getObs() {
		return obs;
	}

}