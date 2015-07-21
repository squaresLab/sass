package deterministicIterativeDeeping;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;

import main.EvaluateFitness;
import main.TooManyBranchesException;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.function.SubProgram;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.impl.ProgramChromosome;

import plan.Plan;
import actions.AddServerL1;
import actions.AddServerL10;
import actions.AddServerL2;
import actions.AddServerL3;
import actions.AddServerL4;
import actions.AddServerL5;
import actions.AddServerL6;
import actions.AddServerL7;
import actions.AddServerL8;
import actions.AddServerL9;
import actions.DecreaseDatabaseAThreads;
import actions.DecreaseDatabaseBThreads;
import actions.DeleteServerL1;
import actions.DeleteServerL10;
import actions.DeleteServerL2;
import actions.DeleteServerL3;
import actions.DeleteServerL4;
import actions.DeleteServerL5;
import actions.DeleteServerL6;
import actions.DeleteServerL7;
import actions.DeleteServerL8;
import actions.DeleteServerL9;
import actions.IfSuccessElse;
import actions.IncreaseDatabaseAThreads;
import actions.IncreaseDatabaseBThreads;
import actions.IncreaseTextResolution;
import actions.ReduceTextResolution;

public class IterativeDeepening {

	static int maxPlanSize = 2;

	static GPConfiguration gpConf = null;

	static EvaluateFitness ef = new EvaluateFitness();

	static double bestFitness = 0;

	static ProgramChromosome bestPlan = null;

	static PrintWriter writer = null;

	static {
		try {
			gpConf = new GPConfiguration();
			gpConf.setFitnessEvaluator(ef);
			writer = new PrintWriter(
					"/home/zack/Documents/SoftwareModels/Project/deterministicTimings.txt",
					"UTF-8");
		} catch (Exception e) {

		}

	}

	public static void main(String args[]) {
		long startTime = System.currentTimeMillis();
		System.out.println("Starting application");
		for (int i = 1; i < maxPlanSize + 1; i++) {
			int[] currentCombination = new int[i];
			// list possible actions, save count of possible actions
			// to the countOfPossibleActions variable
			int countOfPossibleActions = 28;
			for (int j = 0; j < i; j++) {
				// System.out.println("j is: " + j);
				recursiveIterateOverIndex(currentCombination, countOfPossibleActions,
						0, i);
			}
			long endTime = System.currentTimeMillis();
			writer.println("Time for plans of " + i + " size: "
					+ ((double) ((endTime - startTime)) / 1000) + " in seconds");
			startTime = endTime;
		}
		writer.close();
		System.out.println("===============");
		System.out.println("Best plan: " + bestPlan.toStringNorm(0));
		System.out.println("Best Fitness: " + bestFitness);
	}

	public static void recursiveIterateOverIndex(int[] currentCombination,
			int countOfPossibleActions, int index, int indexesLeft) {
		if (indexesLeft == 0) {
			performActionWithCombination(currentCombination);
		} else {

			for (int k = 0; k < countOfPossibleActions; k++) {
				currentCombination[index] = k;
				recursiveIterateOverIndex(currentCombination, countOfPossibleActions,
						index + 1, indexesLeft - 1);
			}
			currentCombination[index] = 0;
		}
	}

