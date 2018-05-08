package ecj;

import java.io.Serializable;

import ec.gp.GPNode;

public interface JavaGenerator extends Serializable {
	
	public JavaRep generateJava(JavaRep java);

}
