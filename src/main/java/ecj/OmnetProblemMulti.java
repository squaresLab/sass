package ecj;

import ec.EvolutionState;
import ec.Individual;
import ec.gp.GPIndividual;
import ec.gp.GPProblem;
import ec.multiobjective.MultiObjectiveFitness;
import ec.simple.SimpleProblemForm;
import ec.util.Parameter;
import generalomnet.Omnet;
import generalomnet.tactics.OmnetPlan;

public class OmnetProblemMulti extends GPProblem implements SimpleProblemForm {
	
		public final static String objectivesString  = "objectives";

		private final int INVALID_ACTION_PENALTY = 30;
		private final int VERBOSENESS_PENALTY = 20;
		
		String paramValue;

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
				// get objective values
				Parameter objectivesParam = new Parameter(objectivesString);
		        	paramValue = null;
		        if (state.parameters.exists(objectivesParam, null))
		        {
		            paramValue = state.parameters.getStringWithDefault(objectivesParam, null, "paqtcrfdil");
		        } else {
		        	System.err.println("please define the objectives parameter to list what the plans should be evaluated on");
		        	System.exit(1);
		        }

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

				/*
				 * 
				 This is all for the single objective case
				KozaFitness f = ((KozaFitness)ind.fitness);
			
				((StateData)input).initializeState();
				((GPIndividual)ind).trees[0].child.eval(state, threadnum, input, stack, ((GPIndividual)ind), this);
			
				double fitnessValue = ((StateData)input).plan.evaluate(new Omnet());
				
				//take off a penalty for invalid actions
				fitnessValue -= ((StateData)input).plan.invalidActions * INVALID_ACTION_PENALTY;
				//take off a penalty for plan length
				fitnessValue -= ((GPIndividual)ind).size() * VERBOSENESS_PENALTY;
				
				
				
				if (fitnessValue <= 0){
					fitnessValue = Double.MAX_VALUE;
				}else{
					fitnessValue = 1 / fitnessValue;
				}
				
				System.out.println("Score: "+((GPIndividual)ind).size()+" "+1.0/fitnessValue + " " + ((StateData)input).plan);
				
				/*
				System.out.println("current individual:");
				((GPIndividual)ind).printTrees(state, 0);
				System.out.println("Score: "+fitnessValue);
				/*
				
				
				
				f.setStandardizedFitness(state, fitnessValue);
				
				f.hits = 0 ;
				
				*/
				
				// begin code for multi objective				
				
				double[] objectives;
				
				
				
				((StateData)input).initializeState();
				
				((GPIndividual)ind).trees[0].child.eval(state, threadnum, input, stack, ((GPIndividual)ind), this);
				
				
				if (((StateData)input).plan.size() < 10){
				
					objectives = getObjectives(paramValue,(StateData)input,(GPIndividual)ind);
				
				}else{
					objectives = getNonsense(paramValue.length());
				}
				
				
				((MultiObjectiveFitness)ind.fitness).setObjectives(state, objectives);
				
				//System.out.println(((StateData)input).plan);
				
				
				ind.evaluated=true;
			} 
		}

		/*
		#t - time to complete plan
		#n - number of servers active at the end of the plan
		#c - cost per second at the end of the plan
		#r - responses handled by the system per second at the end of the plan
		#d - dimmed responses handled at the end of the plan
		#f - non-dimmed responses handled at the end of the plan
		#i - gross income of the system configuration at the end of the plan
		#p - profit
		#s - penalizing same trees on both sides of an if then (meaning if 
		 	the action succeeds or fails still do the same thing) this is 
		 	the number that occur
		#l - minimize plan length
		#example objective string: rql - evaluate based on response time, quality, and plan length 
		 
		objectives=tcrfdipl
		1      2     3       4    5      6     7       8
		time,cost,respones,full,dimmed,income,profit,length
		*/
		
		private double[] getNonsense(int size){
			double[] ans = new double[size];
			for (int count = 0; count < size; count++){
				ans[count] = Double.NEGATIVE_INFINITY;
			}
			return ans;
		}
		
		private double[] getObjectives(String objString, StateData input,GPIndividual ind) {
			
			OmnetPlan plan = input.plan;
			
			plan.evaluate(new Omnet());
			
			double[] ans = new double[objString.length()];
			
			for (int count = 0; count < objString.length(); count++){
				char cur = objString.charAt(count);
				
				 switch(cur){
				 case 't' : ans[count] = plan.getTime(); break;
				 case 'c' : ans[count] = plan.getCost(); break;
				 case 'r' : ans[count] = plan.getResponses(); break;
				 case 'd' : ans[count] = plan.getDimmedResponses(); break;
				 case 'f' : ans[count] = plan.getNormalResponses(); break;
				 case 'i' : ans[count] = plan.getIncome(); break;
				 case 'p' : ans[count] = plan.getProfit(); break;
				 case 'l' : ans[count] = plan.size(); break;
				 case 'q' : ans[count] = plan.getNormalResponses() / plan.getResponses(); break;
				 case 'a' : ans[count] = plan.getLatency(); break;
				}
				
			}
	
			return ans;
		}


	}
