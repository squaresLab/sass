package omnet;

import java.util.ArrayList;
import java.util.List;

import omnet.components.Datacenter;
import omnet.components.DatacenterFactory;
import tactics.Tactic;

public class Scenario {

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
	
	// constants for each tactic, note that these aren't applied yet
	// DD, DT, ID, IT, ShS, StS
	//double[] failChance = {0.05,0.01,0.05,0.05,0.1,0.1};
	//long[] executionTime = {1,5,1,5,30};
	
	interface TacticHandler {
		void acceptTactic(Tactic tactic);
	}
	
	List<TacticHandler> tacticHandlers;
	
	public Scenario() {
		tacticHandlers = new ArrayList<TacticHandler>();
	}
	
	public void applyTo(Omnet omnet) {
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

//	public double[] getFailChance() {
//		return failChance;
//	}
//
//	public long[] getExecutionTime() {
//		return executionTime;
//	}

	// allow for arbitrary modifications to tactics
	public void acceptTactic(Tactic tactic) {
		for (TacticHandler f : tacticHandlers) {
			f.acceptTactic(tactic);
		}
	}

}
