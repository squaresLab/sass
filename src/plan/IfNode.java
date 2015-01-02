package plan;

import org.jgap.gp.CommandGene;

public class IfNode extends PlanNode {

	PlanNode testNode;
	PlanNode successNode;
	PlanNode failureNode;

	public IfNode(CommandGene planGene) {
		this.planGene = planGene;
	}

	public IfNode(CommandGene planGene, PlanNode testNode, PlanNode successNode,
			PlanNode failureNode) {
		this.planGene = planGene;
		this.testNode = testNode;
		this.successNode = successNode;
		this.failureNode = failureNode;
	}

	public PlanNode getTestNode() {
		return testNode;
	}

	public void setTestNode(PlanNode testNode) {
		this.testNode = testNode;
	}

	public PlanNode getSuccessNode() {
		return successNode;
	}

	public void setSuccessNode(PlanNode successNode) {
		this.successNode = successNode;
	}

	public PlanNode getFailureNode() {
		return failureNode;
	}

	public void setFailureNode(PlanNode failureNode) {
		this.failureNode = failureNode;
	}

	public PlanNode getBranch(int branchNumber) {
		if (branchNumber == 0) {
			return testNode;
		} else if (branchNumber == 1) {
			return successNode;
		} else if (branchNumber == 2) {
			return failureNode;
		} else {
			System.err.println("Invalid branchNumber");
			return null;
		}
	}

	@Override
	public String planString() {
		if (testNode == this) {
			System.out.println("test node is the same as this node");
		}
		if (successNode == this) {
			System.out.println("success node is the same as this node");
		}
		if (failureNode == this) {
			System.out.println("failure node is the same as this node");
		}
		String result = "if (" + testNode.planString() + ") do("
				+ successNode.planString() + ") else (" + failureNode.planString()
				+ ")";
		return result;
	}

}
