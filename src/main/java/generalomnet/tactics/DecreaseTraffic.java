package generalomnet.tactics;

import generalomnet.Omnet;
import generalomnet.components.Server;
import system.SystemState;
import tactics.FailableTactic;

public class DecreaseTraffic extends FailableTactic {

	// set statically for now
	private static double failChance = 0.05;
	
	private String server;

	public DecreaseTraffic(String serverName){
		server = serverName;
	}
	
	@Override
	public void visit(SystemState systemState) {
		
		Omnet omnet = (Omnet) systemState;
		
		// first, get the target server
		Server target = omnet.getServer(server);
		
		// next, decrease that servers traffic
		target.setTraffic(target.getTraffic()-1);
		
	}

	@Override
	public double getFailChance() {
		return failChance;
	}

	@Override
	public void undo(SystemState systemState) {
		
		Omnet omnet = (Omnet) systemState;
		
		// first, get the target server
		Server target = omnet.getServer(server);
		
		// next, increase that servers traffic
		target.setTraffic(target.getTraffic()+1);
		
	}
	
	public String toString() {
		 return "DecreaseTraffic"+server;
		}

}
