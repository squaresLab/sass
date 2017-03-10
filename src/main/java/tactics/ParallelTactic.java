package tactics;

import java.util.ArrayList;

import system.Fitness;
import system.SystemState;

public class ParallelTactic extends Tactic {
	
	private Plan p;
	
	public ParallelTactic(Plan p){
		this.p = p;
	}

	@Override
	public void visit(SystemState systemState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void undo(SystemState systemState) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public long getExecutionTime() {
		long max = 0;
		for (int count = 0; count < p.size(); count++){
			if(p.tactics.get(count).getExecutionTime() > max){
				max = p.tactics.get(count).getExecutionTime();
			}
		}
		return max;
	}

	@Override
	public int size() {
		return p.size();
	}
	
	public ArrayList<Tactic> getTactics(){
		return p.getTactics();
	}
	
	public Object clone() {
		ParallelTactic ans = new ParallelTactic((Plan)p.clone());
		return ans;
	}


}
