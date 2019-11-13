package ecj.operators;

import java.util.HashMap;

import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;
import ecj.JavaGenerator;
import ecj.JavaRep;
import ecj.StateData;

public class SequenceOperator extends GPNode implements JavaGenerator {

	public String toString() { return ";"; }

	public int expectedChildren() { return 2;}

	public void eval(final EvolutionState state,
			final int thread,
			final GPData input,
			final ADFStack stack,
			final GPIndividual individual,
			final Problem problem)
	{
		//double result;

		//now handling with a runtime exception
		StateData o = (StateData)input;
		
		children[0].eval(state,thread,input,stack,individual,problem);
		
		children[1].eval(state,thread,input,stack,individual,problem);
		
//		o.getAllFinalStates(this);

//		children[0].eval(state,thread,input,stack,individual,problem);
//
//		//System.out.println("middle: "+sd.toString()+"\n");
//		children[1].eval(state,thread,input,stack,individual,problem);
		//System.out.println("after: "+sd.toString()+"\n");
		//the result information should now be held in input
		


	}

	@Override
	public JavaRep generateJava(JavaRep java) {
		((JavaGenerator) children[0]).generateJava(java);
		java.appendVector(((JavaGenerator) children[0]).generateVector());
		java.newLine();
		((JavaGenerator) children[1]).generateJava(java);
		java.appendVector(((JavaGenerator) children[1]).generateVector());
		java.newLine();
		return java;
	}

	@Override
	public HashMap<String, Integer> generateVector() {
		HashMap<String, Integer> ans = JavaRep.vectorAdd(((JavaGenerator) children[0]).generateVector(), ((JavaGenerator) children[1]).generateVector());
		ans.put("SequenceOperator", ans.getOrDefault("SequenceOperator", 0)+1);
		return ans;
	}


}







