package ecj.actions;

import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;
import ec.util.Parameter;
import ecj.JavaGenerator;
import ecj.JavaRep;
import ecj.StateData;
import ecj.TacticFactory;
import tactics.FailableTactic;

public abstract class TacticLabel extends GPNode implements JavaGenerator {
	
	String label;
	
	public TacticLabel(String label){
		this.label = label;
	}
	
	public void eval(final EvolutionState state,
			final int thread,
			final GPData input,
			final ADFStack stack,
			final GPIndividual individual,
			final Problem problem)
	{
		
		TacticFactory o = (TacticFactory) input;
		
		o.setTarget(label);
		
	}
	
	public int expectedChildren(){return 0;};
				
	
	@Override
	public String toString(){
		return label;
	}
	
	public JavaRep generateJava(JavaRep java) {
		java.appendLine(label);
		return java;
	}

}
