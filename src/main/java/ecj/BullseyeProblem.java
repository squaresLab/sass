package ecj;

import java.util.ArrayList;

import ec.*;
import ec.coevolve.*;
import ec.simple.SimpleFitness;
import ec.vector.IntegerVectorIndividual;

public class BullseyeProblem extends Problem implements GroupedProblemForm{

	@Override
	public void preprocessPopulation(EvolutionState state, Population pop, boolean[] prepareForAssessment, boolean countVictoriesOnly) {
		for (int i = 0; i < pop.subpops.size(); i++) {
			if (prepareForAssessment[i]) {
				for (int j = 0; j < pop.subpops.get(i).individuals.size(); j++) {
					SimpleFitness fit = (SimpleFitness) (pop.subpops.get(i).individuals.get(j).fitness);
					fit.trials = new ArrayList();
				}
			}
		}
		
	}

	@Override
    public void evaluate(final EvolutionState state,
            final Individual[] ind,  // the individuals to evaluate together
            final boolean[] updateFitness,  // should this individuals' fitness be updated?
            final boolean countVictoriesOnly,
            int[] subpops,
            final int threadnum)
            {
            if( ind.length != 2 || updateFitness.length != 2 )
                state.output.fatal( "The InternalSumProblem evaluates only two individuals at a time." );

            if( ! ( ind[0] instanceof IntegerVectorIndividual ) )
                state.output.fatal( "The individuals in the BullseyeProblem should be IntegerVectorIndividual." );

            if( ! ( ind[1] instanceof IntegerVectorIndividual ) )
                state.output.fatal( "The individuals in the BullseyeProblem should be IntegerVectorIndividual." );

            // TODO the actual simulation and util calc here
            double score0 = 0;
            double score1 = 0;

            if( updateFitness[0] )
                {
                SimpleFitness fit = ((SimpleFitness)(ind[0].fitness));
                fit.trials.add(new Double(score0));
                            
                // set the fitness because if we're doing Single Elimination Tournament, the tournament
                // needs to know who won this time around.  Don't bother declaring the ideal here.
                fit.setFitness(state, score0, false);
                }

            if( updateFitness[1] )
                {
                SimpleFitness fit = ((SimpleFitness)(ind[1].fitness));
                fit.trials.add(new Double(score1));

                // set the fitness because if we're doing Single Elimination Tournament, the tournament
                // needs to know who won this time around.
                fit.setFitness(state, score1, false);
                }
            }
	
	@Override
	public int postprocessPopulation(EvolutionState state, Population pop, boolean[] updateFitness, boolean countVictoriesOnly) {
		  int total = 0;
	        for(int i = 0; i < pop.subpops.size(); i++ )
	            if (updateFitness[i])
	                for(int j = 0; j < pop.subpops.get(i).individuals.size() ; j++ )
	                    {
	                    SimpleFitness fit = ((SimpleFitness)(pop.subpops.get(i).individuals.get(j).fitness));

	                    // average of the trials we got
	                    int len = fit.trials.size();
	                    double sum = 0;
	                    for(int l = 0; l < len; l++)
	                        sum += ((Double)(fit.trials.get(l))).doubleValue();
	                    sum /= len;
	                                                                        
	                    // we'll not bother declaring the ideal
	                    fit.setFitness(state, sum, false);
	                    pop.subpops.get(i).individuals.get(j).evaluated = true;
	                    total++;
	                    }
	        return total;
	}


}
