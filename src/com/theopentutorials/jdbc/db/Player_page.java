package com.theopentutorials.jdbc.db;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.table.DefaultTableModel;

import com.mysql.cj.xdevapi.Statement;
import javax.swing.JTable;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JLabel;



public class Player_page extends JFrame {

    private JPanel contentPane;
    private Connection conn;
    String Gname;
    

    
    Connection connection = null;
    PreparedStatement pst ;
    java.sql.Statement stmt;
    ResultSet rs;
    private JTable table;
    private JButton btnNewButton_1;
    
    
    
    
    
    
    
 
    
    private void close() {
		this.setVisible(false);
	}
    
    private boolean viewed = false;
    private JButton btnNewButton_2;
    private JTextField textServerIP;
    private JLabel lblNewLabel;
    private JTextField textGname;
    
    private void updateTable() {
        String sql = "Select * from Games";
        try {
            pst = connection.prepareStatement(sql);
            rs = pst.executeQuery();
            //table.setModel(DbUtils.resultSetToTableModel(rs));            
            
            
        } catch (Exception e) {
            
            e.printStackTrace();
        }
    }
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Player_page frame = new Player_page();
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
    public Player_page() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 1128, 529);
        contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);
        contentPane.setLayout(null);
        
        JButton btnNewButton = new JButton("View All Games");
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(viewed != true) {
                	viewed = true;
                	 try {
                         conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/playon", "root", "SIR123");
                         stmt = conn.createStatement();
                         String sql = "select * from games";
                         ResultSet rs = stmt.executeQuery(sql);
                         
                         while(rs.next()) {
                             
                             String Gname = rs.getString("Gname");
                             String Age = rs.getString("Age");
                             String Rating = rs.getString("Rating");
                             
                             String tbData[] = {Gname,Age,Rating};
                             DefaultTableModel tblModel = (DefaultTableModel)table.getModel();
                             
                             tblModel.addRow(tbData);
                         }
                         } catch (SQLException e1) {
                             JOptionPane.showMessageDialog(null, e1);
                     }
                }
                else {
                	// if already viewed
                	JOptionPane.showMessageDialog(null, "all games already viewed");
                }
               
            }
        });
        btnNewButton.setBounds(88, 315, 200, 50);
        contentPane.add(btnNewButton);
        
        table = new JTable();
        table.setModel(new DefaultTableModel(
        	new Object[][] {
        		{"Game Name:", "Age:", "Rating"},
        	},
        	new String[] {
        		"Game Name:", "Age", "Rating"
        	}
        ));
        table.setBounds(298, 112, 741, 253);
        contentPane.add(table);
        
        btnNewButton_1 = new JButton("Back");
        btnNewButton_1.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		close();
        	}
        });
        btnNewButton_1.setBounds(10, 428, 89, 23);
        contentPane.add(btnNewButton_1);
        
        btnNewButton_2 = new JButton("Selected IP");
        btnNewButton_2.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		String IP = textServerIP.getText();
        		
        		try {
                    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/playon", "root", "SIR123");
                    stmt = conn.createStatement();
                    String sql = "select * from Games WHERE Console_Server_IPServer='" + IP + "'";
                    ResultSet rs = stmt.executeQuery(sql);
                    
                    String Selected[] = {"From IP: " + IP};
                    DefaultTableModel StblModel = (DefaultTableModel)table.getModel();

                    StblModel.addRow(Selected);

                    while(rs.next()) {

                        String Gname = rs.getString("Gname");
                        String Age = rs.getString("Age");
                        String Rating = rs.getString("Rating");
                        
                        String tbData[] = {Gname,Age,Rating};
                        DefaultTableModel tblModel = (DefaultTableModel)table.getModel();
                        tblModel.addRow(tbData);
                    }
                    } catch (SQLException e1) {
                        JOptionPane.showMessageDialog(null, e1);
                }
        		
        		
        		
        		
        	}
        });
        btnNewButton_2.setBounds(88, 254, 200, 50);
        contentPane.add(btnNewButton_2);
        
        textServerIP = new JTextField();
        textServerIP.setBounds(171, 181, 117, 42);
        contentPane.add(textServerIP);
        textServerIP.setColumns(10);
        
        lblNewLabel = new JLabel("Select IP: ");
        lblNewLabel.setBounds(93, 195, 68, 14);
        contentPane.add(lblNewLabel);
        
        textGname = new JTextField();
        textGname.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		
        		
        		
        		String SelectedGname = textGname.getText();
        		
        		try {
                    conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/playon", "root", "SIR123");
                    stmt = conn.createStatement();
                    String sql = "select * from Games WHERE Gname='" + SelectedGname + "'";
                    ResultSet rs = stmt.executeQuery(sql);
                    
                    String Selected[] = {"From your Search: " + SelectedGname};
                    DefaultTableModel StblModel = (DefaultTableModel)table.getModel();

                    StblModel.addRow(Selected);

                    while(rs.next()) {

                        String Gname = rs.getString("Gname");
                        String Age = rs.getString("Age");
                        String Rating = rs.getString("Rating");
                        
                        String tbData[] = {Gname,Age,Rating};
                        DefaultTableModel tblModel = (DefaultTableModel)table.getModel();
                        tblModel.addRow(tbData);
                    }
                    } catch (SQLException e1) {
                        JOptionPane.showMessageDialog(null, e1);
                }
        		
        		
        	}
        });
        textGname.setColumns(10);
        textGname.setBounds(923, 66, 117, 35);
        contentPane.add(textGname);
        
        JLabel lblSelectGameName = new JLabel("Select Game Name: ");
        lblSelectGameName.setBounds(796, 76, 117, 14);
        contentPane.add(lblSelectGameName);
    }
}