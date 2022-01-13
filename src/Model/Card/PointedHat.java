package Model.Card;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;
import Model.CardModel.RumourCard;
import Model.CardModel.RumourCardName;
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
			ArrayList<String> cardList = new ArrayList<String>();
			for (RumourCard card : player.getRevealedCards()) {
				System.out.printf("%d. %s\n",player.getRevealedCards().indexOf(card)+1, card.getCardName().toString());
				cardList.add(card.getCardName().toString());
			}
			Object[] options = cardList.toArray();
			System.out.println("Take one of your own revealed Rumour cards into your hand");
			String chosenCarName = (String)JOptionPane.showInputDialog(null, "You choose", "Take one card into your hand", 1, null, options, options[0]);
			RumourCard chosenCard = null;
			for(RumourCard card: player.getHand()) {
				if(card.getCardName().toString() == chosenCarName) {
					chosenCard = card;
				}
			}
//			Scanner scanner = new Scanner(System.in);
//			RumourCard chosenCard = player.getRevealedCards().get(scanner.nextInt() - 1);
			chosenCard.setIsUsed(false);
			System.out.printf("You take %s into your hand\n", chosenCard.getCardName().toString());
			JOptionPane.showMessageDialog(null,String.format("You take %s into your hand", chosenCard.getCardName().toString()),"Pointed Hat",1);
			player.addHand(chosenCard);
			player.getRevealedCards().remove(chosenCard);
			//current player takes next turn
			System.out.println("You will take next turn");
			JOptionPane.showMessageDialog(null,"You will take next turn","Pointed Hat",1);
			game.setCurrentPlayer(game.getCurrentPlayer());
			setIsUsed(true);
			game.getGamePane().repaint();
		}
		else {
			System.out.println("You don't have any revealed card");
			JOptionPane.showMessageDialog(null,"You don't have any revealed card!","Pointed Hat",0);
			setIsUsed(false);
		}
	}

	@Override
	public void huntEffect(Game game) {
		// TODO 自动生成的方法存根
		Player player = game.getCurrentPlayer();
		if(!player.getRevealedCards().isEmpty()) {
			System.out.println("You have these revealed Rumour cards:");
			ArrayList<String> cardList = new ArrayList<String>();
			for (RumourCard card : player.getRevealedCards()) {
				System.out.printf("%d. %s\n",player.getRevealedCards().indexOf(card)+1, card.getCardName().toString());
				cardList.add(card.getCardName().toString());
			}
			System.out.println("Take one of your own revealed Rumour cards into your hand");
			Object[] options = cardList.toArray();
			System.out.println("Take one of your own revealed Rumour cards into your hand");
			String chosenCarName = (String)JOptionPane.showInputDialog(null, "You choose", "Take one card into your hand", 1, null, options, options[0]);
			RumourCard chosenCard = null;
			for(RumourCard card: player.getHand()) {
				if(card.getCardName().toString() == chosenCarName) {
					chosenCard = card;
				}
			}
//			Scanner scanner = new Scanner(System.in);
//			RumourCard chosenCard = player.getRevealedCards().get(scanner.nextInt() - 1);
			chosenCard.setIsUsed(false);
			System.out.printf("You take %s into your hand\n", chosenCard.getCardName().toString());
			JOptionPane.showMessageDialog(null,String.format("You take %s into your hand", chosenCard.getCardName().toString()),"Pointed Hat",1);
			player.addHand(chosenCard);
			player.getRevealedCards().remove(chosenCard);
			//Choose another player to play next turn
			player.chooseNextPlayer(game);
			setIsUsed(true);
			game.getGamePane().repaint();
		}
		else {
			System.out.println("You don't have any revealed card!");
			JOptionPane.showMessageDialog(null,"You don't have any revealed card!","Pointed Hat",0);
			setIsUsed(false);
		}
	}

	@Override
	public void robotWitchEffect(Game game) {
		// TODO 自动生成的方法存根
		Bot player = (Bot) game.getCurrentPlayer();
		if(!player.getRevealedCards().isEmpty()) {
			RumourCard chosenCard = player.getRevealedCards().get((int) (Math.random() * (player.getHand().size())));
			chosenCard.setIsUsed(false);
			player.addHand(chosenCard);
			player.getRevealedCards().remove(chosenCard);
			//current player takes next turn
			System.out.printf("player %d will take next turn",player.getPlayerId());
			game.setCurrentPlayer(game.getCurrentPlayer());
			setIsUsed(true);
		}
		else {
			player.chooseNextPlayer(game);
			System.out.printf("Player %d takes next turn\n", game.getCurrentPlayer().getPlayerId());
			setIsUsed(false);
		}
	}

	@Override
	public void robotHuntEffect(Game game) {
		// TODO 自动生成的方法存根
		Bot player = (Bot) game.getCurrentPlayer();
		if(!player.getRevealedCards().isEmpty()) {
			RumourCard chosenCard = player.getRevealedCards().get((int) (Math.random() * (player.getHand().size())));
			chosenCard.setIsUsed(false);
			player.addHand(chosenCard);
			player.getRevealedCards().remove(chosenCard);
			//current player takes next turn
			System.out.printf("player %d will take next turn",player.getPlayerId());
			game.setCurrentPlayer(game.getCurrentPlayer());
			setIsUsed(true);
		}
		else {
			player.chooseNextPlayer(game);
			System.out.printf("Player %d takes next turn\n", game.getCurrentPlayer().getPlayerId());
			setIsUsed(false);
		}

	}
}
