package dart;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import system.Fitness;
import system.SystemState;

public class Dart extends SystemState {
	static final String PLA_DIR = "/home/ckinneer/research/pladapt";
//	static final String FITNESS_CMD_STRING = "LD_LIBRARY_PATH=${LD_LIBRARY_PATH}:"+PLA_DIR+"/examples/dart/dartam/build/src/.libs "+PLA_DIR+"/examples/dart/dartfitness/dartfitness";
//  static final String FITNESS_CMD_STRING = "/home/ckinneer/research/pladapt/examples/dart/dartfitness/test.sh";
    static final String FITNESS_CMD_STRING = "/home/ckinneer/research/pladapt/examples/dart/dartfitness/run.sh";
//	static final String FITNESS_CMD_STRING = "echo \"test\"";


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
			String lastline = "";
			while ((s = stdInput.readLine()) != null) {
				lastline = s;
			}
			s = lastline;
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		Fitness ans = new Fitness();
		s = s.split(",")[s.split(",").length-1];
		ans.put("Profit", Double.parseDouble(s));
		
		return ans;
	}

}
