package RumourCards;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;

import Model.Bot;
import Controller.Game;
import Model.Identity;
import Model.Player;

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
		JOptionPane.showMessageDialog(null, "You will play next turn", "Black Cat", 1);
		setIsUsed(true);

	}

	@Override
	public void huntEffect(Game game) {
		// TODO 自动生成的方法存根
		Player player = game.getCurrentPlayer();
		if(!game.discardPile.isEmpty()) {
			System.out.println("You can choose one of these Rumour cards:");
			ArrayList<String> cardList = new ArrayList<String>();
			for (RumourCard card : game.discardPile) {
				System.out.printf("%d. %s\n",game.discardPile.indexOf(card)+1, card.getCardName().toString());
				cardList.add(card.getCardName().toString());
			}
			System.out.println("Which one do you want to add ?");
			Object[] options = cardList.toArray();
			String chosenCarName = (String)JOptionPane.showInputDialog(null, "You choose", "Discard", 1, null, options, options[0]);
//			Scanner scanner = new Scanner(System.in);
//			RumourCard choosedCard = game.discardPile.get(scanner.nextInt() - 1);
			RumourCard chosenCard = null;
			for(RumourCard card: game.discardPile) {
				if(card.getCardName().toString() == chosenCarName) {
					chosenCard = card;
				}
			}
			chosenCard.setIsUsed(false);
			System.out.printf("You take %s into your hand\n", chosenCard.getCardName().toString());
			JOptionPane.showMessageDialog(null, String.format("You take %s into your hand\n", chosenCard.getCardName().toString()),"Black Cat",1);
			player.addHand(chosenCard);
			game.getDiscardPile().remove(chosenCard);
			//current player takes next turn
			game.setCurrentPlayer(player);
			//discard Black Cat
//			player.getHand().remove(this);
//			game.getDiscardPile().add(this);
			setIsUsed(true);
			game.getGamePane().repaint();
		}
		else {
			System.out.println("There is no card in the discard pile !");
			JOptionPane.showMessageDialog(null, "There is no card in the discard pile !", "Black Cat", 0);
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
