import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class loginSignupWindow {

	JFrame frame;
	JButton loginButton;
	JButton signupButton;
	JLabel titleLabel;
	JLabel auLabel;
	JLabel background;
	Font font;
	Font btnFont;
	int index;
	
	loginSignupWindow(int in)
	{
		index=in;
	}
	
	public void displayWindow()
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
		
		//AdminUserLabel
		if(index==1)
			auLabel = new JLabel("Admin Side");
		else
			auLabel = new JLabel("User Side");
		auLabel.setFont(font);
		auLabel.setForeground(Color.WHITE);
		auLabel.setFont (auLabel.getFont ().deriveFont (45.0f));
		Dimension sizeau = auLabel.getPreferredSize();
		auLabel.setBounds(330, 200, sizeau.width+10, sizeau.height);
		
		//Log-in button
		loginButton = new JButton("Log In");
		loginButton.setFont(btnFont);
		loginButton.setBackground(Color.BLACK);
		loginButton.setForeground(Color.WHITE);
		loginButton.setBorderPainted(false);
		loginButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		loginButton.setBounds(370, 300, 150, 60);
		loginButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
//				System.out.println("Login Successful!");
				frame.dispose();
				Login l = new Login(index);
				l.loginMenu();				
			}
		});

		//Sign-up Button
		signupButton = new JButton("Sign-up");
		signupButton.setFont(btnFont);
		signupButton.setBackground(Color.BLACK);
		signupButton.setForeground(Color.WHITE);
		signupButton.setBorderPainted(false);		
		signupButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		signupButton.setBounds(370, 380, 150, 60);
		signupButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
//				System.out.println("Sign up successful!");
//				frame.dispose();
				if(index==1)
				{
					frame.dispose();
					AdminSignup s = new AdminSignup();
					s.signupMenu();
				}
				else
				{
					frame.dispose();
					signUp s = new signUp();
					s.signupMenu();
				}
			}
		});
		
		frame.add(background);
		background.add(titleLabel);
		background.add(auLabel);
		background.add(loginButton);
		background.add(signupButton);
		
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
}

