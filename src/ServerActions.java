import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.impl.GPConfiguration;

import actions.CostRewardObject;

public abstract class ServerActions extends CommandGene {

	public ServerActions(final GPConfiguration a_conf)
			throws InvalidConfigurationException {
		super(a_conf, 0, CommandGene.VoidClass);
		// TODO Auto-generated constructor stub
	}

	int timeToPeformAction;

	public abstract boolean arePreconditionsSatisfied(int i);

	public abstract void results(int i, CostRewardObject cr);

}
