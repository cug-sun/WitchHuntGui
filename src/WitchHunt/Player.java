package WitchHunt;
import java.util.ArrayList;
import java.util.Scanner;

import RumourCards.RumourCard;
import RumourCards.RumourCardName;



public class Player {
	private int playerId;
	protected ArrayList<RumourCard> hand;
	//Identity
	private Identity identity;
	//whether the identity has been revealed, original value: false
	private boolean isIdRevealed;
	//points of player
	private int points;
	//pile of revealed Rumour cards
	protected ArrayList<RumourCard> revealedCards;
	//if this player is chosen by Evil Eye, id of the player who uses Evil eye
	private int evilEye;
	
	public Player(int playerId) {
		this.playerId = playerId;
		this.points = 0;
		this.isIdRevealed = false;
		this.hand = new ArrayList<RumourCard>();
		this.revealedCards = new ArrayList<RumourCard>();
		this.evilEye = 0;
	}
	//get field isIdReavealed
	public boolean isRevealed() {
		return this.isIdRevealed;
	}
	public void setIsRevealed(boolean isRevealed) {
		this.isIdRevealed = isRevealed;
	}
	
	public int getPlayerId() {
		return this.playerId;
	}
	
	public int getEvilEye() {
		return evilEye;
	}
	public void setEvilEye(int evilEye) {
		this.evilEye = evilEye;
	}
	public Identity getIdentity() {
		return this.identity;
	}
	public void setIdentity(Identity identity) {
		this.identity = identity;
	}
	
	
	public ArrayList<RumourCard> getRevealedCards(){
		return this.revealedCards;
	}
	
	public void addHand(RumourCard card) {
		this.hand.add(card);
	}
	
	public void displayHand() {
		for (RumourCard rumourCard : hand) {
			System.out.printf("%d.%s\n", hand.indexOf(rumourCard)+1,rumourCard.getCardName().toString());
		}
	}
	
	public boolean revealIdentity() {
		if(this.isIdRevealed == false) {
			this.isIdRevealed = true;
			System.out.println("Player "+ this.playerId + "'s identity card is revealed, he/she is a "+ this.identity);
			return true;
		}
		else {
			System.out.println("Player "+ this.playerId + " has been revealed as a " + this.identity);
			return false;
		}
	}
	
	public void chooseNextPlayer(Game game) {
		System.out.println("You choose which player to play next turn ?");
		for (Player  player : game.getPlayerList()) {
			if (player.equals(game.getCurrentPlayer())) {
				continue;
			}
			System.out.printf("Player %d\n", player.getPlayerId());
		}
		Scanner scanner = new Scanner(System.in);
		boolean correct = true;
		Player choosedPlayer;
		do {
			int choosedId = scanner.nextInt();
			choosedPlayer = game.findPlayer(choosedId);
			if (choosedPlayer != null) {
				correct = false;
				
			}
			else {
				System.out.println("Invalide input! Input a correct playerId");
				correct = true;
			}
		} while (correct);
		
		System.out.printf("You choose player %d to play next turn\n", choosedPlayer.getPlayerId());
		game.setCurrentPlayer(game.findPlayer(choosedPlayer.getPlayerId()));
	}
	
	public void discard(Game game) {
		if(!this.hand.isEmpty()) {
			System.out.println("You have these Rumour cards:");
			for (RumourCard rumourCard : hand) {
				System.out.printf("%d. %s\n", hand.indexOf(rumourCard)+1, rumourCard.getCardName().toString() );
			}
			System.out.println("Which card will you discard ?");
			Scanner scanner = new Scanner(System.in);
			RumourCard discarded = hand.remove(scanner.nextInt()-1);
			System.out.println("You discard " + discarded.getCardName().toString());
			game.discardPile.add(discarded);
		}
		else {
			System.out.println("This player doesn't have any card in hand");
		}
	}
	
	//update player's points in the game
	public void updatePoints(int points) {
		this.points += points;
	}
	
	public int getPoint() {
		return this.points;
	}
	
	public ArrayList<RumourCard> getHand(){
		return this.hand;
	}
		
