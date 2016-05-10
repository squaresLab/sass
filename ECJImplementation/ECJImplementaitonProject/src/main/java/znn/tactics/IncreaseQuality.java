package main.java.znn.tactics;

import main.java.actions.ActionTemplate;
import main.java.main.StateData;
import main.java.znn.components.QualityChange;

public class IncreaseQuality extends ActionTemplate {
	
	
    public IncreaseQuality(){
    	component=new QualityChange();
    }
	
	@Override
	public String toString() {
		return "IncreaseQuality";
	}



	@Override
	protected boolean isInvalidChange(StateData sd) {
		//currently making change quality fail if already in a high quality
		//state because loops of only changing quality were being generated
		//and causing the code to slow down considerably.
		if(sd.isHighQuality()){
			return true;
		} else {
			sd.setHighQuality(true);
			return false;
		}
	}

	@Override
	protected boolean areAddingItem() {
		return true;
	}
	

}
