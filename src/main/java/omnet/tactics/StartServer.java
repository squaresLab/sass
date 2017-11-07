package omnet.tactics;

import omnet.Omnet;
import omnet.Omnet.Scenario;
import omnet.components.Server;
import omnet.components.ServerFactory;
import system.SystemState;
import tactics.FailableTactic;

public class StartServer extends FailableTactic {

	private double failChance = 0.1;
	private long latency;
	
	private String server;

	public StartServer(String serverName){
		server = serverName;
	}
	
	@Override
	public void visit(SystemState systemState) {
		
		Omnet omnet = (Omnet) systemState;
		
		String location = server;
		
		// figure out how many servers at that location are running
		int running = omnet.getServer(location).getServers();
		
		if (running < omnet.MAX_SERVER_COUNT_PER_LOC){
			
			/*
			// change fail chance depending on scenario
			if (omnet.getScenario().equals(Scenario.failc) && location.equals("C")){
				failChance
			}
			*/
			
			latency = omnet.getServer(location).getLatency();
			
			// add it to the list
			omnet.getServer(location).setServers(running+1);
			
		}else{
			setFailed(true);
			return;
		}	
		
	}
	
	@Override
	public void undo(SystemState systemState) {
		
		Omnet omnet = (Omnet) systemState;
		
		int running = omnet.getServer(server).getServers();
		
		omnet.getServer(server).setServers(running - 1);
		
	}
	
	public void setFailChance(double p){
		failChance = p;
	}
	
	@Override
	public double getFailChance() {
		return failChance;
	}
	
	public String toString() {
		 return "StartServer"+server;
		}

	public String getServer(){
		return server;
	}
	
	@Override
	public long getExecutionTime() {
		switch (server) {
			case "A": case "B": case "C":
			return 120;
			case "D": return 60;
			default: return 30;
		}
	}

}
