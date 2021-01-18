package bullseye;

import java.util.ArrayList;
import java.util.Random;

import bullseye.actions.PhishEmployee;
import bullseye.attackerTypes.AttackerType;
import bullseye.attackerTypes.Criminal;
import bullseye.attackerTypes.Intelligence;
import bullseye.attackerTypes.Terrorist;
import bullseye.tactics.Tactic;
import bullseye.tactics.attacker.AttackerTactic;
import bullseye.tactics.defender.DefenderTactic;
import ec.gp.GPIndividual;
import ec.gp.GPNode;

public class System {

	private static final int TIMESTEP_LIMIT = 2;
	
	boolean attackerHasWebExploited= false;
	boolean attackerHasPaymentExploited = false;
	boolean attackerHasWebPassword = false;
	boolean attackerHasPaymentPassword = false;
	boolean webServerKeylogged = false;
	boolean paymentServerKeylogged = false;
	boolean posFirmwareCompromised = false;
	boolean uploadingData = false;
	boolean webDisrupted = false;
	boolean paymentServerDisrupted = false;
	boolean posDisrupted = false;
	
	boolean camoflauge = false;
	boolean throttle = false;
	
	boolean attackerDetected = false;
	
	BullseyeScenario scenario;
	
	public System() {
		scenario = new BullseyeScenario();
	}
	
	public System(BullseyeScenario scenario) {
		this.scenario = scenario;
	}
	
	public static double[] evaluate(ArrayList<DefenderTactic> defender, ArrayList<AttackerTactic> attacker, AttackerType type) {
		double[] ans = new double[2];
		
		System sys = new System();
		
		int timestep = 0;
		
		while(!sys.getAttackerDetected() && (timestep < defender.size() || timestep < attacker.size())) {
			
			AttackerTactic a = null;
			
			if (timestep < attacker.size()) {
				a = attacker.get(timestep);
			}
			
			DefenderTactic d = null;
			
			if (timestep < defender.size()) {
				d = defender.get(timestep);
			}
			
			if (a != null) {
				sys.acceptTactic(a);
			}
			
			if (d != null) {
				sys.acceptTactic(d);
			}
			
			ans[0] += sys.instantaneousUtilityDefender(type);
			ans[1] += sys.instantaneousUtilityAttacker(type);
			
			timestep++;
			
		}
		
		return ans;
	}
	
	public double instantaneousUtilityAttacker(AttackerType type) {
		
		double util = 0;
		
		if (type instanceof Terrorist) {
			
			if (isWebDisrupted()) {
				util += 5;
			}
			
			if (isPaymentServerDisrupted()) {
				util += 7;
			}
			
			if (isPosDisrupted()) {
				util += 10;
			}
			
		}else if (type instanceof Criminal) {
			if (isPosFirmwareCompromised() && isUploadingData()) {
				if (isThrottle()) {
					util += 5;
				}else {
					util += 10;
				}
				
			}
		}else if (type instanceof Intelligence) {
			
			int sources = 0;
			
			if (isAttackerHasWebPassword() || isAttackerHasWebExploited()) {
				sources += scenario.getWebPresenceVal();
			}
			
			if (isAttackerHasPaymentPassword() || isAttackerHasPaymentExploited()) {
				sources += scenario.getPayPresenceVal();
			}
			
			if (isPosFirmwareCompromised()) {
				sources += scenario.getPosPresenceVal();
			}
			
			if (true) {
				
				if (isThrottle()) {
					util += sources;
				}else {
					util += sources * 2;
				}
				
			}
			
		}
		
		return util;
	}
	
	public double instantaneousUtilityDefender(AttackerType type) {
		
		double util = 0;
		
		if (isWebDisrupted()) {
			util -= 5;
		}
		
		if (isPaymentServerDisrupted()) {
			util -= 7;
		}
		
		if (isPosDisrupted()) {
			util -= 10;
		}
		
		if (isCamoflauge()) {
			util -= 1;
		}
		
		if(isWebServerKeylogged()) {
			util -= 2;
		}
		
		if (isPaymentServerKeylogged()) {
			util -= 3;
		}
		
		if (isUploadingData()) {
			if (isThrottle()) {
				util -= 1;
			}else {
				util -= 3;
			}
		}
		
		if (isThrottle()) {
			util -= 1;
		}
		
		util = -1 * instantaneousUtilityAttacker(type);
		
		return util;
	}
	
	public boolean isAttackerHasWebExploited() {
		return attackerHasWebExploited;
	}

	public void setAttackerHasWebExploited(boolean attackerHasWebExploited) {
		this.attackerHasWebExploited = attackerHasWebExploited;
	}

	public boolean isAttackerHasPaymentExploited() {
		return attackerHasPaymentExploited;
	}

