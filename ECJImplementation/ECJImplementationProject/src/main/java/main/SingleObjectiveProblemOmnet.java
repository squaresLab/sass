package main.java.main;

import ec.EvolutionState;
import ec.Individual;
import ec.gp.GPIndividual;
import ec.gp.GPNode;
import ec.gp.GPProblem;
import ec.gp.GPTree;
import ec.gp.koza.KozaFitness;
import ec.simple.SimpleProblemForm;
import ec.util.Parameter;
import main.java.actions.AddServer;
import main.java.actions.operators.IfThenElseOperator;
import main.java.znn.tactics.IncreaseDatabaseAThreads;

public class SingleObjectiveProblemOmnet extends GPProblem implements SimpleProblemForm {

	

	public void setup(final EvolutionState state, final Parameter base){
		super.setup(state, base);
		if (!(input instanceof OmnetStateData)){
			state.output.fatal("GPData class must subclass from " + OmnetStateData.class
					+" instead given "+ input.getClass().toString(), base.push("data"),null);
		}
		else{
			//set up the initial state
			OmnetStateData sd = (OmnetStateData)(input);
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
			boolean joannaTest = true;
			if(joannaTest) {
				double fitnessValue = ((OmnetStateData) input).countPossibleStates((GPIndividual)ind);
				
			} else {
			//evaluate the fitness later
			KozaFitness f = ((KozaFitness)ind.fitness);
			//currently there is only ever one tree in trees
			//long fitnessValue = Math.abs(countAddServer(((GPIndividual)ind).trees[0])-5);
			//boolean allPathsFeasible= allPathsFeasible(state,ind,threadnum);
			((OmnetStateData)input).initializeState();
			((GPIndividual)ind).trees[0].child.eval(state, threadnum, input, stack, 
					((GPIndividual)ind), this);
			//TODO: determine if you need to adjust the returned range
			double fitnessValue = ((OmnetStateData)input).getSingleObjectiveScore();
			if(fitnessValue==0){
				System.out.println("debugging");
				System.out.println("current individual:");
				((GPIndividual)ind).printTrees(state, 0);
				System.out.println("Score: "+((OmnetStateData)input).getSingleObjectiveScore());
				((OmnetStateData)input).initializeState();
				((GPIndividual)ind).trees[0].child.eval(state, threadnum, input, stack, 
						((GPIndividual)ind), this);
			}
			/*if(fitnessValue<6500){
				System.out.println("current individual:");
				((GPIndividual)ind).printTrees(state, 0);
				((GPIndividual)ind).trees[0].child.eval(state, threadnum, input, stack, 
						((GPIndividual)ind), this);
				System.out.println("Number of databaseAThreads: "+((StateData)input).getDatabaseAThreadsCount());
				System.out.println("Max databastAThreads: "+((StateData)input).getMaxDatabaseAThreadsCount());
				System.out.println("Single evaluation score: "+((StateData)input).calculateStateScore());
				System.out.println("Final evaluation score: "+fitnessValue);
				System.out.println("Stopping for debugging");
				((StateData)input).initializeData();
				((GPIndividual)ind).trees[0].child.eval(state, threadnum, input, stack, 
						((GPIndividual)ind), this);
				((StateData)input).initializeData();
			}*/
			//System.out.println("Fitness Value: "+fitnessValue);
			//((GPIndividual)ind).printTrees(state,1);
			//System.out.println("");
			f.setStandardizedFitness(state, fitnessValue);
			f.hits = ((OmnetStateData)input).invalidActionCount;
			ind.evaluated=true;

			/*((GPIndividual)ind).trees[0].child.eval(
                    state,threadnum,input,stack,((GPIndividual)ind),this);

             result = Math.abs(expectedResult - input.x);*/
		}
	} 
	}

	private int countAddServer(GPTree tree){
		GPNode rootNode = tree.child;
		return checkChildForAddServer(rootNode);
	}

	private int checkChildForAddServer(GPNode node){
		int result=0;
		if(node instanceof AddServer){
			result=1;
		}
		for(int i=0; i<node.children.length;i++){
			result+= checkChildForAddServer(node.children[i]);
		}
		return result;
	}

	private int countIncreaseDatabaseAThreads(GPTree tree){
		GPNode rootNode = tree.child;
		return checkChildForIncreaseDatabaseAThreads(rootNode);
	}

	private int checkChildForIncreaseDatabaseAThreads(GPNode node){
		int result=0;
		if(node instanceof IncreaseDatabaseAThreads){
			result=1;
		}
		for(int i=0; i<node.children.length;i++){
			result+= checkChildForIncreaseDatabaseAThreads(node.children[i]);
		}
		return result;
	}

	private int countIfThenElseOperators(GPTree tree){
		GPNode rootNode = tree.child;
		return checkChildForIfThenElseOperators(rootNode);
	}

	private int checkChildForIfThenElseOperators(GPNode node){
		int result=0;
		if(node instanceof IfThenElseOperator){
			result=1;
		}
		for(int i=0; i<node.children.length;i++){
			result+= checkChildForIfThenElseOperators(node.children[i]);
		}
		return result;
	}

	private boolean allPathsFeasible(EvolutionState state, Individual ind, int threadnum) {
		/*int ifTheElseCount = countIfThenElseOperators(((GPIndividual)ind).trees[0]);
	   try{
	     int[][] branchChoices = generateBranchChoices(ifTheElseCount);
	     //need to fill the rest in later - use the EvaluateFitness method from your old code
	     return true;
	   } catch(TooManyBranchesException e){
		   return false;
	   }*/
		return true;

	}

	//For some reason this really slows down the code - try to profile why later
	private int[][] generateBranchChoices(int numberOfBranches)
			throws TooManyBranchesException {
		int numberOfCombinations = 1;
		if (numberOfBranches > 20) {
			throw new TooManyBranchesException(numberOfBranches);
		}
		for (int i = 0; i < numberOfBranches; i++) {
			numberOfCombinations *= 2;
		}
		//System.out.println("number of combinations: " + numberOfCombinations);
		int[][] branchCombinations = new int[numberOfCombinations][numberOfBranches];
		for (int i = numberOfBranches - 1; i > -1; i--) {
			int amountBeforeSwitching = 1;
			for (int k = 0; k < i; k++) {
				amountBeforeSwitching *= 2;
			}
			int currentColumn = numberOfBranches - i - 1;
			int branchChoice = 1; // 1 means going left, 2 means going right. 0 is
			// only taken for going left in if-success
			// added for debugging
			if (amountBeforeSwitching == 0) {
				//System.out.println("number of branches: " + numberOfBranches);
				//System.out.println("i value: " + i);
				amountBeforeSwitching = 1;
				//System.out.print(i + ", ");
				for (int k = 0; k < i; k++) {
					amountBeforeSwitching *= 2;
					//System.out.print(amountBeforeSwitching + ", ");
				}
				//System.out.print("\n");
			}
			for (int l = 0; l < (numberOfCombinations / amountBeforeSwitching); l++) {
				for (int j = 0; j < amountBeforeSwitching; j++) {
					branchCombinations[j + l * amountBeforeSwitching][currentColumn] = branchChoice;
				}
				if (branchChoice == 1) {
					branchChoice = 2;
				} else {
					branchChoice = 1;
				}
			}
		}

		return branchCombinations;
	}
	

	
	
	
}
