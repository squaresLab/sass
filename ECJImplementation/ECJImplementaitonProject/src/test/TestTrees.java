package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import actions.operators.ForOperator;
import actions.operators.IfThenElseOperator;
import actions.operators.SequenceOperator;
import ec.EvolutionState;
import ec.Evolve;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;
import ec.gp.GPTree;
import ec.util.ParameterDatabase;
import junit.framework.TestCase;
import main.MultiObjectiveProblem;
import main.SingleObjectiveProblem;
import main.StateData;
import znn.tactics.AddServerL1;
import znn.tactics.DecreaseDatabaseAThreads;
import znn.tactics.DecreaseDatabaseBThreads;
import znn.tactics.DecreaseQuality;
import znn.tactics.DeleteServerL1;
import znn.tactics.DeleteServerL2;
import znn.tactics.IncreaseDatabaseAThreads;
import znn.tactics.IncreaseDatabaseBThreads;
import znn.tactics.IncreaseQuality;

public class TestTrees extends TestCase {
  
	@Test
	public void checkInitialScore()
	{
		StateData sd = new StateData();
		sd.initializeData();
		System.out.println("Initial State Score: "+sd.calculateStateScore());
		assert(sd.calculateStateScore()==980);
	}
	
	@Test
	public void checkBasicSequence()
	{
		StateData sd = new StateData();
		sd.initializeData();
		GPIndividual ind = new GPIndividual();
		GPTree[] treeInit = {new GPTree()};
		ind.trees = treeInit;
		GPNode root = new SequenceOperator();
		//TODO - set up the tree correctly and then check if the eval
		//method works correctly
		GPNode secondSequenceOperator = new SequenceOperator();
		GPNode[] childs = {new AddServerL1(), secondSequenceOperator};
		root.children = childs;
		ind.trees[0].child = root;
		GPNode[] childs2  = {new DecreaseDatabaseBThreads(), new DecreaseQuality()};
		secondSequenceOperator.children=childs2;
		Evolve ev = new Evolve();
		String[] inputFile = {"-file","selfadaptivesystemsingleobjective.params"};
		ParameterDatabase params = ev.loadParameterDatabase(inputFile);
		EvolutionState state = ev.initialize(params,0);
		((GPIndividual)ind).trees[0].child.eval(state, 0, (GPData)sd, new ADFStack(), 
				((GPIndividual)ind), new SingleObjectiveProblem());
		System.out.println("Final Result: "+sd.calculateStateScore());
		//TODO:Go back and make the test pass for different combinations of successes and failures
		//At the moment I'm assuming all the actions succeed.  If any fail than the test
		//will fail
		assert(Math.abs(sd.calculateStateScore()-1596.8)<0.1);
		
	}
	
	@Test
	public void checkFailingSequence(){
		StateData sd = new StateData();
		sd.initializeData();
		GPIndividual ind = new GPIndividual();
		GPTree[] treeInit = {new GPTree()};
		ind.trees = treeInit;
		GPNode root = new SequenceOperator();
		//TODO - set up the tree correctly and then check if the eval
		//method works correctly
		GPNode secondSequenceOperator = new SequenceOperator();
		GPNode[] childs = {new DecreaseDatabaseBThreads(), secondSequenceOperator};
		root.children = childs;
		ind.trees[0].child = root;
		GPNode[] childs2  = {new DecreaseDatabaseBThreads(), new DecreaseDatabaseBThreads()};
		secondSequenceOperator.children=childs2;
		Evolve ev = new Evolve();
		String[] inputFile = {"-file","selfadaptivesystemsingleobjective.params"};
		ParameterDatabase params = ev.loadParameterDatabase(inputFile);
		EvolutionState state = ev.initialize(params,0);
		((GPIndividual)ind).trees[0].child.eval(state, 0, (GPData)sd, new ADFStack(), 
				((GPIndividual)ind), new SingleObjectiveProblem());
		System.out.println("Final Result: "+sd.calculateStateScore());
		//TODO:Go back and make the test pass for different combinations of successes and failures
		//At the moment I'm assuming all the actions succeed.  If any fail than the test
		//will fail
		assertEquals(sd.calculateStateScore(),Long.MAX_VALUE, 0.01);
	}
	
