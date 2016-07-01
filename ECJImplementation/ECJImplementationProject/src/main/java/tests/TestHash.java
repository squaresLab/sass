package main.java.tests;

import main.java.main.OmnetStateData;
import main.java.main.OmnetStatePath;
import main.java.omnet.components.ServerA;
import main.java.omnet.tactics.DecreaseDimmerLevelA;
import main.java.omnet.tactics.IncreaseTrafficLevelA;
import main.java.omnet.tactics.ServerTactic;
import main.java.omnet.tactics.ShutdownServerA;
import main.java.omnet.tactics.StartNewServerA;

public class TestHash {
	public static void main(String[] args){
		OmnetStateData sd = new OmnetStateData();
		ServerTactic start = new StartNewServerA();
		ServerTactic increaseT = new IncreaseTrafficLevelA();
		ServerTactic shut = new ShutdownServerA();
		ServerTactic decreaseD = new DecreaseDimmerLevelA();
		OmnetStatePath state1 = new OmnetStatePath();
		OmnetStatePath state2 = new OmnetStatePath(); //always the initial
		
		state1.performTactic(increaseT);
		sd.visited.add(state1);
		
		
		boolean checkV = sd.visited.contains(state2);
		boolean check = state1.equals(state2);
		System.out.println(checkV);
//		sd.visited.add(state1);
//		sd.visited.add(state1);
//		System.out.println("Should be one in visited " + sd.visited.size());
//		state1.performTactic(start);
//		sd.visited.add(state1);
//		System.out.println("Should be two in visited " + sd.visited.size());
//		state1.performTactic(start);
//		sd.visited.add(state1);
//		System.out.println("Should be three in visited " + sd.visited.size());

//		state1.undoTactic();//state1 is back
//		sd.visited.add(state1);
//		System.out.println("Should be two in visited " + sd.visited.size());
//		System.out.println("Equals?" + state1.equals(state2));
		
		
//		System.out.println("containts? " + sd.visited.contains(state1));
//		System.out.println("Equals?" + state2.equals(state1));
		//System.out.println(sd.visited.size());
	}

}
