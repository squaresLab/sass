import org.jgap.gp.impl.GPConfiguration;

public class ReduceTextResolution extends Actions {

	SystemState ss;

	public ReduceTextResolution(SystemState ss, GPConfiguration gpConf)
			throws Exception {
		super(gpConf);
		this.ss = ss;
		this.timeToPeformAction = 60;
	}

	@Override
	public boolean arePreconditionsSatisfied(CostRewardObject cr) {
		if (cr.systemResponseTime <= 2 * cr.serverCount) {
			return false;
		}
		return ss.getUsingHighTextResolution();
	}

	@Override
	public void results(CostRewardObject cr) {
		ss.toogleUsingHighTextResolution();
		cr.systemResponseTime = cr.systemResponseTime - 2 * cr.serverCount;
		cr.setUsingHighTextResolution(false);
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
	public void setSystemState(SystemState ss) {
		this.ss = ss;

	}

	@Override
	public SystemState getSystemState() {
		return this.ss;
	}

}
