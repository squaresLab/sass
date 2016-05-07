package omnet.components;

import main.OmnetStateData;

public class ServerD extends OmnetComponent{
	
	public ServerD(){
		super();
		costPerSecond = 0.5;
		normalRequestsHandledPerSecond = 25;
		dimmedRequestsHandledPerSecond = 80;
	}
	

}
