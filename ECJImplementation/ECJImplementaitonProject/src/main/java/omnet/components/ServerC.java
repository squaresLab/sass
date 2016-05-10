package main.java.omnet.components;


public class ServerC extends OmnetComponent{
	
	public ServerC(){
		super();
		costPerSecond = 1;
		normalRequestsHandledPerSecond = 150;
		dimmedRequestsHandledPerSecond = 300;
	}

}
