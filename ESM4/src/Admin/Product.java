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

public class Product implements ActionListener {
	JFrame frame;
	JButton acButton;
	JButton refButton;
	JButton ledButton;
	JButton backButton;
	JLabel titleLabel;
	JLabel background;
	JLabel adminLabel;
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

		btnFont = new Font(Font.SANS_SERIF, Font.ITALIC, 25);
		
		String l="Products";
		//AdminLabel
		adminLabel = new JLabel(l);
		adminLabel.setFont(font);
		adminLabel.setForeground(Color.WHITE);
		adminLabel.setFont (adminLabel.getFont ().deriveFont (45.0f));
		Dimension sizelogin = adminLabel.getPreferredSize();
		adminLabel.setBounds(330, 180, sizelogin.width+10, sizelogin.height);
		btnFont = new Font(Font.SANS_SERIF, Font.ITALIC, 25);
		
		//Air-Conditioners button
		acButton = new JButton("Air-Conditioners");
		acButton.setFont(btnFont);
		acButton.setBackground(Color.BLACK);
		acButton.setForeground(Color.WHITE);
		acButton.setBorderPainted(false);
		acButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		acButton.setBounds(300, 270, 300, 60);
		acButton.addActionListener(this);

		//Refrigerators Button
		refButton = new JButton("Refrigerators");
		refButton.setFont(btnFont);
		refButton.setBackground(Color.BLACK);
		refButton.setForeground(Color.WHITE);
		refButton.setBorderPainted(false);		
		refButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		refButton.setBounds(300, 350, 300, 60);
		refButton.addActionListener(this);
		
		//L.E.D. Button
		ledButton = new JButton("L.E.D.");
		ledButton.setFont(btnFont);
		ledButton.setBackground(Color.BLACK);
		ledButton.setForeground(Color.WHITE);
		ledButton.setBorderPainted(false);
		ledButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
		ledButton.setBounds(300, 430, 300, 60);
		ledButton.addActionListener(this);

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
		background.add(acButton);
		background.add(refButton);
		background.add(ledButton);
		background.add(backButton);
		
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
//		frame.dispose();
		if(e.getSource()==acButton)
		{
			frame.dispose();
			addAC aAC = new addAC();
			aAC.addingAC();
		}
		else if(e.getSource()==refButton)
		{
			frame.dispose();
			addREF aREF = new addREF();
			aREF.addingREF();
		}
		else if(e.getSource()==ledButton)
		{
			frame.dispose();
			addLED aLED = new addLED();
			aLED.addingLED();
		}
		else if(e.getSource()==backButton)
		{
			frame.dispose();
			ProductData pd = new ProductData();
			pd.productMenu();
		}
	}
}
