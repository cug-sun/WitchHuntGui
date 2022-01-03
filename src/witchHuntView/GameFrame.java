package witchHuntView;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import WitchHunt.Game;

public class GameFrame extends JFrame {

	GamePane gamePane = null;
	
	public GameFrame(Game model) {
		// TODO 自动生成的构造函数存根
		this.setTitle("Witch Hunt");
		//最大化窗口
		this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(750 + 200, 650);
		this.setResizable(false);
		
		gamePane = new GamePane(model);
		
		this.getContentPane().add(gamePane);
//		gamePane.paintAccuse(null);
		
		this.setUndecorated(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		JLabel label = new JLabel("123345");
//		gamePane.add(label);
//		try {
//			Thread.sleep(3000);
//		} catch (InterruptedException e) {
//			// TODO 自动生成的 catch 块
//			e.printStackTrace();
//		}
//		gamePane.remove(label);
		gamePane.getInfoLabel().setText("accuse player 2");
	}

	public GamePane getGamePane() {
		return gamePane;
	}
	

}
