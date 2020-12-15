package Admin;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class searchCustomer implements ActionListener {
	JFrame frame;
	JLabel titleLabel;
	JLabel background;
	JLabel adminLabel;
	JButton searchBtn;
	JButton backButton;
	JLabel searchL;
	JTextField searchTxt;
	JTable table;
	JScrollPane scrollPane;
	String query;
	Font font;
	Font btnFont;
	int id;
	public void display()
	{
		frame = new JFrame();
		frame.setSize(1000, 700);
		frame.setTitle("Electronic Store Management System");

		//background
		background = new JLabel();
		background.setIcon(new ImageIcon(new ImageIcon("C:\\Users\\hp\\Downloads\\esm\\bg6.jpg").getImage().getScaledInstance(1000, 700, Image.SCALE_DEFAULT)));
		//background.setIcon(new ImageIcon("C:\\Users\\hp\\Desktop\\medical\\bg.jpg"));
		Dimension size1 = background.getPreferredSize();
		background.setBounds(0, 0, size1.width, size1.height);
		
		//title
		titleLabel = new JLabel("Electronic Store Management System");
		font = new Font(Font.SERIF ,Font.BOLD, 30);
		titleLabel.setFont(font);
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setFont (titleLabel.getFont ().deriveFont (45.0f));
		Dimension sizeTitle = titleLabel.getPreferredSize();
		titleLabel.setBounds(115, 80, sizeTitle.width+10, sizeTitle.height);

		btnFont = new Font(Font.SANS_SERIF, Font.PLAIN, 20);
		
		String l="Search Customer Data:";
		//AdminLabel
		adminLabel = new JLabel(l);
		adminLabel.setFont(btnFont);
		adminLabel.setForeground(Color.WHITE);
		adminLabel.setFont (adminLabel.getFont ().deriveFont (30.0f));
		Dimension sizelogin = adminLabel.getPreferredSize();
		adminLabel.setBounds(100, 180, sizelogin.width+100, sizelogin.height);
		
		searchL = new JLabel("Username: ");
		searchL.setFont(btnFont);
		searchL.setForeground(Color.WHITE);
		searchL.setFont(searchL.getFont().deriveFont(20.0f));
		searchL.setBounds(100, 250, 150, 40);
		searchTxt = new JTextField();
		searchTxt.setBounds(250, 255, 200, 30);
		searchTxt.setFont(btnFont);

		searchBtn = new JButton("Search");
		searchBtn.setFont(btnFont);
		searchBtn.setBackground(Color.BLACK);
		searchBtn.setForeground(Color.WHITE);
		searchBtn.setBorderPainted(false);
		searchBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		searchBtn.setBounds(500, 255, 100, 30);
		searchBtn.addActionListener(this);
		
		
		//table
		String q = "select * from purchasedproduct where cust_id=0;";
		table = showTable(q);
		scrollPane=new JScrollPane(table);
		scrollPane.setBounds(150, 300, 700, 300);
		
		
		Font backFont = new Font(Font.SANS_SERIF, Font.ITALIC, 15);
		//Back Button
		backButton = new JButton("BACK");
		backButton.setFont(backFont);
		backButton.setBackground(Color.BLACK);
		backButton.setForeground(Color.WHITE);
		backButton.setBorderPainted(false);
		backButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		backButton.setBounds(20, 580, 100, 30);
		backButton.addActionListener(this);

		frame.add(background);
		background.add(titleLabel);
		background.add(adminLabel);
		background.add(searchL);
		background.add(searchTxt);
		background.add(searchBtn);
		background.add(scrollPane);
		background.add(backButton);
		
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==searchBtn)
		{
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/esm","root","md1712"); 
				Statement stmt=con.createStatement();
				String query = "select cust_id from customer where email= '"+searchTxt.getText()+"';";
	            ResultSet rs=stmt.executeQuery(query);
	            rs.next();
	            id = rs.getInt(1);
	            query = "select * from purchasedproduct where cust_id= '"+id+"';";
	            table = showTable(query);
	            scrollPane.setVisible(false);
	            scrollPane = new JScrollPane(table);
	    		scrollPane.setBounds(150, 300, 700, 300);
	    		scrollPane.setVisible(true);
	    		background.add(scrollPane);
			}
			catch (Exception e1) {
				// TODO: handle exception
				System.out.println(e1);
				JOptionPane.showMessageDialog(frame, "No Details Found!!");
			}
		}
		else if(e.getSource()==backButton)
		{
			frame.dispose();
			CustomerData cd = new CustomerData();
			cd.customerMenu();
		}
	}

	public JTable showTable(String q)
	{
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/esm","root","md1712"); 
			Statement stmt=con.createStatement();
			String query = "select count(*) from purchasedproduct where cust_id='"+id+"';";
            ResultSet rs=stmt.executeQuery(query);
            rs.next();
            int k = rs.getInt(1);
            rs=stmt.executeQuery(q);
            String h[]={"Id","Product","Model No.","Quantity","Date Purchased"};
            if(k<12)
            	k=12;
            String d[][]=new String[k][5];
            int i=0, j=0;
            while(rs.next()){
            	d[i][j++]=Integer.toString(rs.getInt(1));
                d[i][j++]=rs.getString(2);
                d[i][j++]=rs.getString(3);
                d[i][j++]=Integer.toString(rs.getInt(4));
                d[i][j++]=rs.getString(5);
                i++;
                j=0;
            }
        
            table=new JTable(d,h);
            table.setRowHeight(25);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return table;
	}
}
