package witchHuntView;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import RumourCards.BlackCat;
import RumourCards.EvilEye;
import RumourCards.HookedNose;
import RumourCards.PetNewt;
import RumourCards.RumourCard;
import WitchHunt.Player;

import javax.swing.JMenu;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class WitchHuntView {

	private JFrame frame;
	private JMenuBar menuBar = new JMenuBar();
	private JMenu mnNewMenu = new JMenu("Game");
	private JMenuItem start = new JMenuItem("New game");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WitchHuntView window = new WitchHuntView();
					window.frame.setVisible(true);
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
		frame = new JFrame();
		frame.setBounds(300, 100, 750, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setJMenuBar(menuBar);	
		menuBar.add(mnNewMenu);
//		//test code
//		ArrayList<Player> playerList = new ArrayList<Player>();
//		Player player = new Player(1);
//		playerList.add(player);
//		RumourCard blackCat = new BlackCat();
//		RumourCard evilEye = new EvilEye();
//		RumourCard hookedNose = new HookedNose();
//		RumourCard petNewt = new PetNewt();
//		player.addHand(blackCat);
//		player.addHand(evilEye);
//		player.addHand(hookedNose);
//		player.addHand(petNewt);
//		//
//		GamePane gamePane = new GamePane(playerList);
//		frame.getContentPane().add(gamePane);
		
		start.addActionListener(new JMenuItemHanlder());
		mnNewMenu.add(start);
	}
	
	
	
	
	
	
	class JMenuItemHanlder implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO 自动生成的方法存根
			Integer[] choice = {3,4,5,6};
			int nPlayer = (int)JOptionPane.showInputDialog(null,"Number of players", "Initialize",JOptionPane.INFORMATION_MESSAGE, null,choice, choice[0]);
			String mes = "Each player has " + 12/nPlayer + " rumour cards";
			JOptionPane.showMessageDialog(null, mes);
			//test code
			ArrayList<Player> playerList = new ArrayList<Player>();
			Player player = new Player(1);
			playerList.add(player);
			RumourCard blackCat = new BlackCat();
			RumourCard evilEye = new EvilEye();
			RumourCard hookedNose = new HookedNose();
			RumourCard petNewt = new PetNewt();
			player.addHand(blackCat);
			player.addHand(evilEye);
			player.addHand(hookedNose);
			player.addHand(petNewt);
			//
			GamePane gamePane = new GamePane(playerList);
			frame.getContentPane().add(gamePane);
			frame.validate();
			
			
		}
		
	}

}
