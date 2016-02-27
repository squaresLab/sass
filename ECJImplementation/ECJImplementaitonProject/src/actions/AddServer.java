package actions;

import components.Server;
import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;
import ec.util.Parameter;
import main.StateData;

public abstract class AddServer extends ActionTemplate{


    
	@Override
	boolean areAddingItem() {
		return true;
	}
    
    abstract protected boolean invalidChangeAtLocation(StateData sd);
    
    public String toString(){
    	return "AddServer";
    }
}

