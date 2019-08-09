package omnet;

import omnet.Omnet.Seams2018Scenario;
import omnet.tactics.StartServer;

public class ScenarioFactory {

	public static Scenario fromString(String scenarioName) {
		return getScenario(Seams2018Scenario.fromString(scenarioName));
	}

	/*
	 * 
	 * 		if (tactic instanceof StartServer){
			StartServer s = (StartServer) tactic;
			if (scenario.equals(Seams2018Scenario.failc) && s.getServer().equals("C")){
				s.setFailChance(1);
			}else if (scenario.equals(Seams2018Scenario.unreliable)){
				s.setFailChance(.66);
			}
	 */
	
	//normal,requests,fourserv,requestsfourserv,econ,failc,unreliable;
	public static Scenario getScenario(Seams2018Scenario seams2018Scenario) {
		Scenario ans = new Scenario();
		switch(seams2018Scenario) {
		case requests:
			ans.SYSTEM_DEMAND = 10000;
			return ans;
		case fourserv:
			ans.fourservEnabled = true;
			return ans;
		case requestsfourserv:
			ans.fourservEnabled = true;
			ans.SYSTEM_DEMAND = 10000;
			return ans;
		case econ:
			double[] costs = ans.getCost();
			for (int i = 0; i < costs.length; i++) {
				costs[i] *= 100;
			}
			return ans;
		case failc:
			ans.tacticHandlers.add((tactic)->{
				if (tactic instanceof StartServer){
					StartServer s = (StartServer) tactic;
					if (s.getServer().equals("C")){
						s.setFailChance(1);
					}
				}
			});
			return ans;
		case unreliable:
			ans.tacticHandlers.add((tactic)->{
				if (tactic instanceof StartServer){
					StartServer s = (StartServer) tactic;
					s.setFailChance(.66);
				}
			});
			return ans;
		case normal: default:
			return ans;
			
		}
	}

}
