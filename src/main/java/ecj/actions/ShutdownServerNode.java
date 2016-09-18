package ecj.actions;

import generalomnet.tactics.ShutdownServer;

public class ShutdownServerNode extends LabeledTactic {
	
	public ShutdownServerNode(){ 
		super(ShutdownServer.class);
	}

}