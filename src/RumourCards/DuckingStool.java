package RumourCards;

import java.util.Scanner;
import WitchHunt.Game;
import WitchHunt.Identity;
import WitchHunt.Player;


public class DuckingStool extends RumourCard {
	public static RumourCardName cardName = RumourCardName.Ducking_Stool;
	

	public DuckingStool() {
		super();
		// TODO 自动生成的构造函数存根
	}

	@Override
	public RumourCardName getCardName() {
		// TODO 自动生成的方法存根
		return cardName;
	}

	@Override
	public void witchEffect(Game game) {
		// TODO 自动生成的方法存根
		Player player = game.getCurrentPlayer();
		
		player.chooseNextPlayer(game);
		Player chosenPlayer = game.getCurrentPlayer();
		boolean duckingTool = false;
		//when a player has a revealed Wart, he/she can't be chosen by Ducking Stool
		for (RumourCard card : chosenPlayer.getRevealedCards()) {
			if(card.getCardName() == RumourCardName.Wart) {
				duckingTool = true;
				break;
			}
		}
		if (duckingTool) {
			System.out.printf("Player %d has a revealed Wart, he/she can't be chosen by Ducking Stool!\n",chosenPlayer.getPlayerId());
			game.setCurrentPlayer(player);
			setIsUsed(false);
		}
		else {
			game.setCurrentPlayer(chosenPlayer);
			setIsUsed(true);
		}
		
	}

	@Override
	public void huntEffect(Game game) {
		// TODO 自动生成的方法存根
		Player player = game.getCurrentPlayer();
		//choose a player, they must reveal their identity or discard a card from their hand
		System.out.println("You can choose a player, they must reveal their identity or discard a card from their hand");
		System.out.println("You choose which player ?");
		game.displayPlayers();
		Scanner scanner = new Scanner(System.in);
		Player chosenPlayer = game.findPlayer(scanner.nextInt());
		boolean duckingTool = false;
		//when a player has a revealed Wart, he/she can't be chosen by Ducking Stool
		for (RumourCard card : chosenPlayer.getRevealedCards()) {
			if(card.getCardName() == RumourCardName.Wart) {
				duckingTool = true;
				break;
			}
		}
		if (duckingTool) {
			System.out.printf("Player %d has a revealed Wart, he/she can't be chosen by Ducking Stool!\n",chosenPlayer.getPlayerId());
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
			int choice = scanner.nextInt();
			switch (choice) {
			case 1: {
				//Reveal identity
				chosenPlayer.revealIdentity();
				if(chosenPlayer.getIdentity() == Identity.Witch) {
					System.out.printf("Player %d, you gain 1 point, you will take next turn\n",player.getPlayerId());
					player.updatePoints(1);
					game.setCurrentPlayer(player);
					setIsUsed(true);
					break;
				}
				else if(chosenPlayer.getIdentity() == Identity.Villager) {
					System.out.printf("Player %d, you lost 1 point, player %d will take next turn\n",player.getPlayerId(),chosenPlayer.getPlayerId());
					player.updatePoints(-1);
					game.setCurrentPlayer(chosenPlayer);
					setIsUsed(true);
					break;
				}
				
				
			}
			case 2: {
				//Discard a card from hand
				chosenPlayer.discard(game);
				System.out.println("You will take next turn");
				game.setCurrentPlayer(chosenPlayer);
				setIsUsed(true);
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + choice);
			}
			
			
		}
		
	}

}
