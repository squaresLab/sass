package ecj;

import java.lang.reflect.InvocationTargetException;

import ec.gp.GPData;
import tactics.FailableTactic;

public class TacticFactory<T extends FailableTactic> extends GPData {
	
	private Class<T> tactic;
	
	private String target;
	
	public TacticFactory(Class<T> tactic){
		this.tactic = tactic;
	}
	
	public void setTarget(String s){
		this.target = s;
	}
	
	public FailableTactic getLabledTacticInstance(){
		try {
			return tactic.getDeclaredConstructor(String.class).newInstance(target);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException
				| NoSuchMethodException | SecurityException e) {
			e.printStackTrace();
			throw new IllegalArgumentException();
		}
	}

}
