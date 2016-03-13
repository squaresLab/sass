package main;

import java.util.HashMap;
import java.util.function.Function;

import actions.IfThenElseOperator;
import ec.EvolutionState;
import ec.Individual;
import ec.gp.GPIndividual;
import ec.gp.GPNode;
import ec.gp.GPProblem;
import ec.gp.GPTree;
import ec.gp.koza.KozaFitness;
import ec.simple.SimpleProblemForm;
import ec.util.Parameter;
import ec.multiobjective.MultiObjectiveFitness;

public class MultiObjectiveProblem extends GPProblem implements SimpleProblemForm{

	public final static String objectivesString  = "objectives";
	/*public final static HashMap<String,String> paramHashMap = new HashMap<String,String>();
	static {
		paramHashMap.put("r", "response time");
		paramHashMap.put("c", "cost");
		paramHashMap.put("q", "quality");
		paramHashMap.put("t", "clock time");
		paramHashMap.put("s", "same children for if then");
		paramHashMap.put("l", "length");
		}*/
	
	
	/*This is the method to evaluate the fitness of a plan
	 * TODO - check this is in the correct final form*/
	@Override
	public void evaluate(EvolutionState state, Individual ind, int subpopulation, int threadnum) {
		// TODO Auto-generated method stub
        Parameter objectivesParam = new Parameter(objectivesString);
        String paramValue = null;
        if (state.parameters.exists(objectivesParam, null))
        {
            paramValue = state.parameters.getStringWithDefault(objectivesParam, null, "rcqtsl");
        } else {
        	System.err.println("please define the objectives parameter to list what the plans should be evaluated on");
        	System.exit(1);
        }
		boolean feasible=true;
		if(!ind.evaluated) //don't reevaluate it again
		{

			double[] objectives = ((MultiObjectiveFitness)ind.fitness).getObjectives();
			int timesToRun=50000;
			double responseTimeTotal=0;
			double costTotal=0;
			double highQualityTotal=0;
			double clockTimeTotal=0;
			boolean allPathsFeasible=true;
			//((GPIndividual)ind).printTrees(state, 0);
			for(int i = 0; i < timesToRun; i++){
				//not sure why I need this but some times the statedata is not reset
				((StateData)input).initializeData();
				((GPIndividual)ind).trees[0].child.eval(state, threadnum, input, stack, 
						((GPIndividual)ind), this);
				//TODO: determine if you need to adjust the returned range
				responseTimeTotal+=((StateData)input).getResponseTime();
				costTotal+=((StateData)input).getCost();
				boolean highQuality=((StateData)input).isHighQuality();
				if(highQuality){
					highQualityTotal+=1;
				}
				clockTimeTotal+=((StateData)input).getTime();

				if(((StateData)input).getReachedInvalidState()){
					feasible=false;
					//next two lines are for debugging purposes
					/*((StateData)input).initializeData();
					((GPIndividual)ind).trees[0].child.eval(state, threadnum, input, stack, 
							((GPIndividual)ind), this);*/
					break;
				}
				//((StateData)input).initializeData();
			}
			if(feasible){
				//this isn't clean but I can't think of a better way to do this in java at the moment
				//maybe ask claire if there is a way to do this functionally
				int stringIndex = 0; 
				int paramCount = 0;
				while(stringIndex != paramValue.length()){
					String param = paramValue.substring(stringIndex, stringIndex+1);
					stringIndex++;
					if(param.equals("r")){
						objectives[paramCount]=responseTimeTotal/timesToRun;
					} else if (param.equals("c")){
						objectives[paramCount]=costTotal/timesToRun;
					} else if (param.equals("q"))  {
						objectives[paramCount]=highQualityTotal/timesToRun;
					} else if (param.equals("t")) {
						objectives[paramCount]=clockTimeTotal/timesToRun;
					} else if (param.equals("s")){
						objectives[paramCount]=ifsWithSameChildren(((GPIndividual)ind).trees[0]);
					} else if (param.equals("l")){
						objectives[paramCount]=calculatePlanLength(((GPIndividual)ind).trees[0]);
					} else {
						System.err.println("Invalid param option for feasible plan");
						System.exit(1);
					}
					paramCount++;
				}
			} else{
				scoreInfeasiblePlan(ind, objectives, paramValue);
			}
			((MultiObjectiveFitness)ind.fitness).setObjectives(state, objectives);
			ind.evaluated = true;
		}
	}
	
	private void scoreInfeasiblePlan(Individual ind, double[] objectives, String paramValue){
		int paramCount = 0;
		int stringIndex = 0;
		while(stringIndex!=paramValue.length()){
			stringIndex++;
			//check if min or maxing param
			//then set the score as the worst score possible
			boolean isMaximizing = ((MultiObjectiveFitness)ind.fitness).isMaximizing(paramCount);
		    if(isMaximizing){
		    	objectives[paramCount] = ((MultiObjectiveFitness)ind.fitness).minObjective[paramCount];
  		    }else {
  		    	objectives[paramCount] = ((MultiObjectiveFitness)ind.fitness).maxObjective[paramCount];
		    }
			paramCount++;
		}
	}

	
	
	public static int calculatePlanLength(GPTree tree){
		GPNode rootNode = tree.child;
		return recursivelyCalculateTreeLength(rootNode);
	}
	
	private static int recursivelyCalculateTreeLength(GPNode node){
		int result = 1;
		for(int i = 0; i < node.children.length; i++){
			result += recursivelyCalculateTreeLength(node.children[i]);
		}
		return result;
	}

	public static int ifsWithSameChildren(GPTree tree){
		GPNode rootNode = tree.child;
		return checkChildForIfThenElseOperators(rootNode);
	}

	private static int checkChildForIfThenElseOperators(GPNode node){
		int result=0;
		if(node instanceof IfThenElseOperator){
			if(areSameSubTrees((IfThenElseOperator)node)){
				result=1;
			}
		}
		for(int i=0; i<node.children.length;i++){
			result+= checkChildForIfThenElseOperators(node.children[i]);
		}
		return result;
	}

	public static boolean areSameSubTrees(IfThenElseOperator ifElse){
		return areTreesEqual(ifElse.children[1],ifElse.children[2]);
	}

	public static boolean areTreesEqual(GPNode root1, GPNode root2) {
		// Shortcut for reference equality; also handles equals(null, null)
		if (root1 == root2) {
			return true;
		}
		if (root1 == null || root2 == null) {
			return false;
		}
		if (!root1.getClass().equals(root2.getClass())){
			return false;
		} else if (root1.children.length != root2.children.length){
			return false;
		} else {
			for(int i = 0; i < root1.children.length; i++){
				if(!(areTreesEqual(root1.children[i], root2.children[i]))){
					return false;
				}
			}
			return true;
		}
	} 

}
