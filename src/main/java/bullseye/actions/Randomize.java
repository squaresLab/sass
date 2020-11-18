package bullseye.actions;

import java.util.Random;

import bullseye.System;
import bullseye.tactics.Tactic;
import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;

public class Randomize extends GPNode implements Tactic{

	@Override
	public void eval(EvolutionState arg0, int arg1, GPData arg2, ADFStack arg3, GPIndividual arg4, Problem arg5) {
		
		children[1].eval(arg0, arg1, arg2, arg3, arg4, arg5);
		children[2].eval(arg0, arg1, arg2, arg3, arg4, arg5);
		
	}

	@Override
	public String toString() {
		return "(R "+children[0] + " "+children[1]+" "+children[2]+")";
	}

	@Override
	public Tactic visit(System system) {
		
		Random rand = new Random();
		
		double roll = rand.nextDouble();
		
		Probability p = (Probability) children[0];
		
		Tactic t = null;
		
		if (roll < p.value) {
			t = ((Tactic) children[1]);
		}else {
			t = ((Tactic) children[2]);
		}
		
		return t.visit(system);
		
	}

	@Override
	public boolean isApplicable(System system) {
		return true;
	}

}
