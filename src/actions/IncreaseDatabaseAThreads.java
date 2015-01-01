package actions;
import org.jgap.InvalidConfigurationException;
import org.jgap.gp.impl.GPConfiguration;

public class IncreaseDatabaseAThreads extends Actions {

	final int responseChange = -2;
	final int threadChange = 1;
	final double failureRate = 0.2;
	final GPConfiguration gpConf;

	public IncreaseDatabaseAThreads(GPConfiguration gpConf)
			throws InvalidConfigurationException {
		super(gpConf);
		this.gpConf = gpConf;
		this.timeToPeformAction = 180;
	}

	@Override
	public Object clone() {
		try {
			return new IncreaseDatabaseAThreads(gpConf);
		} catch (InvalidConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean arePreconditionsSatisfied(CostRewardObject cr) {
		if (cr.systemResponseTime < 3) {
			return false;
		}
		return !(cr.getSystemState().getDatabaseAThreadCount() == cr
				.getSystemState().maxDatabaseThreads);
	}

	@Override
	public void results(CostRewardObject cr) {
		cr.getSystemState().setDatabaseAThreadCount(
				cr.getSystemState().getDatabaseAThreadCount() + threadChange);
		cr.systemResponseTime = cr.systemResponseTime + responseChange;

	}

	@Override
	public String toString() {
		return "increaseDatabaseAThreads";
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
				+ String.valueOf(timeToPeformAction) + ")";
		return result;
	}

	@Override
	public double getFailureRate() {
		return failureRate;
	}

}
