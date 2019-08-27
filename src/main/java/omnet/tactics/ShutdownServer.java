package omnet.tactics;

import omnet.Omnet;
import omnet.components.Server;
import system.SystemState;
import tactics.FailableTactic;

public class ShutdownServer extends FailableTactic {

	// set statically for now
	//private static double failChance = 0.1;
	private static long latency = 30;
	
	private String server;

	public ShutdownServer(String serverName){
		server = serverName;
		failChance = 0.1;
	}
	
	@Override
	public void visit(SystemState systemState) {
		
		Omnet omnet = (Omnet) systemState;
		
		int running = omnet.getServer(server).getServers();
		
		if (running > 0){
		
			omnet.getServer(server).setServers(running - 1);
			
		
		}else{
			setFailed(true);
			return;
		}
				
	}

	@Override
	public void undo(SystemState systemState) {
		
		Omnet omnet = (Omnet) systemState;
		
		int running = omnet.getServer(server).getServers();
		
		omnet.getServer(server).setServers(running + 1);
		
	}
	
	public String toString() {
		 return "ShutdownServer"+server;
		}

	@Override
	public long getExecutionTime() {
		return latency;
	}

	public String getServer() {
		return server;
	}

}
