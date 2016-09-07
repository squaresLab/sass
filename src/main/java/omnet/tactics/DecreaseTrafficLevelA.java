package omnet.tactics;

import main.OmnetStateData;
import main.OmnetStatePath;
import omnet.components.ServerA;

public class DecreaseTrafficLevelA extends DecreaseTrafficLevel {

	public DecreaseTrafficLevelA(){
		latency=5;
		failureWeight=0.01;
	}
	
	@Override
	public String toString(){
	 	return "DecreaseTrafficLevelA";
	}

	@Override
	public void callPerformTactic(OmnetStateData sd) {
		//sd.performTactic(this, ServerA.class);
		
	}

	@Override
	public void reallyPerform(OmnetStatePath state) {
		int serverIndex = OmnetStatePath.ServerType.SERVERA.ordinal();
		boolean tacticFail=false;
		if(state.countArray[serverIndex]==0){
			state.setAllStatesValid(false, "unable to decrease traffic level for"
					+ServerA.class.toString()+". There are no active servers of that type.");
			tacticFail=true;
			state.modifiedTrafficLevel.add(false);
		}else{
			if(state.serverArray[serverIndex].getTrafficLevel() == state.serverArray[serverIndex].getMaxTrafficLevel()){
				state.setAllStatesValid(false,"unable to decrease traffic level for"
						+ServerA.class.toString()+". The traffic level is already the highest possible"+
						" in the state.");
				tacticFail=true;
				state.modifiedTrafficLevel.add(false);
			} else{
				state.serverArray[serverIndex].setTrafficLevel(state.serverArray[serverIndex].getTrafficLevel()-1, state);
				state.modifiedTrafficLevel.add(true);
			}
		}
		if(tacticFail){
			state.invalidActions++;
		
		}
			state.alreadyPerformed.add(this);
	
		state.modifiedCountArray.add(false);
		state.emptyCount.add(false);
		state.modifiedDimmerLevel.add(false);
		state.totalTime += this.getLatency();
		state.pathProbability = state.pathProbability*(1-this.getFailureWeight());
		state.probabilityArray.add(state.pathProbability);
		}


	@Override
	public void reallyUndo(OmnetStatePath state) {
		int serverIndex = OmnetStatePath.ServerType.SERVERA.ordinal();
		state.setAllStatesValid(true,"undo the IncreaseTrafficLevel tactic");
		if(state.modifiedTrafficLevel.peekLast() != null && state.modifiedTrafficLevel.pollLast()){
			state.serverArray[serverIndex].setTrafficLevel(state.serverArray[serverIndex].getTrafficLevel()+1, state);
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
		state.modifiedDimmerLevel.removeLast();
		
	}


	@Override
	public void failForSure(OmnetStatePath state) {
		state.setAllStatesValid(false,"failing on purpose");
		state.modifiedDimmerLevel.add(false);
		state.modifiedCountArray.add(false);
		state.emptyCount.add(false);
		state.modifiedTrafficLevel.add(false);
		state.totalTime += this.getLatency();
		state.pathProbability = state.pathProbability*(1-this.getFailureWeight());
		state.probabilityArray.add(state.pathProbability);
		state.invalidActions++;
		state.alreadyPerformed.add(this);
	}


}
