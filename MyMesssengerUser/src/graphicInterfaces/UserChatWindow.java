package graphicInterfaces;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import message.ChatMessage;

public class UserChatWindow {

	private JFrame frmConversation;
	private String friend;
	private AppHandler appHandler;
	private ChatMessage chatMessage;
	private JScrollPane scrollPane;
	private JTextArea textArea;
	private JTextField messageToSend;
	private JScrollPane scrollPaneSender;
	private UserChatWindow thatsMe;

	public String getFriend() {
		return friend;
	}

	public UserChatWindow(String friend, AppHandler appHandler) {
		System.out.println("new userChatWindow");
		this.appHandler = appHandler;
		this.friend = friend;
		this.thatsMe = this;
		initialize();
	}

	public void closeConversation() {
		frmConversation.dispose();
	}

	private void initialize() {
		frmConversation = new JFrame();
		frmConversation.setTitle(friend);
		frmConversation.setBounds(100, 100, 450, 300);
		frmConversation.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frmConversation.setLocationRelativeTo(frmConversation);

		JButton sendButton = new JButton("Send");

		sendButton.setBounds(237, 185, 68, 59);

		frmConversation.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {

				ListOfUsersWindow.removeFromUsers(thatsMe);

				e.getWindow().dispose();
			}
		});

		sendButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				String user = new String();
				String msgToSend = new String();

				msgToSend = messageToSend.getText();

				if (!msgToSend.equals("")) {
					user = appHandler.getUsername();

					chatMessage = new ChatMessage(user, msgToSend, friend);
					appHandler.addMessageToQueue(chatMessage);

					synchronized (textArea) {
						String aux = textArea.getText();
						textArea.setText("");
						textArea.setText(aux + "\n" + msgToSend);
						messageToSend.setText("");
					}

				}

			}
		});

		frmConversation.getContentPane().setLayout(null);
		frmConversation.getContentPane().add(sendButton);

		JLabel lblNewLabel = new JLabel("New label");
		lblNewLabel.setBounds(329, 11, 95, 95);
		frmConversation.getContentPane().add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("New label");
		lblNewLabel_1.setBounds(329, 149, 95, 95);
		frmConversation.getContentPane().add(lblNewLabel_1);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 11, 293, 152);
		frmConversation.getContentPane().add(scrollPane);

		textArea = new JTextArea();
		textArea.setEditable(false);
		scrollPane.setViewportView(textArea);

		scrollPaneSender = new JScrollPane();
		scrollPaneSender.setBounds(10, 185, 223, 66);
		frmConversation.getContentPane().add(scrollPaneSender);

		messageToSend = new JTextField();
		scrollPaneSender.setViewportView(messageToSend);
		messageToSend.setColumns(10);

		frmConversation.setVisible(true);

		scrollPaneSender.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					System.out.println("in enter din scrollPane");
					e.consume();
					String user = new String();
					String msgToSend = new String();

					msgToSend = messageToSend.getText();

					if (!msgToSend.equals("")) {
						user = appHandler.getUsername();

						chatMessage = new ChatMessage(user, msgToSend, friend);
						appHandler.addMessageToQueue(chatMessage);

						synchronized (textArea) {
							String aux = textArea.getText();
							textArea.setText("");
							textArea.setText(aux + "\n" + msgToSend);
							messageToSend.setText("");
						}

					}
				}
			}
		});

		messageToSend.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					e.consume();
					String user = new String();
					String msgToSend = new String();

					msgToSend = messageToSend.getText();

					if (!msgToSend.equals("")) {
						user = appHandler.getUsername();

						chatMessage = new ChatMessage(user, msgToSend, friend);
						appHandler.addMessageToQueue(chatMessage);

						synchronized (textArea) {
							String aux = textArea.getText();
							textArea.setText("");
							textArea.setText(aux + "\n" + msgToSend);
							messageToSend.setText("");
						}

					}
				}
			}
		});
		
	}

	public synchronized void setMessage(String message) {
		String aux = textArea.getText();
		textArea.setText("");
		aux = aux + "\n" + message;
		textArea.setText(aux);
		messageToSend.setText("");
	}
}
