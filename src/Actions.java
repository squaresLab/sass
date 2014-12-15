import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.impl.GPConfiguration;

public abstract class Actions extends CommandGene {

	public Actions(final GPConfiguration a_conf)
			throws InvalidConfigurationException {
		super(a_conf, 0, CommandGene.VoidClass);
		// TODO Auto-generated constructor stub
	}

	int timeToPeformAction;

	public abstract boolean arePreconditionsSatisfied(CostRewardObject cr);

	public abstract void results(CostRewardObject cr);

	public abstract int getTime();

	public abstract void setSystemState(SystemState ss);

	public abstract SystemState getSystemState();

}
