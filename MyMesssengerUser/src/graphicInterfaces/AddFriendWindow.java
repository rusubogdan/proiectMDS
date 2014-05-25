package graphicInterfaces;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AddFriendWindow {

	private JFrame frame;
	private ListOfUsersWindow listOfUsersWindow;
	private JTextField textField;

	public AddFriendWindow(ListOfUsersWindow listOfUsersWindow) {
		this.listOfUsersWindow = listOfUsersWindow;
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 231, 151);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

		JLabel usernameLabel = new JLabel("Username:");
		usernameLabel.setBounds(10, 11, 95, 14);
		frame.getContentPane().add(usernameLabel);

		textField = new JTextField();
		textField.setBounds(10, 36, 179, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);

		JButton btnAdd = new JButton("ADD");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String text = textField.getText();
				if (!text.equals("")) {
					listOfUsersWindow.addFriend(text);
				} else
					new WarningWindows("Please type a username");
				frame.dispose();
			}
		});
		btnAdd.setBounds(58, 67, 89, 23);
		frame.getContentPane().add(btnAdd);
	}
}
