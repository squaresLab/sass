package main;

import java.util.ArrayList;
import java.util.HashMap;

import javax.print.attribute.standard.Severity;

import ec.gp.GPData;
import omnet.components.OmnetComponent;
import omnet.components.ServerA;
import omnet.components.ServerB;
import omnet.components.ServerC;
import omnet.components.ServerD;
import omnet.components.ServerE;
import omnet.components.ServerF;
import omnet.components.ServerG;
import omnet.tactics.DecreaseDimmerLevel;
import omnet.tactics.DecreaseTrafficLevel;
import omnet.tactics.IncreaseDimmerLevel;
import omnet.tactics.IncreaseTrafficLevel;
import omnet.tactics.ShutdownServer;
import omnet.tactics.StartNewServer;

public class OmnetStateData extends GPData{
	static final int MaxServerCount=5;
	ArrayList<ServerA> Aservers;
	ArrayList<ServerB> Bservers;
	ArrayList<ServerC> Cservers;
	ArrayList<ServerD> Dservers;
	//ArrayList<ServerE> Eservers;
	//ArrayList<ServerF> Fservers;
	//ArrayList<ServerG> Gservers;
	ArrayList<ArrayList<? extends OmnetComponent>> serverList;
	HashMap<Class<? extends OmnetComponent>,ArrayList<? extends OmnetComponent>> serverSetLookup;
	int totalTime = 0;
	double normalProfitPerSecond=10;
	double dimmedProfitPerSecond=1.5;
	boolean allStatesValid=true;
	String reasonForAllStatesValidSetting="all states are assumed to be initially true";
	public static final int SYSTEM_DEMAND=1000;

	public OmnetStateData(){
		this.initializeState();
	}

	public void initializeState(){
		serverList = new ArrayList<ArrayList<? extends OmnetComponent>>();
		Aservers = new ArrayList<ServerA>();
		Aservers.add(new ServerA());
		Bservers = new ArrayList<ServerB>();
		Bservers.add(new ServerB());
		Cservers = new ArrayList<ServerC>();
		Cservers.add(new ServerC());
		Dservers = new ArrayList<ServerD>();
		Dservers.add(new ServerD());
		//Eservers = new ArrayList<ServerE>();
		//Eservers.add(new ServerE());
		//Fservers = new ArrayList<ServerF>();
		//Fservers.add(new ServerF());
		//Gservers = new ArrayList<ServerG>();
		//Gservers.add(new ServerG());
		serverList.add(Aservers);
		serverList.add(Bservers);
		serverList.add(Cservers);
		serverList.add(Dservers);
		//serverList.add(Eservers);
		//serverList.add(Fservers);
		//serverList.add(Gservers);
		serverSetLookup = new HashMap<Class<? extends OmnetComponent>, ArrayList<? extends OmnetComponent>>();
		serverSetLookup.put(ServerA.class,Aservers);
		serverSetLookup.put(ServerB.class,Bservers);
		serverSetLookup.put(ServerC.class,Cservers);
		serverSetLookup.put(ServerD.class,Dservers);
		//serverSetLookup.put(ServerE.class,Eservers);
		//serverSetLookup.put(ServerF.class,Fservers);
		//serverSetLookup.put(ServerG.class,Gservers);
		totalTime=0;
		allStatesValid=true;
		reasonForAllStatesValidSetting="all states are assumed to be initially true";
	}

	public int getTotalTime(){
		return totalTime;
	}

