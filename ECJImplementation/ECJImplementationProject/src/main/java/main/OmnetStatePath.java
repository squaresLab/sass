package main.java.main;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

import ec.gp.GPNode;
import main.java.omnet.components.OmnetComponent;
import main.java.omnet.components.ServerA;
import main.java.omnet.components.ServerB;
import main.java.omnet.components.ServerC;
import main.java.omnet.components.ServerD;
import main.java.omnet.tactics.DecreaseDimmerLevel;
import main.java.omnet.tactics.DecreaseTrafficLevel;
import main.java.omnet.tactics.IncreaseDimmerLevel;
import main.java.omnet.tactics.IncreaseTrafficLevel;
import main.java.omnet.tactics.ServerTactic;
import main.java.omnet.tactics.ShutdownServer;
import main.java.omnet.tactics.StartNewServer;

public class OmnetStatePath implements Serializable{
	public static final int SYSTEM_DEMAND=1000;
	public static final int MaxServerCount=5;
	ServerA prototypeServerA;
	int serverCountA;
	ServerB prototypeServerB;
	int serverCountB;
	ServerC prototypeServerC;
	int serverCountC;
	ServerD prototypeServerD;
	int serverCountD;
	public int[] countArray;
	public OmnetComponent[] serverArray;
	//ArrayList<ServerE> Eservers;
	//ArrayList<ServerF> Fservers;
	//ArrayList<ServerG> Gservers;
	//ArrayList<ArrayList<? extends OmnetComponent>> serverList;
	HashMap<Class<? extends OmnetComponent>,Integer> serverSetLookup;
	public int totalTime = 0;
	double normalProfitPerSecond=10;
	double dimmedProfitPerSecond=1.5;
	boolean allStatesValid=true;
	public String reasonForAllStatesValidSetting="all states are assumed to be initially true";

	public double pathProbability=1;
	
	//record changes during performTactic
	public ArrayDeque<Boolean> modifiedCountArray= new ArrayDeque<Boolean>();
	public ArrayDeque<Boolean> emptyCount= new ArrayDeque<Boolean>();
	public ArrayDeque<Boolean> modifiedDimmerLevel= new ArrayDeque<Boolean>();
	public ArrayDeque<Boolean> modifiedTrafficLevel= new ArrayDeque<Boolean>();
	public ArrayDeque<Double> probabilityArray= new ArrayDeque<Double>();
	public ArrayDeque<ServerTactic> alreadyPerformed = new ArrayDeque<ServerTactic>();
	

	public OmnetStatePath(){
		initializeState();
	}


	public void initializeState(){
		prototypeServerA = new ServerA();
		prototypeServerB = new ServerB();
		prototypeServerC = new ServerC();
		prototypeServerD = new ServerD();
		serverCountA=1;
		serverCountB=1;
		serverCountC=1;
		serverCountD=1;

		//serverList.add(Eservers);
		//serverList.add(Fservers);
		//serverList.add(Gservers);
		serverSetLookup = new HashMap<Class<? extends OmnetComponent>,Integer>();
		serverSetLookup.put(ServerA.class,0);
		serverSetLookup.put(ServerB.class,1);
		serverSetLookup.put(ServerC.class,2);
		serverSetLookup.put(ServerD.class,3);
		//serverSetLookup.put(ServerE.class,Eservers);
		//serverSetLookup.put(ServerF.class,Fservers);
		//serverSetLookup.put(ServerG.class,Gservers);
		totalTime=0;
		allStatesValid=true;
		reasonForAllStatesValidSetting="all states are assumed to be initially true";
		pathProbability=1;
		countArray = new int[4];
		countArray[0]=serverCountA;
		countArray[1]=serverCountB;
		countArray[2]=serverCountC;
		countArray[3]=serverCountD;
		
		serverArray = new OmnetComponent[4];
		serverArray[0]=prototypeServerA;
		serverArray[1]=prototypeServerB;
		serverArray[2]=prototypeServerC;
		serverArray[3]=prototypeServerD;
		probabilityArray.add(pathProbability);
	}
	
