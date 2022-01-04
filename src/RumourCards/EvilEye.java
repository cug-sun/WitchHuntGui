package RumourCards;

import Model.Bot;
import Controller.Game;
import Model.Identity;
import Model.Player;


public class EvilEye extends RumourCard {
	public static RumourCardName cardName = RumourCardName.Evil_Eye;
	

	public EvilEye() {
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
		//choose next player
		player.chooseNextPlayer(game);
		Player chosenPlayer = game.getCurrentPlayer();
		System.out.printf("You have choose player %d, on his/her turn, he/she must accuse a player other than you, if possible\n",chosenPlayer.getPlayerId());
		chosenPlayer.setEvilEye(player.getPlayerId());
		setIsUsed(true);
	}

	@Override
	public void huntEffect(Game game) {
		// TODO 自动生成的方法存根
		Player player = game.getCurrentPlayer();
		//choose next player
		player.chooseNextPlayer(game);
		Player chosenPlayer = game.getCurrentPlayer();
		System.out.printf("You have choose player %d, on his/her turn, he/she must accuse a player other than you, if possible\n",chosenPlayer.getPlayerId());
		chosenPlayer.setEvilEye(player.getPlayerId());
		setIsUsed(true);
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
