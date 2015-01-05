package test;

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

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
