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
import main.java.omnet.tactics.ServerTactic;
import main.java.omnet.tactics.ShutdownServerA;
import main.java.omnet.tactics.ShutdownServerB;
import main.java.omnet.tactics.StartNewServerA;
import main.java.omnet.tactics.StartNewServerB;
import main.java.omnet.tactics.StartNewServerC;

public class TestUndoTactics {
	public static void main(String[] args) throws InstantiationException{	
		
		//systemState.initializeState();
//		System.out.println("total server count = " + systemState.getTotalServerCount());
//		System.out.println("total time = " + systemState.getTotalTime());
//		System.out.println("path Probability = " + systemState.getPathProbability());
//
//		systemState.performTactic(startA);
//		System.out.println("\n" + "perform start server A");
//		System.out.println("total server count = " + systemState.getTotalServerCount());
//		System.out.println("total time = " + systemState.getTotalTime());
//		System.out.println("path Probability = " + systemState.getPathProbability());
//		systemState.printAlreadyPerformed();
//
//		systemState.printProbabilityArray();
//
//		systemState.undoTactic();
//		System.out.println("\n" + "undo 1");
//		System.out.println("total server count = " + systemState.getTotalServerCount());
//		System.out.println("total time = " + systemState.getTotalTime());
//		System.out.println("path Probability = " + systemState.getPathProbability());
//		systemState.printAlreadyPerformed();
		
		OmnetStatePath systemState = new OmnetStatePath();
		OmnetStateData sd = new OmnetStateData();
		GPIndividual ind = new GPIndividual();
		GPTree[] treeInit = {new GPTree()};
		ind.trees = treeInit;
		GPNode node1 = new SequenceOperator();
		GPNode node2 = new StartNewServerA();
		GPNode node3 = new SequenceOperator();
		GPNode node4 = new IncreaseTrafficLevelA();
		
		GPNode[] childrenOfNode1 = new GPNode[2];
		GPNode[] childrenOfNode2 = new GPNode[2];
		GPNode[] childrenOfNode3 = new GPNode[2];
		GPNode[] childrenOfNode4 = new GPNode[2];
		
		childrenOfNode1[0] = node2;
		childrenOfNode1[1] = node3;
		childrenOfNode2[0] = null;
		childrenOfNode2[1] = null;
		childrenOfNode3[0] = node4;
		childrenOfNode3[1] = null;
		childrenOfNode4[0] = null;
		childrenOfNode4[1] = null;
		ind.trees[0].child=node1;
		node1.children = childrenOfNode1;
		node2.children = childrenOfNode2;
		node3.children = childrenOfNode3;
		node4.children = childrenOfNode4;
		
		
		node1.parent = ind.trees[0];
		node2.parent = node1;
		node3.parent = node1;
		node4.parent = node3;

		System.out.println(sd.performAll(ind, systemState));
		System.out.println("total server count = " + systemState.getTotalServerCount());
		System.out.println("total time = " + systemState.getTotalTime());
		System.out.println("path Probability = " + systemState.getPathProbability());
		System.out.println("ServerA current traffic level" + systemState.prototypeServerA.getTrafficLevel());
		
		//sd.performAll(ind, systemState);
		//System.out.println(ind.trees[0].child.children[1].children[1]);
	}
}
