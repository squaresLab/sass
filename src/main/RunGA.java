package main;
import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.GPFitnessFunction;
import org.jgap.gp.IGPFitnessEvaluator;
import org.jgap.gp.IGPProgram;
import org.jgap.gp.function.SubProgram;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.impl.GPGenotype;
import org.jgap.util.NumberKit;

import actions.AddServerL1;
import actions.AddServerL2;
import actions.DeleteServerL1;
import actions.DeleteServerL2;
import actions.IfSuccessElse;
import actions.IncreaseDatabaseAThreads;
import actions.IncreaseDatabaseBThreads;
import actions.IncreaseTextResolution;
import actions.ReduceTextResolution;
import actions.SystemState;

public class RunGA {

	public static void main(String args[]) {
		// Configuration conf = new DefaultConfiguration();
		long startTime = System.currentTimeMillis();

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
		GPGenotype gp = null;
		try {
			gp = create(gpConf);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// final Thread t = new Thread(gp);

		// I think this is the way to find the fittest individual in each generation
		/*
		 * gpConf.getEventManager().addEventListener(
		 * GeneticEvent.GPGENOTYPE_EVOLVED_EVENT, new GeneticEventListener() {
		 * public void geneticEventFired(GeneticEvent a_firedEvent) { GPGenotype
		 * genotype = (GPGenotype) a_firedEvent.getSource(); int evno =
		 * genotype.getGPConfiguration().getGenerationNr(); double freeMem =
		 * SystemKit.getFreeMemoryMB(); if (evno % 100 == 0) { double bestFitness =
		 * genotype.getFittestProgram() .getFitnessValue();
		 * System.out.println("Evolving generation " + evno + ", best fitness: " +
		 * bestFitness + ", memory free: " + freeMem + " MB"); } if (evno > 500000)
		 * { t.stop(); } else { try { // Collect garbage if memory low. //
		 * ------------------------------ if (freeMem < 50) { System.gc(); (new
		 * Thread()).sleep(500); } else { // Avoid 100% CPU load. //
		 * -------------------- (new Thread()).sleep(30); } } catch
		 * (InterruptedException iex) { iex.printStackTrace(); System.exit(1); } } }
		 * }); GeneticEventListener myGeneticEventListener = new EventListener(this,
		 * t); gpConf.getEventManager().addEventListener(
		 * GeneticEvent.GPGENOTYPE_NEW_BEST_SOLUTION, myGeneticEventListener);
		 */
		gp.setVerboseOutput(true);
		int generationCount = 0;
		try {
			while (generationCount < 10) {
				System.out.println("Starting to evolve generation");
				gp.evolve();
				System.out.println("Finished a generation");
				gp.calcFitness();
				// Do G.C. for cleanup and to avoid 100% CPU load.
				// -----------------------------------------------
				System.out.println("Finished a calculating fitness");
				printSolution(gp.getGPPopulation().determineFittestProgram());
				System.gc();
				System.out.println("Finished garbage collection");
				generationCount++;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
			System.exit(1);
		}
		System.out.println("Finished Running program!!");
		long endTime = System.currentTimeMillis();
		System.out.println("Total Execution Time: " + (endTime - startTime));
	}

	/**
	 * Sets up the functions to use and other parameters. Then creates the initial
	 * genotype.
	 * 
	 * @return the genotype created
	 * @author Klaus Meffert
	 * @throws Exception
	 * @since 3.0
	 */
	public static GPGenotype create(GPConfiguration gpConf) throws Exception {
		Class[] types = { CommandGene.VoidClass };
		Class[][] argTypes = { {} };
		int[] minDepths = new int[] { 1 };
		int[] maxDepths = new int[] { 2 };
		SystemState ss = new SystemState();
		CommandGene[][] nodeSets = { {
				// new SubProgram(gpConf, new Class[] { CommandGene.VoidClass,
				// CommandGene.VoidClass, CommandGene.VoidClass }, true),
				new SubProgram(gpConf, new Class[] { CommandGene.VoidClass,
						CommandGene.VoidClass }, true),
				// new SubProgram(gpConf,
				// new Class[] {
				// CommandGene.VoidClass, // nonclassic
				// CommandGene.VoidClass, CommandGene.VoidClass,
				// CommandGene.VoidClass }),
				new IfSuccessElse(gpConf), new AddServerL1(gpConf),
				new AddServerL2(gpConf),
				new DeleteServerL1(gpConf),
				new DeleteServerL2(gpConf), // nonclassic
				new IncreaseDatabaseAThreads(gpConf),
				new IncreaseDatabaseBThreads(gpConf), // nonclassic
				new IncreaseTextResolution(gpConf), // nonclassic
				new ReduceTextResolution(gpConf), // nonclassic
		} };
		// Create genotype with initial population.
		// ----------------------------------------
		return GPGenotype.randomInitialGenotype(gpConf, types, argTypes, nodeSets,
				minDepths, maxDepths, 1000, new boolean[] { true }, true);
	}

	/*
	 * class EventListener implements GeneticEventListener {
	 * 
	 * private AntTrailProblem problem; private Thread m_t;
	 * 
	 * public EventListener(AntTrailProblem a_problem, Thread a_t) { problem =
	 * a_problem; m_t = a_t; }
	 * 
	 * /** New best solution found.
	 * 
	 * @param a_firedEvent GeneticEvent
	 */
	/*
	 * public void geneticEventFired(GeneticEvent a_firedEvent) { GPGenotype
	 * genotype = (GPGenotype) a_firedEvent.getSource(); int evno =
	 * genotype.getGPConfiguration().getGenerationNr(); String indexString = "" +
	 * evno; while (indexString.length() < 5) { indexString = "0" + indexString; }
	 * String filename = "anttrail_best" + indexString + ".png"; IGPProgram best =
	 * genotype.getAllTimeBest(); try { // Create graphical tree of GPProgram. //
	 * ----------------------------------- TreeBranchRenderer antBranchRenderer =
	 * new AntTreeBranchRenderer(); TreeNodeRenderer antNodeRenderer = new
	 * AntTreeNodeRenderer(); problem.showTree(best, filename, antBranchRenderer,
	 * antNodeRenderer); // Display solution's trail. // -------------------------
	 * AntMap antmap = (AntMap) best.getApplicationData();
	 * problem.displaySolution(antmap.getMovements());
	 * System.out.println(" Number of moves: " + antmap.getMoveCount());
	 * System.out.println(" Food taken: " + antmap.getFoodTaken()); } catch
	 * (InvalidConfigurationException iex) { iex.printStackTrace(); } double
	 * bestFitness = genotype.getFittestProgram().getFitnessValue(); if
	 * (bestFitness < 0.001) { genotype.outputSolution(best); m_t.stop();
	 * System.exit(0); } } }
	 */

	public static void printSolution(IGPProgram a_best) {
		if (a_best == null) {
			System.out.println("No best solution (null)");
			return;
		}
		double bestValue = a_best.getFitnessValue();
		if (Double.isInfinite(bestValue)) {
			System.out.println("No best solution (infinite)");
			return;
		}
		System.out.println("Best solution fitness: "
				+ NumberKit.niceDecimalNumber(bestValue, 2));
		System.out.println("Best solution: " + a_best.toStringNorm(0));
		String depths = "";
		int size = a_best.size();
		for (int i = 0; i < size; i++) {
			if (i > 0) {
				depths += " / ";
			}
			depths += a_best.getChromosome(i).getDepth(0);
		}
		if (size == 1) {
			System.out.println("Depth of chrom: " + depths);
		} else {
			System.out.println("Depths of chroms: " + depths);
		}
	}
}
