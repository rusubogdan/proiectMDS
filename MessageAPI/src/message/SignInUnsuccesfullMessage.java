package message;

public class SignInUnsuccesfullMessage implements Message {

	private static final long serialVersionUID = 1L;
	private String user;
	
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	
	
}