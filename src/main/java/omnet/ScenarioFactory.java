package omnet;

import omnet.Omnet.Seams2018Scenario;
import omnet.Scenario.TacticHandler;
import omnet.tactics.DecreaseDimmer;
import omnet.tactics.DecreaseTraffic;
import omnet.tactics.IncreaseDimmer;
import omnet.tactics.IncreaseTraffic;
import omnet.tactics.ShutdownServer;
import omnet.tactics.StartServer;
import tactics.FailableTactic;

import java.util.Random;

public class ScenarioFactory {
	
	private Class[] tacticClasses = {DecreaseDimmer.class, IncreaseDimmer.class, DecreaseTraffic.class, IncreaseTraffic.class, ShutdownServer.class, StartServer.class};
	
	private Random random = new Random();
	
	public enum ScenarioType {
		newDataCenter, lossOfTactic, changeToConstants;
	}

	public static Scenario getDefault() {
		return new Scenario();
	}
	
	public static Scenario fromString(String scenarioName) {
		return getScenario(Seams2018Scenario.fromString(scenarioName));
	}
	
	// This method will take a scenario and perform a change operation on it
	// used to get a new scenario, can be used multiple times for more changes
	public void mutateScenario(Scenario scenario) {
		double roll = random.nextDouble();
		
		// 10% flip the bit, 20% tactic failrate change
		if (roll < .1) {
			// flip the bit on 4 serv enabled
			scenario.fourservEnabled = Boolean.logicalXor(scenario.fourservEnabled, true);
		}else if (roll < .3) {
			// pick a tactic to change
			int index = random.nextInt(tacticClasses.length);
			double failchance = random.nextDouble();
			TacticHandler th = getFailchanceHandler(tacticClasses[index],failchance);
		}else {
			// pick a paramter to change
			// ignoreing latency for now
			int paramRoll = random.nextInt(4);
			if (paramRoll == 0) {
				// change the demand
				scenario.SYSTEM_DEMAND *= random.nextDouble()*2;
			}else{
				Double[] a = null;
				int maxChange = 0;
				switch(paramRoll) {
				case 1:
					a = toObjectArray(scenario.cost);
					maxChange = 1; break;
				case 2: 
					a = toObjectArray(scenario.power);
					maxChange = 300; break;
				case 3:
					a = toObjectArray(scenario.powerPerNormal);
					maxChange = 3; break;
				}
				mutateArray(a,maxChange);
			}
		}
		
	}
	
	// pick a random element of array, plus or minus by a uniform random value up to maxChange
	private <T> void mutateArray(T[] a, int maxChange) {
		int index = random.nextInt(a.length);
		if (a instanceof Double[]) {
			double change = random.nextInt(maxChange+1);
			change += random.nextDouble();
			if (random.nextBoolean()) {
				change *= -1;
			}
			Double[] aDouble = (Double[]) a;
			aDouble[index] += change;
		} else if (a instanceof Long[]) {
			Long change = (long) random.nextInt(a.length);
			if (random.nextBoolean()) {
				change *= -1;
			}
			Long[] aLong = (Long[]) a;
			aLong[index] += change;
		}
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
	// add a handler to multiply the tactic by a coefficient
	private TacticHandler getFailchanceHandler(Class c, double failchancecoeff) {
		return (tactic)->{
			if (tactic instanceof FailableTactic) {
				FailableTactic ft = (FailableTactic) tactic;
				if (c.isInstance(tactic)){
					StartServer s = (StartServer) tactic;
					ft.setFailChance(ft.getFailChance()*failchancecoeff);
				}
			}
		};
	}
	
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
	
	private Double[] toObjectArray(double[] array) {
		Double[] ans = new Double[array.length];
		for (int i = 0; i < array.length; i++) {
			ans[i] = (Double) array[i];
		}
		return ans;
	}

}
