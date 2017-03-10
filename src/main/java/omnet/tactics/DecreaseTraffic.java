package omnet.tactics;

import java.util.ArrayList;

import omnet.Omnet;
import omnet.components.Server;
import system.SystemState;
import tactics.FailableTactic;

public class DecreaseTraffic extends FailableTactic {

	// set statically for now
	private static double failChance = 0.01;
	private static long latency = 5;
	
	private String server;

	public DecreaseTraffic(String serverName){
		server = serverName;
	}
	
	@Override
	public void visit(SystemState systemState) {
		
		Omnet omnet = (Omnet) systemState;
		
		ArrayList<Server> targets = omnet.getServers(server);
		
		if (targets.size() > 0){
			
			for (Server t : targets){
				t.setTraffic(t.getTraffic()-1);
			}
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
		
		ArrayList<Server> targets = omnet.getServers(server);
			
			for (Server t : targets){
				t.setTraffic(t.getTraffic()+1);
			}
		
	}
	
	public String toString() {
		 return "DecreaseTraffic"+server;
		}

	@Override
	public long getExecutionTime() {
		return latency;
	}

}
