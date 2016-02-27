package actions;

import components.L2Server;
import main.StateData;

public class DeleteServerL2 extends DeleteServer {
	

	
    public DeleteServerL2(){
    	component= new L2Server();
    }
    
    @Override
    public String toString(){
    	return "DeleteServerL2";
    }
    
	@Override
	protected boolean invalidChangeAtLocation(StateData sd) {
		sd.setL2ServerCount(sd.getL2ServerCount()-1);
		return sd.getL2ServerCount() < 0 || sd.getServerCount() < 1;
	}
}
