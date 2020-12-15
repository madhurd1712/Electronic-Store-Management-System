package Admin;

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

public class CustomerData implements ActionListener {

	JFrame frame;
	JButton addCustomerButton;
	JButton customersAttachedButton;
	JButton searchButton;
	JButton backButton;
	JLabel titleLabel;
	JLabel background;
	JLabel adminLabel;
	Font font;
	Font btnFont;
	public void customerMenu()
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
		
		String l="Customer Data";
		//AdminLabel
		adminLabel = new JLabel(l);
		adminLabel.setFont(font);
		adminLabel.setForeground(Color.WHITE);
		adminLabel.setFont (adminLabel.getFont ().deriveFont (45.0f));
		Dimension sizelogin = adminLabel.getPreferredSize();
		adminLabel.setBounds(330, 180, sizelogin.width+10, sizelogin.height);
		btnFont = new Font(Font.SANS_SERIF, Font.ITALIC, 25);
		
		//Add Customer button
		addCustomerButton = new JButton("Add Customer");
		addCustomerButton.setFont(btnFont);
		addCustomerButton.setBackground(Color.BLACK);
		addCustomerButton.setForeground(Color.WHITE);
		addCustomerButton.setBorderPainted(false);
		addCustomerButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		addCustomerButton.setBounds(300, 270, 300, 60);
		addCustomerButton.addActionListener(this);

		//Customer's Attached Button
		customersAttachedButton = new JButton("Customer's Attached");
		customersAttachedButton.setFont(btnFont);
		customersAttachedButton.setBackground(Color.BLACK);
		customersAttachedButton.setForeground(Color.WHITE);
		customersAttachedButton.setBorderPainted(false);
		customersAttachedButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		customersAttachedButton.setBounds(300, 350, 300, 60);
		customersAttachedButton.addActionListener(this);
		
		//Search Customer Data Button
		searchButton = new JButton("Search Customer Data");
		searchButton.setFont(btnFont);
		searchButton.setBackground(Color.BLACK);
		searchButton.setForeground(Color.WHITE);
		searchButton.setBorderPainted(false);
		searchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		searchButton.setBounds(300, 430, 300, 60);
		searchButton.addActionListener(this);
		
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
		background.add(addCustomerButton);
		background.add(customersAttachedButton);
		background.add(searchButton);
		background.add(backButton);
		
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==addCustomerButton)
		{
			frame.dispose();
			addCust ac = new addCust();
			ac.addingCust();
		}
		else if(e.getSource()==customersAttachedButton)
		{
			frame.dispose();
			CustomerAttached ca = new CustomerAttached();
			ca.display();
		}
		else if(e.getSource()==searchButton)
		{
			frame.dispose();
			searchCustomer sc = new searchCustomer();
			sc.display();
		}
		else if(e.getSource()==backButton)
		{
			frame.dispose();
			AdminWindow aw = new AdminWindow();
			aw.adminMenu();
		}
	}
}
