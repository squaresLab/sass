package actions.tactics;

import components.L1Server;
import main.StateData;

public class AddServerL1 extends AddServer {

    public AddServerL1() {
    	component=new L1Server();
	}
	
	
    @Override
    public String toString(){
    	return "AddServerL1";
    }

	@Override
	protected boolean isInvalidChange(StateData sd) {
		sd.setL1ServerCount(sd.getL1ServerCount()+1);
		return sd.getL1ServerCount()>sd.getMaxL1ServerCount();
	}


}
