package main.java.main;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import main.java.omnet.components.OmnetComponent;
import main.java.omnet.components.ServerA;
import main.java.omnet.components.ServerB;
import main.java.omnet.components.ServerC;
import main.java.omnet.components.ServerD;
import main.java.omnet.tactics.DecreaseDimmerLevel;
import main.java.omnet.tactics.DecreaseTrafficLevel;
import main.java.omnet.tactics.IncreaseDimmerLevel;
import main.java.omnet.tactics.IncreaseTrafficLevel;
import main.java.omnet.tactics.ShutdownServer;
import main.java.omnet.tactics.StartNewServer;

public class OmnetStatePath implements Serializable{
	public static final int SYSTEM_DEMAND=1000;
	static final int MaxServerCount=5;
	ServerA prototypeServerA;
	int serverCountA;
	ServerB prototypeServerB;
	int serverCountB;
	ServerC prototypeServerC;
	int serverCountC;
	ServerD prototypeServerD;
	int serverCountD;
	int[] countArray;
	OmnetComponent[] serverArray;
	//ArrayList<ServerE> Eservers;
	//ArrayList<ServerF> Fservers;
	//ArrayList<ServerG> Gservers;
	//ArrayList<ArrayList<? extends OmnetComponent>> serverList;
	HashMap<Class<? extends OmnetComponent>,Integer> serverSetLookup;
	int totalTime = 0;
	double normalProfitPerSecond=10;
	double dimmedProfitPerSecond=1.5;
	boolean allStatesValid=true;
	String reasonForAllStatesValidSetting="all states are assumed to be initially true";

	double pathProbability=1;

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
			//return INVALID_PLAN_SCORE+INVALID_ACTION_PENALTY*invalidActionCount;
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

	@SuppressWarnings("unchecked")
	public <T extends OmnetComponent> boolean performTactic(StartNewServer s, Class<T> c){
		int serverIndex = serverSetLookup.get(c).intValue();
		boolean invalidAction=false;
		if(countArray[serverIndex]+1>MaxServerCount){
			setAllStatesValid(false, "unable to start up "+c.toString()
			+" there are already the max amount of servers"
			+ "at that location");
			invalidAction=true;
		} else{
			try {
				T item;
				if(countArray[serverIndex] == 0){
					serverArray[serverIndex]=c.newInstance();
				}
				countArray[serverIndex]++;

			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		totalTime+=s.getLatency();
		pathProbability=pathProbability*(1-s.getFailureWeight());
		return invalidAction;
	}

	public <T extends OmnetComponent> boolean performTactic(ShutdownServer s, Class<T> c) {
		int serverIndex = serverSetLookup.get(c);
		boolean invalidAction=false;
		if (countArray[serverIndex] == 0){
			setAllStatesValid(false,"unable to shutdown "
					+c.toString()+ ".  There are no "
					+ "active servers of type "+c.toString());
			invalidAction=true;
		}	else if(getTotalServerCount()==1){
			setAllStatesValid(false, "unable to shutdown"
					+ c.toString()+ ".  The"
					+ "system would become unoperationable due"
					+ "to no servers being active.");
			invalidAction=true;
		} else {
			countArray[serverIndex]--;
		}
		totalTime+=s.getLatency();
		pathProbability=pathProbability*(1-s.getFailureWeight());
		return invalidAction;

	}

	public <T extends OmnetComponent> boolean performTactic(IncreaseDimmerLevel d, Class<T> c){
		int serverIndex = serverSetLookup.get(c);
		boolean invalidAction=false;
		if(countArray[serverIndex]==0){
			setAllStatesValid(false, "unable to increase dimmer level for"
					+c.toString()+". There are no active servers of that type.");
			invalidAction=true;
		}else if(serverArray[serverIndex].getDimmerLevel()==serverArray[serverIndex].getMaxDimmerLevel()){
			setAllStatesValid(false, "unable to increase dimmer level for"
					+c.toString()+". The dimmer level is already the highest possible"+
					" in the state.");
			invalidAction=true;
		} else{
			serverArray[serverIndex].setDimmerLevel(serverArray[serverIndex].getDimmerLevel()+1, this);
			
		}
		totalTime+=d.getLatency();
		pathProbability=pathProbability*(1-d.getFailureWeight());
		return invalidAction;
	}

	public <T extends OmnetComponent> boolean performTactic(DecreaseDimmerLevel d, Class<T> c){
		int serverIndex = serverSetLookup.get(c).intValue();
		boolean invalidAction=false;
		if(countArray[serverIndex]==0){
			setAllStatesValid(false, "unable to decrease dimmer level for"
					+c.toString()+". There are no active servers of that type.");
			invalidAction=true;
		}else if(serverArray[serverIndex].getDimmerLevel()==0){
			setAllStatesValid(false, "unable to decrease dimmer level for"
					+c.toString()+". The dimmer level is already the lowest possible"+
					" in the state.");
			invalidAction=true;
		} else{
			serverArray[serverIndex].setDimmerLevel(serverArray[serverIndex].getDimmerLevel()-1, this);
		}
		totalTime+=d.getLatency();
		pathProbability=pathProbability*(1-d.getFailureWeight());
		return invalidAction;
	}


	public <T extends OmnetComponent> boolean performTactic(IncreaseTrafficLevel t, Class<T> c){
		int serverIndex = serverSetLookup.get(c).intValue();
		boolean invalidAction=false;
		if(countArray[serverIndex]==0){
			setAllStatesValid(false, "unable to increase traffic level for"
					+c.toString()+". There are no active servers of that type.");
			invalidAction=true;
		}else{
			if(serverArray[serverIndex].getTrafficLevel()==serverArray[serverIndex].getMaxTrafficLevel()){
				setAllStatesValid(false,"unable to increase traffic level for"
						+c.toString()+". The traffic level is already the highest possible"+
						" in the state.");
				invalidAction=true;
			} else{
				serverArray[serverIndex].setTrafficLevel(serverArray[serverIndex].getTrafficLevel()+1, this);
			}
		}
		totalTime+=t.getLatency();
		pathProbability=pathProbability*(1-t.getFailureWeight());
		return invalidAction;
	}

	public <T extends OmnetComponent> boolean performTactic(DecreaseTrafficLevel t, Class<T> c){
		int serverIndex = serverSetLookup.get(c).intValue();
		boolean invalidAction=false;
		if(countArray[serverIndex]==0){
			setAllStatesValid(false, "unable to decrease traffic level for"
					+c.toString()+". There are no active servers of that type.");
			invalidAction=true;
		}else if(serverArray[serverIndex].getTrafficLevel()==0){
			setAllStatesValid(false, "unable to decrease traffic level for"
					+c.toString()+". The traffic level is already the lowest possible"+
					" in the state.");
			invalidAction=true;
		} else{
			serverArray[serverIndex].setTrafficLevel(serverArray[serverIndex].getTrafficLevel()-1, this);
		}
		totalTime+=t.getLatency();
		pathProbability=pathProbability*(1-t.getFailureWeight());
		return invalidAction;
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
}
