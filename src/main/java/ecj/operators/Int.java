package ecj.operators;

import ec.gp.*;

public class Int extends GPData {
	
	int value;
	
	public void copyTo(final GPData data){
		Int dataInt = (Int) data;
		dataInt.value = value;
	}

}
