package actions.tactics;

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
	protected boolean isInvalidChange(StateData sd) {
		//currently making change quality fail if already in a high quality
				//state because loops of only changing quality were being generated
				//and causing the code to slow down considerably.
		if(!sd.isHighQuality()){
			return true;
		} else { 
			sd.setHighQuality(false);
			return false;
		}
	}

	@Override
	boolean areAddingItem() {
		return false;
	}
	

}
