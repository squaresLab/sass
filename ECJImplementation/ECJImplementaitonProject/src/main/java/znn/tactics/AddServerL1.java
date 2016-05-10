package main.java.znn.tactics;

import main.java.actions.AddServer;
import main.java.main.StateData;
import main.java.znn.components.L1Server;

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
