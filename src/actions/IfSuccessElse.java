package actions;

import java.util.Random;

import main.RunGA;

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
	public String toString() {
		return "if-success (&1) do (&2) else (&3)";
	}

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
