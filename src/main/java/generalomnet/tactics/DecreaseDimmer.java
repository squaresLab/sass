package generalomnet.tactics;

import generalomnet.Omnet;
import generalomnet.components.Server;
import system.SystemState;
import tactics.FailableTactic;

public class DecreaseDimmer extends FailableTactic {

	// set statically for now
	private static double failChance = 0.05;
	
	private String server;
	
	public DecreaseDimmer(String serverName){
		server = serverName;
	}
	
	@Override
	public void visit(SystemState systemState) {
		
		Omnet omnet = (Omnet) systemState;
		
		// first, get the target server
		Server target = omnet.getServer(server);
		
		if (target != null){
		
		// next, decrease that servers dimmer
		target.setDimmer(target.getDimmer()-1);
		
		} else {
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
		
		// next, increase that servers dimmer
		if (target != null)
		target.setDimmer(target.getDimmer()+1);
		
	}
	
	public String toString() {
	 return "DecreaseDimmer"+server;
	}

	@Override
	public Object clone() {
		return new DecreaseDimmer(server);
	}


}
