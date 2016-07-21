package main.java.main;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Array;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

import javax.swing.text.html.HTMLDocument.Iterator;

import ec.gp.GPData;
import ec.gp.GPIndividual;
import ec.gp.GPNode;
import ec.gp.GPTree;
import main.java.actions.FailableTactic;
import main.java.actions.operators.IfThenElseOperator;
import main.java.actions.operators.SequenceOperator;
import main.java.omnet.components.OmnetComponent;
import main.java.omnet.tactics.DecreaseDimmerLevel;
import main.java.omnet.tactics.DecreaseTrafficLevel;
import main.java.omnet.tactics.IncreaseDimmerLevel;
import main.java.omnet.tactics.IncreaseTrafficLevel;
import main.java.omnet.tactics.ServerTactic;
import main.java.omnet.tactics.ShutdownServer;
import main.java.omnet.tactics.StartNewServer;


public class OmnetStateData extends GPData {



	public GPNode currentNode;
	int currentStep;
	GPNode initialTactic;
	public HashSet<OmnetStatePath> visited = new HashSet<OmnetStatePath>();//HashSet to store visited states
	//variable that holds if the current path could include the end of the plan
	boolean possiblePlanEnd=true;
	//path score is the score of the current evaluation path
	//double pathScore=0;
	//total score is the sum of all path scores times the path probability
	//double totalScore=0;
	int invalidActionCount=0;
	public static final double MINIMAL_INVALID_PLAN_SCORE=0.5;
	public static final double INVALID_ACTION_PENALTY=0.05;
	public static final int MAX_PATH_COPIES=1048576;
	//public int timesUpdatedScore=0;
	ArrayList<OmnetStatePath> paths;
	public ArrayList<Double> finalScores;
	boolean planTooLarge=false;
	ArrayList<ServerTactic> plan = new ArrayList<ServerTactic>();

	OmnetStatePath initialState = new OmnetStatePath();


	public OmnetStateData(){
		this.initializeState();
	}

	public void initializeState(){
		paths= new ArrayList<OmnetStatePath>();
		paths.add(new OmnetStatePath());
		finalScores = new ArrayList<Double>();
		possiblePlanEnd=true; 
		//totalScore=0;
		//pathScore=0;
		invalidActionCount=0;
		//timesUpdatedScore =0;
	}


	public boolean isPossiblePlanEnd(){
		return possiblePlanEnd;
	}

	public void setPossiblePlanEnd(boolean possiblePlanEnd){
		this.possiblePlanEnd = possiblePlanEnd;
	}


	public int getInvalidActionCount(){
		return invalidActionCount;
	}

	public void setInvalidActionCount(int invalidActionCount){
		this.invalidActionCount=invalidActionCount;
	}

	public boolean isPlanTooLarge(){
		return planTooLarge;
	}

	public void setPlanTooLarge(boolean planTooLarge){
		this.planTooLarge = planTooLarge;
	}


