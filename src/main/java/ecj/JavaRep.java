package ecj;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;

public class JavaRep implements Serializable {
	
	private HashMap<Integer,Object> objMap;
	private HashMap<Integer,String> stringMap;
	private HashMap<Integer, HashMap<String,Integer>> charVectorMap;
	private HashSet<String> vectorKeys;
	
	private int cursor;
	
	public HashMap<Integer, Object> getObjMap() {
		return objMap;
	}

	public JavaRep(){
		objMap = new HashMap<Integer,Object>();
		stringMap = new HashMap<Integer,String>();
		charVectorMap = new HashMap<Integer, HashMap<String,Integer>>();
		vectorKeys = new HashSet<String>();
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
	
	// associates a vector with the current line, overwrites existing vector
	public void appendVector(HashMap<String,Integer> vector) {
		charVectorMap.put(cursor, vector);
		vectorKeys.addAll(vector.keySet());
	}
	
	// adds the given vector to the current vector
	public void addToVector(HashMap<String,Integer> vector) {
		HashMap<String,Integer> existing = charVectorMap.getOrDefault(cursor, new HashMap<String,Integer>());
		
		for (String s : vector.keySet()) {
			existing.put(s, existing.getOrDefault(s, 0)+vector.get(s));
		}
		
		charVectorMap.put(cursor, existing);
		
		vectorKeys.addAll(vector.keySet());
	}
	
	public static HashMap<String,Integer> vectorAdd(HashMap<String,Integer> a, HashMap<String,Integer> b) {
		
		HashMap<String,Integer> ans = new HashMap<String,Integer>(a);
		
		for (String s : b.keySet()) {
			ans.put(s, ans.getOrDefault(s, 0)+b.get(s));
		}
		
		return ans;
		
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

	public String getVectorString(String filename) {
		String ans = "";
		
		// get a consistent list of the keys
		ArrayList<String> keys = new ArrayList<String>(vectorKeys);
		
		Collections.sort(keys);
		
		ans += "#";
		for (String key : keys) {
			ans += key;
			ans += ", ";
		}
		
		ans += "\n";
		
		for (int line = 0; line < cursor; line++) {
			HashMap<String,Integer> vector = charVectorMap.get(line);
			
			if (vector == null) {
				continue;
			}
			
			ans += getVectorComment(line, filename);
			ans += "\n";
			
			for (String key : keys) {
				ans += vector.getOrDefault(key, 0);
				ans += " ";
			}
			ans = ans.trim();
			ans += "\n";
			
		}
		
		return ans;
	}

	private String getVectorComment(int line, String filepath) {
		//# FILE:/home/ckinneer/git/sass/javagen/Plan1571871557780.java, LINE:1, OFFSET:10, NODE_KIND:0, CONTEXT_KIND:0, NEIGHBOR_KIND:0, NUM_NODE:118, NUM_DECL:0, NUM_STMT:0, NUM_EXPR:0, TBID:0, TEID:54, VARs:{}5, 
		return "# FILE:"+filepath+", LINE:"+line+", OFFSET:10, NODE_KIND:0, CONTEXT_KIND:0, NEIGHBOR_KIND:0, NUM_NODE:118, NUM_DECL:0, NUM_STMT:0, NUM_EXPR:0, TBID:0, TEID:54, VARs:{}5,";
	}
	
}