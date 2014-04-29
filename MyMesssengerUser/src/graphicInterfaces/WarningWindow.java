package graphicInterfaces;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class WarningWindow {

	private JFrame frame;
	private JTextField textField;
	private String text;

	public static void openWarningWindow(final String text) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WarningWindow window = new WarningWindow(text);
					window.frame.setVisible(false); 
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public WarningWindow(String text) {
		this.text = text;
	}

	public void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 444, 145);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		frame.setVisible(true);

		textField = new JTextField();
		textField.setBounds(10, 11, 408, 52);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		textField.setText(text);

		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
			}
		});
		btnOk.setBounds(157, 73, 89, 23);
		frame.getContentPane().add(btnOk);
	}

}
