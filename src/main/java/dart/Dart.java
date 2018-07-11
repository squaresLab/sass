package dart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import system.Fitness;
import system.SystemState;

public class Dart extends SystemState {
	
	static final String FITNESS_CMD_STRING = "./runDartFitness";

	@Override
	public boolean isStateValid() {
		// the outside simulator will manage everything
		return true;
	}

	@Override
	public Fitness calculateInstFitness() {
		
		// call the dart fitness simulator for fitness calculation
		Runtime rt = Runtime.getRuntime();
		String [] args = {};
		String cmd = FITNESS_CMD_STRING;
		for (int i = 0; i < args.length; i++) {
			cmd += " " + args[i];
		}
		
		String s = "";
		
		try {
			Process pr = rt.exec(cmd);
			BufferedReader stdInput = new BufferedReader(new 
				     InputStreamReader(pr.getInputStream()));
			
			while ((s = stdInput.readLine()) != null) {
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Fitness ans = new Fitness();
		
		ans.put("Profit", (double) Integer.parseInt(s));
		
		return ans;
	}

}
