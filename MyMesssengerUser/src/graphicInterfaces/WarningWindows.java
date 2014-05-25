package graphicInterfaces;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class WarningWindows extends Thread {

	private JFrame frame;
	private JTextField textField;
	private String textToBeShown;
	private AppMainWindow appMainWindow;

	public WarningWindows(String text) {
		textToBeShown = text;
		initialize();
	}

	public WarningWindows(String text, AppMainWindow appMainWindow) {
		this.textToBeShown = text;
		this.appMainWindow = appMainWindow;
		initialize();
	}

	private void initialize() {
		frame = new JFrame();

		frame.setBounds(100, 100, 450, 207);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					frame.setVisible(false);
					System.out.println("DIN ENTER");
				}
			}
		});

		textField.setEditable(false);
		textField.setBounds(37, 46, 350, 49);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		textField.setText(textToBeShown);

		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (appMainWindow != null)
					appMainWindow.closeWindow();
				frame.setVisible(false);
				// frame.dispose();

			}
		});

		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					frame.setVisible(false);
					System.out.println("DIN ENTER");
				}
			}
		});

		frame.getContentPane().addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					frame.setVisible(false);
					System.out.println("DIN ENTER");
				}
			}
		});

		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (appMainWindow != null)
					appMainWindow.closeWindow();
				frame.setVisible(false);
			}
		});

		btnOk.setBounds(157, 117, 89, 23);
		frame.getContentPane().add(btnOk);
		frame.setVisible(true);
	}
}
