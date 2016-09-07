package main.java.omnet.components;


public class ServerE extends OmnetComponent{
	
	public ServerE(){
		super();
		costPerSecond = 0.5;
		normalRequestsHandledPerSecond = 100;
		dimmedRequestsHandledPerSecond = 200;
	}
	
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("DimmerLevel " + this.getDimmerLevel());
		result.append("TrafficLevel " + this.getTrafficLevel());
		return result.toString();
	}
}
