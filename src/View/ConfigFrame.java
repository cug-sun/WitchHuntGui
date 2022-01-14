package View;


import java.io.IOException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
/**
 * The main frame of the start window.
 * <p>
 * Inherits the super class {@code JFrame}.
 * @author SUN Sun
 *
 */
public class ConfigFrame extends JFrame {
	
	JButton startButton;
	JButton exitButton;
	JPanel buttonPanel;
	BackgroundPanel backgroundPanel;
	/**
	 * Initializes the frame.
	 * @throws IOException when the icon file not found
	 */
	public ConfigFrame() throws IOException  {
		// TODO 自动生成的构造函数存根
		ImageIcon icon = new ImageIcon("./image/icon/witch-hat.png");
		this.setIconImage(icon.getImage());
		this.setTitle("Witch Hunt");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(340,330);
		//禁止用户缩放
		this.setResizable(false);
		buttonPanel = new JPanel();
		backgroundPanel = new BackgroundPanel((new ImageIcon("./image/background/background.png")).getImage());
		backgroundPanel.setBounds(0,0,340,330);
		this.getContentPane().add(backgroundPanel);
		ConfigPane configPane = new ConfigPane(this);
		this.getContentPane().add(configPane);
		//设置边框显示
		this.setUndecorated(false);
		//设置居中
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
	}	
}

