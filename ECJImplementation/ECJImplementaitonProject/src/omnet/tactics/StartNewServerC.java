package omnet.tactics;

import actions.AddServer;
import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;
import ec.util.Parameter;
import main.OmnetStateData;
import main.StateData;
import omnet.components.OmnetComponent;
import omnet.components.ServerC;

public class StartNewServerC extends StartNewServer {

	public StartNewServerC(){
		latency=120;
		failureWeight=0.1;
	}
	
	@Override
	public String toString(){
	 	return "AddServerC";
	}

	@Override
	protected void callPerformTactic(OmnetStateData sd) {
		sd.performTactic(this, ServerC.class);
		
	}


	

}

