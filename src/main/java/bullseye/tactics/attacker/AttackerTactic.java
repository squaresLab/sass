package bullseye.tactics.attacker;

import bullseye.BullseyeScenario;
import bullseye.attackerTypes.AttackerTypeVisitor;
import bullseye.tactics.Tactic;

public interface AttackerTactic extends AttackerTypeVisitor, Tactic{

	public double getObs(BullseyeScenario scenario);

}