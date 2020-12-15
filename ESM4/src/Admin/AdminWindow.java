package Admin;

import java.awt.Color;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class AdminWindow implements ActionListener {
	JFrame frame;
	JButton customerDataButton;
	JButton productDataButton;
	JButton chatButton;
	JLabel titleLabel;
	JLabel background;
	JLabel adminLabel;
	Font font;
	Font btnFont;
	public void adminMenu()
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
		
		String l="Admin Side";
		//AdminLabel
		adminLabel = new JLabel(l);
		adminLabel.setFont(font);
		adminLabel.setForeground(Color.WHITE);
		adminLabel.setFont (adminLabel.getFont ().deriveFont (45.0f));
		Dimension sizelogin = adminLabel.getPreferredSize();
		adminLabel.setBounds(330, 180, sizelogin.width+10, sizelogin.height);
		btnFont = new Font(Font.SANS_SERIF, Font.ITALIC, 25);
		
		//Customer Data button
		customerDataButton = new JButton("Customer Data");
		customerDataButton.setFont(btnFont);
		customerDataButton.setBackground(Color.BLACK);
		customerDataButton.setForeground(Color.WHITE);
		customerDataButton.setBorderPainted(false);
		customerDataButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		customerDataButton.setBounds(300, 270, 300, 60);
		customerDataButton.addActionListener(this);

		//Product Data Button
		productDataButton = new JButton("Product Data");
		productDataButton.setFont(btnFont);
		productDataButton.setBackground(Color.BLACK);
		productDataButton.setForeground(Color.WHITE);
		productDataButton.setBorderPainted(false);		
		productDataButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		productDataButton.setBounds(300, 350, 300, 60);
		productDataButton.addActionListener(this);
		
		//Chat Button
		chatButton = new JButton("Chat");
		chatButton.setFont(btnFont);
		chatButton.setBackground(Color.BLACK);
		chatButton.setForeground(Color.WHITE);
		chatButton.setBorderPainted(false);
		chatButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		chatButton.setBounds(300, 430, 300, 60);
		chatButton.addActionListener(this);
		
		frame.add(background);
		background.add(titleLabel);
		background.add(adminLabel);
		background.add(customerDataButton);
		background.add(productDataButton);
		background.add(chatButton);
		
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==customerDataButton)
		{
			frame.dispose();
			CustomerData c = new CustomerData();
			c.customerMenu();
		}
		else if(e.getSource()==productDataButton)
		{
			frame.dispose();
			ProductData p = new ProductData();
			p.productMenu();
		}
		else if(e.getSource()==chatButton)
		{
			System.out.println("chatButton");
			Server s = new Server();
			try {
				s.go();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	}
	
}
