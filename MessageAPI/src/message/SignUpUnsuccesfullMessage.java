package message;

public class SignUpUnsuccesfullMessage implements Message {

	private static final long serialVersionUID = 1L;
	private String user;
	private String reason;

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}