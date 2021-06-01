package dbExceptions;

public class DataBException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	// custom exception
	public DataBException(String msg) {
		super(msg);
	}
}
