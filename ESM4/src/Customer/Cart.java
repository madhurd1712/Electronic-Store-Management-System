package Customer;

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
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Cart implements ActionListener{
	JFrame frame;
	JLabel titleLabel;
	JLabel background;
	JLabel headLabel;
	JLabel productL;
	JLabel modelL;
	JComboBox<String> productCombo;
	JComboBox<String> ac,ref,led;
	JButton getModelsbtn;
	JButton addBtn;
	JButton billBtn;
	JButton backButton;
	JLabel qtyL;
	JTextField qtyTxt;
	JTable table;
	JScrollPane scrollPane;
	String query;
	Font btnFont, comboFont;
	Font font;
	String[] product= {"AC", "REF", "LED"};
	String pr="AC";
	int id;
	String Name;
	public Cart(int x, String n)
	{
		id=x;
		Name=n;
	}
	
	public void opt()
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

		String l="Cart";
		//signupLabel
		headLabel = new JLabel(l);
		headLabel.setFont(font);
		headLabel.setForeground(Color.WHITE);
		headLabel.setFont (headLabel.getFont ().deriveFont (35.0f));
		Dimension sizehead = headLabel.getPreferredSize();
		headLabel.setBounds(200, 150, sizehead.width+10, sizehead.height);
		
		btnFont = new Font(Font.SANS_SERIF, Font.ITALIC, 25);
		
		productL = new JLabel("Product:");
		productL.setFont(btnFont);
		productL.setForeground(Color.WHITE);
		productL.setFont(productL.getFont().deriveFont(20.0f));
		productL.setBounds(200, 200, 150, 40);
		
		comboFont = new Font(Font.SANS_SERIF, Font.ITALIC, 15);
		
		productCombo = new JComboBox<String>(product);
		productCombo.setFont(comboFont);
		productCombo.setBounds(350, 200, 200, 40);
		
		//GetModel  Button
		getModelsbtn = new JButton("Get Models");
		getModelsbtn.setFont(comboFont);
		getModelsbtn.setBackground(Color.BLACK);
		getModelsbtn.setForeground(Color.WHITE);
		getModelsbtn.setBorderPainted(false);
		getModelsbtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		getModelsbtn.setBounds(600, 200, 120, 40);
		getModelsbtn.addActionListener(this);
		
		modelL = new JLabel("Model Number:");
		modelL.setFont(btnFont);
		modelL.setForeground(Color.WHITE);
		modelL.setFont(modelL.getFont().deriveFont(20.0f));
		modelL.setBounds(200, 260, 300, 40);
		
		ac = new JComboBox<String>();
		ac = getModels("AC");
		ac.setFont(comboFont);
		ac.setBounds(350, 260, 200, 40);
		
		ref = new JComboBox<String>();
		ref = getModels("REF");
		ref.setFont(comboFont);
		ref.setBounds(350, 260, 200, 40);
		
		led = new JComboBox<String>();
		led = getModels("LED");
		led.setFont(comboFont);
		led.setBounds(350, 260, 200, 40);

		//Submit Button
		addBtn = new JButton("Add to Cart");
		addBtn.setFont(comboFont);
		addBtn.setBackground(Color.BLACK);
		addBtn.setForeground(Color.WHITE);
		addBtn.setBorderPainted(false);
		addBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		addBtn.setBounds(600, 260, 120, 40);
		addBtn.addActionListener(this);

		qtyL = new JLabel("Quantity:");
		qtyL.setFont(btnFont);
		qtyL.setForeground(Color.WHITE);
		qtyL.setFont(productL.getFont().deriveFont(20.0f));
		qtyL.setBounds(200, 320, 150, 40);
		qtyTxt = new JTextField();
		qtyTxt.setFont(btnFont);
		qtyTxt.setBounds(350, 325, 150, 40);

		//table
		query = "select * from cart;";
		table = showTable(query);
		scrollPane=new JScrollPane(table);
		scrollPane.setBounds(150, 380, 700, 200);
		
		
		//Submit Button
		billBtn = new JButton("Genertae Bill");
		billBtn.setFont(btnFont);
		billBtn.setBackground(Color.BLACK);
		billBtn.setForeground(Color.WHITE);
		billBtn.setBorderPainted(false);
		billBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		billBtn.setBounds(300, 600, 200, 60);
		billBtn.addActionListener(this);
		
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
		background.add(getModelsbtn);
		background.add(modelL);
		background.add(ac);
		background.add(ref);
		background.add(led);
		background.add(scrollPane);
		background.add(addBtn);
		background.add(qtyL);
		background.add(qtyTxt);		
		background.add(billBtn);
		background.add(backButton);

		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==getModelsbtn)
		{
			pr = (String)productCombo.getItemAt(productCombo.getSelectedIndex());
			if(pr.equals("AC"))
			{
				ac.setVisible(true);
				ref.setVisible(false);
				led.setVisible(false);
			}
			if(pr.equals("REF"))
			{
				ref.setVisible(true);
				ac.setVisible(false);
				led.setVisible(false);
			}
			if(pr.equals("LED"))
			{
				led.setVisible(true);
				ref.setVisible(false);
				ac.setVisible(false);
			}
		}
		if(e.getSource()==addBtn)
		{
			pr = (String)productCombo.getItemAt(productCombo.getSelectedIndex());
			String mdl="";
			if(pr.equals("AC"))
				mdl = (String)ac.getItemAt(ac.getSelectedIndex());
			else if(pr.equals("REF"))
				mdl = (String)ref.getItemAt(ref.getSelectedIndex());
			else if(pr.equals("LED"))
				mdl = (String)led.getItemAt(led.getSelectedIndex());
			
			try{
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/esm","root","md1712"); 
				Statement stmt=con.createStatement();
				int qty = Integer.parseInt(qtyTxt.getText());
				String query = "select mrp from "+pr+" where model_no = '" + mdl+"';";
	            ResultSet rs=stmt.executeQuery(query);
	            rs.next();
	            String q = "Insert into cart Values('"+pr+"', '"+mdl+"', '"+qty+"', '"+rs.getLong(1)+"');";
	            stmt.executeUpdate(q);
	            JOptionPane.showMessageDialog(frame, "Product added to cart!!");
	            q = "select * from cart;";
	            table = showTable(q);
	            scrollPane.setVisible(false);
	            scrollPane = new JScrollPane(table);
	    		scrollPane.setBounds(150, 380, 700, 200);
	    		scrollPane.setVisible(true);
	    		background.add(scrollPane);
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(frame, "Oops, error occurred!!");
				ex.printStackTrace();
			}
		}
		if(e.getSource()==billBtn)
		{
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/esm","root","md1712"); 
				Statement stmt=con.createStatement();
				Statement stmt1=con.createStatement();
				String dat = "Select SYSDATE()";
				ResultSet rs = stmt.executeQuery(dat);
				rs.next();
				dat=rs.getString(1);
				rs.close();
				String q = "Select * from cart;";
				rs = stmt.executeQuery(q);
				while(rs.next())
				{
					String te="Insert into purchasedproduct Values('"+id+"', '"+rs.getString(1)+"', '"+rs.getString(2)+"', '"+rs.getInt(3)+"', '"+dat+"');";
					stmt1.executeUpdate(te);
				}
				
				Bil b = new Bil();
				b.go();
				
				q = "TRUNCATE TABLE cart;";
				stmt.executeUpdate(q);
			}
			catch (Exception e1) {
				// TODO: handle exception
				JOptionPane.showMessageDialog(frame, "Oops, error occurred!!");
				e1.printStackTrace();
			}
			
		}
		if(e.getSource()==backButton)
		{
			frame.dispose();
			CustWindow cw = new CustWindow(id,Name);
			cw.custMenu();
		}
	}

	public JComboBox<String> getModels(String pro)
	{
		JComboBox<String> jcb = new JComboBox<String>();
		try{
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/esm","root","md1712"); 
			Statement stmt=con.createStatement();
			String query = "select model_no from "+pro+";";
            ResultSet rs=stmt.executeQuery(query);
            while(rs.next())
            {
            	jcb.addItem(rs.getString(1));
            }
		}
		catch(Exception ex)
		{
			JOptionPane.showMessageDialog(frame, "Oops, error occurred!!");
			ex.printStackTrace();
		}
		return jcb;
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
            String h[]={"Product","Model No.","Quantity","Amount"};
            if(k<8)
            	k=8;
            String d[][]=new String[k][4];
            int i=0, j=0;
            while(rs.next()){
                d[i][j++]=rs.getString(1);
                d[i][j++]=rs.getString(2);
            	d[i][j++]=Integer.toString(rs.getInt(3));
                d[i][j++]=Long.toString(rs.getLong(4));
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
