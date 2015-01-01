public class CostRewardObject {

	int systemResponseTime;
	// int serverCount;
	int cost;
	// boolean highTextResolution;
	SystemState ss = new SystemState();

	public CostRewardObject(int systemResponseTime, int cost) {
		this.systemResponseTime = systemResponseTime;
		this.cost = cost;
	}

	public int getSystemResponseTime() {
		return systemResponseTime;
	}

	public SystemState getSystemState() {
		return ss;
	}

	public void setSystemResponseTime(int systemResponseTime) {
		this.systemResponseTime = systemResponseTime;
	}

	public int getServerCount() {
		return ss.currentServerCount();
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public boolean getUsingHighTextResolution() {
		return ss.getUsingHighTextResolution();
	}

	public void toggleHighTextResolution() {
		ss.toogleUsingHighTextResolution();
	}

}
