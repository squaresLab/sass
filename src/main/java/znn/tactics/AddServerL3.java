package znn.tactics;

import actions.AddServer;
import main.StateData;
import znn.components.L3Server;

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