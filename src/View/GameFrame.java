package View;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import Controller.Game;

public class GameFrame extends JFrame {

	GamePane gamePane = null;
	
	
	
	public GameFrame(Game model) {
		// TODO 自动生成的构造函数存根
		this.setTitle("Witch Hunt");
		
		ImageIcon icon = new ImageIcon("./image/icon/witch-hat.png");
		this.setIconImage(icon.getImage());
		//最大化窗口
		this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(750 + 200, 650);
		this.setResizable(false);
		
		//set background
//		JLabel backgroundLabel = new JLabel(new ImageIcon("./image/background/background.png"));
//		backgroundLabel.setBounds(0, 0, 950, 650);
//		this.getContentPane().add(backgroundLabel);
		
		gamePane = new GamePane(model);
		//set game panel
		this.getContentPane().add(gamePane);
		
		this.setUndecorated(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		
	}

	public GamePane getGamePane() {
		return gamePane;
	}
	

}
