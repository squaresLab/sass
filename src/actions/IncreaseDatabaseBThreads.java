package actions;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.impl.GPConfiguration;

public class IncreaseDatabaseBThreads extends Actions {

	final int responseChange = -1;
	final int threadChange = 2;
	final double failureRate = 0.2;
	final GPConfiguration gpConf;

	public IncreaseDatabaseBThreads(GPConfiguration gpConf)
			throws InvalidConfigurationException {
		super(gpConf);
		this.timeToPeformAction = 180;
		this.gpConf = gpConf;
	}

	@Override
	public Object clone() {
		try {
			return new IncreaseDatabaseBThreads(gpConf);
		} catch (InvalidConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean arePreconditionsSatisfied(CostRewardObject cr) {
		if (cr.systemResponseTime < 2) {
			return false;
		}
		return !(cr.getSystemState().getDatabaseBThreadCount() > cr
				.getSystemState().maxDatabaseThreads - 2);
	}

	@Override
	public void results(CostRewardObject cr) {
		cr.getSystemState().setDatabaseAThreadCount(
				cr.getSystemState().getDatabaseAThreadCount() + threadChange);
		cr.systemResponseTime = cr.systemResponseTime + responseChange;

	}

	@Override
	public String toString() {
		return "increaseDatabaseBThreads";
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
				+ "&(cost'=cost)" + "&(serverCount'= serverCount)"
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
