package omnet.tactics;

import omnet.Omnet;
import omnet.components.Server;
import system.SystemState;
import tactics.FailableTactic;

public class StartServer extends FailableTactic {

	// set statically for now
	private static double failChance = 0.05;
	
	private String server;

	public StartServer(String serverName){
		server = serverName;
	}
	
	@Override
	public void visit(SystemState systemState) {
		
		Omnet omnet = (Omnet) systemState;
		
		Server check = omnet.getServer(server);
		
		if (check != null){
			setFailed(true);
			return;
		}
		
		// instantiate new server
		Server serv = new Server(server);
		// add it to the list
		omnet.getServers().add(serv);
		
		
	}
	
	@Override
	public void undo(SystemState systemState) {
		
		Omnet omnet = (Omnet) systemState;
		
		Server removed = omnet.getServer(server);
		
		// remove the server from the servers list
		omnet.getServers().remove(removed);
				
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
		return 60;
	}

}