	public void chooseIdentity() {
		System.out.printf("Player %d chooses to be a 1.Villager 2.Witch\n",this.getPlayerId());
		Scanner scanner = new Scanner(System.in);
		while (true) {
			try {
				int identity = scanner.nextInt();
				if (identity < 1 || identity > 2) {
					System.out.println("Invalide input! Input again");
					scanner = new Scanner(System.in);
				}
				else {
					switch (identity) {
					case 1: {
						this.setIdentity(Identity.Villager);
						break;
					}
					case 2: {
						this.setIdentity(Identity.Witch);
						break;
					}
				}
					break;
				}
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("Invalide input! Input again");
				scanner = new Scanner(System.in);
			}
			
			
		}
	}
	
	//when a player is accused to be a witch
	public void beAccused(Game game) {
		game.displayStatus();
		Player accusePlayer = game.findPlayer(game.getAccuse()[0]);
		Player accusedPlayer = game.findPlayer(game.getAccuse()[1]);
		if (!this.hand.isEmpty()) {
			System.out.printf("Player %d, you are accused by player %d\n", this.getPlayerId(),accusePlayer.getPlayerId());
			System.out.println("You must either:\n" + 
			"1.Reveal your identity card.\nor\n" + 
					"2.Reveal a Rumour card from you hand and"
					+ " play it face up in front of yourself, "
					+ "resolving its Witch? effect.");
			System.out.println("You have these cards:");
			this.displayHand();
			Scanner scanner = new Scanner(System.in);
			switch (scanner.nextInt()) {
			case 1: {
				//reveal identity card
				System.out.println("You choose to revealed your identity card");
				this.revealIdentity();
				if (this.getIdentity() == Identity.Villager) {
					System.out.printf("Player %d is a villager, player %d gains no point, player %d will play next turn\n",
							this.getPlayerId(),accusePlayer.getPlayerId(),this.getPlayerId());
					accusePlayer.updatePoints(0);
					game.setCurrentPlayer(accusedPlayer);
				}
				else if (this.getIdentity() == Identity.Witch) {
					System.out.printf("Player %d is a witch, player %d gains 1 point, player %d will play next turn\n",
							this.getPlayerId(),accusePlayer.getPlayerId(),accusePlayer.getPlayerId());
					accusePlayer.updatePoints(1);
					game.setCurrentPlayer(accusePlayer);
				}
				break;
			}
			case 2: {
				//resolve witch! effect
				game.setCurrentPlayer(this);
				System.out.println("You choose to reveal a Rumour card from you hand and resolving its Witch? effect");
				System.out.println("You have these Rumour cards in your hand, which one do you want to use ?");
				this.displayHand();
				RumourCard chosenCard = hand.get(scanner.nextInt()-1);
				System.out.printf("You choose %s to effect it's Witch? effect\n",chosenCard.getCardName().toString());
				chosenCard.witchEffect(game);
				//add this card to revealed card pile
				if(chosenCard.getIsUsed() == true) {
					hand.remove(chosenCard);
					revealedCards.add(chosenCard);
				}
				else {
					this.beAccused(game);
				}
				break;
			}
			default:
				throw new IllegalArgumentException("Unexpected value: " + scanner.nextInt());
			}
		}
		else {
			System.out.println("You don't have any card in your hand, you must reveal your identity card");
			this.revealIdentity();
		}
		
	}
	
	
	public boolean haveBroomstick() {
		boolean broomstick = false;
		for (RumourCard rumourCard : revealedCards) {
			if (rumourCard.getCardName() == RumourCardName.Broomstick) {
				broomstick = true;
				break;
			}
		}
		return broomstick;
	}
	
	public boolean existRevealedCard(RumourCardName cardName) {
		boolean exist = false;
		for (RumourCard rumourCard : revealedCards) {
			if (rumourCard.getCardName() == cardName) {
				exist = true;
				break;
			}
		}
		return exist;
	}
	
