package ecj;

import ec.EvolutionState;
import ec.Individual;
import ec.Population;
import ec.gp.GPIndividual;
import ec.gp.GPProblem;
import ec.gp.koza.KozaFitness;
import ec.simple.SimpleEvaluator;
import ec.simple.SimpleProblemForm;
import ec.util.Parameter;
import omnet.FitnessSanity;
import omnet.Omnet;
import omnet.Omnet.Scenario;
import system.Fitness;
import system.Simulator;

public class OmnetProblemSingle extends GPProblem implements SimpleProblemForm {

		private double INVALID_ACTION_PENALTY = 0;
		private double VERBOSENESS_PENALTY = 0;
		private double minAcceptedImprovement = 0;
		private double killratio;
		
		private Scenario scenario;

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
				
				INVALID_ACTION_PENALTY = state.parameters.getDoubleWithDefault(new Parameter("invalid_action_penalty"),null,0);
				VERBOSENESS_PENALTY = state.parameters.getDoubleWithDefault(new Parameter("verboseness_penalty"),null,0);
				minAcceptedImprovement = state.parameters.getDoubleWithDefault(new Parameter("min_accepted_improvement"),null,0);
				String scenarioName = state.parameters.getStringWithDefault(new Parameter("scenario_name"), null,"normal");
				
				killratio = state.parameters.getDouble(new Parameter("runtime_kill_ratio"), null);
				
				scenario = Scenario.fromString(scenarioName);
				
			}
		}

		/*This is the method to evaluate the fitness of a plan
		 * TODO - check this is in the correct final form*/
		@Override
		public void evaluate(EvolutionState state, Individual ind, int subpopulation, int threadnum) {
			
			if (moreThanHalf(state)){
				Simulator.kill(true);
			}else{
				Simulator.kill(false);
			}
			
			// DEBUG ONLY
			///ind = MutationBuilder.ind;
			// END DEBUG
			
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
				
				
				((StateData)input).plan.setMinAcceptedImprovment(minAcceptedImprovement);
				
				// NEXT LINE DEBUG ONLY
				//FitnessSanity.testInd(((StateData)input).plan);
				
				double fitnessValue;
				
				if(((StateData)input).plan.size() <= 20){
					
					Fitness fitness = ((StateData)input).plan.evaluate(new Omnet(scenario));
					
					if (fitness == null){
						
						fitnessValue = 0;
						
					}else{

						fitnessValue = fitness.get("Profit");
					}					

				}else{
					fitnessValue = 0;
				}
			
				//take off a penalty for invalid actions
				fitnessValue -= ((StateData)input).plan.invalidActions * INVALID_ACTION_PENALTY;
				//take off a penalty for plan length
				fitnessValue -= ((StateData)input).plan.size() * VERBOSENESS_PENALTY;
				// and tree size
				//fitnessValue -= ((GPIndividual)ind).size() * VERBOSENESS_PENALTY;
				
				
				
				if (fitnessValue <= 0){
					fitnessValue = Double.MAX_VALUE;
				}else{
					fitnessValue = 1 / fitnessValue;
				}
				
				if (fitnessValue == 0){
					System.out.println("wtf?");
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

		private boolean moreThanHalf(EvolutionState state) {
			
			if (state == null){
				return false;
			}
			
			Population p = state.population;
			
			if (p != null && p.subpops.get(0) != null && p.subpops.get(0).individuals != null){
			
			int popsize = p.subpops.get(0).individuals.size();
			
			int done = 0;
			for (int i = 0; i < popsize; i++){
				
				Individual ind = p.subpops.get(0).individuals.get(i);
				
				if (ind != null && p.subpops.get(0).individuals.get(i).evaluated){
					done++;
				}
				
				if (done > popsize * killratio)
					return true;
			}
			
			return false;
			
			}else{
				return false;
			}
		}


	}
