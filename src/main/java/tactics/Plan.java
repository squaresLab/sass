package tactics;

import java.util.ArrayList;
import java.util.List;

public abstract class Plan implements Cloneable {
	
	protected ArrayList<Tactic> tactics;
	
	public Plan(){
		tactics = new ArrayList<Tactic>();
	}
	
	public Plan(List<Tactic> tactics){
		this.tactics = new ArrayList<Tactic>(tactics);
	}
	
	public ArrayList<Tactic> getTactics(){
		return tactics;
	}
	
	public int size(){
		int ans = 0;
		for (int count = 0; count < tactics.size(); count++){
			ans += tactics.get(count).size();
		}
		return ans;
	}
	
	public double getTime(){
		int ans = 0;
		for (int count = 0; count < tactics.size(); count++){
			ans += tactics.get(count).getTime();
		}
		return ans;
	}
	
}
