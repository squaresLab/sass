package actions;

import java.util.Random;

import main.RunGA;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.IMutateable;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.util.ICloneable;

public abstract class Actions extends CommandGene implements ICloneable,
		IMutateable {

	public Actions(final GPConfiguration a_conf)
			throws InvalidConfigurationException {
		super(a_conf, 0, CommandGene.VoidClass);
		// TODO Auto-generated constructor stub
	}

	int timeToPeformAction;

	public abstract boolean arePreconditionsSatisfied(CostRewardObject cr);

	public abstract void results(CostRewardObject cr);

	public abstract int getTime();

	public abstract String getPrismSucessString();

	public abstract String getPrismFailureString();

	public abstract double getFailureRate();

	// added because I couldn't tell the difference between different objects
	// and the genetic algorithm only clones genes, doesn't create new ones.
	@Override
	public abstract Object clone();

	// need to come up with a way to determine what changed.

	@Override
	public CommandGene applyMutation(int arg0, double arg1)
			throws InvalidConfigurationException {
		double val = Math.random();
		if (val < arg1) {
			CommandGene[][] possibleCommands = RunGA.nodeSets;
			Random rand = new Random();
			int choice = rand.nextInt(possibleCommands[0].length);
			return possibleCommands[0][choice];
		}
		return this;
	}
}
