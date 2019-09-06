package ecj;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.Reader;
import java.io.StringReader;
import java.io.File;
import java.util.Random;

import ec.EvolutionState;
import ec.Species;
import ec.gp.GPFunctionSet;
import ec.gp.GPIndividual;
import ec.gp.GPInitializer;
import ec.gp.GPNode;
import ec.gp.GPNodeBuilder;
import ec.gp.GPNodeParent;
import ec.gp.GPNodeSelector;
import ec.gp.GPSpecies;
import ec.gp.GPTree;
import ec.gp.GPType;
import ec.gp.koza.GPKozaDefaults;
import ec.gp.koza.MutationPipeline;
import ec.util.MersenneTwisterFast;
import ec.util.Parameter;
import ecj.operators.SequenceOperator;
import util.ReusePicker;
import ecj.actions.DecreaseDimmerLevel;
import ecj.actions.IncreaseTrafficLevel;
import ecj.actions.ServerA;
import ecj.actions.ServerB;

/*
 * This class now compiles - test that it works at run time tomorrow
 * TODO: test the class at runtime and then fix the other todos
 * 
 */
public class PopFromRepertoireBuilder extends ec.gp.GPNodeBuilder {

	public static final String P_MUTATEBUILDER = "initmutate";
	public static final String P_NODESELECTOR = "ns";
	public static final String P_NUM_TRIES = "tries";
	public static final String P_BUILDER = "build";
	public static final String P_MAXDEPTH = "maxdepth";
	public static final int NO_SIZE_LIMIT = -1;
	
	private static double buildprob;
	
	Parameter base;
	
	/** How to choose a subtree to mutate */
    public GPNodeSelector nodeselect;
    
    /** How mutated subtrees are built */
    public GPNodeBuilder builder;
    
    // the starting individual
    GPIndividual ind = null;
        
    int numTries;
    
    int maxDepth;
    
    String repertoireFolderPath = "repertoire";
    
    File repertoireFolder = new File(repertoireFolderPath);
    
    File[] planFiles;
    
    Random random = new Random();
    
    EvolutionState state;
    
    GPSpecies species;
    
   // static MersenneTwisterFast mtf = null;

    //if trees must be equal size
    //TODO: make this a parameter later
    boolean equalSize=false;
private static String initial;
    
	@Override
    public Parameter defaultBase()
        {
        return GPKozaDefaults.base().push(P_MUTATEBUILDER); 
        }
	
	@Override
	public void setup(final EvolutionState state, final Parameter base)
    {
    super.setup(state,base);

    this.base = base;
    
    this.state = state;
    
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
        
        
        buildprob = state.parameters.getDouble(new Parameter("build_prob"), null);
        planFiles = repertoireFolder.listFiles();
        
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
		
		GPNode node = null;
		
		for (int i = 0; i < numTries; i++){
			int index = random.nextInt(planFiles.length);
			File planFile = planFiles[index];
			node = readPlanFromFile(planFile);
			
			if (node != null){
				break;
			}else{
				continue;
			}
		}
		return node;
		//first the method will hard code a node
		//TODO: figure out how to avoid hard coding the plan if you can
		//then method will mutate the original node some number of times to 
		//create the new node for the population
		
	/*
	//Original Plan: (; StartNewServerB (; StartNewServerB (; (; StartNewServerB (; StartNewServerC StartNewServerC)) (; StartNewServerB (; StartNewServerC StartNewServerC))))))
		// change to: ( StartNewServerB ; StartNewServerC )
	StateData sd = new StateData();
	sd.initializeState();
	
	if (ind == null)
		ind = loadStartInd(state);
	
	//GPIndividual ind = (GPIndividual) this.ind.clone();
	
		 nodeselect.reset();
		 
		 Random rand = new Random();
		 
		 if (rand.nextDouble() < buildprob){
			 return builder.newRootedTree(state, type, thread, parent, set, argposition, requestedSize);
		 }
		 
		// validity result...
         boolean res = false;
		 
         // pick a node
         
         GPNode p1=null;  // the node we pick
         
         GPInitializer initializer = ((GPInitializer)state.initializer);
         
         for(int x=0;x<numTries;x++)
             {
        	 //subpopulation and indCount are not used in koza node selector so set them to 0
        	 int subpopulation = 0;
        	 //assuming the 0 thread is always active
        	 int threadInd =0;
        	 
             // pick a node in individual 1
             p1 = nodeselect.pickNode(state,subpopulation,threadInd,ind,ind.trees[0]);
             
             if (!p1.constraints((GPInitializer) state.initializer).returntype.equals(((GPTree)p1.rootParent()).constraints((GPInitializer) state.initializer).treetype)){
            	continue; 
             }else{
            	 break;
             }
            }
           
         GPIndividual j;

         //TODO: I think the first if statement will work without 
         // the check, because it is just checking if the plan
         // is a copy that it can alter or if the plan needs
         // to be copied before editing.
         //if (sources[0] instanceof BreedingPipeline)
             // it's already a copy, so just smash the tree in
         //    {

             j = (GPIndividual)(ind.lightClone());
             
             // Fill in various tree information that didn't get filled in there
             j.trees = new GPTree[ind.trees.length];
             
             // at this point, p1 or p2, or both, may be null.
             // If not, swap one in.  Else just copy the parent.
           
             int x = 0;
          
             j.trees[x] = (GPTree)(ind.trees[x].lightClone());
             j.trees[x].owner = j;
             j.trees[x].child = (GPNode) p1.clone();
             j.trees[x].child.parent = j.trees[x];
             j.trees[x].child.argposition = 0;
             j.evaluated = false;  
             
             return j.trees[0].child;
             
         //return mutate(state,thread,j).trees[0].child;
          */
	}
	
	private GPIndividual mutate(EvolutionState state,int thread,GPIndividual ind){
		StateData sd = new StateData();
		sd.initializeState();
		
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
	         /*
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
	             */
	             j = (GPIndividual)(ind.lightClone());
	             
	             // Fill in various tree information that didn't get filled in there
	             j.trees = new GPTree[ind.trees.length];
	             
	             // at this point, p1 or p2, or both, may be null.
	             // If not, swap one in.  Else just copy the parent.
	           
	             int x = 0;
	                 if (res)  // we've got a tree with a kicking cross position!
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
	                 
	             
	         
	         //return the first node; this may be node1
	         return j;
	}
	
	private GPNode readPlanFromFile(File file) {
		try(BufferedReader br = new BufferedReader(new FileReader(file))) {
		    StringBuilder sb = new StringBuilder();
		    String indString = br.readLine();
		    indString = "Evaluated: F\n"+
			"Fitness: d4554802393809453312|3.3430128051926966E-4|i0|\n"+
			"Tree 0:\n" + indString;
		    
			LineNumberReader lnr = new LineNumberReader(new StringReader(indString));
			
			if (species == null) {
				species = new GPSpecies();
				species.setup(state, new Parameter("pop.subpop.0.species"));
			}
			
			GPIndividual ind = null;
			
			try {
				ind = (GPIndividual) species.newIndividual(state, lnr);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		//	ind.printIndividualForHumans(state, 0);
			
			
		return ind.trees[0].child;
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
