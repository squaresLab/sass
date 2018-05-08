package util;

import java.io.*;
import java.util.*;
import ecj.JavaRep;

public class ReusePicker {
	
	public static void main(String[] args) throws IOException, ClassNotFoundException {
		slurpClusterFile("/home/ckinneer/class/ckinneer/project/Deckard/javagen/clusters/post_cluster_vdb_50_0_allg_0.95_30");
		Map<String,JavaRep> objMap = slurpObjsMethods("objectgen");
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
	
	public static void slurpClusterFile(String filepath) throws IOException {
		FileInputStream fis = new FileInputStream(filepath);
		 
		  //Construct BufferedReader from InputStreamReader
		  BufferedReader br = new BufferedReader(new InputStreamReader(fis));

		  String line = null;
		  while ((line = br.readLine()) != null) {
			  System.out.println(line);
		  }

		  br.close();
	}

}
