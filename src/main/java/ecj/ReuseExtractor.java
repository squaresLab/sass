package ecj;

import omnet.tactics.DecreaseDimmer;
import omnet.tactics.DecreaseTraffic;
import omnet.tactics.IncreaseDimmer;
import omnet.tactics.IncreaseTraffic;
import omnet.tactics.OmnetPlan;
import omnet.tactics.ShutdownServer;
import omnet.tactics.StartServer;
import tactics.Tactic;
import tactics.TryCatchFinallyTactic;

public class ReuseExtractor {
	
	public static String planToSource(OmnetPlan plan) {
		String javaCode = "";
		
		for (Tactic t: plan.getTactics()) {
			javaCode += tacticToSource(t);
		}
		
		return javaCode;
	}

	private static String tacticToSource(TryCatchFinallyTactic t) {
		String javaCode = "if ( ";
		javaCode += tacticToSource(t.getTry());
		javaCode += " ) {\n";
		javaCode += planToSource((OmnetPlan) t.getFinally());
		javaCode += "\n} else {\n";
		javaCode += planToSource((OmnetPlan) t.getCatch());
		javaCode += "\n}";
		return javaCode;
	}
	
	private static String tacticToSource(DecreaseDimmer t) {
		return "DecreaseDimmer(" + t.getServer() + ");";
	}
	
	private static String tacticToSource(IncreaseDimmer t) {
		return "IncreaseDimmer(" + t.getServer() + ");";
	}
	
	private static String tacticToSource(IncreaseTraffic t) {
		return "IncreaseTraffic(" + t.getServer() + ");";
	}
	
	private static String tacticToSource(DecreaseTraffic t) {
		return "DecreaseTraffic(" + t.getServer() + ");";
	}
	
	private static String tacticToSource(StartServer t) {
		return "StartServer(" + t.getServer() + ");";
	}
	
	private static String tacticToSource(ShutdownServer t) {
		return "ShutdownServer(" + t.getServer() + ");";
	}

}
