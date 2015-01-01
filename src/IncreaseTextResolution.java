import org.jgap.InvalidConfigurationException;
import org.jgap.gp.impl.GPConfiguration;

public class IncreaseTextResolution extends Actions {

	final double failureRate = 0.3;
	final GPConfiguration gpConf;

	// final int timeToIncreaseTextResolution = 1;

	public IncreaseTextResolution(GPConfiguration gpConf)
			throws InvalidConfigurationException {
		super(gpConf);
		this.gpConf = gpConf;
		this.timeToPeformAction = 1;
	}

	@Override
	public Object clone() {
		try {
			return new IncreaseTextResolution(gpConf);
		} catch (InvalidConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public boolean arePreconditionsSatisfied(CostRewardObject cr) {
		return !cr.getSystemState().getUsingHighTextResolution();
	}

	@Override
	public void results(CostRewardObject cr) {
		cr.getSystemState().toogleUsingHighTextResolution();
		cr.systemResponseTime = cr.systemResponseTime + 2 * cr.getServerCount();
	}

	@Override
	public String toString() {
		return "increaseTextResolution";
	}

	@Override
	public int getTime() {
		return timeToPeformAction;
	}

	@Override
	public String getPrismSucessString() {
		String result = "(clockTime'=clockTime+"
				+ String.valueOf(timeToPeformAction) + ")"
				+ "&(responseTime'= responseTime+(serverCount*2))" + "&(cost'=cost)"
				+ "&(serverCount'= serverCount)" + "&(contentQuality'=2)";
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
