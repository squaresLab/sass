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
import main.java.omnet.tactics.ShutdownServerA;
import main.java.omnet.tactics.ShutdownServerB;
import main.java.omnet.tactics.StartNewServerA;

public class TestUndoTactics {
	public static void main(String[] args) throws InstantiationException{	
		OmnetStatePath systemState = new OmnetStatePath();
		ShutdownServerA shutA = new ShutdownServerA();
		ShutdownServerB shutB = new ShutdownServerB();
		StartNewServerA startA = new StartNewServerA();
		systemState.initializeState();
		System.out.println("total server count = " + systemState.getTotalServerCount());
		System.out.println("total time = " + systemState.getTotalTime());
		System.out.println("path Probability = " + systemState.getPathProbability());
		
		systemState.performTactic(shutA);
		System.out.println("\n" + "perform shut server A");
		System.out.println("total server count = " + systemState.getTotalServerCount());
		System.out.println("total time = " + systemState.getTotalTime());
		System.out.println("path Probability = " + systemState.getPathProbability());
		
		systemState.performTactic(startA);
		System.out.println("\n" + "perform start server A");
		System.out.println("total server count = " + systemState.getTotalServerCount());
		System.out.println("total time = " + systemState.getTotalTime());
		System.out.println("path Probability = " + systemState.getPathProbability());
		
		
//		systemState.undo();
//		System.out.println("\n" + "undo shut server A");
//		System.out.println("total server count = " + systemState.getTotalServerCount());
//		System.out.println("total time = " + systemState.getTotalTime());
//		System.out.println("path Probability = " + systemState.getPathProbability());
		
//		systemState.performTactic(shutA);
//		System.out.println("\n" + "perform shut server A again");
//		System.out.println("total server count = " + systemState.getTotalServerCount());
//		System.out.println("total time = " + systemState.getTotalTime());
//		System.out.println("path Probability = " + systemState.getPathProbability());
//		
//		systemState.performTactic(shutB);
//		System.out.println("\n" + "perform shut server B ");
//		System.out.println("total server count = " + systemState.getTotalServerCount());
//		System.out.println("total time = " + systemState.getTotalTime());
//		System.out.println("path Probability = " + systemState.getPathProbability());

		systemState.printAlreadyPerformed();
//		systemState.undoTactic(startA, ServerA.class);
//		System.out.println("total server count = " + systemState.getTotalServerCount());
//		System.out.println("total time = " + systemState.getTotalTime());
//		System.out.println("path Probability = " + systemState.getPathProbability());
//		systemState.performTactic(startA, ServerA.class);
//		System.out.println("total server count = " + systemState.getTotalServerCount());
//		System.out.println("total time = " + systemState.getTotalTime());
//		System.out.println("path Probability = " + systemState.getPathProbability());

}
}
