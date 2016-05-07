package omnet.components;

import main.OmnetStateData;

public class ServerA extends OmnetComponent{
	
	public ServerA(){
		super();
		costPerSecond = 0.5;
		normalRequestsHandledPerSecond = 50;
		dimmedRequestsHandledPerSecond = 150;
		
	}

}