	public <T extends OmnetComponent> int getServerIndex(Class<T> c){
		return serverSetLookup.get(c).intValue();
	}
	

	public void setTotalTime(int totalTime){
		this.totalTime = totalTime;
	}
	
	public int getTotalTime(){
		return totalTime;
	}
	
	public int getTotalServerCount(){
		int total = 0;
		for(int i=0; i<countArray.length;i++){
			total+=countArray[i];
		}
		return total;
	}

	public void setAllStatesValid(boolean newAllStatesValid, String reason){
		this.allStatesValid=newAllStatesValid;
		this.reasonForAllStatesValidSetting=reason;
	}

	public boolean areAllStatesValid(){
		return allStatesValid;
	}

	public String getReasonForAllStatesValidSetting(){
		return reasonForAllStatesValidSetting;
	}

	public double totalServerCostPerSecond(){
		double totalCost = 0;
		for(int i = 0; i < serverArray.length; i++){
			totalCost += serverArray[i].getCostPerSecond() * countArray[i];
		}
		return totalCost;
	}

	public double getPathProbability(){
		return pathProbability;
	}

	public void setPathProbability(double pathProbability){
		this.pathProbability=pathProbability;
	}

	public double currentGrossIncome(){
		double totalGrossIncome=0;

		int totalTrafficLevel = 0;
		for(int i = 0; i < serverArray.length; i++){
			totalTrafficLevel+=serverArray[i].getTrafficLevel()*countArray[i];
		}
		double requestsPerTrafficLevel = ((double)SYSTEM_DEMAND)/totalTrafficLevel;
		for(int i =0; i < serverArray.length; i++){
			OmnetComponent server = serverArray[i];
			double currentDimmerPercentage = ((double)server.getDimmerLevel())/server.getMaxDimmerLevel();
			double currentDimmedRequests = currentDimmerPercentage * server.getDimmedRequestsHandledPerMinute();
			double currentNormalRequests = (1-currentDimmerPercentage) * server.getNormalRequestsHandledPerMinute();
			if(server.getTrafficLevel()*requestsPerTrafficLevel >= currentDimmedRequests + currentNormalRequests){
				totalGrossIncome += (normalProfitPerSecond * currentNormalRequests + 
						dimmedProfitPerSecond * currentDimmedRequests)*countArray[i];	
			} else {
				//use as much of the normal requests as possible and then use the 
				//rest at the dimmed request rate
				long requestsLeft = Math.round(server.getTrafficLevel()*requestsPerTrafficLevel);
				if(requestsLeft > currentNormalRequests){
					totalGrossIncome += (normalProfitPerSecond * currentNormalRequests) * countArray[i];
					requestsLeft = requestsLeft - Math.round(currentNormalRequests);
					totalGrossIncome += (dimmedProfitPerSecond * requestsLeft) * countArray[i];
				} else {
					//just make all the requests normal requests
					totalGrossIncome += (normalProfitPerSecond * requestsLeft) * countArray[i];
				}
			}
		}
		return totalGrossIncome;
	}

