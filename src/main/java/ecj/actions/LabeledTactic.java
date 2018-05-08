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

public abstract class LabeledTactic<T extends FailableTactic> extends GPNode implements JavaGenerator {
	
	Class<T> tactic;
	
	FailableTactic tacticInstance;
	
	public LabeledTactic(Class<T> tactic){
		this.tactic = tactic;
	}
	
	public void eval(final EvolutionState state,
			final int thread,
			final GPData input,
			final ADFStack stack,
			final GPIndividual individual,
			final Problem problem)
	{
		
		StateData o = (StateData)input;
		
		TacticFactory<T> factory = new TacticFactory<T>(tactic);
		
		children[0].eval(state, thread, factory, stack, individual, problem);
		
		tacticInstance = factory.getLabledTacticInstance();
		
		o.plan.getTactics().add(tacticInstance);
		
	}
	
	public JavaRep generateJava(JavaRep java) {
		java.appendLine(tactic.getSimpleName(), this);
		java.appendLine("(\"");
		((JavaGenerator) children[0]).generateJava(java);
		java.appendLine("\");");
		return java;
	}
	
	public int expectedChildren(){return 1;};
	
	public void checkConstraints(final EvolutionState state,
            final int tree,
            final GPIndividual typicalIndividual,
            final Parameter individualBase){
		
			super.checkConstraints(state,tree,typicalIndividual,individualBase);
			
			if (children.length != 1){
				state.output.error("Incorrect number of children for node " +
                        toStringForError() + " at " +
                        individualBase);
			}
				

	}
	
	@Override
	public String toString(){
		return tactic.getSimpleName();
	}

}
