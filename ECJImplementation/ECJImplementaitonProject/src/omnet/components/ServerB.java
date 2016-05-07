package omnet.components;

import main.OmnetStateData;

public class ServerB extends OmnetComponent{
	
	public ServerB(){
		super();
		costPerSecond = 0.7;
		normalRequestsHandledPerSecond = 130;
		dimmedRequestsHandledPerSecond = 200;
	}


}
