package ecj.actions;

import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;
import ecj.DartStateData;

public class GoLoose extends GPNode {

	@Override
	public void eval(EvolutionState arg0, int arg1, GPData arg2, ADFStack arg3, GPIndividual arg4, Problem arg5) {
		DartStateData o = (DartStateData) arg2;
		
		o.plan.getTactics().add(new dart.tactics.GoLoose());
		
	}

	@Override
	public String toString() {
		return "GoLoose";
	}

}
