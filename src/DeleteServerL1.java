import java.util.ArrayList;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.impl.GPConfiguration;

public class DeleteServerL1 extends Actions {

	SystemState ss;
	final int serverCost = 15;
	final int responseChange = 5;

	public DeleteServerL1(SystemState ss, GPConfiguration gpConf)
			throws InvalidConfigurationException {
		super(gpConf);
		this.timeToPeformAction = 120;
		this.ss = ss;
	}

	@Override
	public boolean arePreconditionsSatisfied(CostRewardObject cr) {
		if (ss.getUsedServersL1().size() > 2) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public void results(CostRewardObject cr) {
		ArrayList<Integer> usedServers = ss.getUsedServersL1();
		for (int i = ss.validL1Servers.length - 1; i > -1; i--) {
			if (usedServers.contains(new Integer(ss.validL1Servers[i]))) {
				ss.removeUsedServerL1(ss.validL1Servers[i]);
			}
		}
		cr.serverCount = cr.serverCount - 1;
		cr.cost = cr.cost - serverCost;
		cr.systemResponseTime = cr.systemResponseTime + 5;

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
	public void setSystemState(SystemState ss) {
		this.ss = ss;

	}

	@Override
	public SystemState getSystemState() {
		return this.ss;
	}

}
