package main.java.main;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Stack;

import javax.swing.text.html.HTMLDocument.Iterator;

import ec.gp.GPNode;
import main.java.omnet.components.OmnetComponent;
import main.java.omnet.components.ServerA;
import main.java.omnet.components.ServerB;
import main.java.omnet.components.ServerC;
import main.java.omnet.components.ServerD;
import main.java.omnet.components.ServerE;
import main.java.omnet.components.ServerF;
import main.java.omnet.components.ServerG;
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

	public enum ServerType { SERVERA, SERVERB, SERVERC, SERVERD, SERVERE, SERVERF, SERVERG }

	public int uniqueID = 0;
	public int[] countArray;
	public OmnetComponent[] serverArray;
	//ArrayList<ServerE> Eservers;
	//ArrayList<ServerF> Fservers;
	//ArrayList<ServerG> Gservers;
	//ArrayList<ArrayList<? extends OmnetComponent>> serverList;
	public int totalTime = 0;
	double normalProfitPerSecond=10;
	double dimmedProfitPerSecond=1.5;
	boolean allStatesValid=true;
	public String reasonForAllStatesValidSetting="all states are assumed to be initially true";

	public double pathProbability=1;
	public int invalidActions;

	//record changes during performTactic
	public ArrayDeque<Boolean> modifiedCountArray= new ArrayDeque<Boolean>();
	public ArrayDeque<Boolean> emptyCount= new ArrayDeque<Boolean>();
	public ArrayDeque<Boolean> modifiedDimmerLevel= new ArrayDeque<Boolean>();
	public ArrayDeque<Boolean> modifiedTrafficLevel= new ArrayDeque<Boolean>();
	public ArrayDeque<Double> probabilityArray= new ArrayDeque<Double>();
	public ArrayDeque<ServerTactic> alreadyPerformed = new ArrayDeque<ServerTactic>();
	public boolean undone = false;


	public OmnetStatePath(){
		initializeState();
	}


	public void initializeState(){

		//serverList.add(Eservers);
		//serverList.add(Fservers);
		//serverList.add(Gservers);

		totalTime=0;
		allStatesValid=true;
		reasonForAllStatesValidSetting="all states are assumed to be initially true";
		pathProbability=1;
		countArray = new int[7];
		countArray[0]=1;
		countArray[1]=1;
		countArray[2]=1;
		countArray[3]=1;
		countArray[4]=1;
		countArray[5]=1;
		countArray[6]=1;

		serverArray = new OmnetComponent[7];
		serverArray[ServerType.SERVERA.ordinal()]=new ServerA();
		serverArray[ServerType.SERVERB.ordinal()]=new ServerB();
		serverArray[ServerType.SERVERC.ordinal()]=new ServerC();
		serverArray[ServerType.SERVERD.ordinal()]=new ServerD();
		serverArray[ServerType.SERVERE.ordinal()]=new ServerE();
		serverArray[ServerType.SERVERF.ordinal()]=new ServerF();
		serverArray[ServerType.SERVERG.ordinal()]=new ServerG();
		probabilityArray.add(pathProbability);
//		modifiedCountArray.add(null);
//		modifiedDimmerLevel.add(null);
//		modifiedTrafficLevel.add(null);
//		emptyCount.add(null);

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
		if(alreadyPerformed.peekLast() != null){
			ServerTactic s = alreadyPerformed.pollLast();
			s.reallyUndo(this);
		}
	}

	public void performFailure(GPNode s){
		if(s != null && s instanceof ServerTactic){
			((ServerTactic) s).failForSure(this);
		}
		else if(s.children[0] != null && s.children[0] instanceof ServerTactic){
			((ServerTactic) s.children[0]).failForSure(this);
		}
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
	
	public String printModifiedCountArray(){
		return modifiedCountArray.toString();
		
	}


	@Override
	public boolean equals(Object state) {
		if(state == null)
		{
			return false;
		}
		if (state == this)
		{
			return true;
		}
		if (getClass() != state.getClass())
		{
			return false;
		}

		OmnetStatePath s = (OmnetStatePath) state;
		
		Object[] count = this.modifiedCountArray.toArray();
		


		return this.totalTime == s.totalTime &&
				this.pathProbability == s.pathProbability &&
				this.serverArray[ServerType.SERVERA.ordinal()].toString().equals(s.serverArray[ServerType.SERVERA.ordinal()].toString()) &&
				this.serverArray[ServerType.SERVERB.ordinal()].toString().equals(s.serverArray[ServerType.SERVERB.ordinal()].toString()) &&
				this.serverArray[ServerType.SERVERC.ordinal()].toString().equals(s.serverArray[ServerType.SERVERC.ordinal()].toString()) &&
				this.serverArray[ServerType.SERVERD.ordinal()].toString().equals(s.serverArray[ServerType.SERVERD.ordinal()].toString()) &&
				this.serverArray[ServerType.SERVERE.ordinal()].toString().equals(s.serverArray[ServerType.SERVERA.ordinal()].toString()) &&
				this.serverArray[ServerType.SERVERF.ordinal()].toString().equals(s.serverArray[ServerType.SERVERB.ordinal()].toString()) &&
				this.serverArray[ServerType.SERVERG.ordinal()].toString().equals(s.serverArray[ServerType.SERVERC.ordinal()].toString()) &&
				this.countArray[ServerType.SERVERA.ordinal()] == s.countArray[ServerType.SERVERA.ordinal()]&&
				this.countArray[ServerType.SERVERB.ordinal()] == s.countArray[ServerType.SERVERB.ordinal()]&&
				this.countArray[ServerType.SERVERC.ordinal()] == s.countArray[ServerType.SERVERC.ordinal()]&&
				this.countArray[ServerType.SERVERD.ordinal()] == s.countArray[ServerType.SERVERD.ordinal()]&&
				this.countArray[ServerType.SERVERE.ordinal()] == s.countArray[ServerType.SERVERB.ordinal()]&&
				this.countArray[ServerType.SERVERF.ordinal()] == s.countArray[ServerType.SERVERC.ordinal()]&&
				this.countArray[ServerType.SERVERG.ordinal()] == s.countArray[ServerType.SERVERD.ordinal()]&&
				this.modifiedCountArray.toString() == s.modifiedCountArray.toString() &&
				this.modifiedDimmerLevel.toString() == s.modifiedDimmerLevel.toString() &&
				this.modifiedTrafficLevel.toString() == s.modifiedDimmerLevel.toString() &&
				this.emptyCount.toString() == s.emptyCount.toString();



		// return this.getUniqueID() == s.getUniqueID();
	}

	@Override
	public int hashCode()
	{
		final int PRIME = 31;
		int result = 1;
		result = PRIME * result + totalTime;
		result = PRIME * result + (int)pathProbability;
		result = PRIME * result + serverArray[ServerType.SERVERA.ordinal()].toString().hashCode();
		result = PRIME * result + serverArray[ServerType.SERVERB.ordinal()].toString().hashCode();
		result = PRIME * result + serverArray[ServerType.SERVERC.ordinal()].toString().hashCode();
		result = PRIME * result + serverArray[ServerType.SERVERD.ordinal()].toString().hashCode();
		result = PRIME * result + serverArray[ServerType.SERVERE.ordinal()].toString().hashCode();
		result = PRIME * result + serverArray[ServerType.SERVERF.ordinal()].toString().hashCode();
		result = PRIME * result + serverArray[ServerType.SERVERG.ordinal()].toString().hashCode();
		result = PRIME * result + countArray[ServerType.SERVERA.ordinal()];
		result = PRIME * result + countArray[ServerType.SERVERB.ordinal()];
		result = PRIME * result + countArray[ServerType.SERVERC.ordinal()];
		result = PRIME * result + countArray[ServerType.SERVERD.ordinal()];
		result = PRIME * result + countArray[ServerType.SERVERE.ordinal()];
		result = PRIME * result + countArray[ServerType.SERVERF.ordinal()];
		result = PRIME * result + countArray[ServerType.SERVERG.ordinal()];
		result = PRIME * result + modifiedCountArray.toString().hashCode();
		result = PRIME * result + modifiedDimmerLevel.toString().hashCode();
		result = PRIME * result + modifiedTrafficLevel.toString().hashCode();
		result = PRIME * result + emptyCount.toString().hashCode();
		return result;


	}






}
