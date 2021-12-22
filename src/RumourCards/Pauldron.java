package RumourCards;


import WitchHunt.Game;
import WitchHunt.Player;
import WitchHunt.Identity;

public class Pauldron extends RumourCard {
	public static RumourCardName cardName = RumourCardName.Pauldron;
	

	public Pauldron() {
		super();
		// TODO 自动生成的构造函数存根
	}

	@Override
	public RumourCardName getCardName() {
		// TODO 自动生成的构造函数存根
		return cardName;
	}

	@Override
	public void witchEffect(Game game) {
		// TODO 自动生成的构造函数存根
		Player player = game.getCurrentPlayer();
		System.out.println("The player who accused you discards a random card from their hand");
		Player accusePlayer = game.findPlayer(game.getAccuse()[0]);
		
		//Randomly select the discard
		RumourCard chosenCard = game.findPlayer(game.getAccuse()[0]).getHand().get((int)(Math.random() * (player.getHand().size())));
		System.out.printf("Player %d randomly discards %s from his/her hand\n",accusePlayer.getPlayerId(),chosenCard.getCardName().toString());
	    accusePlayer.getHand().remove(chosenCard);
	    game.getDiscardPile().add(chosenCard);
	    game.setCurrentPlayer(player);
	    setIsUsed(true);
	}

	@Override
	public void huntEffect(Game game) {
		// TODO 自动生成的构造函数存根
		//reveal your identity
		Player player = game.getCurrentPlayer();
				
		//determine the player's identity is revealed or not
		if (player.isRevealed() == false) {
			player.revealIdentity();
					
			//Determine the identity
			if(player.getIdentity() == Identity.Villager) {
				//choose a player to take the turn
				System.out.println("you can choose the player who will take the turn");
				player.chooseNextPlayer(game);
						
			}
			else {
				//left player takes the turn,the game is played clockwise
				int leftIndex = game.getPlayerList().indexOf(player) + 1;
				if(leftIndex == game.getPlayerList().size()) {
					leftIndex = 0;
					Player chosenPlayer = game.getPlayerList().get(leftIndex);
					game.setCurrentPlayer(chosenPlayer);
					setIsUsed(true);
				}
				else {
					Player chosenPlayer = game.getPlayerList().get(leftIndex);
					game.setCurrentPlayer(chosenPlayer);
					setIsUsed(true);
				}
			}
		} 
		else {
			//can't use Hunt! effect return to the step choose action
			System.out.println("Your identity has been revealed, you can't use its Hunt! effect");
			game.setCurrentPlayer(player);
			super.isUsed = false;
		}
	}

}
