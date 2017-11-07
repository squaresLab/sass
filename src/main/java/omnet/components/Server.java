package omnet.components;

import java.util.Hashtable;

public class Server {
	
	// server name to keep track of multiple servers
	private String name;
	
	private boolean validState;
	
	// the cost per second
	protected double cost;
	// the computational power
	protected double power;
	
	protected double powerPerNormal;
	
	protected double powerPerDimmed;
	
	protected long latency;
	
	public Server(String name){
		this.name = name;
		
		powerPerNormal = 2;
		powerPerDimmed = 1;
		
		power = 200;
		
		cost = 1;
		
		latency = 120;
		
		validState = true;
	
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public boolean isValid(){
		return validState;
	}
	
	public Object clone(){
		try {
			return super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

	public double getPower() {
		return power;
	}

	public double getPowerPerNormal() {
		return powerPerNormal;
	}

	public double getPowerPerDimmed() {
		return powerPerDimmed;
	}

	public long getLatency() {
		return latency;
	}
	

}
