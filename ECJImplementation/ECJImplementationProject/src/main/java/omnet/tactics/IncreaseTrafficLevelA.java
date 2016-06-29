package main.java.omnet.tactics;

import main.java.main.OmnetStateData;
import main.java.main.OmnetStatePath;
import main.java.omnet.components.ServerA;

public class IncreaseTrafficLevelA extends IncreaseTrafficLevel {

	public IncreaseTrafficLevelA(){
		latency=5;
		failureWeight=0.01;
	}
	
	
	@Override
	public String toString(){
	 	return "IncreaseTrafficLevelA";
	}

	@Override
	public void callPerformTactic(OmnetStateData sd) {
		//sd.performTactic(this, ServerA.class);
		
	}


	@Override
	public void reallyPerform(OmnetStatePath state) {
		int serverIndex = state.getServerIndex(ServerA.class);
		boolean tacticFail=false;
		if(state.countArray[serverIndex]==0){
			state.setAllStatesValid(false, "unable to increase traffic level for"
					+ServerA.class.toString()+". There are no active servers of that type.");
			tacticFail=true;
			state.modifiedTrafficLevel.add(false);
		}else{
			if(state.serverArray[serverIndex].getTrafficLevel() == state.serverArray[serverIndex].getMaxTrafficLevel()){
				state.setAllStatesValid(false,"unable to increase traffic level for"
						+ServerA.class.toString()+". The traffic level is already the highest possible"+
						" in the state.");
				tacticFail=true;
				state.modifiedTrafficLevel.add(false);
			} else{
				state.serverArray[serverIndex].setTrafficLevel(state.serverArray[serverIndex].getTrafficLevel()+1, state);
				state.modifiedTrafficLevel.add(true);
			}
		}
		if(!tacticFail){
			state.alreadyPerformed.add(this);
		}
		state.totalTime += this.getLatency();
		state.pathProbability = state.pathProbability*(1-this.getFailureWeight());
		state.probabilityArray.add(state.pathProbability);
	}


	@Override
	public void reallyUndo(OmnetStatePath state) {
		int serverIndex = state.getServerIndex(ServerA.class);
		state.setAllStatesValid(true,"undo the IncreaseTrafficLevel tactic");
		if(state.modifiedTrafficLevel.peekLast() != null && state.modifiedTrafficLevel.pollLast()){
			state.serverArray[serverIndex].setTrafficLevel(state.serverArray[serverIndex].getTrafficLevel()-1, state);
		}
		state.totalTime -= this.getLatency();
		if(state.probabilityArray.peekLast() != null && state.pathProbability == state.probabilityArray.peekLast()){
			state.probabilityArray.removeLast();
		}
		if(state.probabilityArray.peek() != null){
			state.pathProbability = state.probabilityArray.pollLast();
		}
		
	}


	@Override
	public void failForSure(OmnetStatePath state) {
		state.setAllStatesValid(false,"failing on purpose");
		state.alreadyPerformed.add(this);
		state.totalTime += this.getLatency();
		state.pathProbability = state.pathProbability*(1-this.getFailureWeight());
		state.probabilityArray.add(state.pathProbability);
	}
	
}