	/*Check the speed of this function later if you have optimization issues
	 * 
	 */
	public OmnetStateData deepCopy(){
		OmnetStateData copy=null;
		try {
			// Write the object out to a byte array
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(bos);
			out.writeObject(this);
			out.flush();
			out.close();

			// Make an input stream from the byte array and read
			// a copy of the object back in.
			ObjectInputStream in = new ObjectInputStream(
					new ByteArrayInputStream(bos.toByteArray()));
			copy = (OmnetStateData) in.readObject();
		}
		catch(IOException e) {
			e.printStackTrace();
			//ending the execution on an error for debugging
			System.exit(1);
		}
		catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
			//ending the executon on an error for debugging
			System.exit(1);
		}
		return copy;
	}


	// this computes fitness of itself
	public double getSingleObjectiveScore(){
		if(planTooLarge){
			//worst plan possible
			return calculateWorstPlanScore();
		}
		double score=0;
		boolean invalidPlan=false;
		for(OmnetStatePath path: paths){
			double pathScore=path.calculateProfit();
			if(pathScore==0){
				invalidPlan=true;
				break;
			}else{
				score+=pathScore*path.getPathProbability();
			}
		}
		if(invalidPlan || invalidActionCount>0){
			double invalidPlanPenalty;
			if(invalidActionCount*INVALID_ACTION_PENALTY<MINIMAL_INVALID_PLAN_SCORE){
				return 1/(MINIMAL_INVALID_PLAN_SCORE - invalidActionCount * INVALID_ACTION_PENALTY);
			} else {
				return calculateWorstPlanScore();
			}
		} else {
			return 1/score;
		}
	}

	public void extractPlan(GPNode node){
		ArrayDeque<GPNode> s = new ArrayDeque<GPNode>();
		s.add(node);
		while(s.isEmpty() == false){
			GPNode temp = s.pollFirst();
			if(temp.children.length > 0) s.add(temp.children[0]);
			if(temp.children.length > 1) s.add(temp.children[1]);

			if(temp instanceof ServerTactic){
				plan.add((ServerTactic) temp);
			}
		}
	}

	
	public void performAll(OmnetStatePath systemState, int start){
		for(int i = start; i < plan.size(); i++){
			systemState.performTactic(plan.get(i));
			currentStep = i;
		}
	}


	//undo tactics until it reach a state that has not been undone yet. 
	public void undoUntilVisited(OmnetStatePath systemState){
		systemState.undoTactic();
		while(visited.contains(systemState) && !(systemState.equals(initialState)) && currentStep >= 0){
			systemState.undoTactic();
			currentStep--;
		}
		visited.add(systemState); //at the new undone state to visited
	}

	public void addScore(OmnetStatePath systemState){
		double currentScore;
		if(systemState.invalidActions == 0){
			currentScore = systemState.calculateProfit() * systemState.getPathProbability();
			finalScores.add(1/currentScore);
		}
		else{
			currentScore = MINIMAL_INVALID_PLAN_SCORE - systemState.invalidActions * INVALID_ACTION_PENALTY;
			if(currentScore > 0){
				finalScores.add(1/currentScore);
			}
			else{
				finalScores.add(calculateWorstPlanScore());
			}
		}
	}

	public int countPossibleStates(GPNode ind) {
		int count = 0;
		int numVistedInitial = 0;
		double currentScore = 0;
		OmnetStatePath systemState = new OmnetStatePath(); //create a new state
		//count the left most path, unique situation
		extractPlan(ind);
		performAll(systemState,0);

		addScore(systemState);
		count++; //first time all success version of final state

		systemState.undoTactic();
		if(systemState.equals(initialState)){
			numVistedInitial++;
		}
		visited.add(systemState);
		systemState.performFailure(plan.get(currentStep));
		addScore(systemState);
		count++;
		undoUntilVisited(systemState);
		if(systemState.equals(initialState)){
			numVistedInitial++;
		}

		while(numVistedInitial < 2){
			systemState.performFailure(plan.get(currentStep));
			currentStep++;
			performAll(systemState,currentStep); 
			addScore(systemState);
			count++; //reached a success final state
			systemState.undoTactic();
			visited.add(systemState);
			systemState.performFailure(plan.get(currentStep));	
			addScore(systemState);
			count++;

			undoUntilVisited(systemState);
			if(systemState.equals(initialState)){
				numVistedInitial++;
			}
		}

		return count;
	}

	public void perform(ServerTactic tac){
		initialState.performTactic(tac);
	}

	public double averageScore(){
		double total = 0;
		for(int i = 0; i < finalScores.size(); i++){
			total = total + finalScores.get(i);
		}
		return total/finalScores.size();
	}

	public void printScores(){
		for(int i = 0; i < finalScores.size(); i++){
			System.out.println("The " + (i+1) + " final state score is " + finalScores.get(i));
		}
	}

	public static double calculateWorstPlanScore(){

		return 1/(MINIMAL_INVALID_PLAN_SCORE % INVALID_ACTION_PENALTY);

	}

	public OmnetStateData createFailureBranch(double failureProbability){
		if(paths.size() >  MAX_PATH_COPIES){
			//state space is getting to large, going to run out of memory
			planTooLarge=true;
			return null;
		}
		OmnetStateData copy = this.deepCopy();
		//don't need to set success path probably here, that will be updated when the tactic succeeds.
		//for(OmnetStatePath path: paths){
		//	path.setPathProbability(path.getPathProbability()*(1-failureProbability));
		//}

		//need to update failure probability here because the failure never occurs
		for(OmnetStatePath path: copy.paths){
			path.setPathProbability(path.getPathProbability()*failureProbability);
		}
		copy.invalidActionCount=0;
		return copy;
	}

	public void mergeStateDatea(OmnetStateData o){
		for(OmnetStatePath path: o.paths){
			this.paths.add(path);
		}
		this.invalidActionCount+=o.invalidActionCount;
	}

	public void setPathsInvalid(String failingNode){
		invalidActionCount++;
		for(OmnetStatePath path: paths){
			path.setAllStatesValid(false, "if statement must "
					+ "test a tactic that can fail.  Currently"
					+ "testing "+failingNode);
		}

	}

	public boolean areAllPathsValid(){
		for(OmnetStatePath path: paths){
			if(!path.allStatesValid){
				return false;
			}
		}
		return true;
	}

	public ArrayList<OmnetStatePath> deepCopyPaths(){
		ArrayList<OmnetStatePath>copy=null;
		try {
			// Write the object out to a byte array
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			ObjectOutputStream out = new ObjectOutputStream(bos);
			out.writeObject(this.paths);
			out.flush();
			out.close();

			// Make an input stream from the byte array and read
			// a copy of the object back in.
			ObjectInputStream in = new ObjectInputStream(
					new ByteArrayInputStream(bos.toByteArray()));
			copy = (ArrayList<OmnetStatePath>) in.readObject();
		}
		catch(IOException e) {
			e.printStackTrace();
			//ending the execution on an error for debugging
			System.exit(1);
		}
		catch(ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
			//ending the executon on an error for debugging
			System.exit(1);
		}
		return copy;
	}

	public int getTotalPlanTime(){
		double averagePlanTime=0;
		for(OmnetStatePath path: paths){
			averagePlanTime+=path.getTotalTime();
		}
		averagePlanTime=averagePlanTime/paths.size();
		return (int)Math.round(averagePlanTime);
	}

	public double getPlanCost(){
		double averageCost=0;
		for(OmnetStatePath path: paths){
			averageCost+=path.totalServerCostPerSecond();
		}
		return averageCost/paths.size();

	}

	public double getPlanRequestsHandledPerSecond(){
		double averageHandledRequests=0;
		for(OmnetStatePath path: paths){
			averageHandledRequests+=path.requestsHandledPerSecond();
		}
		return averageHandledRequests/paths.size();
	}

	public double getPlanGrossIncome(){
		double averageGrossIncome=0;
		for(OmnetStatePath path:paths){
			averageGrossIncome+=path.currentGrossIncome();
		}
		return averageGrossIncome/paths.size();
	}

	public boolean isPlanValid(){
		if(invalidActionCount>0){
			return false;
		}
		//shouldn't need this next check but doing it to be safe
		for(OmnetStatePath path: paths){
			if(!path.areAllStatesValid()){
				return false;
			}
		}
		return true;
	}

}


