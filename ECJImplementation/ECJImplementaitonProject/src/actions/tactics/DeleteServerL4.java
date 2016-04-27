package actions.tactics;

import components.L4Server;
import main.StateData;

public class DeleteServerL4 extends DeleteServer {
	

	
    public DeleteServerL4(){
    	component= new L4Server();
    }
    
    @Override
    public String toString(){
    	return "DeleteServerL4";
    }
    
	@Override
	protected boolean isInvalidChange(StateData sd) {
		sd.setL4ServerCount(sd.getL4ServerCount()-1);
		return sd.getL4ServerCount() < 0 || sd.getServerCount() < 1;
	}
}
