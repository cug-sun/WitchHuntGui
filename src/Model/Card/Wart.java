package Model.Card;

import Model.Bot;

import javax.swing.JOptionPane;
import Model.CardModel.RumourCard;
import Model.CardModel.RumourCardName;
import Controller.Game;
import Model.Identity;
import Model.Player;

public class Wart extends RumourCard {
	public static RumourCardName cardName = RumourCardName.Wart;
	

	public Wart() {
		super();
		// TODO 自动生成的方法存根
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
		JOptionPane.showMessageDialog(null,"Choose a player to play next turn","Wart",1);
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
