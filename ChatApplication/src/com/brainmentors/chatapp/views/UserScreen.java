package com.brainmentors.chatapp.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.security.NoSuchAlgorithmException;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import com.brainmentors.chatapp.dao.UserDao;
import com.brainmentors.chatapp.dto.UserDto;
import com.brainmentors.chatapp.utils.Userinfo;

public class UserScreen extends JFrame{
	private JTextField useridtxt;
	private JPasswordField passwordtxt;

	public static void main(String[] args) {
		UserScreen window = new UserScreen();
	}
	
	UserDao userDao=new UserDao();
	private void doLogin() {
		String userid=useridtxt.getText();
		char[] password=passwordtxt.getPassword();
		
		UserDto userDto=new UserDto(userid, password);
		try {
			String message="";
			if (userDao.isLogin(userDto)) {
				message="Welcome "+userid;
				Userinfo.USER_NAME=userid;
				JOptionPane.showMessageDialog(this, message);
				setVisible(false);
				dispose();
				
				DashBoard dashBoard=new DashBoard(message);
				dashBoard.setVisible(true);
			}
			else {
				message="Invalid Userid or password";
				JOptionPane.showMessageDialog(this, message);
			}
			
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private void register()
	{
		String userid=useridtxt.getText();
		char[] password=passwordtxt.getPassword();
		//UserDao userDao=new UserDao();
		UserDto userDto=new UserDto(userid, password);
		try {
		int result=userDao.add(userDto);
		if(result>0) {
			JOptionPane.showMessageDialog(this, "Register Successfully");
		}
		else {
			JOptionPane.showMessageDialog(this, "Register Fail");
		}
		}
		catch(ClassNotFoundException |SQLException ex) {
			System.out.println("DB Issue....");
			ex.printStackTrace();
		}
		catch(Exception ex) {
			System.out.println("Some generic exception raised...");
			ex.printStackTrace();  //Where is  the Exception
		}
		System.out.println("User Id: "+userid+"\nPassword: "+password+" "+password.toString());
	}

	
	public UserScreen() {
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 14));
		setResizable(false);
		setTitle("Login");
		getContentPane().setBackground(Color.WHITE);
		
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LOGIN");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setBounds(308, 61, 146, 76);
		getContentPane().add(lblNewLabel);
		
		useridtxt = new JTextField();
		useridtxt.setBounds(337, 167, 206, 36);
		getContentPane().add(useridtxt);
		useridtxt.setColumns(10);
		
		JLabel UserIdlbl = new JLabel("User Id");
		UserIdlbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		UserIdlbl.setBounds(251, 164, 76, 36);
		getContentPane().add(UserIdlbl);
		
		JLabel pwdlbl = new JLabel("Password");
		pwdlbl.setFont(new Font("Tahoma", Font.PLAIN, 14));
		pwdlbl.setBounds(251, 228, 76, 36);
		getContentPane().add(pwdlbl);
		
		passwordtxt = new JPasswordField();
		passwordtxt.setBounds(337, 228, 206, 36);
		getContentPane().add(passwordtxt);
		
		JButton Loginbt = new JButton("Login");
		Loginbt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				doLogin();
			}
		});
		Loginbt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Loginbt.setBounds(267, 333, 85, 21);
		
		getContentPane().add(Loginbt);
		
		JButton Registerbt = new JButton("Register");
		Registerbt.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				register();
			}
		});
		Registerbt.setFont(new Font("Tahoma", Font.PLAIN, 16));
		Registerbt.setBounds(386, 333, 117, 21);
		getContentPane().add(Registerbt);
		setBackground(Color.WHITE);
		setSize(833, 440);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);	
		setVisible(true);
		
	}
}