	@Test
	public void checkServerIndependene(){
		StateData sd = new StateData();
		sd.initializeData();
		GPIndividual ind = new GPIndividual();
		GPTree[] treeInit = {new GPTree()};
		ind.trees = treeInit;
		GPNode root = new SequenceOperator();
		//TODO - set up the tree correctly and then check if the eval
		//method works correctly
		GPNode secondSequenceOperator = new SequenceOperator();
		GPNode[] childs = {new DeleteServerL2(), new DeleteServerL1()};
		root.children = childs;
		ind.trees[0].child = root;
		Evolve ev = new Evolve();
		String[] inputFile = {"-file","selfadaptivesystemsingleobjective.params"};
		ParameterDatabase params = ev.loadParameterDatabase(inputFile);
		EvolutionState state = ev.initialize(params,0);
		((GPIndividual)ind).trees[0].child.eval(state, 0, (GPData)sd, new ADFStack(), 
				((GPIndividual)ind), new SingleObjectiveProblem());
		System.out.println("Final Result: "+sd.calculateStateScore());
		//TODO:Go back and make the test pass for different combinations of successes and failures
		//At the moment I'm assuming all the actions succeed.  If any fail than the test
		//will fail
		assertTrue(sd.calculateStateScore()!=Long.MAX_VALUE);
	}
	
	@Test
	public void scoreDoubleDeleteServer(){
		StateData sd = new StateData();
		sd.initializeData();
		GPIndividual ind = new GPIndividual();
		GPTree[] treeInit = {new GPTree()};
		ind.trees = treeInit;
		GPNode root = new SequenceOperator();
		//TODO - set up the tree correctly and then check if the eval
		//method works correctly
		GPNode secondSequenceOperator = new SequenceOperator();
		GPNode[] childs = {new DeleteServerL1(), new DeleteServerL1()};
		root.children = childs;
		ind.trees[0].child = root;
		Evolve ev = new Evolve();
		String[] inputFile = {"-file","selfadaptivesystemsingleobjective.params"};
		ParameterDatabase params = ev.loadParameterDatabase(inputFile);
		EvolutionState state = ev.initialize(params,0);
		((GPIndividual)ind).trees[0].child.eval(state, 0, (GPData)sd, new ADFStack(), 
				((GPIndividual)ind), new SingleObjectiveProblem());
		System.out.println("Score Test Result: "+sd.calculateStateScore());
		//TODO:Go back and make the test pass for different combinations of successes and failures
		//At the moment I'm assuming all the actions succeed.  If any fail than the test
		//will fail
		//assert(sd.calculateStateScore()!=Long.MAX_VALUE);
	}
	
	@Test
	public void scoreIncreaseThenDecrease(){
		StateData sd = new StateData();
		sd.initializeData();
		GPIndividual ind = new GPIndividual();
		GPTree[] treeInit = {new GPTree()};
		ind.trees = treeInit;
		GPNode root = new SequenceOperator();
		//TODO - set up the tree correctly and then check if the eval
		//method works correctly
		GPNode secondSequenceOperator = new SequenceOperator();
		GPNode[] childs = {new IncreaseDatabaseAThreads(), new DecreaseDatabaseAThreads()};
		root.children = childs;
		ind.trees[0].child = root;
		Evolve ev = new Evolve();
		String[] inputFile = {"-file","selfadaptivesystemsingleobjective.params"};
		ParameterDatabase params = ev.loadParameterDatabase(inputFile);
		EvolutionState state = ev.initialize(params,0);
		((GPIndividual)ind).trees[0].child.eval(state, 0, (GPData)sd, new ADFStack(), 
				((GPIndividual)ind), new SingleObjectiveProblem());
		System.out.println("Increase Decrease Result: "+sd.calculateStateScore());
		//TODO:Go back and make the test pass for different combinations of successes and failures
		//At the moment I'm assuming all the actions succeed.  If any fail than the test
		//will fail
		//assert(sd.calculateStateScore()!=Long.MAX_VALUE);
	}
	
