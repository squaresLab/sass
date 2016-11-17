package ecj;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import ec.EvolutionState;
import ec.Evolve;
import ec.gp.GPIndividual;
import ec.simple.SimpleEvaluator;
import ec.simple.SimpleStatistics;
import ec.util.DataPipe;
import ec.util.Log;
import ec.util.Output;
import ec.util.Parameter;
import ec.util.ParameterDatabase;

/*
Parameters that we are interested in:
generations = 50
pop.subpop.0.size =	1024
//crossover chance
pop.subpop.0.species.pipe.source.0.prob = 0.8
// mutation chance
pop.subpop.0.species.pipe.source.1.prob = 0.2
// the use of parsimony pressure to control "code bloat"
#stat.num-children = 1
#stat.child.0 = ec.parsimony.TarpeianStatistics
#stat.child.0.kill-proportion = 0.2
 */

/**
 * 
 * @author Cody Kinneer
 * 
 * This class will study how the planner handles adaptation
 *
 */
public class Adaptor {

	private static double generations = 30;
	private static double popSize = 1000;
	private static double crossoverChance = .6;
	private static double killRatio = 0.0;
	private static double invalidActionPenalty = 0;
	private static double verbosenessPenalty = 0.01;
	private static double minAcceptedImprovement = 0.001;
	private static double reproductionChance = 0.2;
	private static double mutationChance = 0.2;

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException{

		File parameterFile = new File("/home/cody/AdaptiveSystemsGeneticProgrammingPlanner/selfadaptivesystemsingleobjective.params");

		ParameterDatabase dbase = new ParameterDatabase(parameterFile,new String[] {"-file",parameterFile.getCanonicalPath()});

		// set statistics to simplestatistics
		dbase.setProperty("stat", "ec.simple.SimpleStatistics");

		dbase.setProperty("stat.file", "stats.txt");

		// print header
		System.out.println("trial,generation,size,runtime,profit");
			
		// run multiple trials
		for (int trial = 0; trial < 10; trial++){

			Output out = Evolve.buildOutput();

			//disable output
			out.getLog(0).silent = true;
			out.getLog(1).silent = true;
			
			// copy the database so that we can change the values we are interested in
			ParameterDatabase copy = setParams(dbase);
					
			// run ECJ with the settings that I asked for
			EvolutionState evaluatedState = Evolve.initialize(copy,trial,out);
			
			evaluatedState.startFresh();
			
			int generation = 0;
			
			int result = EvolutionState.R_NOTDONE;
			while( result == EvolutionState.R_NOTDONE ){
				// do one generation
				long starttime = System.currentTimeMillis();
				result = evaluatedState.evolve();
				long runtime = System.currentTimeMillis() - starttime;
				
				// collect some stats
				SimpleStatistics stats = (SimpleStatistics) evaluatedState.statistics;

				GPIndividual best = (GPIndividual) stats.best_of_run[0];

				double profit = CustomStats.getProfit(evaluatedState, best);

				int size = CustomStats.getSize(evaluatedState, best);
				
				System.out.println(trial+","+generation++ +","+size+","+runtime+","+profit);
				
				}
			
			SimpleStatistics stats = (SimpleStatistics) evaluatedState.statistics;

			GPIndividual best = (GPIndividual) stats.best_of_run[0];
					
			// We have to do this in order to fix a memory leak
			// otherwise, ECJ will keep making threads forever until we run out of memory to track them all
			((SimpleEvaluator)evaluatedState.evaluator).pool.killAll();

			Evolve.cleanup(evaluatedState);

			// also fixes a resource leak
			out.close();

		}
		
		}


	
	
	private static ParameterDatabase setParams(ParameterDatabase dbase) throws ClassNotFoundException, IOException {
		ParameterDatabase copy = (ParameterDatabase) (DataPipe.copy(dbase));
		
		// change the file name so we know where this data came from
		//copy.setProperty("stat.file", fileString);

		// params in the ecj params file that we want to vary
		copy.setProperty("generations", generations+"");
		copy.setProperty("pop.subpop.0.size", popSize+"");
		copy.setProperty("pop.subpop.0.species.pipe.source.0.source.0.prob", crossoverChance+"");
		copy.setProperty("pop.subpop.0.species.pipe.source.0.source.1.prob", reproductionChance+"");
		copy.setProperty("pop.subpop.0.species.pipe.source.0.source.2.prob", mutationChance+"");

		// these three are all setting up Tarpeian Parsimony pressure
		copy.setProperty("stat.num-children", "1");
		copy.setProperty("stat.child.0", "ec.parsimony.TarpeianStatistics");
		copy.setProperty("stat.child.0.kill-proportion", killRatio+"");

		// params in our java stuff that we want to alter
		copy.setProperty("invalid_action_penalty", invalidActionPenalty+"");
		copy.setProperty("verboseness_penalty", verbosenessPenalty+"");
		copy.setProperty("min_accepted_improvement", minAcceptedImprovement+"");
		
		return copy;
	}

	public void scorePlan(EvolutionState evaluatedState){
		evaluatedState.setup(evaluatedState, null);
		
		GPIndividual ind = MutationBuilder.loadStartInd(evaluatedState);
		
		OmnetProblemSingle prob = new OmnetProblemSingle();
		prob.setup(evaluatedState, new Parameter("eval.problem"));
		
		prob.evaluate(evaluatedState, ind, 0, 0);
		
		System.out.println(CustomStats.getProfit(evaluatedState, ind));
		
		System.exit(0);
	}

}
