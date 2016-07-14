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
		//sd.performTactic(this, ServerA.class);

	}

	@Override
	public void reallyPerform(OmnetStatePath state) {
		boolean tacticFail=false;
		int serverIndex = OmnetStatePath.ServerType.SERVERA.ordinal();
		if(state.countArray[serverIndex]+1 > state.MaxServerCount){
			state.setAllStatesValid(false, "unable to start up "+ ServerA.class.toString()
					+" there are already the max amount of servers"
					+ "at that location");
			tacticFail=true;
			state.emptyCount.add(false);
			state.modifiedCountArray.add(false);
			state.modifiedTrafficLevel.add(false);
			state.modifiedDimmerLevel.add(false);
		} else{
			try {
				if(state.countArray[serverIndex] == 0){
					state.serverArray[serverIndex]=ServerA.class.newInstance();
					state.emptyCount.add(true);
					//state.modifiedTrafficLevel.add(false);
				}
				state.countArray[serverIndex]++;
				state.modifiedCountArray.add(true);
				state.emptyCount.add(false);
				state.modifiedTrafficLevel.add(false);
				state.modifiedDimmerLevel.add(false);

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
		state.probabilityArray.add(state.pathProbability);
	}

	@Override
	public void reallyUndo(OmnetStatePath state) {
		int serverIndex = OmnetStatePath.ServerType.SERVERA.ordinal();
		state.setAllStatesValid(true,"Undo the StarNewServer tactic");
		if(state.emptyCount.peekLast() != null && state.emptyCount.pollLast()){
			state.serverArray[serverIndex] = null;
		}
		else if(state.modifiedCountArray.peekLast() != null && state.modifiedCountArray.pollLast()){
			state.countArray[serverIndex]--;
		}
		else{
			state.invalidActions--;
		}
		state.totalTime-= this.getLatency();
		state.probabilityArray.removeLast();
		if(state.probabilityArray.peekLast() != null){
			state.pathProbability = state.probabilityArray.peekLast();
		}
		state.modifiedTrafficLevel.removeLast();
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
		state.alreadyPerformed.add(this);
		state.invalidActions++;
	}

}