	@Test
	public void score10Increase(){
		StateData sd = new StateData();
		sd.initializeData();
		GPIndividual ind = new GPIndividual();
		GPTree[] treeInit = {new GPTree()};
		ind.trees = treeInit;
		GPNode root = new SequenceOperator();
		//TODO - set up the tree correctly and then check if the eval
		//method works correctly
		GPNode secondSequenceOperator = new SequenceOperator();
		GPNode[] childs = {new IncreaseDatabaseAThreads(), secondSequenceOperator};
		root.children = childs;
		ind.trees[0].child = root;
		GPNode thirdSequenceOperator = new SequenceOperator();
		GPNode[] childs2  = {new IncreaseDatabaseAThreads(), thirdSequenceOperator};
		secondSequenceOperator.children=childs2;
		GPNode fourthSequenceOperator = new SequenceOperator();
		GPNode[] childs3  = {new IncreaseDatabaseAThreads(), fourthSequenceOperator};
		thirdSequenceOperator.children=childs3;
		GPNode fifthSequenceOperator = new SequenceOperator();
		GPNode[] childs4  = {new IncreaseDatabaseAThreads(), fifthSequenceOperator};
		fourthSequenceOperator.children=childs4;
		GPNode sixthSequenceOperator = new SequenceOperator();
		GPNode[] childs5  = {new IncreaseDatabaseAThreads(), sixthSequenceOperator};
		fifthSequenceOperator.children=childs5;
		GPNode seventhSequenceOperator = new SequenceOperator();
		GPNode[] childs6  = {new IncreaseDatabaseAThreads(), seventhSequenceOperator};
		sixthSequenceOperator.children=childs6;
		GPNode eigthSequenceOperator = new SequenceOperator();
		GPNode[] childs7  = {new IncreaseDatabaseAThreads(), eigthSequenceOperator};
		seventhSequenceOperator.children=childs7;
		GPNode ninthSequenceOperator = new SequenceOperator();
		GPNode[] childs8  = {new IncreaseDatabaseAThreads(), ninthSequenceOperator};
		eigthSequenceOperator.children=childs8;
		GPNode[] childs9  = {new IncreaseDatabaseAThreads(), new IncreaseDatabaseAThreads()};
		ninthSequenceOperator.children=childs9;
		
		Evolve ev = new Evolve();
		String[] inputFile = {"-file","selfadaptivesystemsingleobjective.params"};
		ParameterDatabase params = ev.loadParameterDatabase(inputFile);
		EvolutionState state = ev.initialize(params,0);
		((GPIndividual)ind).trees[0].child.eval(state, 0, (GPData)sd, new ADFStack(), 
				((GPIndividual)ind), new SingleObjectiveProblem());
		System.out.println("10 Increase Result: "+sd.calculateStateScore());
		//TODO:Go back and make the test pass for different combinations of successes and failures
		//At the moment I'm assuming all the actions succeed.  If any fail than the test
		//will fail
		//assert(sd.calculateStateScore()!=Long.MAX_VALUE);
	}
	
	@Test
	public void compareIncreaseingThreads(){
		StateData sd = new StateData();
		sd.initializeData();
		GPIndividual ind = new GPIndividual();
		GPTree[] treeInit = {new GPTree()};
		ind.trees = treeInit;
		GPNode root = new IncreaseDatabaseAThreads();
		ind.trees[0].child = root;
		Evolve ev = new Evolve();
		String[] inputFile = {"-file","selfadaptivesystemsingleobjective.params"};
		ParameterDatabase params = ev.loadParameterDatabase(inputFile);
		EvolutionState state = ev.initialize(params,0);
		((GPIndividual)ind).trees[0].child.eval(state, 0, (GPData)sd, new ADFStack(), 
				((GPIndividual)ind), new SingleObjectiveProblem());
		double increaseAResult = sd.calculateStateScore();
		
		
		sd.initializeData();
		GPIndividual ind2 = new GPIndividual();
		GPTree[] treeInit2 = {new GPTree()};
		ind2.trees = treeInit2;
		GPNode root2 = new IncreaseDatabaseBThreads();
		ind2.trees[0].child = root2;
		((GPIndividual)ind2).trees[0].child.eval(state, 0, (GPData)sd, new ADFStack(), 
				((GPIndividual)ind2), new SingleObjectiveProblem());
		double increaseBresult = sd.calculateStateScore();
		
		
		System.out.println("Increase A result: "+increaseAResult+", increase B result: "+increaseBresult);
		//TODO:Go back and make the test pass for different combinations of successes and failures
		//At the moment I'm assuming all the actions succeed.  If any fail than the test
		//will fail
		//assert(sd.calculateStateScore()!=Long.MAX_VALUE);
	}
	
