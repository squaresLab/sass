package codysomnet.components;

public class Server {
	
	// the cost per second
	private double cost;
	// the number of requests per seccond
	private double normalLoad;
	private double dimmedLoad;
	// the dimmer settings
	public static final int MAX_DIMMER_LVL = 10;
	public static final int MIN_DIMMER_LVL = 0;
	private int dimmer;
	// the requested traffic ratio
	public static final int MAX_TRAFFIC_LVL = 10;
	public static final int MIN_TRAFFIC_LVL = 0;
	private int traffic;
	
	// server name to keep track of multiple servers
	private String name;
	
	private boolean validState;
	
	public Server(String name){
		this.name = name;
		
		// provide default values
		dimmer = 3;
		traffic = 4;
		
		validState = true;
	
	}

	public double getCost() {
		return cost;
	}

	public void setCost(double cost) {
		this.cost = cost;
	}

	public double getNormalLoad() {
		return normalLoad;
	}

	public void setNormalLoad(double normalLoad) {
		this.normalLoad = normalLoad;
	}

	public double getDimmedLoad() {
		return dimmedLoad;
	}

	public void setDimmedLoad(double dimmedLoad) {
		this.dimmedLoad = dimmedLoad;
	}

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
		if (traffic > MAX_TRAFFIC_LVL || traffic < MIN_TRAFFIC_LVL)
		this.traffic = traffic;
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
	
	

}
