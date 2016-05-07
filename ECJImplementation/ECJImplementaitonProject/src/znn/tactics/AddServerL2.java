package znn.tactics;

import actions.AddServer;
import main.StateData;
import znn.components.L2Server;

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
