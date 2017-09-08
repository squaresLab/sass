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
		int running = omnet.serversUp(location);
		
		if (running < omnet.MAX_SERVER_COUNT_PER_LOC){
			
			ServerFactory factory = omnet.getServerFactory();
			Server serv = null;
			
			switch(location){
				case "A": serv = factory.getA(); break;
				case "B": serv = factory.getB(); break;
				case "C": serv = factory.getC(); break;
				case "D": serv = factory.getD(); break;
				case "E": serv = factory.getE(); break;
				case "F": serv = factory.getF(); break;
				case "G": serv = factory.getG(); break;
				case "H": serv = factory.getH(); break;
				case "I": serv = factory.getI(); break;
				case "J": serv = factory.getJ(); break;
				case "K": serv = factory.getK(); break;
				case "L": serv = factory.getL(); break;
				case "M": serv = factory.getM(); break;
				case "N": serv = factory.getN(); break;
				case "O": serv = factory.getO(); break;
				case "P": serv = factory.getP(); break;
			}
			/*
			// change fail chance depending on scenario
			if (omnet.getScenario().equals(Scenario.failc) && location.equals("C")){
				failChance
			}
			*/
			
			latency = serv.getLatency();
			
			// add it to the list
			omnet.getServers().add(serv);
		}else{
			setFailed(true);
			return;
		}	
		
	}
	
	@Override
	public void undo(SystemState systemState) {
		
		Omnet omnet = (Omnet) systemState;
		
		int running = omnet.serversUp(server);
		
		String serverInstance = server + (running - 1);
		
		Server removed = omnet.getServer(serverInstance);
		
		// remove the server from the servers list
		omnet.getServers().remove(removed);
		
		// now update the count in the factory
		int index = omnet.getServerFactory().getIndex(server);
		omnet.getServerFactory().getNumServers()[index]--;
				
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
