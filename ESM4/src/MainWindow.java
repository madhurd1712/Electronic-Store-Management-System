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

public class MainWindow implements ActionListener{

	JFrame frame;
	JButton adminButton;
	JButton userButton;
	JLabel titleLabel;
	JLabel background;
	Font font;
	Font btnFont;
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
		
		//Log-in button
		adminButton = new JButton("Admin");
		adminButton.setFont(btnFont);
		adminButton.setBackground(Color.BLACK);
		adminButton.setForeground(Color.WHITE);
		adminButton.setBorderPainted(false);
		adminButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		adminButton.setBounds(200, 300, 150, 60);
		adminButton.addActionListener(this);

		//Sign-up Button
		userButton = new JButton("User");
		userButton.setFont(btnFont);
		userButton.setBackground(Color.BLACK);
		userButton.setForeground(Color.WHITE);
		userButton.setBorderPainted(false);		
		userButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		userButton.setBounds(200, 380, 150, 60);
		userButton.addActionListener(this);
		
		frame.add(background);
		background.add(titleLabel);
		background.add(adminButton);
		background.add(userButton);
		
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==adminButton)
		{
//			System.out.println("Admin Window");
			frame.dispose();
			loginSignupWindow lsw =new loginSignupWindow(1);
			lsw.displayWindow();
		}
		else if(e.getSource()==userButton)
		{
//			System.out.println("User Window");
			frame.dispose();
			loginSignupWindow lsw =new loginSignupWindow(0);
			lsw.displayWindow();
		}
	}
}
