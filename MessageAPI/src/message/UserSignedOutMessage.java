package message;

public class UserSignedOutMessage implements Message {

	private static final long serialVersionUID = 1L;

	private String signedOutUser;

	public String getSignedOutUser() {
		return signedOutUser;
	}

	public void setSignedOutUser(String signedOutUser) {
		this.signedOutUser = signedOutUser;
	}
	
	
}
