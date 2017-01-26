package system;

import java.util.Hashtable;

public class Fitness {

	public static Fitness MINIMUM_POSSIBLE_FITNESS;
	
	private Hashtable<String,Double> objectiveValues;
	
	public Fitness(){
		objectiveValues = new Hashtable<String,Double>();
	}

	// add them together
	public Fitness or(Fitness fitness) {
		// to do this, the types must be the same
		if (this.objectiveValues.keySet() != fitness.objectiveValues.keySet()){
			return null;
		}else{
			Fitness ans = new Fitness();
			for (String key : this.objectiveValues.keySet()){
				ans.put(key,this.objectiveValues.get(key) + fitness.objectiveValues.get(key));
			}
			return ans;
		}
	}
	
	// multiply them together
	public Fitness and(Fitness fitness){
		// to do this, the types must be the same
		if (this.objectiveValues.keySet() != fitness.objectiveValues.keySet()){
			return null;
		}else{
			Fitness ans = new Fitness();
			for (String key : this.objectiveValues.keySet()){
				ans.put(key,this.objectiveValues.get(key) * fitness.objectiveValues.get(key));
			}
			return ans;
		}
	}
	
	public void put(String s, Double d){
		objectiveValues.put(s,d);
	}
	
	public Double get(String s){
		return objectiveValues.get(s);
	}

}
