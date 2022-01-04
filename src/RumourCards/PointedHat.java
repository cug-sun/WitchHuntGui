package RumourCards;

import java.util.Scanner;

import Model.Bot;
import Controller.Game;
import Model.Identity;
import Model.Player;

public class PointedHat extends RumourCard {

	public static RumourCardName cardName = RumourCardName.Pointed_Hat;

	public PointedHat() {
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
		if(!player.getRevealedCards().isEmpty()) {
			System.out.println("You have these revealed Rumour cards:");
			for (RumourCard card : player.getRevealedCards()) {
				System.out.printf("%d. %s\n",player.getRevealedCards().indexOf(card)+1, card.getCardName().toString());
			}
			System.out.println("Take one of your own revealed Rumour cards into your hand");
			Scanner scanner = new Scanner(System.in);
			RumourCard chosenCard = player.getRevealedCards().get(scanner.nextInt() - 1);
			chosenCard.setIsUsed(false);
			System.out.printf("You take %s into your hand\n", chosenCard.getCardName().toString());
			player.addHand(chosenCard);
			player.getRevealedCards().remove(chosenCard);
			//current player takes next turn
			System.out.println("You will take next turn");
			game.setCurrentPlayer(game.getCurrentPlayer());
			setIsUsed(true);
		}
		else {
			System.out.println("You don't have any revealed card");
			setIsUsed(false);
		}
	}

	@Override
	public void huntEffect(Game game) {
		// TODO 自动生成的方法存根
		Player player = game.getCurrentPlayer();
		if(!player.getRevealedCards().isEmpty()) {
			System.out.println("You have these revealed Rumour cards:");
			for (RumourCard card : player.getRevealedCards()) {
				System.out.printf("%d. %s\n",player.getRevealedCards().indexOf(card)+1, card.getCardName().toString());
			}
			System.out.println("Take one of your own revealed Rumour cards into your hand");
			Scanner scanner = new Scanner(System.in);
			RumourCard chosenCard = player.getRevealedCards().get(scanner.nextInt() - 1);
			chosenCard.setIsUsed(false);
			System.out.printf("You take %s into your hand\n", chosenCard.getCardName().toString());
			player.addHand(chosenCard);
			player.getRevealedCards().remove(chosenCard);
			//Choose another player to play next turn
			player.chooseNextPlayer(game);
			setIsUsed(true);
		}
		else {
			System.out.println("You don't have any revealed card!");
			setIsUsed(false);
		}
	}

	@Override
	public void robotWitchEffect(Game game) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void robotHuntEffect(Game game) {
		// TODO 自动生成的方法存根
		
	}

}
