import java.util.ArrayList;

import actions.Actions;

public class LastActionInIfAndFirstTwoOfBranches {

	ArrayList<Actions> lastActionsInIf;
	Actions firstStatementInSuccessBranch;
	Actions firstStatementInFailureBranch;

	public LastActionInIfAndFirstTwoOfBranches(ArrayList<Actions> lastActionInIf,
			Actions firstStatementInSuccessBranch,
			Actions firstStatementInFailureBranch) {
		this.lastActionsInIf = lastActionInIf;
		this.firstStatementInSuccessBranch = firstStatementInSuccessBranch;
		this.firstStatementInFailureBranch = firstStatementInFailureBranch;
	}

	public ArrayList<Actions> getLastActionsInIf() {
		return lastActionsInIf;
	}

	public Actions getFirstStatementInSuccessBranch() {
		return firstStatementInSuccessBranch;
	}

	public Actions getFirstStatementInFailureBranch() {
		return firstStatementInFailureBranch;
	}
}