	public void setAttackerHasPaymentExploited(boolean attackerHasPaymentExploited) {
		this.attackerHasPaymentExploited = attackerHasPaymentExploited;
	}

	public boolean isAttackerHasWebPassword() {
		return attackerHasWebPassword;
	}

	public void setAttackerHasWebPassword(boolean attackerHasWebPassword) {
		this.attackerHasWebPassword = attackerHasWebPassword;
	}

	public boolean isAttackerHasPaymentPassword() {
		return attackerHasPaymentPassword;
	}

	public void setAttackerHasPaymentPassword(boolean attackerHasPaymentPassword) {
		this.attackerHasPaymentPassword = attackerHasPaymentPassword;
	}
	
	public boolean isAttackerHasWebPresence() {
		return attackerHasWebExploited || attackerHasWebPassword;
	}

	public boolean isAttackerHasPaymentPresence() {
		return attackerHasPaymentExploited || attackerHasPaymentPassword;
	}

	public boolean isWebServerKeylogged() {
		return webServerKeylogged;
	}

	public void setWebServerKeylogged(boolean webServerKeylogged) {
		this.webServerKeylogged = webServerKeylogged;
	}

	public boolean isPaymentServerKeylogged() {
		return paymentServerKeylogged;
	}

	public void setPaymentServerKeylogged(boolean paymentServerKeylogged) {
		this.paymentServerKeylogged = paymentServerKeylogged;
	}

	public boolean isPosFirmwareCompromised() {
		return posFirmwareCompromised;
	}

	public void setPosFirmwareCompromised(boolean posFirmwareCompromised) {
		this.posFirmwareCompromised = posFirmwareCompromised;
	}

	public boolean isUploadingData() {
		return uploadingData;
	}

	public void setUploadingData(boolean uploadingData) {
		this.uploadingData = uploadingData;
	}

	public boolean isWebDisrupted() {
		return webDisrupted;
	}

	public void setWebDisrupted(boolean webDisrupted) {
		this.webDisrupted = webDisrupted;
	}

	public boolean isPaymentServerDisrupted() {
		return paymentServerDisrupted;
	}

	public void setPaymentServerDisrupted(boolean paymentServerDisrupted) {
		this.paymentServerDisrupted = paymentServerDisrupted;
	}

	public boolean isPosDisrupted() {
		return posDisrupted;
	}

	public void setPosDisrupted(boolean posDisrupted) {
		this.posDisrupted = posDisrupted;
	}

	public boolean isCamoflauge() {
		return camoflauge;
	}

	public void setCamoflauge(boolean camoflauge) {
		this.camoflauge = camoflauge;
	}

	public boolean isThrottle() {
		return throttle;
	}

	public void setThrottle(boolean throttle) {
		this.throttle = throttle;
	}
	
	public Tactic acceptTactic(Tactic tactic) {
		Tactic next = null;
		if (tactic.isApplicable(this)) {
			next = tactic.visit(this);
		}
		return next;
	}

	public void setAttackerDetected(boolean b) {
		this.attackerDetected = b;
	}
	
	public boolean getAttackerDetected() {
		return this.attackerDetected;
	}
	
	private static GPNode shallowClone(GPNode input) {
		
		if (input == null) {
			return input;
		}
		
		GPNode ans = input.lightClone();
		
		for (int i = 0; i < ans.children.length; i++) {
			ans.children[i] = input.children[i];
		}
		
		return ans;
	}

	public double[] evaluate(GPIndividual defender, GPIndividual attacker, Intelligence type) {
		GPNode defenderNode = (GPNode) defender.trees[0].child;
		GPNode attackerNode = (GPNode) attacker.trees[0].child;
		
		Tactic defenderTactic = (Tactic) defenderNode;
		Tactic attackertactic = (Tactic) attackerNode;
		
		double[] ans = new double[2];
		
		int timestep = 0;
		
		Random rand = new Random();
		
		boolean outOfTactics = false;
		
		while(!getAttackerDetected() && timestep < TIMESTEP_LIMIT) {
			
			if (attackertactic != null) {
				attackertactic = acceptTactic(attackertactic);
			}
			
			if (defenderTactic != null) {
				defenderTactic = acceptTactic(defenderTactic);
			}
			
			if (getAttackerDetected()) {
				break;
			}
			
			ans[0] -= instantaneousUtilityAttacker(type);
			ans[1] += instantaneousUtilityAttacker(type);
			
			timestep++;
			
			if (attackertactic == null && defenderTactic == null) {
				break;
			}
			
		}
		
		return ans;
		
	}

	public void rollObserved(AttackerTactic tactic) {
		Random rand = new Random();
		
		double roll = rand.nextDouble();
		
		if (roll < tactic.getObs(scenario)) {
			setAttackerDetected(true);
		}
	}

}
