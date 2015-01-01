package plan;

import org.jgap.gp.CommandGene;

public class SingleActionNode extends PlanNode {
	PlanNode nextNode;

	public SingleActionNode(CommandGene planGene) {
		this.planGene = planGene;
	}

	public SingleActionNode(CommandGene planGene, PlanNode nextNode,
			PlanNode previousNode) {
		this.planGene = planGene;
		this.nextNode = nextNode;
		this.previousNode = previousNode;
	}

	@Override
	public String planString() {
		String result = planGene.toString();
		if (nextNode != null) {
			result += ", " + nextNode.planString();
		}
		return result;
	}

	public PlanNode getNextNode() {
		return nextNode;
	}

	public void setNextNode(PlanNode nextNode) {
		this.nextNode = nextNode;
	}

}
