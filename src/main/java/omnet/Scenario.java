package omnet;

import java.util.ArrayList;
import java.util.Arrays;
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
	double[] cost = {.5,.7,1,.5,.5,.7,1,.5,.5,.7,1,.5,.5,.7,1,.5};
	double[] power = {150,200,300,80,150,200,300,80,150,200,300,80,150,200,300,80};
	double[] powerPerNormal = {150.0/50.0, 200.0/130.0, 300.0/150.0, 80.0 / 25.0,150.0/50.0, 200.0/130.0, 300.0/150.0, 80.0 / 25.0,150.0/50.0, 200.0/130.0, 300.0/150.0, 80.0 / 25.0,150.0/50.0, 200.0/130.0, 300.0/150.0, 80.0 / 25.0};
	long[] latency = {120,120,120,60,120,120,120,60,120,120,120,60,120,120,120,60};
	
	double[] regionCost = {0.0001502580449,0.0001431959624,0.0001666704614,0.0001666704614,0.0001584547662,0.0001577868852,0.0001735883424,0.0002167501518,0.0001557680631,
			0.0001615664845,0.0001793260474,0.0001607088646,0.0001422282939,0.0001422282939,0.0001724954463,0.0001422282939};
	double[] regionPower = {1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000,1000};
	double[] regionPowerPerNormal = {1000/500,1000/500,1000/500,1000/500,1000/500,1000/500,1000/500,1000/500,1000/500,1000/500,1000/500,1000/500,1000/500,1000/500,1000/500,1000/500};
	double[] regionLatency = {120,120,120,120,120,120,120,120,120,120,120,120,120,120,120,120};
	
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

	public double profitCoef = 1.0;
	public double latencyCoef = 0.0;
	
	public Scenario() {
		tacticHandlers = new ArrayList<TacticHandler>();
		tacticFailrateHandlers = new HashMap<Class,TacticHandler>();
		// initialize handlers w/ defaults
		updateHandlers();
		
		cost = getDefaultByRegion(regionCost);
		power = getDefaultByRegion(regionPower);
		powerPerNormal = getDefaultByRegion(regionPowerPerNormal);
		double[] latencyDub = getDefaultByRegion(regionLatency);
		
		latency = new long[latencyDub.length];
		for (int i = 0; i < latencyDub.length; i++) {
			latency[i] = (long) latencyDub[i];
		}
	}
	
	public double[] getDefaultByRegion(double[] regionArr) {
		int size = DatacenterFactory.serverLabels.length;
		double[] ans = new double[size];
		
		for (int i = 0; i < size; i++) {
			String label = DatacenterFactory.serverLabels[i];
			String region = getRegionFromLabel(label);
			int index = Arrays.asList(DatacenterFactory.regionLabels).indexOf(region);
			ans[i] = regionArr[index];
		}
		
		return ans;
	}
	
	private String getRegionFromLabel(String label){
		return label.substring(0,label.length()-1);
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
		ans += arrayToString(toObjectArray(failChance)) + ",";
		ans += profitCoef + ",";
		ans += latencyCoef;
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
	
	public Object clone() {
		Scenario ans = new Scenario();
		ans.SYSTEM_DEMAND = this.SYSTEM_DEMAND;
		ans.NORMAL_PROFIT_PER_SECOND = this.NORMAL_PROFIT_PER_SECOND;
		ans.DIMMED_PROFIT_PER_SECOND = this.DIMMED_PROFIT_PER_SECOND;
		ans.MAX_SERVER_COUNT_PER_LOC = this.MAX_SERVER_COUNT_PER_LOC;
		ans.fourservEnabled = this.fourservEnabled;
		ans.cost = Arrays.copyOf(this.cost, cost.length);
		ans.power = Arrays.copyOf(this.power, power.length);
		ans.powerPerNormal = Arrays.copyOf(this.powerPerNormal, powerPerNormal.length);
		ans.latency = Arrays.copyOf(this.latency, latency.length);
		ans.failChance = Arrays.copyOf(this.failChance, failChance.length);
		
		ans.tacticHandlers = new ArrayList<TacticHandler>(this.tacticHandlers);
		ans.tacticFailrateHandlers = (HashMap<Class, TacticHandler>) this.tacticFailrateHandlers.clone();
		
		ans.profitCoef = this.profitCoef;
		ans.latencyCoef = this.latencyCoef;
		
		return ans;
	}

}
