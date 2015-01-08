package actions;

import java.util.ArrayList;
import java.util.Random;

import main.RunGA;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.IMutateable;
import org.jgap.gp.impl.GPConfiguration;

public class AddServerL1 extends Actions {

	final int serverCost = 15;
	final int responseChange = -5;
	final double failureRate = 0.1;
	final GPConfiguration gpConf;

	// final int timeToAddServer = 600;

	public AddServerL1(GPConfiguration gpConf)
			throws InvalidConfigurationException {
		super(gpConf);
		this.gpConf = gpConf;
		this.timeToPeformAction = 600;
	}

	@Override
	public Object clone() {
		try {
			return new AddServerL1(gpConf);
		} catch (InvalidConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public boolean arePreconditionsSatisfied(CostRewardObject cr) {
		if (cr.getSystemState().validL1Servers.length == cr.getSystemState()
				.getUsedServersL1().size()) {
			return false;
		}
		if (cr.systemResponseTime < 6) {
			return false;
		}
		return true;
	}

	public void results(CostRewardObject cr) {
		ArrayList<Integer> usedServers = cr.getSystemState().getUsedServersL1();
		for (int i = 0; i < cr.getSystemState().validL1Servers.length; i++) {
			if (!(usedServers.contains(new Integer(
					cr.getSystemState().validL1Servers[i])))) {
				cr.getSystemState().addUsedServerL1(
						new Integer(cr.getSystemState().validL1Servers[i]));
				break;
			}
		}
		cr.setCost(cr.getCost() + serverCost);
		cr.setSystemResponseTime(cr.getSystemResponseTime() + responseChange);
	}

	@Override
	public String toString() {
		return "addL1Server";
	}

	@Override
	public int getTime() {
		return timeToPeformAction;
	}

	@Override
	public String getPrismSucessString() {
		String result = "(clockTime'=clockTime+"
				+ String.valueOf(timeToPeformAction) + ")"
				+ "&(responseTime'= responseTime-" + Math.abs(responseChange) + ")"
				+ "&(cost'=cost+" + String.valueOf(serverCost) + ")"
				+ "&(serverCount'= serverCount+1)"
				+ "&(contentQuality'=contentQuality)";
		return result;
	}

	@Override
	public String getPrismFailureString() {
		String result = "(clockTime'=clockTime+"
				+ String.valueOf(timeToPeformAction) + ")";
		return result;
	}

	@Override
	public double getFailureRate() {
		return failureRate;
	}

}
