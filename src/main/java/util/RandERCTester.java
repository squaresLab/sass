package util;

import java.util.Random;

public class RandERCTester {

	public static void main(String[] args) {
		Random rand = new Random();
		
		double start = rand.nextDouble();
		double value = start;
		
		int trials = 10;
		
		System.out.println(value);
		
		for (int trial = 0; trial < trials; trial++) {
			double v;
			do {
				double nextGaus = rand.nextGaussian()*.01;
				v = value + nextGaus;
//				state.random[thread].n
			}while(v < 0 || v > 1.0);
			
			value = v;
			
			System.out.println(value);
		}
		
	}
	
}
