package omnet;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import omnet.Omnet.Scenario;
import omnet.tactics.DecreaseDimmer;
import omnet.tactics.IncreaseDimmer;
import omnet.tactics.IncreaseTraffic;
import omnet.tactics.OmnetPlan;
import omnet.tactics.ShutdownServer;
import omnet.tactics.StartServer;
import system.Fitness;
import tactics.TryCatchFinallyTactic;

public class FitnessSanity {

	@Test
	public void shutdownServerPointerBug() {
		OmnetPlan plan = new OmnetPlan();

		TryCatchFinallyTactic tcf = new TryCatchFinallyTactic(new StartServer("C"), new OmnetPlan(Arrays.asList(new IncreaseTraffic("A"))), new OmnetPlan(Arrays.asList(new StartServer("B"),new StartServer("B"),new StartServer("B"),new StartServer("B"))));
		plan.getTactics().add(tcf);
		
		Fitness f = plan.evaluate(new Omnet(Scenario.normal));
		
		System.out.println(f.get("Profit"));
		assert f.get("Profit") == 1607.7200000000003;
	}
	
	
	@Test
	public void test() {
		OmnetPlan plan = new OmnetPlan();
		
		//TryCatchFinallyTactic tcf = new TryCatchFinallyTactic(new ShutdownServer("A"), new OmnetPlan(Arrays.asList(new ShutdownServer("A"),new StartServer("C"),new StartServer("C"),new StartServer("C"),new StartServer("C"),new StartServer("C"),new StartServer("B"),new StartServer("B"),new StartServer("B"),new StartServer("B"))),new OmnetPlan(Arrays.asList(new ShutdownServer("A"))));
		
		//plan.tactics.add(tcf);
		
		/*
		for (int count = 0; count < 3; count++)
			plan.tactics.add(new ShutdownServer("A"));
		

		
		for (int count = 0; count < 5; count++)
			plan.tactics.add(new StartServer("B"));
			
		for (int count = 0; count < 4; count++)
			plan.tactics.add(new StartServer("C"));
	*/
		
		for (int count = 0; count < 1; count++)
			plan.getTactics().add(new StartServer("C"));
		
		for (int count = 0; count < 1; count++)
			plan.getTactics().add(new StartServer("B"));
		
		for (int count = 0; count < 1; count++)
			plan.getTactics().add(new ShutdownServer("A"));
		
		
		
		Fitness f = plan.evaluate(new Omnet(Scenario.normal));
		
		System.out.println(f.get("Profit"));
		assert f.get("Profit") == 1607.7200000000003;
	}
	
	@Test
	public void trycatchtest() {
		OmnetPlan plan = new OmnetPlan();
		
		TryCatchFinallyTactic tcf = new TryCatchFinallyTactic(new ShutdownServer("A"), new OmnetPlan(Arrays.asList(new ShutdownServer("A"))), new OmnetPlan(Arrays.asList(new ShutdownServer("A"))));
		plan.getTactics().add(tcf);		
		/*
		for (int count = 0; count < 3; count++)
			plan.tactics.add(new ShutdownServer("A"));
		

		
		for (int count = 0; count < 5; count++)
			plan.tactics.add(new StartServer("B"));
			
		for (int count = 0; count < 4; count++)
			plan.tactics.add(new StartServer("C"));
	
		
		for (int count = 0; count < 1; count++)
			plan.getTactics().add(new StartServer("C"));
		
		for (int count = 0; count < 1; count++)
			plan.getTactics().add(new StartServer("B"));
		
		for (int count = 0; count < 1; count++)
			plan.getTactics().add(new ShutdownServer("A"));
		*/
		
		
		Fitness f = plan.evaluate(new Omnet(Scenario.normal));
		
		System.out.println(f.get("Profit"));
		assert f.get("Profit") == 1607.7200000000003;
	}
	
	@Test
	public void compTryCatch() {
		OmnetPlan plan = new OmnetPlan();
		OmnetPlan plan2 = new OmnetPlan();
		
		TryCatchFinallyTactic tcf = new TryCatchFinallyTactic(new StartServer("C"), new OmnetPlan(Arrays.asList(new StartServer("C"))), new OmnetPlan(Arrays.asList(new ShutdownServer("A"))));
		TryCatchFinallyTactic tcf2 = new TryCatchFinallyTactic(new StartServer("C"), new OmnetPlan(Arrays.asList(new ShutdownServer("A"))), new OmnetPlan(Arrays.asList(new ShutdownServer("A"))));

		plan.getTactics().add(tcf);		
		plan2.getTactics().add(tcf2);
		/*
		for (int count = 0; count < 3; count++)
			plan.tactics.add(new ShutdownServer("A"));
		

		
		for (int count = 0; count < 5; count++)
			plan.tactics.add(new StartServer("B"));
			
		for (int count = 0; count < 4; count++)
			plan.tactics.add(new StartServer("C"));
	
		
		for (int count = 0; count < 1; count++)
			plan.getTactics().add(new StartServer("C"));
		
		for (int count = 0; count < 1; count++)
			plan.getTactics().add(new StartServer("B"));
		
		for (int count = 0; count < 1; count++)
			plan.getTactics().add(new ShutdownServer("A"));
		*/
		
		
		Fitness f = plan.evaluate(new Omnet(Scenario.normal));
		
		Fitness f2 = plan2.evaluate(new Omnet(Scenario.normal));
		
		System.out.println(f.get("Profit"));
		System.out.println(f2.get("Profit"));
		
		assert f.get("Profit") > f2.get("Profit");
	}

}
