package message;

public class ErrorMessage implements Message {

	private static final long serialVersionUID = 1L;
	private String user;
	private String message;
	
	public ErrorMessage(String message) {
		this.message = message;
	}
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}

}