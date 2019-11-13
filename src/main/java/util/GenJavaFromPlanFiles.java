package util;

import java.io.*;

import ec.EvolutionState;
import ec.Evolve;
import ec.gp.GPIndividual;
import ec.gp.GPNode;
import ec.gp.GPSpecies;
import ec.util.Output;
import ec.util.Parameter;
import ec.util.ParameterDatabase;
import ecj.JavaGenerator;
import ecj.JavaRep;
import ecj.RepertoireBuilder;
import omnet.Scenario;

public class GenJavaFromPlanFiles {
	
	public EvolutionState state;
	
	public GenJavaFromPlanFiles() {
		
		RepertoireBuilder.scenario = new Scenario();
		
		File parameterFile = new File( System.getProperty("user.dir")+"/selfadaptivesystemsingleobjective.params");

		ParameterDatabase dbase;
		try {
			dbase = new ParameterDatabase(parameterFile,new String[] {"-file",parameterFile.getCanonicalPath()});
			
			dbase.setProperty("gp.tc.0.init","ec.gp.koza.HalfBuilder");
			
			Output out = Evolve.buildOutput();

			//disable output
			out.getLog(0).silent = true;
			out.getLog(1).silent = true;
			
			state = Evolve.initialize(dbase,0,out);
			
			state.startFresh();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) throws FileNotFoundException, IOException {
		
		GenJavaFromPlanFiles gen = new GenJavaFromPlanFiles();
		
		File folder = new File("repertoireBuilder25Gen");
		File[] files = folder.listFiles();
		
		folder = new File("objectgen");
		for (File f : folder.listFiles()) {
			f.delete();
		}
		
		folder = new File("javagen");
		for (File f : folder.listFiles()) {
			f.delete();
		}
		
		folder = new File("vectorgen");
		for (File f : folder.listFiles()) {
			f.delete();
		}
		
		for (File f : files) {
			//System.out.println(f);
			
			//System.out.println(gen.readPlanFromFile(f));
			JavaGenerator jg = (JavaGenerator) gen.readPlanFromFile(f);
			
			String planName = f.getName().split(",")[0];
			
			String planNumber = planName.replace("Plan", "");
			
			JavaRep javaRep = new JavaRep();
			javaRep.addLine("public class Plan"+planNumber+" extends Plan { ", null);
			javaRep.addLine("public static void main(String[] args) { ", null);
			jg.generateJava(javaRep);
			javaRep.newLine();
			javaRep.addLine("}", null);
			javaRep.addLine("}", null);
			
			//System.out.println(javaRep);	
			
			String fileName = "javagen/"+planName + ".java";
			FileWriter fileWriter = new FileWriter(fileName);
		    PrintWriter printWriter = new PrintWriter(fileWriter);
		    printWriter.print(javaRep.toString());
		    printWriter.close();
		    
		    fileName = "objectgen/"+planName+".ser";
		    FileOutputStream fos = new FileOutputStream(new File(fileName));
			ObjectOutputStream oos = new ObjectOutputStream(fos);

			// Write objects to file
			oos.writeObject(javaRep);

			oos.close();
			fos.close();
			
			// write out the charactaristic vectors to file
			fileName = "vectorgen/"+planName+".vdb";
			fileWriter = new FileWriter(fileName);
		    printWriter = new PrintWriter(fileWriter);
		    
		    printWriter.print(javaRep.getVectorString(fileName));
		    printWriter.close();
			
		}
	}

	private GPNode readPlanFromFile(File file) {
		try(BufferedReader br = new BufferedReader(new FileReader(file))) {
		    StringBuilder sb = new StringBuilder();
		    String indString = br.readLine();
		    indString = "Evaluated: F\n"+
			"Fitness: d4554802393809453312|3.3430128051926966E-4|i0|\n"+
			"Tree 0:\n" + indString;
		    
			LineNumberReader lnr = new LineNumberReader(new StringReader(indString));
			
			GPSpecies species = null;
			
			if (species == null) {
				species = new GPSpecies();
				species.setup(state, new Parameter("pop.subpop.0.species"));
			}
			
			GPIndividual ind = null;
			
			try {
				ind = (GPIndividual) species.newIndividual(state, lnr);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		//	ind.printIndividualForHumans(state, 0);
			
			
		return ind.trees[0].child;
		
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
}
