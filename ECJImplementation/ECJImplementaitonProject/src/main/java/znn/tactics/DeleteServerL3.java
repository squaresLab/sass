package main.java.znn.tactics;

import main.java.actions.DeleteServer;
import main.java.main.StateData;
import main.java.znn.components.L3Server;

public class DeleteServerL3 extends DeleteServer {
	

	
    public DeleteServerL3(){
    	component= new L3Server();
    }
    
    @Override
    public String toString(){
    	return "DeleteServerL3";
    }
    
	@Override
	protected boolean isInvalidChange(StateData sd) {
		sd.setL3ServerCount(sd.getL3ServerCount()-1);
		return sd.getL3ServerCount() < 0 || sd.getServerCount() < 1;
	}
}
