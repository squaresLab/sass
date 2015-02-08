package main;

import java.util.ArrayList;
import java.util.Random;

import org.jgap.InvalidConfigurationException;
import org.jgap.gp.CommandGene;
import org.jgap.gp.IGPProgram;
import org.jgap.gp.impl.GPConfiguration;
import org.jgap.gp.impl.ProgramChromosome;

import actions.Actions;

public class SelfAdaptivePlanMutation {

	public static ProgramChromosome performMutation(GPConfiguration gConf,
			ProgramChromosome chromosome, int selectedGenePosition) {
		IGPProgram ind = chromosome.getIndividual();

		CommandGene[][] possibleCommands = RunGA.nodeSets;
		Random rand;
		CommandGene result = null;
		rand = new Random();
		int choice = rand.nextInt(possibleCommands[0].length);
		result = possibleCommands[0][choice];
		if (result.getArity(ind) == chromosome.getGene(selectedGenePosition)
				.getArity(ind)) {
			chromosome.setGene(selectedGenePosition, result);
			return chromosome;
		} else if (result.getArity(ind) > chromosome.getGene(selectedGenePosition)
				.getArity(ind)) {
			CommandGene[] genesToInsert = recursivelyMakeNewBranch(ind, rand,
					new ArrayList<CommandGene>(), result, 0);
			CommandGene[] genesOfNewChromosome = insertCommandGeneArray(
					chromosome.getFunctions(), selectedGenePosition, genesToInsert);
			ProgramChromosome newChromosome = generateNewChromosome(gConf,
					chromosome, genesOfNewChromosome);
			return newChromosome;
		} else { // result's children are less than the original node
			// number of nodes in the branch to be removed
			int nodesToRemove = chromosome.getSize(selectedGenePosition);
			CommandGene[] newGenes = removeBranchAndReplaceWithSingleGene(
					chromosome.getFunctions(), selectedGenePosition, result,
					nodesToRemove);
			ProgramChromosome newChromosome = generateNewChromosome(gConf,
					chromosome, newGenes);
			return newChromosome;

		}
	}

	private static ProgramChromosome generateNewChromosome(GPConfiguration gConf,
			ProgramChromosome oldChromosome, CommandGene[] newGenes) {
		ProgramChromosome newChromosome = null;
		try {
			newChromosome = new ProgramChromosome(gConf, newGenes.length,
					oldChromosome.getFunctionSet(), oldChromosome.getArgTypes(),
					oldChromosome.getIndividual());
			newChromosome.setFunctions(newGenes);
			newChromosome.redepth();

		} catch (InvalidConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.exit(1); // lazy error handling but not sure how to handle the
											// error at the moment, I'll implement something if it
											// happens
		}
		return newChromosome;
	}

	private static CommandGene[] recursivelyMakeNewBranch(IGPProgram ind,
			Random rand, ArrayList<CommandGene> branchToBuild,
			CommandGene currentGene, int depth) {
		int numberOfChildren = currentGene.getArity(ind);
		branchToBuild.add(currentGene);
		if (numberOfChildren != 0) {
			for (int i = 0; i < numberOfChildren; i++) {
				CommandGene[][] possibleCommands = RunGA.nodeSets;
				int choice = rand.nextInt(possibleCommands[0].length);
				CommandGene newResult = possibleCommands[0][choice];
				if (depth > 4) { // limiting the branch size if we continually get genes
													// with children for some reason.
					while (newResult.getArity(ind) != 0) {
						choice = rand.nextInt(possibleCommands[0].length);
						newResult = possibleCommands[0][choice];
					}
					recursivelyMakeNewBranch(ind, rand, branchToBuild, newResult,
							depth + 1);
				}
			}
		}
		return branchToBuild.toArray(new CommandGene[branchToBuild.size()]);
	}

	private static CommandGene[] insertCommandGeneArray(
			CommandGene[] originalArray, int positionToInsert,
			CommandGene[] arrayToInsert) {
		int newArraySize = originalArray.length + arrayToInsert.length - 1;
		CommandGene[] resultArray = new CommandGene[newArraySize];
		for (int i = 0; i < positionToInsert; i++) {
			resultArray[i] = originalArray[i];
		}
		for (int i = 0; i < arrayToInsert.length; i++) {
			resultArray[positionToInsert + i] = arrayToInsert[i];
		}
		// skipping the gene we are replacing
		for (int i = positionToInsert + 1; i < originalArray.length; i++) {
			resultArray[arrayToInsert.length + i] = originalArray[i];
		}
		return resultArray;
	}

	private static CommandGene[] removeBranchAndReplaceWithSingleGene(
			CommandGene[] originalArray, int positionToInsert,
			CommandGene commandToInsert, int sizeToRemove) {
		CommandGene[] result = new CommandGene[originalArray.length + 1
				- sizeToRemove];
		for (int i = 0; i < positionToInsert; i++) {
			result[i] = originalArray[i];
		}
		result[positionToInsert] = commandToInsert;
		for (int i = positionToInsert; i < result.length; i++) {
			result[i] = originalArray[i + sizeToRemove];
		}
		return result;
	}
}
