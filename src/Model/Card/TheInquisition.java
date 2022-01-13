package Model.Card;

import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JOptionPane;
import Model.CardModel.RumourCard;
import Model.CardModel.RumourCardName;
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
			ArrayList<String> cardList = new ArrayList<String>();
			for (RumourCard rumourCard : player.getHand()) {
				if(rumourCard.getCardName() == RumourCardName.The_Inquisition) {
					continue;
				}
				System.out.printf("%d. %s\n", player.getHand().indexOf(rumourCard)+1, rumourCard.getCardName().toString() );
				cardList.add(rumourCard.getCardName().toString());
			}
			Object[] options = cardList.toArray();
			String chosenCarName = (String)JOptionPane.showInputDialog(null, "You discard", "You have these cards", 1, null, options, options[0]);
			RumourCard chosenCard = null;
			for(RumourCard card: player.getHand()) {
				if(card.getCardName().toString() == chosenCarName) {
					chosenCard = card;
				}
			}
			System.out.println("Which card will you discard ?");
//			Scanner scanner = new Scanner(System.in);
//			RumourCard discarded = player.getHand().remove(scanner.nextInt()-1);
			System.out.println("You discard " + chosenCard.getCardName().toString());
			player.getHand().remove(chosenCard);
			game.discardPile.add(chosenCard);
			game.getGamePane().repaint();
			game.setCurrentPlayer(player);
		}
		else {
			System.out.println("You don't have any card in hand!");
			JOptionPane.showMessageDialog(null,"You don't have any card in hand","The inquisition",0);
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
			JOptionPane.showMessageDialog(null,"This player is a "+ nextIdentity,"info",1);
			setIsUsed(true);
			
		}
		else {
			System.out.println(this.getCardName() + " is only playable if you have been revealed as a villager");
			JOptionPane.showMessageDialog(null,"The Inquisition is only playable if you have been revealed as a villager","The Inquisition",0);
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
				//accuse another player
			else{
					player.chooseNextPlayer(game);
					System.out.printf("Player %d takes next turn\n", game.getCurrentPlayer().getPlayerId());
					setIsUsed(false);
				}
			}
	

}
