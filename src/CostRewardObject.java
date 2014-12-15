public class CostRewardObject {

	int systemResponseTime;
	int serverCount;
	int cost;
	boolean highTextResolution;

	public CostRewardObject(int systemResponseTime, int serverCount, int cost,
			boolean highTextResolution) {
		this.systemResponseTime = systemResponseTime;
		this.serverCount = serverCount;
		this.cost = cost;
		this.highTextResolution = highTextResolution;

	}

	public int getSystemResponseTime() {
		return systemResponseTime;
	}

	public void setSystemResponseTime(int systemResponseTime) {
		this.systemResponseTime = systemResponseTime;
	}

	public int getServerCount() {
		return serverCount;
	}

	public void setServerCount(int serverCount) {
		this.serverCount = serverCount;
	}

	public int getCost() {
		return cost;
	}

	public void setCost(int cost) {
		this.cost = cost;
	}

	public boolean getUsingHighTextResolution() {
		return highTextResolution;
	}

	public void setUsingHighTextResolution(boolean newTextResolution) {
		highTextResolution = newTextResolution;
	}

}
