package main.java.tests;

import ec.EvolutionState;
import ec.Evolve;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;
import ec.gp.GPTree;
import ec.util.ParameterDatabase;
import main.java.actions.FailableTactic;
import main.java.actions.operators.SequenceOperator;
import main.java.main.OmnetStateData;
import main.java.main.OmnetStatePath;
import main.java.main.SingleObjectiveProblem;
import main.java.omnet.components.ServerA;
import main.java.omnet.tactics.DecreaseDimmerLevelA;
import main.java.omnet.tactics.DecreaseDimmerLevelF;
import main.java.omnet.tactics.DecreaseTrafficLevelA;
import main.java.omnet.tactics.DecreaseTrafficLevelB;
import main.java.omnet.tactics.DecreaseTrafficLevelD;
import main.java.omnet.tactics.IncreaseDimmerLevelA;
import main.java.omnet.tactics.IncreaseDimmerLevelB;
import main.java.omnet.tactics.IncreaseDimmerLevelC;
import main.java.omnet.tactics.IncreaseDimmerLevelF;
import main.java.omnet.tactics.IncreaseTrafficLevelA;
import main.java.omnet.tactics.IncreaseTrafficLevelB;
import main.java.omnet.tactics.IncreaseTrafficLevelC;
import main.java.omnet.tactics.IncreaseTrafficLevelD;
import main.java.omnet.tactics.IncreaseTrafficLevelF;
import main.java.omnet.tactics.IncreaseTrafficLevelG;
import main.java.omnet.tactics.ServerTactic;
import main.java.omnet.tactics.ShutdownServerA;
import main.java.omnet.tactics.ShutdownServerB;
import main.java.omnet.tactics.ShutdownServerE;
import main.java.omnet.tactics.ShutdownServerG;
import main.java.omnet.tactics.StartNewServerA;
import main.java.omnet.tactics.StartNewServerB;
import main.java.omnet.tactics.StartNewServerC;
import main.java.omnet.tactics.StartNewServerF;
import main.java.omnet.tactics.StartNewServerG;

public class TestUndoTactics {
	
	public static void build(GPIndividual ind){
	
		//building a plan tree
		GPTree[] treeInit = {new GPTree()};
		ind.trees = treeInit;
		GPNode node1 = new SequenceOperator();
		GPNode node2 = new ShutdownServerG();
		GPNode node3 = new SequenceOperator();
		GPNode node4 = new IncreaseDimmerLevelC();
		GPNode node5 = new SequenceOperator();
		GPNode node6 = new StartNewServerF();
		GPNode node7 = new SequenceOperator();
		GPNode node8 = new IncreaseTrafficLevelC();
		GPNode node9 = new SequenceOperator();
		GPNode node10 = new ShutdownServerE();
		GPNode node11 = new SequenceOperator();
		GPNode node12 = new IncreaseTrafficLevelD();
		GPNode node13 = new SequenceOperator();
		GPNode node14 = new ShutdownServerB();
		GPNode node15 = new SequenceOperator();
		GPNode node16 = new DecreaseDimmerLevelA();
		
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
		GPNode[] childrenOfNode12 = new GPNode[2];
		GPNode[] childrenOfNode13 = new GPNode[2];
		GPNode[] childrenOfNode14 = new GPNode[2];
		GPNode[] childrenOfNode15 = new GPNode[2];
		GPNode[] childrenOfNode16 = new GPNode[2];
		childrenOfNode1[0] = node2;
		childrenOfNode1[1] = node3;
		childrenOfNode2[0] = null;
		childrenOfNode2[1] = null;
		childrenOfNode3[0] = node4;
		childrenOfNode3[1] = node5;
		childrenOfNode4[0] = null;
		childrenOfNode4[1] = null;
		childrenOfNode5[0] = node6;
		childrenOfNode5[1] = node7;
		childrenOfNode6[0] = null;
		childrenOfNode6[1] = null;
		childrenOfNode7[0] = node8;
		childrenOfNode7[1] = node9;
		childrenOfNode8[0] = null;
		childrenOfNode8[1] = null;
		childrenOfNode9[0] = node10;
		childrenOfNode9[1] = node11;
		childrenOfNode10[0] = null;
		childrenOfNode10[1] = null;
		childrenOfNode11[0] = node12;
		childrenOfNode11[1] = node13;
		childrenOfNode12[0] = null;
		childrenOfNode12[1] = null;
		childrenOfNode13[0] = node14;
		childrenOfNode13[1] = node15;
		childrenOfNode14[0] = null;
		childrenOfNode14[1] = null;
		childrenOfNode15[0] = node16;
		childrenOfNode15[1] = null;
		childrenOfNode16[0] = null;
		childrenOfNode16[1] = null;
		
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
		node1.parent = ind.trees[0];
		node2.parent = node1;
		node3.parent = node1;
		node4.parent = node3;
		node5.parent = node3;
		node6.parent = node5;
		node7.parent = node5;
		node8.parent = node7;
		node9.parent = node7;
		node10.parent = node9;
		node11.parent = node9;
		node12.parent = node11;
		node13.parent = node11;
		node14.parent = node13;
		node15.parent = node13;
		node16.parent = node15;
	}
	
	public void print(){
		
	}
	public static void main(String[] args) throws InstantiationException{	
		
		OmnetStateData sd = new OmnetStateData();
		GPIndividual ind = new GPIndividual();
		build(ind);
		System.out.println("There are " + sd.countPossibleStates(ind) + " possible final states");
		sd.printScores();

	}
}
