package tests;

import ec.gp.GPIndividual;
import ec.gp.GPNode;
import ec.gp.GPTree;
import main.OmnetStateData;
import omnet.tactics.IncreaseTrafficLevelA;

public class TestSize1 {
	
	public static void build1(GPIndividual ind){
		//building a plan tree
		GPTree[] treeInit = {new GPTree()};
		ind.trees = treeInit;
		GPNode node1 = new IncreaseTrafficLevelA();
		ind.trees[0].child=node1;
		GPNode[] childrenOfNode1 = new GPNode[0];
		node1.children = childrenOfNode1;
	}
	
	public static void main(String[] args) throws InstantiationException{	

		OmnetStateData sd = new OmnetStateData();
		GPIndividual ind = new GPIndividual();
		build1(ind);
		sd.getAllFinalStates(ind.trees[0].child);
		sd.printScores();

	}

}
