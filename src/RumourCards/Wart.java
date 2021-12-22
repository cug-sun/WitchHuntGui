package RumourCards;

import WitchHunt.Game;
import WitchHunt.Player;

public class Wart extends RumourCard {
	public static RumourCardName cardName = RumourCardName.Wart;
	

	public Wart() {
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
		System.out.println("You will take next turn");
		game.setCurrentPlayer(player);
		setIsUsed(true);
	}

	@Override
	public void huntEffect(Game game) {
		// TODO 自动生成的方法存根
		Player player = game.getCurrentPlayer();
		System.out.println("Choose a player to play next turn");
		player.chooseNextPlayer(game);
		setIsUsed(true);

	}

}
