package Model.Card;

/**
 * Stratégie de la carte DuckingStool.
 * <p>
 * L'effet de Witch est sélectionne un autre joueur.
 * L'effet de village est choisie un autre joueur et demandez-lui de révéler son identité ou de défausser une carte..
 * Inherits the super class {@code RumourCard}.
 * @author ZHANG xiao
 * @see RumourCard
 */
import Model.Bot;


import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;
import Model.CardModel.RumourCard;
import Model.CardModel.RumourCardName;
import Controller.Game;
import Model.Identity;
import Model.Player;

public class DuckingStool extends RumourCard {
	public static RumourCardName cardName = RumourCardName.Ducking_Stool;
	

	public DuckingStool() {
		super();
		// TODO 自动生成的构造函数存根
	}

	/**
	* {@inheritDoc}
	* 
	*/
	@Override
	public RumourCardName getCardName() {
		// TODO 自动生成的构造函数存根
		return cardName;
	}

	/**
	* {@inheritDoc}
	* 
	*/
	@Override
	public void witchEffect(Game game) {
		// TODO 自动生成的构造函数存根
		Player player = game.getCurrentPlayer();
		
		player.chooseNextPlayer(game);
		Player chosenPlayer = game.getCurrentPlayer();
//		boolean duckingTool = false;
//		
//		for (RumourCard card : chosenPlayer.getRevealedCards()) {
//			if(card.getCardName() == RumourCardName.Wart) {
//				duckingTool = true;
//				break;
//			}
//		}
		//when a player has a revealed Wart, he/she can't be chosen by Ducking Stool
		if (chosenPlayer.haveWart() == true) {
			System.out.printf("Player %d has a revealed Wart, he/she can't be chosen by Ducking Stool!\n",chosenPlayer.getPlayerId());
			JOptionPane.showMessageDialog(null,String.format("Player %d has a revealed Wart, he/she can't be chosen by Ducking Stool!", chosenPlayer.getPlayerId()),"Ducking Stool",0);
			game.setCurrentPlayer(player);
			setIsUsed(false);
		}
		else {
			game.setCurrentPlayer(chosenPlayer);
			setIsUsed(true);
		}
		
	}

	/**
	* {@inheritDoc}
	* 
	*/
	@Override
	public void huntEffect(Game game) {
		// TODO 自动生成的方法存根
		Player player = game.getCurrentPlayer();
		//choose a player, they must reveal their identity or discard a card from their hand
		System.out.println("You can choose a player, they must reveal their identity or discard a card from their hand");
		System.out.println("You choose which player ?");
		game.displayPlayers();
		ArrayList<Integer> idList = new ArrayList<Integer>();
		for(int i = 1; i < game.getPlayerList().size(); i++) {
			Player p = game.getPlayerList().get(i);
			if (p.haveBroomstick() == false) {
				idList.add(p.getPlayerId());
			}
		}
		Object[] options = idList.toArray();
		int chosenId = (int)JOptionPane.showInputDialog(null, "player", "Ducking Stool", 1, null, options, options[0]);
//		Scanner scanner = new Scanner(System.in);
//		Player chosenPlayer = game.findPlayer(scanner.nextInt());
		Player chosenPlayer = game.findPlayer(chosenId);
		if (chosenPlayer.haveWart() == true) {
			JOptionPane.showMessageDialog(null, String.format("Player %d has a revealed Wart, you can't choose him", chosenId), "Ducking Stool" , 0);
			game.setCurrentPlayer(player);
		}
		else {
			//bot acts, choose to reveal identity
			System.out.printf("Player %d choose to reveal identity card", chosenId);
			JOptionPane.showMessageDialog(null, String.format("Player %d choose to reveal identity card", chosenId), "Ducking Stool", 1);
			chosenPlayer.revealIdentity();
			switch (chosenPlayer.getIdentity()) {
			case Villager:
				System.out.printf("Player %d, you lost 1 point, player %d will take next turn\n",player.getPlayerId(),chosenPlayer.getPlayerId());
				player.updatePoints(-1);
				game.setCurrentPlayer(chosenPlayer);
				setIsUsed(true);
				break;
				
			case Witch:
				
				System.out.printf("Player %d, you gain 1 point, you will take next turn\n",player.getPlayerId());
				player.updatePoints(1);
				game.setCurrentPlayer(player);
				setIsUsed(true);
				break;

			default:
				break;
			}
		}
		
	}

	/**
	* {@inheritDoc}
	* 
	*/
	@Override
	public void robotWitchEffect(Game game) {
		// TODO 自动生成的构造函数存根
		Bot player = (Bot) game.getCurrentPlayer();
		player.chooseNextPlayer(game);
		System.out.printf("Player %d takes next turn\n", game.getCurrentPlayer().getPlayerId());
		setIsUsed(true);
	}

	/**
	* {@inheritDoc}
	* 
	*/
	@Override
	public void robotHuntEffect(Game game) {
		// TODO 自动生成的构造函数存根
		Bot player = (Bot) game.getCurrentPlayer();
		//choose a player, they must reveal their identity or discard a card from their hand
		Player chosenPlayer = game.getPlayerList().get((int)(Math.random() * (game.getPlayerList().size())));
		boolean duckingTool = false;
		//when a player has a revealed Wart, he/she can't be chosen by Ducking Stool
		for (RumourCard card : chosenPlayer.getRevealedCards()) {
			if(card.getCardName() == RumourCardName.Wart) {
				duckingTool = true;
				break;
			}
		}
		if (duckingTool) {
			game.setCurrentPlayer(player);
			setIsUsed(false);
		}
		else {
			System.out.printf("Player %d, you must\n1.Reveal your identity\nor\n2.Discard a card from your hand\n",chosenPlayer.getPlayerId());
//			System.out.printf("You have %d Rumour cards\n", chosenPlayer.getHand().size());
//			if (chosenPlayer.isRevealed() == true && chosenPlayer.getHand().isEmpty() == true) {
//				System.out.printf("Player %d doesn't have any Rumour card and his identity card has been revealed...\n");
//				game.setCurrentPlayer(chosenPlayer);
//			}
//			if (chosenPlayer.isRevealed() == false) {
//				System.out.println("You identity is not revealed");
//			}
			System.out.println("Input your choice");
			
//			switch (choice) {
//			case 1: {
//				//Reveal identity
//				chosenPlayer.revealIdentity();
//				if(chosenPlayer.getIdentity() == Identity.Witch) {
//					System.out.printf("Player %d, you gain 1 point, you will take next turn\n",player.getPlayerId());
//					player.updatePoints(1);
//					game.setCurrentPlayer(player);
//					setIsUsed(true);
//					break;
//				}
//				else if(chosenPlayer.getIdentity() == Identity.Villager) {
//					System.out.printf("Player %d, you lost 1 point, player %d will take next turn\n",player.getPlayerId(),chosenPlayer.getPlayerId());
//					player.updatePoints(-1);
//					game.setCurrentPlayer(chosenPlayer);
//					setIsUsed(true);
//					break;
//				}
//				
//				
//			}
//			case 2: {
//				//Discard a card from hand
//				chosenPlayer.discard(game);
//				System.out.println("You will take next turn");
//				game.setCurrentPlayer(chosenPlayer);
//				setIsUsed(true);
//				break;
//			}
//			default:
//				throw new IllegalArgumentException("Unexpected value: " + choice);
//			}
			
			
		}
	}

}
