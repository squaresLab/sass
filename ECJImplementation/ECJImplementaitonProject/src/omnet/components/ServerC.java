package omnet.components;

import main.OmnetStateData;

public class ServerC extends OmnetComponent{
	
	public ServerC(){
		super();
		costPerSecond = 1;
		normalRequestsHandledPerSecond = 150;
		dimmedRequestsHandledPerSecond = 300;
	}

}
