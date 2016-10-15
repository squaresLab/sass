package util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Hashtable;

public class ReadPrism {

	public static void main(String[] args) throws IOException{
		
		//args[0] = "test.tra";
		
		FileWriter fw = new FileWriter(new File("outplan.txt"));
		BufferedWriter bw = new BufferedWriter(fw);
		
		String[] trans = preprocessTra(args[0]);
		
        Integer startState = null;
        
        String tranFile = args[0];
		String stateFile = args[1];
		  
		  BufferedReader stateReader = new BufferedReader(new FileReader(stateFile));
        
        // now figure out the starting state
        String line;
        while ((line = stateReader.readLine()) != null) {

      	  if (line.contains("(1,1,1,0,0,0,4,4,4,true,0)")){
      		  String[] values = line.split(":");
      		  startState = Integer.parseInt(values[0]);
      		  break;
      	  }
      	  
            
        }
        
        System.out.println(startState);
		
        bw.write("("+dfs(startState,trans)+")");
        
		bw.flush();
		bw.close();
		
		/*
		
		Hashtable<Integer,String[]> stateDict = new Hashtable<Integer,String[]>();
		
		String tranFile = args[0];
		String stateFile = args[1];
		
		String csvSplitBy = " ";
		
		  
		  BufferedReader stateReader = new BufferedReader(new FileReader(stateFile));

		  String line;
		  
          
          Integer startState = null;
          
          // now figure out the starting state
          while ((line = stateReader.readLine()) != null) {

        	  if (line.contains("(1,1,1,0,0,0,4,4,4,true,0)")){
        		  String[] values = line.split(":");
        		  startState = Integer.parseInt(values[0]);
        		  break;
        	  }
        	  
              
          }
          
          System.out.println(startState);
          
          stateReader.close();
		  
          System.out.println(dfs(startState,tranFile));
          /*
          String[] tran;
          // now that we have the needed info, lets print out the all succeed plan
		while ((tran = fetchLine(startState,tranFile,true)) != null){
			
			startState = Integer.parseInt(tran[1]);
			
			String action = tran[3];
			
			if (!action.equals("finishedAction"))
				System.out.println(action);
			
		}
		*/
	}

	private static String dfs(Integer startState, String[] trans) throws NumberFormatException, IOException {
		String[] suc,fail;
		if ((suc = fetchLine(startState,trans,true)) != null){
			
			double chance = Double.parseDouble(suc[2]);
			
			if (chance == 1){
				return dfs(Integer.parseInt(suc[1]),trans);
			}else if ((fail = fetchLine(startState,trans,false)) != null){
				
				String c = dfs(Integer.parseInt(fail[1]),trans);
				String f = dfs(Integer.parseInt(suc[1]),trans);
			
				if (c != null && f != null){
			
					return "T ("+fixName(suc[3])+") ("+c+") ("+f+")";
				}else{
					return fixName(suc[3]);
				}
			
			}
			
		}
		
		return null;
		
	}

	private static String fixName(String string) {
		String ans = string;
		if (string.contains("addServer")){
			ans = string.replace("addServer", "StartServer ");
		}else if(string.contains("shutdownServer")){
			ans = string.replace("shutdownServer", "ShutdownServer ");
		}else if(string.contains("increaseDimmerLevel")){
			ans = string.replace("increaseDimmerLevel", "IncreaseDimmer ");
		}else if (string.contains("decreaseDimmerLevel")){
			ans = string.replace("decreaseDimmerLevel", "DecreaseDimmer ");
		}else if(string.contains("increaseTrafficLevel")){
			ans = string.replace("increaseTrafficLevel", "IncreaseTraffic ");
		}else if (string.contains("decreaseTrafficLevel")){
			ans = string.replace("decreaseTrafficLevel", "DecreaseTraffic ");
		}
		return ans;
	}

