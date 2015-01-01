package actions;
import java.util.ArrayList;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.impl.GPConfiguration;

public class AddServerL2 extends Actions {

	final int serverCost = 20;
	final int responseChange = -5;
	final double failureRate = 0.1;
	final GPConfiguration gpConf;

	public AddServerL2(GPConfiguration gpConf)
			throws InvalidConfigurationException {
		super(gpConf);
		this.gpConf = gpConf;
		this.timeToPeformAction = 600;
	}

	@Override
	public Object clone() {
		try {
			return new AddServerL2(gpConf);
		} catch (InvalidConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public boolean arePreconditionsSatisfied(CostRewardObject cr) {
		if (cr.getSystemState().validL2Servers.length == cr.getSystemState()
				.getUsedServersL2().size()) {
			return false;
		}
		if (cr.systemResponseTime < 6) {
			return false;
		}
		return true;
	}

	public void results(CostRewardObject cr) {
		ArrayList<Integer> usedServers = cr.getSystemState().getUsedServersL2();
		for (int i = 0; i < cr.getSystemState().validL2Servers.length; i++) {
			if (!(usedServers.contains(new Integer(
					cr.getSystemState().validL2Servers[i])))) {
				cr.getSystemState().addUsedServerL2(
						new Integer(cr.getSystemState().validL2Servers[i]));
				break;
			}
		}
		cr.setCost(cr.getCost() + serverCost);
		cr.setSystemResponseTime(cr.getSystemResponseTime() + responseChange);
	}

	@Override
	public String toString() {
		return "addL2Server";
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
