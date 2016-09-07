package omnet.components;


public class ServerB extends OmnetComponent{
	
	public ServerB(){
		super();
		costPerSecond = 0.7;
		normalRequestsHandledPerSecond = 130;
		dimmedRequestsHandledPerSecond = 200;
	}

	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("DimmerLevel " + this.getDimmerLevel());
		result.append("TrafficLevel " + this.getTrafficLevel());
		return result.toString();
	}
}
