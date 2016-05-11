package main.java.tests;

import ec.EvolutionState;
import ec.Evolve;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;
import ec.gp.GPTree;
import ec.util.ParameterDatabase;
import junit.framework.TestCase;
import main.java.actions.operators.ForOperator;
import main.java.actions.operators.IfThenElseOperator;
import main.java.actions.operators.SequenceOperator;
import main.java.main.OmnetStateData;
import main.java.main.SingleObjectiveProblem;
import main.java.omnet.tactics.DecreaseDimmerLevelA;
import main.java.omnet.tactics.DecreaseDimmerLevelB;
import main.java.omnet.tactics.DecreaseDimmerLevelC;
import main.java.omnet.tactics.DecreaseDimmerLevelD;
import main.java.omnet.tactics.DecreaseTrafficLevelA;
import main.java.omnet.tactics.DecreaseTrafficLevelB;
import main.java.omnet.tactics.DecreaseTrafficLevelC;
import main.java.omnet.tactics.DecreaseTrafficLevelD;
import main.java.omnet.tactics.IncreaseDimmerLevelA;
import main.java.omnet.tactics.IncreaseDimmerLevelB;
import main.java.omnet.tactics.IncreaseDimmerLevelC;
import main.java.omnet.tactics.IncreaseDimmerLevelD;
import main.java.omnet.tactics.IncreaseTrafficLevelA;
import main.java.omnet.tactics.IncreaseTrafficLevelB;
import main.java.omnet.tactics.IncreaseTrafficLevelC;
import main.java.omnet.tactics.ShutdownServerA;
import main.java.omnet.tactics.ShutdownServerB;
import main.java.omnet.tactics.ShutdownServerC;
import main.java.omnet.tactics.ShutdownServerD;
import main.java.omnet.tactics.StartNewServerA;
import main.java.omnet.tactics.StartNewServerB;
import main.java.omnet.tactics.StartNewServerC;
import main.java.omnet.tactics.StartNewServerD;


public class TestForLoop extends TestCase  {
	public void testLargeLoop(){
			OmnetStateData sd = new OmnetStateData();
			sd.initializeState();
			GPIndividual ind = new GPIndividual();
			GPTree[] treeInit = {new GPTree()};
			ind.trees = treeInit;
			GPNode root = new ForOperator();
			((ForOperator)root).setForCount(10);
			//TODO - set up the tree correctly and then check if the eval
			//method works correctly
			GPNode[] childs = {new StartNewServerA()};
			root.children = childs;
			ind.trees[0].child = root;
			Evolve ev = new Evolve();
			String[] inputFile = {"-file","selfadaptivesystemsingleobjective.params"};
			ParameterDatabase params = ev.loadParameterDatabase(inputFile);
			EvolutionState state = ev.initialize(params,0);
			((GPIndividual)ind).trees[0].child.eval(state, 0, (GPData)sd, new ADFStack(), 
					((GPIndividual)ind), new SingleObjectiveProblem());
			System.out.println("Final Result: "+sd.getSingleObjectiveScore());
			//TODO:Go back and make the test pass for different combinations of successes and failures
			//At the moment I'm assuming all the actions succeed.  If any fail than the test
			//will fail
			assertTrue(sd.getSingleObjectiveScore()>OmnetStateData.INVALID_PLAN_SCORE);
	}
	
	
	public void testNestedLoops(){
		OmnetStateData sd = new OmnetStateData();
		sd.initializeState();
		GPIndividual ind = new GPIndividual();
		GPTree[] treeInit = {new GPTree()};
		ind.trees = treeInit;
		GPNode root = new ForOperator();
		((ForOperator)root).setForCount(2);
		//TODO - set up the tree correctly and then check if the eval
		//method works correctly
		GPNode nestedFor = new ForOperator();
		((ForOperator)nestedFor).setForCount(3);
		GPNode[] childs = {nestedFor};
		root.children = childs;
		ind.trees[0].child = root;
		GPNode[] childs2  = {new StartNewServerB()};
		nestedFor.children=childs2;
		Evolve ev = new Evolve();
		String[] inputFile = {"-file","selfadaptivesystemsingleobjective.params"};
		ParameterDatabase params = ev.loadParameterDatabase(inputFile);
		EvolutionState state = ev.initialize(params,0);
		((GPIndividual)ind).trees[0].child.eval(state, 0, (GPData)sd, new ADFStack(), 
				((GPIndividual)ind), new SingleObjectiveProblem());
		System.out.println("Final Result: "+sd.getSingleObjectiveScore());
		//TODO:Go back and make the test pass for different combinations of successes and failures
		//At the moment I'm assuming all the actions succeed.  If any fail than the test
		//will fail
		assertTrue(sd.getSingleObjectiveScore()>OmnetStateData.INVALID_PLAN_SCORE);
	}
	
