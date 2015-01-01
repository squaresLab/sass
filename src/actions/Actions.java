package actions;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.util.ICloneable;

public abstract class Actions extends CommandGene implements ICloneable {

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
}
