package main;

import actions.AddServerL1;
import components.DatabaseAThread;
import components.DatabaseBThread;
import components.L1Server;
import components.L2Server;
import ec.gp.GPData;

public class StateData extends GPData {
	//TODO: go through the files and decide if you are going to use time or clockTime 
	//because you keep switching at the moment and you should be consistent
	double time;
	//double responseTime;
	double cost;
	boolean reachedInvalidState=false;
	int l1ServerCount;
	int maxL1ServerCount;
	int l2ServerCount;
	int maxL2ServerCount;
	int databaseAThreadsCount;
	int maxDatabaseAThreadsCount;
	int databaseBThreadsCount;
	int maxDatabaseBThreadsCount;
	boolean highQuality;
	double security;
	double computingPower;
	//remember to update the copyTo function when you add new variables
	
	public boolean getReachedInvalidState() {
		return reachedInvalidState;
	}
	
	public void setReachedInvalidState(boolean reachedInvalidState) {
		/*if(reachedInvalidState){
			System.out.println("Stopping here for debugging");
		}*/
		this.reachedInvalidState = reachedInvalidState;
	}
	
	public double getTime() {
		return time;
	}
	
	public void setTime(double time) {
		this.time = time;
	}
	
	public double getResponseTime() {
		//this assumes a constant user load at the moment
		double multiplier;
		if(isHighQuality()){
			multiplier=1;
		} else {
			//system is twice as fast in low quality state
			multiplier=2;
		}
		return 0.1+60/(computingPower*multiplier); 
	}
	/*public void setResponseTime(double responseTime) {
		this.responseTime = responseTime;
	}*/
	
	public double getCost() {
		return cost;
	}
	
	public double getComputingPower() {
		return computingPower;
	}
	
	public void setComputingPower(double computingPower) {
		this.computingPower = computingPower;
	}
	
	public void setCost(double cost) {
		this.cost = cost;
	}
	
	public int getServerCount() {
		return l1ServerCount + l2ServerCount;
	}

	public int getL1ServerCount() {
		return l1ServerCount;
	}
	
	public void setL1ServerCount(int l1ServerCount) {
		this.l1ServerCount = l1ServerCount;
	}
	
	public int getMaxL1ServerCount() {
		return maxL1ServerCount;
	}
	
	public void setMaxL1ServerCount(int maxL1ServerCount) {
		this.maxL1ServerCount = maxL1ServerCount;
	}
	
	public int getL2ServerCount() {
		return l2ServerCount;
	}
	
	public void setL2ServerCount(int l2ServerCount) {
		this.l2ServerCount = l2ServerCount;
	}
	
	public int getMaxL2ServerCount() {
		return maxL2ServerCount;
	}
	
	public void setMaxL2ServerCount(int maxL2ServerCount) {
		this.maxL2ServerCount = maxL2ServerCount;
	}
	
	public int getDatabaseAThreadsCount() {
		return databaseAThreadsCount;
	}
	
	public void setDatabaseAThreadsCount(int databaseAThreadsCount) {
		this.databaseAThreadsCount = databaseAThreadsCount;
	}
	
	public int getMaxDatabaseAThreadsCount() {
		return maxDatabaseAThreadsCount;
	}
	
	public void setMaxDatabaseAThreadsCount(int maxDatabaseAThreadsCount) {
		this.maxDatabaseAThreadsCount = maxDatabaseAThreadsCount;
	}
	
	public int getDatabaseBThreadsCount() {
		return databaseBThreadsCount;
	}
	
	public void setDatabaseBThreadsCount(int databaseBThreadsCount) {
		this.databaseBThreadsCount = databaseBThreadsCount;
	}
	
	public int getMaxDatabaseBThreadsCount() {
		return maxDatabaseBThreadsCount;
	}
	
	public void setMaxDatabaseBThreadsCount(int maxDatabaseBThreadsCount) {
		this.maxDatabaseBThreadsCount = maxDatabaseBThreadsCount;
	}
	
