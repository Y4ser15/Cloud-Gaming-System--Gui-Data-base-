package com.theopentutorials.jdbc.db;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;

public class Adminstrators extends JFrame {

	private JPanel contentPane;
	private JTextField txtServerIp;
	private JTextField txtRegion;
	private JTextField txtAdress;
	private JTextField txtMaxplayers;
	private JTextField txtConsoleId;
	private JTextField txtGpu;
	private JTextField txtCpu;
	private JTextField txtRam;
	private JTextField txtType;
	
	
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
					Adminstrators frame = new Adminstrators();
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
	public Adminstrators() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 855, 509);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtServerIp = new JTextField();
		txtServerIp.setBounds(251, 71, 86, 20);
		contentPane.add(txtServerIp);
		txtServerIp.setColumns(10);
		
		txtRegion = new JTextField();
		txtRegion.setBounds(251, 102, 86, 20);
		contentPane.add(txtRegion);
		txtRegion.setColumns(10);
		
		txtAdress = new JTextField();
		txtAdress.setColumns(10);
		txtAdress.setBounds(251, 133, 86, 20);
		contentPane.add(txtAdress);
		
		txtMaxplayers = new JTextField();
		txtMaxplayers.setColumns(10);
		txtMaxplayers.setBounds(251, 164, 86, 20);
		contentPane.add(txtMaxplayers);
		
		txtConsoleId = new JTextField();
		txtConsoleId.setColumns(10);
		txtConsoleId.setBounds(426, 71, 86, 20);
		contentPane.add(txtConsoleId);
		
		txtGpu = new JTextField();
		txtGpu.setColumns(10);
		txtGpu.setBounds(426, 102, 86, 20);
		contentPane.add(txtGpu);
		
		txtCpu = new JTextField();
		txtCpu.setColumns(10);
		txtCpu.setBounds(426, 133, 86, 20);
		contentPane.add(txtCpu);
		
		txtRam = new JTextField();
		txtRam.setColumns(10);
		txtRam.setBounds(426, 164, 86, 20);
		contentPane.add(txtRam);
		
		txtType = new JTextField();
		txtType.setColumns(10);
		txtType.setBounds(426, 195, 86, 20);
		contentPane.add(txtType);
		
		JButton btnNewButton = new JButton("Add new Server");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				
				
				
				//int IP = Integer.parseInt(txtServerIp.getText());
				//int MaxP = Integer.parseInt(txtMaxplayers.getText());
				String IP = txtServerIp.getText();
				String MaxP = txtMaxplayers.getText();
				String Region = txtRegion.getText();
				String Address = txtAdress.getText();
				
				if (IP.equalsIgnoreCase("") || MaxP.equalsIgnoreCase("") ||  Region.equalsIgnoreCase("") ||  Address.equalsIgnoreCase("")) 
				{
					JOptionPane.showMessageDialog(null, "Please Enter IP, Region, Address and MaxPlayers");
				}

				
				else try {
	                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/playon", "root", "SIR123");
	                stmt = conn.createStatement();
	                stmt.executeUpdate("INSERT INTO SERVER VALUES('" + IP + "', '" + Region + "', '" + Address +  "', '" + MaxP + "')" );
	                JOptionPane.showMessageDialog(null, "inserted Seccussfully...");
	                stmt.close();
	                conn.close();
	                } catch (SQLException e1) {
	                    JOptionPane.showMessageDialog(null, e1);
	            }
			
				
			}
		});
		btnNewButton.setBounds(224, 240, 150, 46);
		contentPane.add(btnNewButton);
		
		JButton btnAddNewConsole = new JButton("Add new Console");
		btnAddNewConsole.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//int IP = Integer.parseInt(txtServerIp.getText());
				//int ConsleID = Integer.parseInt(txtConsoleId.getText());
				String IP = txtServerIp.getText();
				String ConsleID = txtConsoleId.getText();
				String GPU = txtGpu.getText();
				String CPU = txtCpu.getText();
				String RAM = txtRam.getText();
				String Type = txtType.getText();
				
				if (IP.equalsIgnoreCase("") || ConsleID.equalsIgnoreCase("") ||  GPU.equalsIgnoreCase("") ||  CPU.equalsIgnoreCase("") ||  RAM.equalsIgnoreCase("") ||  Type.equalsIgnoreCase("")) 
				{
					JOptionPane.showMessageDialog(null, "Please Enter IP, Consle ID, GPU, CPU, RAM and Type");
				}
				
				else try {
	                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/playon", "root", "SIR123");
	                stmt = conn.createStatement();
	                stmt.executeUpdate("INSERT INTO console VALUES('" + ConsleID + "', '" + IP  + "', '" + GPU + "', '" + CPU +  "', '" + RAM + "', '" + Type + "')" );
	                JOptionPane.showMessageDialog(null, "inserted Seccussfully...");
	                stmt.close();
	                conn.close();
	                } catch (SQLException e1) {
	                    JOptionPane.showMessageDialog(null, e1);
	            }
				
				
			}
		});
		btnAddNewConsole.setBounds(397, 240, 150, 46);
		contentPane.add(btnAddNewConsole);
		
		JLabel lblNewLabel = new JLabel("Server IP");
		lblNewLabel.setBounds(180, 74, 61, 14);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Region");
		lblNewLabel_1.setBounds(180, 105, 46, 14);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Address");
		lblNewLabel_2.setBounds(180, 136, 61, 14);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Maxplayers");
		lblNewLabel_3.setBounds(180, 167, 73, 14);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Console id");
		lblNewLabel_4.setBounds(357, 74, 84, 14);
		contentPane.add(lblNewLabel_4);
		
		JLabel lblNewLabel_5 = new JLabel("GPU");
		lblNewLabel_5.setBounds(370, 105, 46, 14);
		contentPane.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("CPU");
		lblNewLabel_6.setBounds(370, 136, 46, 14);
		contentPane.add(lblNewLabel_6);
		
		JLabel lblNewLabel_7 = new JLabel("RAM");
		lblNewLabel_7.setBounds(370, 167, 46, 14);
		contentPane.add(lblNewLabel_7);
		
		JLabel lblNewLabel_8 = new JLabel("Type");
		lblNewLabel_8.setBounds(370, 198, 46, 14);
		contentPane.add(lblNewLabel_8);
		
		JButton btnNewButton_1 = new JButton("Back");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				close();
			}
		});
		btnNewButton_1.setBounds(10, 379, 99, 46);
		contentPane.add(btnNewButton_1);
		
		JButton btnEditGames = new JButton("Edit Games");
		btnEditGames.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Edit_Games_page EditGames = new Edit_Games_page();
				EditGames.setVisible(true);
			}
		});
		btnEditGames.setBounds(274, 297, 238, 46);
		contentPane.add(btnEditGames);
	}
}
