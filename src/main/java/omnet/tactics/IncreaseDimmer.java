package omnet.tactics;

import omnet.Omnet;
import omnet.components.Server;
import system.SystemState;
import tactics.FailableTactic;

public class IncreaseDimmer extends FailableTactic {

	// set statically for now
	private static double failChance = 0.05;
	
	private String server;

	public IncreaseDimmer(String serverName){
		server = serverName;
	}
	
	@Override
	public void visit(SystemState systemState) {
		
		Omnet omnet = (Omnet) systemState;
		
		// first, get the target server
		Server target = omnet.getServer(server);
		
		if (target != null){
		
		// next, increase that servers dimmer
		target.setDimmer(target.getDimmer()+1);	
		
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
		
		// next, decrease that servers dimmer
		target.setDimmer(target.getDimmer()-1);
		
	}
	
	public String toString() {
		 return "IncreaseDimmer"+server;
		}

	@Override
	public double getTime() {
		return 15;
	}

}
