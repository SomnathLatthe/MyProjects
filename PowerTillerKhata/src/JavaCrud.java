import java.awt.EventQueue;
import java.sql.*;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.proteanit.sql.DbUtils;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class JavaCrud {

	private JFrame frame;
	private JTextField txtName;
	private JTextField txtArea;
	private JTextField txtTotal;
	private JTable table;
	private JTextField txtSearch;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JavaCrud window = new JavaCrud();
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
	public JavaCrud() {
		initialize();
		Connect();
		table_load();
	}
	
	
	Connection con;
	PreparedStatement pst;
	ResultSet rs;
	private JTable table_1;
	
 
	 public void Connect()
	    {
	        try {
	            Class.forName("com.mysql.cj.jdbc.Driver");
	            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydata", "root","manager");
	        }
	        catch (ClassNotFoundException ex) 
	        {
	          ex.printStackTrace();
	        }
	        catch (SQLException ex) 
	        {
	        	   ex.printStackTrace();
	        }
 
	    }
	 
	 public void table_load()
	    {
	    	try 
	    	{
		    pst = con.prepareStatement("select * from khata");
		    rs = pst.executeQuery();
		    table_1.setModel(DbUtils.resultSetToTableModel(rs));
		} 
	    	catch (SQLException e) 
	    	 {
	    		e.printStackTrace();
		  } 
	    }
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 729, 409);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Record");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD | Font.ITALIC, 25));
		lblNewLabel.setBounds(300, 0, 107, 42);
		frame.getContentPane().add(lblNewLabel);
		
		JPanel panel = new JPanel();
		panel.setBorder(new TitledBorder(null, "Registration", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel.setBounds(12, 40, 321, 178);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("Name");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1.setBounds(26, 35, 88, 35);
		panel.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Area");
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(26, 81, 88, 35);
		panel.add(lblNewLabel_1_1);
		
		JLabel lblNewLabel_1_1_1 = new JLabel("Total");
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_1_1.setBounds(26, 128, 88, 35);
		panel.add(lblNewLabel_1_1_1);
		
		txtName = new JTextField();
		txtName.setBounds(104, 35, 184, 28);
		panel.add(txtName);
		txtName.setColumns(10);
		
		txtArea = new JTextField();
		txtArea.setColumns(10);
		txtArea.setBounds(104, 83, 184, 28);
		panel.add(txtArea);
		
		txtTotal = new JTextField();
		txtTotal.setColumns(10);
		txtTotal.setBounds(104, 130, 184, 28);
		panel.add(txtTotal);
		
		JButton btnNewButton = new JButton("Save");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				//String name,area,total;
				String name,total;
				name = txtName.getText();
				float field = Float.parseFloat(txtArea.getText());
				total = txtTotal.getText();
				java.util.Date date=new java.util.Date();
				java.sql.Date ondate=new java.sql.Date(date.getTime());
							
				 try {
					pst = con.prepareStatement("insert into khata(ondate,name,field,total)values(?,?,?,?)");
					pst.setDate(1, ondate);
					pst.setString(2, name);
					pst.setFloat(3, field);
					pst.setString(4, total);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Addedddd!!!!!");
					table_load();
						           
					txtName.setText("");
					txtArea.setText("");
					txtTotal.setText("");
					txtName.requestFocus();
				   }
			 
				catch (SQLException e1) 
			        {
									
						e1.printStackTrace();
			        }
			}
		});
		btnNewButton.setBounds(22, 229, 89, 37);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
		});
		btnExit.setBounds(132, 229, 89, 37);
		frame.getContentPane().add(btnExit);
		
		JButton btnClear = new JButton("Clear");
		btnClear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				txtName.setText("");
				txtArea.setText("");
				txtTotal.setText("");
				txtName.requestFocus();
			}
		});
		btnClear.setBounds(244, 229, 89, 37);
		frame.getContentPane().add(btnClear);
		
		table = new JTable();
		table.setBounds(396, 201, 89, -93);
		frame.getContentPane().add(table);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBorder(new TitledBorder(null, "Search", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		panel_1.setBounds(12, 278, 321, 81);
		frame.getContentPane().add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNewLabel_1_2 = new JLabel("Name");
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(10, 21, 57, 28);
		panel_1.add(lblNewLabel_1_2);
		
		txtSearch = new JTextField();
		txtSearch.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				
				try {
			          
		            String name = txtSearch.getText();

		                pst = con.prepareStatement("select field,total from khata where name = ?");
		                pst.setString(1, name);
		                ResultSet rs = pst.executeQuery();

		            if(rs.next()==true)
		            {
//		            	Date dateObj = rs.getDate(1);
//		                String ondate = dateObj.toString();
		                Float field = rs.getFloat(1);
		                String area=Float.toString(field);
		                String total = rs.getString(2);
		                
		                txtName.setText(name);
		                txtArea.setText(area);
		                txtTotal.setText(total);    
		            }   
		            else
		            {
		            	txtName.setText("");
		            	txtArea.setText("");
		                txtTotal.setText("");   
		            }  
		        } 
			
			 catch (SQLException ex) {
		           
		        }
				
			}
		});
		txtSearch.setColumns(10);
		txtSearch.setBounds(107, 21, 184, 28);
		panel_1.add(txtSearch);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(343, 51, 360, 215);
		frame.getContentPane().add(scrollPane);
		
		table_1 = new JTable();
		scrollPane.setViewportView(table_1);
		
		JButton btnUpdate = new JButton("Update");
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name,total;
				name = txtSearch.getText();
				float field = Float.parseFloat(txtArea.getText());
				total = txtTotal.getText();
				java.util.Date date=new java.util.Date();
				java.sql.Date ondate=new java.sql.Date(date.getTime());
							
				 try {
					pst = con.prepareStatement("update khata set ondate=?,field=?,total=? where name=?");
					pst.setDate(1, ondate);
					pst.setFloat(2, field);
					pst.setString(3, total);
					pst.setString(4, name);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Updated!!!!!");
					table_load();
						           
					txtName.setText("");
					txtArea.setText("");
					txtTotal.setText("");
					txtName.requestFocus();
				   }
			 
				catch (SQLException e1) 
			        {
									
						e1.printStackTrace();
			        }
				
			}
		});
		btnUpdate.setBounds(343, 301, 89, 37);
		frame.getContentPane().add(btnUpdate);
		
		JButton btnDelete = new JButton("Delete");
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String name;
				name = txtSearch.getText();
											
				 try {
					pst = con.prepareStatement("delete from khata where name=?");
					
					pst.setString(1, name);
					pst.executeUpdate();
					JOptionPane.showMessageDialog(null, "Record Deleted!!!!!");
					table_load();
						           
					txtName.setText("");
					txtArea.setText("");
					txtTotal.setText("");
					txtName.requestFocus();
				   }
			 
				catch (SQLException e1) 
			        {
									
						e1.printStackTrace();
			        }
				
			}
		});
		btnDelete.setBounds(459, 301, 89, 37);
		frame.getContentPane().add(btnDelete);
		
			
		
		
		
	}
}
