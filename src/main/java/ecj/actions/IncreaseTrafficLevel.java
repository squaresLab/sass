package ecj.actions;

import ecj.JavaRep;
import omnet.tactics.IncreaseTraffic;

public class IncreaseTrafficLevel extends LabeledTactic {
	
	public IncreaseTrafficLevel(){ 
		super(IncreaseTraffic.class);
	}
	
	public JavaRep generateJava(JavaRep java) {
		java.appendLine("", this);
		return java;
	}

}