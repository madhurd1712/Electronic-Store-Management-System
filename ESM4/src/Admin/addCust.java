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
import javax.swing.JTextField;

public class addCust implements ActionListener{
	JFrame frame;
	JLabel titleLabel;
	JLabel background;
	JLabel adminLabel;
	JLabel userL, nameL, addressL, phnL;
	JTextField userTxt, nameTxt, addressTxt, phnTxt;
	JButton submitButton;
	JButton backButton;
	Font font;
	Font btnFont;
	
	String name, username, address;
	long phn;
	
	public void addingCust()
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
		
		String l="Enter Details of Customer:";
		//AdminLabel
		adminLabel = new JLabel(l);
		adminLabel.setFont(btnFont);
		adminLabel.setForeground(Color.WHITE);
		adminLabel.setFont (adminLabel.getFont ().deriveFont (30.0f));
		Dimension sizelogin = adminLabel.getPreferredSize();
		adminLabel.setBounds(100, 180, sizelogin.width+50, sizelogin.height);

		userL = new JLabel("Username:");
		userL.setFont(btnFont);
		userL.setForeground(Color.WHITE);
		userL.setFont(userL.getFont().deriveFont(20.0f));
		userL.setBounds(100, 250, 150, 40);
		userTxt = new JTextField();
		userTxt.setBounds(250, 255, 300, 30);
		userTxt.setFont(btnFont);

		nameL = new JLabel("Name: ");
		nameL.setFont(btnFont);
		nameL.setForeground(Color.WHITE);
		nameL.setFont(nameL.getFont().deriveFont(20.0f));
		nameL.setBounds(100, 300, 150, 40);
		nameTxt = new JTextField();
		nameTxt.setBounds(250, 305, 300, 30);
		nameTxt.setFont(btnFont);
		
		phnL = new JLabel("Phone No.: ");
		phnL.setFont(btnFont);
		phnL.setForeground(Color.WHITE);
		phnL.setFont(nameL.getFont().deriveFont(20.0f));
		phnL.setBounds(100, 350, 150, 40);
		phnTxt = new JTextField();
		phnTxt.setBounds(250, 355, 300, 30);
		phnTxt.setFont(btnFont);

		addressL = new JLabel("Address: ");
		addressL.setFont(btnFont);
		addressL.setForeground(Color.WHITE);
		addressL.setFont(addressL.getFont().deriveFont(20.0f));
		addressL.setBounds(100, 400, 150, 40);
		addressTxt = new JTextField();
		addressTxt.setBounds(250, 405, 500, 30);
		addressTxt.setFont(btnFont);
		
		btnFont = new Font(Font.SANS_SERIF, Font.ITALIC, 25);
		//Submit Button
		submitButton = new JButton("Submit");
		submitButton.setFont(btnFont);
		submitButton.setBackground(Color.BLACK);
		submitButton.setForeground(Color.WHITE);
		submitButton.setBorderPainted(false);
		submitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		submitButton.setBounds(250, 480, 150, 60);
		submitButton.addActionListener(this);
		

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
		background.add(userL);
		background.add(userTxt);
		background.add(nameL);
		background.add(nameTxt);
		background.add(phnL);
		background.add(phnTxt);
		background.add(addressL);
		background.add(addressTxt);
		background.add(submitButton);
		background.add(backButton);
		
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==submitButton)
		{
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/esm","root","md1712"); 
				Statement stmt=con.createStatement();
				
				name = nameTxt.getText();
				username = userTxt.getText();
				address = addressTxt.getText();
				phn = Long.parseLong(phnTxt.getText());
				
				String query = "Insert into customer Values(null, '"+username+"', null, 0, '"+name+"', '"+address+"', '"+phn+"');";
				stmt.executeUpdate(query);
				JOptionPane.showMessageDialog(frame, "Customer added successfully");
				frame.dispose();
				//Add Purchased Products
				query = "Select cust_id from customer where email='"+username+"';";
				int id = 0;
				ResultSet rs=stmt.executeQuery(query);
	            while(rs.next())
	            {
	            	id=rs.getInt(1);
	            }
				ProductPurchased pp = new ProductPurchased(id);
				pp.addProducts();
			}
			catch (Exception e1) {
				// TODO: handle exception
				if(e1.getLocalizedMessage().substring(0, 15).equals("Duplicate entry"))
					JOptionPane.showMessageDialog(frame, "Customer already exists");
				else
				{
					JOptionPane.showMessageDialog(frame, "Oops, error occurred!!");
					System.out.println(e1);
				}
			}
		}
		if(e.getSource()==backButton)
		{
			frame.dispose();
			CustomerData cd = new CustomerData();
			cd.customerMenu();
		}
	}	
}