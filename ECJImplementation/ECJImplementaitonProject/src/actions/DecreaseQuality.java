package actions;

import components.QualityChange;
import main.StateData;

public class DecreaseQuality extends ActionTemplate {

	
	
    public DecreaseQuality(){
    	component=new QualityChange();
    }
	
	@Override
	public String toString() {
		return "DecreaseQuality";
	}

	@Override
	public void uniqueSuccessChanges(StateData sd) {
	  //only have positive effect if the quality is currently worse 
	  if(sd.isHighQuality()){
        sd.setHighQuality(false);
	  }
	}

	//this action can't cause an error.  nothing happens if the system tries 
	//to decrease quality when it is already at the lowest setting except 
	//for adding the time
	@Override
	protected boolean invalidChangeAtLocation(StateData sd) {
		return false;
	}

	@Override
	boolean areAddingItem() {
		return false;
	}
	

}
