package exception;

public class DatabaseException extends Exception {
	public DatabaseException() {
		super("Unable to connect to Database .");
	}
	
	@Override
	public String toString(){
		return "DatabaseException: "+getMessage();
		
	}
}
