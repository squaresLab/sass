package test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.util.ArrayList;

import main.EvaluateFitness;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.GPFitnessFunction;
import org.jgap.gp.IGPFitnessEvaluator;
import org.jgap.gp.function.SubProgram;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.impl.ProgramChromosome;

import plan.Plan;
import actions.AddServerL1;
import actions.DeleteServerL1;
import actions.DeleteServerL2;
import actions.IfSuccessElse;
import actions.IncreaseDatabaseAThreads;
import actions.IncreaseDatabaseBThreads;

public class ComplicatedPlan {
	public static void main(String[] args) {
		CommandGene[] commands = new CommandGene[12];
		int[] depths = new int[12];

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
			commands[1] = new IfSuccessElse(gpConf);
			depths[1] = 1;
			commands[2] = new DeleteServerL2(gpConf);
			depths[2] = 2;
			commands[3] = new IncreaseDatabaseBThreads(gpConf);
			depths[3] = 2;
			commands[4] = new AddServerL1(gpConf);
			depths[4] = 2;
			commands[5] = new SubProgram(gpConf, new Class[] { CommandGene.VoidClass,
					CommandGene.VoidClass }, true);
			depths[5] = 1;
			commands[6] = new IncreaseDatabaseBThreads(gpConf);
			depths[6] = 2;
			commands[7] = new IncreaseDatabaseBThreads(gpConf);
			depths[7] = 2;
			commands[8] = new IfSuccessElse(gpConf);
			depths[8] = 1;
			commands[9] = new DeleteServerL1(gpConf);
			depths[9] = 2;
			commands[10] = new IncreaseDatabaseAThreads(gpConf);
			depths[10] = 2;
			commands[11] = new IncreaseDatabaseBThreads(gpConf);
			depths[11] = 2;

			ProgramChromosome chromosome = new ProgramChromosome(gpConf, 12);
			chromosome.setFunctions(commands);

			Field depthArray = chromosome.getClass().getDeclaredField("m_depth");
			depthArray.setAccessible(true);
			depthArray.set(chromosome, depths);
			System.out.println("Created plan: " + chromosome.toStringNorm(0));
			EvaluateFitness ef = new EvaluateFitness();
			Plan generatedPlan = ef.createPlan(chromosome, 0);
			System.out.println("Our plan: " + generatedPlan.planString());
			System.out.println("Copy: "
					+ generatedPlan.getStartNode().deepCopy().planString());

			ef.makePrismFileFromPlan(generatedPlan, chromosome);
			System.out.println("finished generating files");

			ArrayList<ArrayList<CommandGene>> planList = ef
					.generatePossiblePlanExecutions(chromosome);

			// System.out.println("plan list size: " + planList.size());
			for (int i = 0; i < planList.size(); i++) {
				boolean isPlanFeasible = ef.checkPlanFeasibility(planList.get(i));
				/*
				 * if (sub) {
				 * System.out.println("sub is true after checking for feasibility"); }
				 */
				ArrayList<CommandGene> testPlan = planList.get(i);
				System.out.print("A plan: ");
				for (int j = 0; j < testPlan.size(); j++) {
					System.out.print(testPlan.get(j).toString() + ", ");
				}
				System.out.print("\n");

				if (!isPlanFeasible) {

					System.out.println("Plan failed");
					System.exit(1);
				}
			}
			System.out.println("All plans succeeded");

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
				result = result + 99999; // multiplied by negative 1
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
