package RumourCards;

import java.util.Scanner;

import Model.Bot;
import Controller.Game;
import Model.Identity;
import Model.Player;

public class TheInquisition extends RumourCard {
	
	public static RumourCardName cardName = RumourCardName.The_Inquisition;

	public TheInquisition() {
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
		
		if(player.getHand().size() > 1) {
			System.out.println("You have these Rumour cards:");
			for (RumourCard rumourCard : player.getHand()) {
				if(rumourCard.getCardName() == RumourCardName.The_Inquisition) {
					continue;
				}
				System.out.printf("%d. %s\n", player.getHand().indexOf(rumourCard)+1, rumourCard.getCardName().toString() );
			}
			System.out.println("Which card will you discard ?");
			Scanner scanner = new Scanner(System.in);
			RumourCard discarded = player.getHand().remove(scanner.nextInt()-1);
			System.out.println("You discard " + discarded.getCardName().toString());
			game.discardPile.add(discarded);
		}
		else {
			System.out.println("You don't have any card in hand!");
		}
		setIsUsed(true);
	}

	@Override
	public void huntEffect(Game game) {
		// TODO 鑷姩鐢熸垚鐨勬柟娉曞瓨鏍�
		//Only playable if you have been revealed as a villager
		Player player = game.getCurrentPlayer();
		if(player.isRevealed() == true && player.getIdentity() == Identity.Villager) {
			System.out.println("Choose a player to play next turn, you can look at his/her identity");
			player.chooseNextPlayer(game);
			//currentPlayer has changed to the next chosen player 
			String nextIdentity = game.getCurrentPlayer().getIdentity().toString();
			System.out.printf("The player you choose is a %s",nextIdentity);
			setIsUsed(true);
			
		}
		else {
			System.out.println(this.getCardName() + " is only playable if you have been revealed as a villager");
			setIsUsed(false);
		}
	}

	@Override
	public void robotWitchEffect(Game game) {
		// TODO 鑷姩鐢熸垚鐨勬柟娉曞瓨鏍�
		Bot player = (Bot) game.getCurrentPlayer();
		
		if(player.getHand().size() > 1) {
			RumourCard chosenCard = null;
			int index;
			do {
			index = (int) (Math.random() * (player.getHand().size()));
			chosenCard = player.getHand().get(index);
			}while (chosenCard.getCardName() == RumourCardName.The_Inquisition);
			
			System.out.printf("Player %d discards %s", player.getPlayerId(), chosenCard.getCardName().toString());
			player.getHand().remove(index);
			
			RumourCard discarded = player.getHand().remove(index);
			game.discardPile.add(discarded);
		}
		else {
			player.revealIdentity();
		}
		setIsUsed(true);
	}


	@Override
	public void robotHuntEffect(Game game) {
		// TODO 鑷姩鐢熸垚鐨勬柟娉曞瓨鏍�
		//Only playable if you have been revealed as a villager
				Bot player = (Bot) game.getCurrentPlayer();
				if(player.isRevealed() == true && player.getIdentity() == Identity.Villager) {
					player.chooseNextPlayer(game);
					//currentPlayer has changed to the next chosen player 
					String nextIdentity = game.getCurrentPlayer().getIdentity().toString();
					//for bot 
					//System.out.printf("The player you choose is a %s",nextIdentity);
					setIsUsed(true);
					
				}
				//accuse another
				else {
					if(player.getHand().size() > 1) {
						
					}
//					else(player.acc)
					setIsUsed(false);
				}
			}
	

}