	@Test
	public void compareDecreaseQuality(){
		StateData sd = new StateData();
		sd.initializeData();
		double initialScore = sd.calculateStateScore();
		GPIndividual ind = new GPIndividual();
		GPTree[] treeInit = {new GPTree()};
		ind.trees = treeInit;
		GPNode root = new DecreaseQuality();
		ind.trees[0].child = root;
		Evolve ev = new Evolve();
		String[] inputFile = {"-file","selfadaptivesystemsingleobjective.params"};
		ParameterDatabase params = ev.loadParameterDatabase(inputFile);
		EvolutionState state = ev.initialize(params,0);
		((GPIndividual)ind).trees[0].child.eval(state, 0, (GPData)sd, new ADFStack(), 
				((GPIndividual)ind), new SingleObjectiveProblem());
		System.out.println("Initial: "+initialScore+", After Decrease Quality:"+sd.calculateStateScore());
	}
	

	@Test
	public void compareAllDecreaseAThreadsWithMix(){

		StateData sd = new StateData();
		sd.initializeData();
		GPIndividual ind = new GPIndividual();
		GPTree[] treeInit = {new GPTree()};
		ind.trees = treeInit;
		GPNode root = new SequenceOperator();
		//TODO - set up the tree correctly and then check if the eval
		//method works correctly
		GPNode secondSequenceOperator = new SequenceOperator();
		GPNode[] childs = {new IncreaseDatabaseAThreads(), secondSequenceOperator};
		root.children = childs;
		ind.trees[0].child = root;
		GPNode thirdSequenceOperator = new SequenceOperator();
		GPNode[] childs2  = {new IncreaseDatabaseAThreads(), thirdSequenceOperator};
		secondSequenceOperator.children=childs2;
		GPNode fourthSequenceOperator = new SequenceOperator();
		GPNode[] childs3  = {new IncreaseDatabaseAThreads(), fourthSequenceOperator};
		thirdSequenceOperator.children=childs3;
		GPNode fifthSequenceOperator = new SequenceOperator();
		GPNode[] childs4  = {new DecreaseQuality(), fifthSequenceOperator};
		fourthSequenceOperator.children=childs4;
		GPNode sixthSequenceOperator = new SequenceOperator();
		GPNode[] childs5  = {new IncreaseDatabaseAThreads(), sixthSequenceOperator};
		fifthSequenceOperator.children=childs5;
		GPNode[] childs6  = {new IncreaseDatabaseAThreads(), new IncreaseDatabaseAThreads()};
		sixthSequenceOperator.children=childs6;
		Evolve ev = new Evolve();
		String[] inputFile = {"-file","selfadaptivesystemsingleobjective.params"};
		ParameterDatabase params = ev.loadParameterDatabase(inputFile);
		EvolutionState state = ev.initialize(params,0);
		((GPIndividual)ind).trees[0].child.eval(state, 0, (GPData)sd, new ADFStack(), 
				((GPIndividual)ind), new SingleObjectiveProblem());
		double allIncreaseThreadA = sd.calculateStateScore();
		
		sd.initializeData();
		GPIndividual indB = new GPIndividual();
		GPTree[] treeInitB = {new GPTree()};
		indB.trees = treeInitB;
		GPNode rootB = new SequenceOperator();
		//TODO - set up the tree correctly and then check if the eval
		//method works correctly
		GPNode secondSequenceOperatorB = new SequenceOperator();
		GPNode[] childsB = {new IncreaseDatabaseAThreads(), secondSequenceOperatorB};
		rootB.children = childsB;
		indB.trees[0].child = rootB;
		GPNode thirdSequenceOperatorB = new SequenceOperator();
		GPNode[] childs2B  = {new IncreaseDatabaseAThreads(), thirdSequenceOperatorB};
		secondSequenceOperatorB.children=childs2B;
		GPNode fourthSequenceOperatorB = new SequenceOperator();
		GPNode[] childs3B  = {new IncreaseDatabaseAThreads(), fourthSequenceOperatorB};
		thirdSequenceOperatorB.children=childs3B;
		GPNode fifthSequenceOperatorB = new SequenceOperator();
		GPNode[] childs4B  = {new DecreaseQuality(), fifthSequenceOperatorB};
		fourthSequenceOperatorB.children=childs4B;
		GPNode sixthSequenceOperatorB = new SequenceOperator();
		GPNode[] childs5B  = {new IncreaseDatabaseBThreads(), sixthSequenceOperatorB};
		fifthSequenceOperatorB.children=childs5B;
		GPNode[] childs6B  = {new IncreaseDatabaseBThreads(), new IncreaseDatabaseBThreads()};
		sixthSequenceOperatorB.children=childs6B;
		
		((GPIndividual)indB).trees[0].child.eval(state, 0, (GPData)sd, new ADFStack(), 
				((GPIndividual)indB), new SingleObjectiveProblem());
		double mixedThreads = sd.calculateStateScore();
		System.out.println("All inrease A Threads: "+allIncreaseThreadA+
				", mixedThreadIncrease:"+mixedThreads);
	}
	
