import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import org.jgap.gp.CommandGene;
import org.jgap.gp.GPFitnessFunction;
import org.jgap.gp.IGPFitnessEvaluator;
import org.jgap.gp.IGPProgram;

public class EvaluateFitness extends GPFitnessFunction implements
		IGPFitnessEvaluator {

	int feasibilityReward = 99999;
	boolean sub = false; // used for debugging
	int uniqueStateCount;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected double evaluate(IGPProgram currentPlan) {
		// System.out.println("Starting to evaluate a plan!");
		// attempting to print plan
		// System.out.println("New Plan!");

		boolean isPlanFeasible = checkPlanFeasibility(currentPlan);
		/*
		 * if (sub) {
		 * System.out.println("sub is true after checking for feasibility"); }
		 */
		if (!isPlanFeasible) {
			// return -1 * infeasibilityPenalty; // some large negative number to
			// always
			// lose the
			/*
			 * if (sub) { System.out.println("sub is invalid plan"); }
			 */

			return 0;
			// fitness
			// comparisons later
		}
		/*
		 * System.out.println("Number of choromsomes: " + amount); for (int i = 0; i
		 * < amount; i++) {
		 * System.out.println(currentPlan.getChromosome(i).toStringNorm(0)); }
		 */
		/*
		 * if (sub) { System.out.println("sub is true before making prism file"); }
		 */
		makePrismFiles(currentPlan);
		if (uniqueStateCount == 0) {
			return 0;
		}
		// end attempt to print plan
		BufferedReader reader = null;
		try {
			Process p = Runtime
					.getRuntime()
					.exec(
							"/home/zack/Documents/SoftwareModels/Project/prism-4.2.beta1-linux64/bin/prism /home/zack/Documents/SoftwareModels/Project/generatedPrismFile.sm /home/zack/Documents/SoftwareModels/Project/generatedPropertyFile.pctl -dtmc -sim");
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
			result = result * -1 + feasibilityReward; // multiplied by negative 1
																								// because it was made positive
																								// for prism
			/*
			 * if (sub) { System.out.println("sub fitness value:" +
			 * String.valueOf(result)); } else { System.out.println("sub is false"); }
			 */
			if ((new Double(result)).intValue() == feasibilityReward) {
				System.out.println("No cost for plan.");
				System.out.println("Plan: "
						+ currentPlan.getChromosome(0).toStringNorm(0));
				System.exit(1);
			}
			return result;
		}
		return new Double(0); // shouldn't be reachable but needed for compiler
	}

	// this method steps through a plan to makes sure it is feasible, currently,=
	// it does not handle branching.
	private boolean checkPlanFeasibility(IGPProgram currentPlan) {
		SystemState ss = new SystemState();
		CostRewardObject cr = createCostRewardObject();
		int serverNumber = 3; // need to not hard code it and instead have the plan
													// generate the number.
		int chromosomeSize = currentPlan.getChromosome(0).getSize(0);
		sub = false;
		for (int i = 0; i < chromosomeSize; i++) {
			CommandGene cg = currentPlan.getChromosome(0).getGene(i);
			if (cg.toString().contains("sub")) {
				/*
				 * if (!sub) { System.out.println("contains sub: " +
				 * currentPlan.getChromosome(0).toStringNorm(0)); }
				 */
				sub = true;
				// System.out.println("value of sub after setting to true: "
				// + String.valueOf(sub));
			}
			// System.out.println("value of sub after if: " + String.valueOf(sub));
			if (cg instanceof Actions) {
				((Actions) cg).setSystemState(ss);
				if (((Actions) cg).arePreconditionsSatisfied(cr)) {
					((Actions) cg).results(cr);
					ss = ((Actions) cg).getSystemState();
				} else {
					// System.out.println("value of sub at end of feasiblity check 1:"
					// + String.valueOf(sub));
					// System.out.println("failing command: " + cg.toString() + " pos: "
					// + String.valueOf(i));
					return false;
				}
			} else if (cg instanceof ServerActions) {
				(new Exception()).printStackTrace();
				System.exit(1); // This path shouldn't be feasible anymore but leaving
												// this here in case I made mistake
			}
		}
		// System.out.println("value of sub at end of feasiblity check 2:"
		// + String.valueOf(sub));
		return true;
	}

	private CostRewardObject createCostRewardObject() {
		return (new CostRewardObject(20, 4, 30, true));
	}

	private void makePrismFiles(IGPProgram currentPlan) { // first need to get
		// what is in the current plan to tailor the prism file to it.
		int planSize = currentPlan.size();
		boolean[] containsAction = { false, false, false, false, false, false,
				false, false };
		int[] actionCount = { 0, 0, 0, 0, 0, 0, 0, 0 };
		String[] actionStrings = { "addL1Server", "addL2Server", "deleteL1Server",
				"deleteL2Server", "increaseDatabaseAThreads",
				"increaseDatabaseBThreads", "increaseTextResolution",
				"reduceTextResolution" };
		if (planSize > 1) { // not sure how to handle this case, I'll handle it if
												// it comes up
			(new Exception()).printStackTrace();
			System.exit(1);
		} else {
			if (actionStrings.length != containsAction.length) {
				System.out
						.println("action strings and string check lengths are not equal.  \nTerminating the program.");
				System.exit(1);
			}
			for (int i = 0; i < actionStrings.length; i++) {
				if (currentPlan.getChromosome(0).toStringNorm(0)
						.contains(actionStrings[i])) {
					containsAction[i] = true;
				}
			}
		}

		int trueCount = 0;
		for (int i = 0; i < containsAction.length; i++) {
			if (containsAction[i] == true) {
				trueCount++;
			}
		}
		if (trueCount == 0) {
			System.out.println("Error.  Chromosome contained none of the actions.");
			System.out.println("Chromosome: "
					+ currentPlan.getChromosome(0).toStringNorm(0));
			System.exit(1);
		}

		PrintWriter writer = null;
		try {
			writer = new PrintWriter(
					"/home/zack/Documents/SoftwareModels/Project/generatedPrismFile.sm"

					, "UTF-8");
		} catch (Exception e) { // TODO Auto-generated
			e.printStackTrace();
		}

		writer.println("dtmc\n");

		// writer
		// .println("const double addServerFailureRate = 0.1; \n"
		// + "const double addServerSuccessRate = 1 - addServerFailureRate;\n"
		// +

		writer.println("const int timeToAddL1Server = 600;\n"
				+ "const int timeToAddL2Server = 600;\n\n"
				// +

				// "const double removeServerFailureRate = 0.1;\n"
				// +
				// "const double removeServerSuccessRate = 1 - removeServerFailureRate;\n"
				+ "const int timeToDeleteL1Server = 120;\n"
				+ "const int timeToDeleteL2Server = 120;\n\n"
				+

				// "const double reduceTextResolutionFailureRate = 0.2;\n"
				// +
				// "const double reduceTextResolutionSuccessRate = 1 - reduceTextResolutionFailureRate;\n"
				// +
				"const int timeToReduceTextResolution = 60;\n\n"
				+

				// "const double increaseTextResolutionFailureRate = 0.2;\n"
				// +
				// "const double increateTextResolutionSuccessRate = 1 - increaseTextResolutionFailureRate;\n"
				// +
				"const int timeToIncreaseTextResolution = 1;\n\n"
				+

				// "const double increaseDatabaseThreadsFailureRate = 0.3;\n"
				// +
				// "const double increaseDatabaseThreadsSuccessRate = 1 - increaseDatabaseThreadsFailureRate;\n"
				// +
				"const int timeToIncreaseDatabaseAThreads = 180;\n"
				+ "const int timeToIncreaseDatabaseBThreads = 180;\n"
				// +

				// "const double moveToCheaperLocationFailureRate = 0.4;\n"
				// +
				// "const double moveToCheaperLocationSuccessRate = 1 - moveToCheaperLocationFailureRate;\n"
				+ "const int timeToMoveToCheaperLocation = 600;\n\n");

		writer.println("rewards");
		IGPProgram prog = currentPlan.getChromosome(0).getIndividual();
		int size = currentPlan.getChromosome(0).size();

		/*
		 * for (int i = 0; i < size; i++) { for (int j = 0; j <
		 * actionStrings.length; j++) { if
		 * (currentPlan.getChromosome(0).getGene(i).toString()
		 * .equals(actionStrings[j])) { actionCount[j]++; String outputString =
		 * actionStrings[j]; writer.println("[" + actionStrings[j] +
		 * String.valueOf(actionCount[j]) + "] true: timeTo" +
		 * String.valueOf(Character.toUpperCase(outputString.charAt(0))) +
		 * outputString.substring(1) + ";"); } } }
		 */

		// not counting states where opposites occur - thus addServer and then
		// removeServer count as 0.
		uniqueStateCount = 0;
		boolean addedServer = false;
		boolean removedServer = false;
		boolean reducedText = false;
		boolean increasedText = false;

		for (int i = 0; i < size; i++) {
			String actionString = currentPlan.getChromosome(0).getGene(i).toString();
			if (!(actionString.startsWith("sub["))) {
				if (actionString.startsWith("addL")) {
					if (removedServer) {
						removedServer = false;
						addedServer = false;
						uniqueStateCount--;
					} else {
						addedServer = true;
						uniqueStateCount++;
					}

				} else if (actionString.startsWith("deleteL")) {
					if (addedServer) {
						removedServer = false;
						addedServer = false;
						uniqueStateCount--;
					} else {
						removedServer = true;
						uniqueStateCount++;
					}
				} else if (actionString.startsWith("reduceText")) {
					if (increasedText) {
						reducedText = false;
						increasedText = false;
						uniqueStateCount--;
					} else {
						reducedText = true;
						uniqueStateCount++;
					}
				} else if (actionString.startsWith("increaseText")) {
					if (reducedText) {
						increasedText = false;
						reducedText = false;
						uniqueStateCount--;
					} else {
						increasedText = true;
						uniqueStateCount++;
					}
				} else {
					uniqueStateCount++;
				}
			}
			// System.out.println("State: " + actionString + "  unique count: "
			// + uniqueStateCount);
		}
		if (uniqueStateCount == 0) {
			return; // handling divide by zero error
		}
		CostRewardObject cr = createCostRewardObject();
		SystemState ss = new SystemState();
		for (int i = 0; i < size; i++) {
			CommandGene cg = currentPlan.getChromosome(0).getGene(i);
			if (cg instanceof Actions) {
				((Actions) cg).setSystemState(ss);
				if (((Actions) cg).arePreconditionsSatisfied(cr)) {
					((Actions) cg).results(cr);
					ss = ((Actions) cg).getSystemState();
					// System.out.println("gene: |" + cg.toString() + "|");
					double transitionCost = calculateTransitionCost(cr, ((Actions) cg));
					// System.out.println("response time: " + cr.getSystemResponseTime());
					for (int j = 0; j < actionStrings.length; j++) {
						// System.out.println("checking action = |" + actionStrings[j] +
						// "|");
						if (cg.toString().equals(actionStrings[j])) {
							// System.out.println("they are equal!");
							actionCount[j]++;
							int total = (int) transitionCost; // + feasibilityReward;
							total = (total * -1) / uniqueStateCount; // prism can't take
																												// negative
							// values
							// System.out.println("[" + cg.toString()
							// + String.valueOf(actionCount[j]) + "] true: " + total + ";");
							// System.out.println("transaction cost: " + transitionCost);
							// System.out.println("feasibiltyReward: " + feasibilityReward);
							// System.out.println("Total: " + total);
							writer.println("[" + cg.toString()
									+ String.valueOf(actionCount[j]) + "] true: " + total + ";");
							break;
						}
					}

				}
			}
		}

		writer.println("\nendrewards\n\nmodule AutomaticEvalutation\n\n");
		writer.println("clockTime: [0..999999] init 0;");
		writer.println("currentState: [0..20] init 0;\n");

		for (int i = 0; i < actionCount.length; i++) {
			actionCount[i] = 0;
		}

		int stateCount = 0;
		for (int i = 0; i < size; i++) {
			// System.out.println(currentPlan.getChromosome(0).getGene(i));
			for (int j = 0; j < actionStrings.length; j++) {
				if (currentPlan.getChromosome(0).getGene(i).toString()
						.equals(actionStrings[j])) {
					actionCount[j]++;
					String outputString = actionStrings[j];
					writer.println("[" + actionStrings[j]
							+ String.valueOf(actionCount[j]) + "] currentState = "
							+ String.valueOf(stateCount) + "-> "
							+ "(currentState' = currentState+1)&(clockTime'=clockTime+timeTo"
							+ String.valueOf(Character.toUpperCase(outputString.charAt(0)))
							+ outputString.substring(1) + ");");
					stateCount++;
				}
			}
		}

		// added for debugging
		/*
		 * if (stateCount > 1) { System.out .println("!!!!!" +
		 * currentPlan.getChromosome(0).toStringNorm(0)); }
		 */

		writer.println("\nendmodule");

		// System.out.println("End of Prism plan check");
		// currentPlan.getChromosome(0).getFunctions()[1].getArity(a_indvividual)
		writer.close();

		PrintWriter propertyCheckWriter = null;
		try {
			propertyCheckWriter = new PrintWriter(
					"/home/zack/Documents/SoftwareModels/Project/generatedPropertyFile.pctl",
					"UTF-8");
		} catch (Exception e) { // TODO Auto-generated
			e.printStackTrace();
		}

		int maxStateCount = 0;

		for (int i = 0; i < size; i++) {
			if (!(currentPlan.getChromosome(0).getGene(i).toString()
					.startsWith("sub["))) {
				maxStateCount++;
			}
		}

		propertyCheckWriter.write("R=? [ F currentState="
				+ String.valueOf(maxStateCount) + " ]");
		propertyCheckWriter.flush();
		propertyCheckWriter.close();

		// System.exit(0);// ended here to debug - remove later

	}

	private double calculateTransitionCost(CostRewardObject cr, Actions a) {
		int contentQuailtyImportance = 20;
		if (!cr.getUsingHighTextResolution()) {
			contentQuailtyImportance = contentQuailtyImportance / 2;
		}
		int time = a.getTime();
		time = time / 100;
		// double cost = -50 * cr.getSystemResponseTime() *
		// cr.getSystemResponseTime()
		// + -0.2 * cr.getCost() + 0.1 * contentQuailtyImportance - 0.003 * time
		// * time;
		double cost = -5 * cr.getSystemResponseTime() + -0.2 * cr.getCost() + 0.1
				* contentQuailtyImportance - 0.3 * time;

		// System.out.println("response time: " + cr.getSystemResponseTime());
		// System.out.println("cost: " + cr.getCost());
		// System.out.println("quality: " + contentQuailtyImportance);
		// System.out.println("time: " + a.getTime());
		// System.out.println("total: " + cost);

		// System.out.println("Reponse Penalty: "
		// + (-0.4 * cr.getSystemResponseTime()));
		// System.out.println("Cost Penalty: " + (-0.2 * cr.getCost()));
		// System.out.println("Quailty Bonus: " + (0.1 * contentQuailtyImportance));
		// System.out.println("Time Penalty: " + (-0.3 * a.getTime()));
		// System.out.println("Total with Feasible Bonus: "
		// + (cost + feasibilityReward));

		return cost;
	}

	@Override
	public boolean isFitter(double arg0, double arg1) {
		// System.out.println("Called #1: arg0-" + String.valueOf(arg0) + " arg1-"
		// + String.valueOf(arg1));
		return (arg0 > arg1); // looking for maximum fitness
	}

	@Override
	public boolean isFitter(IGPProgram arg0, IGPProgram arg1) {
		double eval0 = evaluate(arg0);
		double eval1 = evaluate(arg1);
		// System.out.println("Called #2: eval0-" + String.valueOf(eval0) +
		// " eval1-"
		// + String.valueOf(eval1));
		return (eval0 > eval1);
	}

}
