package actions;

import main.StateData;

public abstract class DeleteServer extends ActionTemplate{

	@Override
	protected boolean areAddingItem() {
		return false;
	}

    public void uniqueSuccessChanges(StateData sd){

        if(isInvalidChange(sd)){
        	sd.setReachedInvalidState(true);
        }
    }
    
    abstract protected boolean isInvalidChange(StateData sd);
    
    public String toString(){
    	return "AddServer";
    }
}
