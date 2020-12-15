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

public class ProductPurchased implements ActionListener{
	JFrame frame;
	JLabel titleLabel;
	JLabel background;
	JLabel adminLabel;
	JButton submitButton;
	JButton backButton;
	Font font;
	Font btnFont;
	int id;
	JLabel prL, modelL, qtyL;
	JTextField prTxt, modelTxt, qtyTxt;
	String pr,model;
	int qty;
	ProductPurchased(int x)
	{
		id = x;
	}
	public void addProducts()
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
		
		String l="Enter Details of Purchased Product:";
		//AdminLabel
		adminLabel = new JLabel(l);
		adminLabel.setFont(btnFont);
		adminLabel.setForeground(Color.WHITE);
		adminLabel.setFont (adminLabel.getFont ().deriveFont (30.0f));
		Dimension sizelogin = adminLabel.getPreferredSize();
		adminLabel.setBounds(100, 180, sizelogin.width+100, sizelogin.height);

		
		prL = new JLabel("Product Name:");
		prL.setFont(btnFont);
		prL.setForeground(Color.WHITE);
		prL.setFont(prL.getFont().deriveFont(20.0f));
		prL.setBounds(100, 250, 150, 40);
		prTxt = new JTextField();
		prTxt.setBounds(250, 255, 300, 30);
		prTxt.setFont(btnFont);

		modelL = new JLabel("Name: ");
		modelL.setFont(btnFont);
		modelL.setForeground(Color.WHITE);
		modelL.setFont(modelL.getFont().deriveFont(20.0f));
		modelL.setBounds(100, 300, 150, 40);
		modelTxt = new JTextField();
		modelTxt.setBounds(250, 305, 300, 30);
		modelTxt.setFont(btnFont);
		
		qtyL = new JLabel("Quantity: ");
		qtyL.setFont(btnFont);
		qtyL.setForeground(Color.WHITE);
		qtyL.setFont(qtyL.getFont().deriveFont(20.0f));
		qtyL.setBounds(100, 350, 150, 40);
		qtyTxt = new JTextField();
		qtyTxt.setBounds(250, 355, 100, 30);
		qtyTxt.setFont(btnFont);
		
		
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
		background.add(prL);
		background.add(prTxt);
		background.add(modelL);
		background.add(modelTxt);
		background.add(qtyL);
		background.add(qtyTxt);
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
				
				pr = prTxt.getText();
				model = modelTxt.getText();
				qty = Integer.parseInt(qtyTxt.getText());
				String dat = "Select SYSDATE()";
				ResultSet rs = stmt.executeQuery(dat);
				rs.next();
				dat=rs.getString(1);
				String query = "Insert into purchasedproduct Values('"+id+"', '"+pr+"', '"+model+"', '"+qty+"', '"+dat+"');";
				stmt.executeUpdate(query);
				JOptionPane.showMessageDialog(frame, "Product added successfully");
			
				frame.dispose();
				ProductPurchased pp = new ProductPurchased(id);
				pp.addProducts();
			}
			catch (Exception e1) {
				// TODO: handle exception
				
					JOptionPane.showMessageDialog(frame, "Oops, error occurred!!");
					System.out.println(e1);
			}
		}
		else if(e.getSource()==backButton)
		{
			frame.dispose();
			CustomerData cd = new CustomerData();
			cd.customerMenu();
		}
	}
}
