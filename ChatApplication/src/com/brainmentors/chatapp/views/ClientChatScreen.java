package com.brainmentors.chatapp.views;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import com.brainmentors.chatapp.network.Client;
import com.brainmentors.chatapp.utils.Userinfo;

public class ClientChatScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	//private final JScrollPane scrollPane = new JScrollPane();
	private JTextField textField;
	private JTextArea textArea;
	private Client client;
	
	public static void main(String[] args) {
			try {
				ClientChatScreen frame = new ClientChatScreen();
				
			} catch (UnknownHostException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	} 
	
	private void Sendbtn() {
		String message=textField.getText();
		try {
			client.sendMessage(Userinfo.USER_NAME+"-"+ message);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	public ClientChatScreen() throws UnknownHostException, IOException {
		textArea = new JTextArea();
		client=new Client(textArea);
		setTitle("Chit-Chat");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 878, 515);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JScrollPane scrollPane=new JScrollPane();
		scrollPane.setBounds(15, 17, 817, 404);
		contentPane.add(scrollPane);
		
		
		textArea.setFont(new Font("Monospaced", Font.PLAIN, 16));
		textArea.setBounds(23, 10, 814, 458);
		scrollPane.setViewportView(textArea);
		
		textField = new JTextField();
		textField.setFont(new Font("Tahoma", Font.PLAIN, 16));
		textField.setBounds(15, 431, 673, 37);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton Sendbtn = new JButton("Send Message");
		Sendbtn.setBackground(Color.WHITE);
		Sendbtn.setFont(new Font("Tahoma", Font.PLAIN, 14));
		Sendbtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sendbtn();
			}
		});
		Sendbtn.setBounds(709, 431, 123, 37);
		contentPane.add(Sendbtn);
		setVisible(true);
	}
}
