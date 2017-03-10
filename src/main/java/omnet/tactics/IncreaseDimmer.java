package omnet.tactics;

import java.util.ArrayList;

import omnet.Omnet;
import omnet.components.Server;
import system.SystemState;
import tactics.FailableTactic;

public class IncreaseDimmer extends FailableTactic {

	// set statically for now
	private static double failChance = 0.05;
	private static long latency = 1;
	
	private String server;

	public IncreaseDimmer(String serverName){
		server = serverName;
	}
	
	@Override
	public void visit(SystemState systemState) {
		
		Omnet omnet = (Omnet) systemState;
		
		ArrayList<Server> targets = omnet.getServers(server);
		
		if (targets.size() > 0){
			
			for (Server t : targets){
				t.setDimmer(t.getDimmer()+1);
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
				t.setDimmer(t.getDimmer()-1);
			}
		
	}
	
	public String toString() {
		 return "IncreaseDimmer"+server;
		}

	@Override
	public long getExecutionTime() {
		return latency ;
	}

}
