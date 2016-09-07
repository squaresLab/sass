package main.java.znn.tactics;

import main.java.actions.AddServer;
import main.java.main.StateData;
import main.java.znn.components.L2Server;

public class AddServerL2 extends AddServer {
	
	public AddServerL2() {
    	component=new L2Server();
	}
    
    @Override
    public String toString(){
    	return "AddServerL2";
    }

	@Override
	protected boolean isInvalidChange(StateData sd) {
		sd.setL2ServerCount(sd.getL2ServerCount()+1);
		return sd.getL2ServerCount()>sd.getMaxL2ServerCount();
	}
}
