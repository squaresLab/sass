package generalomnet;

import java.util.ArrayList;

import generalomnet.components.Server;
import system.SystemState;
import tactics.Tactic;

public class Omnet extends SystemState {
	
	// requests / sec on the system, assumed constant for now
	public static final int SYSTEM_DEMAND = 1000;
	
	private static final double NORMAL_PROFIT_PER_SECOND = 10;

	private static final double DIMMED_PROFIT_PER_SECOND = 1.5;
	
	private ArrayList<Server> servers;
	
	public Omnet(){
		servers = new ArrayList<Server>();
		// starting state is assumed valid
	}
	
	//lower is better - goal is to get as close to zero as possible
		public double calculateProfit(){
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
								/servers.get(i).MAX_DIMMER_LVL;
						
						double currentDimmedRequests = currentDimmerPercentage * requestsPerTrafficLevel * current.getTraffic();
						double currentNormalRequests = (1-currentDimmerPercentage) * requestsPerTrafficLevel * current.getTraffic();
						
						totalProfit += NORMAL_PROFIT_PER_SECOND * currentNormalRequests + 
								DIMMED_PROFIT_PER_SECOND * currentDimmedRequests;
								
						totalProfit -= servers.get(i).getCost();
						
					
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
	
}
