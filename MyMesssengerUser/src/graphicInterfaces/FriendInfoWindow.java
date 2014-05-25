package graphicInterfaces;

import javax.swing.JFrame;

import message.RequestedFriendInfo;
import javax.swing.JTextField;

public class FriendInfoWindow {

	private JFrame frame;
	private RequestedFriendInfo requestedFriendInfo;
	private JTextField txtFriend;
	private JTextField textField;
	private JTextField txtFirstName;
	private JTextField fname;
	private JTextField txtLastName;
	private JTextField lname;
	private JTextField txtMobile;
	private JTextField mobile;
	private JTextField txtHomePhone;
	private JTextField hphone;
	private JTextField txtAddress;
	private JTextField address;

	public FriendInfoWindow(RequestedFriendInfo requestedFriendInfo) {
		this.requestedFriendInfo = requestedFriendInfo;
		initialize();
		
	}

	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 386, 316);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtFriend = new JTextField();
		txtFriend.setEditable(false);
		txtFriend.setText("Friend:");
		txtFriend.setBounds(10, 11, 86, 20);
		frame.getContentPane().add(txtFriend);
		txtFriend.setColumns(10);
		
		textField = new JTextField();
		textField.setBounds(106, 11, 167, 20);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		txtFirstName = new JTextField();
		txtFirstName.setEditable(false);
		txtFirstName.setText("First name:");
		txtFirstName.setBounds(10, 52, 86, 20);
		frame.getContentPane().add(txtFirstName);
		txtFirstName.setColumns(10);
		
		fname = new JTextField();
		fname.setBounds(106, 52, 254, 20);
		frame.getContentPane().add(fname);
		fname.setColumns(10);
		
		txtLastName = new JTextField();
		txtLastName.setEditable(false);
		txtLastName.setText("Last name:");
		txtLastName.setBounds(10, 83, 86, 20);
		frame.getContentPane().add(txtLastName);
		txtLastName.setColumns(10);
		
		lname = new JTextField();
		lname.setBounds(106, 83, 254, 20);
		frame.getContentPane().add(lname);
		lname.setColumns(10);
		
		txtMobile = new JTextField();
		txtMobile.setEditable(false);
		txtMobile.setText("Mobile:");
		txtMobile.setBounds(10, 114, 86, 20);
		frame.getContentPane().add(txtMobile);
		txtMobile.setColumns(10);
		
		mobile = new JTextField();
		mobile.setBounds(106, 114, 254, 20);
		frame.getContentPane().add(mobile);
		mobile.setColumns(10);
		
		txtHomePhone = new JTextField();
		txtHomePhone.setEditable(false);
		txtHomePhone.setText("Home phone:");
		txtHomePhone.setBounds(10, 145, 86, 20);
		frame.getContentPane().add(txtHomePhone);
		txtHomePhone.setColumns(10);
		
		hphone = new JTextField();
		hphone.setBounds(106, 145, 254, 20);
		frame.getContentPane().add(hphone);
		hphone.setColumns(10);
		
		txtAddress = new JTextField();
		txtAddress.setEditable(false);
		txtAddress.setText("Address:");
		txtAddress.setBounds(10, 176, 86, 20);
		frame.getContentPane().add(txtAddress);
		txtAddress.setColumns(10);
		
		address = new JTextField();
		address.setBounds(106, 176, 254, 20);
		frame.getContentPane().add(address);
		address.setColumns(10);
		frame.setVisible(true);
		
		textField.setText(requestedFriendInfo.getUser());
		fname.setText(requestedFriendInfo.getFirstName());
		lname.setText(requestedFriendInfo.getLastName());
		mobile.setText(requestedFriendInfo.getMobileNumber());
		hphone.setText(requestedFriendInfo.getHomePhoneNumber());
		address.setText(requestedFriendInfo.getAddress());
		
		
	}
}
