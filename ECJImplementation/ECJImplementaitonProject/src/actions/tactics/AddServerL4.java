
package actions.tactics;

import components.L4Server;
import main.StateData;

public class AddServerL4 extends AddServer {

	public AddServerL4() {
		component=new L4Server();
	}

	@Override
	public String toString(){
		return "AddServerL4";
	}

	@Override
	protected boolean isInvalidChange(StateData sd) {
		sd.setL4ServerCount(sd.getL4ServerCount()+1);
		return sd.getL4ServerCount()>sd.getMaxL4ServerCount();
	}
}

