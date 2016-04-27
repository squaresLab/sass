package actions.tactics;

import components.L3Server;
import main.StateData;

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