	@Test
	public void tooManyDatabaseAThreadsTest(){
		StateData sd = new StateData();
		sd.initializeData();
		GPIndividual ind = new GPIndividual();
		GPTree[] treeInit = {new GPTree()};
		ind.trees = treeInit;
		GPNode root = new SequenceOperator();
		//TODO - set up the tree correctly and then check if the eval
		//method works correctly
		GPNode secondSequenceOperator = new SequenceOperator();
		GPNode fifthSequenceOperator = new SequenceOperator();
		GPNode[] childs = {secondSequenceOperator, fifthSequenceOperator};
		root.children = childs;
		ind.trees[0].child = root;
		GPNode thirdSequenceOperator = new SequenceOperator();
		GPNode[] childs2  = {thirdSequenceOperator, new IncreaseDatabaseAThreads()};
		secondSequenceOperator.children=childs2;
		GPNode fourthSequenceOperator = new SequenceOperator();
		GPNode[] childs3  = {fourthSequenceOperator, new IncreaseDatabaseAThreads()};
		thirdSequenceOperator.children=childs3;
		GPNode[] childs4  = {new IncreaseDatabaseAThreads(), new IncreaseDatabaseAThreads()};
		fourthSequenceOperator.children=childs4;
		GPNode sixthSequenceOperator = new SequenceOperator();
		GPNode[] childs5  = {sixthSequenceOperator, new IncreaseDatabaseAThreads()};
		fifthSequenceOperator.children=childs5;
		GPNode[] childs6  = {new IncreaseDatabaseAThreads(), new DecreaseQuality()};
		sixthSequenceOperator.children=childs6;
		Evolve ev = new Evolve();
		String[] inputFile = {"-file","selfadaptivesystemsingleobjective.params"};
		ParameterDatabase params = ev.loadParameterDatabase(inputFile);
		EvolutionState state = ev.initialize(params,0);
		((GPIndividual)ind).trees[0].child.eval(state, 0, (GPData)sd, new ADFStack(), 
				((GPIndividual)ind), new SingleObjectiveProblem());
		double seeminglyInvalidSequnceScore = sd.calculateStateScore();
		System.out.println("database a thread count: "+sd.getDatabaseAThreadsCount());
		System.out.println("database a max thread count: "+sd.getMaxDatabaseAThreadsCount());
		System.out.println("Seemingly invalid Sequence Score: "+seeminglyInvalidSequnceScore);
		System.out.println("Reached invalid state: "+sd.getReachedInvalidState());
		assertTrue(sd.getReachedInvalidState());
	}
	
