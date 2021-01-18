/*
  Copyright 2006 by Sean Luke
  Licensed under the Academic Free License version 3.0
  See the file "LICENSE" for more information
*/


package bullseye;
import ec.*;
import ec.simple.SimpleProblemForm;
import ec.steadystate.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

import coevutil.MatchupTester;
import ec.util.*;

import java.io.BufferedReader;
import java.io.File;

/* 
 * SimpleStatistics.java
 * 
 * Created: Tue Aug 10 21:10:48 1999
 * By: Sean Luke
 */

/**
 * A basic Statistics class suitable for simple problem applications.
 *
 * SimpleStatistics prints out the best individual, per subpopulation,
 * each generation.  At the end of a run, it also prints out the best
 * individual of the run.  SimpleStatistics outputs this data to a log
 * which may either be a provided file or stdout.  Compressed files will
 * be overridden on restart from checkpoint; uncompressed files will be 
 * appended on restart.
 *
 * <p>SimpleStatistics implements a simple version of steady-state statistics:
 * if it quits before a generation boundary,
 * it will include the best individual discovered, even if the individual was discovered
 * after the last boundary.  This is done by using individualsEvaluatedStatistics(...)
 * to update best-individual-of-generation in addition to doing it in
 * postEvaluationStatistics(...).

 <p><b>Parameters</b><br>
 <table>
 <tr><td valign=top><i>base.</i><tt>gzip</tt><br>
 <font size=-1>boolean</font></td>
 <td valign=top>(whether or not to compress the file (.gz suffix added)</td></tr>
 <tr><td valign=top><i>base.</i><tt>file</tt><br>
 <font size=-1>String (a filename), or nonexistant (signifies stdout)</font></td>
 <td valign=top>(the log for statistics)</td></tr>
 <tr><td valign=top><i>base.</i><tt>do-final</tt><br>
 <font size=-1>boolean</font></td>
 <td valign=top>(do we print out any final statistics to the log?)</td></tr>
 <tr><td valign=top><i>base.</i><tt>do-generation</tt><br>
 <font size=-1>boolean</font></td>
 <td valign=top>(do we print out any per-generation statistics to the log?)</td></tr>
 <tr><td valign=top><i>base.</i><tt>do-message</tt><br>
 <font size=-1>boolean</font></td>
 <td valign=top>(do we print out any per-generation statistics to stdout?)</td></tr>
 <tr><td valign=top><i>base.</i><tt>do-description</tt><br>
 <font size=-1>boolean</font></td>
 <td valign=top>(do we print out any final description to the log?)</td></tr>
 <tr><td valign=top><i>base.</i><tt>do-per-generation-description</tt><br>
 <font size=-1>boolean</font></td>
 <td valign=top>(do we print out any per-generation description to stdout?)</td></tr>
 </table>

 *
 * @author Sean Luke
 * @version 1.0 
 */