	public int getTotalServerCount(){
		int total = 0;
		for(ArrayList<? extends OmnetComponent> cList: serverList){
			total+=cList.size();
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
		for(ArrayList<? extends OmnetComponent> cList: serverList){
			for (OmnetComponent server: cList){
				totalCost += server.getCostPerSecond();
			}
		}
		return totalCost;
	}

	public double requestsHandledPerSecond(){
		int totalTrafficLevel = 0;
		for(ArrayList<? extends OmnetComponent> cList: serverList){
			for(OmnetComponent server: cList){
				totalTrafficLevel+=server.getTrafficLevel();
			}
		}
		double requestsPerTrafficLevel = ((double)SYSTEM_DEMAND)/totalTrafficLevel;
		double totalRequestsHandled = 0;
		for(ArrayList<? extends OmnetComponent> cList: serverList){
			for(OmnetComponent server: cList){
				double currentDimmerPercentage = ((double)server.getDimmerLevel())/server.getMaxDimmerLevel();
				double currentServerCapacity = currentDimmerPercentage * server.getDimmedRequestsHandledPerMinute()
						+ (1-currentDimmerPercentage) * server.getNormalRequestsHandledPerMinute();
				//The server either handles the total number of requests sent to the server or
				// the max amount it can
				long requestsSentToServer = Math.round(server.getTrafficLevel()*requestsPerTrafficLevel);
				if(requestsSentToServer > currentServerCapacity){
					totalRequestsHandled+=currentServerCapacity;
				} else {
					totalRequestsHandled+=requestsSentToServer;
				}
			}
		}
		return totalRequestsHandled;
	}

	public double currentGrossIncome(){
		double totalGrossIncome=0;

		int totalTrafficLevel = 0;
		for(ArrayList<? extends OmnetComponent> cList: serverList){
			for(OmnetComponent server: cList){
				totalTrafficLevel+=server.getTrafficLevel();
			}
		}
		double requestsPerTrafficLevel = ((double)SYSTEM_DEMAND)/totalTrafficLevel;
		for(ArrayList<? extends OmnetComponent> cList: serverList){
			for(OmnetComponent server: cList){			
				double currentDimmerPercentage = ((double)server.getDimmerLevel())/server.getMaxDimmerLevel();
				double currentDimmedRequests = currentDimmerPercentage * server.getDimmedRequestsHandledPerMinute();
				double currentNormalRequests = (1-currentDimmerPercentage) * server.getNormalRequestsHandledPerMinute();
				if(server.getTrafficLevel()*requestsPerTrafficLevel >= currentDimmedRequests + currentNormalRequests){
					totalGrossIncome += normalProfitPerSecond * currentNormalRequests + 
							dimmedProfitPerSecond * currentDimmedRequests;	
				} else {
					//use as much of the normal requests as possible and then use the 
					//rest at the dimmed request rate
					long requestsLeft = Math.round(server.getTrafficLevel()*requestsPerTrafficLevel);
					if(requestsLeft > currentNormalRequests){
						totalGrossIncome += normalProfitPerSecond * currentNormalRequests;
						requestsLeft = requestsLeft - Math.round(currentNormalRequests);
						totalGrossIncome += dimmedProfitPerSecond * requestsLeft;
					} else {
						//just make all the requests normal requests
						totalGrossIncome += normalProfitPerSecond * requestsLeft;
					}
				}
			}
		}
		return totalGrossIncome;
	}

	public double singleObjectiveScore(){
		if(areAllStatesValid()){
			double totalProfit=0;

			int totalTrafficLevel = 0;
			for(ArrayList<? extends OmnetComponent> cList: serverList){
				if(cList.size()> 0){
					totalTrafficLevel += cList.get(0).getTrafficLevel()*cList.size();
				}
			}
			double requestsPerTrafficLevel = ((double)SYSTEM_DEMAND)/totalTrafficLevel;
			for(ArrayList<? extends OmnetComponent> singleLocList: serverList){
				if(singleLocList.size() > 0){		
					double currentDimmerPercentage = ((double)singleLocList.get(0).getDimmerLevel())/singleLocList.get(0).getMaxDimmerLevel();
					double currentDimmedRequests = currentDimmerPercentage * singleLocList.get(0).getDimmedRequestsHandledPerMinute()*singleLocList.size();
					double currentNormalRequests = (1-currentDimmerPercentage) * singleLocList.get(0).getNormalRequestsHandledPerMinute()*singleLocList.size();
					if(singleLocList.get(0).getTrafficLevel()*requestsPerTrafficLevel * singleLocList.size()
							>= currentDimmedRequests + currentNormalRequests){
						totalProfit += normalProfitPerSecond * currentNormalRequests + 
								dimmedProfitPerSecond * currentDimmedRequests;	
					} else {
						//use as much of the normal requests as possible and then use the 
						//rest at the dimmed request rate
						long requestsLeft = Math.round(singleLocList.get(0).getTrafficLevel()*requestsPerTrafficLevel* singleLocList.size());
						if(requestsLeft > currentNormalRequests){
							totalProfit+= normalProfitPerSecond * currentNormalRequests;
							requestsLeft = requestsLeft - Math.round(currentNormalRequests);
							totalProfit += dimmedProfitPerSecond * requestsLeft;
						} else {
							//just make all the requests normal requests
							totalProfit += normalProfitPerSecond * requestsLeft;
						}
					}
					totalProfit -= singleLocList.get(0).getCostPerSecond() * singleLocList.size();
				}
			}
			return totalProfit;
		}
		else {
			return 0;
		}
	}

	@SuppressWarnings("unchecked")
	public <T extends OmnetComponent> void performTactic(StartNewServer s, Class<T> c){
		ArrayList<T> serverList = (ArrayList<T>)((ArrayList<OmnetComponent>)serverSetLookup.get(c));
		if(serverList.size()+1>MaxServerCount){
			setAllStatesValid(false, "unable to start up "+c.toString()
			+" there are already the max amount of servers"
			+ "at that location");
		} else{
			try {
				T item;
				if(serverList.size() > 0){
					//performing a shallow copy of item
					//currently not a problem because
					//OmnetComponent doesn't contain 
					//objects as fields, but you may
					//want to update this to a
					//deep copy if that changes
					item=(T)(serverList.get(0).clone());
				} else {
					item=c.newInstance();
				}
				serverList.add(item);
				totalTime+=s.getLatency();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public <T extends OmnetComponent> void performTactic(ShutdownServer s, Class<T> c) {
		ArrayList<T> serverList = (ArrayList<T>)((ArrayList<OmnetComponent>)serverSetLookup.get(c));
		if (serverList.size() == 0){
			setAllStatesValid(false,"unable to shutdown "
					+c.toString()+ ".  There are no "
					+ "active servers of type "+c.toString());
		}	else if(getTotalServerCount()==1){
			setAllStatesValid(false, "unable to shutdown"
					+ c.toString()+ ".  The"
					+ "system would become unoperationable due"
					+ "to no servers being active.");
		} else {
			serverList.remove(serverList.size()-1);
			totalTime+=s.getLatency();
		}

	}

	public <T extends OmnetComponent> void performTactic(IncreaseDimmerLevel d, Class<T> c){
		ArrayList<T> serverList = (ArrayList<T>)((ArrayList<OmnetComponent>)serverSetLookup.get(c));
		if(serverList.size()==0){
			setAllStatesValid(false, "unable to increase dimmer level for"
					+c.toString()+". There are no active servers of that type.");
		}else if(serverList.get(0).getDimmerLevel()==serverList.get(0).getMaxDimmerLevel()){
			setAllStatesValid(false, "unable to increase dimmer level for"
					+c.toString()+". The dimmer level is already the highest possible"+
					" in the state.");
		} else{
			for(T server : serverList){
				server.setDimmerLevel(server.getDimmerLevel()+1, this);
			}
			totalTime+=d.getLatency();
		}
	}

	public <T extends OmnetComponent> void performTactic(DecreaseDimmerLevel d, Class<T> c){
		ArrayList<T> serverList = (ArrayList<T>)((ArrayList<OmnetComponent>)serverSetLookup.get(c));
		if(serverList.size()==0){
			setAllStatesValid(false, "unable to decrease dimmer level for"
					+c.toString()+". There are no active servers of that type.");
		}else if(serverList.get(0).getDimmerLevel()==0){
			setAllStatesValid(false, "unable to decrease dimmer level for"
					+c.toString()+". The dimmer level is already the lowest possible"+
					" in the state.");
		} else{
			for(T server : serverList){
				server.setDimmerLevel(server.getDimmerLevel()-1, this);
			}
			totalTime+=d.getLatency();
		}
	}


	public <T extends OmnetComponent> void performTactic(IncreaseTrafficLevel t, Class<T> c){
		ArrayList<T> serverList = (ArrayList<T>)((ArrayList<OmnetComponent>)serverSetLookup.get(c));
		if(serverList.size()==0){
			setAllStatesValid(false, "unable to increase traffic level for"
					+c.toString()+". There are no active servers of that type.");
		}else
			if(serverList.get(0).getTrafficLevel()==serverList.get(0).getMaxTrafficLevel()){
				setAllStatesValid(false,"unable to increase traffic level for"
						+c.toString()+". The traffic level is already the highest possible"+
						" in the state.");
			} else{
				for(T server : serverList){
					server.setTrafficLevel(server.getTrafficLevel()+1, this);
				}
			}
		totalTime+=t.getLatency();
	}

	public <T extends OmnetComponent> void performTactic(DecreaseTrafficLevel t, Class<T> c){
		ArrayList<T> serverList = (ArrayList<T>)((ArrayList<OmnetComponent>)serverSetLookup.get(c));
		if(serverList.size()==0){
			setAllStatesValid(false, "unable to decrease traffic level for"
					+c.toString()+". There are no active servers of that type.");
		}else if(serverList.get(0).getTrafficLevel()==0){
			setAllStatesValid(false, "unable to decrease traffic level for"
					+c.toString()+". The traffic level is already the lowest possible"+
					" in the state.");
		} else{
			for(T server : serverList){
				server.setTrafficLevel(server.getTrafficLevel()-1, this);
			}
		}
		totalTime+=t.getLatency();
	}

}


