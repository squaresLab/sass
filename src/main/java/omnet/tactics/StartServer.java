package omnet.tactics;

import omnet.Omnet;
import omnet.components.Server;
import omnet.components.ServerFactory;
import system.SystemState;
import tactics.FailableTactic;

public class StartServer extends FailableTactic {

	// set statically for now
	private static double failChance = 0.1;
	private static double latency;
	
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
			}
			
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

	@Override
	public double getFailChance() {
		return failChance;
	}
	
	public String toString() {
		 return "StartServer"+server;
		}

	@Override
	public double getTime() {
		return latency;
	}

}
