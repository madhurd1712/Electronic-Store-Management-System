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
import java.text.NumberFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class updStock implements ActionListener{
	JFrame frame;
	JLabel titleLabel;
	JLabel background;
	JLabel headLabel;
	JLabel productL;
	JLabel modelL;
	JLabel cprice, uprice, cdp, udp;
	JFormattedTextField dpTxt;
	JComboBox<String> productCombo;
	JComboBox<String> ac,ref,led;
	JButton updButton;
	JButton getModelsbtn;
	JButton getPricebtn;
	JButton backButton;
	Font btnFont, comboFont;
	Font font;
	String[] product= {"AC", "REF", "LED"};
	String pr="AC";
	String sdp,smrp;
	
	public void upd()
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

		String l="Update Stock";
		//signupLabel
		headLabel = new JLabel(l);
		headLabel.setFont(font);
		headLabel.setForeground(Color.WHITE);
		headLabel.setFont (headLabel.getFont ().deriveFont (45.0f));
		Dimension sizehead = headLabel.getPreferredSize();
		headLabel.setBounds(200, 180, sizehead.width+10, sizehead.height);
		
		btnFont = new Font(Font.SANS_SERIF, Font.ITALIC, 25);
		
		productL = new JLabel("Product:");
		productL.setFont(btnFont);
		productL.setForeground(Color.WHITE);
		productL.setFont(productL.getFont().deriveFont(20.0f));
		productL.setBounds(200, 280, 150, 40);
		
		comboFont = new Font(Font.SANS_SERIF, Font.ITALIC, 15);
		
		productCombo = new JComboBox<String>(product);
		productCombo.setFont(comboFont);
		productCombo.setBounds(350, 280, 200, 40);
		
		//GetModel  Button
		getModelsbtn = new JButton("Get Models");
		getModelsbtn.setFont(comboFont);
		getModelsbtn.setBackground(Color.BLACK);
		getModelsbtn.setForeground(Color.WHITE);
		getModelsbtn.setBorderPainted(false);
		getModelsbtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		getModelsbtn.setBounds(600, 280, 120, 40);
		getModelsbtn.addActionListener(this);
		
		modelL = new JLabel("Model Number:");
		modelL.setFont(btnFont);
		modelL.setForeground(Color.WHITE);
		modelL.setFont(modelL.getFont().deriveFont(20.0f));
		modelL.setBounds(200, 350, 300, 40);
		
		ac = new JComboBox<String>();
		ac = getModels("AC");
		ac.setFont(comboFont);
		ac.setBounds(350, 350, 200, 40);
		
		ref = new JComboBox<String>();
		ref = getModels("REF");
		ref.setFont(comboFont);
		ref.setBounds(350, 350, 200, 40);
		
		led = new JComboBox<String>();
		led = getModels("LED");
		led.setFont(comboFont);
		led.setBounds(350, 350, 200, 40);

		//GetPrice  Button
		getPricebtn = new JButton("Get Stock");
		getPricebtn.setFont(comboFont);
		getPricebtn.setBackground(Color.BLACK);
		getPricebtn.setForeground(Color.WHITE);
		getPricebtn.setBorderPainted(false);
		getPricebtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		getPricebtn.setBounds(600, 350, 120, 40);
		getPricebtn.addActionListener(this);
		
		cprice = new JLabel("CURRENT:");
		cprice.setFont(btnFont);
		cprice.setForeground(Color.WHITE);
		cprice.setFont(cprice.getFont().deriveFont(20.0f));
		cprice.setBounds(200, 400, 150, 40);
		
		sdp = "Stock: ";
		cdp = new JLabel(sdp);
		cdp.setFont(btnFont);
		cdp.setForeground(Color.WHITE);
		cdp.setFont(cdp.getFont().deriveFont(20.0f));
		cdp.setBounds(200, 430, 250, 40);
		
		uprice = new JLabel("UPDATED:");
		uprice.setFont(btnFont);
		uprice.setForeground(Color.WHITE);
		uprice.setFont(uprice.getFont().deriveFont(20.0f));
		uprice.setBounds(200, 480, 150, 40);
		
		udp = new JLabel("Stock: ");
		udp.setFont(btnFont);
		udp.setForeground(Color.WHITE);
		udp.setFont(cdp.getFont().deriveFont(20.0f));
		udp.setBounds(200, 510, 180, 40);
		dpTxt = new JFormattedTextField( NumberFormat.getIntegerInstance());
		dpTxt.setBounds(330, 515, 150, 30);
		dpTxt.setFont(btnFont);

		
		//Submit Button
		updButton = new JButton("Update");
		updButton.setFont(btnFont);
		updButton.setBackground(Color.BLACK);
		updButton.setForeground(Color.WHITE);
		updButton.setBorderPainted(false);
		updButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		updButton.setBounds(300, 570, 150, 60);
		updButton.addActionListener(this);
		
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
		background.add(getPricebtn);
		background.add(cprice);
		background.add(cdp);
		background.add(uprice);
		background.add(udp);
		background.add(dpTxt);
		background.add(updButton);
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
		if(e.getSource()==getPricebtn)
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
				String query = "select stock from "+pr+" where model_no = '" + mdl+"';";
	            ResultSet rs=stmt.executeQuery(query);
	            rs.next();
	            String tdp = sdp + rs.getInt(1);
	            cdp.setText(tdp);
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(frame, "Oops, error occurred!!");
				ex.printStackTrace();
			}
		}
		if(e.getSource()==updButton)
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
				int dpV = Integer.parseInt(dpTxt.getText().replaceAll(",",  ""));
				String query = "UPDATE "+pr+" set stock= '"+dpV+"' where model_no = '"+mdl+"';";
	            stmt.executeUpdate(query);

				JOptionPane.showMessageDialog(frame, "Stock Updated Successfully..!!");
				con.close();
				frame.dispose();
				stock s = new stock();
				s.menu();
			}
			catch(Exception ex)
			{
				JOptionPane.showMessageDialog(frame, "Oops, error occurred!!");
				ex.printStackTrace();
			}
		}
		if(e.getSource()==backButton)
		{
			frame.dispose();
			stock s = new stock();
			s.menu();
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
}
