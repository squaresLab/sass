package omnet.components;


public class ServerG extends OmnetComponent{
	
	public ServerG(){
		super();
		costPerSecond = 0.5;
		normalRequestsHandledPerSecond = 60;
		dimmedRequestsHandledPerSecond = 180;
	}
	
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("DimmerLevel " + this.getDimmerLevel());
		result.append("TrafficLevel " + this.getTrafficLevel());
		return result.toString();
	}
}
