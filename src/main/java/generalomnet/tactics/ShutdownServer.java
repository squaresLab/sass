package generalomnet.tactics;

import generalomnet.Omnet;
import generalomnet.components.Server;
import system.SystemState;
import tactics.FailableTactic;

public class ShutdownServer extends FailableTactic {

	// set statically for now
	private static double failChance = 0.05;
	
	private String server;
	
	private Server removed;

	public ShutdownServer(String serverName){
		server = serverName;
		removed = null;
	}
	
	@Override
	public void visit(SystemState systemState) {
		
		Omnet omnet = (Omnet) systemState;
		
		removed = omnet.getServer(server);
		
		if (removed == null){
			setFailed(true);
			return;
		}
		
		// remove the server from the servers list
		omnet.getServers().remove(removed);
				
	}

	@Override
	public double getFailChance() {
		return failChance;
	}

	@Override
	public void undo(SystemState systemState) {
		
		Omnet omnet = (Omnet) systemState;
		
		removed = omnet.getServer(server);
		
		if (removed != null){
			
			// re-add the server from the servers list
			omnet.getServers().add(removed);

		}
		
	}
	public String toString() {
		 return "ShutdownServer"+server;
		}

}
