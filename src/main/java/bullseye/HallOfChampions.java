package bullseye;

import java.util.Random;

import ec.Individual;

public class HallOfChampions {
	
	Individual[] inds;
	
	int size;
	
	double[] fitnessSum;
	
	int[] fitnessEvals;
	
	int pointer;
	
	int filled;
	
	Random rand;
	
	public HallOfChampions(int size) {
		
		rand = new Random();
		
		this.size = size;
		
		inds = new Individual[size];
		fitnessSum = new double[size];
		fitnessEvals = new int[size];
		
		pointer = 0;
		
		filled = 0;
		
	}
	
	public void add(Individual ind) {
		if (pointer >= inds.length) {
			pointer = 0;
		}
		
		inds[pointer++] = ind;
		
		filled = Math.max(filled, pointer);

	}
	
	public Individual getRandInd() {
		
		int index = rand.nextInt(filled);
		
		return inds[index];
		
	}
	

}