	@Test
	public void testingIfAddingAServerIsHelpfulAfterSequence(){
		StateData sd = new StateData();
		sd.initializeData();
		GPIndividual ind = new GPIndividual();
		GPTree[] treeInit = {new GPTree()};
		ind.trees = treeInit;
		GPNode root = new SequenceOperator();
		//TODO - set up the tree correctly and then check if the eval
		//method works correctly
		GPNode secondSequenceOperator = new SequenceOperator();
		GPNode[] childs = {new IncreaseDatabaseAThreads(), secondSequenceOperator};
		root.children = childs;
		ind.trees[0].child = root;
		GPNode thirdSequenceOperator = new SequenceOperator();
		GPNode[] childs2  = {new IncreaseDatabaseAThreads(), thirdSequenceOperator};
		secondSequenceOperator.children=childs2;
		GPNode fourthSequenceOperator = new SequenceOperator();
		GPNode[] childs3  = {new IncreaseDatabaseAThreads(), fourthSequenceOperator};
		thirdSequenceOperator.children=childs3;
		GPNode fifthSequenceOperator = new SequenceOperator();
		GPNode[] childs4  = {new DecreaseQuality(), fifthSequenceOperator};
		fourthSequenceOperator.children=childs4;
		GPNode sixthSequenceOperator = new SequenceOperator();
		GPNode[] childs5  = {new IncreaseDatabaseBThreads(), sixthSequenceOperator};
		fifthSequenceOperator.children=childs5;
		GPNode[] childs6  = {new IncreaseDatabaseBThreads(), new IncreaseDatabaseBThreads()};
		sixthSequenceOperator.children=childs6;
		Evolve ev = new Evolve();
		String[] inputFile = {"-file","selfadaptivesystemsingleobjective.params"};
		ParameterDatabase params = ev.loadParameterDatabase(inputFile);
		EvolutionState state = ev.initialize(params,0);
		((GPIndividual)ind).trees[0].child.eval(state, 0, (GPData)sd, new ADFStack(), 
				((GPIndividual)ind), new SingleObjectiveProblem());
		double scoreWithoutAddServer = sd.calculateStateScore();
		System.out.println("state without add server: "+sd.toString());

		sd.initializeData();
		GPIndividual indB = new GPIndividual();
		GPTree[] treeInitB = {new GPTree()};
		indB.trees = treeInitB;
		GPNode rootB = new SequenceOperator();
		//TODO - set up the tree correctly and then check if the eval
		//method works correctly
		GPNode secondSequenceOperatorB = new SequenceOperator();
		IncreaseDatabaseAThreads idat1 = new IncreaseDatabaseAThreads();
		GPNode[] noChildrenArray = {};
		idat1.children=noChildrenArray;
		GPNode[] childsB = {idat1, secondSequenceOperatorB};
		rootB.children = childsB;
		indB.trees[0].child = rootB;
		GPNode thirdSequenceOperatorB = new SequenceOperator();
		IncreaseDatabaseAThreads idat2 = new IncreaseDatabaseAThreads();
		idat2.children=noChildrenArray;
		GPNode[] childs2B  = {idat2, thirdSequenceOperatorB};
		secondSequenceOperatorB.children=childs2B;
		GPNode fourthSequenceOperatorB = new SequenceOperator();
		IncreaseDatabaseAThreads idat3 = new IncreaseDatabaseAThreads();
		idat3.children=noChildrenArray;
		GPNode[] childs3B  = {idat3, fourthSequenceOperatorB};
		thirdSequenceOperatorB.children=childs3B;
		GPNode fifthSequenceOperatorB = new SequenceOperator();
		DecreaseQuality dq = new DecreaseQuality();
		dq.children=noChildrenArray;
		GPNode[] childs4B  = {dq, fifthSequenceOperatorB};
		fourthSequenceOperatorB.children=childs4B;
		GPNode sixthSequenceOperatorB = new SequenceOperator();
		IncreaseDatabaseBThreads idbt1 = new IncreaseDatabaseBThreads();
		idbt1.children=noChildrenArray;
		GPNode[] childs5B  = {idbt1, sixthSequenceOperatorB};
		fifthSequenceOperatorB.children=childs5B;
		GPNode seventhSequenceOperatorB = new SequenceOperator();
		IncreaseDatabaseBThreads idbt2 = new IncreaseDatabaseBThreads();
		idbt2.children=noChildrenArray;
		GPNode[] childs6B  = {idbt2, seventhSequenceOperatorB};
		sixthSequenceOperatorB.children=childs6B;
		IncreaseDatabaseBThreads idbt3 = new IncreaseDatabaseBThreads();
		idbt3.children=noChildrenArray;
		AddServerL1 adl1 = new AddServerL1();
		adl1.children=noChildrenArray;
		GPNode[] childs7B  = {idbt3, adl1};
		seventhSequenceOperatorB.children=childs7B;
        indB.printTrees(state, 0);
		((GPIndividual)indB).trees[0].child.eval(state, 0, (GPData)sd, new ADFStack(), 
				((GPIndividual)indB), new SingleObjectiveProblem());
		double scoreWithAddServer = sd.calculateStateScore();
		System.out.println("state with add server: "+sd.toString());
		
		System.out.println("Score with add server: "+scoreWithAddServer
				+", score without add server: "+scoreWithoutAddServer);
		assertTrue(scoreWithAddServer<scoreWithoutAddServer);
	}
	
