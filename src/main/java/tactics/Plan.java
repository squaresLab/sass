package tactics;

import java.util.ArrayList;

public abstract class Plan implements Cloneable {
	
	protected ArrayList<Tactic> tactics;
	
	public Plan(){
		tactics = new ArrayList<Tactic>();
	}
	
	public ArrayList<Tactic> getTactics(){
		return tactics;
	}
	
}
