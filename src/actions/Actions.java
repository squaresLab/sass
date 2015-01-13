package actions;

import java.util.Random;

import main.RunGA;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.IGPProgram;
import org.jgap.gp.IMutateable;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.impl.ProgramChromosome;
import org.jgap.util.ICloneable;

/**
 * This class in the parent classes for single action commands in the programs.
 * These actions do not allow for branching or multiple statements.
 */
public abstract class Actions extends CommandGene implements IMutateable {

	/**
	 * The default constructor for the class
	 * 
	 * @param a_conf
	 *          - the GPConfiguration used for constructing a CommandGene
	 * @throws InvalidConfigurationException
	 *           - if there is an error in the parent constructor
	 */
	public Actions(final GPConfiguration a_conf)
			throws InvalidConfigurationException {
		super(a_conf, 0, CommandGene.VoidClass);
		// TODO Auto-generated constructor stub
	}

	/**
	 * the number of seconds to perform an action
	 */
	int timeToPeformAction;

	/**
	 * The function that tests if the action is allowed for the current state
	 * 
	 * @param cr
	 *          - the current state
	 * @return - true if the action is allowed or false otherwise
	 */
	public abstract boolean arePreconditionsSatisfied(CostRewardObject cr);

	/**
	 * Updates the state as if the action occurred in the current state
	 * 
	 * This method is used to adjust the state when testing command sequences
	 * 
	 * @param cr
	 *          - the current state
	 */

	public abstract void results(CostRewardObject cr);

	/**
	 * returns the amount of the time the action takes
	 * 
	 * @return - the amount of time the action takes.
	 */
	public abstract int getTime();

	/**
	 * returns the string used to update the prism state if the action succeeds
	 * 
	 * @return the prism update string
	 */
	public abstract String getPrismSucessString();

	/**
	 * returns the string used to update the prism state if the action fails
	 * 
	 * @return the prism update string
	 */
	public abstract String getPrismFailureString();

	/**
	 * returns the failure rate of the action
	 * 
	 * used to calculate how likely the failure updates and the success update are
	 * 
	 * @return the failure rate
	 */
	public abstract double getFailureRate();

	/**
	 * returns how to mutate an action
	 * 
	 * currently I have actions mutating into a random new command
	 * 
	 * must have nodeSets initialized in RunGA before this method will work
	 * 
	 * @param arg0
	 *          - not used (from CommandGene)
	 * @param arg1
	 *          - the random role to update the gene, tested against the update
	 *          percentage to see if the gene should be updated
	 * @return either the original gene or a gene that is the result of mutating
	 *         the original gene
	 * 
	 *         *NOTE - I think there is an error if the arity of a gene changes;
	 *         need to look into this later
	 */
	@Override
	public CommandGene applyMutation(int arg0, double arg1)
			throws InvalidConfigurationException {
		double val = Math.random();
		if (val < arg1) {
			CommandGene[][] possibleCommands = RunGA.nodeSets;
			Random rand;
			CommandGene result = null;
			while (!!(result instanceof Actions)) {
				rand = new Random();
				int choice = rand.nextInt(possibleCommands[0].length);
				result = possibleCommands[0][choice];
			}
			return result;
		}
		return this;
	}
}
