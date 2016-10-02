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
 * This class will conduct a parameter sweep to give us some insights into how changing the params effects the time and fitness
 *
 */
public class Sweeper {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException{
		
		File parameterFile = new File("/home/cody/AdaptiveSystemsGeneticProgrammingPlanner/selfadaptivesystemsingleobjective.params");
		
		ParameterDatabase dbase = new ParameterDatabase(parameterFile,new String[] {"-file",parameterFile.getCanonicalPath()});

		// set statistics to simplestatistics
		dbase.setProperty("stat", "ec.simple.SimpleStatistics");
		
		dbase.setProperty("stat.file", "/dev/null");
		
		// print header
		System.out.println("generations,popSize,crossoverChance,killRatio,invalidActionPenalty,verbosenessPenalty,minAcceptedImprovement,runtime,profit");
		
		// run multiple trials
		for (int trials = 0; trials < 10; trials++){
		
		for (int generations = 10; generations <= 1000; generations *= 10){

			for(int popSize = 10; popSize <= 1000; popSize *= 10){

				for (double crossoverChance: new double[] {.9,.8,.7,.6,0}){
					double mutationChance = 1-crossoverChance;

					for (double killRatio: new double[] {0,.1,.2,.3,.4}){

						for(double invalidActionPenalty: new double[] {10,1,.1,.01,.001,0}){

							for(double verbosenessPenalty: new double[] {10,1,.1,.01,.001,0}){

								for(double minAcceptedImprovement: new double[] {10,1,.1,.01,.001,0}){
									
									Output out = Evolve.buildOutput();
									
									//disable output
									out.getLog(0).silent = true;
									out.getLog(1).silent = true;
									
									// copy the database so that we can change the values we are interested in
									ParameterDatabase copy = (ParameterDatabase) (DataPipe.copy(dbase));
									
									// run ECJ with the settings that I asked for
									EvolutionState evaluatedState = Evolve.initialize(copy,0,out);
									
									String line = generations + "," + popSize + "," + crossoverChance + "," + killRatio + "," + invalidActionPenalty +"," + verbosenessPenalty + "," + minAcceptedImprovement + ",";
																	
									// change the file name so we know where this data came from
									//copy.setProperty("stat.file", fileString);
									
									// params in the ecj params file that we want to vary
									copy.setProperty("generations", generations+"");
									copy.setProperty("pop.subpop.0.size", popSize+"");
									copy.setProperty("pop.subpop.0.species.pipe.source.0.prob", crossoverChance+"");
									copy.setProperty("pop.subpop.0.species.pipe.source.1.prob", mutationChance+"");

									// these three are all setting up Tarpeian Parsimony pressure
									copy.setProperty("stat.num-children", "1");
									copy.setProperty("stat.child.0", "ec.parsimony.TarpeianStatistics");
									copy.setProperty("stat.child.0.kill-proportion", killRatio+"");

									// params in our java stuff that we want to alter
									copy.setProperty("invalid_action_penalty", invalidActionPenalty+"");
									copy.setProperty("verboseness_penalty", verbosenessPenalty+"");
									copy.setProperty("min_accepted_improvement", minAcceptedImprovement+"");									
													
									long starttime = System.currentTimeMillis();
									
									evaluatedState.run(EvolutionState.C_STARTED_FRESH);
									
									long runtime = System.currentTimeMillis() - starttime;
									
									SimpleStatistics stats = (SimpleStatistics) evaluatedState.statistics;
									
									GPIndividual best = (GPIndividual) stats.best_of_run[0];
									
									double profit = CustomStats.getProfit(evaluatedState, best);
									
									System.out.println(line+runtime+","+profit);
									
									((SimpleEvaluator)evaluatedState.evaluator).pool.killAll();
									
									Evolve.cleanup(evaluatedState);
									
									out.close();
									
								}

							}

						}

					}
					}

				}
			}	

		}
	}

}