	public boolean isHighQuality() {
		return highQuality;
	}
	
	public void setHighQuality(boolean highQuality) {
		this.highQuality = highQuality;
	}
	
	public double getSecurity() {
		return security;
	}
	
	public void setSecurity(double security) {
		this.security = security;
	}
	
	public void copyTo(final GPData gpd){
		((StateData)gpd).setCost(cost);
		((StateData)gpd).setComputingPower(computingPower);
		((StateData)gpd).setHighQuality(highQuality);
		((StateData)gpd).setSecurity(security);
		((StateData)gpd).setDatabaseAThreadsCount(databaseAThreadsCount);
		((StateData)gpd).setDatabaseBThreadsCount(databaseBThreadsCount);
		((StateData)gpd).setL1ServerCount(l1ServerCount);
		((StateData)gpd).setL2ServerCount(l2ServerCount);
		((StateData)gpd).setMaxL1ServerCount(maxL1ServerCount);
		((StateData)gpd).setMaxL2ServerCount(maxL2ServerCount);
		((StateData)gpd).setMaxDatabaseAThreadsCount(maxDatabaseAThreadsCount);
		((StateData)gpd).setMaxDatabaseBThreadsCount(maxDatabaseBThreadsCount);
		((StateData)gpd).setReachedInvalidState(reachedInvalidState);
	}
	
	
	//TODO: figure out the return ranges later
	public double calculateStateScore(){
		if(reachedInvalidState){
			//illegal state
			//returns worse value - update when you determine the correct range
			return Long.MAX_VALUE;
		}else{
			int contentQualityInt = 0;
			if(highQuality){
				contentQualityInt = 1;
			}
			//System.out.println(toString());
			//remember closer values to zero are better; also remember that 
			//ECJ does not accept negative numbers; this means negative is
			//and positive is bad
			//TODO: adjust this equation - the parameters are very imbalanced - better but they still need work
		    //eventually make the exponential instead of linear - I think that makes more sense - small increase
			//in cost is not a big deal but a large increase is very bad
			return 17000 * getResponseTime() + 2 * cost + -1200 * contentQualityInt 
					+ 10 * time;
		}
		//-20 * responseTime + -20 * cost + 20 * contentQuality - 0.02 * clockTime
	}
	
	/*This is where the starting state is defined */
	public void initializeData(){
		
		security=5;
        time=0;
        highQuality=true;
        reachedInvalidState=false;
        l1ServerCount=2;
    	maxL1ServerCount=8; //TODO: check the max numbers later to check if they are correct
    	l2ServerCount=2;
    	maxL2ServerCount=8;
    	databaseAThreadsCount=2;
    	maxDatabaseAThreadsCount=5;
    	databaseBThreadsCount=2;
    	maxDatabaseBThreadsCount=5;
    	//calculate these from the starting server and thread counts
    	computingPower=2*(new L1Server()).getComputingPowerChange()+2*(new L2Server()).getComputingPowerChange()
    			+(new DatabaseAThread()).getFirstThreadComputingPowerChange()+
    			(new DatabaseAThread()).getSecondThreadComputingPowerChange()+
    			(new DatabaseBThread()).getFirstThreadComputingPowerChange()+
    			(new DatabaseBThread()).getSecondThreadComputingPowerChange();
    	cost=2*(new L1Server()).getCost()+2*(new L2Server()).getCost();
	}
	
	public int getTotalThreadCount(){
		return databaseAThreadsCount + databaseBThreadsCount;
	}
	
	public int getTotalServerCount(){
		return l1ServerCount + l2ServerCount;
	}
	
	
	//TODO: decide if you should change this later
	
	public String toString(){
		return "response time: "+getResponseTime()+", cost: "+cost+
				", contentQuality: "+String.valueOf(highQuality)+
				", time: "+time;
	}

}
