package graphicInterfaces;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JButton;

import message.UpdateUserInfo;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class UpdateInfoWindow {

	private JFrame frame;
	private JTextField txtFirstName;
	private JTextField txtLastName;
	private JTextField txtMobile;
	private JTextField txtHomePhone;
	private JTextField txtAddress;
	private JTextField fname;
	private JTextField lname;
	private JTextField mobile;
	private JTextField hphone;
	private JTextField address;
	private JTextField txtMyInfo;
	private AppHandler appHandler;

	public UpdateInfoWindow(AppHandler appHandler) {
		this.appHandler = appHandler;
		initialize();
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setVisible(true);

		txtFirstName = new JTextField();
		txtFirstName.setEditable(false);
		txtFirstName.setText("First name:");
		txtFirstName.setBounds(10, 53, 106, 20);
		frame.getContentPane().add(txtFirstName);
		txtFirstName.setColumns(10);

		txtLastName = new JTextField();
		txtLastName.setEditable(false);
		txtLastName.setText("Last name:");
		txtLastName.setBounds(10, 84, 106, 20);
		frame.getContentPane().add(txtLastName);
		txtLastName.setColumns(10);

		txtMobile = new JTextField();
		txtMobile.setEditable(false);
		txtMobile.setText("Mobile:");
		txtMobile.setBounds(10, 115, 106, 20);
		frame.getContentPane().add(txtMobile);
		txtMobile.setColumns(10);

		txtHomePhone = new JTextField();
		txtHomePhone.setEditable(false);
		txtHomePhone.setText("Home phone:");
		txtHomePhone.setBounds(10, 146, 106, 20);
		frame.getContentPane().add(txtHomePhone);
		txtHomePhone.setColumns(10);

		txtAddress = new JTextField();
		txtAddress.setEditable(false);
		txtAddress.setText("Address:");
		txtAddress.setBounds(10, 177, 106, 20);
		frame.getContentPane().add(txtAddress);
		txtAddress.setColumns(10);

		fname = new JTextField();
		fname.setBounds(126, 53, 275, 20);
		frame.getContentPane().add(fname);
		fname.setColumns(10);

		lname = new JTextField();
		lname.setBounds(126, 84, 275, 20);
		frame.getContentPane().add(lname);
		lname.setColumns(10);

		mobile = new JTextField();
		mobile.setBounds(126, 115, 275, 20);
		frame.getContentPane().add(mobile);
		mobile.setColumns(10);

		hphone = new JTextField();
		hphone.setBounds(126, 146, 275, 20);
		frame.getContentPane().add(hphone);
		hphone.setColumns(10);

		address = new JTextField();
		address.setBounds(126, 177, 275, 20);
		frame.getContentPane().add(address);
		address.setColumns(10);

		txtMyInfo = new JTextField();
		txtMyInfo.setEditable(false);
		txtMyInfo.setText("My info:");
		txtMyInfo.setBounds(92, 22, 86, 20);
		frame.getContentPane().add(txtMyInfo);
		txtMyInfo.setColumns(10);

		JButton btnSubmit = new JButton("Submit");
		btnSubmit.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				UpdateUserInfo updateUserInfo = new UpdateUserInfo();

				updateUserInfo.setUser(appHandler.getUsername());
				if (fname.getText() != null)
					updateUserInfo.setFirstName(fname.getText());
				else
					updateUserInfo.setFirstName("");
				if (lname.getText() != null)
					updateUserInfo.setLastName(lname.getText());
				else
					updateUserInfo.setLastName("");
				if (mobile.getText() != null)
					updateUserInfo.setMobileNumber(mobile.getText());
				else
					updateUserInfo.setMobileNumber("");
				if (hphone.getText() != null)
					updateUserInfo.setHomePhoneNumber(hphone.getText());
				else
					updateUserInfo.setHomePhoneNumber("");
				if (address.getText() != null)
					updateUserInfo.setAddress(address.getText());
				else
					updateUserInfo.setAddress("");

				appHandler.addMessageToQueue(updateUserInfo);
				
				frame.dispose();

			}
		});
		btnSubmit.setBounds(136, 215, 89, 23);
		frame.getContentPane().add(btnSubmit);
	}

}
