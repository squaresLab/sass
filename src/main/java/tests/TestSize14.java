package tests;

import ec.gp.GPIndividual;
import ec.gp.GPNode;
import ec.gp.GPTree;
import actions.operators.SequenceOperator;
import main.OmnetStateData;
import omnet.tactics.DecreaseDimmerLevelA;
import omnet.tactics.DecreaseDimmerLevelC;
import omnet.tactics.DecreaseDimmerLevelD;
import omnet.tactics.DecreaseTrafficLevelB;
import omnet.tactics.DecreaseTrafficLevelG;
import omnet.tactics.IncreaseDimmerLevelA;
import omnet.tactics.IncreaseDimmerLevelE;
import omnet.tactics.ShutdownServerC;
import omnet.tactics.ShutdownServerF;
import omnet.tactics.StartNewServerB;

public class TestSize14 {
	public static void build14(GPIndividual ind){

		//building a plan tree
		GPTree[] treeInit = {new GPTree()};
		ind.trees = treeInit;
		GPNode node1 = new SequenceOperator();
		GPNode node2 = new IncreaseDimmerLevelA();
		GPNode node3 = new SequenceOperator();
		GPNode node4 = new IncreaseDimmerLevelA();
		GPNode node5 = new SequenceOperator();
		GPNode node6 = new IncreaseDimmerLevelA();
		GPNode node7 = new SequenceOperator();
		GPNode node8 = new DecreaseDimmerLevelA();
		GPNode node9 = new SequenceOperator();
		GPNode node10 = new IncreaseDimmerLevelE();
		GPNode node11 = new SequenceOperator();
		GPNode node12 = new DecreaseDimmerLevelA();
		GPNode node13 = new SequenceOperator();
		GPNode node14 = new DecreaseDimmerLevelC();
		GPNode node15 = new SequenceOperator();
		GPNode node16 = new DecreaseDimmerLevelD();
		GPNode node17 = new SequenceOperator();
		GPNode node18 = new DecreaseDimmerLevelA();
		GPNode node19 = new SequenceOperator();
		GPNode node20 = new DecreaseTrafficLevelB();
		GPNode node21 = new SequenceOperator();
		GPNode node22 = new ShutdownServerC();
		GPNode node23 = new SequenceOperator();
		GPNode node24 = new DecreaseTrafficLevelG();
		GPNode node25 = new SequenceOperator();
		GPNode node26 = new StartNewServerB();
		GPNode node27 = new SequenceOperator();
		GPNode node28 = new ShutdownServerF();

		GPNode[] childrenOfNode1 = new GPNode[2];
		GPNode[] childrenOfNode2 = new GPNode[0];
		GPNode[] childrenOfNode3 = new GPNode[2];
		GPNode[] childrenOfNode4 = new GPNode[0];
		GPNode[] childrenOfNode5 = new GPNode[2];
		GPNode[] childrenOfNode6 = new GPNode[0];
		GPNode[] childrenOfNode7 = new GPNode[2];
		GPNode[] childrenOfNode8 = new GPNode[0];
		GPNode[] childrenOfNode9 = new GPNode[2];
		GPNode[] childrenOfNode10 = new GPNode[0];
		GPNode[] childrenOfNode11 = new GPNode[2];
		GPNode[] childrenOfNode12 = new GPNode[0];
		GPNode[] childrenOfNode13 = new GPNode[2];
		GPNode[] childrenOfNode14 = new GPNode[0];
		GPNode[] childrenOfNode15 = new GPNode[2];
		GPNode[] childrenOfNode16 = new GPNode[0];
		GPNode[] childrenOfNode17 = new GPNode[2];
		GPNode[] childrenOfNode18 = new GPNode[0];
		GPNode[] childrenOfNode19 = new GPNode[2];
		GPNode[] childrenOfNode20 = new GPNode[0];
		GPNode[] childrenOfNode21 = new GPNode[2];
		GPNode[] childrenOfNode22 = new GPNode[0];
		GPNode[] childrenOfNode23 = new GPNode[2];
		GPNode[] childrenOfNode24 = new GPNode[0];
		GPNode[] childrenOfNode25 = new GPNode[2];
		GPNode[] childrenOfNode26 = new GPNode[0];
		GPNode[] childrenOfNode27 = new GPNode[1];
		GPNode[] childrenOfNode28 = new GPNode[0];

		childrenOfNode1[0] = node2;
		childrenOfNode1[1] = node3;
		childrenOfNode3[0] = node4;
		childrenOfNode3[1] = node5;
		childrenOfNode5[0] = node6;
		childrenOfNode5[1] = node7;
		childrenOfNode7[0] = node8;
		childrenOfNode7[1] = node9;
		childrenOfNode9[0] = node10;
		childrenOfNode9[1] = node11;
		childrenOfNode11[0] = node12;
		childrenOfNode11[1] = node13;
		childrenOfNode13[0] = node14;
		childrenOfNode13[1] = node15;
		childrenOfNode15[0] = node16;
		childrenOfNode15[1] = node17;
		childrenOfNode17[0] = node18;
		childrenOfNode17[1] = node19;
		childrenOfNode19[0] = node20;
		childrenOfNode19[1] = node21;
		childrenOfNode21[0] = node22;
		childrenOfNode21[1] = node23;
		childrenOfNode23[0] = node24;
		childrenOfNode23[1] = node25;
		childrenOfNode25[0] = node26;
		childrenOfNode25[1] = node27;
		childrenOfNode27[0] = node28;

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
		node13.children = childrenOfNode13;
		node14.children = childrenOfNode14;
		node15.children = childrenOfNode15;
		node16.children = childrenOfNode16;
		node17.children = childrenOfNode17;
		node18.children = childrenOfNode18;
		node19.children = childrenOfNode19;
		node20.children = childrenOfNode20;
		node21.children = childrenOfNode21;
		node22.children = childrenOfNode22;
		node23.children = childrenOfNode23;
		node24.children = childrenOfNode24;
		node25.children = childrenOfNode25;
		node26.children = childrenOfNode26;
		node27.children = childrenOfNode27;
		node28.children = childrenOfNode28;

	}
	
	public static void main(String[] args) throws InstantiationException{	

		OmnetStateData sd = new OmnetStateData();
		GPIndividual ind = new GPIndividual();
		build14(ind);
		sd.getAllFinalStates(ind.trees[0].child);
		sd.printScores();

	}
}
