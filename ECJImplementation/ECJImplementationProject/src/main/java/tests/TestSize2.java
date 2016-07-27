package main.java.tests;

import ec.gp.GPIndividual;
import ec.gp.GPNode;
import ec.gp.GPTree;
import main.java.actions.operators.SequenceOperator;
import main.java.main.OmnetStateData;
import main.java.omnet.tactics.DecreaseDimmerLevelA;
import main.java.omnet.tactics.IncreaseTrafficLevelF;

public class TestSize2 {
	public static void build2(GPIndividual ind){
		//building a plan tree
		GPTree[] treeInit = {new GPTree()};
		ind.trees = treeInit;
		GPNode node1 = new SequenceOperator();
		GPNode node2 = new IncreaseTrafficLevelF();
		GPNode node3 = new DecreaseDimmerLevelA();

		GPNode[] childrenOfNode1 = new GPNode[2];
		GPNode[] childrenOfNode2 = new GPNode[0];
		GPNode[] childrenOfNode3 = new GPNode[0];

		childrenOfNode1[0] = node2;
		childrenOfNode1[1] = node3;

		ind.trees[0].child=node1;
		node1.children = childrenOfNode1;
		node2.children = childrenOfNode2;
		node3.children = childrenOfNode3;

	}
	
	public static void main(String[] args) throws InstantiationException{	

		OmnetStateData sd = new OmnetStateData();
		GPIndividual ind = new GPIndividual();
		build2(ind);
		sd.countPossibleStates(ind.trees[0].child);
		sd.averageScore();
		//System.out.println(sd.finalScores.size());
		//sd.printScores();

	}
}
