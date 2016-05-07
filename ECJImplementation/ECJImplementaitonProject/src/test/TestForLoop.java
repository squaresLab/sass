package test;

import actions.operators.ForOperator;
import actions.operators.SequenceOperator;
import ec.EvolutionState;
import ec.Evolve;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;
import ec.gp.GPTree;
import ec.util.ParameterDatabase;
import main.OmnetStateData;
import main.SingleObjectiveProblem;
import omnet.tactics.StartNewServerA;
import znn.tactics.DecreaseDatabaseBThreads;
import znn.tactics.DecreaseQuality;

public class TestForLoop {
	public static void main(String[] args){
			OmnetStateData sd = new OmnetStateData();
			sd.initializeState();
			GPIndividual ind = new GPIndividual();
			GPTree[] treeInit = {new GPTree()};
			ind.trees = treeInit;
			GPNode root = new ForOperator();
			((ForOperator)root).setForCount(10);
			//TODO - set up the tree correctly and then check if the eval
			//method works correctly
			GPNode secondSequenceOperator = new SequenceOperator();
			GPNode[] childs = {new StartNewServerA()};
			root.children = childs;
			ind.trees[0].child = root;
			GPNode[] childs2  = {new DecreaseDatabaseBThreads(), new DecreaseQuality()};
			secondSequenceOperator.children=childs2;
			Evolve ev = new Evolve();
			String[] inputFile = {"-file","selfadaptivesystemsingleobjective.params"};
			ParameterDatabase params = ev.loadParameterDatabase(inputFile);
			EvolutionState state = ev.initialize(params,0);
			((GPIndividual)ind).trees[0].child.eval(state, 0, (GPData)sd, new ADFStack(), 
					((GPIndividual)ind), new SingleObjectiveProblem());
			System.out.println("Final Result: "+sd.singleObjectiveScore());
			//TODO:Go back and make the test pass for different combinations of successes and failures
			//At the moment I'm assuming all the actions succeed.  If any fail than the test
			//will fail
			//assert(Math.abs(sd.calculateStateScore()-1596.8)<0.1);
			
	}
	
	
}
