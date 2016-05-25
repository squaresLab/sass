package main.java.omnet.components;

import java.io.Serializable;

import main.java.main.OmnetStateData;
import main.java.main.OmnetStatePath;


public abstract class OmnetComponent implements Cloneable, Serializable{
	
	// how much does the change cost per second
	// if the cost is part of an equation, then
	// the cost will be set to zero and used in another place
	// Note: may change this later to use a different data
	// type, maybe something that includes a Java lambda
	// to handle the equations - look into it later when you
	// know the specifics
	double costPerSecond;
	// the requests that the server can handle per second
	// when operating normally
	int normalRequestsHandledPerSecond;
	// the requests that the server can handle per second
	// when operating in dimmed mode;
	// in general, this should be higher
	// than normalRequestsHandledPerSecond
	int dimmedRequestsHandledPerSecond;
	//This is the highest value of the dimmer
	//so 4 out 4 dimmer would be 100% dimming
	public static final int MaxDimmerLevel=4;
	//The level the quality of the server traffic
	//is reduced
	int dimmerLevel;
	// The ratio that the server receives of 
	// new traffic.  For example if one 
	// server has a traffic level of 4 and the
	// other server has a traffic level of 1
	// the first server would get 4 request
	// for every one that the second server
	// handles
	public static final int MaxTrafficLevel=4;
	// the value of the traffic level of the 
	// server
	int trafficLevel;
	
	//Only call the classes super constructor
	//if you want to use the default values
	//for the dimmerLevel and trafficLevel
	public OmnetComponent(){
		//default dimmerLevel
		dimmerLevel=0;
		//default trafficLevel
		trafficLevel=4;
	}
	

	
	public void setCostPerSecond(double newCostPerSecond){
		this.costPerSecond = newCostPerSecond;
	}
	
	public double getCostPerSecond(){
		return this.costPerSecond;
	}
	
	
	public int getNormalRequestsHandledPerMinute() {
		return normalRequestsHandledPerSecond;
	}

	public void setNormalRequestsHandledPerMinute(int normalRequestsHandledPerMinute) {
		this.normalRequestsHandledPerSecond = normalRequestsHandledPerMinute;
	}

	public int getDimmedRequestsHandledPerMinute() {
		return dimmedRequestsHandledPerSecond;
	}

	public void setDimmedRequestsHandledPerMinute(int dimmedRequestsHandledPerMinute) {
		this.dimmedRequestsHandledPerSecond = dimmedRequestsHandledPerMinute;
	}

	public int getMaxDimmerLevel(){
		return MaxDimmerLevel;
	}

	public void setDimmerLevel(int newDimmerLevel,OmnetStatePath o) {
		if(newDimmerLevel < 0 || newDimmerLevel > MaxDimmerLevel){
			o.setAllStatesValid(false, "Provided dimmer level of "
					+ newDimmerLevel + " is outside valid dimmer" +
					"level range.");
		} else {
			this.dimmerLevel = newDimmerLevel;
		}
	}
	
	public int getDimmerLevel(){
		return this.dimmerLevel;
	}
	
	public int getMaxTrafficLevel() {
		return this.MaxTrafficLevel;
	}
	
	public int getTrafficLevel() {
		return trafficLevel;
	}

	public void setTrafficLevel(int newTrafficLevel, OmnetStatePath o) {
		if(newTrafficLevel < 0 || newTrafficLevel > MaxTrafficLevel){
			o.setAllStatesValid(false, "the provided traffic level "+
				newTrafficLevel+" is not inside the valid range.");	
		} else {
			this.trafficLevel = newTrafficLevel;
		}
	}
	
	@Override
	public OmnetComponent clone(){
		try {
			return (OmnetComponent) super.clone();
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	
	
	
}
