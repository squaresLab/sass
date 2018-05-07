package ecj;

import java.util.HashMap;

import ec.gp.GPNode;

public abstract class JavaGenerator extends GPNode {
	
	private static final long serialVersionUID = 5649052705596298495L;

	public abstract JavaRep generateJava(JavaRep java);
	
	public class JavaRep {
		
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
			stringMap.put(cursor,stringMap.get(cursor)+string);
		}
		
		// append string only, no change to object
		public void appendLine(String string){
			stringMap.put(cursor,stringMap.get(cursor)+string);
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
		
	}

}
