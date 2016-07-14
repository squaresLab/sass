package main.java.omnet.tactics;

import main.java.main.OmnetStateData;
import main.java.main.OmnetStatePath;
import main.java.omnet.components.ServerA;

public class ShutdownServerA extends ShutdownServer {

	public ShutdownServerA(){
		latency=20;
		failureWeight=0.1;
	}

	@Override
	public String toString(){
		return "ShutdownServerA";
	}

	@Override
	public void callPerformTactic(OmnetStateData sd) {
		//sd.performTactic(this, ServerA.class);

	}


	@Override
	public void reallyPerform(OmnetStatePath state) {
		int serverIndex = OmnetStatePath.ServerType.SERVERA.ordinal();
		boolean tacticFail=false;
		if (state.countArray[serverIndex] == 0){
			state.setAllStatesValid(false,"unable to shutdown "
					+ServerA.class.toString()+ ".  There are no "
					+ "active servers of type "+ServerA.class.toString());
			tacticFail=true;
			state.modifiedCountArray.add(false);
		}	else if(state.getTotalServerCount()==1){
			state.setAllStatesValid(false, "unable to shutdown"
					+ ServerA.class.toString()+ ".  The"
					+ "system would become unoperationable due"
					+ "to no servers being active.");
			tacticFail=true;
			state.modifiedCountArray.add(false);
		} else {
			state.countArray[serverIndex]--;
			state.modifiedCountArray.add(true); 
		}
		if(!tacticFail){
			state.alreadyPerformed.add(this);
		}
		state.emptyCount.add(false);
		state.modifiedDimmerLevel.add(false);
		state.modifiedTrafficLevel.add(false);
		state.totalTime += this.getLatency();
		state.pathProbability = state.pathProbability*(1-this.getFailureWeight());
		state.probabilityArray.add(state.pathProbability);
	}

	public void reallyUndo(OmnetStatePath state) {
		int serverIndex = OmnetStatePath.ServerType.SERVERA.ordinal();
		state.setAllStatesValid(true,"Undo the ShutdownServerA tactic");
		if(state.modifiedCountArray.peekLast() != null && state.modifiedCountArray.pollLast()){
			state.countArray[serverIndex]++;
		}	
		else{
			state.invalidActions--;
		}
		state.totalTime-=this.getLatency();
		state.probabilityArray.removeLast();
		if(state.probabilityArray.peekLast() != null){
			state.pathProbability = state.probabilityArray.peekLast();
		}
		state.modifiedTrafficLevel.removeLast();
		state.modifiedDimmerLevel.removeLast();
		state.emptyCount.removeLast();
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
		state.alreadyPerformed.add(this);
		state.invalidActions++;
	}
}
