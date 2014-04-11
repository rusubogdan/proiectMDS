package message;

public class ErrorMessage extends Message {

	private static final long serialVersionUID = 1L;

	public ErrorMessage(String message) {
		super(null, message, null);//message = user/friend unexistent
	}

}
