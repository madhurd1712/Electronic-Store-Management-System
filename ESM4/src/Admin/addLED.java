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
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class addLED implements ActionListener{
	JFrame frame;
	JLabel titleLabel;
	JLabel background;
	JLabel adminLabel;
	JLabel brandL, modelL, starRatingL, dpL, mrpL, warrantyL, stockL, descL, sizeL;
	JTextField brandTxt, modelTxt, descTxt;
	JFormattedTextField starTxt, dpTxt, mrpTxt, warrantyTxt, stockTxt, sizeTxt;
	JButton submitButton;
	JButton backButton;
	Font font;
	Font btnFont;
	
	String brand,model,desc;
	int starRating,dp,mrp,warranty,stock;
	float size;
	
	public void addingLED()
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
		
		String l="Enter Details of LED:";
		//AdminLabel
		adminLabel = new JLabel(l);
		adminLabel.setFont(btnFont);
		adminLabel.setForeground(Color.WHITE);
		adminLabel.setFont (adminLabel.getFont ().deriveFont (30.0f));
		Dimension sizelogin = adminLabel.getPreferredSize();
		adminLabel.setBounds(100, 180, sizelogin.width+10, sizelogin.height);

		brandL = new JLabel("Brand:");
		brandL.setFont(btnFont);
		brandL.setForeground(Color.WHITE);
		brandL.setFont(brandL.getFont().deriveFont(20.0f));
		brandL.setBounds(100, 230, 150, 40);
		brandTxt = new JTextField();
		brandTxt.setBounds(250, 235, 300, 30);
		brandTxt.setFont(btnFont);

		modelL = new JLabel("Model no.: ");
		modelL.setFont(btnFont);
		modelL.setForeground(Color.WHITE);
		modelL.setFont(brandL.getFont().deriveFont(20.0f));
		modelL.setBounds(100, 280, 150, 40);
		modelTxt = new JTextField();
		modelTxt.setBounds(250, 285, 300, 30);
		modelTxt.setFont(btnFont);
		
		sizeL = new JLabel("Size(Inches):");
		sizeL.setFont(btnFont);
		sizeL.setForeground(Color.WHITE);
		sizeL.setFont(brandL.getFont().deriveFont(20.0f));
		sizeL.setBounds(100, 380, 150, 40);
		sizeTxt = new JFormattedTextField(new DecimalFormat());
		sizeTxt.setBounds(250, 385, 100, 30);
		sizeTxt.setFont(btnFont);

		warrantyL = new JLabel("Warranty(Year):");
		warrantyL.setFont(btnFont);
		warrantyL.setForeground(Color.WHITE);
		warrantyL.setFont(brandL.getFont().deriveFont(20.0f));
		warrantyL.setBounds(400, 380, 150, 40);
		warrantyTxt = new JFormattedTextField( NumberFormat.getIntegerInstance());
		warrantyTxt.setBounds(550, 385, 100, 30);
		warrantyTxt.setFont(btnFont);
		
		dpL= new JLabel("Dealer Price:");
		dpL.setFont(btnFont);
		dpL.setForeground(Color.WHITE);
		dpL.setFont(brandL.getFont().deriveFont(20.0f));
		dpL.setBounds(100, 430, 150, 40);
		dpTxt = new JFormattedTextField( NumberFormat.getIntegerInstance());
		dpTxt.setBounds(250, 435, 100, 30);
		dpTxt.setFont(btnFont);

		mrpL= new JLabel("MRP:");
		mrpL.setFont(btnFont);
		mrpL.setForeground(Color.WHITE);
		mrpL.setFont(brandL.getFont().deriveFont(20.0f));
		mrpL.setBounds(400, 430, 150, 40);
		mrpTxt = new JFormattedTextField( NumberFormat.getIntegerInstance());
		mrpTxt.setBounds(550, 435, 100, 30);
		mrpTxt.setFont(btnFont);

		starRatingL= new JLabel("Star Rating:");
		starRatingL.setFont(btnFont);
		starRatingL.setForeground(Color.WHITE);
		starRatingL.setFont(brandL.getFont().deriveFont(20.0f));
		starRatingL.setBounds(100, 480, 150, 40);
		starTxt = new JFormattedTextField(NumberFormat.getIntegerInstance());
		starTxt.setBounds(250, 485, 100, 30);
		starTxt.setFont(btnFont);

		stockL = new JLabel("Stock:");
		stockL.setFont(btnFont);
		stockL.setForeground(Color.WHITE);
		stockL.setFont(brandL.getFont().deriveFont(20.0f));
		stockL.setBounds(400, 480, 150, 40);
		stockTxt = new JFormattedTextField( NumberFormat.getIntegerInstance());
		stockTxt.setBounds(550, 485, 100, 30);
		stockTxt.setFont(btnFont);

		descL = new JLabel("Description:");
		descL.setFont(btnFont);
		descL.setForeground(Color.WHITE);
		descL.setFont(descL.getFont().deriveFont(20.0f));
		descL.setBounds(100, 330, 150, 40);
		descTxt = new JTextField();
		descTxt.setBounds(250, 335, 300, 30);
		descTxt.setFont(btnFont);

		btnFont = new Font(Font.SANS_SERIF, Font.ITALIC, 25);
		//Submit Button
		submitButton = new JButton("Submit");
		submitButton.setFont(btnFont);
		submitButton.setBackground(Color.BLACK);
		submitButton.setForeground(Color.WHITE);
		submitButton.setBorderPainted(false);
		submitButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		submitButton.setBounds(250, 535, 150, 60);
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
		background.add(brandL);
		background.add(brandTxt);
		background.add(modelL);
		background.add(modelTxt);
		background.add(sizeL);
		background.add(sizeTxt);
		background.add(warrantyL);
		background.add(warrantyTxt);
		background.add(dpL);
		background.add(dpTxt);
		background.add(mrpL);
		background.add(mrpTxt);
		background.add(starRatingL);
		background.add(starTxt);
		background.add(stockL);
		background.add(stockTxt);
		background.add(descL);
		background.add(descTxt);
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
				
				brand = brandTxt.getText();
				model = modelTxt.getText();
				desc = descTxt.getText();
				dp = Integer.parseInt(dpTxt.getText().replaceAll(",",  ""));
				mrp = Integer.parseInt(mrpTxt.getText().replaceAll(",",  ""));
				starRating = Integer.parseInt(starTxt.getText().replaceAll(",",  ""));
				stock = Integer.parseInt(stockTxt.getText().replaceAll(",",  ""));
				warranty = Integer.parseInt(warrantyTxt.getText().replaceAll(",",  ""));
				size = Float.parseFloat(sizeTxt.getText());
								
				String query = "Insert into led Values('"+brand+"', '"+model+"', '"+starRating+"', '"+dp+"', '"+mrp+"', '"+size+"', '"+warranty+"', '"+stock+"', '"+desc+"');";
				stmt.executeUpdate(query);
				System.out.println(query);
				JOptionPane.showMessageDialog(frame, "Product Registerd Successfully..!!");
				System.out.println("Product Registerd Successfully..!!");
				con.close();
				
				frame.dispose();
				ProductData p = new ProductData();
				p.productMenu();
			}
			catch (Exception e1) {
				// TODO: handle exception
				if(e1.getLocalizedMessage().substring(0, 15).equals("Duplicate entry"))
					JOptionPane.showMessageDialog(frame, "Product already exists");
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
			Product p = new Product();
			p.menu();
		}
	}

}
