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
import Customer.CustWindow;

public class Login implements ActionListener {
	JFrame frame;
	JLabel titleLabel;
	JLabel background;
	JLabel loginLabel;
	JLabel uLabel, pLabel;
	JTextField uTxt;
	JPasswordField pTxt;
	JButton submitButton;
	Font btnFont;
	Font font;
	int index;
	
	Login(int in)
	{
		index=in;
	}
	
	public void loginMenu()
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
		
		String l="";
		if(index == 1)
			l+="Admin Login";
		else
			l+="User Login";
		//loginLabel
		loginLabel = new JLabel(l);
		loginLabel.setFont(font);
		loginLabel.setForeground(Color.WHITE);
		loginLabel.setFont (loginLabel.getFont ().deriveFont (45.0f));
		Dimension sizelogin = loginLabel.getPreferredSize();
		loginLabel.setBounds(200, 180, sizelogin.width+10, sizelogin.height);
		

		//Username
		uLabel = new JLabel("Username: ");
		uLabel.setFont(btnFont);
		uLabel.setForeground(Color.WHITE);
		uLabel.setFont(uLabel.getFont().deriveFont(20.0f));
		uLabel.setBounds(200, 260, 200, 40);
		uTxt = new JTextField();
		uTxt.setBounds(200, 310, 200, 30);
		
		//Password
		pLabel = new JLabel("Password: ");
		pLabel.setFont(btnFont);
		pLabel.setForeground(Color.WHITE);
		pLabel.setFont(pLabel.getFont().deriveFont(20.0f));
		pLabel.setBounds(200, 360, 200, 40);
		pTxt = new JPasswordField();
		pTxt.setBounds(200, 410, 200, 30);
		
		//Submit Button
		submitButton = new JButton("Submit");
		submitButton.setFont(btnFont);
		submitButton.setBackground(Color.BLACK);
		submitButton.setForeground(Color.WHITE);
		submitButton.setBorderPainted(false);
		submitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		submitButton.setBounds(225, 470, 150, 60);
		submitButton.addActionListener(this);

		frame.add(background);
		background.add(titleLabel);	
		background.add(loginLabel);
		background.add(uLabel);
		background.add(pLabel);
		background.add(pTxt);
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
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/esm","root","md1712"); 
			Statement stmt=con.createStatement();
			String username = uTxt.getText();
			String password = new String(pTxt.getPassword());
			String query = "select * from customer where email='"+username+"';";
			ResultSet rs = stmt.executeQuery(query);
			if(rs.next())
			{
				if(username.equals(rs.getString(2))==true && index==rs.getInt(4))
				{
					String name =rs.getString(5);
					if(password.equals(rs.getString(3))==false)
					{
						JOptionPane.showMessageDialog(frame, "Wrong Password");
						System.out.println("Wrong Password");
					}
					else
					{
						System.out.println("Login Successful");
						frame.dispose();
						if(index==1)
						{
							frame.dispose();
							AdminWindow mw = new AdminWindow();
							mw.adminMenu();
						}
						else
						{
							int id = rs.getInt(1);
	//						UserWindow
							String q = "TRUNCATE TABLE cart;";
							stmt.executeUpdate(q);
							frame.dispose();
							CustWindow cw = new CustWindow(id,name);
							cw.custMenu();
						}
					}
				}
				else
				{
					JOptionPane.showMessageDialog(frame, "Username doesn't exists");
					System.out.println("Username not found");
				}	
			}
			else
			{
				JOptionPane.showMessageDialog(frame, "Username doesn't exists");
				System.out.println("Username not found");
			}	
		} catch (Exception e2) {
			// TODO: handle exception
			System.out.println(e2);
			e2.printStackTrace();
		}
	}
}
