package RumourCards;

import java.util.Scanner;

import WitchHunt.Game;
import WitchHunt.Identity;
import WitchHunt.Player;

public class TheInquisition extends RumourCard {
	
	public static RumourCardName cardName = RumourCardName.The_Inquisition;

	public TheInquisition() {
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
		// TODO 自动生成的方法存根
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

}
