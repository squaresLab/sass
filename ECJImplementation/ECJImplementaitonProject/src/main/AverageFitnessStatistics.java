package main;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import ec.EvolutionState;
import ec.Individual;
import ec.gp.koza.KozaFitness;
import ec.simple.SimpleProblemForm;
import ec.simple.SimpleStatistics;

public class AverageFitnessStatistics extends SimpleStatistics {

	
	boolean warned = false;
	@Override
	/** Logs the best individual of the generation and the average fitness 
	 *  of the population */
    
    public void postEvaluationStatistics(final EvolutionState state)
        {
        //super.postEvaluationStatistics(state);
        
        // for now we just print the best fitness per subpopulation.
        Individual[] best_i = new Individual[state.population.subpops.length];  // quiets compiler complaints
        double fitnessSum=0;
        int individualCount=0;
        for(int x=0;x<state.population.subpops.length;x++){
            best_i[x] = state.population.subpops[x].individuals[0];
            fitnessSum+=((KozaFitness)best_i[x].fitness).standardizedFitness();
            individualCount+=state.population.subpops[x].individuals.length;
            for(int y=1;y<state.population.subpops[x].individuals.length;y++)
                {
                if (state.population.subpops[x].individuals[y] == null)
                    {
                    if (!warned)
                        {
                        state.output.warnOnce("Null individuals found in subpopulation");
                        warned = true;  // we do this rather than relying on warnOnce because it is much faster in a tight loop
                        }
                    //add the same score as an invalid plan to the fitness sum
                     fitnessSum+=SingleObjectiveProblemOmnet.INVALID_PLAN_SCORE;
                     
                }
                else {
                	fitnessSum+=((KozaFitness)state.population.subpops[x].individuals[y].fitness).standardizedFitness();
                	if (best_i[x] == null || state.population.subpops[x].individuals[y].fitness.betterThan(best_i[x].fitness)){
                
                        best_i[x] = state.population.subpops[x].individuals[y];
                	}
                }
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
        double averageFitness = fitnessSum/individualCount;
        // print the best-of-generation individual
        System.out.println("do generation: "+doGeneration);
        if (doGeneration) state.output.println("\nGeneration: " + state.generation,statisticslog);
        NumberFormat nf = new DecimalFormat("#0.00000");
        if (doGeneration) state.output.println("\nAverage Fitness: "+nf.format(averageFitness),statisticslog);
        System.out.println("Average Fitness: "+nf.format(averageFitness));
        if (doGeneration) state.output.println("Best Individual:",statisticslog);
        for(int x=0;x<state.population.subpops.length;x++)
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
            }
        }
	

	
}