	public static void performActionWithCombination(int[] combination) {
		CommandGene[] genes = new CommandGene[combination.length];

		boolean nullInPlan = false;
		for (int i = 0; i < combination.length; i++) {
			System.out.println("gene selection: " + combination[i]);
			CommandGene command = getGene(combination[i], combination.length - i - 1);

			if (command == null) {
				System.out.println("gene is null");
				nullInPlan = true;
				break;
			}
			System.out.println("gene is: " + command.toString());
			genes[i] = command;

		}
		if (!nullInPlan) {
			ProgramChromosome chromosome = null;
			try {
				chromosome = new ProgramChromosome(gpConf, combination.length);
			} catch (InvalidConfigurationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				chromosome.setFunctions(genes);
			} catch (InvalidConfigurationException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

			chromosome.redepth();
			if (chromosome.validPlanSize(0) != combination.length) {
				System.out.println("generated plan is in invalid plan for that size");
				return;
			}
			double fitness = 0;

			ArrayList<ArrayList<CommandGene>> planList = null;
			boolean tooManyBranches = false;
			try {
				planList = ef.generatePossiblePlanExecutions(chromosome);
			} catch (TooManyBranchesException e) {
				System.out.println("Too many branches: " + e.getNumberOfBranches());
				tooManyBranches = true;
			}
			// System.out.println("plan list size: " + planList.size());
			boolean aPlanIsInfeasible = false;
			for (int i = 0; i < planList.size(); i++) {
				boolean isPlanFeasible = ef.checkPlanFeasibility(planList.get(i));
				if (!isPlanFeasible || tooManyBranches) {
					aPlanIsInfeasible = true;
					break;
				}
			}
			if (!aPlanIsInfeasible && !tooManyBranches) {
				System.out.println("A plan: " + chromosome.toStringNorm(0));
				Plan generatedPlan = ef.createPlan(chromosome, 0);
				ef.makePrismFileFromPlan(generatedPlan, chromosome);

				BufferedReader reader = null;
				try {
					/*
					 * String projectDir = Paths.get(".").toAbsolutePath().normalize()
					 * .toString(); final String prismFile = projectDir +
					 * "/GeneratedFiles/generatedPrismFile.pm"; final String pctlFile =
					 * projectDir + "/GeneratedFiles/generatedPropertyFile.pctl";
					 */
					final String prismFile = "/home/zack/Documents/SoftwareModels/Project/generatedPrismFile.pm";
					final String pctlFile = "/home/zack/Documents/SoftwareModels/Project/generatedPropertyFile.pctl";

					Process p = Runtime.getRuntime().exec(
							"/home/zack/Documents/SoftwareModels/Project/prism-4.2.1-src/bin/prism "
									+ prismFile + " " + pctlFile
									+ " -dtmc -sim -simsamples 100000");
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
					// result = result * -1 + feasibilityReward; // multiplied by negative
					// 1
					// because it was made positive
					// for prism
					result = result + ef.feasibilityReward;

					/*
					 * if (sub) { System.out.println("sub fitness value:" +
					 * String.valueOf(result)); } else {
					 * System.out.println("sub is false"); }
					 */
					if ((new Double(result)).intValue() == ef.feasibilityReward) {
						System.out.println("No cost for plan.");
						System.out.println("Plan: " + chromosome.toStringNorm(0));
						System.exit(1);
					}
					System.out.println("Cost of plan:" + result);
					fitness = result;
				}
			}

			System.out.println("Plan fitness: " + fitness);
			if (fitness > bestFitness) {
				bestPlan = chromosome;
			}
		}
	}

	// return fitness for comparison - exactly how to compare needs to be decided
	// later

	public static CommandGene getGene(int geneChoice,
			int commandsPositionsAfterCurrentGene) {
		try {
			switch (geneChoice) {
			case 0:
				if (commandsPositionsAfterCurrentGene > 1) {
					return new SubProgram(gpConf, new Class[] { CommandGene.VoidClass,
							CommandGene.VoidClass }, true);
				} else {
					return null;
				}

			case 1:
				if (commandsPositionsAfterCurrentGene > 2) {
					return new IfSuccessElse(gpConf);
				} else {
					return null;
				}

			case 2:
				return new AddServerL1(gpConf);
			case 3:
				return new AddServerL2(gpConf);
			case 4:
				return new AddServerL3(gpConf);
			case 5:
				return new AddServerL4(gpConf);
			case 6:
				return new AddServerL5(gpConf);
			case 7:
				return new AddServerL6(gpConf);
			case 8:
				return new AddServerL7(gpConf);
			case 9:
				return new AddServerL8(gpConf);
			case 10:
				return new AddServerL9(gpConf);
			case 11:
				return new AddServerL10(gpConf);
			case 12:
				return new DeleteServerL1(gpConf);
			case 13:
				return new DeleteServerL2(gpConf);
			case 14:
				return new DeleteServerL3(gpConf);
			case 15:
				return new DeleteServerL4(gpConf);
			case 16:
				return new DeleteServerL5(gpConf);
			case 17:
				return new DeleteServerL6(gpConf);
			case 18:
				return new DeleteServerL7(gpConf);
			case 19:
				return new DeleteServerL8(gpConf);
			case 20:
				return new DeleteServerL9(gpConf);
			case 21:
				return new DeleteServerL10(gpConf);
			case 22:
				return new IncreaseDatabaseAThreads(gpConf);
			case 23:
				return new IncreaseDatabaseBThreads(gpConf);
			case 24:
				return new DecreaseDatabaseAThreads(gpConf);
			case 25:
				return new DecreaseDatabaseBThreads(gpConf);
			case 26:
				return new IncreaseTextResolution(gpConf);
			case 27:
				return new ReduceTextResolution(gpConf);
			default:
				return null;
			}
		} catch (Exception e) {
			return null;
		}
	}

	/*
	 * new SubProgram(gpConf, new Class[] { CommandGene.VoidClass,
	 * CommandGene.VoidClass }, true), new IfSuccessElse(gpConf), new
	 * AddServerL1(gpConf), new AddServerL2(gpConf), new AddServerL3(gpConf), new
	 * AddServerL4(gpConf), new AddServerL5(gpConf), new AddServerL6(gpConf), new
	 * AddServerL7(gpConf), new AddServerL8(gpConf), new AddServerL9(gpConf), new
	 * AddServerL10(gpConf), new DeleteServerL1(gpConf), new
	 * DeleteServerL2(gpConf), // nonclassic new DeleteServerL3(gpConf), new
	 * DeleteServerL4(gpConf), new DeleteServerL5(gpConf), new
	 * DeleteServerL6(gpConf), new DeleteServerL7(gpConf), new
	 * DeleteServerL8(gpConf), new DeleteServerL9(gpConf), new
	 * DeleteServerL10(gpConf), new IncreaseDatabaseAThreads(gpConf), new
	 * IncreaseDatabaseBThreads(gpConf), // nonclassic new
	 * DecreaseDatabaseAThreads(gpConf), new DecreaseDatabaseBThreads(gpConf), new
	 * IncreaseTextResolution(gpConf), // nonclassic new
	 * ReduceTextResolution(gpConf),
	 */

}
