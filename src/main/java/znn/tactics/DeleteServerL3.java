package znn.tactics;

import actions.DeleteServer;
import main.StateData;
import znn.components.L3Server;

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
