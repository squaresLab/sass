package ecj;

import java.util.ArrayList;

import bullseye.attackerTypes.Intelligence;
import bullseye.tactics.attacker.*;
import bullseye.tactics.defender.*;
import ec.*;
import ec.coevolve.*;
import ec.gp.GPIndividual;
import ec.simple.SimpleFitness;
import ec.vector.IntegerVectorIndividual;

public class BullseyeProblem extends Problem implements GroupedProblemForm{

	
//	DefenderTactic[] defenderTactics = new DefenderTactic[] {new ChangePasswordPayment(),new ChangePasswordWeb(), new EnableCamoflauge(), new ReimagePayment(), new ReimagePOS(), new ReimageWeb(),new ThrottleConnection(), new Wait()};
	// len 8
//	AttackerTactic[] attackerTactics = new AttackerTactic[] {new CrackWebHashes(), new DisruptPayment(), new DisruptPOS(), new DisruptWeb(), new ExfilData(), new InfectPos(), new KeylogPayment(), new KeylogWeb(), new PhishVendor(), new ZeroDayPayment(), new ZeroDayWeb()};
	// len 11
	
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

	
	private double[] evalFitness(GPIndividual defender, GPIndividual attacker) {
		return bullseye.System.evaluate(defender, attacker, new Intelligence());
//		return new double[] {0,0};
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

            if( ! ( ind[0] instanceof GPIndividual ) )
                state.output.fatal( "The individuals in the BullseyeProblem should be GPIndividual." );

            if( ! ( ind[1] instanceof GPIndividual ) )
                state.output.fatal( "The individuals in the BullseyeProblem should be GPIndividual." );
            
            ((GPIndividual) ind[0]).trees[0].child.eval(state, threadnum, null, null, (GPIndividual) ind[0], this);
            ((GPIndividual) ind[1]).trees[0].child.eval(state, threadnum, null, null, (GPIndividual) ind[1], this);
            
//            System.out.println(((GPIndividual) ind[1]).trees[0].child);
            // TODO the actual simulation and util calc here
            
            double util[] = evalFitness(((GPIndividual) ind[0]), ((GPIndividual) ind[1]));
            //double util[] = {0,0};     
            double score0 = util[0];
            double score1 = util[1];
            System.out.println(score0);
            System.out.println(score1);
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
