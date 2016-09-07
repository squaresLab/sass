package tests;

import ec.gp.GPIndividual;
import ec.gp.GPNode;
import ec.gp.GPTree;
import actions.operators.SequenceOperator;
import main.OmnetStateData;
import omnet.tactics.DecreaseDimmerLevelA;
import omnet.tactics.IncreaseDimmerLevelA;
import omnet.tactics.ShutdownServerC;

public class TestSize4 {
	
	//plan size 4
	public static void build4(GPIndividual ind){
		//4 actions plan
		GPTree[] treeInit = {new GPTree()};
		ind.trees = treeInit;
		GPNode node1 = new SequenceOperator();
		GPNode node2 = new IncreaseDimmerLevelA();
		GPNode node3 = new SequenceOperator();
		GPNode node4 = new DecreaseDimmerLevelA();
		GPNode node5 = new SequenceOperator();
		GPNode node6 = new DecreaseDimmerLevelA();
		GPNode node7 = new SequenceOperator();
		GPNode node8 = new ShutdownServerC();

		GPNode[] childrenOfNode1 = new GPNode[2];
		GPNode[] childrenOfNode2 = new GPNode[0];
		GPNode[] childrenOfNode3 = new GPNode[2];
		GPNode[] childrenOfNode4 = new GPNode[0];
		GPNode[] childrenOfNode5 = new GPNode[2];
		GPNode[] childrenOfNode6 = new GPNode[0];
		GPNode[] childrenOfNode7 = new GPNode[1];
		GPNode[] childrenOfNode8 = new GPNode[0];

		childrenOfNode1[0] = node2;
		childrenOfNode1[1] = node3;
		childrenOfNode3[0] = node4;
		childrenOfNode3[1] = node5;
		childrenOfNode5[0] = node6;
		childrenOfNode5[1] = node7;
		childrenOfNode7[0] = node8;

		ind.trees[0].child=node1;
		node1.children = childrenOfNode1;
		node2.children = childrenOfNode2;
		node3.children = childrenOfNode3;
		node4.children = childrenOfNode4;
		node5.children = childrenOfNode5;
		node6.children = childrenOfNode6;
		node7.children = childrenOfNode7;
		node8.children = childrenOfNode8;
	}

	public static void main(String[] args) throws InstantiationException{	

		OmnetStateData sd = new OmnetStateData();
		GPIndividual ind = new GPIndividual();
		build4(ind);
		sd.getAllFinalStates(ind.trees[0].child);
		sd.printScores();

	}
}
