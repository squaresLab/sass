package main.java.omnet.tactics;

import main.java.main.OmnetStateData;
import main.java.main.OmnetStatePath;
import main.java.omnet.components.ServerG;

public class DecreaseDimmerLevelG extends DecreaseDimmerLevel {

	public DecreaseDimmerLevelG(){
		latency=1;
		failureWeight=0.05;
	}
	
	@Override
	public String toString(){
	 	return "DecreaseDimmerLevelG";
	}

	@Override
	public void callPerformTactic(OmnetStateData sd) {
		//sd.performTactic(this, ServerG.class);
		
	}
	@Override
	public void reallyPerform(OmnetStatePath state) {
		int serverIndex = OmnetStatePath.ServerType.SERVERG.ordinal();
		boolean tacticFail=false;
		if(state.countArray[serverIndex]==0){
			state.setAllStatesValid(false, "unable to decrease dimmer level for"
					+this.toString()+". There are no active servers of that type.");
			tacticFail=true;
			state.modifiedDimmerLevel.add(false);
		}else if(state.serverArray[serverIndex].getDimmerLevel()==0){
			state.setAllStatesValid(false, "unable to decrease dimmer level for"
					+this.toString()+". The dimmer level is already the lowest possible"+
					" in the state.");
			tacticFail=true;
			state.modifiedDimmerLevel.add(false);
		} else{
			state.serverArray[serverIndex].setDimmerLevel(state.serverArray[serverIndex].getDimmerLevel()-1, state);
			state.modifiedDimmerLevel.add(true);
		}
		if(tacticFail){
			state.invalidActions++;
		
		}
			state.alreadyPerformed.add(this);
	
		state.modifiedCountArray.add(false);
		state.emptyCount.add(false);
		state.modifiedTrafficLevel.add(false);
		state.totalTime += this.getLatency();
		state.pathProbability = state.pathProbability*(1-this.getFailureWeight());
		state.probabilityArray.add(state.pathProbability);
	}


	@Override
	public void reallyUndo(OmnetStatePath state) {
		int serverIndex = OmnetStatePath.ServerType.SERVERG.ordinal();
		state.setAllStatesValid(true,"Undo the DecreaseDimmerLevel tactic");
		if(state.modifiedDimmerLevel.peekLast() != null && state.modifiedDimmerLevel.pollLast()){
			state.serverArray[serverIndex].setDimmerLevel(state.serverArray[serverIndex].getDimmerLevel()+1, state);
		}else{
			state.invalidActions--;
		}
		state.totalTime -= this.getLatency(); 
		state.probabilityArray.removeLast();
		if(state.probabilityArray.peekLast() != null){
			state.pathProbability = state.probabilityArray.peekLast();
		}
		state.modifiedCountArray.removeLast();
		state.emptyCount.removeLast();
		state.modifiedTrafficLevel.removeLast();
	}
	
	@Override
	public void failForSure(OmnetStatePath state) {
		state.setAllStatesValid(false,"failing on purpose");
		state.modifiedTrafficLevel.add(false);
		state.modifiedCountArray.add(false);
		state.emptyCount.add(false);
		state.modifiedDimmerLevel.add(false);
		state.totalTime += this.getLatency();
		state.pathProbability = state.pathProbability*(1-this.getFailureWeight());
		state.probabilityArray.add(state.pathProbability);
		state.invalidActions++;
		state.alreadyPerformed.add(this);
	}


}
