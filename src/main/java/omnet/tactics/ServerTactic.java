package omnet.tactics;

import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.util.Parameter;
import actions.FailableTactic;
import main.OmnetStateData;
import main.OmnetStatePath;

public abstract class ServerTactic extends FailableTactic {

	//time to perform the tactic
	int latency; 

	//if the tactic is located in the test position of an if statement
	boolean inIfStatementTest = false;

	public void setLatency(int newLatency){
		this.latency=newLatency;
	}

	public int getLatency(){
		return this.latency;
	}

	public void setInIfStatementTest(boolean inIfStatementTest){
		this.inIfStatementTest = inIfStatementTest;
	}

	public boolean isInIfStatementTest(){
		return inIfStatementTest;
	}

	@Override
	public void eval(EvolutionState state, int thread, GPData input, ADFStack stack, GPIndividual individual,
			Problem problem) {
		OmnetStateData sd = (OmnetStateData)input;
		//callPerformTactic(sd);
		sd.getAllFinalStates(this);
		//sd.performOne(this);

	}

	public abstract void failForSure(OmnetStatePath state);

	public abstract void callPerformTactic(OmnetStateData sd);

	public abstract void reallyPerform(OmnetStatePath state);

	public abstract void reallyUndo(OmnetStatePath state);

	//may need to change the state later
	@Override
	public void checkConstraints(EvolutionState state, int tree,
			GPIndividual typicalIndividual, Parameter individualBase){
		super.checkConstraints(state,tree,typicalIndividual, individualBase);
		//need to replace sd with the state later
		/*if(currentServerCount < maxServerCount 
    			&& sd.getResponseTime() > Math.abs(responseChange)){
    		//check succeeds here

    	}*/
		if(children.length!=0){
			//check fails here
			state.output.error("Incorrect number of children for node " + 
					toStringForError() + " at " +
					individualBase);
		}


	}

	@Override
	public abstract String toString();


}