	public void testSequenceOperatorInIf(){
		OmnetStateData sd = new OmnetStateData();
		sd.initializeState();
		GPIndividual ind = new GPIndividual();
		GPTree[] treeInit = {new GPTree()};
		ind.trees = treeInit;
		GPNode root = new IfThenElseOperator();
		GPNode node1 = new SequenceOperator();
		GPNode[] node1Children={new StartNewServerA(),new StartNewServerA()};
		node1.children=node1Children;
		GPNode[] childrenOfRoot = new GPNode[3];
		childrenOfRoot[0]=node1;
		childrenOfRoot[1]=new StartNewServerA();
		childrenOfRoot[2]=new StartNewServerA();
		root.children=childrenOfRoot;
		ind.trees[0].child = root;
		Evolve ev = new Evolve();
		String[] inputFile = {"-file","selfadaptivesystemsingleobjective.params"};
		ParameterDatabase params = ev.loadParameterDatabase(inputFile);
		EvolutionState state = ev.initialize(params,0);
		((GPIndividual)ind).trees[0].child.eval(state, 0, (GPData)sd, new ADFStack(), 
				((GPIndividual)ind), new SingleObjectiveProblem());
		System.out.println("Final Result: "+sd.getSingleObjectiveScore());
		//TODO:Go back and make the test pass for different combinations of successes and failures
		//At the moment I'm assuming all the actions succeed.  If any fail than the test
		//will fail
		assertTrue(sd.getSingleObjectiveScore()>OmnetStateData.INVALID_PLAN_SCORE);
	}
	
	public void testTripleForLoop(){
		OmnetStateData sd = new OmnetStateData();
		sd.initializeState();
		GPIndividual ind = new GPIndividual();
		GPTree[] treeInit = {new GPTree()};
		ind.trees = treeInit;
		GPNode root = new IfThenElseOperator();
		ind.trees[0].child=root;
		GPNode node1 = new ForOperator();
		((ForOperator)node1).setForCount(3);
		//TODO - set up the tree correctly and then check if the eval
		//method works correctly
		GPNode node2 = new ForOperator();
		((ForOperator)node2).setForCount(3);
		GPNode node3 = new ForOperator();
		((ForOperator)node3).setForCount(10);
		GPNode node4 = new SequenceOperator();
		GPNode[] childrenOf4 = {new ShutdownServerD(),new IncreaseDimmerLevelC()};
		node4.children = childrenOf4;
		GPNode[] childrenOfRoot = new GPNode[3];
		childrenOfRoot[0]= new IncreaseDimmerLevelD();
		childrenOfRoot[1]= node1;
		childrenOfRoot[2]= new  IncreaseDimmerLevelD();
		root.children= childrenOfRoot;
		GPNode[] childrenOfNode1 = new GPNode[1];
		childrenOfNode1[0]=node2;
		node1.children=childrenOfNode1;
		GPNode[] childrenOfNode2 = new GPNode[1];
		childrenOfNode2[0]=node3;
		node2.children=childrenOfNode2;
		GPNode[] childrenOfNode3 = new GPNode[1];
		childrenOfNode3[0]=node4;
		node3.children=childrenOfNode3;
		Evolve ev = new Evolve();
		String[] inputFile = {"-file","selfadaptivesystemsingleobjective.params"};
		ParameterDatabase params = ev.loadParameterDatabase(inputFile);
		EvolutionState state = ev.initialize(params,0);
		((GPIndividual)ind).trees[0].child.eval(state, 0, (GPData)sd, new ADFStack(), 
				((GPIndividual)ind), new SingleObjectiveProblem());
		System.out.println("Final Result: "+sd.getSingleObjectiveScore());
		//TODO:Go back and make the test pass for different combinations of successes and failures
		//At the moment I'm assuming all the actions succeed.  If any fail than the test
		//will fail
		assertFalse(sd.areAllPathsValid());
	}
	
