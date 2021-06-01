package dbExceptions;

public class DataBIntegrityException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	// Integrity custom exception
	public  DataBIntegrityException(String msg) {
		super(msg);
	}

}
