package test;

import java.lang.reflect.Field;
import java.util.ArrayList;

import main.EvaluateFitness;
import main.TooManyBranchesException;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.GPFitnessFunction;
import org.jgap.gp.IGPFitnessEvaluator;
import org.jgap.gp.function.SubProgram;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.impl.ProgramChromosome;

import actions.IncreaseDatabaseBThreads;

public class SimpleFailingPlan {

	static int size = 5;
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
			commands[0] = new SubProgram(gpConf, new Class[] { CommandGene.VoidClass,
					CommandGene.VoidClass }, true);
			depths[0] = 0;
			commands[1] = new SubProgram(gpConf, new Class[] { CommandGene.VoidClass,
					CommandGene.VoidClass }, true);
			depths[1] = 1;
			commands[2] = new IncreaseDatabaseBThreads(gpConf);
			depths[2] = 2;
			commands[3] = new IncreaseDatabaseBThreads(gpConf);
			depths[3] = 2;
			commands[4] = new IncreaseDatabaseBThreads(gpConf);
			depths[4] = 1;

			ProgramChromosome chromosome = new ProgramChromosome(gpConf, size);
			chromosome.setFunctions(commands);

			Field depthArray = chromosome.getClass().getDeclaredField("m_depth");
			depthArray.setAccessible(true);
			depthArray.set(chromosome, depths);

			ArrayList<ArrayList<CommandGene>> planList = null;
			EvaluateFitness ef = new EvaluateFitness();
			try {
				planList = ef.generatePossiblePlanExecutions(chromosome);
			} catch (TooManyBranchesException e) {
				System.out.println("Too many branches: " + e.getNumberOfBranches());

			}
			// System.out.println("plan list size: " + planList.size());
			boolean planIsCompletelyFeasible = true;
			for (int i = 0; i < planList.size(); i++) {
				System.out.println("Plan execution: ");
				for (int j = 0; j < planList.size(); j++) {
					System.out.println(planList.get(j));
				}
				boolean isPlanFeasible = ef.checkPlanFeasibility(planList.get(i));
				if (!isPlanFeasible) {
					planIsCompletelyFeasible = false;
					break;
				}
			}
			System.out.println("Plan is completely feasible: "
					+ planIsCompletelyFeasible);
		} catch (Exception e) {

		}
	}

}
