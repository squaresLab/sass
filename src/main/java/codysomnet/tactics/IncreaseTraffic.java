package codysomnet.tactics;

import codysomnet.Omnet;
import codysomnet.components.Server;
import system.SystemState;
import tactics.FailableTactic;

public class IncreaseTraffic extends FailableTactic {

	// set statically for now
	private static double failChance = 0.05;
	
	private String server;
	
	public IncreaseTraffic(String serverName){
		server = serverName;
	}
	
	@Override
	public void visit(SystemState systemState) {
		
		Omnet omnet = (Omnet) systemState;
		
		// first, get the target server
		Server target = omnet.getServer(server);
		
		
		if (target != null){
		// next, increase that servers traffic
		target.setTraffic(target.getTraffic()+1);
		}else{
			this.setFailed(true);
		}
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
		
		// next, decrease that servers traffic
		if (target != null)
		target.setTraffic(target.getTraffic()-1);
		
	}
	
	public String toString() {
		 return "IncreaseTraffic"+server;
		}

}
