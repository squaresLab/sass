package omnet;

import java.util.ArrayList;

import omnet.components.Datacenter;
import omnet.components.DatacenterFactory;

public class Scenario {
	
	// omnet constant parameters
	int SYSTEM_DEMAND = 1000;
	
	double NORMAL_PROFIT_PER_SECOND = 3;

	double DIMMED_PROFIT_PER_SECOND = 1;

	double MAX_LATENCY_PER_SERVER = 1000;

	int MAX_SERVER_COUNT_PER_LOC = 5;
	
	// new data center
	boolean fourservEnabled = false;
	
	// constants for each datacenter / server type
	// A B C D
	double[] cost = {.5,.7,1,.5};
	double[] power = {150,200,300,80};
	double[] powerPerNormal = {150.0/50.0, 200.0/130.0, 300.0/150.0};
	long[] latency = {120,120,120,60};
	
	// constants for each tactic
	// DD, DT, ID, IT, ShS, StS
	double[] failChance = {0.05,0.01,0.05,0.05,0.1,0.1};
	long[] executionTime = {1,5,1,5,30};

}