	public void testLongerIncorrectPlan(){
		OmnetStateData sd = new OmnetStateData();
		sd.initializeState();
		GPIndividual ind = new GPIndividual();
		GPTree[] treeInit = {new GPTree()};
		ind.trees = treeInit;
		GPNode node1 = new SequenceOperator();
		GPNode node2 = new IfThenElseOperator();
		GPNode node3 = new ForOperator();
		((ForOperator)node3).setForCount(3);
		GPNode node4 = new SequenceOperator();
		GPNode node5 = new ForOperator();
		((ForOperator)node5).setForCount(3);
		GPNode node6 = new ForOperator();
		((ForOperator)node6).setForCount(10);
		GPNode node7 = new SequenceOperator();
		GPNode[] node7Children = {new ShutdownServerD(), new IncreaseDimmerLevelC()};
		node7.children=node7Children;
		GPNode[] childrenOfNode6 = new GPNode[1];
		childrenOfNode6[0]=node7;
		node6.children=childrenOfNode6;
		GPNode[] childrenOfNode5 = new GPNode[1];
		childrenOfNode5[0]=node6;
		node5.children=childrenOfNode5;
		GPNode node8 = new IfThenElseOperator();
		GPNode node9 = new SequenceOperator();
		GPNode node10 = new SequenceOperator();
		GPNode[] node10Children = {new IncreaseDimmerLevelD(), new StartNewServerD()};
		node10.children=node10Children;
		GPNode[] childrenOfNode9 = new GPNode[2];
		childrenOfNode9[0]=new IncreaseDimmerLevelB();
		childrenOfNode9[1]=node10;
		node9.children=childrenOfNode9;
		GPNode node11 = new ForOperator();
		((ForOperator)node11).setForCount(2);
		GPNode[] node11Children = {new StartNewServerA()};
		node11.children=node11Children;
		GPNode node12 = new IfThenElseOperator();
		GPNode node13 = new ForOperator();
		((ForOperator)node13).setForCount(3);
		GPNode node14 = new SequenceOperator();
		GPNode node15 = new IfThenElseOperator();
		GPNode node16 = new SequenceOperator();
		GPNode[] node16Children = {new IncreaseDimmerLevelA(), new ShutdownServerD()};
		node16.children=node16Children;
		GPNode[] childrenOfNode15 = new GPNode[3];
		childrenOfNode15[0]=new IncreaseDimmerLevelA();
		childrenOfNode15[1]=node16;
		childrenOfNode15[2]=new DecreaseDimmerLevelA();
		node15.children=childrenOfNode15;
		GPNode[] childrenOfNode14 = new GPNode[2];
		childrenOfNode14[0]=new ShutdownServerA();
		childrenOfNode14[1]=node15;
		node14.children=childrenOfNode14;
		GPNode[] childrenOfNode13 = new GPNode[1];
		childrenOfNode13[0]=node14;
		node13.children=childrenOfNode13;
		GPNode[] childrenOfNode12 = new GPNode[3];
		childrenOfNode12[0]=node13;
		childrenOfNode12[1]=new IncreaseDimmerLevelA();
		childrenOfNode12[2]=new IncreaseDimmerLevelB();
		node12.children=childrenOfNode12;
		GPNode[] childrenOfNode8 = new GPNode[3];
		childrenOfNode8[0]=node9;
		childrenOfNode8[1]=node11;
		childrenOfNode8[2]=node12;
		node8.children=childrenOfNode8;
		GPNode[] childrenOfNode4 = new GPNode[2];
		childrenOfNode4[0]=node5;
		childrenOfNode4[1]=node8;
		node4.children=childrenOfNode4;
		GPNode[] childrenOfNode3 = new GPNode[1];
		childrenOfNode3[0]=node4;
		node3.children=childrenOfNode3;
		GPNode node17 = new ForOperator();
		((ForOperator)node17).setForCount(1);
		GPNode[] node17Children = {new IncreaseDimmerLevelA()};
		node17.children=node17Children;
		GPNode[] childrenOfNode2 = new GPNode[3];
		childrenOfNode2[0]=new IncreaseDimmerLevelD();
		childrenOfNode2[1]=node3;
		childrenOfNode2[2]=node17;
		node2.children=childrenOfNode2;
		GPNode node18 = new SequenceOperator();
		GPNode[] node18Children = {new DecreaseDimmerLevelB(), new StartNewServerC()};
		node18.children=node18Children;
		GPNode[] childrenOfNode1 = new GPNode[2];
		childrenOfNode1[0]=node2;
		childrenOfNode1[1]=node18;
		node1.children=childrenOfNode1;
		ind.trees[0].child=node1;

		Evolve ev = new Evolve();
		String[] inputFile = {"-file","selfadaptivesystemsingleobjective.params"};
		ParameterDatabase params = ev.loadParameterDatabase(inputFile);
		EvolutionState state = ev.initialize(params,0);
		((GPIndividual)ind).trees[0].child.eval(state, 0, (GPData)sd, new ADFStack(),
		((GPIndividual)ind), new SingleObjectiveProblem());
		System.out.println("Final Result for longer plan: "+sd.getSingleObjectiveScore());
		assertTrue(sd.getSingleObjectiveScore()>OmnetStateData.INVALID_PLAN_SCORE);

	}
	
	
	
		public void testLargerNestedLoop(){
			OmnetStateData sd = new OmnetStateData();
			sd.initializeState();
			GPIndividual ind = new GPIndividual();
			GPTree[] treeInit = {new GPTree()};
			ind.trees = treeInit;
			GPNode root = new SequenceOperator();
			//TODO - set up the tree correctly and then check if the eval
			//method works correctly
			GPNode node2 = new SequenceOperator();
			GPNode node3 = new StartNewServerC();
			GPNode node4 = new ShutdownServerA();
			GPNode[] childrenOf2 = {node3,node4};
			node2.children = childrenOf2;
			GPNode node5 = new SequenceOperator();
			GPNode[] childrenOfRoot = {node2,node5};
			root.children = childrenOfRoot;
			GPNode node6 = new IfThenElseOperator();
			GPNode node7 = new StartNewServerB();
			GPNode node8 = new StartNewServerC();
			GPNode node9 = new StartNewServerC();
			GPNode[] childrenOf6 = {node7,node8, node9};
			node6.children=childrenOf6;
			GPNode node10 = new SequenceOperator();
			GPNode[] childrenOf5 = {node6,node10};
			node5.children=childrenOf5;
			GPNode node11 = new SequenceOperator();
			GPNode node12 = new StartNewServerB();
			GPNode node13 = new IfThenElseOperator();
			GPNode[] childrenOf11 = {node12,node13};
			node11.children=childrenOf11;
			GPNode node14 = new ShutdownServerD();
			GPNode node15 = new StartNewServerB();
			GPNode node16 = new ForOperator();
			((ForOperator)node16).setForCount(2);
			GPNode[] childrenOf13 = {node14,node15,node16};
			node13.children=childrenOf13;
			GPNode node17 = new SequenceOperator();
			GPNode[] childrenOf16 = {node17};
			node16.children = childrenOf16;
			GPNode node18 = new ForOperator();
			((ForOperator)node18).setForCount(2);
			GPNode node19 = new StartNewServerB();
			GPNode[] childrenOf18 = {node19};
			node18.children=childrenOf18;
			GPNode node20 = new ForOperator();
			((ForOperator)node20).setForCount(2);
			GPNode node21 = new StartNewServerB();
			GPNode[] childrenOf20 = {node21};
			node20.children = childrenOf20;
			GPNode[] childrenOf17  = {node18,node20};
			node17.children = childrenOf17;
			GPNode node22 = new StartNewServerB();
			GPNode[] childrenOf10 = {node11,node22};
			node10.children = childrenOf10;
			ind.trees[0].child = root;
			Evolve ev = new Evolve();
			String[] inputFile = {"-file","selfadaptivesystemsingleobjective.params"};
			ParameterDatabase params = ev.loadParameterDatabase(inputFile);
			EvolutionState state = ev.initialize(params,0);
			((GPIndividual)ind).trees[0].child.eval(state, 0, (GPData)sd, new ADFStack(), 
					((GPIndividual)ind), new SingleObjectiveProblem());
			System.out.println("Final Result: "+sd.getSingleObjectiveScore());
			//TODO:Go back and make the test pass for different combinations of successes and failures
			//At the moment I'm assuming all the actions succeed.  If any fail than the test
			//will fail
			assertFalse(sd.areAllPathsValid());
		}
		
