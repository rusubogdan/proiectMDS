package graphicInterfaces;

import java.awt.EventQueue;
import java.util.ArrayList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import com.entities.User;

public class ListOfUsersWindow {

	private JFrame frame;
	private DefaultListModel<String> listModel;
	@SuppressWarnings("rawtypes")
	private JList list;

	private void openWindow() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					frame.setVisible(true);
					System.out.println("in openWindow");
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public ListOfUsersWindow() {
		initialize();
		//openWindow();
		System.out.println("in constructorul clasei ListOfUser");
	}

	public synchronized void addUsersToList(ArrayList<User> listOfUsers) {
		listModel.clear();
		for (User user : listOfUsers) {
			listModel.addElement(user.getUsername());
		}
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private synchronized void initialize() {
		System.out.println("in initialize()");
		frame = new JFrame();
		frame.setVisible(true);
		System.out.println("dupa set Visible");
		frame.setBounds(100, 100, 283, 418);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		JButton button = new JButton("+");
		button.setBounds(10, 38, 41, 23);
		frame.getContentPane().add(button);

		JButton btnNewGroup = new JButton("New group");
		btnNewGroup.setBounds(65, 38, 89, 23);
		frame.getContentPane().add(btnNewGroup);

		listModel = new DefaultListModel<String>();

		list = new JList(listModel);

		list.setBounds(27, 89, 204, 253);
		frame.getContentPane().add(list);
	}
}