public class SimpleCoevStatistics extends Statistics implements SteadyStateStatisticsForm //, ec.eval.ProvidesBestSoFar
    {
    public Individual[] getBestSoFar() { return best_of_run; }

    /** log file parameter */
    public static final String P_STATISTICS_FILE = "file";
    
    /** compress? */
    public static final String P_COMPRESS = "gzip";
    
    public static final String P_DO_FINAL = "do-final";
    public static final String P_DO_GENERATION = "do-generation";
    public static final String P_DO_MESSAGE = "do-message";
    public static final String P_DO_DESCRIPTION = "do-description";
    public static final String P_DO_PER_GENERATION_DESCRIPTION = "do-per-generation-description";
    public static final String P_EXPLOITABILITY_PATH = "exploitability-script-path";

    /** The Statistics' log */
    public int statisticslog = 0;  // stdout

    /** The best individual we've found so far */
    public Individual[] best_of_run = null;
        
    /** Should we compress the file? */
    public boolean compress;
    public boolean doFinal;
    public boolean doGeneration;
    public boolean doMessage;
    public boolean doDescription;
    public boolean doPerGenerationDescription;
    
    private long cumulativeTime;
    private long evalStartTime;
    
    public static String exploitPath;

    public void setup(final EvolutionState state, final Parameter base)
        {
        super.setup(state,base);
        
        compress = state.parameters.getBoolean(base.push(P_COMPRESS),null,false);
                
        File statisticsFile = state.parameters.getFile(base.push(P_STATISTICS_FILE),null);

        doFinal = state.parameters.getBoolean(base.push(P_DO_FINAL),null,true);
        doGeneration = state.parameters.getBoolean(base.push(P_DO_GENERATION),null,true);
        doMessage = state.parameters.getBoolean(base.push(P_DO_MESSAGE),null,true);
        doDescription = state.parameters.getBoolean(base.push(P_DO_DESCRIPTION),null,true);
        doPerGenerationDescription = state.parameters.getBoolean(base.push(P_DO_PER_GENERATION_DESCRIPTION),null,false);

        exploitPath = state.parameters.getString(base.push(P_EXPLOITABILITY_PATH), null);
        
        if (silentFile)
            {
            statisticslog = Output.NO_LOGS;
            }
        else if (statisticsFile!=null)
            {
            try
                {
                statisticslog = state.output.addLog(statisticsFile, !compress, compress);
                }
            catch (IOException i)
                {
                state.output.fatal("An IOException occurred while trying to create the log " + statisticsFile + ":\n" + i);
                }
            }
        else state.output.warning("No statistics file specified, printing to stdout at end.", base.push(P_STATISTICS_FILE));
        }

    public void preEvaluationStatistics(final EvolutionState state) {
    	super.preEvaluationStatistics(state);
    	
    	evalStartTime = java.lang.System.currentTimeMillis();
    	
    }
    
    public void postInitializationStatistics(final EvolutionState state)
        {
        super.postInitializationStatistics(state);
        
        // set up our best_of_run array -- can't do this in setup, because
        // we don't know if the number of subpopulations has been determined yet
        best_of_run = new Individual[state.population.subpops.size()];
        }

    /** Allows MultiObjectiveStatistics etc. to call super.super.postEvaluationStatistics(...) without
        calling super.postEvaluationStatistics(...) */
    protected void bypassPostEvaluationStatistics(EvolutionState state)
        { super.postEvaluationStatistics(state); }

    /** Logs the best individual of the generation. */
    boolean warned = false;
    public void postEvaluationStatistics(final EvolutionState state)
        {
        super.postEvaluationStatistics(state);
        
        long evalTime = java.lang.System.currentTimeMillis() - evalStartTime;
        
        // for now we just print the best fitness per subpopulation.
        Individual[] best_i = new Individual[state.population.subpops.size()];  // quiets compiler complaints
        for(int x = 0; x< state.population.subpops.size(); x++)
            {
            best_i[x] = state.population.subpops.get(x).individuals.get(0);
            for(int y = 1; y< state.population.subpops.get(x).individuals.size(); y++)
                {
                if (state.population.subpops.get(x).individuals.get(y) == null)
                    {
                    if (!warned)
                        {
                        state.output.warnOnce("Null individuals found in subpopulation");
                        warned = true;  // we do this rather than relying on warnOnce because it is much faster in a tight loop
                        }
                    }
                else if (best_i[x] == null || state.population.subpops.get(x).individuals.get(y).fitness.betterThan(best_i[x].fitness))
                    best_i[x] = state.population.subpops.get(x).individuals.get(y);
                if (best_i[x] == null)
                    {
                    if (!warned)
                        {
                        state.output.warnOnce("Null individuals found in subpopulation");
                        warned = true;  // we do this rather than relying on warnOnce because it is much faster in a tight loop
                        }
                    }
                }
        
            // now test to see if it's the new best_of_run
            if (best_of_run[x]==null || best_i[x].fitness.betterThan(best_of_run[x].fitness))
                best_of_run[x] = (Individual)(best_i[x].clone());
            }
        
        // print the best-of-generation individual
        if (doGeneration) state.output.println("\nGeneration: " + state.generation,statisticslog);
        if (doGeneration) state.output.println("Best Individual:",statisticslog);
        for(int x = 0; x< state.population.subpops.size(); x++)
            {
            if (doGeneration) state.output.println("Subpopulation " + x + ":",statisticslog);
            if (doGeneration) best_i[x].printIndividualForHumans(state,statisticslog);
            if (doMessage && !silentPrint) state.output.message("Subpop " + x + " best fitness of generation" + 
                (best_i[x].evaluated ? " " : " (evaluated flag not set): ") +
                best_i[x].fitness.fitnessToStringForHumans());
                
            // describe the winner if there is a description
            if (doGeneration && doPerGenerationDescription) 
                {
                if (state.evaluator.p_problem instanceof SimpleProblemForm)
                    ((SimpleProblemForm)(state.evaluator.p_problem.clone())).describe(state, best_i[x], x, 0, statisticslog);   
                }
            
            cumulativeTime += evalTime;
            
        	// get the exploitability by calling python
            // fitness,exploitability
            if (x == 0) {
            	long exploitStartTime = java.lang.System.currentTimeMillis();
            	double exploitability = getExploitability(best_i[x], state);
            	double exploitabilityData[] = getPopAvgExploit(state);
            	double exploitabilityAvg = exploitabilityData[0];
            	double analyzed = exploitabilityData[1];
            	long exploitTime = java.lang.System.currentTimeMillis() - exploitStartTime;
            	
//            	cumulativeTime -= exploitTime;
            	
            	state.output.println(""+best_i[x].fitness.fitness()+","+ exploitability + ","+exploitabilityAvg+","+analyzed+","+cumulativeTime,0);
            }
            
            }
        
        }

    public static double getExploitability(Individual individual, EvolutionState state) {
		double ans = 1;
		
		String indStringRep = MatchupTester.indToString(individual, state);
		//"/home/ckinneer/PycharmProjects/bullseye/exploitability.py"
		ProcessBuilder processBuilder = new ProcessBuilder("python2", exploitPath, indStringRep);
		processBuilder.redirectErrorStream(false);
		
		try {
			Process process = processBuilder.start();
			InputStream is = process.getInputStream();
			
			if (!process.waitFor(1, TimeUnit.SECONDS)) {
				process.destroyForcibly();
				return 1;
			}
			
			InputStreamReader isr = new InputStreamReader(is,
                    StandardCharsets.UTF_8);
			
			BufferedReader br = new BufferedReader(isr);

			String line = br.readLine();
			
	        ans = Double.parseDouble(line);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ans;
	}
    
    private double[] getPopAvgExploit(EvolutionState state) {
    	double sum = 0;
    	double processed = 0;
    	
    	double numInds = state.population.subpops.get(0).individuals.size();
    	
    	for (int i = 0; i < numInds; i++) {
    		double result = getExploitability(state.population.subpops.get(0).individuals.get(i), state);
//    		java.lang.System.out.print(","+result);
    		if (result <= 0) {
    			sum += result;
    			processed++;
//    			java.lang.System.out.println("proced");
    		}else{
//    			java.lang.System.out.println("skiped");
    		}
    	}
//    	java.lang.System.out.println();
    	return new double[] {sum/processed, processed/numInds};
    }

	/** Allows MultiObjectiveStatistics etc. to call super.super.finalStatistics(...) without
        calling super.finalStatistics(...) */
    protected void bypassFinalStatistics(EvolutionState state, int result)
        { super.finalStatistics(state, result); }

    /** Logs the best individual of the run. */
    public void finalStatistics(final EvolutionState state, final int result)
        {
        super.finalStatistics(state,result);
        
        // for now we just print the best fitness 
        
        if (doFinal) state.output.println("\nBest Individual of Run:",statisticslog);
        for(int x = 0; x< state.population.subpops.size(); x++ )
            {
            if (doFinal) state.output.println("Subpopulation " + x + ":",statisticslog);
            if (doFinal) best_of_run[x].printIndividualForHumans(state,statisticslog);
            if (doMessage && !silentPrint) state.output.message("Subpop " + x + " best fitness of run: " + best_of_run[x].fitness.fitnessToStringForHumans());
            
            // finally describe the winner if there is a description
            if (doFinal && doDescription) 
                if (state.evaluator.p_problem instanceof SimpleProblemForm)
                    ((SimpleProblemForm)(state.evaluator.p_problem.clone())).describe(state, best_of_run[x], x, 0, statisticslog);      
            }
        }
    }
