package ecj.actions;

import omnet.tactics.ShutdownServer;

public class ShutdownServerNode extends LabeledTactic {
	
	public ShutdownServerNode(){ 
		super(ShutdownServer.class);
	}

}