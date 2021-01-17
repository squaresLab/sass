package bullseye;

import java.io.File;
import java.io.IOException;

import ec.EvolutionState;
import ec.Evolve;
import ec.gp.GPIndividual;
import ec.simple.SimpleEvaluator;
import ec.simple.SimpleStatistics;
import ec.util.Output;
import ec.util.Parameter;
import ec.util.ParameterDatabase;
import ecj.CustomStats;

public class CoevRunner {
	
	public static void main(String[] args) {
		File parameterFile = new File( java.lang.System.getProperty("user.dir")+"/bullseye.gp.params");

		ParameterDatabase dbase;
		
		EvolutionState state = null;
		
		try {
			dbase = new ParameterDatabase(parameterFile,new String[] {"-file",parameterFile.getCanonicalPath()});
			
//			dbase.setProperty("gp.tc.0.init","ec.gp.koza.HalfBuilder");
			dbase.set(new Parameter("stat"), "bullseye.SimpleCoevStatistics");
			Output out = Evolve.buildOutput();

			//disable output
			out.getLog(0).silent = false;
			out.getLog(1).silent = true;
			
			int trial = 0;
			
			// run ECJ with the settings that I asked for
			EvolutionState evaluatedState = Evolve.initialize(dbase,trial,out);
			
			evaluatedState.startFresh();
			
			int generation = 0;
			
			int result = EvolutionState.R_NOTDONE;
			
			long totaltime = 0;
			
			while( result == EvolutionState.R_NOTDONE ){
				
				// do one generation
				long starttime = java.lang.System.currentTimeMillis();
				result = evaluatedState.evolve();
				long runtime = java.lang.System.currentTimeMillis() - starttime;
				
				totaltime += runtime;
				
				// collect some stats
				SimpleCoevStatistics stats = (SimpleCoevStatistics) evaluatedState.statistics;

				GPIndividual best = (GPIndividual) stats.best_of_run[0];
//				evaluatedState.
				
//				System.out.println(trial+","+generation++ +","+size+","+runtime+","+profit+","+diff+","+sdiff+","+plan+"," + init+"," + window +","+ buildprob+","+ enableRuntimeKill+","+trimmerChance+","+scenario.toString()+","+avgSize);
				
				}
			
			SimpleCoevStatistics stats = (SimpleCoevStatistics) evaluatedState.statistics;

			GPIndividual best = (GPIndividual) stats.best_of_run[0];
					
			// We have to do this in order to fix a memory leak
			// otherwise, ECJ will keep making threads forever until we run out of memory to track them all
//			((SimpleEvaluator)evaluatedState.evaluator).pool.killAll();

			Evolve.cleanup(evaluatedState);

			// also fixes a resource leak
			out.close();
			
			
		}catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
