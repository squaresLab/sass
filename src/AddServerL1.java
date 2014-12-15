import java.util.ArrayList;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.impl.GPConfiguration;

public class AddServerL1 extends Actions {

	final int serverCost = 15;
	// final int timeToAddServer = 600;
	SystemState ss;

	public AddServerL1(SystemState ss, GPConfiguration gpConf)
			throws InvalidConfigurationException {
		super(gpConf);
		this.timeToPeformAction = 600;
		this.ss = ss;
	}

	public boolean arePreconditionsSatisfied(CostRewardObject cr) {
		if (ss.validL1Servers.length == ss.getUsedServersL1().size()) {
			return false;
		}
		if (cr.systemResponseTime < 6) {
			return false;
		}
		return true;
	}

	public void results(CostRewardObject cr) {
		ArrayList<Integer> usedServers = ss.getUsedServersL1();
		for (int i = 0; i < ss.validL1Servers.length; i++) {
			if (!(usedServers.contains(new Integer(ss.validL1Servers[i])))) {
				ss.addUsedServerL1(new Integer(ss.validL1Servers[i]));
				break;
			}
		}
		cr.serverCount = cr.serverCount + 1;
		cr.cost = cr.cost + serverCost;
		cr.systemResponseTime = cr.systemResponseTime - 5;

	}

	@Override
	public String toString() {
		return "addL1Server";
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
