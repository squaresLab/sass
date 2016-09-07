package znn.components;


import actions.Component;
import main.StateData;

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
