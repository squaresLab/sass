package tests;

import ec.gp.GPIndividual;
import ec.gp.GPNode;
import ec.gp.GPTree;
import actions.operators.SequenceOperator;
import main.OmnetStateData;
import omnet.tactics.DecreaseTrafficLevelB;
import omnet.tactics.IncreaseTrafficLevelA;
import omnet.tactics.IncreaseTrafficLevelB;
import omnet.tactics.IncreaseTrafficLevelC;
import omnet.tactics.IncreaseTrafficLevelD;
import omnet.tactics.IncreaseTrafficLevelE;
import omnet.tactics.IncreaseTrafficLevelF;
import omnet.tactics.IncreaseTrafficLevelG;

public class TestSize20 {
	
	public static void build20(GPIndividual ind){
		GPTree[] treeInit = {new GPTree()};
		ind.trees = treeInit;
		GPNode node1 = new SequenceOperator();
		GPNode node2 = new SequenceOperator();
		GPNode node3 = new SequenceOperator();
		GPNode node4 = new SequenceOperator();
		GPNode node5 = new SequenceOperator();
		GPNode node6 = new SequenceOperator();
		GPNode node7 = new SequenceOperator();
		GPNode node8 = new SequenceOperator();
		GPNode node9 = new SequenceOperator();
		GPNode node10 = new SequenceOperator();
		GPNode node11 = new SequenceOperator();
		GPNode node12 = new IncreaseTrafficLevelA();
		GPNode node13 = new IncreaseTrafficLevelB();
		GPNode node14 = new IncreaseTrafficLevelC();
		GPNode node15 = new IncreaseTrafficLevelD();
		GPNode node16 = new SequenceOperator();
		GPNode node17 = new SequenceOperator();
		GPNode node18 = new SequenceOperator();
		GPNode node19 = new SequenceOperator();
		GPNode node20 = new SequenceOperator();
		GPNode node21 = new SequenceOperator();
		GPNode node22 = new SequenceOperator();
		GPNode node23 = new SequenceOperator();
		GPNode node24 = new IncreaseTrafficLevelE();
		GPNode node25 = new IncreaseTrafficLevelF();
		GPNode node26 = new IncreaseTrafficLevelG();
		GPNode node27 = new IncreaseTrafficLevelA();
		GPNode node28 = new DecreaseTrafficLevelB();
		GPNode node29 = new IncreaseTrafficLevelB();
		GPNode node30 = new IncreaseTrafficLevelC();
		GPNode node31 = new IncreaseTrafficLevelD();
		GPNode node32 = new IncreaseTrafficLevelE();
		GPNode node33 = new IncreaseTrafficLevelF();
		GPNode node34 = new IncreaseTrafficLevelG();
		GPNode node35 = new IncreaseTrafficLevelA();
		GPNode node36 = new IncreaseTrafficLevelB();
		GPNode node37 = new IncreaseTrafficLevelC();
		GPNode node38 = new IncreaseTrafficLevelD();
		GPNode node39 = new IncreaseTrafficLevelE();


		GPNode[] childrenOfNode1 = new GPNode[2];
		GPNode[] childrenOfNode2 = new GPNode[2];
		GPNode[] childrenOfNode3 = new GPNode[2];
		GPNode[] childrenOfNode4 = new GPNode[2];
		GPNode[] childrenOfNode5 = new GPNode[2];
		GPNode[] childrenOfNode6 = new GPNode[2];
		GPNode[] childrenOfNode7 = new GPNode[2];
		GPNode[] childrenOfNode8 = new GPNode[2];
		GPNode[] childrenOfNode9 = new GPNode[2];
		GPNode[] childrenOfNode10 = new GPNode[2];
		GPNode[] childrenOfNode11 = new GPNode[2];
		GPNode[] childrenOfNode12 = new GPNode[0];
		GPNode[] childrenOfNode13 = new GPNode[0];
		GPNode[] childrenOfNode14 = new GPNode[0];
		GPNode[] childrenOfNode15 = new GPNode[0];
		GPNode[] childrenOfNode16 = new GPNode[2];
		GPNode[] childrenOfNode17 = new GPNode[2];
		GPNode[] childrenOfNode18 = new GPNode[2];
		GPNode[] childrenOfNode19 = new GPNode[2];
		GPNode[] childrenOfNode20 = new GPNode[2];
		GPNode[] childrenOfNode21 = new GPNode[2];
		GPNode[] childrenOfNode22 = new GPNode[2];
		GPNode[] childrenOfNode23 = new GPNode[2];
		GPNode[] childrenOfNode24 = new GPNode[0];
		GPNode[] childrenOfNode25 = new GPNode[0];
		GPNode[] childrenOfNode26 = new GPNode[0];
		GPNode[] childrenOfNode27 = new GPNode[0];
		GPNode[] childrenOfNode28 = new GPNode[0];
		GPNode[] childrenOfNode29 = new GPNode[0];
		GPNode[] childrenOfNode30 = new GPNode[0];
		GPNode[] childrenOfNode31 = new GPNode[0];
		GPNode[] childrenOfNode32 = new GPNode[0];		
		GPNode[] childrenOfNode33 = new GPNode[0];
		GPNode[] childrenOfNode34 = new GPNode[0];
		GPNode[] childrenOfNode35 = new GPNode[0];
		GPNode[] childrenOfNode36 = new GPNode[0];
		GPNode[] childrenOfNode37 = new GPNode[0];
		GPNode[] childrenOfNode38 = new GPNode[0];
		GPNode[] childrenOfNode39 = new GPNode[0];


		childrenOfNode1[0] = node2;
		childrenOfNode1[1] = node3;
		childrenOfNode2[0] = node4;
		childrenOfNode2[1] = node5;
		childrenOfNode3[0] = node6;
		childrenOfNode3[1] = node7;
		childrenOfNode4[0] = node8;
		childrenOfNode4[1] = node9;
		childrenOfNode5[0] = node10;
		childrenOfNode5[1] = node11;
		childrenOfNode6[0] = node12;
		childrenOfNode6[1] = node13;
		childrenOfNode7[0] = node14;
		childrenOfNode7[1] = node15;
		childrenOfNode8[0] = node16;
		childrenOfNode8[1] = node17;
		childrenOfNode9[0] = node18;
		childrenOfNode9[1] = node19;
		childrenOfNode10[0] = node20;
		childrenOfNode10[1] = node21;
		childrenOfNode11[0] = node22;
		childrenOfNode11[1] = node23;


		childrenOfNode16[0] = node24;
		childrenOfNode16[1] = node25;
		childrenOfNode17[0] = node26;
		childrenOfNode17[1] = node27;
		childrenOfNode18[0] = node28;
		childrenOfNode18[1] = node29;
		childrenOfNode19[0] = node30;
		childrenOfNode19[1] = node31;
		childrenOfNode20[0] = node32;
		childrenOfNode20[1] = node33;
		childrenOfNode21[0] = node34;
		childrenOfNode21[1] = node35;
		childrenOfNode22[0] = node36;
		childrenOfNode22[1] = node37;
		childrenOfNode23[0] = node38;
		childrenOfNode23[1] = node39;

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
		node29.children = childrenOfNode29;
		node30.children = childrenOfNode30;
		node31.children = childrenOfNode31;
		node32.children = childrenOfNode32;
		node33.children = childrenOfNode33;
		node34.children = childrenOfNode34;
		node35.children = childrenOfNode35;
		node36.children = childrenOfNode36;
		node37.children = childrenOfNode37;
		node38.children = childrenOfNode38;
		node39.children = childrenOfNode39;

	}
	public static void main(String[] args) throws InstantiationException{	

		OmnetStateData sd = new OmnetStateData();
		GPIndividual ind = new GPIndividual();
		build20(ind);
		sd.getAllFinalStates(ind.trees[0].child);
		sd.printScores();

	}
}
