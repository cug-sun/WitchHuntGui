package Model.Card;

import Model.Bot;
/**
 * Stratégie de la carte EvilEye.
 * <p>
 * L'effet de Witch est choisie un joueur qui doit accuser un autre joueur si possible.
 * L'effet de village est choisie un joueur qui doit accuser un autre joueur si possible.
 * Inherits the super class {@code RumourCard}.
 * @author ZHANG xiao
 * @see RumourCard
 */

import javax.swing.JOptionPane;

import Controller.Game;
import Model.Identity;
import Model.Player;
import Model.CardModel.RumourCard;
import Model.CardModel.RumourCardName;

public class EvilEye extends RumourCard {
	public static RumourCardName cardName = RumourCardName.Evil_Eye;
	

	public EvilEye() {
		super();
		// TODO 鑷姩鐢熸垚鐨勬瀯閫犲嚱鏁板瓨鏍�
	}

	/**
	* {@inheritDoc}
	* 
	*/
	@Override
	public RumourCardName getCardName() {
		// TODO 自动生成的方法存根
		return cardName;
	}

	/**
	* {@inheritDoc}
	* 
	*/
	@Override
	public void witchEffect(Game game) {
		// TODO 自动生成的方法存根
		Player player = game.getCurrentPlayer();
		//choose next player
		player.chooseNextPlayer(game);
		Player chosenPlayer = game.getCurrentPlayer();
		System.out.printf("You have choose player %d, on his/her turn, he/she must accuse a player other than you, if possible\n",chosenPlayer.getPlayerId());
		JOptionPane.showMessageDialog(null, String.format("Player %d must accuse a player other than you",chosenPlayer.getPlayerId()), "Evil Eye", 1);
		chosenPlayer.setEvilEye(player.getPlayerId());
		setIsUsed(true);
	}

	/**
	* {@inheritDoc}
	* 
	*/
	@Override
	public void huntEffect(Game game) {
		// TODO 自动生成的方法存根
		Player player = game.getCurrentPlayer();
		//choose next player
		player.chooseNextPlayer(game);
		Player chosenPlayer = game.getCurrentPlayer();
		System.out.printf("You have choose player %d, on his/her turn, he/she must accuse a player other than you, if possible\n",chosenPlayer.getPlayerId());
		JOptionPane.showMessageDialog(null, String.format("Player %d must accuse a player other than you",chosenPlayer.getPlayerId()),"Evil Eye", 1);
		chosenPlayer.setEvilEye(player.getPlayerId());
		setIsUsed(true);
	}

	/**
	* {@inheritDoc}
	* 
	*/
	@Override
	public void robotWitchEffect(Game game) {
		// TODO 自动生成的方法存根
		Bot player = (Bot) game.getCurrentPlayer();
		//choose next player
		player.chooseNextPlayer(game);
		Player chosenPlayer = game.getCurrentPlayer();
		System.out.printf("choose player %d, on his/her turn, he/she must accuse a player other than %d, if possible\n",player.getPlayerId(),chosenPlayer.getPlayerId());
		chosenPlayer.setEvilEye(player.getPlayerId());
		setIsUsed(true);
	}

	/**
	* {@inheritDoc}
	* 
	*/
	@Override
	public void robotHuntEffect(Game game) {
		// TODO 自动生成的方法存根
		Bot player = (Bot) game.getCurrentPlayer();
		//choose next player
		player.chooseNextPlayer(game);
		Player chosenPlayer = game.getCurrentPlayer();
		System.out.printf("choose player %d, on his/her turn, he/she must accuse a player other than %d, if possible\n",player.getPlayerId(),chosenPlayer.getPlayerId());
		chosenPlayer.setEvilEye(player.getPlayerId());
		setIsUsed(true);
	}

}
