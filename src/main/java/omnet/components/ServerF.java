package main.java.omnet.components;


public class ServerF extends OmnetComponent{
	
	public ServerF(){
		super();
		costPerSecond = 1.2;
		normalRequestsHandledPerSecond = 250;
		dimmedRequestsHandledPerSecond = 550;
	}
	
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("DimmerLevel " + this.getDimmerLevel());
		result.append("TrafficLevel " + this.getTrafficLevel());
		return result.toString();
	}
	
}
