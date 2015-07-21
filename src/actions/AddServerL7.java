package actions;

import java.util.ArrayList;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.impl.ProgramChromosome;

/**
 * the action used to represent adding a server at location 3
 * 
 * @author Zack
 * 
 */
public class AddServerL7 extends Actions {

	// the cost of adding server at location 2
	final int serverCost = 5;
	// the change in response time of the system (negative means a decrease in
	// response time)
	final int responseChange = -2;
	// the chance the action will fail (out of 1)
	final double failureRate = 0.1;
	// the configuration used to initialize the parent CommandGene
	final GPConfiguration gpConf;

	/**
	 * Initializes an add Location 7 Server action
	 * 
	 * @param gpConf
	 *          - the configuration used to initialize the parent
	 * @throws InvalidConfigurationException
	 *           - the exception throw if initializing the parent fails
	 */
	public AddServerL7(GPConfiguration gpConf)
			throws InvalidConfigurationException {
		super(gpConf);
		this.gpConf = gpConf;
		this.timeToPeformAction = 600;
	}

	/**
	 * Returns whether the action can be successfully applied in the state
	 * 
	 * @return true if the action can be applied and false otherwise
	 */
	public boolean arePreconditionsSatisfied(CostRewardObject cr) {
		if (cr.getSystemState().validL3Servers.length == cr.getSystemState()
				.getUsedServersL7().size()) {
			return false;
		}
		if (cr.systemResponseTime < -1 * responseChange + 1) {
			return false;
		}
		return true;
	}

	/**
	 * updates a state by adding a server at location 7
	 * 
	 * @return cr - the state
	 */
	public void results(CostRewardObject cr) {
		ArrayList<Integer> usedServers = cr.getSystemState().getUsedServersL7();
		for (int i = 0; i < cr.getSystemState().validL7Servers.length; i++) {
			if (!(usedServers.contains(new Integer(
					cr.getSystemState().validL7Servers[i])))) {
				cr.getSystemState().addUsedServerL7(
						new Integer(cr.getSystemState().validL7Servers[i]));
				break;
			}
		}
		cr.setCost(cr.getCost() + serverCost);
		cr.setSystemResponseTime(cr.getSystemResponseTime() + responseChange);
	}

	/**
	 * Returns the string representation of adding a server at location 7 used
	 * when print a plan
	 * 
	 * @return the string representation of adding a server at location 7
	 */
	@Override
	public String toString() {
		return "addL7Server";
	}

	/**
	 * returns the time to perform the action
	 * 
	 * @return the time to perform the action
	 */
	@Override
	public int getTime() {
		return timeToPeformAction;
	}

	/**
	 * Return the prism state changes for successfully adding a server at location
	 * 7
	 * 
	 * @return the prism state change string
	 */
	@Override
	public String getPrismSucessString() {
		String result = "(clockTime'=clockTime+"
				+ String.valueOf(timeToPeformAction) + ")"
				+ "&(responseTime'= responseTime-" + Math.abs(responseChange) + ")"
				+ "&(cost'=cost+" + String.valueOf(serverCost) + ")"
				+ "&(serverCount'= serverCount+1)"
				+ "&(contentQuality'=contentQuality)";
		return result;
	}

	/**
	 * Return the prism state changes for failing to add a server at location 7
	 * 
	 * @return the state change string
	 */
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
