package Model.Card;
/**
 * Stratégie de la carte AngryMob.
 * <p>
 * L'effet de Witch est de continuer le prochain tour.
 * L'effet de chasse ne peut être utilisé que si l'identité est révélée comme étant celle d'un villageois, 
 * révélant la carte d'identité d'une autre personne et marquant des points en fonction de son identité.
 * Inherits the super class {@code RumourCard}.
 * @author ZHANG xiao
 * @see RumourCard
 */

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;
import Model.CardModel.RumourCard;
import Model.CardModel.RumourCardName;
import Model.Bot;
import Controller.Game;
import Model.Identity;
import Model.Player;

public class AngryMob extends RumourCard {
	
	public static RumourCardName cardName = RumourCardName.Angry_Mob;
	public AngryMob() {
		super();
		// TODO 自动生成的构造函数存根
	}
	/**
	* {@inheritDoc}
	*/
	@Override
	public RumourCardName getCardName() {
		// TODO 自动生成的方法存根
		return cardName;
	}
	/**
	* {@inheritDoc}
	*/
	@Override
	public void witchEffect(Game game) {
		// TODO 自动生成的方法存根
		//take next turn
		System.out.println("You will take next turn");
		JOptionPane.showMessageDialog(null,"You will play next turn","info",1);
		game.setCurrentPlayer(game.getCurrentPlayer());
		setIsUsed(true);
	}
	/**
	* {@inheritDoc}
	*/
	@Override
	public void huntEffect(Game game) {
		// TODO 自动生成的方法存根
		Player player = game.getCurrentPlayer();
		//Only playable if you have been revealed as a villager
		if(player.isRevealed() == true && player.getIdentity() == Identity.Villager) {
			
			ArrayList<Integer> idList = new ArrayList<Integer>();
			for(int i = 1; i < game.getPlayerList().size(); i++) {
				Player p = game.getPlayerList().get(i);
				if (p.haveBroomstick() == false) {
					idList.add(p.getPlayerId());
				}
			}
			Object[] options = idList.toArray();
			int chosenId = (int)JOptionPane.showInputDialog(null, "player", "Broomstick", 1, null, options, options[0]);
			//reveal another player's identity
//			Scanner scanner = new Scanner(System.in);
//			System.out.println("You can reveal another player's identity\nInput the player's id");
//			int chosenId = scanner.nextInt();
			Player chosenPlayer = game.findPlayer(chosenId);
			//when a player has a revealed Broomstick, he/she can't be chosen by Angry Mob
//			boolean broomStick = false;
//			for (RumourCard card : chosenPlayer.getRevealedCards()) {
//				if(card.getCardName() == RumourCardName.Broomstick) {
//					broomStick = true;
//					break;
//				}	
//			}
			
//			if(broomStick) {
//				System.out.printf("Player %d has a revealed Broomstick, he/she can't be chosen by Angry Mob!\n",chosenPlayer.getPlayerId());
//				game.setCurrentPlayer(player);
//				setIsUsed(false);
//			}
//			else {
				chosenPlayer.revealIdentity();
				//calculate point
				if (chosenPlayer.getIdentity() == Identity.Villager) {
					System.out.println("You lose 2 pts, the player whose identity card you reveal plays the next turn");
					JOptionPane.showMessageDialog(null,String.format("You lose 2 pts, player %d plays next turn", chosenId),"Broomstick",1);
					player.updatePoints(-2);
					game.setCurrentPlayer(chosenPlayer);
				}
				else if (chosenPlayer.getIdentity() == Identity.Witch) {
					System.out.println("You gain 2 pts, you take next turn");
					JOptionPane.showMessageDialog(null,"You gain 2 pts, you take next turn","Broomstick",1);
					player.updatePoints(2);
					game.setCurrentPlayer(player);
				}
				setIsUsed(true);
//			}		
		}
		else {
			System.out.println(this.getCardName() + " is only playable if you have been revealed as a villager");
			JOptionPane.showMessageDialog(null,"Broomstick is only playable if you have been revealed as a villager","Broomstick",0);
			setIsUsed(false);
		}
	}
	/**
	* {@inheritDoc}
	*/
	@Override
	public void robotWitchEffect(Game game) {
		// TODO 自动生成的方法存根
		System.out.printf("Player %d takes next turn\n", game.getCurrentPlayer().getPlayerId());
		game.setCurrentPlayer(game.getCurrentPlayer());
		setIsUsed(true);
	}
	/**
	* {@inheritDoc}
	*/
	@Override
	public void robotHuntEffect(Game game) {
		// TODO 自动生成的方法存根
		Bot player = (Bot) game.getCurrentPlayer();
		if(player.isRevealed() == true && player.getIdentity() == Identity.Villager) {
			Player chosenPlayer = null;
			do {
				chosenPlayer = player.randomChoose(game);
			} while (chosenPlayer.isRevealed() == true);
			chosenPlayer.revealIdentity();
			if (chosenPlayer.existRevealedCard(RumourCardName.Broomstick) == false) {
				if(chosenPlayer.getIdentity() == Identity.Witch) {
					System.out.printf("Player %d gains 2 points, he/she plays next turn\n",player.getPlayerId());
					game.setCurrentPlayer(player);
				}
				else {
					System.out.printf("Player %d loses 2 points, player %d plays next turn\n",chosenPlayer.getPlayerId());
					game.setCurrentPlayer(chosenPlayer);
				}
				setIsUsed(true);
			}
			
		}
	}
}
