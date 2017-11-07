package ecj;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ec.EvolutionState;
import ec.Evolve;
import ec.Individual;
import ec.gp.GPIndividual;
import ec.simple.SimpleEvaluator;
import ec.simple.SimpleStatistics;
import ec.util.DataPipe;
import ec.util.Log;
import ec.util.Output;
import ec.util.Parameter;
import ec.util.ParameterDatabase;
import omnet.Omnet.Scenario;

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
public class MutationStudy {

	private static double generations = 1;
	private static double popSize = 1000;
	private static double crossoverChance = 0.6;
	private static double killRatio = 0.0;
	private static double invalidActionPenalty = 0;
	private static double verbosenessPenalty = 0.01;
	private static double minAcceptedImprovement = 0.001;
	private static double reproductionChance = .2;
	private static double mutationChance = .2;

	private static GPIndividual ind;
	private static double pi;
	private static double si;
	
	public static ArrayList<Individual> getInds(EvolutionState state){
		ArrayList<Individual> ans = new ArrayList<Individual>();
		
		for (int i = 0; i < state.population.subpops.size(); i++){
			for (int j = 0; j < state.population.subpops.get(i).individuals.size(); j++){
				ans.add((Individual) state.population.subpops.get(i).individuals.get(j).clone());
			}
		}
		
		return ans;
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException{

		File parameterFile = new File("/home/ckinneer/research/AdaptiveSystemsGeneticProgrammingPlanner/selfadaptivesystemsingleobjective.params");

		ParameterDatabase dbase = new ParameterDatabase(parameterFile,new String[] {"-file",parameterFile.getCanonicalPath()});

		// set statistics to simplestatistics
		dbase.setProperty("stat", "ec.simple.SimpleStatistics");

		dbase.setProperty("stat.file", "stats.txt");

		// print header
		System.out.println("trial,generation,individual,plan,scenario,dAverageSize,dAverageFitness,dAverageDiff,dAverageTDiff");
		
		// for every scenario
		for (Scenario scenario : new Scenario[] {Scenario.fourserv,Scenario.requests,Scenario.requestsfourserv,Scenario.econ,Scenario.unreliable,Scenario.failc}){

		// try every plan
		for (String plan : getPlans()){
			
			
			
		// run multiple trials
		for (int trial = 0; trial < 1; trial++){
			
			
			
			boolean once = true;

			Output out = Evolve.buildOutput();

			//disable output
			out.getLog(0).silent = true;
			out.getLog(1).silent = true;
			
			// copy the database so that we can change the values we are interested in
			ParameterDatabase copy = setParams(dbase,scenario,plan);
					
			// run ECJ with the settings that I asked for
			EvolutionState evaluatedState = Evolve.initialize(copy,trial,out);
			
			evaluatedState.startFresh();
			
			ind = MutationBuilder.loadStartInd(evaluatedState);
			
			pi = CustomStats.getProfit(evaluatedState, ind, scenario);
			si = ind.size();
			
			int generation = 0;
			
			int result = EvolutionState.R_NOTDONE;
			while( result == EvolutionState.R_NOTDONE ){
				// do one generation
				long starttime = System.currentTimeMillis();
				result = evaluatedState.evolve();
				long runtime = System.currentTimeMillis() - starttime;
				
				//double[][] c = loadData(evaluatedState, scenario); 
				
				//double[] d = avgDiff(c);
				
				// collect some stats
				SimpleStatistics stats = (SimpleStatistics) evaluatedState.statistics;

				GPIndividual best = (GPIndividual) stats.best_of_run[0];

				ArrayList<Individual> inds = getInds(evaluatedState);
				
				for (int count = 0; count < inds.size(); count++){
					
				Individual i = inds.get(count);
				
				
				double dProfit;
				if (CustomStats.getSize(evaluatedState, i) > 20){
					dProfit = 0 - pi;
				}else{
					dProfit = CustomStats.getProfit(evaluatedState, i, scenario) - pi; 
				}
				
					
				int size = (int) Math.round(((GPIndividual) i).size() - si);
					
				double diff = CustomStats.calcDiff(ind,i,evaluatedState, false);
				double sdiff = CustomStats.calcDiff(ind,i,evaluatedState, true);
				
				/*	
				double dProfit = d[0];

				int size = (int) Math.round(d[1]);
				
				double diff = d[2];
			
				double sdiff = d[3];
				*/
				
				//double avgSize = CustomStats.calcAvgSize(evaluatedState);
				/*
				if (true || once){
					once = false;
					
					ind = MutationBuilder.loadStartInd(evaluatedState);
					
					pi = CustomStats.getProfit(evaluatedState, ind, scenario);
					si = ind.size();
					
					ArrayList<Individual> inds = getInds(evaluatedState);
					
					// now spin up the threads;
					
					final double CPU_USE_FRACTION = 0.75;
			    	
			    	int cores = (int) Math.round(Runtime.getRuntime().availableProcessors() * CPU_USE_FRACTION);
			    	
			    	DistributedInd[] threads = new DistributedInd[cores];
			    	
			    	int done = 0;
			    	int shipped = 0;
			    	
			    	long[] startTime = new long[cores];
			    	
			    	final long MAX_TIME = 1000*60*10;
			    	
			    	while (done < inds.size()){
			    		
			    		// loop through the threads
			    		for (int t = 0; t < threads.length; t++){
			    			
			    			DistributedInd active = threads[t];
			    			
			    			// check if this thread is done
			    			if (active != null && !active.isAlive()){
			    				
			    				double[] d = active.getAns();
			    				
			    				double dProfit = d[0]; 
								
								int size = (int) d[1];
								
								double diff = d[2];
								double sdiff = d[3];
								
								System.out.println(trial+","+generation + ","+ done +","+plan+","+scenario.toString()+","+size+","+dProfit+","+diff+","+sdiff);
	    				
			    				done++;		
			    			}else if (active != null && active.isAlive()){
			    				// if its still running, kill if if its gone for too long
			    				if ((System.currentTimeMillis() - startTime[t]) > MAX_TIME){
			    					threads[t] = null;
			    					active = null;
								System.out.println(trial+","+generation + ","+ done +","+plan+","+scenario.toString()+",NULL,NULL,NULL,NULL");
			    				}
			    			}
			    			
			    			// spin up a new job, as long as there are jobs left to ship
			    			if ((active == null || !active.isAlive()) && shipped < inds.size()){
			    				threads[t] = new DistributedInd(evaluatedState,inds.get(shipped),scenario);
			    				threads[t].start();
			    				shipped++;
			    				
			    				startTime[t] = System.currentTimeMillis();
			    			}
			    			
			    			

		    		}
			    		
			    	}
			    	

				}
				*/
				
				
				System.out.println(trial+","+generation + ","+ count +","+plan+","+scenario.toString()+","+size+","+dProfit+","+diff+","+sdiff);

				}
				generation++;
				//System.out.println(trial+","+generation++ +","+size+","+runtime+","+profit+","+diff+","+sdiff+","+plan+","+scenario.toString()+","+avgSize);
				
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


	public static class DistributedInd extends Thread {
		
		private Individual i;
		private EvolutionState evaluatedState;
		private Scenario scenario;
		
		private double[] ans;
		
		public DistributedInd(EvolutionState state, Individual i, Scenario scenario){
			this.i = i;
			this.evaluatedState = state;
			this.scenario = scenario;
		}
		
		public void run(){
		
			try{
			
		ans = new double[4];
    	
		double dProfit = CustomStats.getProfit(evaluatedState, i, scenario) - pi; 
	
		int size = (int) Math.round(((GPIndividual) i).size() - si);
		
		double diff = CustomStats.calcDiff(ind,i,evaluatedState, false);
		double sdiff = CustomStats.calcDiff(ind,i,evaluatedState, true);
		
		ans[0] = dProfit;
		ans[1] = size;
		ans[2] = diff;
		ans[3] = sdiff;
		
			}catch(Exception e){
				e.printStackTrace();
			}
	
		}
    	
		public double[] getAns(){
			return ans;
		}
		
    }
	

	private static double[][] loadData(EvolutionState evaluatedState, Scenario scenario) {
		
		GPIndividual ind = MutationBuilder.loadStartInd(evaluatedState);
		
		double pi = CustomStats.getProfit(evaluatedState, ind, scenario);
		double si = ind.size();
		
			ArrayList<Individual> inds = getInds(evaluatedState);
			
			double[][] ans = new double[4][(int) popSize];
			
			for (int i = 0; i < inds.size(); i++){
				
				
				ans[0][i] = CustomStats.getProfit(evaluatedState, inds.get(i), scenario) - pi; 
				
				ans[1][i] = ((GPIndividual)inds.get(i)).size() - si;
				
				ans[2][i] = CustomStats.calcDiff(ind,inds.get(i),evaluatedState, false);
				ans[3][i] = CustomStats.calcDiff(ind,inds.get(i),evaluatedState, true);
				
			}
			
		return ans;
	}
	
	private static double[] avgDiff(double[][] d){
		
double[] ans = new double[d.length];
		
		for (int m = 0; m < d.length; m++){
			
			double msum = 0;
			double mcount = 0;
			
			for (int i = 0; i < d[0].length; i++){
	
				
					msum += d[m][i];
					mcount++;
		
			}
			
			ans[m] = msum / mcount;
		}
		
		return ans;
		
	}
	
	private static double[] avgDiff(double[][] d1, double[][] d2){
		double[] ans = new double[d1.length];
		
		for (int m = 0; m < d1.length; m++){
			
			double msum = 0;
			double mcount = 0;
			
			for (int i = 0; i < d1[0].length; i++){
	
				
				for (int j = 0; j< d2[0].length; j++){
				
					msum += (d2[m][j] - d1[m][i]);
					mcount++;
				
				}
				
			}
			
			ans[m] = msum / mcount;
		}
		
		return ans;
	}

	private static List<String> getPlans() {
		return Arrays.asList("long","short","poor");
	}




	private static ParameterDatabase setParams(ParameterDatabase dbase,Scenario scenario, String plan) throws ClassNotFoundException, IOException {
		ParameterDatabase copy = (ParameterDatabase) (DataPipe.copy(dbase));
		
		// change the file name so we know where this data came from
		//copy.setProperty("stat.file", fileString);
		
		copy.setProperty("gp.koza.mutate.source.0", "ec.select.RandomSelection");
		copy.setProperty("gp.breed.mutate-one-node.source.0", "ec.select.RandomSelection");
		copy.setProperty("gp.koza.xover.source.0", "ec.select.RandomSelection");
		copy.setProperty("breed.reproduce.source.0", "ec.select.RandomSelection");
		
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
	
		
		// now set the param for the starting plan
		// if from scratch, special behavior is needed
		String init;
		
		if (plan.equals("scratch")){
			init = "ec.gp.koza.HalfBuilder";
		}else{
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
			case "poor": ans = "(; (IncreaseTraffic A) (; (DecreaseDimmer B) (; (DecreaseDimmer B) (DecreaseDimmer B))))"; break;
			case "long": ans = "(; (; (T (StartServer B) (T (StartServer B) (T (StartServer B) (T (StartServer B) (StartServer B) (ShutdownServer A)) (ShutdownServer A)) (StartServer C)) (; (StartServer C) (; (StartServer C) (ShutdownServer A)))) (StartServer C)) (; (F ERC[i4|] (; (StartServer C) (ShutdownServer A))) (; (T (StartServer B) (T (StartServer B) (T (StartServer B) (T (StartServer B) (StartServer B) (ShutdownServer A)) (ShutdownServer A)) (; (StartServer C) (ShutdownServer A))) (; (StartServer C) (; (; (StartServer C) (ShutdownServer A)) (ShutdownServer A)))) (StartServer C))))"; break;
			case "short": ans = "(; (; (; (StartServer C) (; (StartServer C) (; (; (F ERC[i4|] (ShutdownServer A)) (; (StartServer B) (F ERC[i2|] (StartServer B)))) (F ERC[i4|] (StartServer C))))) (StartServer B)) (StartServer B))"; break;
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
