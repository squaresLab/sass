package main.java.omnet.tactics;

import main.java.main.OmnetStateData;
import main.java.main.OmnetStatePath;
import main.java.omnet.components.ServerA;
import main.java.omnet.components.ServerB;

public class ShutdownServerB extends ShutdownServer {

	public ShutdownServerB(){
		latency=20;
		failureWeight=0.1;
	}


	@Override
	public String toString(){
		return "ShutdownServerB";
	}

	@Override
	public void callPerformTactic(OmnetStateData sd) {
		sd.performTactic(this, ServerB.class);

	}


	@Override
	public void reallyPerform(OmnetStatePath state) {
		int serverIndex = state.getServerIndex(ServerB.class);
		boolean tacticFail=false;
		if (state.countArray[serverIndex] == 0){
			state.setAllStatesValid(false,"unable to shutdown "
					+ServerB.class.toString()+ ".  There are no "
					+ "active servers of type "+ServerB.class.toString());
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
		state.totalTime += this.getLatency();
		state.pathProbability = state.pathProbability*(1-this.getFailureWeight());
	}


}
