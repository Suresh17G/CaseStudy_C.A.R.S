package exception;

public class DatabaseException extends Exception {
	public DatabaseException() {
		super("Unable to connect to Database .");
	}
	 public DatabaseException(String message) {
	        super(message);
	    }
	
	@Override
	public String toString(){
		return "DatabaseException: "+getMessage();
		
	}
}
