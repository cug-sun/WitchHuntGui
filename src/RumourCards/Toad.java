package RumourCards;

import WitchHunt.Game;
import WitchHunt.Identity;
import WitchHunt.Player;

public class Toad extends RumourCard {
	public static RumourCardName cardName = RumourCardName.Toad;
	

	public Toad() {
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
		setIsUsed(true);
	}

	@Override
	public void huntEffect(Game game) {
		// TODO 自动生成的方法存根
		//reveal your identity
				Player player = game.getCurrentPlayer();
						
				//determine the player's identity is revealed or not
				if (player.isRevealed() == false) {
					player.revealIdentity();
							
					//Determine the identity
					if(player.getIdentity() == Identity.Villager) {
						//choose a player to take the turn
						System.out.println("you can choose the player to play next turn");
						player.chooseNextPlayer(game);
								
					}
					else {
						//left player takes the turn,the game is played clockwise
						int leftIndex = game.getPlayerList().indexOf(player) + 1;
						if(leftIndex == game.getPlayerList().size()) {
							leftIndex = 0;
							Player chosenPlayer = game.getPlayerList().get(leftIndex);
							System.out.printf("Player %d chooses the player to his/her left to play next turn\n",player.getPlayerId());
							game.setCurrentPlayer(chosenPlayer);
							setIsUsed(true);
						}
						else {
							Player chosenPlayer = game.getPlayerList().get(leftIndex);
							System.out.printf("Player %d chooses the player to his/her left to play next turn\n",player.getPlayerId());
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

	@Override
	public void robotWitchEffect(Game game) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void robotHuntEffect(Game game) {
		// TODO 自动生成的方法存根
		
	}

}
