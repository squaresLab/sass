package actions;
import org.jgap.InvalidConfigurationException;
import org.jgap.gp.impl.GPConfiguration;

public class ReduceTextResolution extends Actions {

	final double failureRate = 0.3;
	final GPConfiguration gpConf;

	public ReduceTextResolution(GPConfiguration gpConf) throws Exception {
		super(gpConf);
		this.gpConf = gpConf;
		this.timeToPeformAction = 60;
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
		if (cr.systemResponseTime <= 2 * cr.getServerCount()) {
			return false;
		}
		return cr.getSystemState().getUsingHighTextResolution();
	}

	@Override
	public void results(CostRewardObject cr) {
		cr.getSystemState().toogleUsingHighTextResolution();
		cr.systemResponseTime = cr.systemResponseTime - 2 * cr.getServerCount();
	}

	@Override
	public String toString() {
		return "reduceTextResolution";
	}

	@Override
	public int getTime() {
		return timeToPeformAction;
	}

	@Override
	public String getPrismSucessString() {
		String result = "(clockTime'=clockTime+"
				+ String.valueOf(timeToPeformAction) + ")"
				+ "&(responseTime'= responseTime-(serverCount*2))" + "&(cost'=cost)"
				+ "&(serverCount'= serverCount)" + "&(contentQuality'=1)";
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