		public void test2ForIfThen(){
			OmnetStateData sd = new OmnetStateData();
			sd.initializeState();
			GPIndividual ind = new GPIndividual();
			GPTree[] treeInit = {new GPTree()};
			ind.trees = treeInit;
			GPNode node1 = new ForOperator();
			((ForOperator)node1).setForCount(4);
			GPNode node2 = new ForOperator();
			((ForOperator)node2).setForCount(4);
			GPNode node3 = new IfThenElseOperator();
			GPNode[] node3Children = {new StartNewServerD(), new DecreaseDimmerLevelA(), new IncreaseDimmerLevelC()};
			node3.children=node3Children;
			GPNode[] childrenOfNode2 = new GPNode[1];
			childrenOfNode2[0]=node3;
			node2.children=childrenOfNode2;
			GPNode[] childrenOfNode1 = new GPNode[1];
			childrenOfNode1[0]=node2;
			node1.children=childrenOfNode1;
			ind.trees[0].child=node1;

			Evolve ev = new Evolve();
			String[] inputFile = {"-file","selfadaptivesystemsingleobjective.params"};
			ParameterDatabase params = ev.loadParameterDatabase(inputFile);
			EvolutionState state = ev.initialize(params,0);
			((GPIndividual)ind).trees[0].child.eval(state, 0, (GPData)sd, new ADFStack(),
			((GPIndividual)ind), new SingleObjectiveProblem());
			System.out.println("Final Result: "+sd.getSingleObjectiveScore());
			assertTrue(sd.getSingleObjectiveScore()>OmnetStateData.INVALID_PLAN_SCORE);

		}
		
		public void testPassingPlan(){
			OmnetStateData sd = new OmnetStateData();
			sd.initializeState();
			GPIndividual ind = new GPIndividual();
			GPTree[] treeInit = {new GPTree()};
			ind.trees = treeInit;
			GPNode node1 = new SequenceOperator();
			GPNode[] node1Children = {new IncreaseDimmerLevelD(), new ShutdownServerB()};
			node1.children=node1Children;
			ind.trees[0].child=node1;

			Evolve ev = new Evolve();
			String[] inputFile = {"-file","selfadaptivesystemsingleobjective.params"};
			ParameterDatabase params = ev.loadParameterDatabase(inputFile);
			EvolutionState state = ev.initialize(params,0);
			((GPIndividual)ind).trees[0].child.eval(state, 0, (GPData)sd, new ADFStack(),
			((GPIndividual)ind), new SingleObjectiveProblem());
			System.out.println("Final Result: "+sd.getSingleObjectiveScore());
			assertFalse(sd.getSingleObjectiveScore()>OmnetStateData.INVALID_PLAN_SCORE);
		}
	
		public void testLastPassingPlan(){
			OmnetStateData sd = new OmnetStateData();
			sd.initializeState();
			GPIndividual ind = new GPIndividual();
			GPTree[] treeInit = {new GPTree()};
			ind.trees = treeInit;
			GPNode node1 = new SequenceOperator();
			GPNode node2 = new ForOperator();
			((ForOperator)node2).setForCount(4);
			GPNode node3 = new ForOperator();
			((ForOperator)node3).setForCount(4);
			GPNode node4 = new IfThenElseOperator();
			GPNode[] node4Children = {new StartNewServerD(), new DecreaseDimmerLevelA(), new IncreaseDimmerLevelC()};
			node4.children=node4Children;
			GPNode[] childrenOfNode3 = new GPNode[1];
			childrenOfNode3[0]=node4;
			node3.children=childrenOfNode3;
			GPNode[] childrenOfNode2 = new GPNode[1];
			childrenOfNode2[0]=node3;
			node2.children=childrenOfNode2;
			GPNode node5 = new ForOperator();
			((ForOperator)node5).setForCount(1);
			GPNode node6 = new SequenceOperator();
			GPNode node7 = new IfThenElseOperator();
			GPNode node8 = new SequenceOperator();
			GPNode[] node8Children = {new DecreaseDimmerLevelD(), new ShutdownServerB()};
			node8.children=node8Children;
			GPNode node9 = new IfThenElseOperator();
			GPNode[] node9Children = {new DecreaseDimmerLevelB(), new StartNewServerA(), new DecreaseTrafficLevelA()};
			node9.children=node9Children;
			GPNode node10 = new SequenceOperator();
			GPNode[] node10Children = {new IncreaseDimmerLevelC(), new StartNewServerB()};
			node10.children=node10Children;
			GPNode[] childrenOfNode7 = new GPNode[3];
			childrenOfNode7[0]=node8;
			childrenOfNode7[1]=node9;
			childrenOfNode7[2]=node10;
			node7.children=childrenOfNode7;
			GPNode[] childrenOfNode6 = new GPNode[2];
			childrenOfNode6[0]=node7;
			childrenOfNode6[1]=new DecreaseTrafficLevelA();
			node6.children=childrenOfNode6;
			GPNode[] childrenOfNode5 = new GPNode[1];
			childrenOfNode5[0]=node6;
			node5.children=childrenOfNode5;
			GPNode[] childrenOfNode1 = new GPNode[2];
			childrenOfNode1[0]=node2;
			childrenOfNode1[1]=node5;
			node1.children=childrenOfNode1;
			ind.trees[0].child=node1;

			Evolve ev = new Evolve();
			String[] inputFile = {"-file","selfadaptivesystemsingleobjective.params"};
			ParameterDatabase params = ev.loadParameterDatabase(inputFile);
			EvolutionState state = ev.initialize(params,0);
			((GPIndividual)ind).trees[0].child.eval(state, 0, (GPData)sd, new ADFStack(),
			((GPIndividual)ind), new SingleObjectiveProblem());
			System.out.println("Final Result last: "+sd.getSingleObjectiveScore());
			assertTrue(sd.getSingleObjectiveScore()>OmnetStateData.INVALID_PLAN_SCORE);
		}
		
