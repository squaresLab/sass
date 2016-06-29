package main.java.tests;

import main.java.main.OmnetStateData;
import main.java.main.OmnetStatePath;
import main.java.omnet.tactics.IncreaseTrafficLevelA;
import main.java.omnet.tactics.ServerTactic;
import main.java.omnet.tactics.StartNewServerA;

public class TestHash {
	public static void main(String[] args){
		OmnetStateData sd = new OmnetStateData();
		ServerTactic tac = new StartNewServerA();
		ServerTactic tac2 = new IncreaseTrafficLevelA();
		OmnetStatePath state1 = new OmnetStatePath();
		OmnetStatePath state2 = new OmnetStatePath();
		sd.visited.add(state1);
		state1.performTactic(tac);
		sd.visited.add(state1);
		System.out.println("containts? " + sd.visited.contains(state1));
		System.out.println("Equals?" + state2.equals(state1));
		//System.out.println(sd.visited.size());
	}

}
