package tactics;

import system.SystemState;

public class ParallelTactic extends FailableTactic {
	
	private FailableTactic a,b;
	
	public ParallelTactic(FailableTactic a, FailableTactic b){
		this.a = a;
		this.b = b;
	}

	@Override
	public void visit(SystemState systemState) {
		a.visit(systemState);
		b.visit(systemState);
		
		if(a.getFailed() || b.getFailed()){
			setFailed(true);
		}
	}

	@Override
	public void undo(SystemState systemState) {
		b.undo(systemState);
		a.undo(systemState);
	}

	// return the chance either fails
	// i.e., the chance that both do not succeed
	@Override
	public double getFailChance() {
		return 1 - (1-a.getFailChance() * 1-b.getFailChance());
	}
	
	@Override
	public int size() {
		return a.size() + b.size();
	}

	@Override
	public long getTime() {
		return Math.max(a.getTime(),b.getTime());
	}
	
}
