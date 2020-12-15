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

public class ProductData implements ActionListener {
	JFrame frame;
	JButton addProductButton;
	JButton deleteProductButton;
	JButton updateProductButton;
	JButton stockButton;
	JButton backButton;
	JLabel titleLabel;
	JLabel background;
	JLabel adminLabel;
	Font font;
	Font btnFont;
	public void productMenu()
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
		
		String l="Product Data";
		//AdminLabel
		adminLabel = new JLabel(l);
		adminLabel.setFont(font);
		adminLabel.setForeground(Color.WHITE);
		adminLabel.setFont (adminLabel.getFont ().deriveFont (45.0f));
		Dimension sizelogin = adminLabel.getPreferredSize();
		adminLabel.setBounds(330, 180, sizelogin.width+10, sizelogin.height);
		btnFont = new Font(Font.SANS_SERIF, Font.ITALIC, 25);
		
		//Add Product button
		addProductButton = new JButton("Add Product");
		addProductButton.setFont(btnFont);
		addProductButton.setBackground(Color.BLACK);
		addProductButton.setForeground(Color.WHITE);
		addProductButton.setBorderPainted(false);
		addProductButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		addProductButton.setBounds(300, 270, 300, 60);
		addProductButton.addActionListener(this);

		//Delete Product Button
		deleteProductButton = new JButton("Delete Product");
		deleteProductButton.setFont(btnFont);
		deleteProductButton.setBackground(Color.BLACK);
		deleteProductButton.setForeground(Color.WHITE);
		deleteProductButton.setBorderPainted(false);
		deleteProductButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		deleteProductButton.setBounds(300, 350, 300, 60);
		deleteProductButton.addActionListener(this);
		
		//Update Product Button
		updateProductButton = new JButton("Update Product");
		updateProductButton.setFont(btnFont);
		updateProductButton.setBackground(Color.BLACK);
		updateProductButton.setForeground(Color.WHITE);
		updateProductButton.setBorderPainted(false);
		updateProductButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		updateProductButton.setBounds(300, 430, 300, 60);
		updateProductButton.addActionListener(this);

		//stock Button
		stockButton = new JButton("Stock");
		stockButton.setFont(btnFont);
		stockButton.setBackground(Color.BLACK);
		stockButton.setForeground(Color.WHITE);
		stockButton.setBorderPainted(false);
		stockButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		stockButton.setBounds(300, 510, 300, 60);
		stockButton.addActionListener(this);

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
		background.add(addProductButton);
		background.add(deleteProductButton);
		background.add(updateProductButton);
		background.add(stockButton);
		background.add(backButton);
		
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		frame.dispose();
		if(e.getSource()==addProductButton)
		{
			Product p =new Product();
			p.menu();
		}
		else if(e.getSource()==deleteProductButton)
		{
			delProduct dp = new delProduct();
			dp.del();
		}
		else if(e.getSource()==updateProductButton)
		{
			updProduct up = new updProduct();
			up.upd();
		}
		else if(e.getSource()==stockButton)
		{
			stock s = new stock();
			s.menu();			
		}
		else if(e.getSource()==backButton)
		{
			AdminWindow aw = new AdminWindow();
			aw.adminMenu();
		}
	}
}