	//lower is better - goal is to get as close to zero as possible
	public double calculateProfit(){
		if(areAllStatesValid()){
			double totalProfit=0;

			int totalTrafficLevel = 0;
			for(int i = 0; i < serverArray.length; i++){
				totalTrafficLevel+=serverArray[i].getTrafficLevel()*countArray[i];
			}
			double requestsPerTrafficLevel = ((double)SYSTEM_DEMAND)/totalTrafficLevel;
			for(int i =0; i < serverArray.length; i++){
				if(countArray[i]> 0){		
					double currentDimmerPercentage = ((double)serverArray[i].getDimmerLevel())
							/serverArray[i].getMaxDimmerLevel();
					double currentDimmedRequests = currentDimmerPercentage 
							* serverArray[i].getDimmedRequestsHandledPerMinute()*countArray[i];
					double currentNormalRequests = (1-currentDimmerPercentage) *
							serverArray[i].getNormalRequestsHandledPerMinute()*countArray[i];
					if(serverArray[i].getTrafficLevel()*requestsPerTrafficLevel * 
							countArray[i] >= currentDimmedRequests + currentNormalRequests){
						totalProfit += normalProfitPerSecond * currentNormalRequests + 
								dimmedProfitPerSecond * currentDimmedRequests;	
					} else {
						//use as much of the normal requests as possible and then use the 
						//rest at the dimmed request rate
						long requestsLeft = Math.round(serverArray[i].getTrafficLevel()*
								requestsPerTrafficLevel* countArray[i]);
						if(requestsLeft > currentNormalRequests){
							totalProfit+= normalProfitPerSecond * currentNormalRequests ;
							requestsLeft = requestsLeft - Math.round(currentNormalRequests);
							totalProfit += dimmedProfitPerSecond * requestsLeft;
						} else {
							//just make all the requests normal requests
							totalProfit += normalProfitPerSecond * requestsLeft * countArray[i];
						}
					}
					totalProfit -= serverArray[i].getCostPerSecond() * countArray[i];
				}
			}
			return totalProfit;
		}
		else {
			return 0;
			//return INVALID_PLAN_SCORE+INVALID_ACTION_PENALTY*tacticFailCount;
		}
	}

	public double requestsHandledPerSecond(){
		int totalTrafficLevel = 0;

		for(int i = 0; i < serverArray.length; i++){
			totalTrafficLevel+=serverArray[i].getTrafficLevel()*countArray[i];
		}
		double requestsPerTrafficLevel = ((double)SYSTEM_DEMAND)/totalTrafficLevel;
		double totalRequestsHandled = 0;
		for(int i = 0; i< serverArray.length;i++){
			double currentDimmerPercentage = ((double)serverArray[i].getDimmerLevel())/serverArray[i].getMaxDimmerLevel();
			double currentServerCapacity = currentDimmerPercentage * serverArray[i].getDimmedRequestsHandledPerMinute()
					+ (1-currentDimmerPercentage) * serverArray[i].getNormalRequestsHandledPerMinute();
			//The server either handles the total number of requests sent to the server or
			// the max amount it can
			long requestsSentToServer = Math.round(serverArray[i].getTrafficLevel()*requestsPerTrafficLevel);
			if(requestsSentToServer > currentServerCapacity){
				totalRequestsHandled+=currentServerCapacity*countArray[i];
			} else {
				totalRequestsHandled+=requestsSentToServer*countArray[i];
			}
		}
		return totalRequestsHandled;
	}
	
	public void performTactic(ServerTactic s){
		s.reallyPerform(this);
	}
	
	public void undoTactic(){
		ServerTactic s = alreadyPerformed.pollLast();
		s.reallyUndo(this);
	}
	/*Check the speed of this function later if you have optimization issues
	 * 
	 */
	public OmnetStatePath deepCopy(){
		OmnetStatePath copy=null;
		try {
			// Write the object out to a byte array
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(bos);
			out.writeObject(this);
			out.flush();
			out.close();

			// Make an input stream from the byte array and read
			// a copy of the object back in.
			ObjectInputStream in = new ObjectInputStream(
					new ByteArrayInputStream(bos.toByteArray()));
			copy = (OmnetStatePath) in.readObject();
		}
		catch(IOException e) {
			e.printStackTrace();
			//ending the execution on an error for debugging
			System.exit(1);
		}
		catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
			//ending the executon on an error for debugging
			System.exit(1);
		}
		return copy;
	}
	
	public void printEmptyCountArray(){		
		for(Boolean boo: emptyCount){
			System.out.println(boo);
		}
	}	
	
	public void printAlreadyPerformed(){		
		for(ServerTactic boo: alreadyPerformed){
			System.out.println(boo);
		}
	}
	
	public void printProbabilityArray(){
		for(Double boo: probabilityArray){
			System.out.println(boo);
		}
	}

}