		public void anotherShouldPassPlan(){
			OmnetStateData sd = new OmnetStateData();
			sd.initializeState();
			GPIndividual ind = new GPIndividual();
			GPTree[] treeInit = {new GPTree()};
			ind.trees = treeInit;
			GPNode node1 = new SequenceOperator();
			GPNode node2 = new IfThenElseOperator();
			GPNode node3 = new ForOperator();
			((ForOperator)node3).setForCount(3);
			GPNode[] node3Children = {new StartNewServerD()};
			node3.children=node3Children;
			GPNode[] childrenOfNode2 = new GPNode[3];
			childrenOfNode2[0]=new IncreaseDimmerLevelD();
			childrenOfNode2[1]=node3;
			childrenOfNode2[2]=new StartNewServerD();
			node2.children=childrenOfNode2;
			GPNode[] childrenOfNode1 = new GPNode[2];
			childrenOfNode1[0]=new StartNewServerB();
			childrenOfNode1[1]=node2;
			node1.children=childrenOfNode1;
			ind.trees[0].child=node1;

			Evolve ev = new Evolve();
			String[] inputFile = {"-file","selfadaptivesystemsingleobjective.params"};
			ParameterDatabase params = ev.loadParameterDatabase(inputFile);
			EvolutionState state = ev.initialize(params,0);
			((GPIndividual)ind).trees[0].child.eval(state, 0, (GPData)sd, new ADFStack(),
			((GPIndividual)ind), new SingleObjectiveProblem());
			System.out.println("Final Result: "+sd.getSingleObjectiveScore());
			assertFalse(sd.getSingleObjectiveScore()>OmnetStateData.INVALID_PLAN_SCORE);

		}
		
		public void testInvalidIfThen() {
		OmnetStateData sd = new OmnetStateData();
		sd.initializeState();
		GPIndividual ind = new GPIndividual();
		GPTree[] treeInit = {new GPTree()};
		ind.trees = treeInit;
		GPNode node1 = new IfThenElseOperator();
		GPNode node2 = new IfThenElseOperator();
		GPNode[] node2Children = {new DecreaseDimmerLevelC(), new ShutdownServerC(), new ShutdownServerD()};
		node2.children=node2Children;
		GPNode node3 = new ForOperator();
		((ForOperator)node3).setForCount(5);
		GPNode[] node3Children = {new IncreaseDimmerLevelC()};
		node3.children=node3Children;
		GPNode node4 = new ForOperator();
		((ForOperator)node4).setForCount(4);
		GPNode[] node4Children = {new IncreaseTrafficLevelA()};
		node4.children=node4Children;
		GPNode[] childrenOfNode1 = new GPNode[3];
		childrenOfNode1[0]=node2;
		childrenOfNode1[1]=node3;
		childrenOfNode1[2]=node4;
		node1.children=childrenOfNode1;
		ind.trees[0].child=node1;

		Evolve ev = new Evolve();
		String[] inputFile = {"-file","selfadaptivesystemsingleobjective.params"};
		ParameterDatabase params = ev.loadParameterDatabase(inputFile);
		EvolutionState state = ev.initialize(params,0);
		((GPIndividual)ind).trees[0].child.eval(state, 0, (GPData)sd, new ADFStack(),
		((GPIndividual)ind), new SingleObjectiveProblem());
		System.out.println("Final Result: "+sd.getSingleObjectiveScore());
		assertTrue(sd.getSingleObjectiveScore()>OmnetStateData.INVALID_PLAN_SCORE);
		}
		
		public void testSimplePlan() {
			//(Loop for 5 times ShutdownServerB)

			OmnetStateData sd = new OmnetStateData();
			sd.initializeState();
			GPIndividual ind = new GPIndividual();
			GPTree[] treeInit = {new GPTree()};
			ind.trees = treeInit;
			GPNode node1 = new ForOperator();
			((ForOperator)node1).setForCount(5);
			GPNode[] node1Children = {new ShutdownServerB()};
			node1.children=node1Children;
			ind.trees[0].child=node1;

			Evolve ev = new Evolve();
			String[] inputFile = {"-file","selfadaptivesystemsingleobjective.params"};
			ParameterDatabase params = ev.loadParameterDatabase(inputFile);
			EvolutionState state = ev.initialize(params,0);
			((GPIndividual)ind).trees[0].child.eval(state, 0, (GPData)sd, new ADFStack(),
			((GPIndividual)ind), new SingleObjectiveProblem());
			System.out.println("Final Result: "+sd.getSingleObjectiveScore());
			assertTrue(sd.getSingleObjectiveScore()>OmnetStateData.INVALID_PLAN_SCORE);
		}
		
