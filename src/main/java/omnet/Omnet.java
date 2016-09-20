package generalomnet;

import java.util.ArrayList;

import generalomnet.components.Server;
import system.SystemState;
import tactics.Tactic;

public class Omnet extends SystemState {
	
	// requests / sec on the system, assumed constant for now
	public static final int SYSTEM_DEMAND = 1000;
	
	private static final double NORMAL_PROFIT_PER_SECOND = 10;

	private static final double DIMMED_PROFIT_PER_SECOND = 2;
	
	private static final double POWER_PER_NORMAL_RESPONSE_PER_SECOND = 10;
	
	private static final double POWER_PER_DIMMED_RESPONSE_PER_SECOND = 5;
	
	private double dimmedResponses,normalResponses,income,cost,latency;

	private ArrayList<Server> servers;

	private static final double MAX_LATENCY_PER_SERVER = 1000;
	
	public Omnet(){
		servers = new ArrayList<Server>();
		// starting state is assumed valid
	}
	
	//lower is better - goal is to get as close to zero as possible
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
				Server current;
				for(int i =0; i < servers.size(); i++){	
					current = servers.get(i);
					
						double currentDimmerPercentage = ((double)current.getDimmer())
								/current.MAX_DIMMER_LVL;
						
						double currentRequests = requestsPerTrafficLevel * current.getTraffic();
						
						double p = current.getPower();
						double fc = POWER_PER_NORMAL_RESPONSE_PER_SECOND;
						double dc = POWER_PER_DIMMED_RESPONSE_PER_SECOND;
						double ra = currentDimmerPercentage;
						
						double avgCostPerUser = dc*ra + fc * (1-ra);
						
						double handleable =  p / avgCostPerUser;
						
						double requests = Math.min(handleable, currentRequests);
						
						double fullReplys = requests * (1-ra);
					
						double dimmedReplys = (requests * ra);
						
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
						
						if (currentRequests - fullReplys - dimmedReplys < 0)
							System.out.println("wtf?");
						
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
		Omnet copy = new Omnet();
		
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
	
}