	public void playTurn(Game game) {
		ArrayList<Player> playerList = game.getPlayerList();
		//when Evil eye is used
		if (this.getEvilEye() != 0) {
			System.out.printf("Player %d, you are chosen by Evil Eye, you must accuse a player other than player %d if possible\n", 
					this.getPlayerId(), this.getEvilEye());
			System.out.println("You can accuse another player of being a Witch\nWhich player ?");
			
			if (playerList.size() > 2) {
				for (Player player : playerList) {
					if(player == this) {
						continue;
					}
					else if (player.getPlayerId() == this.getEvilEye()) {
						continue;
					}
					else {
						if (player.isRevealed() == false) {
							System.out.printf("Player %d\n", player.getPlayerId());
						}
						else {
							System.out.printf("Player %d has been revealed as a %s\n", player.getPlayerId(),player.getIdentity().toString());
						}
					}
				}
			}
			else {
				//if there are only 2 players, then must accuse the player who used Evil Eye
				System.out.println("There are only 2 players...");
				for (Player player : playerList) {
					if(player == this) {
						continue;
					}
					else {
						if (player.isRevealed() == false) {
							System.out.printf("Player %d\n", player.getPlayerId());
						}
						else {
							System.out.printf("Player %d has been revealed as a %s\n", player.getPlayerId(),player.getIdentity().toString());
						}
					}
				}
			}
			this.setEvilEye(0);
			Scanner scanner = new Scanner(System.in);
			int chosenId = scanner.nextInt();
			Player accusedPlayer = game.findPlayer(chosenId);
			
			//whether this player can be accused
			if(accusedPlayer.isRevealed() == true) {
				System.out.println("This player's identity is revealed, you can't accuse him/her");
				game.setCurrentPlayer(this);
			}
			else {
			game.getAccuse()[0] = this.getPlayerId();
			game.getAccuse()[1] = accusedPlayer.getPlayerId();
			//the accused player acts
			accusedPlayer.beAccused(game);
			//setCurrentPlayer(accusedPlayer);
			}
		}
		
		//general situation
		else {
			System.out.printf("Player %d, it's your turn\n", this.getPlayerId());
			System.out.println("You have these cards:");
			this.displayHand();
			System.out.println("you must either:\n" +
					"1.Accuse another player of being a Witch.\nor\n"
					+ "2.Reveal a Rumour card from your hand and play it face up in front of yourself, resolving its Hunt! effect.");
			Scanner scanner = new Scanner(System.in);
			switch (scanner.nextInt()) {
			case 1: {
				//Accuse another player of being a Witch
				System.out.println("You choose to accuse another player of being a Witch\nWhich player ?");
				game.displayUnaccusedPlayers();
				int chosenId = scanner.nextInt();
				Player accusedPlayer = game.findPlayer(chosenId);
				
				//this player can or can't be accused
				if(accusedPlayer.isRevealed() == true) {
					System.out.println("this player'identity is revealed, you can't accuse him/her");
					game.setCurrentPlayer(this);
				}
				else {
					game.getAccuse()[0] = this.getPlayerId();
					game.getAccuse()[1] = accusedPlayer.getPlayerId();
					//the accused player acts
					accusedPlayer.beAccused(game);
					//setCurrentPlayer(accusedPlayer);
				}
				break;
				
			}
			case 2: {
				Player player = this;
				//Reveal a Rumour card from hand, resolving its Hunt! effect
				if(!this.getHand().isEmpty()) {
					System.out.println("You have these Rumour cards:");
					this.displayHand();
					System.out.println("Which card do you want to use ?");
					RumourCard choosedCard = this.getHand().get(scanner.nextInt()-1);
					System.out.printf("You choose to use %s\n",choosedCard.getCardName().toString());
					//current player may have been changed
					choosedCard.huntEffect(game);
					if (choosedCard.getIsUsed() == true) {
						player.getHand().remove(choosedCard);
						//after using Black Cat, discard it
						if (choosedCard.getCardName() == RumourCardName.Black_Cat) {
							game.discardPile.add(choosedCard);
						}
						else {
							player.getRevealedCards().add(choosedCard);
						}
					}
					break;
				}
				else {
					System.out.println("You don't have any card in hand! You must accuse another player of being a witch");
					game.setCurrentPlayer(this);
					break;
				}
				
				
			}
			default:
//				throw new IllegalArgumentException("Unexpected value: " + scanner);
				System.out.println("Invalide input! Input again");
			}
		}
	}
}
