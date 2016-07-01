package main.java.main;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
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
	boolean planTooLarge=false;
	public HashSet<OmnetStatePath> visited = new HashSet<OmnetStatePath>();//HashSet to store visited states
	public GPNode currentNode;
	GPNode initialTactic;
	int numVistedInitial = 0;
	OmnetStatePath initialState = new OmnetStatePath();


	public OmnetStateData(){
		this.initializeState();
	}

	public void initializeState(){
		paths= new ArrayList<OmnetStatePath>();
		paths.add(new OmnetStatePath());
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

	/*public double getTotalScore(){
		return totalScore;
	}

	public void setTotalScore(double totalScore){
		this.totalScore=totalScore;
	}

	public double getPathScore(){
		return pathScore;
	}

	public void setPathScore(double pathScore){
		this.pathScore=pathScore;
	}*/

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

	/*
	 * Will later need to change this to also handle multi-objective functions
	 */
	/*private void setEndOfPathScore(OmnetStatePath path){
		pathScore = path.calculateProfit()*path.getPathProbability();
		timesUpdatedScore++;
		totalScore+=pathScore;
	}*/

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
	//
	//	@SuppressWarnings("unchecked")
	//	public <T extends OmnetComponent> void performTactic(StartNewServer s, Class<T> c){
	//		boolean notTestedInIf = !s.isInIfStatementTest();
	//
	//		ArrayList<OmnetStatePath> failPaths = null;
	//		if(notTestedInIf){
	//			failPaths = deepCopyPaths();
	//		}
	//		boolean hadInvalidAction=false;
	//		for(OmnetStatePath path: paths){
	//			if(path.performTactic(s,c)){
	//				hadInvalidAction=true;
	//			}
	//		}
	//		if(notTestedInIf){
	//			for(OmnetStatePath path: failPaths){
	//				path.setTotalTime(path.getTotalTime()+s.getLatency());
	//				path.setPathProbability(path.getPathProbability()*s.getFailureWeight());
	//			}
	//			paths.addAll(failPaths);
	//		}
	//		if(hadInvalidAction){
	//			invalidActionCount++;
	//			s.setActionSucceeded(false);
	//		}
	//		if(paths.size()>MAX_PATH_COPIES){
	//			planTooLarge=true;
	//		}
	//	}
	//
	//	public <T extends OmnetComponent> void performTactic(ShutdownServer s, Class<T> c) {
	//		boolean notTestedInIf = !s.isInIfStatementTest();
	//		ArrayList<OmnetStatePath> failPaths = null;
	//		if(notTestedInIf){
	//			failPaths=deepCopyPaths();
	//		}
	//		boolean hadInvalidAction=false;
	//		for(OmnetStatePath path: paths){
	//			if(path.performTactic(s,c)){
	//				hadInvalidAction=true;
	//			}
	//		}
	//		if(notTestedInIf){
	//			for(OmnetStatePath path: failPaths){
	//				path.setTotalTime(path.getTotalTime()+s.getLatency());
	//				path.setPathProbability(path.getPathProbability()*s.getFailureWeight());
	//			}
	//			paths.addAll(failPaths);
	//		}
	//		if(hadInvalidAction){
	//			invalidActionCount++;
	//			s.setActionSucceeded(false);
	//		}
	//		if(paths.size()>MAX_PATH_COPIES){
	//			planTooLarge=true;
	//		}
	//	}
	//
	//	public <T extends OmnetComponent> void performTactic(IncreaseDimmerLevel d, Class<T> c){
	//		boolean notTestedInIf = !d.isInIfStatementTest();
	//		ArrayList<OmnetStatePath> failPaths = null;
	//		if(notTestedInIf){
	//			failPaths=deepCopyPaths();
	//		}
	//		boolean hadInvalidAction=false;
	//		for(OmnetStatePath path:paths){
	//			if(path.performTactic(d,c)){
	//				hadInvalidAction=true;
	//			}
	//		}
	//		if(notTestedInIf){
	//			for(OmnetStatePath path: failPaths){
	//				path.setTotalTime(path.getTotalTime()+d.getLatency());
	//				path.setPathProbability(path.getPathProbability()*d.getFailureWeight());
	//			}
	//			paths.addAll(failPaths);
	//		}
	//		if(hadInvalidAction){
	//			invalidActionCount++;
	//			d.setActionSucceeded(false);
	//		}
	//		if(paths.size()>MAX_PATH_COPIES){
	//			planTooLarge=true;
	//		}
	//	}
	//
	//	public <T extends OmnetComponent> void performTactic(DecreaseDimmerLevel d, Class<T> c){
	//		boolean notTestedInIf = !d.isInIfStatementTest();
	//		ArrayList<OmnetStatePath> failPaths = null;
	//		if(notTestedInIf){
	//			failPaths=deepCopyPaths();
	//		}
	//		boolean hadInvalidAction=false;
	//		for(OmnetStatePath path:paths){
	//			if(path.performTactic(d,c)){
	//				hadInvalidAction=true;
	//			}
	//		}
	//		if(notTestedInIf){
	//			for(OmnetStatePath path: failPaths){
	//				path.setTotalTime(path.getTotalTime()+d.getLatency());
	//				path.setPathProbability(path.getPathProbability()*d.getFailureWeight());
	//			}
	//			paths.addAll(failPaths);
	//		}
	//		if(hadInvalidAction){
	//			invalidActionCount++;
	//			d.setActionSucceeded(false);
	//		}
	//		if(paths.size()>MAX_PATH_COPIES){
	//			planTooLarge=true;
	//		}
	//	}
	//
	//	public <T extends OmnetComponent> void performTactic(IncreaseTrafficLevel t, Class<T> c){
	//		boolean notTestedInIf = !t.isInIfStatementTest();
	//		ArrayList<OmnetStatePath> failPaths=null;
	//		if(notTestedInIf){
	//			failPaths = deepCopyPaths();
	//		}
	//		boolean hadInvalidAction=false;
	//		for(OmnetStatePath path:paths){
	//			if(path.performTactic(t,c)){
	//				hadInvalidAction=true;
	//			}
	//		}
	//		if(notTestedInIf){
	//			for(OmnetStatePath path:failPaths){
	//				path.setTotalTime(path.getTotalTime()+t.getLatency());
	//				path.setPathProbability(path.getPathProbability()*t.getFailureWeight());
	//			}
	//			paths.addAll(failPaths);
	//		}
	//		if(hadInvalidAction){
	//			invalidActionCount++;
	//			t.setActionSucceeded(false);
	//		}
	//		if(paths.size()>MAX_PATH_COPIES){
	//			planTooLarge=true;
	//		}
	//	}

	//	public <T extends OmnetComponent> void performTactic(DecreaseTrafficLevel t, Class<T> c){
	//		boolean notTestedInIf = !t.isInIfStatementTest();
	//		ArrayList<OmnetStatePath> failPaths = null;
	//		if(notTestedInIf){
	//			failPaths=deepCopyPaths();
	//		}
	//		boolean hadInvalidAction=false;
	//		for(OmnetStatePath path:paths){
	//			if(path.performTactic(t,c)){
	//				hadInvalidAction=true;
	//			}
	//		}
	//		if(notTestedInIf){
	//			for(OmnetStatePath path: failPaths){
	//				path.setTotalTime(path.getTotalTime()+t.getLatency());
	//				path.setPathProbability(path.getPathProbability()*t.getFailureWeight());
	//			}
	//			paths.addAll(failPaths);
	//		}
	//		if(hadInvalidAction){
	//			invalidActionCount++;
	//			t.setActionSucceeded(false);
	//		}
	//		if(paths.size()>MAX_PATH_COPIES){
	//			planTooLarge=true;
	//		}
	//	}

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


	//perform a given plan on a given state	
	public void performAll(GPNode node, OmnetStatePath systemState){
		ArrayDeque<GPNode> s = new ArrayDeque<GPNode>();
		s.add(node);
		while(s.isEmpty() == false){
			GPNode temp = s.pollFirst();

			if(temp.children[0] != null) s.add(temp.children[0]);
			if(temp.children[1] != null) s.add(temp.children[1]);

			if(temp instanceof ServerTactic){
				systemState.performTactic((ServerTactic) temp);
				currentNode = (GPNode) temp.parent;
			}
			else{
				currentNode = (GPNode) temp;
			}
		}
	}


	//undo tactics until it reach a state that has not been undone yet. 
	public void undoUntilVisited(OmnetStatePath systemState){
		systemState.undoTactic();
		while(visited.contains(systemState) && !(systemState.equals(initialState))){
			systemState.undoTactic();
			//if(currentNode != initialTactic){
				currentNode = (GPNode) currentNode.parent;
			//}
			
		}
		visited.add(systemState); //at the new undone state to visited
		//System.out.println(currentNode);
	}

	public int countPossibleStates(GPIndividual ind) {
		int count = 0;
		initialTactic = ind.trees[0].child; //the tactic to begin with.
		OmnetStatePath systemState = new OmnetStatePath(); //create a new state
		//count the left most path, unique situation
		performAll(initialTactic,systemState);
		count++; //first time all success version of final state
		systemState.undoTactic();
		//currentNode = (GPNode) currentNode.parent;
		visited.add(systemState);
		systemState.performFailure(currentNode);
		//currentNode = (GPNode) currentNode.children[1];
		count++;
		undoUntilVisited(systemState);

		while(numVistedInitial < 2){
			systemState.performFailure(currentNode); 
			currentNode = currentNode.children[1];
			performAll(currentNode,systemState); 
			count++; //reached a success final state
			systemState.undoTactic();
			visited.add(systemState);
			systemState.performFailure(currentNode);
			count++;
			undoUntilVisited(systemState);
			if(systemState.equals(initialState)){
				numVistedInitial++;
			}
		}

		return count;
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


