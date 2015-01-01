import java.util.ArrayList;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.impl.GPConfiguration;

public class DeleteServerL1 extends Actions {

	final int serverCost = 15;
	final int responseChange = 5;
	final double failureRate = 0.1;
	final GPConfiguration gpConf;

	public DeleteServerL1(GPConfiguration gpConf)
			throws InvalidConfigurationException {
		super(gpConf);
		this.timeToPeformAction = 120;
		this.gpConf = gpConf;
	}

	@Override
	public Object clone() {
		try {
			return new DeleteServerL1(gpConf);
		} catch (InvalidConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean arePreconditionsSatisfied(CostRewardObject cr) {
		if (cr.getSystemState().getUsedServersL1().size() > 1) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void results(CostRewardObject cr) {
		ArrayList<Integer> usedServers = cr.getSystemState().getUsedServersL1();
		for (int i = cr.getSystemState().validL1Servers.length - 1; i > -1; i--) {
			if (usedServers.contains(new Integer(
					cr.getSystemState().validL1Servers[i]))) {
				cr.getSystemState().removeUsedServerL1(
						cr.getSystemState().validL1Servers[i]);
				break;
			}
		}
		cr.setCost(cr.getCost() - serverCost);
		cr.setSystemResponseTime(cr.getSystemResponseTime() + responseChange);
	}

	@Override
	public String toString() {
		return "deleteL1Server";
	}

	@Override
	public int getTime() {
		return timeToPeformAction;
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
				+ String.valueOf(timeToPeformAction) + ")";
		return result;
	}

	@Override
	public double getFailureRate() {
		return failureRate;
	}

}