	@Test
	public void incorrectFailingIfThenElse(){
		StateData sd = new StateData();
		sd.initializeData();
		GPIndividual ind = new GPIndividual();
		GPTree[] treeInit = {new GPTree()};
		ind.trees = treeInit;
		GPNode root = new IfThenElseOperator();
		//TODO - set up the tree correctly and then check if the eval
		//method works correctly
		GPNode secondSequenceOperator = new SequenceOperator();
		GPNode[] childs = {new DecreaseQuality(), new IncreaseDatabaseAThreads(), new IncreaseQuality()};
		root.children = childs;
		ind.trees[0].child = root;
		Evolve ev = new Evolve();
		String[] inputFile = {"-file","selfadaptivesystemmultiobjective.params"};
		ParameterDatabase params = ev.loadParameterDatabase(inputFile);
		EvolutionState state = ev.initialize(params,0);
		((GPIndividual)ind).trees[0].child.eval(state, 0, (GPData)sd, new ADFStack(), 
				((GPIndividual)ind), new SingleObjectiveProblem());
		System.out.println("check the failing if then else "+sd.calculateStateScore());
		//TODO:Go back and make the test pass for different combinations of successes and failures
		//At the moment I'm assuming all the actions succeed.  If any fail than the test
		//will fail
		assertEquals(sd.getReachedInvalidState(),false);
	}
	
	@Test
	public void incorrectFailingDecreaseA(){
		StateData sd = new StateData();
		sd.initializeData();
		GPIndividual ind = new GPIndividual();
		GPTree[] treeInit = {new GPTree()};
		ind.trees = treeInit;
		GPNode root = new DecreaseDatabaseAThreads();
		//TODO - set up the tree correctly and then check if the eval
		//method works correctly

		ind.trees[0].child = root;
		Evolve ev = new Evolve();
		String[] inputFile = {"-file","selfadaptivesystemmultiobjective.params"};
		ParameterDatabase params = ev.loadParameterDatabase(inputFile);
		EvolutionState state = ev.initialize(params,0);
		((GPIndividual)ind).trees[0].child.eval(state, 0, (GPData)sd, new ADFStack(), 
				((GPIndividual)ind), new SingleObjectiveProblem());
		System.out.println("check the failing decrease database A: "+sd.calculateStateScore());
		//TODO:Go back and make the test pass for different combinations of successes and failures
		//At the moment I'm assuming all the actions succeed.  If any fail than the test
		//will fail
		assertEquals(sd.getReachedInvalidState(),false);
	}
	
	@Test
	public void correctEvaluationOfSameSubTrees(){
		StateData sd = new StateData();
		sd.initializeData();
		GPIndividual ind = new GPIndividual();
		GPTree[] treeInit = {new GPTree()};
		ind.trees = treeInit;
		GPNode root = new IfThenElseOperator();
		//TODO - set up the tree correctly and then check if the eval
		//method works correctly
		GPNode secondSequenceOperator = new SequenceOperator();
		GPNode decreaseQualityNode = new DecreaseQuality();
		decreaseQualityNode.children = new GPNode[0];
		GPNode increaseDatabaseAThreadsNode1 = new IncreaseDatabaseAThreads();
		increaseDatabaseAThreadsNode1.children = new GPNode[0];
		GPNode increaseDatabaseAThreadsNode2 = new IncreaseDatabaseAThreads();
		increaseDatabaseAThreadsNode2.children = new GPNode[0];
		GPNode[] childs = {decreaseQualityNode, increaseDatabaseAThreadsNode1, increaseDatabaseAThreadsNode2};
		root.children = childs;
		ind.trees[0].child = root;
		Evolve ev = new Evolve();
		String[] inputFile = {"-file","selfadaptivesystemmultiobjective.params"};
		ParameterDatabase params = ev.loadParameterDatabase(inputFile);
		EvolutionState state = ev.initialize(params,0);

		//TODO:Go back and make the test pass for different combinations of successes and failures
		//At the moment I'm assuming all the actions succeed.  If any fail than the test
		//will fail
		assertEquals(MultiObjectiveProblem.ifsWithSameChildren(treeInit[0]),1);
	}
	
