package ecj;

import dart.Dart;
import dart.tactics.DartPlan;
import ec.gp.GPData;
import omnet.components.Server;
import omnet.tactics.OmnetPlan;
import system.Fitness;
import system.SystemState;

public class DartStateData extends GPData {

	public DartPlan plan;
	
	public void copyTo(final GPData gpd){
		((DartStateData) gpd).plan = (DartPlan) plan.clone();
	}

	public void initializeState() {
		plan = new DartPlan();
	}

}
