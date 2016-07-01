package main.java.omnet.components;


public class ServerA extends OmnetComponent{
	
	public ServerA(){
		super();
		costPerSecond = 0.5;
		normalRequestsHandledPerSecond = 50;
		dimmedRequestsHandledPerSecond = 150;
		
	}
	
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("DimmerLevel " + this.getDimmerLevel());
		result.append("TrafficLevel " + this.getTrafficLevel());
		return result.toString();
	}

}
