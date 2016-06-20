package main.java.omnet.tactics;

import main.java.main.OmnetStateData;
import main.java.main.OmnetStatePath;
import main.java.omnet.components.ServerA;

public class StartNewServerA extends StartNewServer {

	public StartNewServerA(){
		latency = 120;
		failureWeight=0.1;
	}
	
	@Override
	public String toString(){
	 	return "StartNewServerA";
	}

	@Override
	public void callPerformTactic(OmnetStateData sd) {
		sd.performTactic(this, ServerA.class);
		
	}

	@Override
	public void reallyPerform(OmnetStatePath state) {
		int serverIndex = state.getServerIndex(ServerA.class);
		boolean tacticFail=false;
		if(state.countArray[serverIndex]+1 > state.MaxServerCount){
			state.setAllStatesValid(false, "unable to start up "+ ServerA.class.toString()
			+" there are already the max amount of servers"
			+ "at that location");
			tacticFail=true;
			state.emptyCount.add(false);
			state.modifiedCountArray.add(false);
		} else{
			try {
				if(state.countArray[serverIndex] == 0){
					state.serverArray[serverIndex]=ServerA.class.newInstance();
					state.emptyCount.add(true); //JW added this to keep track if there is no server at all before startnewserver
				}
				state.countArray[serverIndex]++;
				state.modifiedCountArray.add(true);//JW added this to keep track if countArray[serverIndex] is changed.

			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(!tacticFail){
			state.alreadyPerformed.add(this);
		}
		state.totalTime += this.getLatency();
		state.pathProbability = state.pathProbability*(1-this.getFailureWeight());
	}

	@Override
	public void reallyUndo(OmnetStatePath state) {
		int serverIndex = state.getServerIndex(ServerA.class);
		if(state.emptyCount.peekLast() != null && state.emptyCount.pollLast()){
				state.serverArray[serverIndex] = null;
		}
		
		if(state.modifiedCountArray.peekLast() != null && state.modifiedCountArray.pollLast()){
			state.countArray[serverIndex]--;
		}
		state.setAllStatesValid(true,"Undo the StarNewServer tactic");
		state.totalTime-= this.getLatency();
		state.pathProbability = state.pathProbability/(1-this.getFailureWeight());
	}

}

