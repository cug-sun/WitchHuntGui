package witchHuntView;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import WitchHunt.Game;
import witchHuntController.WitchHuntController;

import javax.swing.JMenu;
import javax.swing.JButton;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionListener;
import java.awt.Frame;




public class WitchHuntView {

	private JFrame frame;
	private JMenuBar menuBar = new JMenuBar();
	private JMenu mnNewMenu = new JMenu("Game");
	private JMenuItem startConfig = new JMenuItem("New game");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Game game = new Game();
					WitchHuntView window = new WitchHuntView();
					WitchHuntController controller = new WitchHuntController(game, window);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public WitchHuntView() {
		
		initialize();
		
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Witch Hunt");
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setJMenuBar(menuBar);	
		menuBar.add(mnNewMenu);

		
		mnNewMenu.add(startConfig);
		frame.setVisible(true);
		
	}

	public JMenuItem getStartConfig() {
		return startConfig;
	}

	public JFrame getFrame() {
		return frame;
	}


}
