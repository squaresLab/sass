package actions;

import java.util.ArrayList;
import java.util.Random;

import main.RunGA;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.IMutateable;
import org.jgap.gp.impl.GPConfiguration;

/**
 * This class represents the action of adding a server at location 1
 * 
 * @author Zack
 * 
 */
public class AddServerL1 extends Actions {

	// the cost to add a server
	final int serverCost = 15;
	// the change in response time of the system (negative means decrease)
	final int responseChange = -5;
	// the failure rate (out of 1, how often will the action fail)
	final double failureRate = 0.1;
	// the gpConf used in initializing the parent CommandGene
	final GPConfiguration gpConf;

	/**
	 * The initializer for the class
	 * 
	 * @param gpConf
	 *          - the GPConfiguration for the CommandGene initialization
	 * @throws InvalidConfigurationException
	 *           - thrown if the parent initialization fails
	 */
	public AddServerL1(GPConfiguration gpConf)
			throws InvalidConfigurationException {
		super(gpConf);
		this.gpConf = gpConf;
		this.timeToPeformAction = 600;
	}

	/**
	 * Tests if the given state allows a server to be added at location 1
	 * 
	 * @param cr
	 *          - the state
	 * @result true if the state allows the action, false otherwise
	 */
	public boolean arePreconditionsSatisfied(CostRewardObject cr) {
		if (cr.getSystemState().validL1Servers.length == cr.getSystemState()
				.getUsedServersL1().size()) {
			return false;
		}
		if (cr.systemResponseTime < 6) {
			return false;
		}
		return true;
	}

	/**
	 * updates the state to account for adding a server at location 1
	 * 
	 * @param cr
	 *          - the state
	 */
	public void results(CostRewardObject cr) {
		ArrayList<Integer> usedServers = cr.getSystemState().getUsedServersL1();
		for (int i = 0; i < cr.getSystemState().validL1Servers.length; i++) {
			if (!(usedServers.contains(new Integer(
					cr.getSystemState().validL1Servers[i])))) {
				cr.getSystemState().addUsedServerL1(
						new Integer(cr.getSystemState().validL1Servers[i]));
				break;
			}
		}
		cr.setCost(cr.getCost() + serverCost);
		cr.setSystemResponseTime(cr.getSystemResponseTime() + responseChange);
	}

	/**
	 * The string used when printing the action in a plan
	 * 
	 * @return the string representation of the class
	 */
	@Override
	public String toString() {
		return "addL1Server";
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
	 * returns the string used in prism to update the prism state when the action
	 * succeeds
	 * 
	 * @return the string for prism when the action succeeds
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
	 * returns the string used in prism to update the prism state when the action
	 * fails
	 * 
	 * @return the string for prism when the action fails
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
