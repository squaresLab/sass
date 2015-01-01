package plan;

public class Plan {
	PlanNode startNode;

	// current thought is to create a plan from the bottom up recursively
	public Plan() {

	}

	public Plan(PlanNode startNode) {
		this.startNode = startNode;
	}

	public String planString() {
		return startNode.planString();
	}

	public PlanNode getStartNode() {
		return startNode;
	}

	public void setStartNode(PlanNode startNode) {
		this.startNode = startNode;
	}
}
