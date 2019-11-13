package ecj.operators;

import java.util.HashMap;

import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.ERC;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;
import ec.gp.GPNodeConstraints;
import ec.multiobjective.MultiObjectiveFitness;
import ec.util.Code;
import ec.util.DecodeReturn;
import ecj.JavaGenerator;
import ecj.JavaRep;

public class ForIndex extends ERC implements JavaGenerator {

	int value;

	public static void main(String[] args){
		ForIndex test = new ForIndex();
		
		GPNodeConstraints nc = new GPNodeConstraints();
		
		test.children = nc.zeroChildren;
		
		test = (ForIndex) test.readNode(new DecodeReturn("ERC[i3|]"));
		
		System.out.println(test);
		
		System.out.println(Code.encode(0));
	}
	
	@Override
	public void resetNode(EvolutionState state, int thread) {
		//value = state.random[thread].nextInt(9) + 2;
		value = 2;
	}
	
	@Override
	public void mutateERC(EvolutionState state, int thread)  {
		if (value == 2){
			value++;
		}else if (value == 10){
			value--;
		}else{
			if (state.random[thread].nextBoolean()){
				value++;
			}else{
				value--;
			}
		}
	}

	@Override
	public boolean nodeEquals(GPNode node) {
		return (node.getClass() == this.getClass() && ((ForIndex)node).value == value); 
	}

	@Override
	public String encode() {
		return Code.encode(value);
	}
	
	@Override
	public boolean decode(final DecodeReturn dret)
    {
	
		Code.decode(dret);
		
		if(dret.type == dret.T_INT){
			
			this.value = (int) dret.l;
			
			return true;
		
		}else{
			return false;
		}
    }

	@Override
	public void eval(EvolutionState state, int thread, GPData input, ADFStack stack, GPIndividual individual,
			Problem problem) {
		
		Int intData = (Int) input;
		
		intData.value = this.value;
		
	}
	
	public String toStringForHumans(){
		return "" + value;
	}

	@Override
	public JavaRep generateJava(JavaRep java) {
		java.appendLine(""+value, this);
		return java;
	}

	@Override
	public HashMap<String, Integer> generateVector() {
		HashMap<String, Integer> vector = new HashMap<String, Integer>();
		vector.put(value+"", 1);
		return vector;
	}
	
	

}
