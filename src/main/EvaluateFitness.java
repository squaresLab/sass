package main;

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

import plan.IfNode;
import plan.Plan;
import plan.PlanNode;
import plan.SingleActionNode;
import actions.Actions;
import actions.CostRewardObject;
import actions.SystemState;

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
			// result = result * -1 + feasibilityReward; // multiplied by negative 1
			// because it was made positive
			// for prism
			result = result + feasibilityReward;

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
	public boolean checkPlanFeasibility(ArrayList<CommandGene> currentPlan) {
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

	public ArrayList<ArrayList<CommandGene>> generatePossiblePlanExecutions(
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
		int planSize = currentPlan.size();
		if (planSize > 1) { // not sure how to handle this case, I'll handle it if
			// it comes up
			(new Exception()).printStackTrace();
			System.exit(1);
		}
		makePrismFileFromChromosome(currentPlan.getChromosome(0));

	}

	public void makePrismFileFromChromosome(ProgramChromosome chromosome) {
		// what is in the current plan to tailor the prism file to it.

		int[] actionCount = { 0, 0, 0, 0, 0, 0, 0, 0 };
		HashMap<String, Integer> actionStringCount = new HashMap<String, Integer>();
		String[] actionStrings = { "addL1Server", "addL2Server", "deleteL1Server",
				"deleteL2Server", "increaseDatabaseAThreads",
				"increaseDatabaseBThreads", "increaseTextResolution",
				"reduceTextResolution" };
		for (int i = 0; i < actionStrings.length; i++) {
			actionStringCount.put(actionStrings[i], new Integer(0));
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

		writer.println("//generated plan: " + chromosome.toStringNorm(0));

		Plan generatedPlan = createPlan(chromosome, 0);
		writer.println("//my plan: " + generatedPlan.planString());

		writer.println("rewards\n");
		IGPProgram prog = chromosome.getIndividual();
		int size = chromosome.size();

		writer
				.println("[metric] true: -200 * responseTime + -4 * cost + 20 * contentQuality - 0.02 * clockTime;\n");

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

		writer.println("\nendmodule");

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
			if (!(chromosome.getGene(i).toString().startsWith("sub["))) {
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
			actionStringCount.put(action.toString(),
					(actionStringCount.get(action.toString()) + 1));
			// System.out.println("Command cannot fail");
			writer.println("[" + action.toString()
					+ String.valueOf(actionStringCount.get(action.toString()))
					+ "] currentState = " + String.valueOf(currentCommandCount) + "-> "
					+ String.valueOf((1 - action.getFailureRate()))
					+ ":(currentState' = " + nextStateString + ")&"
					+ action.getPrismSucessString() + "+ "
					+ String.valueOf(action.getFailureRate()) + ":(currentState' = "
					+ nextStateString + ")&" + action.getPrismFailureString() + ";");
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
		// return new Plan(makeSubPlan(chromosome, commandGeneIndex));
		return new Plan(makeSubPlanDebug(chromosome, commandGeneIndex, 0));

	}

	private PlanNode makeSubPlanDebug(ProgramChromosome chromosome,
			int currentGeneIndex, int nesting) {
		CommandGene currentGene = chromosome.getGene(currentGeneIndex);
		if (currentGene.toString().contains("sub")) {
			// System.out.println("starting sub node");
			// last actions of first sub need to connect to the first action of the
			// next sub
			PlanNode firstBranch = makeSubPlanDebug(chromosome,
					chromosome.getChild(currentGeneIndex, 0), nesting + 1);
			// iterating over all the nodes in the sub plan to
			// find the ending nodes
			// if (nesting == 0) {
			// System.out.println("finshed first branch");
			// }
			PlanNode secondBranch = makeSubPlanDebug(chromosome,
					chromosome.getChild(currentGeneIndex, 1), nesting + 1);

			// now connect the first part of the second branch to the
			// ends of the first branch

			// need to first find all nodes that end first branch
			// and not append the second branch as the nodes are found
			// because it could grow by
			// appending the second branch to it if two branches end
			// up converging to the same point in the plan
			// if (nesting == 0) {
			// System.out.println("finshed second branch");
			// }
			Iterator<PlanNode> planIter = firstBranch.iterator();
			ArrayList<SingleActionNode> nodesWhichEndFirstBranch = new ArrayList<SingleActionNode>();
			while (planIter.hasNext()) {
				PlanNode tempNode = planIter.next();
				if (tempNode instanceof SingleActionNode) {
					if (((SingleActionNode) tempNode).getNextNode() == null
							&& !(tempNode.isInTestStatement())) {
						nodesWhichEndFirstBranch.add((SingleActionNode) tempNode);
					}
				}
			}
			// if (nesting == 0) {
			// System.out.println("finshed iterating through first branch");
			// }
			for (int i = 0; i < nodesWhichEndFirstBranch.size(); i++) {
				PlanNode secondBranchCopy = secondBranch.deepCopy();
				SingleActionNode saNode = nodesWhichEndFirstBranch.get(i);
				saNode.setNextNode(secondBranchCopy);
				secondBranchCopy.setPreviousNode(saNode);
			}

			// System.out.println("finished sub node");
			return firstBranch;

		} else if (currentGene.toString().contains("if-success")) {
			// System.out.println("starting if node");
			PlanNode testBranch = makeSubPlanDebug(chromosome,
					chromosome.getChild(currentGeneIndex, 0), nesting + 1);
			PlanNode successBranch = makeSubPlanDebug(chromosome,
					chromosome.getChild(currentGeneIndex, 1), nesting + 1);
			PlanNode failureBranch = makeSubPlanDebug(chromosome,
					chromosome.getChild(currentGeneIndex, 2), nesting + 1);
			Iterator<PlanNode> planIter = testBranch.iterator();
			// add if node to all test branches

			int count = 0;
			int debugCount = 0;
			IfNode lastINode = null;
			ArrayList<SingleActionNode> singleActionAtEndOfTestBranch = new ArrayList<SingleActionNode>();
			while (planIter.hasNext()) {
				count++;
				PlanNode tempNode = planIter.next();
				// need to find all ending nodes without changing it because
				// the iterator will iterate through the changed segment
				// as well - need to wait to change till after all are found
				if (tempNode instanceof SingleActionNode) {
					if (((SingleActionNode) tempNode).getNextNode() == null
							&& !(tempNode.isInTestStatement())) {
						singleActionAtEndOfTestBranch.add((SingleActionNode) tempNode);
					}
				}
			}
			for (SingleActionNode endNode : singleActionAtEndOfTestBranch) {
				IfNode iNode = new IfNode(currentGene);
				iNode.setPreviousNode(endNode.getPreviousNode());
				PlanNode successBranchCopy = successBranch.deepCopy();
				// this next line used to have an error - it overrode the previous
				// setting for the first endnode if the second endNode was
				// different from the first - fixed by the success branch copy
				iNode.setSuccessNode(successBranchCopy);
				PlanNode failureBranchCopy = failureBranch.deepCopy();
				iNode.setFailureNode(failureBranchCopy);
				// this next line used to have an error - it overrode the previous
				// setting
				// for the first endnode if the second endNode was different from
				// the first - fixed by failure branch copy
				failureBranchCopy.setPreviousNode(iNode);
				// marking that the node is used in a test statement so
				// sub doesn't append to it
				endNode.setInTestStatement(true);
				// taking the last node of the test branches
				// placing them inside an if statement,
				// and removing the old copy from the plan
				iNode.setTestNode(endNode);
				lastINode = iNode;
				if (endNode.getPreviousNode() != null) {
					if (endNode.getPreviousNode() instanceof SingleActionNode) {
						// node is a single action and only has one child
						((SingleActionNode) endNode.getPreviousNode()).setNextNode(iNode);
					} else {
						// node is an if statement and has 3 children
						IfNode previousINode = (IfNode) endNode.getPreviousNode();
						PlanNode testSuccessNode = previousINode.getSuccessNode();
						if (endNode == testSuccessNode
								&& testSuccessNode instanceof SingleActionNode
								&& ((SingleActionNode) endNode).getNextNode() == null) {
							previousINode.setSuccessNode(iNode);
						} else {
							PlanNode testFailureNode = previousINode.getFailureNode();
							if (endNode == testFailureNode
									&& testFailureNode instanceof SingleActionNode
									&& ((SingleActionNode) endNode).getNextNode() == null) {
								previousINode.setFailureNode(iNode);
							}
						}
					}

				}
			}
			// if only the last action was in the test branch, then the
			// first node of the plan has to be an ifnode, otherwise
			// the first node is still the first node of the test branch
			if (count == 1) {
				return lastINode;
			} else {
				return testBranch; // return the fully built if node
			}
		} else {
			// System.out.println("starting single action");
			// System.out.println("finished single action");
			return new SingleActionNode(currentGene);

		}
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
			System.out.println("in if success");
			PlanNode testBranch = makeSubPlan(chromosome,
					chromosome.getChild(currentGeneIndex, 0));
			PlanNode successBranch = makeSubPlan(chromosome,
					chromosome.getChild(currentGeneIndex, 1));
			PlanNode failureBranch = makeSubPlan(chromosome,
					chromosome.getChild(currentGeneIndex, 2));
			Iterator<PlanNode> planIter = testBranch.iterator();
			// add if node to all test branches
			int count = 0;
			IfNode lastINode = null;
			while (planIter.hasNext()) {
				count++;
				PlanNode tempNode = planIter.next();
				System.out.println("temp node: " + tempNode.getPlanGene().toString());
				if (tempNode instanceof SingleActionNode) {
					System.out.println(1);
					if (((SingleActionNode) tempNode).getNextNode() == null) {
						System.out.println(2);
						IfNode iNode = new IfNode(currentGene);
						iNode.setSuccessNode(successBranch);
						iNode.setFailureNode(failureBranch);
						// taking the last node of the test branches
						// placing them inside an if statement,
						// and removing the old copy from the plan
						iNode.setTestNode(tempNode);
						lastINode = iNode;
						if (tempNode.getPreviousNode() != null) {
							System.out.println(3);
							if (tempNode.getPreviousNode() instanceof SingleActionNode) {
								System.out.println(4);

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
			// System.out.println("!!!!!!!!!!!!!finished a test branch");
			// System.out.println("count: " + count);
			if (count == 1) {
				return lastINode;
			} else {
				return testBranch; // return the fully built if node
			}
		} else {
			return new SingleActionNode(currentGene);
		}
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
