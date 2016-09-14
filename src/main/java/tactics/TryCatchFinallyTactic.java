package tactics;

import java.util.ArrayList;

import generalomnet.Omnet;
import generalomnet.tactics.OmnetPlan;
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
	}
	
	@Override
	public void undo(SystemState systemState) {
		inTry.undo(systemState);
		
	}
	@Override
	public double getFailChance() {
		return inTry.getFailChance();
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

}
