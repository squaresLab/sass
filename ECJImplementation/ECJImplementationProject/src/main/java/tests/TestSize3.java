package main.java.tests;

import ec.gp.GPIndividual;
import ec.gp.GPNode;
import ec.gp.GPTree;
import main.java.actions.operators.SequenceOperator;
import main.java.main.OmnetStateData;
import main.java.omnet.tactics.StartNewServerA;
import main.java.omnet.tactics.StartNewServerB;
import main.java.omnet.tactics.StartNewServerC;

public class TestSize3 {
	//plan size 3
	public static void build3(GPIndividual ind){
		GPTree[] treeInit = {new GPTree()};
		ind.trees = treeInit;
		GPNode node1 = new SequenceOperator();
		GPNode node2 = new SequenceOperator();
		GPNode node3 = new SequenceOperator();
		GPNode node4 = new StartNewServerA();
		GPNode node5 = new StartNewServerB();
		GPNode node6 = new StartNewServerC();


		GPNode[] childrenOfNode1 = new GPNode[2];
		GPNode[] childrenOfNode2 = new GPNode[2];
		GPNode[] childrenOfNode3 = new GPNode[1];
		GPNode[] childrenOfNode4 = new GPNode[0];
		GPNode[] childrenOfNode5 = new GPNode[0];
		GPNode[] childrenOfNode6 = new GPNode[0];


		childrenOfNode1[0] = node2;
		childrenOfNode1[1] = node3;
		childrenOfNode2[0] = node4;
		childrenOfNode2[1] = node5;
		childrenOfNode3[0] = node6;


		ind.trees[0].child=node1;
		node1.children = childrenOfNode1;
		node2.children = childrenOfNode2;
		node3.children = childrenOfNode3;
		node4.children = childrenOfNode4;
		node5.children = childrenOfNode5;
		node6.children = childrenOfNode6;

	}
	
	public static void main(String[] args) throws InstantiationException{	

		OmnetStateData sd = new OmnetStateData();
		GPIndividual ind = new GPIndividual();
		build3(ind);
		sd.countPossibleStates(ind.trees[0].child);
		sd.averageScore();
		//System.out.println(sd.finalScores.size());
		//sd.printScores();

	}
}
