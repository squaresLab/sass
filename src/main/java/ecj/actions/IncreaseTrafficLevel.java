package ecj.actions;

import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;
import ecj.StateData;
import generalomnet.tactics.IncreaseTraffic;

public abstract class IncreaseTrafficLevel extends GPNode {
	
String target;
	
	public IncreaseTrafficLevel(String target){
		this.target = target;
	}
	
	public void eval(final EvolutionState state,
			final int thread,
			final GPData input,
			final ADFStack stack,
			final GPIndividual individual,
			final Problem problem)
	{
		
		StateData o = (StateData)input;
		
		o.plan.getTactics().add(new IncreaseTraffic(target));
		
	}
	
	@Override
	public String toString(){
		return "IncreaseTrafficLevel"+target;
	}

}
