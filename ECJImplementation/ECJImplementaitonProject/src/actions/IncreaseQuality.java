package actions;

import components.QualityChange;
import main.StateData;

public class IncreaseQuality extends ActionTemplate {
	
	
    public IncreaseQuality(){
    	component=new QualityChange();
    }
	
	@Override
	public String toString() {
		return "IncreaseQuality";
	}

	@Override
	public void uniqueSuccessChanges(StateData sd) {
	  //only have positive effect if the quality is currently worse 
	  if(!sd.isHighQuality()){
        sd.setHighQuality(true);
	  }
	}

	//This action cannot cause an invalid state. Nothing happens if the system is already in 
	//a high quality state and the system tries to increase the quality other than the time
	//required to attempt the action.
	@Override
	protected boolean invalidChangeAtLocation(StateData sd) {
		return false;
	}

	@Override
	boolean areAddingItem() {
		return true;
	}
	

}
