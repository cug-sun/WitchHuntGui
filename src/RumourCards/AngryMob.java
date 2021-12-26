package RumourCards;

import java.util.Scanner;

import WitchHunt.Bot;
import WitchHunt.Game;
import WitchHunt.Identity;
import WitchHunt.Player;

public class AngryMob extends RumourCard {
	
	public static RumourCardName cardName = RumourCardName.Angry_Mob;
	public AngryMob() {
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
		//take next turn
		System.out.println("You will take next turn");
		game.setCurrentPlayer(game.getCurrentPlayer());
		setIsUsed(true);
	}

	@Override
	public void huntEffect(Game game) {
		// TODO 自动生成的方法存根
		Player player = game.getCurrentPlayer();
		//Only playable if you have been revealed as a villager
		if(player.isRevealed() == true && player.getIdentity() == Identity.Villager) {
			//reveal another player's identity
			Scanner scanner = new Scanner(System.in);
			System.out.println("You can reveal another player's identity\nInput the player's id");
			int choosedId = scanner.nextInt();
			Player chosenPlayer = game.findPlayer(choosedId);
			//when a player has a revealed Broomstick, he/she can't be chosen by Angry Mob
			boolean broomStick = false;
			for (RumourCard card : chosenPlayer.getRevealedCards()) {
				if(card.getCardName() == RumourCardName.Broomstick) {
					broomStick = true;
					break;
				}	
			}
			
			if(broomStick) {
				System.out.printf("Player %d has a revealed Broomstick, he/she can't be chosen by Angry Mob!\n",chosenPlayer.getPlayerId());
				game.setCurrentPlayer(player);
				setIsUsed(false);
			}
			else {
				chosenPlayer.revealIdentity();
				//calculate point
				if (chosenPlayer.getIdentity() == Identity.Villager) {
					System.out.println("You lose 2 pts, the player whose identity card you reveal plays the next turn");
					player.updatePoints(-2);
					game.setCurrentPlayer(chosenPlayer);
				}
				else if (chosenPlayer.getIdentity() == Identity.Witch) {
					System.out.println("You gain 2 pts, you take next turn");
					player.updatePoints(2);
					game.setCurrentPlayer(player);
				}
				setIsUsed(true);
			}		
		}
		else {
			System.out.println(this.getCardName() + " is only playable if you have been revealed as a villager");
			setIsUsed(false);
		}
	}
	
	@Override
	public void robotWitchEffect(Game game) {
		// TODO 自动生成的方法存根
		System.out.printf("Player %d takes next turn\n", game.getCurrentPlayer().getPlayerId());
		game.setCurrentPlayer(game.getCurrentPlayer());
		setIsUsed(true);
	}

	@Override
	public void robotHuntEffect(Game game) {
		// TODO 自动生成的方法存根
		Bot player = (Bot) game.getCurrentPlayer();
		if(player.isRevealed() == true && player.getIdentity() == Identity.Villager) {
			Player chosenPlayer = null;
			do {
				chosenPlayer = player.randomChoose(game);
			} while (chosenPlayer.isRevealed() == false);
			chosenPlayer.revealIdentity();
			if (chosenPlayer.existRevealedCard(RumourCardName.Broomstick) == false) {
				if(chosenPlayer.getIdentity() == Identity.Witch) {
					System.out.printf("Player %d gains 2 points, he/she plays next turn\n",player.getPlayerId());
					game.setCurrentPlayer(player);
				}
				else {
					System.out.printf("Player %d loses 2 points, player %d plays next turn\n",chosenPlayer.getPlayerId());
					game.setCurrentPlayer(chosenPlayer);
				}
				setIsUsed(true);
			}
			
		}
	}
}
