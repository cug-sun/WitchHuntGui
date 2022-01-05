package RumourCards;

import java.util.Scanner;

import Model.Bot;
import Controller.Game;
import Model.Identity;
import Model.Player;

public class HookedNose extends RumourCard {

	public static RumourCardName cardName = RumourCardName.Hooked_Nose;
	public HookedNose() {
		super();
		// TODO 鑷姩鐢熸垚鐨勬瀯閫犲嚱鏁板瓨鏍�
	}

	@Override
	public RumourCardName getCardName() {
		// TODO 鑷姩鐢熸垚鐨勬柟娉曞瓨鏍�
		return cardName;
	}

	@Override
	public void witchEffect(Game game) {
		// TODO 鑷姩鐢熸垚鐨勬柟娉曞瓨鏍�
		Player player = game.getCurrentPlayer();
		System.out.println("You can take one card from the hand of the player who accused you");
		Player accusePlayer = game.findPlayer(game.getAccuse()[0]);
		System.out.printf("Player %d has these Rumour cards\n",accusePlayer.getPlayerId());
		accusePlayer.displayHand();
		System.out.println("Which card do you want to take ?");
		Scanner scanner = new Scanner(System.in);
	    RumourCard choosedCard = accusePlayer.getHand().get(scanner.nextInt()-1);
	    System.out.printf("You take %s from player %d\n",choosedCard.getCardName().toString(),accusePlayer.getPlayerId());
	    accusePlayer.getHand().remove(choosedCard);
	    player.addHand(choosedCard);
	    System.out.println("You will take next turn");
	    game.setCurrentPlayer(player);
	    setIsUsed(true);
		
	}

	@Override
	public void huntEffect(Game game) {
		// TODO 鑷姩鐢熸垚鐨勬柟娉曞瓨鏍�
		Player player = game.getCurrentPlayer();
		System.out.println("Choose a player to play next turn, you can take a random card from their hand and add it into your hand");
		player.chooseNextPlayer(game);
		//currentPlayer has changed to the next chosen player
		Player chosenPlayer = game.getCurrentPlayer();
		//get the randomly index of card in the hand
		int index = (int) (Math.random()*chosenPlayer.getHand().size());
		RumourCard chosenCard = chosenPlayer.getHand().get(index);
		System.out.printf("You randomly take %s from his/her hand\n",chosenCard.getCardName().toString());
		chosenPlayer.getHand().remove(chosenCard);
		player.addHand(chosenCard);
		setIsUsed(true);
		
	}

	@Override
	public void robotWitchEffect(Game game) {
		// TODO 鑷姩鐢熸垚鐨勬柟娉曞瓨鏍�
		Bot player = (Bot) game.getCurrentPlayer();
		Player accusePlayer = game.findPlayer(game.getAccuse()[0]);
		RumourCard choosedCard = accusePlayer.getHand().get((int) (Math.random() * (accusePlayer.getHand().size())));
	    accusePlayer.getHand().remove(choosedCard);
	    player.addHand(choosedCard);
	    game.setCurrentPlayer(player);
	    setIsUsed(true);
	}

	@Override
	public void robotHuntEffect(Game game) {
		// TODO 鑷姩鐢熸垚鐨勬柟娉曞瓨鏍�
		Bot player = (Bot) game.getCurrentPlayer();
		player.chooseNextPlayer(game);
		//currentPlayer has changed to the next chosen player
		Player chosenPlayer = game.getCurrentPlayer();
		//get the randomly index of card in the hand
		int index = (int) (Math.random()*chosenPlayer.getHand().size());
		RumourCard chosenCard = chosenPlayer.getHand().get(index);
		chosenPlayer.getHand().remove(chosenCard);
		player.addHand(chosenCard);
		setIsUsed(true);
	}

}
