package main.java.omnet.components;


public class ServerC extends OmnetComponent{
	
	public ServerC(){
		super();
		costPerSecond = 1;
		normalRequestsHandledPerSecond = 150;
		dimmedRequestsHandledPerSecond = 300;
	}
	
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("DimmerLevel " + this.getDimmerLevel());
		result.append("TrafficLevel " + this.getTrafficLevel());
		return result.toString();
	}
}
