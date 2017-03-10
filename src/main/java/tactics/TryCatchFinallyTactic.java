package tactics;

import system.SystemState;

public class TryCatchFinallyTactic extends FailableTactic {

	FailableTactic inTry;
	
	Plan inCatch;
	Plan inFinally;
	
	
	public TryCatchFinallyTactic(FailableTactic inTry, Plan inCatch, Plan inFinally){
		this.inTry = inTry;
		this.inCatch = inCatch;
		this.inFinally = inFinally;
	}
	
	@Override
	public void visit(SystemState systemState) {
		inTry.visit(systemState);
		if (inTry.getFailed())
			setFailed(true);
			
	}
	
	@Override
	public void undo(SystemState systemState) {
		inTry.undo(systemState);
		
	}
	
	public Plan getCatch(){
		return inCatch;
	}
	
	public Plan getFinally(){
		return inFinally;
	}
	
	public String toString(){
		return "T("+inTry+") catch ("+inCatch+")("+inFinally+")";
	}

	@Override
	public int size() {
		return inTry.size() + Math.max(inCatch.size(),inFinally.size());
	}

	@Override
	public long getExecutionTime() {
		return inTry.getExecutionTime();
	}
	
	public Object clone(){
		
		TryCatchFinallyTactic ans = new TryCatchFinallyTactic((FailableTactic) inTry.clone(), (Plan) inCatch.clone(),(Plan) inFinally.clone());
		
		return ans;
	}

	@Override
	public double getFailChance() {
		return inTry.getFailChance();
	}

}
