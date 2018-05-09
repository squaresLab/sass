package util;

import java.util.ArrayList;

public class DeckardCluster implements Comparable {
	
	ArrayList<String> lines;
	
	public DeckardCluster(){
		lines = new ArrayList<String>();
	}
	
	public ArrayList<String> getLines(){
		return lines;
	}

	public void addLine(String line) {
		lines.add(line);
	}

	@Override
	public int compareTo(Object arg0) {
		
		if (!(arg0 instanceof DeckardCluster)){
			throw new ClassCastException("DeckardCluster object expected");
		}
		
		return ((Integer)lines.size()).compareTo(((DeckardCluster) arg0).lines.size());
	}

}