	private static String[] fetchLine(Integer startState,String[] trans,boolean success){
		
		int lowBound = startState;
		int highBound = trans.length-1;
		
		String csvSplitBy = " ";
		
		String line;
		
		while (true){
			int count = (highBound + lowBound) / 2;
			line = trans[count];
			
			if (line != null){
			
			String[] values = line.split(csvSplitBy);
            
            if (values.length == 4){
          	  
          	  Integer start = Integer.parseInt(values[0]);
          	  
          	  if (start > startState){
          		  if (highBound == count)
          			  return null;
          		  highBound = count;
          	  }else if (start < startState){
          		  if (lowBound == count)
          			  return null;
          		  lowBound = count;
          	  }else if (start.equals(startState)){
          		double chance = Double.parseDouble(values[2]);
          		boolean res = chance > .5 ? true : false;
          		if (res == success){
          			return values;
          		}else{
          			line = trans[count-1];
          			values = line.split(csvSplitBy);
          			start = Integer.parseInt(values[0]);
          			chance = Double.parseDouble(values[2]);
          			res = chance > .5 ? true : false;
          			if (res == success && start.equals(startState)){
          				return values;
          			}
          			line = trans[count+1];
          			values = line.split(csvSplitBy);
          			start = Integer.parseInt(values[0]);
          			chance = Double.parseDouble(values[2]);
          			res = chance > .5 ? true : false;
          			if (res == success && start.equals(startState)){
          				return values;
          			}
          			return null;
          		}
          	  }
			
		}else{
			return null;
		}
			}
		}
		
	}
	
	private static String[] fetchLine(Integer startState,String tranFile,boolean sucsess) throws NumberFormatException, IOException {
		
		BufferedReader tranReader = new BufferedReader(new FileReader(tranFile));
		
		String line;
		String csvSplitBy = " ";
		
		   while ((line = tranReader.readLine()) != null) {

            String[] values = line.split(csvSplitBy);
            
            if (values.length == 4){
          	  
          	  Integer start = Integer.parseInt(values[0]);
          	  
          	  double chance = Double.parseDouble(values[2]);
          	  if (sucsess){
          	  if (start.equals(startState) && chance > 0.5){
          		tranReader.close();
          		  return values;
          	  }
          	  }else{
          		if (start.equals(startState) && chance < 0.5){
              		tranReader.close();
              		  return values;
              	  }
          	  }
        }

		}
		   
	return null;
	
}
	
	private static void makeRandomAccess(String filename) throws IOException{
		
		RandomAccessFile raf = new RandomAccessFile(new File(filename),"r");
		
		FileWriter fw = new FileWriter(new File("tranIndex.csv"));
		BufferedWriter bw = new BufferedWriter(fw);
		
		String line;
		String csvSplitBy = " ";
		
		long pointer;
		
		 while ((pointer = raf.getFilePointer()) < raf.length()){
			 
			 //System.out.println(pointer);
			 
			 line = raf.readLine();
			 		   
			   String[] values = line.split(csvSplitBy);
			   
			   double chance = Double.parseDouble(values[2]);
			   
			   Integer next = Integer.parseInt(values[1]);
			   
			   Integer start = Integer.parseInt(values[0]);
			   
			   String action = values[3];
			   
			   bw.write(start + " " + pointer + "\n");
			   
		 }
		 
		 bw.flush();
		 bw.close();
		
	}
	
	private static String[] preprocessTra(String filename) throws IOException{
		
		int linenum = 0;
		
		BufferedReader tranReader = new BufferedReader(new FileReader(filename));
		
		/*
		FileWriter fw = new FileWriter(new File("proc.tra"));
		BufferedWriter bw = new BufferedWriter(fw);
		*/
		
		String[] slurp = new String[41000000];
		
		String line;
		String csvSplitBy = " ";
		
		
		
		   while ((line = tranReader.readLine()) != null) {
			   
			   slurp[linenum++] = line;
			   
			   
			   //System.out.println(linenum++);
			   /*
			   String[] values = line.split(csvSplitBy);
			   
			   double chance = Double.parseDouble(values[2]);
			   
			   Integer next = Integer.parseInt(values[1]);
			   
			   Integer start = Integer.parseInt(values[0]);
			   
			   String action = values[3];
			   
			   // if the line is not a finishedAction line
			   if(chance != 1){
				   
				   // grab the finishedAction tran
				   String[] nextLine = fetchLine(next,filename,true);
				   
				   String out;
				   
				   if (nextLine != null){
				   // replace this end state with the finishedAction end state
				   next = Integer.parseInt(nextLine[1]);
				   out = start + " " + next + " " + chance + " " + action + "\n";
				   }else{
					   out = line;
				   }
				   
				   bw.write(out);
				  
			   }
			    */
			   
		   }
		   System.out.println("done");
		   return slurp;
	
		   /*
		   bw.flush();
		   
		   bw.close();
		   tranReader.close();
		   */
	}
	
}
