package Model.Card;

import Model.Bot;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Scanner;

import javax.swing.JOptionPane;
import Model.CardModel.RumourCard;
import Model.CardModel.RumourCardName;
import Controller.Game;
import Model.Identity;
import Model.Player;


public class PetNewt extends RumourCard {
	public static RumourCardName cardName = RumourCardName.Pet_Newt;
	private ArrayList<RumourCard> getExsistRevealed;

	public PetNewt() {
		super();
		// TODO 自动生成的构造函数存根
	}

	@Override
	public RumourCardName getCardName() {
		// TODO 自动生成的方法存根
		return cardName;
	}
	
	public boolean exsistCards(Game game) {
		int i = 0;
		getExsistRevealed = new ArrayList<RumourCard>();
		for (Player  player : game.getPlayerList()) {
			if (player.equals(game.getCurrentPlayer())) {
				continue;
			}
			if (!player.getRevealedCards().isEmpty()) {
				i++;
				getExsistRevealed.addAll(player.getRevealedCards());
			}
		}
		if (i == 0) {
			return false;
		} 
		else {
			return true;
		}
	}

	@Override
	public void witchEffect(Game game) {
		// TODO 自动生成的方法存根
		game.setCurrentPlayer(game.getCurrentPlayer());
		JOptionPane.showMessageDialog(null, "You will play next turn", "Pet Newt", 1);
		setIsUsed(true);

	}

	@Override
	public void huntEffect(Game game) {
		// TODO 自动生成的方法存根
		Player player = game.getCurrentPlayer();
		
		//is there any card is revealed by others
		if (exsistCards(game) == true) {
			System.out.println("Take one revealed Rumour card into your hand:");
			for (RumourCard card : getExsistRevealed) {
				
				System.out.printf("%d. %s\n",getExsistRevealed.indexOf(card)+1, card.getCardName().toString());
				System.out.println("Take one of revealed Rumour cards into your hand");
				Scanner scanner = new Scanner(System.in);
				RumourCard chosenCard = getExsistRevealed.get(scanner.nextInt() - 1);
				chosenCard.setIsUsed(false);
				System.out.printf("You take %s into your hand\n", chosenCard.getCardName().toString());
				player.addHand(chosenCard);
				//remove chosen card from previous player
				for(Player p: game.getPlayerList()) {
					if (!p.getRevealedCards().isEmpty()) {
						for (Iterator<RumourCard> it = p.getRevealedCards().iterator(); it.hasNext();) {
							RumourCard rumourCard = it.next();
							if (rumourCard.equals(chosenCard)) {
								it.remove();
							}
							
						}
					}
					
				}
				
				
				//choose the next player
				System.out.println("Choose a player to play next turn");
				player.chooseNextPlayer(game);
			}
		super.isUsed = true;
		} 
		else {
			System.out.println("No one has any revealed rumour card!");
			game.setCurrentPlayer(game.getCurrentPlayer());
		super.isUsed = false;
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
		//is there any card is revealed by others
				if (exsistCards(game) == true) {
					for (RumourCard card : getExsistRevealed) {
						RumourCard chosenCard = getExsistRevealed.get((int) (Math.random() * (player.getHand().size())));
						chosenCard.setIsUsed(false);
						player.addHand(chosenCard);
						//remove chosen card from previous player
						for(Player p: game.getPlayerList()) {
							if (!p.getRevealedCards().isEmpty()) {
								for (Iterator<RumourCard> it = p.getRevealedCards().iterator(); it.hasNext();) {
									RumourCard rumourCard = it.next();
									if (rumourCard.equals(chosenCard)) {
										it.remove();
									}
									
								}
							}
							
						}
						
						
						//choose the next player
						player.chooseNextPlayer(game);
					}
					setIsUsed(true);
				} 
				else {
					player.chooseNextPlayer(game);
					System.out.printf("Player %d takes next turn\n", game.getCurrentPlayer().getPlayerId());
					setIsUsed(false);
				}
	}

}
