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
import main.java.omnet.tactics.ServerTactic;
import main.java.omnet.tactics.ShutdownServerA;
import main.java.omnet.tactics.ShutdownServerB;
import main.java.omnet.tactics.StartNewServerA;

public class TestUndoTactics {
	public static void main(String[] args) throws InstantiationException{	
		OmnetStatePath systemState = new OmnetStatePath();
		ShutdownServerA shutA = new ShutdownServerA();
		ShutdownServerB shutB = new ShutdownServerB();
		StartNewServerA startA = new StartNewServerA();
		IncreaseDimmerLevelA idA = new IncreaseDimmerLevelA();
		DecreaseTrafficLevelA dtA = new DecreaseTrafficLevelA();
		
		//systemState.initializeState();
		System.out.println("total server count = " + systemState.getTotalServerCount());
		System.out.println("total time = " + systemState.getTotalTime());
		System.out.println("path Probability = " + systemState.getPathProbability());
		
		systemState.performTactic(startA);
		System.out.println("\n" + "perform start server A");
		System.out.println("total server count = " + systemState.getTotalServerCount());
		System.out.println("total time = " + systemState.getTotalTime());
		System.out.println("path Probability = " + systemState.getPathProbability());
		systemState.printAlreadyPerformed();
		
		systemState.performTactic(shutA);
		System.out.println("\n" + "perform shut server A");
		System.out.println("total server count = " + systemState.getTotalServerCount());
		System.out.println("total time = " + systemState.getTotalTime());
		System.out.println("path Probability = " + systemState.getPathProbability());
		systemState.printAlreadyPerformed();
		
		systemState.performTactic(idA);
		System.out.println("\n" + "perform IncreaseDimmerLevelA");
		System.out.println("total server count = " + systemState.getTotalServerCount());
		System.out.println("total time = " + systemState.getTotalTime());
		System.out.println("path Probability = " + systemState.getPathProbability());
		systemState.printAlreadyPerformed();
		
		systemState.performTactic(idA);
		System.out.println("\n" + "perform IncreaseDimmerLevelA again!");
		System.out.println("total server count = " + systemState.getTotalServerCount());
		System.out.println("total time = " + systemState.getTotalTime());
		System.out.println("path Probability = " + systemState.getPathProbability());
		systemState.printAlreadyPerformed();
		
		systemState.performTactic(dtA);
		System.out.println("\n" + "perform DecreaseTrafficLevelA");
		System.out.println("total server count = " + systemState.getTotalServerCount());
		System.out.println("total time = " + systemState.getTotalTime());
		System.out.println("path Probability = " + systemState.getPathProbability());
		systemState.printAlreadyPerformed();
		
		systemState.printProbabilityArray();

		systemState.undoTactic();
		System.out.println("\n" + "undo 1");
		System.out.println("total server count = " + systemState.getTotalServerCount());
		System.out.println("total time = " + systemState.getTotalTime());
		System.out.println("path Probability = " + systemState.getPathProbability());
		systemState.printAlreadyPerformed();
		
		
		systemState.undoTactic();
		System.out.println("\n" + "undo 2");
		System.out.println("total server count = " + systemState.getTotalServerCount());
		System.out.println("total time = " + systemState.getTotalTime());
		System.out.println("path Probability = " + systemState.getPathProbability());
		systemState.printAlreadyPerformed();

		systemState.undoTactic();
		System.out.println("\n" + "undo 3");
		System.out.println("total server count = " + systemState.getTotalServerCount());
		System.out.println("total time = " + systemState.getTotalTime());
		System.out.println("path Probability = " + systemState.getPathProbability());
		systemState.printAlreadyPerformed();

		systemState.undoTactic();
		System.out.println("\n" + "undo 4");
		System.out.println("total server count = " + systemState.getTotalServerCount());
		System.out.println("total time = " + systemState.getTotalTime());
		System.out.println("path Probability = " + systemState.getPathProbability());
		systemState.printAlreadyPerformed();
		
		systemState.undoTactic();
		System.out.println("\n" + "undo 5");
		System.out.println("total server count = " + systemState.getTotalServerCount());
		System.out.println("total time = " + systemState.getTotalTime());
		System.out.println("path Probability = " + systemState.getPathProbability());
		systemState.printAlreadyPerformed();

		


}
}
