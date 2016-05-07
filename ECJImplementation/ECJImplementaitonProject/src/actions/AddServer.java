package actions;

import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;
import ec.util.Parameter;
import main.StateData;
import znn.components.Server;

public abstract class AddServer extends ActionTemplate{


    
	@Override
	protected boolean areAddingItem() {
		return true;
	}
    
    abstract protected boolean isInvalidChange(StateData sd);
    
    public String toString(){
    	return "AddServer";
    }
}

