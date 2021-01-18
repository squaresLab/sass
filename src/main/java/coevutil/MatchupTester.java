package coevutil;

import java.io.File;
import java.io.IOException;
import java.io.LineNumberReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;

import ec.EvolutionState;
import ec.Evolve;
import ec.Fitness;
import ec.Individual;
import ec.gp.GPIndividual;
import ec.gp.GPSpecies;
import ec.util.Code;
import ec.util.Output;
import ec.util.Parameter;
import ec.util.ParameterDatabase;
import ecj.BullseyeProblem;

import bullseye.actions.*;

public class MatchupTester {
	
	public static void main(String[] args) {
		
		File parameterFile = new File( System.getProperty("user.dir")+"/bullseye.gp.params");

		ParameterDatabase dbase;
		
		EvolutionState state = null;
		
		try {
			dbase = new ParameterDatabase(parameterFile,new String[] {"-file",parameterFile.getCanonicalPath()});
			
//			dbase.setProperty("gp.tc.0.init","ec.gp.koza.HalfBuilder");
			
			Output out = Evolve.buildOutput();

			//disable output
			out.getLog(0).silent = false;
			out.getLog(1).silent = false;
			
			state = Evolve.initialize(dbase,0,out);
			
			Object o = new bullseye.actions.ExploitWeb();
			
			state.startFresh();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		BullseyeProblem problem = new BullseyeProblem();
		
		Individual[] ind = new Individual[2];
		
//		ind[0] = loadInd(state,"(; ChangePword FlashServers)",0);
		
		String gambit2timestepdef = "(R ERC["+Code.encode(0.0186435554)+"] (; FlashServers (R ERC["+Code.encode(0.4864864865)+"] ChangePword FlashServers)) (; Throttle (R ERC["+Code.encode(0.4736842105)+"] ChangePword FlashServers)))";
		
		String gambit2timestepattacker = "(R ERC["+Code.encode(0.3456112063)+"] (; PhishVendor (I isWebHacked ExploitPay ExploitWeb)) (; ExploitWeb (I isWebHacked PhishVendor (R ERC["+Code.encode(0.5135135135)+"] PhishVendor ExploitWeb))))";
		
		String erctest = "(R ERC[d4599261273224094242|0.31028809001012025|] FlashServers ChangePword)";
		
//		System.out.println(Code.encode(.019));
		
		ind[0] = loadInd(state, gambit2timestepdef,0);
		ind[1] = loadInd(state, gambit2timestepattacker,1);
//		
//		ind[0] = loadInd(state, "(; ChangePword (; FlashServers Wait))",0);
//		ind[1] = loadInd(state,"(; PhishVendor (; ExploitWeb ExploitWeb))",1);
		
		ind[0].fitness.trials = new ArrayList<Fitness>();
		ind[1].fitness.trials = new ArrayList<Fitness>();
		
		problem.evaluate(state, ind, new boolean[] {true,true}, true, new int[] {}, 0);
		
		problem.finishEvaluating(state, 0);
		
		StringWriter sw = new StringWriter();
		
		PrintWriter pw = new PrintWriter(sw);
		
		ind[1].printIndividual(state, pw);
		
		System.out.println(ind[0].fitness.trials);
		System.out.println(ind[1].fitness.trials);
		System.out.println(sw);
		
	}
	
	public static String indToString(Individual ind, EvolutionState state) {
		
		StringWriter sw = new StringWriter();
		
		PrintWriter pw = new PrintWriter(sw);
		
		ind.printIndividual(state, pw);
		
		return sw.toString();
		
	}
	
	  public static GPIndividual loadInd(EvolutionState state, String indString, int subpop) {
		  
		  String startString = 
					"Evaluated: F\n"+
					"Fitness: d0|0.0|\n"+
					"Tree 0:\n";
		  
		  
		  // for multiobjective case
		  /*
		 String indString = 
				  "Evaluated: F\n"+
				  "Fitness: [d0|0.0| d0|0.0| d0|0.0| d0|0.0|]\n"+
			      "Fitness: d4567423964645623803|0.0022887690524564497|\n"+
				  "Strength: d0|0.0|\n"+
				  "Distance: d4567423964645623803|0.0022887690524564497|\n"+
				  "Tree 0:\n";
		  */
		  
		 // if (mtf.nextBoolean(0.90))
			 //indString += "(; (IncreaseTraffic A) (; (DecreaseDimmer B) (; (DecreaseDimmer B) (DecreaseDimmer B))))";
		//  else 
		//	 indString += "(; (; (T (StartServer B) (T (StartServer B) (T (StartServer B) (T (StartServer B) (StartServer B) (ShutdownServer A)) (ShutdownServer A)) (StartServer C)) (; (StartServer C) (; (StartServer C) (ShutdownServer A)))) (StartServer C)) (; (F ERC[i4|] (; (StartServer C) (ShutdownServer A))) (; (T (StartServer B) (T (StartServer B) (T (StartServer B) (T (StartServer B) (StartServer B) (ShutdownServer A)) (ShutdownServer A)) (; (StartServer C) (ShutdownServer A))) (; (StartServer C) (; (; (StartServer C) (ShutdownServer A)) (ShutdownServer A)))) (StartServe)r C))))";
			
		//indString += "(; (; (; (StartServer C) (; (StartServer C) (; (; (F ERC[i4|] (ShutdownServer A)) (; (StartServer B) (F ERC[i2|] (StartServer B)))) (F ERC[i4|] (StartServer C))))) (StartServer B)) (StartServer B))";

		  indString = startString + indString;
			 
		//	indString = readPlanFromFile("prismproc.txt");
			
			LineNumberReader lnr = new LineNumberReader(new StringReader(indString));
			
			GPSpecies species = new GPSpecies();
			
			species.setup(state, new Parameter("pop.subpop."+subpop+".species"));
			
			GPIndividual ind = null;
			
			try {
				ind = (GPIndividual) species.newIndividual(state, lnr);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		//	ind.printIndividualForHumans(state, 0);
			
			
		return ind;
	}

}
