package main.java.znn.components;


import main.java.actions.Component;
import main.java.main.StateData;

public class ZnnComponent extends Component {
	int clockTime;
    double computingPowerChange;
    double failureWeight;
    int cost;
    
    public int getClockTime() {
		return clockTime;
	}

    //taking in the parameter because some classes need to override the 
    //method to take the current state into account
	public double getComputingPowerChange(StateData sd) {
		return computingPowerChange;
	}

	public double getFailureWeight() {
		return failureWeight;
	}

	public int getCost() {
		return cost;
	}
}
