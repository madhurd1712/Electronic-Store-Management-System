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
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class CustomerAttached implements ActionListener {
	JFrame frame;
	JLabel titleLabel;
	JLabel background;
	JLabel adminLabel;
	JButton backButton;
	JTable table;
	JScrollPane scrollPane;
	String query;
	Font font;
	Font btnFont;
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
		
		String l="Customers Attached:";
		//AdminLabel
		adminLabel = new JLabel(l);
		adminLabel.setFont(btnFont);
		adminLabel.setForeground(Color.WHITE);
		adminLabel.setFont (adminLabel.getFont ().deriveFont (30.0f));
		Dimension sizelogin = adminLabel.getPreferredSize();
		adminLabel.setBounds(100, 180, sizelogin.width+100, sizelogin.height);


		//table
		query = "select * from customer where admin=0;";
		table = showTable(query);
		scrollPane=new JScrollPane(table);
		scrollPane.setBounds(150, 250, 700, 300);
		
		
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
//		background.add(submitButton);
		background.add(scrollPane);
		background.add(backButton);
		
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		frame.dispose();
		CustomerData cd = new CustomerData();
		cd.customerMenu();
	}
	
	public JTable showTable(String q)
	{
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/esm","root","md1712"); 
			Statement stmt=con.createStatement();
			String query = "select count(*) from customer;";
            ResultSet rs=stmt.executeQuery(query);
            rs.next();
            int k = rs.getInt(1);
            rs=stmt.executeQuery(q);
            String h[]={"Id","username","Name","Address","Phone No."};
            if(k<12)
            	k=12;
            String d[][]=new String[k][5];
            int i=0, j=0;
            while(rs.next()){
            	d[i][j++]=Integer.toString(rs.getInt(1));
                d[i][j++]=rs.getString(2);
                d[i][j++]=rs.getString(5);
                d[i][j++]=rs.getString(6);
                d[i][j++]=Long.toString(rs.getLong(7));
                i++;
                j=0;
            }
        
            table=new JTable(d,h);
            table.setRowHeight(25);
            //table.print();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
		}
		return table;
	}
}
