package ecj;

import java.io.Serializable;
import java.util.HashMap;

public class JavaRep implements Serializable {
	
	private HashMap<Integer,Object> objMap;
	private HashMap<Integer,String> stringMap;
	private int cursor;
	
	public HashMap<Integer, Object> getObjMap() {
		return objMap;
	}

	public JavaRep(){
		objMap = new HashMap<Integer,Object>();
		stringMap = new HashMap<Integer,String>();
		cursor = 0;
	}
	
	public void newLine(){
		cursor++;
	}
	
	// adds to current line and then moves cursor to next line
	public void addLine(String string, Object obj){
		objMap.put(cursor, obj);
		stringMap.put(cursor, string);
		cursor++;
	}
	
	// appends string to the current line w/o overwrite, but 
	// the object is always overwritten
	public void appendLine(String string, Object obj){
		objMap.put(cursor, obj);
		String cur = stringMap.containsKey(cursor) ? stringMap.get(cursor) : "";
		stringMap.put(cursor,cur+string);
	}
	
	// append string only, no change to object
	public void appendLine(String string){
		String cur = stringMap.containsKey(cursor) ? stringMap.get(cursor) : "";
		stringMap.put(cursor,cur+string);
	}
	
	public void setObjMap(HashMap<Integer, Object> objMap) {
		this.objMap = objMap;
	}

	public HashMap<Integer, String> getStringMap() {
		return stringMap;
	}

	public void setStringMap(HashMap<Integer, String> stringMap) {
		this.stringMap = stringMap;
	}

	public int getCursor() {
		return cursor;
	}

	public void setCursor(int cursor) {
		this.cursor = cursor;
	}
	
	public String toString() {
		String ans = "";
		
		for (int line = 0; line < cursor; line++) {
			String cur = stringMap.containsKey(line) ? stringMap.get(line) : "";
			ans += cur + "\n";
		}
		
		return ans;
	}
	
}