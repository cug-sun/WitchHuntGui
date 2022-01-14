package View;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import Controller.Game;
/**
 * The main frame of the game window.
 * <p>
 * Inherits the super class {@code JFrame}.
 * @author SUN Sun 
 */
public class GameFrame extends JFrame {

	GamePane gamePane = null;
	
	
	/**
	 * Initializes the game frame. 
	 * @param game {@link GamePane} component
	 */
	public GameFrame(Game game) {
		// TODO 自动生成的构造函数存根
		this.setTitle("Witch Hunt");
		
		ImageIcon icon = new ImageIcon("./image/icon/witch-hat.png");
		this.setIconImage(icon.getImage());
		//最大化窗口
		this.setExtendedState(this.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setSize(750 + 200, 650);
		this.setResizable(false);
		
		gamePane = new GamePane(game);
		//set game panel
		this.getContentPane().add(gamePane);
		
		this.setUndecorated(false);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		
		
	}
	
}
