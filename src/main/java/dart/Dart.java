package dart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import system.Fitness;
import system.SystemState;

public class Dart extends SystemState {
	static final String PLA_DIR = "/home/cody/taas/pladapt";
//	static final String FITNESS_CMD_STRING = "LD_LIBRARY_PATH=${LD_LIBRARY_PATH}:"+PLA_DIR+"/examples/dart/dartam/build/src/.libs "+PLA_DIR+"/examples/dart/dartfitness/dartfitness";
//  static final String FITNESS_CMD_STRING = "/home/ckinneer/research/pladapt/examples/dart/dartfitness/test.sh";
    static final String FITNESS_CMD_STRING = "/home/cody/taas/pladapt/examples/dart/dartfitness/run.sh";
//	static final String FITNESS_CMD_STRING = "echo \"test\"";


	@Override
	public boolean isStateValid() {
		// the outside simulator will manage everything
		return true;
	}

	public Fitness calculateInstFitness(String plan) {
		
		// call the dart fitness simulator for fitness calculation
		Runtime rt = Runtime.getRuntime();
		String cmd = FITNESS_CMD_STRING;
		cmd += " --plan " + plan.trim().replace(' ', '_') + "  --lookahead-horizon 20 --ecm --altitude-levels 4 --map-size 40 --two-level-tactics --num-targets 20 --num-threats 10 --adapt-mgr sass";
		String s = "";
		//System.out.println(cmd);
		try {
			Process pr = rt.exec(cmd);
			
			BufferedReader stdInput = new BufferedReader(new 
				     InputStreamReader(pr.getInputStream()));
			
			String lastline = "";
			while ((s = stdInput.readLine()) != null) {
				//System.out.println(s);
				lastline = s;
			}
			s = lastline;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		if (s.equals("impossible")) {
			System.out.println("impossible");
		}
		
		Fitness ans = new Fitness();
		s = s.split(",")[s.split(",").length-1];
		
		double fitness = 0;
		
		try {
			fitness = Double.parseDouble(s);
		}catch(java.lang.NumberFormatException e) {
			fitness = 0;
		}
		
		ans.put("Profit", fitness);
		
		return ans;
	}

	@Override
	public Fitness calculateInstFitness() {
		// TODO Auto-generated method stub
		return null;
	}

}
