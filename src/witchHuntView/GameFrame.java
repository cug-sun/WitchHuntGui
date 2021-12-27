package witchHuntView;


import java.awt.HeadlessException;

import javax.swing.JFrame;

public class GameFrame extends JFrame {

	GamePane gamePane = null;
	
	public GameFrame() throws HeadlessException {
		// TODO 自动生成的构造函数存根
		this.setTitle("Witch Hunt");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(750 + 200, 650);
		this.setResizable(false);
//		gamePane = new GamePane();
//		this.getContentPane().add(gamePane);
		
		this.setUndecorated(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

	public GamePane getGamePane() {
		return gamePane;
	}
	

}
