import org.jgap.gp.impl.GPConfiguration;

public class IncreaseTextResolution extends Actions {

	SystemState ss;

	// final int timeToIncreaseTextResolution = 1;

	public IncreaseTextResolution(SystemState ss, GPConfiguration gpConf)
			throws Exception {
		super(gpConf);
		this.ss = ss;
		this.timeToPeformAction = 1;
	}

	@Override
	public boolean arePreconditionsSatisfied(CostRewardObject cr) {
		return !ss.getUsingHighTextResolution();
	}

	@Override
	public void results(CostRewardObject cr) {
		ss.toogleUsingHighTextResolution();
		cr.systemResponseTime = cr.systemResponseTime + 2 * cr.serverCount;
		cr.setUsingHighTextResolution(true);
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
	public void setSystemState(SystemState ss) {
		this.ss = ss;

	}

	@Override
	public SystemState getSystemState() {
		return this.ss;
	}
}
