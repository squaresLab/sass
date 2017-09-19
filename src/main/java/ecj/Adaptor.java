package ecj;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
import omnet.Omnet.Scenario;
import system.Simulator;
import tactics.Plan;

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
		System.out.println("trial,generation,bestSize,runtime,profit,distance,structureDistance,plan,init,window,buildProb,runtimeKill,scenario,averageSize");
		
		
		
		// run multiple trials
		for (int trial = 0; trial < 10; trial++){
			
		for (double enableRuntimeKill : new double[]{0.5,0.75,1.0}){
		
		// for every scenario
		//for (Scenario scenario : new Scenario[] {Scenario.fourserv,Scenario.requests,Scenario.requestsfourserv,Scenario.econ,Scenario.unreliable,Scenario.failc}){
		for (Scenario scenario : new Scenario[] {Scenario.requestsfourserv}){

		// try every plan
		for (String plan : getPlans()){
			
		// try differant start strategies
		for (String init : getInits(plan)){
			
		//adjust the amount of plans from scratch vs seeded plans in the population
		for (double buildprob : getBuildProbs(plan)){
			
		for (long window : new long[] {10,100,1000,10000}){

			Plan.window = window;
			
			Output out = Evolve.buildOutput();

			//disable output
			out.getLog(0).silent = true;
			out.getLog(1).silent = true;
			
			// copy the database so that we can change the values we are interested in
			ParameterDatabase copy = setParams(dbase,scenario,plan,init,buildprob,enableRuntimeKill);
			
			Simulator.setRuntimeKillEnable(enableRuntimeKill < 1 ? true : false);
					
			// run ECJ with the settings that I asked for
			EvolutionState evaluatedState = Evolve.initialize(copy,trial,out);
			
			evaluatedState.startFresh();
			
			int generation = 0;
			
			int result = EvolutionState.R_NOTDONE;
			
			long totaltime = 0;
			
			while( result == EvolutionState.R_NOTDONE ){
				
				// do one generation
				long starttime = System.currentTimeMillis();
				result = evaluatedState.evolve();
				long runtime = System.currentTimeMillis() - starttime;
				
				totaltime += runtime;
				
				// collect some stats
				SimpleStatistics stats = (SimpleStatistics) evaluatedState.statistics;

				GPIndividual best = (GPIndividual) stats.best_of_run[0];

				double profit = CustomStats.getProfit(evaluatedState, best, scenario,(long) Math.ceil(totaltime/1000.0));

				int size = CustomStats.getSize(evaluatedState, best);
				
				double diff = -1; // CustomStats.calcDiversity(evaluatedState, false);
				double sdiff = -1; //CustomStats.calcDiversity(evaluatedState, true);
				
				double avgSize = CustomStats.calcAvgSize(evaluatedState);
				
				System.out.println(trial+","+generation++ +","+size+","+runtime+","+profit+","+diff+","+sdiff+","+plan+"," + init+"," + window +","+ buildprob+","+ enableRuntimeKill+","+scenario.toString()+","+avgSize);
				
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
		}
		}
		}
		}
		}
		}

	
	
	private static double[] getBuildProbs(String plan) {
		
		if (plan.equals("scratch")){
			
			return new double[] {0};
			
		}else{
			
			return new double[] {0.0,0.50,0.75,0.90};
			
		}
	}




	private static String[] getInits(String plan) {
		if (plan.equals("scratch")){
			
			return new String[] {"scratch"};
			
		}else{
			
			return new String[] {"trimmer","mutator"};
			
		}
		
	}




	private static List<String> getPlans() {
		return Arrays.asList("long","scratch");//,"short","poor","scratch");
	}




	private static ParameterDatabase setParams(ParameterDatabase dbase,Scenario scenario, String plan,String initializer, double buildprob, double enableRuntimeKill) throws ClassNotFoundException, IOException {
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
		
		copy.setProperty("scenario_name", scenario.toString()+"");
		
		copy.setProperty("build_prob", buildprob+"");
		
		copy.setProperty("runtime_kill_ratio", enableRuntimeKill+"");
		
		// now set the param for the starting plan
		// if from scratch, special behavior is needed
		String init = "";
		
		if (initializer.equals("scratch")){
			init = "ec.gp.koza.HalfBuilder";
		}else if (initializer.equals("trimmer")){
			init = "ecj.MutationTrimmer";
		}else if (initializer.equals("mutator")){
			init = "ecj.MutationBuilder";
		}
		
		if (scenario.equals(Scenario.fourserv) || scenario.equals(Scenario.requestsfourserv)){
			copy.setProperty("gp.fs.0.size", "14");
		}else{
			copy.setProperty("gp.fs.0.size", "13");
		}
		
		copy.setProperty("gp.tc.0.init",init);
		
		copy.setProperty("initial_ind", getPlan(plan));
		
		return copy;
	}




	private static String getPlan(String plan) {
		
		String ans;
		
		switch(plan){
			case "long": ans = "(; (F ERC[i3|] (; (StartServer B) (; (DecreaseTraffic A) (T (StartServer C) (StartServer C) (StartServer B))))) (T (; (; (StartServer B) (; (T (StartServer B) (StartServer B) (IncreaseDimmer C)) (T (StartServer B) (T (StartServer B) (StartServer B) (StartServer C)) (DecreaseTraffic B)))) (StartServer B)) (ShutdownServer A) (StartServer B)))"; break;
			//case "poor": ans = "(; (IncreaseTraffic A) (; (DecreaseDimmer B) (; (DecreaseDimmer B) (DecreaseDimmer B))))"; break;
			//case "long": ans = "(; (; (T (StartServer B) (T (StartServer B) (T (StartServer B) (T (StartServer B) (StartServer B) (ShutdownServer A)) (ShutdownServer A)) (StartServer C)) (; (StartServer C) (; (StartServer C) (ShutdownServer A)))) (StartServer C)) (; (F ERC[i4|] (; (StartServer C) (ShutdownServer A))) (; (T (StartServer B) (T (StartServer B) (T (StartServer B) (T (StartServer B) (StartServer B) (ShutdownServer A)) (ShutdownServer A)) (; (StartServer C) (ShutdownServer A))) (; (StartServer C) (; (; (StartServer C) (ShutdownServer A)) (ShutdownServer A)))) (StartServer C))))"; break;
			//case "short": ans = "(; (; (; (StartServer C) (; (StartServer C) (; (; (F ERC[i4|] (ShutdownServer A)) (; (StartServer B) (F ERC[i2|] (StartServer B)))) (F ERC[i4|] (StartServer C))))) (StartServer B)) (StartServer B))"; break;
			default: ans = "";
		}

		return ans;
	}
	
	/*

	public void scorePlan(EvolutionState evaluatedState,Scenario scenario){
		evaluatedState.setup(evaluatedState, null);
		
		GPIndividual ind = MutationBuilder.loadStartInd(evaluatedState);
		
		OmnetProblemSingle prob = new OmnetProblemSingle();
		prob.setup(evaluatedState, new Parameter("eval.problem"));
		
		prob.evaluate(evaluatedState, ind, 0, 0);
		
		System.out.println(CustomStats.getProfit(evaluatedState, ind, scenario));
		
		System.exit(0);
	}
	*/

}
