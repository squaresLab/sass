package ecj;

import java.io.Serializable;
import java.util.HashMap;

import ec.gp.GPNode;

public interface JavaGenerator extends Serializable {
	
	public JavaRep generateJava(JavaRep java);
	public HashMap<String,Integer> generateVector();

}
