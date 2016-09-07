package main;

import ec.EvolutionState;
import ec.gp.GPFunctionSet;
import ec.gp.GPIndividual;
import ec.gp.GPInitializer;
import ec.gp.GPNode;
import ec.gp.GPNodeBuilder;
import ec.gp.GPNodeParent;
import ec.gp.GPNodeSelector;
import ec.gp.GPTree;
import ec.gp.GPType;
import ec.gp.koza.GPKozaDefaults;
import ec.util.Parameter;
import actions.operators.SequenceOperator;
import omnet.tactics.DecreaseDimmerLevelA;
import omnet.tactics.DecreaseDimmerLevelB;
import omnet.tactics.DecreaseDimmerLevelD;
import omnet.tactics.DecreaseTrafficLevelF;
import omnet.tactics.IncreaseDimmerLevelA;
import omnet.tactics.IncreaseTrafficLevelA;
import omnet.tactics.IncreaseTrafficLevelB;
import omnet.tactics.IncreaseTrafficLevelD;
import omnet.tactics.ShutdownServerC;
import omnet.tactics.StartNewServerA;
import omnet.tactics.StartNewServerB;
import omnet.tactics.StartNewServerC;

/*
 * This class now compiles - test that it works at run time tomorrow
 * TODO: test the class at runtime and then fix the other todos
 * 
 */

public class MutationBuilder extends ec.gp.GPNodeBuilder {

	
	public static final String P_MUTATEBUILDER = "initmutate";
	public static final String P_NODESELECTOR = "ns";
	public static final String P_NUM_TRIES = "tries";
	public static final String P_BUILDER = "build";
	public static final String P_MAXDEPTH = "maxdepth";
	public static final int NO_SIZE_LIMIT = -1;
	
	/** How to choose a subtree to mutate */
    public GPNodeSelector nodeselect;
    
    /** How mutated subtrees are built */
    public GPNodeBuilder builder;
    
    int numTries;
    
    int maxDepth;

    //if trees must be equal size
    //TODO: make this a parameter later
    boolean equalSize=false;
    
	@Override
    public Parameter defaultBase()
        {
        return GPKozaDefaults.base().push(P_MUTATEBUILDER); 
        }
	
	@Override
	public void setup(final EvolutionState state, final Parameter base)
    {
    super.setup(state,base);

    Parameter def = defaultBase();

    Parameter p = base.push(P_NODESELECTOR);
    Parameter d = def.push(P_NODESELECTOR);

    
    nodeselect = (GPNodeSelector)
        (state.parameters.getInstanceForParameter(
            p,d, GPNodeSelector.class));
    nodeselect.setup(state,p);
    
    numTries = state.parameters.getInt(
            base.push(P_NUM_TRIES),def.push(P_NUM_TRIES),1);
        if (numTries ==0)
            state.output.fatal("Mutation Pipeline has an invalid"
            		+ " number of tries (it must be >= 1).",
            		base.push(P_NUM_TRIES),def.push(P_NUM_TRIES));
        
        maxDepth = state.parameters.getInt(
                base.push(P_MAXDEPTH),def.push(P_MAXDEPTH),1);
            if (maxDepth==0)
                state.output.fatal("The Mutation Pipeline " + base + "has an invalid maximum depth (it must be >= 1).",base.push(P_MAXDEPTH),def.push(P_MAXDEPTH));

            maxSize = NO_SIZE_LIMIT;
            if (state.parameters.exists(base.push(P_MAXSIZE), def.push(P_MAXSIZE)))
                {
                maxSize = state.parameters.getInt(base.push(P_MAXSIZE), def.push(P_MAXSIZE), 1);
                if (maxSize < 1)
                    state.output.fatal("Maximum tree size, if defined, must be >= 1");
                }
            
        
        p = base.push(P_BUILDER);
        d = def.push(P_BUILDER);

        builder = (GPNodeBuilder)
            (state.parameters.getInstanceForParameter(
                p,d, GPNodeBuilder.class));
        builder.setup(state,p);
    
    }
	
	  /** Returns true if inner1 can feasibly be swapped into inner2's position */

    public boolean verifyPoints(GPNode inner1, GPNode inner2)
        {
        // We know they're swap-compatible since we generated inner1
        // to be exactly that.  So don't bother.

        // next check to see if inner1 can fit in inner2's spot
        if (inner1.depth()+inner2.atDepth() > maxDepth) return false;

        // check for size
        if (maxSize != NO_SIZE_LIMIT)
            {
            // first easy check
            int inner1size = inner1.numNodes(GPNode.NODESEARCH_ALL);
            int inner2size = inner2.numNodes(GPNode.NODESEARCH_ALL);
            if (inner1size > inner2size)  // need to test further
                {
                // let's keep on going for the more complex test
                GPNode root2 = ((GPTree)(inner2.rootParent())).child;
                int root2size = root2.numNodes(GPNode.NODESEARCH_ALL);
                if (root2size - inner2size + inner1size > maxSize)  // take root2, remove inner2 and swap in inner1.  Is it still small enough?
                    return false;
                }
            }

        // checks done!
        return true;
        }
	

