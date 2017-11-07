package omnet.components;

import java.util.ArrayList;

public class Datacenter {
	
		// the name of this datacenter
		private String name;
		
		// the properties of the datacenter
		// the dimmer settings
		public static final int MAX_DIMMER_LVL = 4;
		public static final int MIN_DIMMER_LVL = 0;
		private int dimmer;
		// the requested traffic ratio
		public static final int MAX_TRAFFIC_LVL = 4;
		public static final int MIN_TRAFFIC_LVL = 0;
		private int traffic;
	
		// these variables define the properties of servers at this datacenter
		// the cost per second
		protected double cost;
		// the computational power
		protected double power;
		// the ratio of power for normal requests
		protected double powerPerNormal;
		// the ratio of power for dimmed requests
		protected double powerPerDimmed;
		// the start time for these servers
		protected long latency;
		
		boolean validState;
		
		// the running servers at this datacenter
		private int servers;
		
		public Datacenter(String name) {
			this.name = name;
			
			// default values
			servers = 1;
			
			dimmer = 0;
			traffic = 4;
			
			validState = true;
			
			// these values potentially changed by the datacenter factory
			powerPerNormal = 2;
			powerPerDimmed = 1;
			
			power = 200;
			
			cost = 1;
			
			latency = 120;
	
		}
		
		// various getters and setters
		
		public int getDimmer() {
			return dimmer;
		}

		public void setDimmer(int dimmer) {
			if (dimmer > MAX_DIMMER_LVL || dimmer < MIN_DIMMER_LVL){
				validState = false;
			}else{
				this.dimmer = dimmer;
			}
		}

		public int getTraffic() {
			return traffic;
		}

		public void setTraffic(int traffic) {
			if (traffic > MAX_TRAFFIC_LVL || traffic < MIN_TRAFFIC_LVL){
				validState = false;
			}else{
				this.traffic = traffic;
			}
		}
		
		public double getCost() {
			return cost * servers;
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
			return power * servers;
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

		public int getServers() {
			return servers;
		}

		public void setServers(int i) {
			
			this.servers = i;
			
		}
		

}
