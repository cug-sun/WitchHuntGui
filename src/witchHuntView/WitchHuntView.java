package witchHuntView;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;


import witchHuntController.WitchHuntController;

import javax.swing.JMenu;




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
		WitchHuntController controller = new WitchHuntController(frame,start);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(300, 50, 1200, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setExtendedState(frame.getExtendedState() | JFrame.MAXIMIZED_BOTH);
		frame.setJMenuBar(menuBar);	
		menuBar.add(mnNewMenu);

		
//		start.addActionListener(new JMenuItemHanlder());
		mnNewMenu.add(start);
	}
	
	
	
	
	
	
//	class JMenuItemHanlder implements ActionListener{
//
//		@Override
//		public void actionPerformed(ActionEvent e) {
//			// TODO 自动生成的方法存根
//			Integer[] choice = {3,4,5,6};
//			int nPlayer = (int)JOptionPane.showInputDialog(null,"Number of players", "Initialize",JOptionPane.INFORMATION_MESSAGE, null,choice, choice[0]);
//			String mes = "Each player has " + 12/nPlayer + " rumour cards";
//			JOptionPane.showMessageDialog(null, mes);
//			
//			//test code
//			ArrayList<Player> playerList = new ArrayList<Player>();
//			Player player = new Player(1);
//			playerList.add(player);
//			RumourCard AngryMob = new AngryMob();
//			RumourCard evilEye = new EvilEye();
//			RumourCard hookedNose = new HookedNose();
//			RumourCard petNewt = new PetNewt();
//			player.addHand(AngryMob);
//			player.addHand(evilEye);
//			player.addHand(hookedNose);
//			player.addHand(petNewt);
//			//
//			GamePane gamePane = new GamePane(playerList);
//			frame.getContentPane().add(gamePane);
//			frame.validate();
//			
//			
//		}
//		
//	}

}
