package com.theopentutorials.jdbc.db;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Main_Menu {

	private JFrame frame;
	private JTextField textUsername;
	private JPasswordField passwordField;
	private JLabel lblEmail;
	private JLabel lblNewLabel_3;
	private JTextField textEmail;
	private JTextField textAge;
	private JButton btnLogin;
	private JButton btnSignUp;

	private Connection conn;
	private Statement stmt;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Main_Menu window = new Main_Menu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Main_Menu() {
		initialize();
		/*try {
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306", "root", "SIR123");
			stmt = conn.createStatement();
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 1343, 660);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(198, 106, 70, 14);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Password");
		lblNewLabel_1.setBounds(198, 173, 49, 14);
		frame.getContentPane().add(lblNewLabel_1);
		
		textUsername = new JTextField();
		textUsername.setBounds(257, 85, 279, 56);
		frame.getContentPane().add(textUsername);
		textUsername.setColumns(10);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(257, 152, 279, 56);
		frame.getContentPane().add(passwordField);
		
		lblEmail = new JLabel("Email");
		lblEmail.setBounds(752, 106, 70, 14);
		frame.getContentPane().add(lblEmail);
		
		lblNewLabel_3 = new JLabel("Age");
		lblNewLabel_3.setBounds(752, 173, 49, 14);
		frame.getContentPane().add(lblNewLabel_3);
		
		textEmail = new JTextField();
		textEmail.setColumns(10);
		textEmail.setBounds(811, 85, 279, 56);
		frame.getContentPane().add(textEmail);
		
		textAge = new JTextField();
		textAge.setColumns(10);
		textAge.setBounds(811, 152, 279, 56);
		frame.getContentPane().add(textAge);
		
		btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			
			
			
			public void actionPerformed(ActionEvent e) {
				
				String username = textUsername.getText();
				String pass = passwordField.getText();
				
				
				if (username.equalsIgnoreCase("0") || pass.equalsIgnoreCase("0")) 
				{
					//for Adminstratoirs
					Adminstrators adminstrators = new Adminstrators();
					adminstrators.setVisible(true);
				}
				
				else if(username.equalsIgnoreCase("") || pass.equalsIgnoreCase("")) {
					JOptionPane.showMessageDialog(null, "Please Enter Username and Password ");
				}		
				else try {
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/Playon", "root", "SIR123");
					
					
					//Statement stmt = conn.createStatement();
					String query="select * from player where Username=? and password=?";
					PreparedStatement ptr=conn.prepareStatement(query);
					ptr.setString(1, username);
					ptr.setString(2, pass);
					ResultSet rs=ptr.executeQuery();
					
					
					int count = 0;
					while(rs.next()) 
					{
						count++;	
					}
					
					if (count == 1) 
					{
						JOptionPane.showMessageDialog(btnLogin, "logged in Successfully! ");
						//for Adminstratoirs
						Player_page Player_page = new Player_page();
						Player_page.setVisible(true);
					}
					else
					{
						JOptionPane.showMessageDialog(btnLogin, "Username or Password is incorrect");
					}
					
					
				
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
				}
			}
		});
		btnLogin.setBounds(257, 297, 279, 62);
		frame.getContentPane().add(btnLogin);
		
		btnSignUp = new JButton("Sign up");
		btnSignUp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
					
				String username = textUsername.getText();
				String pass = passwordField.getText();
				String Email= textEmail.getText();
				String Age = textAge.getText();
				
				if (username.equalsIgnoreCase("") || pass.equalsIgnoreCase("") ||  Email.equalsIgnoreCase("") ||  Age.equalsIgnoreCase("")) 
				{
					JOptionPane.showMessageDialog(null, "Please Enter Username, Password, Email and Age");
				}
				
				
				else try {
					conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/playon", "root", "SIR123");
	                stmt = conn.createStatement();
	                int IsSigend = stmt.executeUpdate("INSERT INTO player VALUES('" + username + "', '" + pass  + "', '" + Email + "', '" + Age + "')" );
					
					if( IsSigend ==0) {
						JOptionPane.showMessageDialog(null, "Username Already exist");
					}
					else {
						JOptionPane.showMessageDialog(null, "SignUp Successfully!   Welcooome x) " + username);
					}
					conn.close();
					stmt.close();
					
					
					
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(null, e1);
				} 
				
				
			}
		});
		btnSignUp.setBounds(811, 297, 279, 62);
		frame.getContentPane().add(btnSignUp);
		
	}

	
}
