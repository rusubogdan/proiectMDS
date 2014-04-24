package message;

import graphicInterfacesServer.Connection;
import java.io.Serializable;
import com.entities.User;

public interface Message extends Serializable {

	public User getUser();

	public void interactOnServer(Connection connection, Connection connection2);

	public void interactOnClient();

	public Connection getConnectionOfSender();
	
	public Connection getConnectionOfReceiver();
	
	public void setConnectionOfSender(Connection connection);
	
	public void setConnectionOfReceiver(Connection connection);
	
}
