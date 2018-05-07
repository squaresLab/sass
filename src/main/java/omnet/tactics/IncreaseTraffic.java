package omnet.tactics;

import java.util.ArrayList;

import omnet.Omnet;
import omnet.components.Datacenter;
import omnet.components.Server;
import system.SystemState;
import tactics.FailableTactic;

public class IncreaseTraffic extends FailableTactic {

	// set statically for now
	private static double failChance = 0.05;
	private static long latency = 5;
	
	private String server;
	
	public IncreaseTraffic(String serverName){
		server = serverName;
	}
	
	@Override
	public void visit(SystemState systemState) {
		
		Omnet omnet = (Omnet) systemState;
		
		Datacenter center = omnet.getServer(server);
		
		if (center.getTraffic() < center.MAX_TRAFFIC_LVL){
			
			center.setTraffic(center.getTraffic()+1);
			
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
		
		omnet.getServer(server).setTraffic(omnet.getServer(server).getTraffic()-1);
	
	}
	
	public String toString() {
		 return "IncreaseTraffic"+server;
		}

	@Override
	public long getExecutionTime() {
		return latency;
	}

	public String getServer() {
		return server;
	}

}
