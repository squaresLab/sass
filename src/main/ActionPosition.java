package main;
import actions.Actions;

public class ActionPosition {

	Actions action;
	int position;

	public ActionPosition(Actions action, int position) {
		this.action = action;
		this.position = position;
	}

	public int getPosition() {
		return position;
	}

	public Actions getAction() {
		return action;
	}

}
