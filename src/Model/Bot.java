package Model;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import Controller.Game;
import RumourCards.RumourCard;


public class Bot extends Player {

	
	
	public Bot(int playerId) {
		super(playerId);
		// TODO 自动生成的构造函数存根
		
	}
	
	@Override
	//randomly choose identity
	public void chooseIdentity() {
		double seed = Math.random();
		if (seed < 0.5) {
			this.setIdentity(Identity.Witch);
			System.out.printf("Player %d chooses to be a Witch\n", this.getPlayerId());
		}
		else {
			this.setIdentity(Identity.Villager);
			System.out.printf("Player %d chooses to be a Villager\n", this.getPlayerId());
		}
	}
	
	@Override
	public void chooseNextPlayer(Game game) {
		Player chosenPlayer = null;
		do {
			chosenPlayer = game.getPlayerList().get((int)(Math.random() * (game.getPlayerList().size())));
		} while (chosenPlayer != this);
		game.setCurrentPlayer(chosenPlayer);
		System.out.printf("Player %d chooses player %d to play next turn\n", this.getPlayerId(),chosenPlayer.getPlayerId());
	}
	
	@Override
	//randomly discard
	public void discard(Game game) {
		int index = (int) (Math.random() * (this.hand.size()));
		RumourCard chosenCard = this.hand.get(index);
		System.out.printf("Player %d discards %s", this.getPlayerId(), chosenCard.getCardName().toString());
		setMessageLabel(String.format("discard %s", chosenCard.getCardName().toString()));
		this.hand.remove(index);
	}
	//choose randomly another player
	public Player randomChoose(Game game) {
		Player chosenPlayer = null;
		do {
			chosenPlayer = game.getPlayerList().get((int)(Math.random() * (game.getPlayerList().size())));
		} while (chosenPlayer == this);
		return chosenPlayer;
	}
	
	@Override
	public void playTurn(Game game) {
		/* choose whether to accuse or use hunt! effect
		 * bot will give preference to accuse another player 
		 */
		if (accusable(game) != null) {
			Player accusedPlayer = accusable(game);
			game.getAccuse()[0] = this.getPlayerId();
			game.getAccuse()[1] = accusedPlayer.getPlayerId();
			System.out.printf("Player %d accuses player %d of being a witch\n", this.getPlayerId(), accusedPlayer.getPlayerId());
			messageLabel.setText(String.format("accuse player %d", accusedPlayer.getPlayerId()));
			game.getAccuse()[0] = this.getPlayerId();
			game.getAccuse()[1] = accusedPlayer.getPlayerId();
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			accusedPlayer.beAccused(game);
		}
		else {
			//use a Rumour card
		}
		
	}
	
	@Override
	public void beAccused(Game game) {
		Player accusePlayer = game.findPlayer(game.getAccuse()[0]);
		Player accusedPlayer = game.findPlayer(game.getAccuse()[1]);
		//situation 1: reveal identity
		if (this.getIdentity() == Identity.Witch) {
			//use Witch? effect
			System.out.printf("Player %d chooses to reveal identity card\n", this.getPlayerId());
			setMessageLabel("reveal identity card");
			this.revealIdentity();
			if (this.getIdentity() == Identity.Villager) {
				System.out.printf("Player %d gains no point, player %d will play next turn\n",accusePlayer.getPlayerId(),this.getPlayerId());
				accusePlayer.updatePoints(0);
				game.setCurrentPlayer(accusedPlayer);
			}
			else {
				System.out.printf("Player %d gains 1 point, player %d will play next turn\n", accusePlayer.getPlayerId(),accusePlayer.getPlayerId());
				accusePlayer.updatePoints(1);
				game.setCurrentPlayer(accusePlayer);
			}
		}
		else {
			System.out.printf("Player %d chooses to reveal identity card\n", this.getPlayerId());
			setMessageLabel("reveal identity card");
			this.revealIdentity();
			if (this.getIdentity() == Identity.Villager) {
				System.out.printf("Player %d gains no point, player %d will play next turn\n",accusePlayer.getPlayerId(),this.getPlayerId());
				accusePlayer.updatePoints(0);
				game.setCurrentPlayer(accusedPlayer);
			}
			else {
				System.out.printf("Player %d gains 1 point, player %d will play next turn\n", accusePlayer.getPlayerId(),accusePlayer.getPlayerId());
				accusePlayer.updatePoints(1);
				game.setCurrentPlayer(accusePlayer);
			}
		}
		
	}
	
	
	//iterate playerList, return an instance of Player if there is a player whose identity card is not revealed
	//consider card evil eye
	public Player accusable(Game game) {
		boolean accusable = false;
		Player chosenPlayer = null;
		ArrayList<Player> playerList = game.getPlayerList();
		for (Player player : playerList) {
			//consider card evil eye
			if (player.isRevealed() == false && player.getPlayerId() != this.getEvilEye()) {
				accusable = true;
				break;
			}
		}
		
		if (accusable) {
			do {
				chosenPlayer = randomChoose(game);
			} while (chosenPlayer.isRevealed() == true && chosenPlayer.getPlayerId() == this.getEvilEye());
			return chosenPlayer;
		}
		else {
			return null;
		}
	}
}
