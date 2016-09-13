package ecj.actions;

import codysomnet.tactics.DecreaseDimmer;
import codysomnet.tactics.IncreaseDimmer;
import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;
import ecj.StateData;

public abstract class IncreaseDimmerLevel extends GPNode {
	
	String target;
	
	public IncreaseDimmerLevel(String target){
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
		
		o.plan.getTactics().add(new IncreaseDimmer(target));
		
	}
	
	@Override
	public String toString(){
		return "IncreaseDimmerLevel"+target;
	}

}
