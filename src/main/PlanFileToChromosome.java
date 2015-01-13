package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.GPFitnessFunction;
import org.jgap.gp.IGPFitnessEvaluator;
import org.jgap.gp.function.SubProgram;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.impl.ProgramChromosome;

import actions.Actions;
import actions.AddServerL1;
import actions.AddServerL2;
import actions.IncreaseDatabaseAThreads;
import actions.IncreaseDatabaseBThreads;
import actions.ReduceTextResolution;

public class PlanFileToChromosome {

	public static ProgramChromosome planFileToChromosome(String filename,
			GPConfiguration gpConf) {
		Charset charset = Charset.forName("US-ASCII");
		try (BufferedReader reader = Files.newBufferedReader(Paths.get(filename),
				charset)) {
			String line = null;
			ArrayList<String> actionStrings = new ArrayList<String>();
			while ((line = reader.readLine()) != null) {
				line = line.trim();
				String[] lineItems = line.split(" ");
				if (lineItems[0].matches("[0-9.]*:")) {
					int startIndex = 1;
					String action = "";
					while (action.equals("")) {
						// shouldn't always get an action before startIndex gets too large
						action = lineItems[startIndex];
						startIndex++;
					}
					if (action.startsWith("(")) {
						// remove starting parenthesis
						action = action.substring(1);
					}
					if (action.endsWith(")")) {
						// removing ending parenthesis
						action = action.substring(0, action.length() - 1);
					}
					actionStrings.add(action);
				}
			}
			// currently if statements are not handled in plans
			int numberOfSubs = actionStrings.size() - 1;
			int planSize = numberOfSubs + actionStrings.size();
			CommandGene[] commands = new CommandGene[planSize];
			int[] depths = new int[planSize];
			int currentIndex = 0;
			for (int i = 0; i < numberOfSubs; i++) {
				commands[currentIndex] = new SubProgram(gpConf, new Class[] {
						CommandGene.VoidClass, CommandGene.VoidClass }, true);
				depths[currentIndex] = i;
				currentIndex++;
			}
			// each sub has two children and the first one is another sub except for
			// the lowest sub
			int currentDepth = numberOfSubs;
			commands[currentIndex] = getActionFromFileString(actionStrings.get(0),
					gpConf);
			depths[currentIndex] = currentDepth;
			currentIndex++;
			int actionStringIndex = 1;
			for (int i = currentDepth; i > 0; i--) {
				commands[currentIndex] = getActionFromFileString(
						actionStrings.get(actionStringIndex), gpConf);
				depths[currentIndex] = i;
				currentIndex++;
				actionStringIndex++;
			}
			ProgramChromosome chromosome = new ProgramChromosome(gpConf, 12);
			chromosome.setFunctions(commands);

			Field depthArray = chromosome.getClass().getDeclaredField("m_depth");
			depthArray.setAccessible(true);
			depthArray.set(chromosome, depths);
			System.out.println("command list");
			for (int i = 0; i < commands.length; i++) {
				System.out.print(commands[i].toString() + ", ");
			}
			System.out.print("\n");
			System.out.println("depth list");
			for (int i = 0; i < depths.length; i++) {
				System.out.print(depths[i] + ", ");
			}
			System.out.print("\n");
			return chromosome;
		} catch (IOException x) {
			System.err.format("IOException: %s%n", x);
			System.exit(1);
		} catch (Exception e) {
			e.printStackTrace();
			System.exit(1);
		}

		return null; // added until the real return is implemented
	}

	public static CommandGene getActionFromFileString(String actionString,
			GPConfiguration gpConf) {
		try {
			if (actionString.equals("INCREASE-DATABASE-A-THREADS")) {
				return new IncreaseDatabaseAThreads(gpConf);
			} else if (actionString.equals("INCREASE-DATABASE-B-THREADS")) {
				return new IncreaseDatabaseBThreads(gpConf);
			} else if (actionString.equals("ADD-SERVER-FROM-L1")) {
				return new AddServerL1(gpConf);
			} else if (actionString.equals("ADD-SERVER-FROM-L2")) {
				return new AddServerL2(gpConf);
			} else if (actionString.equals("REDUCE-TEXT-RESOLUTION")) {
				return new ReduceTextResolution(gpConf);
			} else {
				System.err.println("Unimplemented action string: " + actionString);
				(new Exception()).printStackTrace();
				System.exit(1);
			}
		} catch (InvalidConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1);
		}
		return null; // this shouldn't be reachable
	}

	public static void main(String[] args) {
		// used to test the planFileToChromosomeMethod
		GPConfiguration gpConf = null;
		try {
			gpConf = new GPConfiguration();
		} catch (InvalidConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1); // lazy error handling
		}
		IGPFitnessEvaluator prismEvaluationFunction = new EvaluateFitness();
		gpConf.setGPFitnessEvaluator(prismEvaluationFunction);
		gpConf.setMaxInitDepth(2);
		try {
			gpConf.setPopulationSize(50);
		} catch (InvalidConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		gpConf.setCrossoverProb(0.9f);
		gpConf.setReproductionProb(0.1f);
		gpConf.setNewChromsPercent(0.3f);
		gpConf.setStrictProgramCreation(true);
		gpConf.setUseProgramCache(true);
		gpConf.setNoCommandGeneCloning(false);
		try {
			gpConf.setFitnessFunction((GPFitnessFunction) prismEvaluationFunction);
		} catch (InvalidConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<String> fileList = new ArrayList<String>();
		fileList
				.add("/home/zack/Documents/SoftwareModels/Project/SamplePlans/1/plan_problem.pddl_2.SOL");
		fileList
				.add("/home/zack/Documents/SoftwareModels/Project/SamplePlans/2/plan_problem1.pddl_2.SOL");
		for (int i = 0; i < fileList.size(); i++) {
			testFileAndPrint(fileList.get(i), gpConf);
		}
	}

	public static void testFileAndPrint(String file, GPConfiguration gpConf) {
		ProgramChromosome chromosome = planFileToChromosome(file, gpConf);
		if (chromosome == null) {
			System.out.println("error!");
			(new Exception()).printStackTrace();
		} else {
			System.out.println("plan:" + chromosome.toStringNorm(0));
		}
	}
}
