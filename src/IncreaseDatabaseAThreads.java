import org.jgap.gp.impl.GPConfiguration;

public class IncreaseDatabaseAThreads extends Actions {

	SystemState ss;

	public IncreaseDatabaseAThreads(SystemState ss, GPConfiguration gpConf)
			throws Exception {
		super(gpConf);
		this.ss = ss;
		this.timeToPeformAction = 180;
	}

	@Override
	public boolean arePreconditionsSatisfied(CostRewardObject cr) {
		if (cr.systemResponseTime < 3) {
			return false;
		}
		return !(ss.getDatabaseAThreadCount() == ss.maxDatabaseThreads);
	}

	@Override
	public void results(CostRewardObject cr) {
		ss.setDatabaseAThreadCount(ss.getDatabaseAThreadCount() + 1);
		cr.systemResponseTime = cr.systemResponseTime - 2;

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
	public void setSystemState(SystemState ss) {
		this.ss = ss;

	}

	@Override
	public SystemState getSystemState() {
		return this.ss;
	}

}
