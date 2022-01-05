package RumourCards;

import Model.Bot;
import Controller.Game;
import Model.Identity;
import Model.Player;

public class Toad extends RumourCard {
	public static RumourCardName cardName = RumourCardName.Toad;
	

	public Toad() {
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
		game.setCurrentPlayer(game.getCurrentPlayer());
		setIsUsed(true);
	}

	@Override
	public void huntEffect(Game game) {
		// TODO 鑷姩鐢熸垚鐨勬柟娉曞瓨鏍�
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
		System.out.printf("Player %d takes next turn\n", game.getCurrentPlayer().getPlayerId());
		game.setCurrentPlayer(game.getCurrentPlayer());
		setIsUsed(true);	
	}

	@Override
	public void robotHuntEffect(Game game) {
		Bot player = (Bot) game.getCurrentPlayer();
		if(player.isRevealed() == true) {
			game.setCurrentPlayer(player);
			super.isUsed = false;
			}else {
				player.revealIdentity();
				if(player.getIdentity() == Identity.Villager) {
					int leftIndex = game.getPlayerList().indexOf(player) + 1;
					if(leftIndex == game.getPlayerList().size()) {
						leftIndex = 0;
						Bot chosenPlayer = (Bot)game.getPlayerList().get(leftIndex);
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
				if (player.getIdentity() == Identity.Witch) {
					player.chooseNextPlayer(game);
					System.out.printf("Player %d takes next turn\n", game.getCurrentPlayer().getPlayerId());
					setIsUsed(true);
				}
		
				}

			}
	}
}
