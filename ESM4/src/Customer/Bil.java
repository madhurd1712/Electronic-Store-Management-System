package Customer;

import java.awt.Dimension;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class Bil {
	JFrame frame;
	JLabel titleLabel;
	JLabel headLabel;
	JLabel amtl;
	JLabel x;
	JTable table;
	JScrollPane scrollPane;
	String query;
	Font btnFont;
	Font font;
	int Amt;
	public void go()
	{
		frame = new JFrame();
		frame.setSize(500, 600);
		frame.setTitle("Bill");
		
		//title
		titleLabel = new JLabel("Electronic Store Management System");
		font = new Font(Font.SERIF ,Font.BOLD, 30);
		titleLabel.setFont(font);
		titleLabel.setFont (titleLabel.getFont ().deriveFont (28.0f));
		titleLabel.setBounds(15, 40, 470, 40);

		String l="Receipt";
		//signupLabel
		headLabel = new JLabel(l);
		headLabel.setFont(font);
		headLabel.setFont (headLabel.getFont ().deriveFont (24.0f));
		Dimension sizehead = headLabel.getPreferredSize();
		headLabel.setBounds(200, 100, sizehead.width+10, sizehead.height);
		
		btnFont = new Font(Font.SANS_SERIF, Font.ITALIC, 25);
		

		//table
		query = "select * from cart;";
		table = showTable(query);
		scrollPane=new JScrollPane(table);
		scrollPane.setBounds(50, 180, 400, 200);

		
		amtl = new JLabel("Amount = " + Amt);
		amtl.setFont(btnFont);
		amtl.setFont(amtl.getFont().deriveFont(20.0f));
		amtl.setBounds(50, 400, 300, 40);
		
		x = new JLabel(".");
		x.setFont(font);
		x.setBounds(50, 400, 300, 40);
		
		frame.add(titleLabel);	
		frame.add(headLabel);
		frame.add(scrollPane);
		frame.add(amtl);
		frame.add(x);
		x.setVisible(false);

		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	public JTable showTable(String q)
	{
		try{  
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/esm","root","md1712"); 
			Statement stmt=con.createStatement();
			String query = "select count(*) from cart;";
            ResultSet rs=stmt.executeQuery(query);
            rs.next();
            int k = rs.getInt(1);
            rs=stmt.executeQuery(q);
            String h[]={"Product","Model No.","Quantity","Amount per unit"};
            if(k<8)
            	k=8;
            String d[][]=new String[k][4];
            int i=0, j=0;
            Amt=0;
            while(rs.next()){
                d[i][j++]=rs.getString(1);
                d[i][j++]=rs.getString(2);
            	d[i][j++]=Integer.toString(rs.getInt(3));
                d[i][j++]=Long.toString(rs.getLong(4));
                i++;
                j=0;
                Amt+=rs.getInt(3)*rs.getLong(4);
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
