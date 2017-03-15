package omnet;

import java.util.ArrayList;

import omnet.Omnet.Scenario;
import omnet.components.Server;
import omnet.components.ServerFactory;
import system.SystemState;
import tactics.Tactic;

public class Omnet extends SystemState {
	
	public enum Scenario {
			Normal,Requests,FourServ,Both,Econ;
		public static Scenario fromString(String name){
			Scenario scenario;
			
			switch (name){
			case "econ": scenario = Scenario.Econ; break;
			case "requests": scenario = Scenario.Requests; break;
			case "fourserv": scenario = Scenario.FourServ; break;
			case "both": scenario = Scenario.Both; break;
			default: scenario = Scenario.Normal; break;
			}
			
			return scenario;
		}
	}

	// requests / sec on the system, assumed constant for now
	public static int SYSTEM_DEMAND = 1000;
	
	private static final double NORMAL_PROFIT_PER_SECOND = 3;

	private static final double DIMMED_PROFIT_PER_SECOND = 1;
	
	private double dimmedResponses,normalResponses,income,cost,latency;

	private static final double MAX_LATENCY_PER_SERVER = 1000;
	
	private ArrayList<Server> servers;
	
	private ServerFactory serverFactory;

	public static final int MAX_SERVER_COUNT_PER_LOC = 5;
	
	private Scenario scenario;
	
	public Omnet(Scenario s){
		
		this.scenario = s;
		
		servers = new ArrayList<Server>();
		serverFactory = new ServerFactory(s);
		
		// we will start with 1 server of each
		servers.add(serverFactory.getA());
		servers.add(serverFactory.getB());
		servers.add(serverFactory.getC());
		if (s.equals(Scenario.FourServ) || s.equals(Scenario.Both)){
			servers.add(serverFactory.getD());
		}
		if (s.equals(Scenario.Requests) || s.equals(Scenario.Both)){
			SYSTEM_DEMAND = 10000;
		}
		/*
		servers.add(serverFactory.getD());
		servers.add(serverFactory.getE());
		servers.add(serverFactory.getF());
		servers.add(serverFactory.getG());
		servers.add(serverFactory.getH());
		servers.add(serverFactory.getI());
		servers.add(serverFactory.getJ());
		servers.add(serverFactory.getK());
		servers.add(serverFactory.getL());
		servers.add(serverFactory.getM());
		servers.add(serverFactory.getN());
		servers.add(serverFactory.getO());
		servers.add(serverFactory.getP());
		*/
		
	}
	
		public double calculateProfit(){
			
			//reset fields
			dimmedResponses = 0; normalResponses = 0; income = 0; cost = 0; latency = 0;
			
			if(isStateValid()){
				
				double totalProfit=0;

				int sumTrafficLevel = 0;
				
				// take sum of traffic level over all servers
				for(int i = 0; i < servers.size(); i++){
					sumTrafficLevel += servers.get(i).getTraffic();
				}
				
				double requestsPerTrafficLevel = ((double)SYSTEM_DEMAND)/sumTrafficLevel;
				
				if (!Double.isFinite(requestsPerTrafficLevel)){
					requestsPerTrafficLevel = 0;
				}
				
				if (requestsPerTrafficLevel == 0){
					latency += SYSTEM_DEMAND;
				}
				
				Server current;
				for(int i =0; i < servers.size(); i++){	
					current = servers.get(i);
					
						double currentDimmerPercentage = ((double)current.getDimmer())
								/current.MAX_DIMMER_LVL;
						
						double currentRequests = requestsPerTrafficLevel * current.getTraffic();
						
						double p = current.getPower();
						double fc = current.getPowerPerNormal();
						double dc = current.getPowerPerDimmed();
						double ra = currentDimmerPercentage;
						
						// use ratio to determine the amount of power to devote to each type of response
						double maxNormal = (p * (1-ra)) / fc;
						double maxDimmed = (p * (ra)) / dc;
						
						double fullReplys,dimmedReplys;
						
						// give normal responses when under low load
						if (currentRequests <= maxNormal){
							
							fullReplys = currentRequests;
							dimmedReplys = 0;
							
						}else{
							
							fullReplys = maxNormal;
							
							// check if we have enough power to fullfill the rest of the requests as dimmed
							if (currentRequests - fullReplys >= maxDimmed){
								dimmedReplys = maxDimmed;
							}else{
								// otherwise fulfill as many as we can
								dimmedReplys = currentRequests - fullReplys;
							}
							
							
							
						}
		
						totalProfit += NORMAL_PROFIT_PER_SECOND * fullReplys + 
								DIMMED_PROFIT_PER_SECOND * dimmedReplys;
								
						totalProfit -= current.getCost();
						
						dimmedResponses += dimmedReplys;
						normalResponses += fullReplys;
						cost += current.getCost();
						income += NORMAL_PROFIT_PER_SECOND * fullReplys + 
								DIMMED_PROFIT_PER_SECOND * dimmedReplys;
						
						/*
						double calcLatency = 1 / (handleable - requests);
						
						if (!Double.isFinite(calcLatency)){
							calcLatency = MAX_LATENCY_PER_SERVER ;
						}
						
						latency += calcLatency;
						*/
						
						latency += currentRequests - fullReplys - dimmedReplys;
						
					
				}
				return totalProfit;
			}
			else {
				// if system is in invalid state return 0
				return 0;
			}
		}
	
	public Server getServer(String name){
		Server server;
		for (int count = 0; count < servers.size(); count++){
			server = servers.get(count);
			
			if (server.getName().equals(name)){
				return server;
			}
			
		}
		
		return null;
	}
	
	public ArrayList<Server> getServers() {
		return servers;
	}

	public void setServers(ArrayList<Server> servers) {
		this.servers = servers;
	}

	@Override
	public boolean isStateValid() {
		// to determine if the system is valid, check to see that each server is valid
		for (int count = 0; count < servers.size(); count++){
			if (!servers.get(count).isValid())
				return false;
		}
		return true;
	}
	
	public Object clone(){
		Omnet copy = new Omnet(scenario);
		
		for (int count = 0; count < history.size(); count++){
			copy.history.add((Tactic) history.get(count).clone());
		}
		
		for (int count = 0; count < servers.size(); count++){
			copy.servers.add((Server) servers.get(count).clone());
		}
		
		return copy;
	}
	
	public double getDimmedResponses() {
		return dimmedResponses;
	}

	public double getNormalResponses() {
		return normalResponses;
	}

	public double getIncome() {
		return income;
	}

	public double getCost() {
		return cost;
	}

	public double getProfit() {
		return income-cost;
	}

	public double getLatency() {
		return latency;
	}

	public int serversUp(String location) {
		
		int index = serverFactory.getIndex(location);
		
		return serverFactory.getNumServers()[index];
		
	}
	
	public ServerFactory getServerFactory(){
		return serverFactory;
	}

	public ArrayList<Server> getServers(String server) {
		
		ArrayList<Server> ans = new ArrayList<Server>();
		
		int num = serversUp(server);
		
		for (int count = 0; count < num; count++){
			
			ans.add(getServer(server+count));
			
		}
		
		return ans;
	}
	
}
