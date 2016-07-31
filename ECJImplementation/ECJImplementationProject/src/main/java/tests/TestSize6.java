package main.java.tests;

import ec.gp.GPIndividual;
import ec.gp.GPNode;
import ec.gp.GPTree;
import main.java.actions.operators.SequenceOperator;
import main.java.main.OmnetStateData;
import main.java.omnet.tactics.DecreaseDimmerLevelA;
import main.java.omnet.tactics.DecreaseTrafficLevelF;
import main.java.omnet.tactics.IncreaseDimmerLevelA;
import main.java.omnet.tactics.IncreaseTrafficLevelD;
import main.java.omnet.tactics.ShutdownServerC;
import main.java.omnet.tactics.StartNewServerB;

public class TestSize6 {
	
	//Size 6: DecreaseDimmerLevelA;StartNewServerB;ShutdownServerC;IncreaseTrafficLevelD;DecreaseTrafficLevelF;
	//			IncreaseDimmerLevelA
	public static void build6(GPIndividual ind){
		GPTree[] treeInit = {new GPTree()};
		ind.trees = treeInit;
		GPNode node1 = new SequenceOperator();
		GPNode node2 = new SequenceOperator();
		GPNode node3 = new SequenceOperator();
		GPNode node4 = new DecreaseDimmerLevelA();
		GPNode node5 = new StartNewServerB();
		GPNode node6 = new SequenceOperator();
		GPNode node7 = new SequenceOperator();
		GPNode node8 = new ShutdownServerC();
		GPNode node9 = new IncreaseTrafficLevelD();
		GPNode node10 = new SequenceOperator();
		GPNode node11 = new DecreaseTrafficLevelF();
		GPNode node12 = new IncreaseDimmerLevelA();

		GPNode[] childrenOfNode1 = new GPNode[2];
		GPNode[] childrenOfNode2 = new GPNode[2];
		GPNode[] childrenOfNode3 = new GPNode[2];
		GPNode[] childrenOfNode4 = new GPNode[0];
		GPNode[] childrenOfNode5 = new GPNode[0];
		GPNode[] childrenOfNode6 = new GPNode[1];
		GPNode[] childrenOfNode7 = new GPNode[2];
		GPNode[] childrenOfNode8 = new GPNode[0];
		GPNode[] childrenOfNode9 = new GPNode[0];
		GPNode[] childrenOfNode10 = new GPNode[2];
		GPNode[] childrenOfNode11 = new GPNode[0];
		GPNode[] childrenOfNode12 = new GPNode[0];

		childrenOfNode1[0] = node2;
		childrenOfNode1[1] = node3;
		childrenOfNode2[0] = node4;
		childrenOfNode2[1] = node5;
		childrenOfNode3[0] = node6;
		childrenOfNode3[1] = node7;
		childrenOfNode6[0] = node8;
		childrenOfNode7[0] = node9;
		childrenOfNode7[1] = node10;
		childrenOfNode10[0] = node11;
		childrenOfNode10[1] = node12;

		ind.trees[0].child=node1;
		node1.children = childrenOfNode1;
		node2.children = childrenOfNode2;
		node3.children = childrenOfNode3;
		node4.children = childrenOfNode4;
		node5.children = childrenOfNode5;
		node6.children = childrenOfNode6;
		node7.children = childrenOfNode7;
		node8.children = childrenOfNode8;
		node9.children = childrenOfNode9;
		node10.children = childrenOfNode10;
		node11.children = childrenOfNode11;
		node12.children = childrenOfNode12;
	}
	public static void main(String[] args) throws InstantiationException{	

		OmnetStateData sd = new OmnetStateData();
		GPIndividual ind = new GPIndividual();
		build6(ind);
		//sd.getPlanScore(ind.trees[0].child);
		//System.out.println(sd.finalScores.size());
		//sd.printScores();

	}
}
