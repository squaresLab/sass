package system;

import tactics.Tactic;

public class Event implements Comparable<Event>{

	long time;
	Tactic tactic;
	EventType type;
	
	public Event(long time, Tactic tactic, EventType type){
		this.time = time;
		this.tactic = tactic;
		this.type = type;
	}
	
	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public Tactic getTactic() {
		return tactic;
	}

	public void setTactic(Tactic tactic) {
		this.tactic = tactic;
	}

	public EventType getType() {
		return type;
	}

	public void setType(EventType type) {
		this.type = type;
	}
	
	public enum EventType {
		START,END;
	}

	@Override
	public int compareTo(Event o) {
		
		return (int) (this.time - o.time);
	}
	
}