		public void testShouldNotBePerfectPlan(){
			//Original Plan: (Loop for 5 times (Loop for 4 times (if then: (if then: (if then: IncreaseDimmerLevelD ShutdownServerC IncreaseTrafficLevelB) (; (if then: DecreaseTrafficLevelD IncreaseDimmerLevelA IncreaseDimmerLevelB) (if then: StartNewServerC IncreaseDimmerLevelD StartNewServerD)) (Loop for 4 times IncreaseDimmerLevelD)) (Loop for 3 times (Loop for 4 times (Loop for 4 times (if then: (if then: (; DecreaseDimmerLevelC DecreaseTrafficLevelB) (if then: ShutdownServerC ShutdownServerB IncreaseDimmerLevelC) (Loop for 3 times StartNewServerD)) (; (if then: DecreaseTrafficLevelA ShutdownServerC StartNewServerC) (Loop for 5 times IncreaseDimmerLevelB))(; (if then: DecreaseTrafficLevelC IncreaseDimmerLevelB ShutdownServerA) (Loop for 2 times IncreaseTrafficLevelA)))))) (if then: (if then: IncreaseTrafficLevelA StartNewServerD ShutdownServerB) (Loop for 1 times IncreaseDimmerLevelC) (; ShutdownServerB IncreaseTrafficLevelC)))))

			OmnetStateData sd = new OmnetStateData();
			sd.initializeState();
			GPIndividual ind = new GPIndividual();
			GPTree[] treeInit = {new GPTree()};
			ind.trees = treeInit;
			GPNode node1 = new ForOperator();
			((ForOperator)node1).setForCount(5);
			GPNode node2 = new ForOperator();
			((ForOperator)node2).setForCount(4);
			GPNode node3 = new IfThenElseOperator();
			GPNode node4 = new IfThenElseOperator();
			GPNode node5 = new IfThenElseOperator();
			GPNode[] node5Children = {new IncreaseDimmerLevelD(), new ShutdownServerC(), new IncreaseTrafficLevelB()};
			node5.children=node5Children;
			GPNode node6 = new SequenceOperator();
			GPNode node7 = new IfThenElseOperator();
			GPNode[] node7Children = {new DecreaseTrafficLevelD(), new IncreaseDimmerLevelA(), new IncreaseDimmerLevelB()};
			node7.children=node7Children;
			GPNode node8 = new IfThenElseOperator();
			GPNode[] node8Children = {new StartNewServerC(), new IncreaseDimmerLevelD(), new StartNewServerD()};
			node8.children=node8Children;
			GPNode[] childrenOfNode6 = new GPNode[2];
			childrenOfNode6[0]=node7;
			childrenOfNode6[1]=node8;
			node6.children=childrenOfNode6;
			GPNode node9 = new ForOperator();
			((ForOperator)node9).setForCount(4);
			GPNode[] node9Children = {new IncreaseDimmerLevelD()};
			node9.children=node9Children;
			GPNode[] childrenOfNode4 = new GPNode[3];
			childrenOfNode4[0]=node5;
			childrenOfNode4[1]=node6;
			childrenOfNode4[2]=node9;
			node4.children=childrenOfNode4;
			GPNode node10 = new ForOperator();
			((ForOperator)node10).setForCount(3);
			GPNode node11 = new ForOperator();
			((ForOperator)node11).setForCount(4);
			GPNode node12 = new ForOperator();
			((ForOperator)node12).setForCount(4);
			GPNode node13 = new IfThenElseOperator();
			GPNode node14 = new IfThenElseOperator();
			GPNode node15 = new SequenceOperator();
			GPNode[] node15Children = {new DecreaseDimmerLevelC(), new DecreaseTrafficLevelB()};
			node15.children=node15Children;
			GPNode node16 = new IfThenElseOperator();
			GPNode[] node16Children = {new ShutdownServerC(), new ShutdownServerB(), new IncreaseDimmerLevelC()};
			node16.children=node16Children;
			GPNode node17 = new ForOperator();
			((ForOperator)node17).setForCount(3);
			GPNode[] node17Children = {new StartNewServerD()};
			node17.children=node17Children;
			GPNode[] childrenOfNode14 = new GPNode[3];
			childrenOfNode14[0]=node15;
			childrenOfNode14[1]=node16;
			childrenOfNode14[2]=node17;
			node14.children=childrenOfNode14;
			GPNode node18 = new SequenceOperator();
			GPNode node19 = new IfThenElseOperator();
			GPNode[] node19Children = {new DecreaseTrafficLevelA(), new ShutdownServerC(), new StartNewServerC()};
			node19.children=node19Children;
			GPNode node20 = new ForOperator();
			((ForOperator)node20).setForCount(5);
			GPNode[] node20Children = {new IncreaseDimmerLevelB()};
			node20.children=node20Children;
			GPNode[] childrenOfNode18 = new GPNode[2];
			childrenOfNode18[0]=node19;
			childrenOfNode18[1]=node20;
			node18.children=childrenOfNode18;
			GPNode node21 = new SequenceOperator();
			GPNode node22 = new IfThenElseOperator();
			GPNode[] node22Children = {new DecreaseTrafficLevelC(), new IncreaseDimmerLevelB(), new ShutdownServerA()};
			node22.children=node22Children;
			GPNode node23 = new ForOperator();
			((ForOperator)node23).setForCount(2);
			GPNode[] node23Children = {new IncreaseTrafficLevelA()};
			node23.children=node23Children;
			GPNode[] childrenOfNode21 = new GPNode[2];
			childrenOfNode21[0]=node22;
			childrenOfNode21[1]=node23;
			node21.children=childrenOfNode21;
			GPNode[] childrenOfNode13 = new GPNode[3];
			childrenOfNode13[0]=node14;
			childrenOfNode13[1]=node18;
			childrenOfNode13[2]=node21;
			node13.children=childrenOfNode13;
			GPNode[] childrenOfNode12 = new GPNode[1];
			childrenOfNode12[0]=node13;
			node12.children=childrenOfNode12;
			GPNode[] childrenOfNode11 = new GPNode[1];
			childrenOfNode11[0]=node12;
			node11.children=childrenOfNode11;
			GPNode[] childrenOfNode10 = new GPNode[1];
			childrenOfNode10[0]=node11;
			node10.children=childrenOfNode10;
			GPNode node24 = new IfThenElseOperator();
			GPNode node25 = new IfThenElseOperator();
			GPNode[] node25Children = {new IncreaseTrafficLevelA(), new StartNewServerD(), new ShutdownServerB()};
			node25.children=node25Children;
			GPNode node26 = new ForOperator();
			((ForOperator)node26).setForCount(1);
			GPNode[] node26Children = {new IncreaseDimmerLevelC()};
			node26.children=node26Children;
			GPNode node27 = new SequenceOperator();
			GPNode[] node27Children = {new ShutdownServerB(), new IncreaseTrafficLevelC()};
			node27.children=node27Children;
			GPNode[] childrenOfNode24 = new GPNode[3];
			childrenOfNode24[0]=node25;
			childrenOfNode24[1]=node26;
			childrenOfNode24[2]=node27;
			node24.children=childrenOfNode24;
			GPNode[] childrenOfNode3 = new GPNode[3];
			childrenOfNode3[0]=node4;
			childrenOfNode3[1]=node10;
			childrenOfNode3[2]=node24;
			node3.children=childrenOfNode3;
			GPNode[] childrenOfNode2 = new GPNode[1];
			childrenOfNode2[0]=node3;
			node2.children=childrenOfNode2;
			GPNode[] childrenOfNode1 = new GPNode[1];
			childrenOfNode1[0]=node2;
			node1.children=childrenOfNode1;
			ind.trees[0].child=node1;

			Evolve ev = new Evolve();
			String[] inputFile = {"-file","selfadaptivesystemsingleobjective.params"};
			ParameterDatabase params = ev.loadParameterDatabase(inputFile);
			EvolutionState state = ev.initialize(params,0);
			((GPIndividual)ind).trees[0].child.eval(state, 0, (GPData)sd, new ADFStack(),
			((GPIndividual)ind), new SingleObjectiveProblem());
			System.out.println("Final Result: "+sd.getSingleObjectiveScore());
			assertTrue(sd.getSingleObjectiveScore()>OmnetStateData.INVALID_PLAN_SCORE);
		}
		
		
		public void testInitialStateScore(){
			OmnetStateData sd = new OmnetStateData();
			sd.initializeState();
			System.out.println("Initial state score: "+sd.getSingleObjectiveScore());
			assertTrue(sd.getSingleObjectiveScore()==3547.3);
		}
		
