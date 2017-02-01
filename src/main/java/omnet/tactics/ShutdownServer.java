package omnet.tactics;

import omnet.Omnet;
import omnet.components.Server;
import system.SystemState;
import tactics.FailableTactic;

public class ShutdownServer extends FailableTactic {

	// set statically for now
	private static double failChance = 0.1;
	private static long latency = 30;
	
	private String server;
	
	private Server removed;

	public ShutdownServer(String serverName){
		server = serverName;
		removed = null;
	}
	
	@Override
	public void visit(SystemState systemState) {
		
		Omnet omnet = (Omnet) systemState;
		
		int running = omnet.serversUp(server);
		
		if (running > 0){
		
			String serverInstance = server + (running - 1);
		
			removed = omnet.getServer(serverInstance);
		
			// remove the server from the servers list
			omnet.getServers().remove(removed);
		
			// now update the count in the factory
			int index = omnet.getServerFactory().getIndex(server);
			omnet.getServerFactory().getNumServers()[index]--;
		
		}else{
			setFailed(true);
			return;
		}
				
	}

	@Override
	public double getFailChance() {
		return failChance;
	}

	@Override
	public void undo(SystemState systemState) {
		
		Omnet omnet = (Omnet) systemState;
			
		// re-add the server from the servers list
		omnet.getServers().add(removed);
		
		// update the count in the factory
		int index = omnet.getServerFactory().getIndex(server);
		omnet.getServerFactory().getNumServers()[index]++;
		
		removed = null;
		
	}
	public String toString() {
		 return "ShutdownServer"+server;
		}

	@Override
	public long getTime() {
		return latency;
	}

}
