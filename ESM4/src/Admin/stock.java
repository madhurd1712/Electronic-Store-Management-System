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

public class stock implements ActionListener {
	JFrame frame;
	JLabel titleLabel;
	JLabel background;
	JLabel adminLabel;
	JButton backButton;
	JButton displayBtn;
	JButton updateBtn;
	Font font;
	Font btnFont;
	public void menu()
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
		
		String l="Stock";
		//AdminLabel
		adminLabel = new JLabel(l);
		adminLabel.setFont(font);
		adminLabel.setForeground(Color.WHITE);
		adminLabel.setFont (adminLabel.getFont ().deriveFont (45.0f));
		Dimension sizelogin = adminLabel.getPreferredSize();
		adminLabel.setBounds(400, 180, sizelogin.width+10, sizelogin.height);
		btnFont = new Font(Font.SANS_SERIF, Font.ITALIC, 25);
		
		
		//Display button
		displayBtn = new JButton("Display");
		displayBtn.setFont(btnFont);
		displayBtn.setBackground(Color.BLACK);
		displayBtn.setForeground(Color.WHITE);
		displayBtn.setBorderPainted(false);
		displayBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		displayBtn.setBounds(300, 270, 300, 60);
		displayBtn.addActionListener(this);

		//Update Button
		updateBtn = new JButton("Update");
		updateBtn.setFont(btnFont);
		updateBtn.setBackground(Color.BLACK);
		updateBtn.setForeground(Color.WHITE);
		updateBtn.setBorderPainted(false);
		updateBtn.setCursor(new Cursor(Cursor.HAND_CURSOR));
		updateBtn.setBounds(300, 350, 300, 60);
		updateBtn.addActionListener(this);
		
		
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
		background.add(updateBtn);
		background.add(displayBtn);
		background.add(backButton);
		
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==displayBtn)
		{
			frame.dispose();
			displayStock ds = new displayStock();
			ds.display();
		}
		else if(e.getSource()==updateBtn)
		{
			frame.dispose();
			updStock us = new updStock();
			us.upd();
		}
		else if(e.getSource()==backButton)
		{
			frame.dispose();
			ProductData pd = new ProductData();
			pd.productMenu();
		}
	}
}
