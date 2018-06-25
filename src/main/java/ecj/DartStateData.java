package ecj;

import dart.tactics.DartPlan;
import ec.gp.GPData;
import omnet.components.Server;
import omnet.tactics.OmnetPlan;

public class DartStateData extends GPData {

	public DartPlan plan;
	
	public void copyTo(final GPData gpd){
		((DartStateData) gpd).plan = (DartPlan) plan.clone();
	}

	public void initializeState() {
		plan = new DartPlan();
	}

}
