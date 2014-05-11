package graphicInterfacesServer;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;

import threads.ServerThread;

public class StartServerWindow {

	private JFrame frame;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StartServerWindow window = new StartServerWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static void stopTheServer() {
		System.exit(0); 
	}
	
	public StartServerWindow() {
		initialize();
		
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 322, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton buttonStartServer = new JButton("StartServer");
		buttonStartServer.addActionListener(new ActionListener() {

			@SuppressWarnings("unused")
			private ServerThread server = null;

			public void actionPerformed(ActionEvent e) {
				server = new ServerThread();
				frame.dispose();
			}

		});
		buttonStartServer.setBounds(63, 94, 179, 74);
		frame.getContentPane().add(buttonStartServer);
	}

}
