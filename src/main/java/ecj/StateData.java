package ecj;

import codysomnet.Omnet;
import codysomnet.tactics.OmnetPlan;
import ec.gp.GPData;
import tactics.Plan;

public class StateData extends GPData {

	public OmnetPlan plan;
	
	public void copyTo(final GPData gpd){
		((StateData) gpd).plan = (OmnetPlan) plan.clone();
	}

	public void initializeState() {
		plan = new OmnetPlan();
	}

}
