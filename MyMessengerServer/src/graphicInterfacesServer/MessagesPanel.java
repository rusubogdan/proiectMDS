package graphicInterfacesServer;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;
import threads.ServerThread;

public class MessagesPanel {

	private JFrame frame;
	public static JTextField textField;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MessagesPanel window = new MessagesPanel();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MessagesPanel() {
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		textField = new JTextField();
		textField.setBounds(10, 121, 287, 130);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JButton buttonStartServer = new JButton("StartServer");
		buttonStartServer.addActionListener(new ActionListener() {
			

			@SuppressWarnings("unused")
			private ServerThread server = null;

			public void actionPerformed(ActionEvent e) {
				server  = new ServerThread();
			}
				
		});
		buttonStartServer.setBounds(321, 175, 89, 23);
		frame.getContentPane().add(buttonStartServer);

		textField = new JTextField();
		textField.setBounds(10, 27, 287, 47);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
	}

}
