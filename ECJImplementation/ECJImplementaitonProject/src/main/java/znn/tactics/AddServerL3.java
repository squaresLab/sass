package main.java.znn.tactics;

import main.java.actions.AddServer;
import main.java.main.StateData;
import main.java.znn.components.L3Server;

public class AddServerL3 extends AddServer {
	
	public AddServerL3() {
    	component=new L3Server();
	}
    
    @Override
    public String toString(){
    	return "AddServerL3";
    }

	@Override
	protected boolean isInvalidChange(StateData sd) {
		sd.setL3ServerCount(sd.getL3ServerCount()+1);
		return sd.getL3ServerCount()>sd.getMaxL3ServerCount();
	}
}