		public void testScoreAddingServerA(){
			OmnetStateData sd = new OmnetStateData();
			sd.initializeState();
			GPIndividual ind = new GPIndividual();
			GPTree[] treeInit = {new GPTree()};
			ind.trees = treeInit;
			GPNode node1 = new StartNewServerA();
			ind.trees[0].child=node1;

			Evolve ev = new Evolve();
			String[] inputFile = {"-file","selfadaptivesystemsingleobjective.params"};
			ParameterDatabase params = ev.loadParameterDatabase(inputFile);
			EvolutionState state = ev.initialize(params,0);
			((GPIndividual)ind).trees[0].child.eval(state, 0, (GPData)sd, new ADFStack(),
			((GPIndividual)ind), new SingleObjectiveProblem());
			System.out.println("Result after adding server A: "+sd.getSingleObjectiveScore());
			assertTrue(sd.getSingleObjectiveScore()==4046.8);
		}
		
		public void testScoreRemovingServerA(){
			OmnetStateData sd = new OmnetStateData();
			sd.initializeState();
			GPIndividual ind = new GPIndividual();
			GPTree[] treeInit = {new GPTree()};
			ind.trees = treeInit;
			GPNode node1 = new ShutdownServerA();
			ind.trees[0].child=node1;

			Evolve ev = new Evolve();
			String[] inputFile = {"-file","selfadaptivesystemsingleobjective.params"};
			ParameterDatabase params = ev.loadParameterDatabase(inputFile);
			EvolutionState state = ev.initialize(params,0);
			((GPIndividual)ind).trees[0].child.eval(state, 0, (GPData)sd, new ADFStack(),
			((GPIndividual)ind), new SingleObjectiveProblem());
			System.out.println("Result after removing server A: "+sd.getSingleObjectiveScore());
			assertTrue(sd.getSingleObjectiveScore()==3047.8);
		}
		
