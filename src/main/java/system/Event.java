package system;

import tactics.FailableTactic;
import tactics.Tactic;

public class Event implements Comparable<Event>{

	long time;
	
	FailableTactic tactic;
	
	EventType type;
	
	double probability;
	
	public Event(long time, FailableTactic tactic, EventType type){
		this.time = time;
		this.tactic = tactic;
		this.type = type;

		probability = tactic.getFailChance();
	
	}
	
	public Event(FailableTactic t) {
		this.tactic = t;
		this.type = EventType.START;
		probability = tactic.getFailChance();
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

	public void setTactic(FailableTactic tactic) {
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
	
	public Event clone(){
		
		Event clone = new Event(time,(FailableTactic) tactic.clone(),type);
		
		clone.probability = this.probability;
		
		return clone;
		
	}
	
}
