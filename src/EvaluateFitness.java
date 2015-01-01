import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import org.jgap.gp.CommandGene;
import org.jgap.gp.GPFitnessFunction;
import org.jgap.gp.IGPFitnessEvaluator;
import org.jgap.gp.IGPProgram;
import org.jgap.gp.impl.ProgramChromosome;

import actions.Actions;
import actions.CostRewardObject;
import actions.SystemState;
import plan.IfNode;
import plan.Plan;
import plan.PlanNode;
import plan.SingleActionNode;

public class EvaluateFitness extends GPFitnessFunction implements
		IGPFitnessEvaluator {

	int feasibilityReward = 99999;
	boolean sub = false; // used for debugging
	// int uniqueStateCount;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected double evaluate(IGPProgram currentPlan) {
		// System.out.println("Starting to evaluate a plan!");
		// attempting to print plan
		// System.out.println("New Plan!");

		System.out.println("A plan: "
				+ currentPlan.getChromosome(0).toStringNorm(0));

		ArrayList<ArrayList<CommandGene>> planList = generatePossiblePlanExecutions(currentPlan
				.getChromosome(0));

		// System.out.println("plan list size: " + planList.size());
		for (int i = 0; i < planList.size(); i++) {
			boolean isPlanFeasible = checkPlanFeasibility(planList.get(i));
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
		}

		/*
		 * if (sub) { System.out.println("sub is true before making prism file"); }
		 */
		makePrismFiles(currentPlan);
		// if (uniqueStateCount == 0) {
		// return 0;
		// }
		// end attempt to print plan
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
			System.out.println("Cost of plan:" + result);
			return result;
		}
		return new Double(0); // shouldn't be reachable but needed for compiler
	}

	// this method steps through a plan to makes sure it is feasible, currently,=
	// it does not handle branching.
	private boolean checkPlanFeasibility(ArrayList<CommandGene> currentPlan) {
		CostRewardObject cr = createCostRewardObject();
		int serverNumber = 3; // need to not hard code it and instead have the plan
													// generate the number.
		int chromosomeSize = currentPlan.size();
		// sub = false;
		// System.out.print("A single plan: ");
		for (int i = 0; i < chromosomeSize; i++) {
			CommandGene cg = currentPlan.get(i);
			// System.out.print(cg.toString() + ", ");
			/*
			 * if (!sub) { System.out.println("contains sub: " +
			 * currentPlan.getChromosome(0).toStringNorm(0)); }
			 */
			// sub = true;
			// System.out.println("value of sub after setting to true: "
			// + String.valueOf(sub));
			// }
			// System.out.println("value of sub after if: " + String.valueOf(sub));
			if (cg instanceof Actions) {
				if (((Actions) cg).arePreconditionsSatisfied(cr)) {
					((Actions) cg).results(cr);
				} else {
					// System.out.println("value of sub at end of feasiblity check 1:"
					// + String.valueOf(sub));
					// System.out.println("failing command: " + cg.toString() + " pos: "
					// + String.valueOf(i));
					// System.out.print("\n");
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
		// System.out.print("\n");
		return true;
	}

	private ArrayList<ArrayList<CommandGene>> generatePossiblePlanExecutions(
			ProgramChromosome chromosome) {

		ArrayList<ArrayList<CommandGene>> planList = new ArrayList<ArrayList<CommandGene>>();
		ArrayList<CommandGene> plan = new ArrayList<CommandGene>();
		int size = chromosome.size();
		int branchCount = 0;
		for (int i = 0; i < size; i++) {
			if (chromosome.getGene(i).toString().contains("if-success")) {
				branchCount++;
			}
		}
		int[][] branchChoices = null;
		if (branchCount > 0) {
			branchChoices = generateBranchChoices(branchCount);
		}

		if (branchChoices == null) {
			addPlanToPlanList(chromosome, planList, null);
		} else {
			for (int i = 0; i < branchChoices.length; i++) {
				addPlanToPlanList(chromosome, planList, branchChoices[i]);
			}
		}

		// System.out.println("Plans in plan list: " + planList.size());
		return planList;

	}

	private int[][] generateBranchChoices(int numberOfBranches) {
		int numberOfCombinations = 1;
		for (int i = 0; i < numberOfBranches; i++) {
			numberOfCombinations *= 2;
		}
		int[][] branchCombinations = new int[numberOfCombinations][numberOfBranches];
		for (int i = numberOfBranches - 1; i > -1; i--) {
			int amountBeforeSwitching = 1;
			for (int k = 0; k < i; k++) {
				amountBeforeSwitching *= 2;
			}
			int currentColumn = numberOfBranches - i - 1;
			int branchChoice = 1; // 1 means going left, 2 means going right. 0 is
														// always taken for if-success
			for (int l = 0; l < (numberOfCombinations / amountBeforeSwitching); l++) {
				for (int j = 0; j < amountBeforeSwitching; j++) {
					branchCombinations[j + l * amountBeforeSwitching][currentColumn] = branchChoice;
				}
				if (branchChoice == 1) {
					branchChoice = 2;
				} else {
					branchChoice = 1;
				}
			}
		}

		return branchCombinations;
	}

	private void addPlanToPlanList(ProgramChromosome chromosome,
			ArrayList<ArrayList<CommandGene>> planList, int[] branchChoicesForPlan) {

		ArrayList<CommandGene> unfinshedPlan = new ArrayList<CommandGene>();
		addNodeToPlan(chromosome, 0, unfinshedPlan, branchChoicesForPlan, 0);

		/*
		 * System.out.println("finished plan"); for (int i = 0; i <
		 * unfinshedPlan.size(); i++) {
		 * System.out.print(unfinshedPlan.get(i).toString() + ", "); }
		 */

		planList.add(unfinshedPlan);
	}

	// this current plan generation technique may generate duplicate plans - may
	// need to fix that later.
	private int addNodeToPlan(ProgramChromosome chromosome, int currentGeneIndex,
			ArrayList<CommandGene> currentPlan, int[] branchChoices,
			int currentChoiceNumber) {
		CommandGene currentGene = chromosome.getGene(currentGeneIndex);
		// System.out.println("current gene: " + currentGene.toString());
		// System.out.println("current choice number: " + currentChoiceNumber);
		IGPProgram ind = chromosome.getIndividual();
		currentPlan.add(currentGene);
		if (currentGene.getArity(ind) != 0) {
			int ifsTaken = currentChoiceNumber;
			if (currentGene.toString().contains("if-success")) {
				int firstGeneIndex = chromosome.getChild(currentGeneIndex, 0);
				ifsTaken = addNodeToPlan(chromosome, firstGeneIndex, currentPlan,
						branchChoices, ifsTaken);
				int secondGeneIndex;
				if (branchChoices[ifsTaken] == 1) {
					secondGeneIndex = chromosome.getChild(currentGeneIndex,
							branchChoices[ifsTaken]);
				} else {
					secondGeneIndex = chromosome.getChild(currentGeneIndex,
							branchChoices[ifsTaken]);
				}
				// System.out.println("second gene index: " + secondGeneIndex);
				ifsTaken = addNodeToPlan(chromosome, secondGeneIndex, currentPlan,
						branchChoices, ifsTaken + 1); // don't need to add one here, it
																					// would be
				// double
				// counting the current if statement
				return ifsTaken;
			} else {
				// System.out.println("current gene for else: " +
				// currentGene.toString());
				for (int i = 0; i < currentGene.getArity(ind); i++) {
					int childGeneIndex = chromosome.getChild(currentGeneIndex, i);
					ifsTaken = addNodeToPlan(chromosome, childGeneIndex, currentPlan,
							branchChoices, ifsTaken);
				}
				return ifsTaken;
			}
		}
		return currentChoiceNumber;

	}

	private CostRewardObject createCostRewardObject() {
		return (new CostRewardObject(20, 30));
	}

	private void makePrismFiles(IGPProgram currentPlan) { // first need to get
		// what is in the current plan to tailor the prism file to it.
		int planSize = currentPlan.size();
		boolean[] containsAction = { false, false, false, false, false, false,
				false, false };
		int[] actionCount = { 0, 0, 0, 0, 0, 0, 0, 0 };
		HashMap<String, Integer> actionStringCount = new HashMap<String, Integer>();
		String[] actionStrings = { "addL1Server", "addL2Server", "deleteL1Server",
				"deleteL2Server", "increaseDatabaseAThreads",
				"increaseDatabaseBThreads", "increaseTextResolution",
				"reduceTextResolution" };
		for (int i = 0; i < actionStrings.length; i++) {
			actionStringCount.put(actionStrings[i], new Integer(0));
		}
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
					"/home/zack/Documents/SoftwareModels/Project/generatedPrismFile.pm"

					, "UTF-8");
		} catch (Exception e) { // TODO Auto-generated
			e.printStackTrace();
		}

		writer.println("dtmc\n");

		writer.println("//generated plan: "
				+ currentPlan.getChromosome(0).toStringNorm(0));

		Plan generatedPlan = createPlan(currentPlan.getChromosome(0), 0);
		writer.println("//my plan: " + generatedPlan.planString());

		writer.println("rewards\n");
		IGPProgram prog = currentPlan.getChromosome(0).getIndividual();
		int size = currentPlan.getChromosome(0).size();

		writer
				.println("[metric] true: -5 * responseTime + -0.2 * cost + contentQuality - 0.3 * clockTime;\n");

		SystemState ss = new SystemState();
		writer.println("\nendrewards\n\nmodule AutomaticEvalutation\n\n");
		writer.println("clockTime: [0..999999] init 0;");
		// zero is the final state where the cost is determined
		// and one is the ending state,
		// so have to start at state two
		writer.println("currentState: [0..99] init 2;");
		CostRewardObject cr = createCostRewardObject();
		writer.println("responseTime: [1..999] init " + cr.getSystemResponseTime()
				+ ";");
		writer.println("cost: [1..999] init " + cr.getCost() + ";");
		writer.println("serverCount: [1.." + ss.getMaxServerCount() + "] init "
				+ cr.getServerCount() + ";");
		int highResolutionState;
		if (cr.getUsingHighTextResolution()) {
			highResolutionState = 2;
		} else {
			highResolutionState = 1;
		}
		writer
				.println("contentQuality: [1..2] init " + highResolutionState + ";\n");

		for (int i = 0; i < actionCount.length; i++) {
			actionCount[i] = 0;
		}

		writer
				.println("[metric] currentState= 0 -> (currentState'=currentState+1);");
		writer.println("[final] currentState = 1 -> true;");

		printPrismNode(writer, actionStringCount, generatedPlan.getStartNode(), 2);

		/*
		 * ArrayList<CommandGene> commandsThatMayFail = new
		 * ArrayList<CommandGene>(); ArrayList<ActionPosition> actionPositionArray =
		 * new ArrayList<ActionPosition>();
		 * ArrayList<LastActionInIfAndFirstTwoOfBranches> testAndBranchArray = new
		 * ArrayList<LastActionInIfAndFirstTwoOfBranches>(); for (int i = 0; i <
		 * size; i++) { //
		 * System.out.println(currentPlan.getChromosome(0).getGene(i)); CommandGene
		 * cg = currentPlan.getChromosome(0).getGene(i); if
		 * (cg.toString().contains("if-success")) { // what if I first got all the
		 * genes in the 0 part of the if statement // then I generated the possible
		 * plans from them, and then I get all // of the final genes. Then if a
		 * final gene is found, // implement that it might fail.
		 * 
		 * ArrayList<CommandGene> genesInTestOfIf = new ArrayList<CommandGene>();
		 * 
		 * int headGeneOfIfTestSectionIndex = currentPlan.getChromosome(0)
		 * .getChild(cg, 0); getGenesInIf(genesInTestOfIf,
		 * headGeneOfIfTestSectionIndex, currentPlan.getChromosome(0)); int
		 * ifTestSize = genesInTestOfIf.size(); int branchCount = 0; // counting
		 * number of successes in the if statement test for (int j = 0; j <
		 * ifTestSize; j++) { if
		 * (genesInTestOfIf.get(j).toString().contains("if-success")) {
		 * branchCount++; } } // adding the action or actions at the end of the test
		 * statement for // the if execution int[][] branchChoices = null;
		 * ArrayList<Actions> actionsThatMayFail = new ArrayList<Actions>(); if
		 * (branchCount > 0) { // take this if if there are branching statements
		 * inside the if test branchChoices = generateBranchChoices(branchCount);
		 * for (int j = 0; j < branchChoices.length; j++) { ArrayList<CommandGene>
		 * currentTestBranch = new ArrayList<CommandGene>();
		 * addNodeToPlan(currentPlan.getChromosome(0), headGeneOfIfTestSectionIndex,
		 * currentTestBranch, branchChoices[j], 0); // need to add the ending action
		 * of all possible branches of the if // test statement
		 * commandsThatMayFail.add(currentTestBranch.get(currentTestBranch .size() -
		 * 1)); actionsThatMayFail.add((Actions) (currentTestBranch
		 * .get(currentTestBranch.size() - 1))); } } else { // only the last action
		 * can fail if there is no branching commandsThatMayFail
		 * .add(genesInTestOfIf.get(genesInTestOfIf.size() - 1));
		 * actionsThatMayFail.add((Actions) (genesInTestOfIf.get(genesInTestOfIf
		 * .size() - 1))); }
		 * 
		 * testAndBranchArray.add(new LastActionInIfAndFirstTwoOfBranches(
		 * actionsThatMayFail, nextAction(currentPlan.getChromosome(0), cg, 1),
		 * nextAction( currentPlan.getChromosome(0), cg, 2))); } else {
		 * System.out.println("CommandGene String: " + cg.toString());
		 * System.out.println("ActionStringCount contains command string: " +
		 * actionStringCount.containsKey(cg.toString())); if
		 * (actionStringCount.containsKey(cg.toString())) { Actions action = null;
		 * if (!(cg instanceof Actions)) { System.out
		 * .println("this shouldn't happen because command gene matches " +
		 * "an action but is not an action."); } else { action = (Actions) cg; }
		 * actionPositionArray.add(new ActionPosition(action, position));
		 * position++; stateCount++; // break; } } }
		 * 
		 * // now creating file when we know where the actions are
		 * System.out.println("action position array: " +
		 * actionPositionArray.size()); for (int i = 0; i <
		 * actionPositionArray.size(); i++) { ActionPosition actionPosition =
		 * actionPositionArray.get(i); Actions action = actionPosition.getAction();
		 * System.out.println("action printing: " + action.toString()); String
		 * nextStateString = null; if (i == actionPositionArray.size() - 1) {
		 * nextStateString = "0"; } else { nextStateString = "currentState+1"; }
		 * CommandGene cg = (CommandGene) action;
		 * actionStringCount.put(action.toString(),
		 * actionStringCount.get(action.toString()) + 1);
		 * 
		 * // added for debugging System.out.println("current gene: " +
		 * cg.toString()); System.out.print("commands that may fail: "); for
		 * (CommandGene tempGene : commandsThatMayFail) {
		 * System.out.print(tempGene.toString() + ", "); } System.out.print("\n");
		 * // can't just use the contains method. CommandGene has a weird //
		 * override of equals that makes contains always return true. boolean
		 * containsCG = false; for (CommandGene commandGeneToCheckForMatch :
		 * commandsThatMayFail) { if (commandGeneToCheckForMatch == cg) { containsCG
		 * = true; break; } } if (containsCG) {
		 * System.out.println("matching command index: " +
		 * commandsThatMayFail.indexOf(cg)); CommandGene weirdMatchingGene =
		 * commandsThatMayFail .get(commandsThatMayFail.indexOf(cg));
		 * System.out.println(weirdMatchingGene.toString());
		 * System.out.println("match matches the current gene: " +
		 * cg.equals(weirdMatchingGene)); System.out.println("Command may fail");
		 * LastActionInIfAndFirstTwoOfBranches lastAndBranch = null; for (int j = 0;
		 * j < testAndBranchArray.size(); j++) { lastAndBranch =
		 * testAndBranchArray.get(j); ArrayList<Actions> lastActions =
		 * lastAndBranch.getLastActionsInIf(); boolean found = false; for (Actions a
		 * : lastActions) { if (a == action) {
		 * System.out.println("Matching failing action: 1)" + a.toString() + " 2)" +
		 * action.toString()); found = true; break; } } if (found) { break; } }
		 * 
		 * Actions firstSuccessAction = lastAndBranch
		 * .getFirstStatementInSuccessBranch();
		 * System.out.println("first success action: " +
		 * firstSuccessAction.toString()); int firstSuccessActionPosition = -1; for
		 * (ActionPosition tempActionPosition : actionPositionArray) { // actions
		 * .equals doesn't work how I want it to, using == instead if
		 * (tempActionPosition.getAction() == firstSuccessAction) {
		 * System.out.println("matching first success action: 1) " +
		 * tempActionPosition.getAction().toString() + " 2)" + firstSuccessAction);
		 * firstSuccessActionPosition = tempActionPosition.getPosition(); break; } }
		 * if (firstSuccessActionPosition == -1) {
		 * System.out.println("Didn't find first success action"); System.exit(1); }
		 * Actions firstFailAction = lastAndBranch
		 * .getFirstStatementInFailureBranch(); int firstFailActionPosition = -1;
		 * for (ActionPosition tempActionPosition : actionPositionArray) { // Action
		 * equals does work like i need it to. Using == instead; if
		 * (tempActionPosition.getAction() == firstFailAction) {
		 * firstFailActionPosition = tempActionPosition.getPosition();
		 * System.out.println("matching first success action: 1) " +
		 * tempActionPosition.getAction().toString() + " 2)" + firstFailAction);
		 * break; } } if (firstFailActionPosition == -1) {
		 * System.out.println("Didn't find first success action"); System.exit(1); }
		 * 
		 * writer.println("[" + action.toString() +
		 * String.valueOf(actionStringCount.get(action.toString())) +
		 * "] currentState = " + String.valueOf(actionPosition.getPosition()) +
		 * "-> " + String.valueOf((1 - action.getFailureRate())) +
		 * ":(currentState' = " + String.valueOf(firstSuccessActionPosition) + ")&"
		 * + action.getPrismSucessString() + "+ " +
		 * String.valueOf(action.getFailureRate()) + ":(currentState' = " +
		 * String.valueOf(firstFailActionPosition) + ")&" +
		 * action.getPrismFailureString() + ";");
		 * 
		 * // added for debugging // System.exit(1); } else { // later I need to
		 * update this to handle System.out.println("Command cannot fail");
		 * writer.println("[" + action.toString() +
		 * String.valueOf(actionStringCount.get(action.toString())) +
		 * "] currentState = " + String.valueOf(actionPosition.getPosition()) +
		 * "-> " + "(currentState' = " + nextStateString + ")&" +
		 * action.getPrismSucessString() + ";"); } }
		 */

		// added for debugging
		/*
		 * if (stateCount > 1) { System.out .println("!!!!!" +
		 * currentPlan.getChromosome(0).toStringNorm(0)); }
		 */

		writer.println("\nendmodule");

		// System.out.println("End of Prism plan check");
		// currentPlan.getChromosome(0).getFunctions()[1].getArity(a_indvividual)
		writer.close();

		// currently using it for debugging
		// System.exit(1);

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

		propertyCheckWriter.write("R=? [ F currentState=1 ]");
		propertyCheckWriter.flush();
		propertyCheckWriter.close();

		// System.exit(0);// ended here to debug - remove later

	}

	public int countNodesInPlanBranch(PlanNode pn) {
		Iterator<PlanNode> planIter = pn.iterator();
		int count = 0;
		while (planIter.hasNext()) {
			if (planIter.next() instanceof SingleActionNode) {
				count++;
			}
		}
		return count;
	}

	public void printPrismNode(PrintWriter writer,
			HashMap<String, Integer> actionStringCount, PlanNode pn,
			int currentCommandCount) {
		if (pn instanceof SingleActionNode) {
			String nextStateString = null;
			if (((SingleActionNode) pn).getNextNode() == null) {
				nextStateString = "0";
			} else {
				nextStateString = "currentState+1";
			}
			Actions action = (Actions) pn.getPlanGene();
			actionStringCount.put(actionStringCount.toString(),
					(actionStringCount.get(action.toString()) + 1));
			// System.out.println("Command cannot fail");
			writer.println("[" + action.toString()
					+ String.valueOf(actionStringCount.get(action.toString()))
					+ "] currentState = " + String.valueOf(currentCommandCount) + "-> "
					+ "(currentState' = " + nextStateString + ")&"
					+ action.getPrismSucessString() + ";");
			if (((SingleActionNode) pn).getNextNode() != null) {
				printPrismNode(writer, actionStringCount,
						((SingleActionNode) pn).getNextNode(), currentCommandCount + 1);
			}
			// added for debugging
			// System.exit(1);
		} else {
			IfNode iNode = (IfNode) pn;
			PlanNode testNode = iNode.getTestNode();
			Actions action = (Actions) testNode.getPlanGene();
			int successNodeCount = countNodesInPlanBranch(iNode.getSuccessNode());
			writer.println("[" + action.toString()
					+ String.valueOf(actionStringCount.get(action.toString()))
					+ "] currentState = " + String.valueOf(currentCommandCount) + "-> "
					+ String.valueOf((1 - action.getFailureRate()))
					+ ":(currentState' = currentState + 1" + ")&"
					+ action.getPrismSucessString() + "+ "
					+ String.valueOf(action.getFailureRate()) + ":(currentState' = "
					+ String.valueOf(currentCommandCount + 1 + successNodeCount) + ")&"
					+ action.getPrismFailureString() + ";");
			printPrismNode(writer, actionStringCount, iNode.getSuccessNode(),
					currentCommandCount + 1);
			printPrismNode(writer, actionStringCount, iNode.getFailureNode(),
					currentCommandCount + 1 + successNodeCount);
		}
	}

	int printCount = 0;

	public Plan createPlan(ProgramChromosome chromosome, int commandGeneIndex) {
		// added for debugging
		printCount = 0;
		return new Plan(makeSubPlan(chromosome, commandGeneIndex));

	}

	private PlanNode makeSubPlan(ProgramChromosome chromosome,
			int currentGeneIndex) {
		CommandGene currentGene = chromosome.getGene(currentGeneIndex);
		if (currentGene.toString().contains("sub")) {
			// last actions of first sub need to connect to the first action of the
			// next sub
			PlanNode firstBranch = makeSubPlan(chromosome,
					chromosome.getChild(currentGeneIndex, 0));
			// iterating over all the nodes in the sub plan to
			// find the ending nodes
			PlanNode secondBranch = makeSubPlan(chromosome,
					chromosome.getChild(currentGeneIndex, 1));
			// now connect the first part of the second branch to the
			// ends of the first branch
			Iterator<PlanNode> planIter = firstBranch.iterator();
			while (planIter.hasNext()) {
				PlanNode tempNode = planIter.next();
				if (tempNode instanceof SingleActionNode) {
					if (((SingleActionNode) tempNode).getNextNode() == null) {
						((SingleActionNode) tempNode).setNextNode(secondBranch);
						secondBranch.setPreviousNode(tempNode);
					}
				}
			}
			return firstBranch;

		} else if (currentGene.toString().contains("if-success")) {
			// end of the test expression needs to connect to
			// the first actions of the success branch and
			// the fail branch

			/*
			 * if (printCount < 5) { System.out.println("Printed plan: " +
			 * chromosome.toStringNorm(0));
			 * System.out.println("Children index of node: " +
			 * chromosome.getChild(currentGeneIndex, 0) + " " +
			 * chromosome.getChild(currentGeneIndex, 1) + " " +
			 * chromosome.getChild(currentGeneIndex, 2)); IGPProgram ind =
			 * chromosome.getIndividual(); System.out.println("test statement: " +
			 * chromosome.getGene(chromosome.getChild(currentGeneIndex, 0))
			 * .toString()); System.out.print("test children"); CommandGene tempGene =
			 * chromosome.getGene(chromosome.getChild( currentGeneIndex, 0)); if
			 * (tempGene.getArity(ind) != 0) { for (int i = 0; i <
			 * tempGene.getArity(ind); i++) { int childGeneIndex =
			 * chromosome.getChild(currentGeneIndex, i);
			 * System.out.print(childGeneIndex + ", "); } } System.out.print("\n");
			 * System.out.println("success statement: " +
			 * chromosome.getGene(chromosome.getChild(currentGeneIndex, 1))
			 * .toString()); System.out.print("success children"); tempGene =
			 * chromosome.getGene(chromosome.getChild(currentGeneIndex, 1)); if
			 * (tempGene.getArity(ind) != 0) { for (int i = 0; i <
			 * tempGene.getArity(ind); i++) { int childGeneIndex =
			 * chromosome.getChild(tempGene, i); System.out.print(childGeneIndex +
			 * ", "); } } System.out.print("\n");
			 * System.out.println("fail statement: " +
			 * chromosome.getGene(chromosome.getChild(currentGeneIndex, 2))
			 * .toString()); System.out.print("fail children"); tempGene =
			 * chromosome.getGene(chromosome.getChild(currentGeneIndex, 2)); if
			 * (tempGene.getArity(ind) != 0) { for (int i = 0; i <
			 * tempGene.getArity(ind); i++) { int childGeneIndex =
			 * chromosome.getChild(tempGene, i); System.out.print(childGeneIndex +
			 * ", "); } } System.out.print("\n"); printCount++;
			 * 
			 * }
			 */
			PlanNode testBranch = makeSubPlan(chromosome,
					chromosome.getChild(currentGeneIndex, 0));
			PlanNode successBranch = makeSubPlan(chromosome,
					chromosome.getChild(currentGeneIndex, 1));
			PlanNode failureBranch = makeSubPlan(chromosome,
					chromosome.getChild(currentGeneIndex, 2));
			Iterator<PlanNode> planIter = testBranch.iterator();
			// add if node to all test branches
			while (planIter.hasNext()) {
				PlanNode tempNode = planIter.next();
				if (tempNode instanceof SingleActionNode) {
					if (((SingleActionNode) tempNode).getNextNode() == null) {
						if (tempNode.getPreviousNode() != null) {
							if (tempNode.getPreviousNode() instanceof SingleActionNode) {
								IfNode iNode = new IfNode(currentGene);
								iNode.setSuccessNode(successBranch);
								iNode.setFailureNode(failureBranch);
								// taking the last node of the test branches
								// placing them inside an if statement,
								// and removing the old copy from the plan
								iNode.setTestNode(tempNode);
								((SingleActionNode) tempNode.getPreviousNode())
										.setNextNode(iNode);

							} else {
								System.err.println("This shouldn't happen!");
								new Exception().printStackTrace();
								System.exit(1);
							}

						}
					}
				}
			}

			return testBranch; // return the fully built if node
		} else {
			return new SingleActionNode(currentGene);
		}
	}

	private Actions nextAction(ProgramChromosome chromosome, CommandGene cg,
			int childIndex) {
		CommandGene nextGene = chromosome.getGene(chromosome.getChild(cg,
				childIndex));
		if (nextGene instanceof Actions) {
			return ((Actions) nextGene);
		} else {
			return nextAction(chromosome, nextGene, 0);// childIndex 0
		}

	}

	private void getGenesInIf(ArrayList<CommandGene> genesInTestOfIf,
			int geneIndex, ProgramChromosome chromosome) {
		CommandGene cg = chromosome.getGene(geneIndex);
		genesInTestOfIf.add(cg);
		IGPProgram ind = chromosome.getIndividual();
		if (cg.getArity(ind) != 0) {
			for (int i = 0; i < cg.getArity(ind); i++) {
				int childGeneIndex = chromosome.getChild(geneIndex, i);
				getGenesInIf(genesInTestOfIf, childGeneIndex, chromosome);
			}
		}

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
