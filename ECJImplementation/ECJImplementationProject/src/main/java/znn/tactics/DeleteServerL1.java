package main.java.znn.tactics;

import main.java.actions.DeleteServer;
import main.java.main.StateData;
import main.java.znn.components.L1Server;

public class DeleteServerL1 extends DeleteServer {
	

	
    public DeleteServerL1(){
    	component=new L1Server();
    }
    
    @Override
    public String toString(){
    	return "DeleteServerL1";
    }

	@Override
	protected boolean isInvalidChange(StateData sd) {
		sd.setL1ServerCount(sd.getL1ServerCount()-1);
		return sd.getL1ServerCount() < 0 || sd.getServerCount() < 1;
	}
}
