package omnet.tactics;

import java.util.ArrayList;

import omnet.Omnet;
import omnet.components.Datacenter;
import omnet.components.Server;
import system.SystemState;
import tactics.FailableTactic;

public class DecreaseTraffic extends FailableTactic {

	// set statically for now
	//private static double failChance = 0.01;
	private static long latency = 5;
	
	private String server;

	public DecreaseTraffic(String serverName){
		server = serverName;
		failChance = 0.01;
	}
	
	@Override
	public void visit(SystemState systemState) {
		
		Omnet omnet = (Omnet) systemState;
		
		Datacenter center = omnet.getServer(server);
		
		if (center.getTraffic() > center.MIN_TRAFFIC_LVL){
			
			center.setTraffic(center.getTraffic()-1);
			
		}else{
			this.setFailed(true);
		}
		
	}

	@Override
	public void undo(SystemState systemState) {
		
		Omnet omnet = (Omnet) systemState;
		
		omnet.getServer(server).setTraffic(omnet.getServer(server).getTraffic()+1);
		
	}
	
	public String toString() {
		 return "DecreaseTraffic"+server;
		}

	@Override
	public long getExecutionTime() {
		return latency;
	}

	public String getServer() {
		return server;
	}

}
