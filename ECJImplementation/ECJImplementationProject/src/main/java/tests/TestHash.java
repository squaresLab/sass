package main.java.tests;

import main.java.main.OmnetStateData;
import main.java.main.OmnetStatePath;
import main.java.omnet.tactics.IncreaseTrafficLevelA;
import main.java.omnet.tactics.ServerTactic;
import main.java.omnet.tactics.StartNewServerA;

public class TestHash {
	public static void main(String[] args){
		OmnetStateData sd = new OmnetStateData();
		ServerTactic start = new StartNewServerA();
		ServerTactic increase = new IncreaseTrafficLevelA();
		OmnetStatePath state1 = new OmnetStatePath();
		OmnetStatePath state2 = new OmnetStatePath(); //always the initial
		

		sd.visited.add(state1);
		sd.visited.add(state1);
		System.out.println("Should be one in visited " + sd.visited.size());
		state1.performTactic(start);
		sd.visited.add(state1);
		System.out.println("Should be two in visited " + sd.visited.size());
		state1.performTactic(start);
		sd.visited.add(state1);
		System.out.println("Should be three in visited " + sd.visited.size());

//		state1.undoTactic();//state1 is back
//		sd.visited.add(state1);
//		System.out.println("Should be two in visited " + sd.visited.size());
//		System.out.println("Equals?" + state1.equals(state2));
		
		
//		System.out.println("containts? " + sd.visited.contains(state1));
//		System.out.println("Equals?" + state2.equals(state1));
		//System.out.println(sd.visited.size());
	}

}
