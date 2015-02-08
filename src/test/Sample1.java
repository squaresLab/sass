package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;

import main.EvaluateFitness;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.GPFitnessFunction;
import org.jgap.gp.IGPFitnessEvaluator;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.impl.ProgramChromosome;

import plan.Plan;
import actions.AddServerL1;
import actions.IfSuccessElse;
import actions.IncreaseDatabaseAThreads;
import actions.ReduceTextResolution;

public class Sample1 {

	static int size = 4;
	static int feasibilityReward = 99999;

	public static void main(String[] args) {
		CommandGene[] commands = new CommandGene[size];
		int[] depths = new int[size];

		GPConfiguration gpConf = null;
		try {
			gpConf = new GPConfiguration();
		} catch (InvalidConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1); // lazy error handling
		}
		IGPFitnessEvaluator prismEvaluationFunction = new EvaluateFitness();
		gpConf.setGPFitnessEvaluator(prismEvaluationFunction);
		gpConf.setMaxInitDepth(2);
		try {
			gpConf.setPopulationSize(50);
		} catch (InvalidConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gpConf.setCrossoverProb(0.9f);
		gpConf.setReproductionProb(0.1f);
		gpConf.setNewChromsPercent(0.3f);
		gpConf.setStrictProgramCreation(true);
		gpConf.setUseProgramCache(true);
		gpConf.setNoCommandGeneCloning(false);
		try {
			gpConf.setFitnessFunction((GPFitnessFunction) prismEvaluationFunction);
		} catch (InvalidConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		try {
			commands[0] = new IfSuccessElse(gpConf);
			depths[0] = 0;
			commands[1] = new AddServerL1(gpConf);
			depths[1] = 1;
			commands[2] = new IncreaseDatabaseAThreads(gpConf);
			depths[2] = 1;
			commands[3] = new ReduceTextResolution(gpConf);
			depths[3] = 1;

			ProgramChromosome chromosome = new ProgramChromosome(gpConf, size);
			chromosome.setFunctions(commands);

			Field depthArray = chromosome.getClass().getDeclaredField("m_depth");
			depthArray.setAccessible(true);
			depthArray.set(chromosome, depths);
			EvaluateFitness ef = new EvaluateFitness();
			Plan generatedPlan = ef.createPlan(chromosome, 0);
			ef.makePrismFileFromPlan(generatedPlan, chromosome);

			BufferedReader reader = null;
			try {
				Process p = Runtime
						.getRuntime()
						.exec(
								"/home/zack/Documents/SoftwareModels/Project/prism-4.2.beta1-linux64/bin/prism /home/zack/Documents/SoftwareModels/Project/generatedPrismFile.pm /home/zack/Documents/SoftwareModels/Project/generatedPropertyFile.pctl -dtmc -sim -simsamples 100000");
				try {
					p.waitFor();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					System.exit(1); // lazy error handling
				}
				reader = new BufferedReader(new InputStreamReader(p.getInputStream()));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				System.exit(1); // being lazy
			}

			// parsing result from command
			String line = null;
			try {
				line = reader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String prismResult = null;
			// System.out.println("\nResults from Prism:");
			while (line != null) {
				// System.out.println(line);
				if (line.contains("Result:")) {
					prismResult = line.substring("Result:".length());
					prismResult = prismResult.trim();
					System.out.println("From prism: " + prismResult);
					break;
				}
				try {
					line = reader.readLine();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if (prismResult == null) {
				new Exception().printStackTrace();
				System.exit(1); // being lazy again
			} else {
				double result = Double.parseDouble(prismResult);
				result = result + feasibilityReward; // multiplied by negative 1
																							// because it was made
																							// positive
																							// for prism
				System.out.println("result: " + result);
			}

			System.out.println("finished");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
