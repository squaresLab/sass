package ecj;

import ec.EvolutionState;
import ec.Individual;
import ec.gp.GPIndividual;
import ec.gp.GPProblem;
import ec.gp.koza.KozaFitness;
import ec.simple.SimpleProblemForm;
import ec.util.Parameter;
import omnet.Omnet;

public class OmnetProblemSingle extends GPProblem implements SimpleProblemForm {

		private final double INVALID_ACTION_PENALTY = 2;
		private final double VERBOSENESS_PENALTY = .5;

		public void setup(final EvolutionState state, final Parameter base){
			super.setup(state, base);
			if (!(input instanceof StateData)){
				state.output.fatal("GPData class must subclass from " + StateData.class
						+" instead given "+ input.getClass().toString(), base.push("data"),null);
			}
			else{
				//set up the initial state
				StateData sd = (StateData)(input);
				sd.initializeState();
				//TODO set up the initial server setting in each location

			}
		}

		/*This is the method to evaluate the fitness of a plan
		 * TODO - check this is in the correct final form*/
		@Override
		public void evaluate(EvolutionState state, Individual ind, int subpopulation, int threadnum) {
			// TODO Auto-generated method stub
			boolean feasible=true;
			if(!ind.evaluated) //don't reevaluate it again
			{
				/*if(countIncreaseDatabaseAThreads(((GPIndividual)ind).trees[0])>3){
					System.out.println("Stopping for debugging");
					System.out.println("current individual:");
					((GPIndividual)ind).printTrees(state, 0);
				}*/
				//System.out.println(ind.genotypeToStringForHumans());
				//TODO figure out the log parameter later
				//
				//boolean joannaTest = true;
				//			if(joannaTest) {
				//				double fitnessValue = ((OmnetStateData) input).countPossibleStates((GPIndividual)ind);
				//				
				//			} else {
				//evaluate the fitness later
				KozaFitness f = ((KozaFitness)ind.fitness);
				//currently there is only ever one tree in trees
				//long fitnessValue = Math.abs(countAddServer(((GPIndividual)ind).trees[0])-5);
				//boolean allPathsFeasible= allPathsFeasible(state,ind,threadnum);
				((StateData)input).initializeState();
				((GPIndividual)ind).trees[0].child.eval(state, threadnum, input, stack, ((GPIndividual)ind), this);
				//TODO: determine if you need to adjust the returned range
				//double fitnessValue = ((OmnetStateData)input).getSingleObjectiveScore();
//				System.err.println("DO I EVER MAKE IT here?");
//				System.exit(0);
				
				
				double fitnessValue;
				
				if(((StateData)input).plan.size() < 40){
					fitnessValue = ((StateData)input).plan.evaluate(new Omnet());
				}else{
					fitnessValue = 0;
				}
				
				//take off a penalty for invalid actions
				fitnessValue -= ((StateData)input).plan.invalidActions * INVALID_ACTION_PENALTY;
				//take off a penalty for plan length
				fitnessValue -= ((StateData)input).plan.size() * VERBOSENESS_PENALTY;
				// and tree size
				fitnessValue -= ((GPIndividual)ind).size() * VERBOSENESS_PENALTY * 2;
				
				
				
				if (fitnessValue <= 0){
					fitnessValue = Double.MAX_VALUE;
				}else{
					fitnessValue = 1 / fitnessValue;
				}
				
				//System.out.println("Score: "+((GPIndividual)ind).size()+" "+1.0/fitnessValue + " " + ((StateData)input).plan);
				
				/*
				System.out.println("current individual:");
				((GPIndividual)ind).printTrees(state, 0);
				System.out.println("Score: "+fitnessValue);
				/*
				
				*/
				
				f.setStandardizedFitness(state, fitnessValue);
				
				f.hits = 0 ;
				
				ind.evaluated=true;
			} 
		}


	}
