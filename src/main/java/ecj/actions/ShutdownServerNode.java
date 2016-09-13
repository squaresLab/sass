package ecj.actions;

import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;
import ecj.StateData;
import generalomnet.tactics.DecreaseDimmer;
import generalomnet.tactics.ShutdownServer;
import main.OmnetStatePath;

public abstract class ShutdownServerNode extends GPNode {

	String target;
	
	public ShutdownServerNode(String target){
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
		
		o.plan.getTactics().add(new ShutdownServer(target));
		
	}
	
	@Override
	public String toString(){
		return "ShutdownServer"+target;
	}

}
