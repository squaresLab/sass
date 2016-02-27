package actions;

import components.L1Server;
import main.StateData;

public class DeleteServerL1 extends DeleteServer {
	

	
    public DeleteServerL1(){
    	component=new L1Server();
    }
    
    @Override
    public String toString(){
    	return "DeleteServerL1";
    }

	@Override
	protected boolean invalidChangeAtLocation(StateData sd) {
		sd.setL1ServerCount(sd.getL1ServerCount()-1);
		return sd.getL1ServerCount() < 0 || sd.getServerCount() < 1;
	}
}
