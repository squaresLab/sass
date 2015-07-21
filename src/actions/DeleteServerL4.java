package actions;

import java.util.ArrayList;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.impl.GPConfiguration;

public class DeleteServerL4 extends Actions {

	final int serverCost = 30;
	final int responseChange = -12;
	final double failureRate = 0.1;
	final GPConfiguration gpConf;

	public DeleteServerL4(GPConfiguration gpConf)
			throws InvalidConfigurationException {
		super(gpConf);
		this.gpConf = gpConf;
		this.timeToPeformAction = 120;
	}

	@Override
	public Object clone() {
		try {
			return new DeleteServerL4(gpConf);
		} catch (InvalidConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean arePreconditionsSatisfied(CostRewardObject cr) {
		if (cr.getSystemState().getUsedServersL4().size() > 0) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void results(CostRewardObject cr) {
		ArrayList<Integer> usedServers = cr.getSystemState().getUsedServersL4();
		for (int i = cr.getSystemState().validL4Servers.length - 1; i > -1; i--) {
			if (usedServers.contains(new Integer(
					cr.getSystemState().validL4Servers[i]))) {
				cr.getSystemState().removeUsedServerL4(
						cr.getSystemState().validL4Servers[i]);
				break;
			}
		}
		cr.setCost(cr.getCost() - serverCost);
		cr.setSystemResponseTime(cr.getSystemResponseTime() + responseChange);

	}

	@Override
	public String toString() {
		return "deleteL4Server";
	}

	@Override
	public int getTime() {
		return this.timeToPeformAction;
	}

	@Override
	public String getPrismSucessString() {
		String result = "(clockTime'=clockTime+"
				+ String.valueOf(timeToPeformAction) + ")"
				+ "&(responseTime'= responseTime+" + responseChange + ")"
				+ "&(cost'=cost-" + String.valueOf(serverCost) + ")"
				+ "&(serverCount'= serverCount-1)"
				+ "&(contentQuality'=contentQuality)";
		return result;
	}

	@Override
	public String getPrismFailureString() {
		String result = "(clockTime'=clockTime+"
				+ String.valueOf(timeToPeformAction) + ")"
				+ "&(responseTime'= responseTime)" + "&(cost'=cost)"
				+ "&(serverCount'= serverCount)" + "&(contentQuality'=contentQuality)";
		return result;
	}

	@Override
	public double getFailureRate() {
		return failureRate;
	}
}
