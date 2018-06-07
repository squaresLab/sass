package util;

import java.io.*;
import java.util.*;

import ec.gp.GPIndividual;
import ec.gp.GPNode;
import ec.gp.GPTree;
import ecj.JavaRep;

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
		
		String deckardFile = "/home/ckinneer/class/ckinneer/project/Deckard/javagen/clusters/post_cluster_vdb_50_0_allg_0.95_30";
		
		int desiredSnippets = 100;
		
		ArrayList<DeckardCluster> clusters = slurpClusterFile("/home/ckinneer/class/ckinneer/project/Deckard/javagen/clusters/post_cluster_vdb_50_0_allg_0.95_30");
		
		Map<String,JavaRep> objMap = slurpObjsMethods("objectgen");
		
		ArrayList<Object> snippets = new ArrayList<Object>();
		
		ReusePicker picker = new ReusePicker(deckardFile);
		
		for (int count = 0; count < 100; count++){
			GPNode node = picker.getNode();
			if (node != null){
				System.out.println(node.parent);
			}
				
		}
		
		
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
		
		String filename = csv[3].split("/")[1].replaceAll( ".java",".ser");
		int sourceLineNumber = Integer.parseInt(csv[4].split(":")[1]);
		
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
				  curCluster.addLine(line);
			  }else{
				  clusters.add(curCluster);
				  curCluster = new DeckardCluster();
			  }
		  }

		  br.close();
		  
		  return clusters;
	}

}