		public void testIfElseScore(){
			OmnetStateData sd = new OmnetStateData();
			sd.initializeState();
			GPIndividual ind = new GPIndividual();
			GPTree[] treeInit = {new GPTree()};
			ind.trees = treeInit;
			GPNode node1 = new IfThenElseOperator();
			GPNode[] childrenOfNode1 = {new StartNewServerA(),new StartNewServerA(),new ShutdownServerA()};
			node1.children=childrenOfNode1;
			ind.trees[0].child=node1;

			Evolve ev = new Evolve();
			String[] inputFile = {"-file","selfadaptivesystemsingleobjective.params"};
			ParameterDatabase params = ev.loadParameterDatabase(inputFile);
			EvolutionState state = ev.initialize(params,0);
			((GPIndividual)ind).trees[0].child.eval(state, 0, (GPData)sd, new ADFStack(),
			((GPIndividual)ind), new SingleObjectiveProblem());
			System.out.println("Result after basic if statement: "+sd.getSingleObjectiveScore());
			assertTrue(sd.getSingleObjectiveScore()==4396.45);
		}
		
		public void testPlanWorseThanInitial(){
			OmnetStateData sd = new OmnetStateData();
			sd.initializeState();
			double initialScore=sd.getSingleObjectiveScore();
			GPIndividual ind = new GPIndividual();
			GPTree[] treeInit = {new GPTree()};
			ind.trees = treeInit;
			//Original Plan: (; IncreaseDimmerLevelD (; IncreaseDimmerLevelD 
			//(; IncreaseDimmerLevelD (; IncreaseDimmerLevelD 
			//(; ShutdownServerA (; ShutdownServerB ShutdownServerC))))))
			GPNode node1 = new SequenceOperator();
			GPNode node2 = new SequenceOperator();
			GPNode node3 = new SequenceOperator();
			GPNode node4 = new SequenceOperator();
			GPNode node5 = new SequenceOperator();
			GPNode node6 = new SequenceOperator();
			GPNode[] node6Children = {new ShutdownServerB(), new ShutdownServerC()};
			node6.children=node6Children;
			GPNode[] childrenOfNode5 = new GPNode[2];
			childrenOfNode5[0]=new ShutdownServerA();
			childrenOfNode5[1]=node6;
			node5.children=childrenOfNode5;
			GPNode[] childrenOfNode4 = new GPNode[2];
			childrenOfNode4[0]=new IncreaseDimmerLevelD();
			childrenOfNode4[1]=node5;
			node4.children=childrenOfNode4;
			GPNode[] childrenOfNode3 = new GPNode[2];
			childrenOfNode3[0]=new IncreaseDimmerLevelD();
			childrenOfNode3[1]=node4;
			node3.children=childrenOfNode3;
			GPNode[] childrenOfNode2 = new GPNode[2];
			childrenOfNode2[0]=new IncreaseDimmerLevelD();
			childrenOfNode2[1]=node3;
			node2.children=childrenOfNode2;
			GPNode[] childrenOfNode1 = new GPNode[2];
			childrenOfNode1[0]=new IncreaseDimmerLevelD();
			childrenOfNode1[1]=node2;
			node1.children=childrenOfNode1;
			ind.trees[0].child=node1;

			Evolve ev = new Evolve();
			String[] inputFile = {"-file","selfadaptivesystemsingleobjective.params"};
			ParameterDatabase params = ev.loadParameterDatabase(inputFile);
			EvolutionState state = ev.initialize(params,0);
			((GPIndividual)ind).trees[0].child.eval(state, 0, (GPData)sd, new ADFStack(),
			((GPIndividual)ind), new SingleObjectiveProblem());
			System.out.println("Result after removing servers: "+sd.getSingleObjectiveScore());
			assertTrue(sd.getSingleObjectiveScore()<initialScore);
		}
		
		public void testCompareSequentialVsBranched(){
			OmnetStateData sd = new OmnetStateData();
			sd.initializeState();
			GPIndividual ind = new GPIndividual();
			GPTree[] treeInit = {new GPTree()};
			ind.trees = treeInit;
			//Original Plan: (; StartNewServerC StartNewServerC)
			GPNode node1 = new SequenceOperator();
			GPNode[] childrenOfNode1 = {new StartNewServerC(),new StartNewServerC()};
			node1.children=childrenOfNode1;
			ind.trees[0].child=node1;

			Evolve ev = new Evolve();
			String[] inputFile = {"-file","selfadaptivesystemsingleobjective.params"};
			ParameterDatabase params = ev.loadParameterDatabase(inputFile);
			EvolutionState state = ev.initialize(params,0);
			((GPIndividual)ind).trees[0].child.eval(state, 0, (GPData)sd, new ADFStack(),
			((GPIndividual)ind), new SingleObjectiveProblem());
			double sequentialScore = sd.getSingleObjectiveScore();
			sd.initializeState();
			GPIndividual ind2 = new GPIndividual();
			GPTree[] treeInit2 = {new GPTree()};
			ind2.trees = treeInit2;
			//Original Plan: (; StartNewServerC StartNewServerC)
			GPNode p2node1 = new IfThenElseOperator();
			GPNode[] childrenOfp2Node1 = {new StartNewServerC(),new StartNewServerC(),new StartNewServerC()};
			p2node1.children=childrenOfp2Node1;
			ind2.trees[0].child=p2node1;

			Evolve ev2 = new Evolve();
			ParameterDatabase params2 = ev.loadParameterDatabase(inputFile);
			EvolutionState state2 = ev.initialize(params,0);
			((GPIndividual)ind2).trees[0].child.eval(state2, 0, (GPData)sd, new ADFStack(),
			((GPIndividual)ind2), new SingleObjectiveProblem());
			double branchScore = sd.getSingleObjectiveScore();
			System.out.println("Result- sequential score: "+
					sequentialScore+" branch score: "+branchScore);
			assertTrue(sequentialScore<branchScore);
		}
		
}
