package ecj;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.ObjectInputStream;
import java.io.Reader;
import java.io.StringReader;
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
public class RepertoireBuilder extends ec.gp.GPNodeBuilder {

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

		Random rand = new Random();
		
		GPNode node = null;
		
		if (rand.nextDouble() < buildprob){
			 return builder.newRootedTree(state, type, thread, parent, set, argposition, requestedSize);
		 }
		
		File dir = new File("repertoire");
		
		File[] files = dir.listFiles();

		File file = files[rand.nextInt(files.length)];
		
		 FileInputStream fileIn;
		try {
			fileIn = new FileInputStream(file);
			ObjectInputStream objectIn = new ObjectInputStream(fileIn);
			 
			Object obj = objectIn.readObject();
			objectIn.close();
			
			GPIndividual ind = (GPIndividual) obj;
			
			node = ind.trees[0].child;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	private String readPlanFromFile(String fileName) {
		try(BufferedReader br = new BufferedReader(new FileReader(fileName))) {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();
		    return "Evaluated: F\n"+
			"Fitness: d4554802393809453312|3.3430128051926966E-4|i0|\n"+
			"Tree 0:\n" + line;
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
