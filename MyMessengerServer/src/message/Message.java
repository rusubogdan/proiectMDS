package message;

import graphicInterfacesServer.Connection;

import java.io.Serializable;

public interface Message extends Serializable {

	public void interactOnServer(Connection connection, Connection connection2);

	public Connection getConnectionOfSender();
	
	public Connection getConnectionOfReceiver();
	
	public void setConnectionOfSender(Connection connection);
	
	public void setConnectionOfReceiver(Connection connection);
	
}
