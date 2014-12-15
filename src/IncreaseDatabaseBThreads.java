import org.jgap.gp.impl.GPConfiguration;

public class IncreaseDatabaseBThreads extends Actions {

	SystemState ss;

	public IncreaseDatabaseBThreads(SystemState ss, GPConfiguration gpConf)
			throws Exception {
		super(gpConf);
		this.ss = ss;
		this.timeToPeformAction = 180;
	}

	@Override
	public boolean arePreconditionsSatisfied(CostRewardObject cr) {
		if (cr.systemResponseTime < 2) {
			return false;
		}
		return !(ss.getDatabaseBThreadCount() > ss.maxDatabaseThreads - 2);
	}

	@Override
	public void results(CostRewardObject cr) {
		ss.setDatabaseAThreadCount(ss.getDatabaseAThreadCount() + 2);
		cr.systemResponseTime = cr.systemResponseTime - 1;

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
	public void setSystemState(SystemState ss) {
		this.ss = ss;

	}

	@Override
	public SystemState getSystemState() {
		return this.ss;
	}

}
