package ecj;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Result {
	List<Long> runtimes;
	List<Double> fitnesses;
	
	public Result() {
		runtimes = new ArrayList<Long>();
		fitnesses = new ArrayList<Double>();
	}
	
	public void add(long runtime, double fitness) {
		runtimes.add(runtime);
		fitnesses.add(fitness);
	}
	
	public double findArea() {
		// special case for first datapoint
		double ans = 0.5*runtimes.get(0)*fitnesses.get(0);
		// find area under trapeziod for other cases
		for (int i = 1; i < runtimes.size(); i++) {
			double b1 = fitnesses.get(i-1);
			double b2 = fitnesses.get(i);
			double h = runtimes.get(i);
			ans += 0.5*(b1+b2)*h; // formula for area under a trapeziod
		}
		return ans;
	}
	
	// find the amount of time that this result is better than the other result
	public double findTimeDominates(Result other) {
		
		double ans = 0;
		
		int usIndex = 0;
		int themIndex = 0;
		
		double usTime = 0;
		double themTime = 0;
		
		double usNextTime = 0;
		double themNextTime = 0;
		
		double usFitness = Double.NEGATIVE_INFINITY;
		double themFitness = Double.NEGATIVE_INFINITY;
		
		double clock = 0;
		double prevClock = clock;
		
		while(usTime != Double.POSITIVE_INFINITY || themTime != Double.POSITIVE_INFINITY){
			
			// pull the lowest time from the stack
			usNextTime = usTime + runtimes.get(usIndex);
			themNextTime = themTime + other.runtimes.get(themIndex);
			
			prevClock = clock;
			clock = Math.min(usNextTime, themNextTime);
			
			if (usFitness > themFitness) {
				ans += clock - prevClock;
			}
			
			if (usNextTime < themNextTime) {
				usFitness = fitnesses.get(usIndex);
				usTime = usNextTime;
				if(usIndex == fitnesses.size()-1) {
					usTime = Double.POSITIVE_INFINITY;
				}else{
					usIndex++;
				}
			}else {
				themFitness = other.fitnesses.get(themIndex);
				themTime = themNextTime;
				if(themIndex == other.fitnesses.size()-1) {
					themTime = Double.POSITIVE_INFINITY;
				}else{
					themIndex++;
				}
			}
			
		}
		return ans;
	}
	
	public double findMax() {
		return Collections.max(fitnesses);
	}
	
}
