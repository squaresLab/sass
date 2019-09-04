package omnet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import omnet.Scenario.TacticHandler;
import omnet.components.Datacenter;
import omnet.components.DatacenterFactory;
import omnet.tactics.DecreaseDimmer;
import omnet.tactics.DecreaseTraffic;
import omnet.tactics.IncreaseDimmer;
import omnet.tactics.IncreaseTraffic;
import omnet.tactics.ShutdownServer;
import omnet.tactics.StartServer;
import tactics.FailableTactic;
import tactics.Tactic;

public class Scenario {

	public static Class[] tacticClasses = {DecreaseDimmer.class, DecreaseTraffic.class, IncreaseDimmer.class, IncreaseTraffic.class, ShutdownServer.class, StartServer.class};
	
	// omnet constant parameters
	int SYSTEM_DEMAND = 1000;
	
	double NORMAL_PROFIT_PER_SECOND = 3;

	double DIMMED_PROFIT_PER_SECOND = 1;

	// never used
	//double MAX_LATENCY_PER_SERVER = 1000;

	int MAX_SERVER_COUNT_PER_LOC = 5;
	
	// new data center
	boolean fourservEnabled = false;
	
	// constants for each datacenter / server type
	// A B C D
	double[] cost = {.5,.7,1,.5};
	double[] power = {150,200,300,80};
	double[] powerPerNormal = {150.0/50.0, 200.0/130.0, 300.0/150.0, 80.0 / 25.0};
	long[] latency = {120,120,120,60};
	
	// constants for each tactic
	// DD, DT, ID, IT, ShS, StS
	double[] failChance = {0.05,0.01,0.05,0.05,0.1,0.1};
	
	// note that this execution time isnt set yet
	//long[] executionTime = {1,5,1,5,30};
	
	interface TacticHandler {
		void acceptTactic(Tactic tactic);
	}
	
	List<TacticHandler> tacticHandlers;
	
	HashMap<Class,TacticHandler> tacticFailrateHandlers;
	
	public Scenario() {
		tacticHandlers = new ArrayList<TacticHandler>();
		tacticFailrateHandlers = new HashMap<Class,TacticHandler>();
		// initialize handlers w/ defaults
		updateHandlers();
	}
	
	// warning, currently this overwrites all of the existing tactic handlers
	public void updateHandlers() {
		for (int count = 0; count < tacticClasses.length; count++) {
			tacticFailrateHandlers.put(tacticClasses[count], getFailchanceHandler(tacticClasses[count],failChance[count]));
		}
		tacticHandlers = new ArrayList<TacticHandler>();
		tacticHandlers.addAll(tacticFailrateHandlers.values());
	}
	
	public void applyTo(Omnet omnet) {
		updateHandlers();
		omnet.SYSTEM_DEMAND = SYSTEM_DEMAND;
		Omnet.NORMAL_PROFIT_PER_SECOND = NORMAL_PROFIT_PER_SECOND;
		Omnet.DIMMED_PROFIT_PER_SECOND = DIMMED_PROFIT_PER_SECOND;
		
		if (fourservEnabled) {
			omnet.getDatacenters().add((omnet.datacenterFactory.getD()));
		}
	}
	
	public int getSYSTEM_DEMAND() {
		return SYSTEM_DEMAND;
	}

	public double getNORMAL_PROFIT_PER_SECOND() {
		return NORMAL_PROFIT_PER_SECOND;
	}

	public double getDIMMED_PROFIT_PER_SECOND() {
		return DIMMED_PROFIT_PER_SECOND;
	}

	public int getMAX_SERVER_COUNT_PER_LOC() {
		return MAX_SERVER_COUNT_PER_LOC;
	}

	public boolean isFourservEnabled() {
		return fourservEnabled;
	}

	public double[] getCost() {
		return cost;
	}

	public double[] getPower() {
		return power;
	}

	public double[] getPowerPerNormal() {
		return powerPerNormal;
	}

	public long[] getLatency() {
		return latency;
	}

	public double[] getFailChance() {
		return failChance;
	}
	
	public void setFourservEnabled(boolean b) {
		fourservEnabled = b;
	}
//
//	public long[] getExecutionTime() {
//		return executionTime;
//	}

	// allow for arbitrary modifications to tactics
	public void acceptTactic(Tactic tactic) {
		for (int count = 0; count < tacticHandlers.size(); count++) {
			TacticHandler f = tacticHandlers.get(count);
			f.acceptTactic(tactic);
		}
	}
	
	// add a handler to set the tactic failrate
	private static TacticHandler getFailchanceHandler(Class c, double failchance) {
		return (tactic)->{
			if (tactic instanceof FailableTactic) {
				FailableTactic ft = (FailableTactic) tactic;
				if (c.isInstance(tactic)){
					ft.setFailChance(failchance);
				}
			}
		};
	}
	
	public String toString() {
		String ans = SYSTEM_DEMAND + "," + fourservEnabled + ",";
		ans += arrayToString(toObjectArray(cost)) + ",";
		ans += arrayToString(toObjectArray(power)) + ",";
		ans += arrayToString(toObjectArray(powerPerNormal)) + ",";
		ans += arrayToString(toObjectArray(failChance));
		return ans;
	}
	
	private static Double[] toObjectArray(double[] array) {
		Double[] ans = new Double[array.length];
		for (int i = 0; i < array.length; i++) {
			ans[i] = (Double) array[i];
		}
		return ans;
	}
	
	private <T> String arrayToString(T[] array) {
		String ans = "";
		for (int i = 0; i < array.length; i++) {
			if (i > 0) {
				ans += ",";
			}
			ans += array[i].toString();
		}
		return ans;
	}

}
