package witchHuntController;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

import RumourCards.AngryMob;
import RumourCards.EvilEye;
import RumourCards.HookedNose;
import RumourCards.PetNewt;
import RumourCards.RumourCard;
import WitchHunt.Game;
import WitchHunt.Identity;
import WitchHunt.Player;
import witchHuntView.GamePane;

public class WitchHuntController {
	
	private Game model;
	
	public WitchHuntController(JFrame frame,JMenuItem item) {
		// TODO 自动生成的构造函数存根
		item.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				setPlayers();
//				setIdentity(null);
				//test code
				ArrayList<Player> playerList = new ArrayList<Player>();
				for(int i = 1; i <= 6; i++) {
					Player player = new Player(i);
					playerList.add(player);
				}
				Player player1 = playerList.get(0);
				RumourCard AngryMob = new AngryMob();
				RumourCard evilEye = new EvilEye();
				RumourCard hookedNose = new HookedNose();
				RumourCard petNewt = new PetNewt();
				player1.addHand(AngryMob);
				player1.addHand(evilEye);
				player1.addHand(hookedNose);
				player1.addHand(petNewt);
				//
				GamePane gamePane = new GamePane(playerList);
				frame.getContentPane().add(gamePane);
				frame.validate();
			}
		});
	}
	public void paintCards() {
		
	}
	public void setIdentity(Player player) {
		String[] list = {"Witch","Villager"};
		String identity = (String)JOptionPane.showInputDialog(null,"You choose to be", "Identity",JOptionPane.INFORMATION_MESSAGE, null,list,list[0] );
		if(identity == "Viilager") {
			player.setIdentity(Identity.Villager);
		}
		else {
			player.setIdentity(Identity.Witch);
		}
	}
	public void setPlayers() {
		Integer[] choice = {3,4,5,6};
		int nPlayer = (int)JOptionPane.showInputDialog(null,"Number of players", "Initialize",JOptionPane.INFORMATION_MESSAGE, null,choice, choice[0]);
		String mes = "Each player has " + 12/nPlayer + " rumour cards";
		JOptionPane.showMessageDialog(null, mes);
	}
}
