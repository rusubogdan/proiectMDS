package graphicInterfaces;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WarningWindows extends Thread{

	private JFrame frame;
	private JTextField textField;
	private String textToBeShown;
	private boolean isCanceled = false;

	public WarningWindows(String text) {
		textToBeShown = text;
		initialize();
	}
	
	private void close() {
		System.out.println("In close");
		isCanceled = true;
	}
	
	public void run() {
		System.out.println("in Run");
		while (!isCanceled) {
			System.out.print(".");
		}
	}

	private void initialize() {
		frame = new JFrame();
		
		
		frame.setBounds(100, 100, 450, 207);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		textField = new JTextField();
		textField.setEditable(false);
		textField.setBounds(37, 46, 350, 49);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		textField.setText(textToBeShown);
		
		JButton btnOk = new JButton("OK");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("In OK button");
				frame.setVisible(false);
				close();
				
				
			}
		});
		
		frame.addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					frame.setVisible(false);
					close();
				}
			}
		});
		
		frame.getContentPane().addKeyListener(new KeyAdapter() {
			@Override
			public void keyTyped(KeyEvent e) {
				if(e.getKeyCode() == KeyEvent.VK_ENTER) {
					frame.setVisible(false);
					close();
				}
			}
		});
		
		frame.addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				frame.setVisible(false);
				close();
			}
		});
		
		btnOk.setBounds(157, 117, 89, 23);
		frame.getContentPane().add(btnOk);
		frame.setVisible(true); 
	}
}