	@Override
	public GPNode newRootedTree(EvolutionState state, GPType type, int thread, GPNodeParent parent, GPFunctionSet set,
			int argposition, int requestedSize) {
		//first the method will hard code a node
		//TODO: figure out how to avoid hard coding the plan if you can
		//then method will mutate the original node some number of times to 
		//create the new node for the population
		
	//Original Plan: (; StartNewServerB (; StartNewServerB (; (; StartNewServerB (; StartNewServerC StartNewServerC)) (; StartNewServerB (; StartNewServerC StartNewServerC))))))
		// change to: ( StartNewServerB ; StartNewServerC )
	OmnetStateData sd = new OmnetStateData();
	sd.initializeState();
	GPIndividual ind = new GPIndividual();
	GPTree[] treeInit = {new GPTree()};
	ind.trees = treeInit;

	
	//size 2, worked, no hanging
//	GPNode node1 = new SequenceOperator();
//	GPNode node2 = new StartNewServerA();
//	GPNode node3 = new IncreaseTrafficLevelD();
//	
//	GPNode[] childrenOfNode1 = new GPNode[2];
//	GPNode[] childrenOfNode2 = new GPNode[0];
//	GPNode[] childrenOfNode3 = new GPNode[0];
//	
//	childrenOfNode1[0] = node2;
//	childrenOfNode1[1] = node3;
//	ind.trees[0].child=node1;
//	node1.children = childrenOfNode1;
//	node2.children = childrenOfNode2;
//	node3.children = childrenOfNode3;
//	node1.parent = ind.trees[0];
//	node2.parent = node1;
//	node3.parent = node1;
	
	//size 3, worked, no hanging
//	GPNode node1 = new SequenceOperator();
//	GPNode node2 = new SequenceOperator();
//	GPNode node3 = new IncreaseTrafficLevelB();
//	GPNode node4 = new StartNewServerA();
//	GPNode node5 = new IncreaseTrafficLevelB();
//
//	GPNode[] childrenOfNode1 = new GPNode[2];
//	GPNode[] childrenOfNode2 = new GPNode[2];
//	GPNode[] childrenOfNode3 = new GPNode[0];
//	GPNode[] childrenOfNode4 = new GPNode[0];
//	GPNode[] childrenOfNode5 = new GPNode[0];
//
//	childrenOfNode1[0] = node2;
//	childrenOfNode1[1] = node3;
//	childrenOfNode2[0] = node4;
//	childrenOfNode2[1] = node5;

//	ind.trees[0].child=node1;
//	node1.children = childrenOfNode1;
//	node2.children = childrenOfNode2;
//	node3.children = childrenOfNode3;
//	node4.children = childrenOfNode4;
//	node5.children = childrenOfNode5;
//	
//	node1.parent = ind.trees[0];
//	node2.parent = node1;
//	node3.parent = node1;
//	node4.parent = node2;
//	node5.parent = node2;

//size 4, hang sometimes...
	GPNode node1 = new SequenceOperator();
	GPNode node2 = new IncreaseTrafficLevelD();
	GPNode node3 = new SequenceOperator();
	GPNode node4 = new DecreaseDimmerLevelB();
	GPNode node5 = new SequenceOperator();
	GPNode node6 = new DecreaseDimmerLevelA();
	GPNode node7 = new SequenceOperator();
	GPNode node8 = new DecreaseDimmerLevelD();

	GPNode[] childrenOfNode1 = new GPNode[2];
	GPNode[] childrenOfNode2 = new GPNode[0];
	GPNode[] childrenOfNode3 = new GPNode[2];
	GPNode[] childrenOfNode4 = new GPNode[0];
	GPNode[] childrenOfNode5 = new GPNode[2];
	GPNode[] childrenOfNode6 = new GPNode[0];
	GPNode[] childrenOfNode7 = new GPNode[1];
	GPNode[] childrenOfNode8 = new GPNode[0];

	childrenOfNode1[0] = node2;
	childrenOfNode1[1] = node3;
	childrenOfNode3[0] = node4;
	childrenOfNode3[1] = node5;
	childrenOfNode5[0] = node6;
	childrenOfNode5[1] = node7;
	childrenOfNode7[0] = node8;

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
	
	
	
//size 5, hang sometimes, when it is working, it is giving me three generations of 
//	different statistics!!! 
//	GPNode node1 = new SequenceOperator();
//	GPNode node2 = new SequenceOperator();
//	GPNode node3 = new SequenceOperator();
//	GPNode node4 = new DecreaseDimmerLevelA();
//	GPNode node5 = new StartNewServerB();
//	GPNode node6 = new SequenceOperator();
//	GPNode node7 = new SequenceOperator();
//	GPNode node8 = new ShutdownServerC();
//	GPNode node9 = new IncreaseTrafficLevelD();
//	GPNode node10 = new DecreaseTrafficLevelF();
//
//	GPNode[] childrenOfNode1 = new GPNode[2];
//	GPNode[] childrenOfNode2 = new GPNode[2];
//	GPNode[] childrenOfNode3 = new GPNode[2];
//	GPNode[] childrenOfNode4 = new GPNode[0];
//	GPNode[] childrenOfNode5 = new GPNode[0];
//	GPNode[] childrenOfNode6 = new GPNode[1];
//	GPNode[] childrenOfNode7 = new GPNode[2];
//	GPNode[] childrenOfNode8 = new GPNode[0];
//	GPNode[] childrenOfNode9 = new GPNode[0];
//	GPNode[] childrenOfNode10 = new GPNode[0];
//
//	childrenOfNode1[0] = node2;
//	childrenOfNode1[1] = node3;
//	childrenOfNode2[0] = node4;
//	childrenOfNode2[1] = node5;
//	childrenOfNode3[0] = node6;
//	childrenOfNode3[1] = node7;
//	childrenOfNode6[0] = node8;
//	childrenOfNode7[0] = node9;
//	childrenOfNode7[1] = node10;
//
//	ind.trees[0].child=node1;
//	node1.children = childrenOfNode1;
//	node2.children = childrenOfNode2;
//	node3.children = childrenOfNode3;
//	node4.children = childrenOfNode4;
//	node5.children = childrenOfNode5;
//	node6.children = childrenOfNode6;
//	node7.children = childrenOfNode7;
//	node8.children = childrenOfNode8;
//	node9.children = childrenOfNode9;
//	node10.children = childrenOfNode10;
//	node1.parent = ind.trees[0];
//	node2.parent = node1;
//	node3.parent = node1;
//	node4.parent = node2;
//	node5.parent = node2;
//	node6.parent = node3;
//	node7.parent = node3;
//	node8.parent = node6;
//	node9.parent = node7;
//	node10.parent = node7;


	
//Zack's original starting plan
//	OmnetStateData sd = new OmnetStateData();
//	sd.initializeState();
//	GPIndividual ind = new GPIndividual();
//	GPTree[] treeInit = {new GPTree()};
//	ind.trees = treeInit;
//	GPNode node1 = new SequenceOperator();
//	GPNode node2 = new StartNewServerB();
//	GPNode[] childrenOfNode2 = new GPNode[0];
//	node2.children=childrenOfNode2;
//	GPNode node3 = new SequenceOperator();
//	GPNode node4 = new StartNewServerB();
//	GPNode[] childrenOfNode4 = new GPNode[0];
//	node4.children=childrenOfNode4;
//	GPNode node5 = new SequenceOperator();
//	GPNode node6 = new SequenceOperator();
//	GPNode node7 = new StartNewServerB();
//	GPNode[] childrenOfNode7 = new GPNode[0];
//	node7.children=childrenOfNode7;
//	GPNode node8 = new SequenceOperator();
//	GPNode node9 = new StartNewServerC();
//	GPNode[] childrenOfNode9 = new GPNode[0];
//	node9.children=childrenOfNode9;
//	GPNode node10 = new StartNewServerC();
//	GPNode[] childrenOfNode10 = new GPNode[0];
//	node10.children=childrenOfNode10;
//	GPNode[] childrenOfNode8 = new GPNode[2];
//	childrenOfNode8[0]=node9;
//	node9.parent=node8;
//	childrenOfNode8[1]=node10;
//	node10.parent=node8;
//	node8.children=childrenOfNode8;
//	GPNode[] childrenOfNode6 = new GPNode[2];
//	childrenOfNode6[0]=node7;
//	node7.parent=node6;
//	childrenOfNode6[1]=node8;
//	node8.parent=node6;
//	node6.children=childrenOfNode6;
//	GPNode node11 = new SequenceOperator();
//	GPNode node12 = new StartNewServerB();
//	GPNode[] childrenOfNode12 = new GPNode[0];
//	node12.children=childrenOfNode12;
//	GPNode node13 = new SequenceOperator();
//	GPNode node14 = new StartNewServerC();
//	GPNode[] childrenOfNode14 = new GPNode[0];
//	node14.children=childrenOfNode14;
//	GPNode node15 = new StartNewServerC();
//	GPNode[] childrenOfNode15 = new GPNode[0];
//	node15.children=childrenOfNode15;
//	GPNode[] childrenOfNode13 = new GPNode[2];
//	childrenOfNode13[0]=node14;
//	node14.parent=node13;
//	childrenOfNode13[1]=node15;
//	node15.parent=node13;
//	node13.children=childrenOfNode13;
//	GPNode[] childrenOfNode11 = new GPNode[2];
//	childrenOfNode11[0]=node12;
//	node12.parent=node11;
//	childrenOfNode11[1]=node13;
//	node13.parent=node11;
//	node11.children=childrenOfNode11;
//	GPNode[] childrenOfNode5 = new GPNode[2];
//	childrenOfNode5[0]=node6;
//	node6.parent=node5;
//	childrenOfNode5[1]=node11;
//	node11.parent=node5;
//	node5.children=childrenOfNode5;
//	GPNode[] childrenOfNode3 = new GPNode[2];
//	childrenOfNode3[0]=node4;
//	node4.parent=node3;
//	childrenOfNode3[1]=node5;
//	node5.parent=node3;
//	node3.children=childrenOfNode3;
//	GPNode[] childrenOfNode1 = new GPNode[2];
//	childrenOfNode1[0]=node2;
//	node2.parent=node1;
//	childrenOfNode1[1]=node3;
//	node3.parent=node1;
//	node1.children=childrenOfNode1;
//	ind.trees[0].child=node1;
//	node1.parent=ind.trees[0];
//;
		
		
		 nodeselect.reset();
         
		 
		// validity result...
         boolean res = false;
		 
         // pick a node
         
         GPNode p1=null;  // the node we pick
         GPNode p2=null;
         
         GPInitializer initializer = ((GPInitializer)state.initializer);
         
         for(int x=0;x<numTries;x++)
             {
        	 //subpopulation and indCount are not used in koza node selector so set them to 0
        	 int subpopulation = 0;
        	 //assuming the 0 thread is always active
        	 int threadInd =0;
        	 
             // pick a node in individual 1
             p1 = nodeselect.pickNode(state,subpopulation,threadInd,ind,ind.trees[0]);
             
             // generate a tree swap-compatible with p1's position
             
             
             int size = GPNodeBuilder.NOSIZEGIVEN;
             if (equalSize) size = p1.numNodes(GPNode.NODESEARCH_ALL);

             p2 = builder.newRootedTree(state,
                 p1.parentType(initializer),
                 thread,
                 p1.parent,
                 ind.trees[0].constraints(initializer).functionset,
                 p1.argposition,
                 size);
             
             // check for depth and swap-compatibility limits
             res = verifyPoints(p2,p1);  // p2 can fit in p1's spot  -- the order is important!
             
             // did we get something that had both nodes verified?
             if (res) break;
             }
         
         GPIndividual j;

         //TODO: I think the first if statement will work without 
         // the check, because it is just checking if the plan
         // is a copy that it can alter or if the plan needs
         // to be copied before editing.
         //if (sources[0] instanceof BreedingPipeline)
             // it's already a copy, so just smash the tree in
         //    {
             j=ind;
             if (res)  // we're in business
                 {
                 p2.parent = p1.parent;
                 p2.argposition = p1.argposition;
                 if (p2.parent instanceof GPNode)
                     ((GPNode)(p2.parent)).children[p2.argposition] = p2;
                 else ((GPTree)(p2.parent)).child = p2;
                 j.evaluated = false;  // we've modified it
                 }
       /*      }
         else // need to clone the individual
             {
             j = (GPIndividual)(ind.lightClone());
             
             // Fill in various tree information that didn't get filled in there
             j.trees = new GPTree[ind.trees.length];
             
             // at this point, p1 or p2, or both, may be null.
             // If not, swap one in.  Else just copy the parent.
             for(int x=0;x<j.trees.length;x++)
                 {
                 if (x==t && res)  // we've got a tree with a kicking cross position!
                     {
                     j.trees[x] = (GPTree)(ind.trees[x].lightClone());
                     j.trees[x].owner = j;
                     j.trees[x].child = ind.trees[x].child.cloneReplacingNoSubclone(p2,p1);
                     j.trees[x].child.parent = j.trees[x];
                     j.trees[x].child.argposition = 0;
                     j.evaluated = false; 
                     } // it's changed
                 else 
                     {
                     j.trees[x] = (GPTree)(ind.trees[x].lightClone());
                     j.trees[x].owner = j;
                     j.trees[x].child = (GPNode)(ind.trees[x].child.clone());
                     j.trees[x].child.parent = j.trees[x];
                     j.trees[x].child.argposition = 0;                   
                     }
                 }
             }*/
         
         //return the first node; this may be node1
         return j.trees[0].child;
	}

}
