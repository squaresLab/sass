package znn.tactics;

import actions.DeleteServer;
import main.StateData;
import znn.components.L2Server;

public class DeleteServerL2 extends DeleteServer {
	

	
    public DeleteServerL2(){
    	component= new L2Server();
    }
    
    @Override
    public String toString(){
    	return "DeleteServerL2";
    }
    
	@Override
	protected boolean isInvalidChange(StateData sd) {
		sd.setL2ServerCount(sd.getL2ServerCount()-1);
		return sd.getL2ServerCount() < 0 || sd.getServerCount() < 1;
	}
}
