package omnet.components;


public class ServerD extends OmnetComponent{
	
	public ServerD(){
		super();
		costPerSecond = 0.8;
		normalRequestsHandledPerSecond = 140;
		dimmedRequestsHandledPerSecond = 260;
	}
	
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("DimmerLevel " + this.getDimmerLevel());
		result.append("TrafficLevel " + this.getTrafficLevel());
		return result.toString();
	}
}
