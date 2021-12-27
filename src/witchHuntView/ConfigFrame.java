package witchHuntView;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

public class ConfigFrame extends JFrame {
	
	JButton startButton;
	JButton exitButton;
	JPanel buttonPanel;
	BackgroundPanel backgroundPanel;
	public ConfigFrame() throws IOException  {
		// TODO 自动生成的构造函数存根
//		this.setLayout(null);
		this.setTitle("Witch Hunt");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(340,330);
		//禁止用户缩放
		this.setResizable(false);
		buttonPanel = new JPanel();
		backgroundPanel = new BackgroundPanel((new ImageIcon("./background.png")).getImage());
//		backgroundPanel = new BackgroundPanel("./background.png");
		backgroundPanel.setBounds(0,0,340,330);
		this.getContentPane().add(backgroundPanel);
		
//		this.getContentPane().add(buttonPanel);
		
		ConfigPane configPane = new ConfigPane(this);
//		configPane.setBounds(0,0,this.getWidth(),this.getHeight());
		this.getContentPane().add(configPane);
		//设置边框显示
		this.setUndecorated(false);
		//设置居中
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}

	public static void main(String[] args) throws IOException {
		ConfigFrame configFrame = new ConfigFrame();
	}
	
}

