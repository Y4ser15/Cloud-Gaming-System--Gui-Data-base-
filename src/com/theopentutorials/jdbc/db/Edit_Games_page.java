package com.theopentutorials.jdbc.db;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Edit_Games_page extends JFrame {

	private JPanel contentPane;
	

	private JTextField textGname;
	private JTextField textGage;
	private JTextField textGmodel;
	private JTextField textConsleID;
	private JTextField textServerIP;
	private JTextField textGrate;
	
	private Connection conn;
	private Statement stmt;

	
	
	
	private void close() {
		this.setVisible(false);
	}
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Edit_Games_page frame = new Edit_Games_page();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Edit_Games_page() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 708, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblGname = new JLabel("Game Name: ");
		lblGname.setBounds(228, 131, 84, 14);
		contentPane.add(lblGname);
		
		JLabel lblType = new JLabel("Consle id:");
		lblType.setBounds(74, 184, 74, 14);
		contentPane.add(lblType);
		
		textGname = new JTextField();
		textGname.setBounds(322, 128, 86, 20);
		contentPane.add(textGname);
		textGname.setColumns(10);
		
		JLabel lblAge = new JLabel("Game Age: ");
		lblAge.setBounds(228, 159, 84, 14);
		contentPane.add(lblAge);
		
		textGage = new JTextField();
		textGage.setColumns(10);
		textGage.setBounds(322, 156, 86, 20);
		contentPane.add(textGage);
		
		JButton btnAdd = new JButton("Add");
		btnAdd.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String Gname= textGname.getText();
				String Gmodel= textGmodel.getText();
				String Gage= textGage.getText();
				String Grate = textGrate.getText();
				
				String IP = textServerIP.getText();
				String ConID = textConsleID.getText();
				
				
				if (IP.equalsIgnoreCase("") || ConID.equalsIgnoreCase("") ||  Grate.equalsIgnoreCase("") ||  Gname.equalsIgnoreCase("") ||  Gmodel.equalsIgnoreCase("") ||  Gage.equalsIgnoreCase("")) 
				{
					JOptionPane.showMessageDialog(null, "Please fill every Text (Server IP and Console ID needed aswell)");
				}

				
				else try {
	                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/playon", "root", "SIR123");
	                stmt = conn.createStatement();
	                stmt.executeUpdate("INSERT INTO games VALUES('" + Gname + "', '" + Gmodel + "', '" + Grate +  "', '" + Gage +  "', '" + ConID + "', '" + IP + "')" );
	                JOptionPane.showMessageDialog(null, "Record inserted...");
	                stmt.close();
	                conn.close();
	                } catch (SQLException e1) {
	                    JOptionPane.showMessageDialog(null, e1);
	            }
				
				
				
			}
		});
		btnAdd.setBounds(189, 246, 103, 43);
		contentPane.add(btnAdd);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String Gname= textGname.getText();
				String Gmodel= textGmodel.getText();
				String Gage= textGage.getText();
				String Grate = textGrate.getText();
				
				
				if (Grate.equalsIgnoreCase("") ||  Gname.equalsIgnoreCase("") ||  Gmodel.equalsIgnoreCase("") ||  Gage.equalsIgnoreCase("")) 
				{
					JOptionPane.showMessageDialog(null, "Please fill every Text for Game");
				}

				
				else try {
	                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/playon", "root", "SIR123");
	           
	                String query="select * from games where GName=?";
					PreparedStatement ptr=conn.prepareStatement(query);
					ptr.setString(1, Gname);
					ResultSet rs=ptr.executeQuery();
					
					
					int count = 0;
					while(rs.next()) 
					{
						count++;	
					}
					
					if (count == 1) 
					{ 
						stmt = conn.createStatement();
			            stmt.executeUpdate("UPDATE games SET Rating='" + Grate + "', Age='" + Gage +  "', Model='" + Gmodel +"' WHERE GName='" + Gname + "'");
			            JOptionPane.showMessageDialog(null, "updated...");
		                stmt.close();
					}
					else
					{
						JOptionPane.showMessageDialog(null, "There is no game with this name");
					}
	                
					ptr.close();
	                conn.close();
	                } catch (SQLException e1) {
	                    JOptionPane.showMessageDialog(null, e1);
	            }
				
				
				
				
			}
		});
		btnUpdate.setBounds(305, 246, 103, 43);
		contentPane.add(btnUpdate);
		
		JButton btnRemove = new JButton("Delete");
		btnRemove.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String Gname= textGname.getText();	
				
				if (Gname.equalsIgnoreCase("")) 
				{
					JOptionPane.showMessageDialog(null, "Please Enter the name of the game you want delete");
				}

				
				else try {
		
	                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/playon", "root", "SIR123");
	                
	                String query="select * from games where GName=?";
					PreparedStatement ptr=conn.prepareStatement(query);
					ptr.setString(1, Gname);
					ResultSet rs=ptr.executeQuery();
					
					
					int count = 0;
					while(rs.next()) 
					{
						count++;	
					}
					
					if (count == 1) 
					{ 
		                stmt = conn.createStatement();
		                stmt.executeUpdate("DELETE FROM games WHERE GName='" + Gname + "'");
		                JOptionPane.showMessageDialog(null, "deleted...");
		                stmt.close();
					}
					else
					{
						JOptionPane.showMessageDialog(null, "There is no game with this name");
					}
	                
					ptr.close();

	                conn.close();
	                } catch (SQLException e1) {
	                    JOptionPane.showMessageDialog(null, e1);
	            }
				
				
			}
		});
		btnRemove.setBounds(418, 246, 103, 43);
		contentPane.add(btnRemove);
		
		JLabel lblGmodel = new JLabel("Game Model: ");
		lblGmodel.setBounds(228, 187, 84, 14);
		contentPane.add(lblGmodel);
		
		textGmodel = new JTextField();
		textGmodel.setColumns(10);
		textGmodel.setBounds(322, 184, 86, 20);
		contentPane.add(textGmodel);
		
		textConsleID = new JTextField();
		textConsleID.setColumns(10);
		textConsleID.setBounds(132, 181, 86, 20);
		contentPane.add(textConsleID);
		
		textServerIP = new JTextField();
		textServerIP.setColumns(10);
		textServerIP.setBounds(132, 209, 86, 20);
		contentPane.add(textServerIP);
		
		JLabel lblServerIp = new JLabel("Server IP: ");
		lblServerIp.setBounds(74, 212, 122, 14);
		contentPane.add(lblServerIp);
		
		JLabel lblGameRating = new JLabel("Game Rating: ");
		lblGameRating.setBounds(228, 212, 84, 14);
		contentPane.add(lblGameRating);
		
		textGrate = new JTextField();
		textGrate.setColumns(10);
		textGrate.setBounds(322, 209, 86, 20);
		contentPane.add(textGrate);
		
		JButton btnNewButton = new JButton("Back");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		btnNewButton.setBounds(20, 369, 103, 34);
		contentPane.add(btnNewButton);
	}
}
