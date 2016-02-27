package actions;

import main.StateData;

public abstract class DeleteServer extends ActionTemplate{

	@Override
	boolean areAddingItem() {
		return false;
	}

    public void uniqueSuccessChanges(StateData sd){

        if(invalidChangeAtLocation(sd)){
        	sd.setReachedInvalidState(true);
        }
    }
    
    abstract protected boolean invalidChangeAtLocation(StateData sd);
    
    public String toString(){
    	return "AddServer";
    }
}
