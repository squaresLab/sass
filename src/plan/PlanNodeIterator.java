package plan;

import java.util.Iterator;
import java.util.Stack;

public class PlanNodeIterator implements Iterator<PlanNode> {

	private class NodeIntPair {
		int branchNumber;
		IfNode ifNode;

		public NodeIntPair() {
		}

		public NodeIntPair(IfNode ifNode, int branchNumber) {
			this.ifNode = ifNode;
			this.branchNumber = branchNumber;
		}

		public IfNode getIfNode() {
			return ifNode;
		}

		public void setPlanNode(IfNode ifNode) {
			this.ifNode = ifNode;
		}

		public int getBranchNumber() {
			return branchNumber;
		}

		public void setBranchNumber(int branchNumber) {
			this.branchNumber = branchNumber;
		}
	}

	PlanNode originalNode;
	PlanNode currentIteratingNode = null;
	Stack<NodeIntPair> ifNodes = new Stack<NodeIntPair>();

	public PlanNodeIterator(PlanNode planNode) {
		this.originalNode = planNode;
		this.currentIteratingNode = planNode;
	}

	@Override
	public boolean hasNext() {
		if (currentIteratingNode == null) {
			return false;
		} else {
			return true;
		}
	}

	public PlanNode getNextIfBranch() {
		NodeIntPair nIP = ifNodes.peek();
		if (nIP.getBranchNumber() < 3) {
			PlanNode node = nIP.getIfNode().getBranch(nIP.getBranchNumber());
			nIP.setBranchNumber(nIP.getBranchNumber() + 1);
			return node;
		} else {
			ifNodes.pop();
			return getNextIfBranch();
		}
	}

	@Override
	public PlanNode next() {
		if (currentIteratingNode instanceof SingleActionNode) {
			if (((SingleActionNode) currentIteratingNode).getNextNode() == null) {
				if (ifNodes.empty()) {
					currentIteratingNode = null;
				} else { // has an untaken branch
					currentIteratingNode = getNextIfBranch();
				}
			} else {
				currentIteratingNode = ((SingleActionNode) currentIteratingNode)
						.getNextNode();
			}
		} else if (currentIteratingNode instanceof IfNode) {
			IfNode iNode = ((IfNode) currentIteratingNode);
			currentIteratingNode = iNode.getTestNode();
			ifNodes.add(new NodeIntPair(iNode, 1));
		} else {
			System.out.println("unimplemented node type");
			System.out.print(new Exception());
			System.exit(1);
		}
		return currentIteratingNode; // shouldn't be reachable
	}

	@Override
	public void remove() {
		throw new UnsupportedOperationException();

	}

}
