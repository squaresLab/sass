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
import tactics.FailableTactic;
import tactics.TryCatchFinallyTactic;

public class For extends GPNode implements JavaGenerator {
	
	public String toString() { return "F"; }

	public int expectedChildren() { return 2;}

	public void eval(final EvolutionState state,
			final int thread,
			final GPData input,
			final ADFStack stack,
			final GPIndividual individual,
			final Problem problem)
	{
		//get the constant value
		Int constant = new Int();
		
		children[0].eval(state, thread, constant, stack, individual, problem);

		//now handling with a runtime exception
		StateData o = (StateData)input;
		
		for (int count = 0; count < constant.value; count++){
			children[1].eval(state,thread,input,stack,individual,problem);
		}

	}

	@Override
	public JavaRep generateJava(JavaRep java) {
		java.appendLine("for (int i = 0; i < ");
		((JavaGenerator) children[0]).generateJava(java);
		java.appendLine(" ; i++) {",this);
		java.appendVector(generateVector());
		java.newLine();
		((JavaGenerator) children[1]).generateJava(java);
		java.appendVector(((JavaGenerator) children[1]).generateVector());
		java.newLine();
		java.addLine("}", null);
		return java;
	}

	@Override
	public HashMap<String, Integer> generateVector() {
		HashMap<String, Integer> ans = JavaRep.vectorAdd(((JavaGenerator) children[0]).generateVector(), ((JavaGenerator) children[1]).generateVector());
		ans.put("For", ans.getOrDefault("For", 0)+1);
		return ans;
	}

}
