package bullseye;

import bullseye.attackerTypes.AttackerType;
import bullseye.tactics.Tactic;

public class System {

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
	
	public double instantaneousUtilityAttacker(AttackerType type) {
		return 0;
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
	
	public void acceptTactic(Tactic tactic) {
		if (tactic.isApplicable(this)) {
			tactic.visit(this);
		}
	}

}
