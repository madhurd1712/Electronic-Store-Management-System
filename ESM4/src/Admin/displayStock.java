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
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class displayStock implements ActionListener{
	JFrame frame;
	JLabel titleLabel;
	JLabel background;
	JLabel headLabel;
	JLabel productL;
	JComboBox<String> productCombo;
	JButton getStockbtn;
	JButton backButton;
	JTable acT,refT,ledT;
	JScrollPane ACscrollPane, REFscrollPane, LEDscrollPane;
	String query;
	Font btnFont, comboFont;
	Font font;
	String[] product= {"AC", "REF", "LED"};
	String pr="AC";
	
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

		String l="Display Stock";
		//signupLabel
		headLabel = new JLabel(l);
		headLabel.setFont(font);
		headLabel.setForeground(Color.WHITE);
		headLabel.setFont (headLabel.getFont ().deriveFont (30.0f));
		Dimension sizehead = headLabel.getPreferredSize();
		headLabel.setBounds(200, 180, sizehead.width+10, sizehead.height);
		
		btnFont = new Font(Font.SANS_SERIF, Font.ITALIC, 25);
		
		productL = new JLabel("Product:");
		productL.setFont(btnFont);
		productL.setForeground(Color.WHITE);
		productL.setFont(productL.getFont().deriveFont(20.0f));
		productL.setBounds(200, 230, 150, 40);
		
		comboFont = new Font(Font.SANS_SERIF, Font.ITALIC, 15);
		
		productCombo = new JComboBox<String>(product);
		productCombo.setFont(comboFont);
		productCombo.setBounds(350, 230, 200, 40);
		
		//GetModel  Button
		getStockbtn = new JButton("Get Stock");
		getStockbtn.setFont(comboFont);
		getStockbtn.setBackground(Color.BLACK);
		getStockbtn.setForeground(Color.WHITE);
		getStockbtn.setBorderPainted(false);
		getStockbtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		getStockbtn.setBounds(600, 230, 120, 40);
		getStockbtn.addActionListener(this);
		
		
		acT=showACTable();
		ACscrollPane = new JScrollPane(acT);
		ACscrollPane.setBounds(150, 300, 700, 300);

		refT=showREFTable();
		REFscrollPane = new JScrollPane(refT);
		REFscrollPane.setBounds(150, 300, 700, 300);

		ledT=showLEDTable();
		LEDscrollPane = new JScrollPane(ledT);
		LEDscrollPane.setBounds(150, 300, 700, 300);
		
		
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
		background.add(headLabel);
		background.add(productL);
		background.add(productCombo);
		background.add(getStockbtn);
		background.add(ACscrollPane);
		background.add(REFscrollPane);
		background.add(LEDscrollPane);
		background.add(backButton);

		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==getStockbtn)
		{
			pr = (String)productCombo.getItemAt(productCombo.getSelectedIndex());
			if(pr.equals("AC"))
			{
				ACscrollPane.setVisible(true);
				REFscrollPane.setVisible(false);
				LEDscrollPane.setVisible(false);
			}
			if(pr.equals("REF"))
			{
				ACscrollPane.setVisible(false);
				REFscrollPane.setVisible(true);
				LEDscrollPane.setVisible(false);
			}
			if(pr.equals("LED"))
			{
				ACscrollPane.setVisible(false);
				REFscrollPane.setVisible(false);
				LEDscrollPane.setVisible(true);
			}
		}
		if(e.getSource()==backButton)
		{
			frame.dispose();
			stock s = new stock();
			s.menu();
		}
	}
	public JTable showACTable()
	{
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/esm","root","md1712"); 
			Statement stmt=con.createStatement();
			String query = "select count(*) from AC;";
            ResultSet rs=stmt.executeQuery(query);
            rs.next();
            int k = rs.getInt(1);
            query = "Select * from AC;";
            rs=stmt.executeQuery(query);
            String h[]={"Brand","Model No.","Star Rating","Dealer Price","MRP","Warranty","Stock","Inverter","Capacity"};
            if(k<12)
            	k=12;
            String d[][]=new String[k][9];
            int i=0, j=0;
            while(rs.next()){
                d[i][j++]=rs.getString(1);
                d[i][j++]=rs.getString(2);
            	d[i][j++]=Integer.toString(rs.getInt(3));
                d[i][j++]=Long.toString(rs.getLong(4));
                d[i][j++]=Long.toString(rs.getLong(5));
                d[i][j++]=Integer.toString(rs.getInt(6));
                d[i][j++]=Integer.toString(rs.getInt(7));
                d[i][j++]=Integer.toString(rs.getInt(8));
                d[i][j++]=Float.toString(rs.getFloat(9));
                i++;
                j=0;
            }
        
            acT=new JTable(d,h);
            acT.setRowHeight(25);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return acT;
	}
	public JTable showREFTable()
	{
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/esm","root","md1712"); 
			Statement stmt=con.createStatement();
			String query = "select count(*) from AC;";
            ResultSet rs=stmt.executeQuery(query);
            rs.next();
            int k = rs.getInt(1);
            query = "Select * from REF;";
            rs=stmt.executeQuery(query);
            String h[]={"Brand","Model No.","Star Rating","Dealer Price","MRP","Warranty","Stock","Inverter","Capacity"};
            if(k<12)
            	k=12;
            String d[][]=new String[k][9];
            int i=0, j=0;
            while(rs.next()){
                d[i][j++]=rs.getString(1);
                d[i][j++]=rs.getString(2);
            	d[i][j++]=Integer.toString(rs.getInt(3));
                d[i][j++]=Long.toString(rs.getLong(4));
                d[i][j++]=Long.toString(rs.getLong(5));
                d[i][j++]=Integer.toString(rs.getInt(6));
                d[i][j++]=Integer.toString(rs.getInt(7));
                d[i][j++]=Integer.toString(rs.getInt(8));
                d[i][j++]=Float.toString(rs.getFloat(9));
                i++;
                j=0;
            }
        
            acT=new JTable(d,h);
            acT.setRowHeight(25);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return acT;
	}
	public JTable showLEDTable()
	{
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/esm","root","md1712"); 
			Statement stmt=con.createStatement();
			String query = "select count(*) from AC;";
            ResultSet rs=stmt.executeQuery(query);
            rs.next();
            int k = rs.getInt(1);
            query = "Select * from LED;";
            rs=stmt.executeQuery(query);
            String h[]={"Brand","Model No.","Star Rating","Dealer Price","MRP","Size","Warranty","Stock","Description"};
            if(k<12)
            	k=12;
            String d[][]=new String[k][9];
            int i=0, j=0;
            while(rs.next()){
                d[i][j++]=rs.getString(1);
                d[i][j++]=rs.getString(2);
            	d[i][j++]=Integer.toString(rs.getInt(3));
                d[i][j++]=Long.toString(rs.getLong(4));
                d[i][j++]=Long.toString(rs.getLong(5));
                d[i][j++]=Float.toString(rs.getFloat(6));
                d[i][j++]=Integer.toString(rs.getInt(7));
                d[i][j++]=Integer.toString(rs.getInt(8));
                d[i][j++]=rs.getString(9);
                i++;
                j=0;
            }
        
            acT=new JTable(d,h);
            acT.setRowHeight(25);
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return acT;
	}
}
