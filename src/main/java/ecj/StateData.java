package ecj;

import ec.gp.GPData;
import omnet.tactics.OmnetPlan;

public class StateData extends GPData {

	public OmnetPlan plan;
	
	public void copyTo(final GPData gpd){
		((StateData) gpd).plan = (OmnetPlan) plan.clone();
	}

	public void initializeState() {
		plan = new OmnetPlan();
	}

}
