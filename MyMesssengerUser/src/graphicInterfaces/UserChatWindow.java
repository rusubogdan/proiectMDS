package graphicInterfaces;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

import org.hibernate.pretty.MessageHelper;

import message.ChatMessage;

public class UserChatWindow {

	private JFrame frmConversation;
	private JTextField messageToSend;
	private JTextField conversationHistory;
	private String friend;
	private ChatWindow chatWidow;

	public UserChatWindow(String friend, ChatWindow chatWindow) {
		this.chatWidow = chatWindow;
		this.friend = friend;
		initialize();
	}

	
	private void initialize() {
		frmConversation = new JFrame();
		frmConversation.setTitle(" To " + friend);
		frmConversation.setBounds(100, 100, 450, 300);
		frmConversation.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmConversation.getContentPane().setLayout(null);
		frmConversation.setLocationRelativeTo(frmConversation); 

		JButton btnNewButton = new JButton("Send");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String user = new String();
				String msgToSend = new String();
				ArrayList<String> friends = new ArrayList<String>();
				
				
				msgToSend = messageToSend.getText();
				
				if( !msgToSend.equals("")){
					user = chatWidow.name;
					friends.add(friend);
					ChatMessage chatMessage = new ChatMessage(user, msgToSend, friends);
					chatWidow.sendMessageToServer(chatMessage); 
					String aux = conversationHistory.getText();
					conversationHistory.setText("");
					aux = aux + "\n !" + msgToSend;
					conversationHistory.setText(aux);
					
					messageToSend.setText("");
					
				}
				
				
			}
		});
		btnNewButton.setBounds(237, 185, 68, 59);
		frmConversation.getContentPane().add(btnNewButton);

		messageToSend = new JTextField();
		messageToSend.setBounds(10, 178, 217, 73);
		frmConversation.getContentPane().add(messageToSend);
		messageToSend.setColumns(10);

		conversationHistory = new JTextField();
		conversationHistory.setEditable(false);
		conversationHistory.setBounds(10, 11, 295, 156);
		frmConversation.getContentPane().add(conversationHistory);
		conversationHistory.setColumns(10);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(329, 11, 95, 95);
		frmConversation.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(329, 149, 95, 95);
		frmConversation.getContentPane().add(lblNewLabel_1);

		frmConversation.setVisible(true);
	}

}
