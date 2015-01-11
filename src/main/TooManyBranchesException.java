package main;

/**
 * This exception if there are too many branches in the plan for the code to
 * generate a list of all possible branch combinations (if the number of
 * branches is greater than 31; the total number of combinations would be
 * greater than the max integer value). I'll have to come up with a different
 * way to handle this exception later if I need large plans.
 * 
 * @author Zack
 * 
 */
public class TooManyBranchesException extends Exception {

	int numberOfBranches = 0;

	public TooManyBranchesException(int numberOfBranches) {
		this.numberOfBranches = numberOfBranches;
	}

	public int getNumberOfBranches() {
		return numberOfBranches;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 3482616576743768754L;

}
