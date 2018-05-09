package util;

import java.io.*;
import java.util.*;

import ec.gp.GPNode;
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
		
		
		
		while (snippets.size() < desiredSnippets){
	
			
			
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
			return (GPNode) node;
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
