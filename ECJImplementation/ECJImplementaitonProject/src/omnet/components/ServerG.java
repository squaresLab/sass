package omnet.components;

import main.OmnetStateData;

public class ServerG extends OmnetComponent{
	
	public ServerG(){
		super();
		costPerSecond = 0.5;
		normalRequestsHandledPerSecond = 60;
		dimmedRequestsHandledPerSecond = 180;
	}
	

}
