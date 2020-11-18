package bullseye.tactics.attacker;

import bullseye.System;
import bullseye.attackerTypes.Criminal;
import bullseye.attackerTypes.Intelligence;
import bullseye.attackerTypes.Terrorist;

public class ExfilData implements AttackerTactic {
	
	@Override
	public void visit(System system) {
		system.setUploadingData(true);
	}

	@Override
	public double getApplicability(Criminal criminal) {
		return 1;
	}

	@Override
	public double getApplicability(Intelligence intelligence) {
		return 1;
	}

	@Override
	public double getApplicability(Terrorist terrorist) {
		return 1;
	}

	@Override
	public boolean isApplicable(System system) {
		return system.isPaymentServerKeylogged() || system.isWebServerKeylogged() || system.isPosFirmwareCompromised();
	}

	@Override
	public double getObs() {
		return 0.25;
	}

}