	@Test
	public void testForLoop(){
		StateData sd = new StateData();
		sd.initializeData();
		GPIndividual ind = new GPIndividual();
		GPTree[] treeInit = {new GPTree()};
		ind.trees = treeInit;
		ForOperator forNode = new ForOperator();
		forNode.setForCount(3);
		GPNode root = forNode;
		
		//TODO - set up the tree correctly and then check if the eval
		//method works correctly
		GPNode[] childs = {new AddServerL1()};
		root.children = childs;
		ind.trees[0].child = root;
		Evolve ev = new Evolve();
		String[] inputFile = {"-file","selfadaptivesystemmultiobjective.params"};
		ParameterDatabase params = ev.loadParameterDatabase(inputFile);
		EvolutionState state = ev.initialize(params,0);
		int originalServerCount = sd.getL1ServerCount();
		System.out.println("Server count at start of experiment : "+sd.getL1ServerCount());
		((GPIndividual)ind).trees[0].child.eval(state, 0, (GPData)sd, new ADFStack(), 
				((GPIndividual)ind), new MultiObjectiveProblem());
		System.out.println("Server count after 3 add servers in for loop: "+sd.getL1ServerCount());
		assertEquals(sd.getL1ServerCount(),3+originalServerCount);
		assertEquals(sd.getReachedInvalidState(),false);
		
	}
	
	@Test
	public void testInvalidForLoop(){
		StateData sd = new StateData();
		sd.initializeData();
		GPIndividual ind = new GPIndividual();
		GPTree[] treeInit = {new GPTree()};
		ind.trees = treeInit;
		ForOperator forNode = new ForOperator();
		forNode.setForCount(3);
		GPNode root = forNode;
		
		//TODO - set up the tree correctly and then check if the eval
		//method works correctly
		GPNode[] childs = {new AddServerL1()};
		root.children = childs;
		ind.trees[0].child = root;
		String[] inputFile = {"-file","selfadaptivesystemmultiobjective.params"};
		ParameterDatabase params = Evolve.loadParameterDatabase(inputFile);
		EvolutionState state = Evolve.initialize(params,0);
		int originalServerCount = sd.getL1ServerCount();
		System.out.println("Invalid Server count at start of experiment : "+sd.getL1ServerCount());
		((GPIndividual)ind).trees[0].child.eval(state, 0, (GPData)sd, new ADFStack(), 
				((GPIndividual)ind), new MultiObjectiveProblem());
		System.out.println("Invalid Server count after 3 add servers in for loop: "+sd.getL1ServerCount());
		System.out.println("originalServerCount: "+originalServerCount);
		System.out.println("are they equal?: "+(sd.getL1ServerCount()==3+originalServerCount));
		assertEquals(sd.getL1ServerCount(),3+originalServerCount);
		assertEquals(sd.getReachedInvalidState(),false);
	}
	
	@Test
	public void testForLoopSize(){
		StateData sd = new StateData();
		sd.initializeData();
		GPIndividual ind = new GPIndividual();
		GPTree[] treeInit = {new GPTree()};
		ind.trees = treeInit;
		ForOperator forNode = new ForOperator();
		forNode.setForCount(3);
		GPNode root = forNode;
		
		//TODO - set up the tree correctly and then check if the eval
		//method works correctly
		GPNode addServerNode = new AddServerL1();
		addServerNode.children = new GPNode[0];
		GPNode[] childs = {addServerNode};
		root.children = childs;
		ind.trees[0].child = root;
		Evolve ev = new Evolve();
		String[] inputFile = {"-file","selfadaptivesystemmultiobjective.params"};
		ParameterDatabase params = ev.loadParameterDatabase(inputFile);
		EvolutionState state = ev.initialize(params,0);
		int originalServerCount = sd.getL1ServerCount();
		((GPIndividual)ind).trees[0].child.eval(state, 0, (GPData)sd, new ADFStack(), 
				((GPIndividual)ind), new MultiObjectiveProblem());
		assertEquals(MultiObjectiveProblem.calculatePlanLength(((GPIndividual)ind).trees[0]),4);
	}
}

