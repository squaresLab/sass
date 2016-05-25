package main.java.main;

import ec.EvolutionState;
import ec.Individual;
import ec.gp.GPIndividual;
import ec.gp.GPNode;
import ec.gp.GPProblem;
import ec.gp.GPTree;
import ec.multiobjective.MultiObjectiveFitness;
import ec.simple.SimpleProblemForm;
import ec.util.Parameter;
import main.java.actions.operators.IfThenElseOperator;

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
	
	
	/*This is the method to evaluate the fitness of a plan */
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
			int timeTotal=0;
			int serverCount = 0;
			double costTotal=0;
			double requestsHandledPerSecondTotal=0;
			double grossIncomeTotal = 0;
			double profitTotal = 0;
			boolean allPathsFeasible=true;
			//((GPIndividual)ind).printTrees(state, 0);
			for(int i = 0; i < timesToRun; i++){
				//not sure why I need this but some times the statedata is not reset
				((OmnetStateData)input).initializeState();
				((GPIndividual)ind).trees[0].child.eval(state, threadnum, input, stack, 
						((GPIndividual)ind), this);
				//TODO: determine if you need to adjust the returned range
				timeTotal+=((OmnetStateData)input).getTotalPlanTime();
				costTotal+=((OmnetStateData)input).getPlanCost();
	            requestsHandledPerSecondTotal+=((OmnetStateData)input).getPlanRequestsHandledPerSecond();
	            grossIncomeTotal+=((OmnetStateData)input).getPlanGrossIncome();
	            if(!((OmnetStateData)input).isPlanValid()){
	            	
					feasible=false;
					break;
				}
				//((StateData)input).initializeData();
			}
			profitTotal = grossIncomeTotal-costTotal;
			if(feasible){
				//this isn't clean but I can't think of a better way to do this in java at the moment
				//maybe ask claire if there is a way to do this functionally
				int stringIndex = 0; 
				int paramCount = 0;
				while(stringIndex != paramValue.length()){
					String param = paramValue.substring(stringIndex, stringIndex+1);
					stringIndex++;
					if(param.equals("t")){
						objectives[paramCount]=((double)timeTotal)/timesToRun;
					} else if (param.equals("n")){
						objectives[paramCount]=((double)serverCount)/timesToRun;
					} else if (param.equals("c"))  {
						objectives[paramCount]=costTotal/timesToRun;
					} else if (param.equals("r")) {
						objectives[paramCount]=requestsHandledPerSecondTotal/timesToRun;
					} else if (param.equals("i")) {
						objectives[paramCount]=grossIncomeTotal/timesToRun;
					} else if (param.equals("p")){
						objectives[paramCount]=profitTotal/timesToRun;
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
		//TODO: decide if this is the correct way to handle for loops
		//currently I am counting only the number of nodes in the plan
		//and not the actual number of steps; this creates an advantage
		//to use for loops in the plan instead of repeating nodes.

		//if(node instanceof ForOperator){
		//	result += recursivelyCalculateTreeLength(node.children[0]) * ((ForOperator)node).getForCount();
		//} else {
		  for(int i = 0; i < node.children.length; i++){
		  	result += recursivelyCalculateTreeLength(node.children[i]);
		  }
		//}
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
