package RumourCards;

import java.util.Scanner;

import WitchHunt.Bot;
import WitchHunt.Game;
import WitchHunt.Player;

public class BlackCat extends RumourCard {
	public static RumourCardName cardName = RumourCardName.Black_Cat;

	public BlackCat() {
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
		game.setCurrentPlayer(game.getCurrentPlayer());
		setIsUsed(true);

	}

	@Override
	public void huntEffect(Game game) {
		// TODO 自动生成的方法存根
		Player player = game.getCurrentPlayer();
		if(!game.discardPile.isEmpty()) {
			System.out.println("You can choose one of these Rumour cards:");
			for (RumourCard card : game.discardPile) {
				System.out.printf("%d. %s\n",game.discardPile.indexOf(card)+1, card.getCardName().toString());
			}
			System.out.println("Which one do you want to add ?");
			Scanner scanner = new Scanner(System.in);
			RumourCard choosedCard = game.discardPile.get(scanner.nextInt() - 1);
			choosedCard.setIsUsed(false);
			System.out.printf("You take %s into your hand\n", choosedCard.getCardName().toString());
			player.addHand(choosedCard);
			game.getDiscardPile().remove(choosedCard);
			//current player takes next turn
			game.setCurrentPlayer(player);
			//discard Black Cat
//			player.getHand().remove(this);
//			game.getDiscardPile().add(this);
			setIsUsed(true);
			
		}
		else {
			System.out.println("There is no card in the discard pile !");
			game.setCurrentPlayer(player);
			setIsUsed(false);
		}


	}

	@Override
	public void robotWitchEffect(Game game) {
		// TODO 自动生成的方法存根
		System.out.printf("Player %d takes next turn\n", game.getCurrentPlayer().getPlayerId());
		game.setCurrentPlayer(game.getCurrentPlayer());
		setIsUsed(true);
	}

	@Override
	public void robotHuntEffect(Game game) {
		// TODO 自动生成的方法存根
		Bot player = (Bot) game.getCurrentPlayer();
		if(!game.discardPile.isEmpty()) {
			//randomly choose a card from discard pile
			int index = (int) (Math.random() * game.discardPile.size());
			RumourCard chosenCard = game.discardPile.get(index);
			System.out.printf(null, null);
		}
	}

}
