package main.java.actions;

import ec.EvolutionState;
import ec.Problem;
import ec.gp.ADFStack;
import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;
import ec.util.Parameter;
import main.java.main.StateData;
import main.java.znn.components.ZnnComponent;

public abstract class ActionTemplate extends GPNode{
	
	
	protected ZnnComponent component;
	protected boolean succeeded = true; //default assume it succeeds
    
	public boolean hasSucceeded(){
		return succeeded;
	}

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
    public void eval(final EvolutionState state,
    		      final int thread,
    		      final GPData input,
    		      final ADFStack stack,
    		      final GPIndividual individual,
    		      final Problem problem){
    	
    	StateData sd = (StateData)input;
    	//no need to evaluate any more if an invalid state has been reached
    	if(sd.getReachedInvalidState()){
    		return;
    	}
    	if(state.random[0].nextDouble() > component.getFailureWeight()){
    	   succeeded=true;
    	   sd.setTime(sd.getTime()+component.getClockTime());
    	   if(areAddingItem()){
    	     sd.setComputingPower(sd.getComputingPower()+component.getComputingPowerChange(sd));
    	     sd.setCost(sd.getCost()+component.getCost());
    	   } else {
    		   sd.setComputingPower(sd.getComputingPower()-component.getComputingPowerChange(sd));
    		   sd.setCost(sd.getCost()-component.getCost());
    	   }
           uniqueSuccessChanges(sd);
           //System.out.println("clocktime: "+clockTime+", responseChange: "+responseChange+", cost: "+cost);
           if(sd.getCost() < 0 || sd.getResponseTime() < 0 || sd.getSecurity() < 0 
        		   || sd.getServerCount() < 0 || sd.getTime() < 0){
        	   sd.setReachedInvalidState(true);
           }
    	}
    	else{
    		succeeded = false;
    		sd.setTime(sd.getTime()+component.getClockTime());
    	}
    	//System.out.println("State after action - "+
    	//this.getClass().toString()+" was "+String.valueOf(success)+": "+sd.toString());
    }
    
    public void uniqueSuccessChanges(StateData sd){
    	
        if(isInvalidChange(sd)){
        	sd.setReachedInvalidState(true);
        }
        //revaluating for debugging
        //invalidChangeAtLocation(sd);
    }

    abstract protected boolean isInvalidChange(StateData sd);
    
    abstract protected boolean areAddingItem();
	
}
