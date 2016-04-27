package actions;

public class InvalidStateException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String message = "";
	
	public InvalidStateException(){
		
	}
	
	public InvalidStateException(String message){
		this.message = message;
	}
}
