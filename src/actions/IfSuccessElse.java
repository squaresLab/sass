package actions;
import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.IMutateable;
import org.jgap.gp.impl.GPConfiguration;

public class IfSuccessElse extends CommandGene implements IMutateable {

	GPConfiguration gConfig;

	public IfSuccessElse(GPConfiguration gConfig)
			throws InvalidConfigurationException {
		super(gConfig, 3, CommandGene.VoidClass);
	}

	@Override
	public CommandGene applyMutation(int arg0, double percentage) {
		org.jgap.RandomGenerator randomGen = getGPConfiguration()
				.getRandomGenerator();
		double random = randomGen.nextDouble();
		if (random < percentage) {
			return applyMutation();
		}
		return this;
	}

	public CommandGene applyMutation() {
		// I eventually want to switch what happens for failure and
		// success
		return this;
	}

	@Override
	public String toString() {
		return "if-success (&1) do (&2) else (&3)";
	}

}
