package util;

import java.io.*;
import java.util.*;

import ec.EvolutionState;
import ec.Evolve;
import ec.gp.GPIndividual;
import ec.gp.GPNode;
import ec.gp.GPTree;
import ec.util.Output;
import ec.util.ParameterDatabase;
import ecj.JavaRep;
import ecj.RepertoireBuilder;
import omnet.Scenario;

public class ReusePicker {
	
	ArrayList<DeckardCluster> clusters;
	
	Map<String,JavaRep> objMap;
	
	Random rand;
	
	final int TOURNAMENT_SIZE = 7;
	
	public ReusePicker(String deckardFile){
		this(deckardFile, "objectgen");
	}
	
	public ReusePicker(String deckardFile, String objectDir){
		rand = new Random();
		try {
			clusters = slurpClusterFile(deckardFile);
			objMap = slurpObjsMethods(objectDir);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		
		RepertoireBuilder.scenario = new Scenario();
		
		File parameterFile = new File( System.getProperty("user.dir")+"/selfadaptivesystemsingleobjective.params");

		ParameterDatabase dbase;
		
		EvolutionState state = null;
		
		try {
			dbase = new ParameterDatabase(parameterFile,new String[] {"-file",parameterFile.getCanonicalPath()});
			
			dbase.setProperty("gp.tc.0.init","ec.gp.koza.HalfBuilder");
			
			Output out = Evolve.buildOutput();

			//disable output
			out.getLog(0).silent = true;
			out.getLog(1).silent = true;
			
			state = Evolve.initialize(dbase,0,out);
			
			state.startFresh();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String deckardFile = "/home/ckinneer/git/sass/clusters/post_cluster_vdb_30_0_allg_1.0_30";
		
		int desiredSnippets = 100;
		
		ArrayList<DeckardCluster> clusters = slurpClusterFile(deckardFile);
		
		Map<String,JavaRep> objMap = slurpObjsMethods("objectgen");
		
		ArrayList<Object> snippets = new ArrayList<Object>();
		/*
		ReusePicker picker = new ReusePicker(deckardFile);
		
		for (int count = 0; count < 100; count++){
			GPNode node = picker.getNode();
			if (node != null){
				System.out.println(node.parent);
			}
				
		}
		*/
		
		for (int count = 0; count < clusters.size(); count++) {
			DeckardCluster cluster = clusters.get(count);
			//System.out.println(cluster.getLines().size());
			
			System.out.println("*******************");
			
			// now choose line to get a snippet from
			
			for (int j = 0; j < cluster.getLines().size(); j++) {
			
				String deckardline = cluster.lines.get(j);
				// we need to know the filename and line number
				// first manage the whitespace
				deckardline = deckardline.trim().replaceAll("\\s+", " ");
				String[] csv = deckardline.split(" ");
				
				String filename = csv[3].split("/")[1].replaceAll( ".java",".ser");
				int sourceLineNumber = Integer.parseInt(csv[4].split(":")[1]);
				
				// now lookup the snippet from the map
				JavaRep java = objMap.get(filename);
				// pull the object from the rep
				if (java != null) {
				Object node = java.getObjMap().get(sourceLineNumber);
				if (node != null){
					//System.out.println(java.getStringMap().get(sourceLineNumber));
					
					GPNode gpNode = (GPNode) node;
					
					 GPTree tree = (GPTree) gpNode.rootParent();
					 
					 GPIndividual ind = tree.owner;
					 
					 StringWriter sw = new StringWriter();
					 PrintWriter pw = new PrintWriter(sw);
					 ind.printIndividual(state, pw);
					 
					 String ans = sw.toString();
					 
					 System.out.println(ans);
			}
			}
			}
		}
		System.out.println("num clusters: "+clusters.size());
	}
	
	public GPNode getNode(){
		// choose a cluster by tournament selection
		DeckardCluster curCluster = tournamentSelection(clusters,TOURNAMENT_SIZE);
		// now choose line to get a snippet from
		String deckardline = curCluster.lines.get(rand.nextInt(curCluster.lines.size()-1));
		// we need to know the filename and line number
		// first manage the whitespace
		deckardline = deckardline.trim().replaceAll("\\s+", " ");
		String[] csv = deckardline.split(" ");
		
		// split on 6 for deckard default and 1 for custom?
		String filename = csv[3].split("/")[1].replaceAll( ".java",".ser");
		int sourceLineNumber = Integer.parseInt(csv[4].split(":")[1]);
		//System.out.println(filename);
		// now lookup the snippet from the map
		JavaRep java = objMap.get(filename);
		// pull the object from the rep
		Object node = java.getObjMap().get(sourceLineNumber);
		if (node != null){
			//System.out.println(java.getStringMap().get(sourceLineNumber));
			
			 GPIndividual j;
			GPNode gpNode = (GPNode) node;
			
			 GPTree tree = (GPTree) gpNode.rootParent();
			 
			 GPIndividual ind = tree.owner;
			
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
	             j.trees[x].child = (GPNode) ((GPNode) node).clone();
	             j.trees[x].child.parent = j.trees[x];
	             j.trees[x].child.argposition = 0;
	             j.evaluated = false;  
	             
	             return j.trees[0].child;
			
		}else{
			return null;
		}
	}
	
	public static <T extends Comparable<? super T>> T tournamentSelection(List<T> objects, int tournamentSize){
		Comparable<T> ans = null;
		
		ArrayList<T> tourney = new ArrayList<T>();
		
		// populate the tournament with randomly chosen inds
		Random rand = new Random();
		for (int i = 0; i < tournamentSize; i++){
			int index = rand.nextInt(objects.size());
			tourney.add(objects.get(index));
		}
		
		// now take the most fit one
		Collections.sort(tourney);
		
		return tourney.get(tourney.size()-1);
	}
	
	public static Map<String,JavaRep> slurpObjsMethods(String path) throws IOException, ClassNotFoundException{
		HashMap<String,JavaRep> ans = new HashMap<String,JavaRep>();
		
		File folder = new File(path);
		File[] listOfFiles = folder.listFiles();

		for (int i = 0; i < listOfFiles.length; i++) {
		  File file = listOfFiles[i];
		  
		  	FileInputStream fi = new FileInputStream(file);
			ObjectInputStream oi = new ObjectInputStream(fi);

			// Read objects
			JavaRep jr = (JavaRep) oi.readObject();
			
			ans.put(file.getName(), jr);

			oi.close();
			fi.close(); 

		}
		
		
		
		return ans;
	}
	
	public static ArrayList<DeckardCluster> slurpClusterFile(String filepath) throws IOException {
		FileInputStream fis = new FileInputStream(filepath);
		 
		  //Construct BufferedReader from InputStreamReader
		  BufferedReader br = new BufferedReader(new InputStreamReader(fis));

		  String line = null;
		  
		  ArrayList<DeckardCluster> clusters = new ArrayList<DeckardCluster>();
		  
		  DeckardCluster curCluster = new DeckardCluster();
		  
		  while ((line = br.readLine()) != null) {
			  if (!line.isEmpty()){
				  //System.out.println("* "+line);
				  curCluster.addLine(line);
			  }else{
				  //System.out.println("  "+line);
				  //System.out.println(curCluster.getLines().size());
				  clusters.add(curCluster);
				  curCluster = new DeckardCluster();
			  }
		  }

		  br.close();
		  
		  return clusters;
	}

}
