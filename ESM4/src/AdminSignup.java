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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Admin.AdminWindow;

public class AdminSignup implements ActionListener {
	JFrame frame;
	JLabel titleLabel;
	JLabel background;
	JLabel signupLabel;
	JLabel nameL, phnL, addressL, userL, pLabel, paLabel;
	JTextField uTxt, nameTxt, addressTxt, phnTxt;
	JPasswordField pTxt,paTxt;
	JButton submitButton;
	Font btnFont;
	Font font;
	String adminPass = "admin@123";
	
	public void signupMenu()
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

		btnFont = new Font(Font.SANS_SERIF, Font.ITALIC, 25);
		
		String l="Admin SignUp";
		//signupLabel
		signupLabel = new JLabel(l);
		signupLabel.setFont(font);
		signupLabel.setForeground(Color.WHITE);
		signupLabel.setFont (signupLabel.getFont ().deriveFont (45.0f));
		Dimension sizelogin = signupLabel.getPreferredSize();
		signupLabel.setBounds(200, 180, sizelogin.width+10, sizelogin.height);
		
		nameL = new JLabel("Name: ");
		nameL.setFont(btnFont);
		nameL.setForeground(Color.WHITE);
		nameL.setFont(nameL.getFont().deriveFont(20.0f));
		nameL.setBounds(100, 250, 150, 40);
		nameTxt = new JTextField();
		nameTxt.setBounds(270, 255, 300, 30);
		nameTxt.setFont(btnFont);
		
		phnL = new JLabel("Phone No.: ");
		phnL.setFont(btnFont);
		phnL.setForeground(Color.WHITE);
		phnL.setFont(nameL.getFont().deriveFont(20.0f));
		phnL.setBounds(100, 300, 150, 40);
		phnTxt = new JTextField();
		phnTxt.setBounds(270, 305, 300, 30);
		phnTxt.setFont(btnFont);

		addressL = new JLabel("Address: ");
		addressL.setFont(btnFont);
		addressL.setForeground(Color.WHITE);
		addressL.setFont(addressL.getFont().deriveFont(20.0f));
		addressL.setBounds(100, 350, 150, 40);
		addressTxt = new JTextField();
		addressTxt.setBounds(270, 355, 500, 30);
		addressTxt.setFont(btnFont);

		userL = new JLabel("Username:");
		userL.setFont(btnFont);
		userL.setForeground(Color.WHITE);
		userL.setFont(userL.getFont().deriveFont(20.0f));
		userL.setBounds(100, 400, 150, 40);
		uTxt = new JTextField();
		uTxt.setBounds(270, 405, 200, 30);
		uTxt.setFont(btnFont);

		//Password
		pLabel = new JLabel("Password: ");
		pLabel.setFont(btnFont);
		pLabel.setForeground(Color.WHITE);
		pLabel.setFont(pLabel.getFont().deriveFont(20.0f));
		pLabel.setBounds(100, 450, 200, 40);
		pTxt = new JPasswordField();
		pTxt.setBounds(270, 455, 200, 30);
		
		//Admin Password
		paLabel = new JLabel("Admin Password: ");
		paLabel.setFont(btnFont);
		paLabel.setForeground(Color.WHITE);
		paLabel.setFont(pLabel.getFont().deriveFont(20.0f));
		paLabel.setBounds(100, 500, 200, 40);
		paTxt = new JPasswordField();
		paTxt.setBounds(270, 505, 200, 30);
		
		//Submit Button
		submitButton = new JButton("Submit");
		submitButton.setFont(btnFont);
		submitButton.setBackground(Color.BLACK);
		submitButton.setForeground(Color.WHITE);
		submitButton.setBorderPainted(false);
		submitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		submitButton.setBounds(225, 550, 150, 60);
		submitButton.addActionListener(this);

		frame.add(background);
		background.add(titleLabel);	
		background.add(signupLabel);
		background.add(nameL);
		background.add(nameTxt);
		background.add(phnL);
		background.add(phnTxt);
		background.add(addressL);
		background.add(addressTxt);
		background.add(userL);
		background.add(pLabel);
		background.add(paLabel);
		background.add(pTxt);
		background.add(paTxt);
		background.add(uTxt);
		background.add(submitButton);

		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		try {
			String ap = new String(paTxt.getPassword());
			if(ap.equals(adminPass))
			{
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/esm","root","md1712"); 
				Statement stmt=con.createStatement();
				String username = uTxt.getText();
				String password = new String(pTxt.getPassword());
				String name = nameTxt.getText();
				String address = addressTxt.getText();
				long phn = Long.parseLong(phnTxt.getText());
				String query = "select * from customer where email='"+username+"';";
				ResultSet rs = stmt.executeQuery(query);
				
				if(rs.next()==false)
				{
					query = "Insert into customer Values(null, '"+username+"', '"+password+"', 1, '"+name+"', '"+address+"', '"+phn+"');";
					stmt.executeUpdate(query);
					frame.dispose();
					System.out.println("Admin Registerd Successfully..!!");
					AdminWindow mw = new AdminWindow();
					mw.adminMenu();
					con.close();
				}
				else
				{
					JOptionPane.showMessageDialog(frame, "Username already exists");
					System.out.println("Username already exists");
				}
			}
			else
			{
				JOptionPane.showMessageDialog(frame, "Admin Password is wrong!!");
				System.out.println("Admin Password is wrong!!");
			}
		} catch (Exception e2) {
			// TODO: handle exception
			System.out.println(e2);
		}
	}
}
