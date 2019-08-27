package omnet.tactics;

import java.util.ArrayList;

import omnet.Omnet;
import omnet.components.Datacenter;
import omnet.components.Server;
import system.SystemState;
import tactics.FailableTactic;

public class IncreaseDimmer extends FailableTactic {

	// set statically for now
	//private static double failChance = 0.05;
	private static long latency = 1;
	
	private String server;

	public IncreaseDimmer(String serverName){
		server = serverName;
		failChance = 0.05;
	}
	
	@Override
	public void visit(SystemState systemState) {
		
		Omnet omnet = (Omnet) systemState;
		
		Datacenter center = omnet.getServer(server);
		
		if (center.getDimmer() < center.MAX_DIMMER_LVL){
			
			center.setDimmer(center.getDimmer()+1);
			
		}else{
			this.setFailed(true);
		}
		
	}

	@Override
	public void undo(SystemState systemState) {
		
		Omnet omnet = (Omnet) systemState;
		
		omnet.getServer(server).setDimmer(omnet.getServer(server).getDimmer()-1);
		
	}
	
	public String toString() {
		 return "IncreaseDimmer"+server;
		}

	@Override
	public long getExecutionTime() {
		return latency ;
	}

	public String getServer() {
		return server;
	}

}
