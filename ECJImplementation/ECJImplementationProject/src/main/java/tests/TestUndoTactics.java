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
import main.java.omnet.tactics.DecreaseTrafficLevelA;
import main.java.omnet.tactics.IncreaseDimmerLevelA;
import main.java.omnet.tactics.IncreaseTrafficLevelA;
import main.java.omnet.tactics.IncreaseTrafficLevelB;
import main.java.omnet.tactics.IncreaseTrafficLevelC;
import main.java.omnet.tactics.ServerTactic;
import main.java.omnet.tactics.ShutdownServerA;
import main.java.omnet.tactics.ShutdownServerB;
import main.java.omnet.tactics.StartNewServerA;
import main.java.omnet.tactics.StartNewServerB;
import main.java.omnet.tactics.StartNewServerC;

public class TestUndoTactics {
	public static void main(String[] args) throws InstantiationException{	
		
		
		OmnetStatePath systemState = new OmnetStatePath();
		OmnetStateData sd = new OmnetStateData();
		GPIndividual ind = new GPIndividual();
		
		//building a plan tree
		GPTree[] treeInit = {new GPTree()};
		ind.trees = treeInit;
		GPNode node1 = new SequenceOperator();
		GPNode node2 = new StartNewServerA();
		GPNode node3 = new SequenceOperator();
		GPNode node4 = new StartNewServerA();
		GPNode node5 = new SequenceOperator();
		GPNode node6 = new IncreaseTrafficLevelB();
		GPNode node7 = new SequenceOperator();
		GPNode node8 = new StartNewServerA();
		GPNode[] childrenOfNode1 = new GPNode[2];
		GPNode[] childrenOfNode2 = new GPNode[2];
		GPNode[] childrenOfNode3 = new GPNode[2];
		GPNode[] childrenOfNode4 = new GPNode[2];
		GPNode[] childrenOfNode5 = new GPNode[2];
		GPNode[] childrenOfNode6 = new GPNode[2];
		GPNode[] childrenOfNode7 = new GPNode[2];
		GPNode[] childrenOfNode8 = new GPNode[2];
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
		childrenOfNode7[1] = null;
		childrenOfNode8[0] = null;
		childrenOfNode8[1] = null;
		
		ind.trees[0].child=node1;
		node1.children = childrenOfNode1;
		node2.children = childrenOfNode2;
		node3.children = childrenOfNode3;
		node4.children = childrenOfNode4;
		node5.children = childrenOfNode5;
		node6.children = childrenOfNode6;
		node7.children = childrenOfNode7;
		node8.children = childrenOfNode8;
		node1.parent = ind.trees[0];
		node2.parent = node1;
		node3.parent = node1;
		node4.parent = node3;
		node5.parent = node3;
		node6.parent = node5;
		node7.parent = node5;
		node8.parent = node7;
		
		//Start to test
//		sd.performAll(node1, systemState);
//		sd.undoUntilVisited(systemState);
		System.out.println(sd.countPossibleStates(ind));
		

	}
}
