package message;

public class UserSignedInMessage implements Message {

	private static final long serialVersionUID = 1L;
	private String signedInUser;
	
	public String getSignedInUser() {
		return signedInUser;
	}
	public void setSignedInUser(String signedInUser) {
		this.signedInUser = signedInUser;
	}
	
	
}
