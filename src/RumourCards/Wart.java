package RumourCards;

import Model.Bot;
import Controller.Game;
import Model.Identity;
import Model.Player;

public class Wart extends RumourCard {
	public static RumourCardName cardName = RumourCardName.Wart;
	

	public Wart() {
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
		System.out.println("You will take next turn");
		game.setCurrentPlayer(player);
		setIsUsed(true);
	}

	@Override
	public void huntEffect(Game game) {
		// TODO 鑷姩鐢熸垚鐨勬柟娉曞瓨鏍�
		Player player = game.getCurrentPlayer();
		System.out.println("Choose a player to play next turn");
		player.chooseNextPlayer(game);
		setIsUsed(true);

	}

	@Override
	public void robotWitchEffect(Game game) {
		System.out.printf("Player %d takes next turn\n", game.getCurrentPlayer().getPlayerId());
		game.setCurrentPlayer(game.getCurrentPlayer());
		setIsUsed(true);
		
	}

	@Override
	public void robotHuntEffect(Game game) {
		Bot player = (Bot) game.getCurrentPlayer();
		player.chooseNextPlayer(game);
		System.out.printf("Player %d takes next turn\n", game.getCurrentPlayer().getPlayerId());
		setIsUsed(true);
		
	}

}
