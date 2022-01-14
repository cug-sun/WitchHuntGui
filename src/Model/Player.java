package Model;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import Controller.Game;
import Model.CardModel.RumourCard;
import Model.CardModel.RumourCardName;

/**
 * Player encapsulates the operations of players.
 * @author SUN Sun
 * @author ZHANG Xiao
 * @see Game
 */
public class Player {
	/**
	 * Identifier of this player.
	 */
	private int playerId;
	/**
	 * Contains this player's Rumour cards
	 */
	protected ArrayList<RumourCard> hand;
	/**
	 * This player's identity.  
	 */
	private Identity identity;
	/**
	 * Whether this player's identity card has been revealed.
	 * <p>
	 * Default: {@code false}.
	 */
	private boolean isIdRevealed;
	/**
	 * This player's points.
	 */
	private int points;
	/**
	 * Contains this player's revealed Rumour cards.
	 */
	protected ArrayList<RumourCard> revealedCards;
	/**
	 * {@code playerId} of the player who used {@code Evil Eye} to this player, default: 0.
	 */
	private int evilEye;
	/**
	 * Image shows that this player's identity card is not revealed.
	 */
	public static Image unknownImage = null;
	/**
	 * Image shows that this player's identity card is {@code Witch}
	 */
	public static Image witchImage = null;
	/**
	 * Image shows that this player's identity card is {@code Villager}
	 */
	public static Image villagerImage = null;
	/**
	 * The component to show message in GUI.
	 */
	public JLabel messageLabel;
	/**
	 * The component to show identity in GUI.
	 */
	public JLabel identityLabel;
	/**
	 * The component to show points in GUI.
	 */
	public JLabel scoreLabel;
	/**
	 * The component to show revealed cards in GUI.
	 */
	public JLabel revealedCardLabel;
	/**
	 * Initializes states of this player.
	 * @param playerId identifier of this player
	 */
	public Player(int playerId) {
		loadImage();
		
		this.playerId = playerId;
		this.points = 0;
		this.isIdRevealed = false;
		this.hand = new ArrayList<RumourCard>();
		this.revealedCards = new ArrayList<RumourCard>();
		this.evilEye = 0;
		
		this.messageLabel = new JLabel(String.format("Player %d", playerId));
		
		this.identityLabel = new JLabel(new ImageIcon(unknownImage));
		
		String message = String.format("Points: %d", points);
		this.scoreLabel = new JLabel(message);
		
		
		this.revealedCardLabel = new JLabel();
	}
	/**
	 * Reads identity images.
	 */
	public void loadImage() {
		try {
			unknownImage = ImageIO.read(new File("./image/identity/unknown1.png")).getScaledInstance(117, 168,Image.SCALE_SMOOTH);
			witchImage = ImageIO.read(new File("./image/identity/witch.png")).getScaledInstance(117, 168,Image.SCALE_SMOOTH);
	    	villagerImage = ImageIO.read(new File("./image/identity/villager.png")).getScaledInstance(117, 168,Image.SCALE_SMOOTH);
		} catch (IOException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	/**
	 * Gets {@code isRevealed}
	 * @return {@code true} if identity card is revealed, {@code false} otherwise
	 */
	public boolean isRevealed() {
		return this.isIdRevealed;
	}
	/**
	 * Sets {@code isRevealed}
	 * @param isRevealed {@code true} if identity card is revealed, {@code false} otherwise
	 */
	public void setIsRevealed(boolean isRevealed) {
		this.isIdRevealed = isRevealed;
	}
	/**
	 * Gets this player's identifier.
	 * @return identifier of this player
	 */
	public int getPlayerId() {
		return this.playerId;
	}
	/**
	 * Gets {@code playerId} of the player who used {@code Evil Eye} to this player, default: 0
	 * @return identifier of a player or {@code 0}
	 */
	public int getEvilEye() {
		return evilEye;
	}
	/**
	 * Sets {@code playerId} of the player who used {@code Evil Eye} to this player.
	 * @param evilEye identifier of a player
	 */
	public void setEvilEye(int evilEye) {
		this.evilEye = evilEye;
	}
	/**
	 * Gets this player's identity
	 * @return {@code Witch} or {@code Villager}
	 */
	public Identity getIdentity() {
		return this.identity;
	}
	/**
	 * Sets this player's identity
	 * @param identity {@code Witch} or {@code Villager}
	 * @see Identity
	 */
	public void setIdentity(Identity identity) {
		this.identity = identity;
	}
	
	/**
	 * Gets the revealed cards of this player. 
	 * @return ArrayList contains Rumour cards
	 */
	public ArrayList<RumourCard> getRevealedCards(){
		return this.revealedCards;
	}
	/**
	 * Sets a message on {@code messageLabel}.
	 * @param message {@code String} message
	 */
	public void setMessageLabel(String message) {
		messageLabel.setText(message);
	}
	/**
	 * Gets Rumour cards in this player's hand.
	 * @param card ArrayList with Rumour cards
	 */
	public void addHand(RumourCard card) {
		this.hand.add(card);
	}
	/**
	 * Print this player's cards in command line.
	 */
	public void displayHand() {
		for (RumourCard rumourCard : hand) {
			System.out.printf("%d.%s\n", hand.indexOf(rumourCard)+1,rumourCard.getCardName().toString());
		}
	}
	/**
	 * Reveal this player's identity card.
	 * @return {@code true} if identity card is revealed successfully, {@code false} otherwise
	 */
	public boolean revealIdentity() {
		if(this.isIdRevealed == false) {
			this.isIdRevealed = true;
			System.out.println("Player "+ this.playerId + "'s identity card is revealed, he/she is a "+ this.identity);
			JOptionPane.showMessageDialog(null, String.format("Player %d reveals identity card", this.getPlayerId()), null, 1);
			if(this.identity == Identity.Villager) {
				identityLabel.setIcon(new ImageIcon(villagerImage));
			}
			else if (this.identity == Identity.Witch) {
				identityLabel.setIcon(new ImageIcon(witchImage));
			} 
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				// TODO 自动生成的 catch 块
				e.printStackTrace();
			}
			return true;
			
		}
		else {
			System.out.println("Player "+ this.playerId + " has been revealed as a " + this.identity);
			return false;
		}
		
	}
	/**
	 * Choose a player to play next turn
	 * @param game reference of {@link Game}
	 */
	public void chooseNextPlayer(Game game) {
		System.out.println("You choose which player to play next turn ?");
		for (Player  player : game.getPlayerList()) {
			if (player.equals(game.getCurrentPlayer())) {
				continue;
			}
			System.out.printf("Player %d\n", player.getPlayerId());
		}
		ArrayList<Integer> idList = new ArrayList<Integer>();
		for(int i = 1; i < game.getPlayerList().size(); i++) {
			Player player = game.getPlayerList().get(i);
			
			idList.add(player.getPlayerId());
			
		}
		Object[] options = idList.toArray();
		int chosenId = (int) JOptionPane.showInputDialog(null, "player", "Choose next player", 1, null, options, options[0]);
		Player chosenPlayer = game.findPlayer(chosenId);
		System.out.printf("You choose player %d to play next turn\n", chosenPlayer.getPlayerId());
		game.setCurrentPlayer(chosenPlayer);
	}
	/**
	 * This player chooses to discard a Rumour card from his hand.
	 * @param game reference of {@link Game}
	 */
	public void discard(Game game) {
		if(!this.hand.isEmpty()) {
			System.out.println("You have these Rumour cards:");
			for (RumourCard rumourCard : hand) {
				System.out.printf("%d. %s\n", hand.indexOf(rumourCard)+1, rumourCard.getCardName().toString() );
			}
			System.out.println("Which card will you discard ?");
			ArrayList<String> cardList = new ArrayList<String>();
			for (RumourCard card: hand) {
				cardList.add(card.getCardName().toString());
			}
			Object[] options = cardList.toArray();
			int chosenIndex = (int) JOptionPane.showInputDialog(null, "You discard", "You have these cards", 1, null, options, options[0]);
			RumourCard chosenCard = hand.get(chosenIndex);
			
			System.out.println("You discard " + chosenCard.getCardName().toString());
			hand.remove(chosenIndex);
			game.discardPile.add(chosenCard);
			game.getGamePane().repaint();
		}
		else {
			System.out.println("This player doesn't have any card in hand");
		}
	}
	
	/**
	 * Update this player's points.
	 * @param points {@code int} points
	 */
	public void updatePoints(int points) {
		this.points += points;
		String message = String.format("Points: %d", points);
		scoreLabel.setText(message);
	}
	/**
	 * Gets this player's points
	 * @return {@code int} points
	 */
	public int getPoint() {
		return this.points;
	}
	/**
	 * Gets this player's Rumour cards.
	 * @return ArrayList with Rumour cards.
	 */
	public ArrayList<RumourCard> getHand(){
		return this.hand;
	}
	/**
	 * This player chooses his identity.
	 * @see Identity
	 */
	public void chooseIdentity() {
		String[] options = {"Witch","Villager"};
		String identity = (String)JOptionPane.showInputDialog(null,"You choose to be", "Identity",JOptionPane.INFORMATION_MESSAGE, null,options,options[0] );
		if(identity == "Villager") {
			this.setIdentity(Identity.Villager);
		}
		else {
			this.setIdentity(Identity.Witch);
		}
	}
	
	/**
	 * When this player is accused by another player, he performs a series of actions.
	 * <p>
	 * This player can choose to reveal identity card or use Witch? effect of a card.
	 * @param game reference of {@link Game}
	 */
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
			String[] actions = {"reveal","use Witch?"};
			int choice = JOptionPane.showOptionDialog(null, "You choose to", String.format("You are accused by player %d", accusePlayer.getPlayerId()),1, 3, null, actions, actions[0]);
			switch (choice) {
			case 0: {
				
				revealIdentity();
				switch (this.identity) {
				case Villager: {
					System.out.printf("Player %d is a villager, player %d gains no point, player %d will play next turn\n",
					this.getPlayerId(),accusePlayer.getPlayerId(),this.getPlayerId());
					accusePlayer.updatePoints(0);
					game.setCurrentPlayer(accusedPlayer);
					break;
					}
				case Witch: {
					System.out.printf("Player %d is a witch, player %d gains 1 point, player %d will play next turn\n",
					this.getPlayerId(),accusePlayer.getPlayerId(),accusePlayer.getPlayerId());
					accusePlayer.updatePoints(1);
					game.setCurrentPlayer(accusePlayer);
					break;
					}
				
				}
				break;
			}
			case 1:{
				game.setCurrentPlayer(this);
				System.out.println("You choose to reveal a Rumour card from you hand and resolving its Witch? effect");
				System.out.println("You have these Rumour cards in your hand, which one do you want to use ?");
				this.displayHand();
				ArrayList<String> cardList = new ArrayList<String>();
				for (RumourCard card: hand) {
					cardList.add(card.getCardName().toString());
				}
				Object[] options = cardList.toArray();
				String chosenCarName = (String)JOptionPane.showInputDialog(null, "You choose", "You have these cards", 1, null, options, options[0]);
				RumourCard chosenCard = null;
				for(RumourCard card: hand) {
					if(card.getCardName().toString() == chosenCarName) {
						chosenCard = card;
					}
				}
				System.out.printf("You choose to use %s\n",chosenCard.getCardName().toString());
				chosenCard.witchEffect(game);
				//add this card to revealed card pile
				if(chosenCard.getIsUsed() == true) {
					hand.remove(chosenCard);
					revealedCards.add(chosenCard);
					game.getGamePane().repaint();
				}
				else {
					this.beAccused(game);
				}

				break;
			}
		}
		}
		else {
			System.out.println("You don't have any card in your hand, you must reveal your identity card");
			JOptionPane.showMessageDialog(null, "You don't have any card in your hand, you must reveal your identity card", null, 1);
			this.revealIdentity();
		}
		
	}
	
	/**
	 * Judges if this player has a {@code Broomstick} in his hand.
	 * @return {@code true} if this player has a {@code Broomstick}, {@code false} otherwise
	 */
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
	/**
	 * Judges if this player has a {@code Wart} in his hand.
	 * @return {@code true} if this player has a {@code Wart}, {@code false} otherwise
	 */
	public boolean haveWart() {
		boolean wart = false;
		for(RumourCard rumourCard : revealedCards) {
			if (rumourCard.getCardName() == RumourCardName.Wart) {
				wart = true;
				break;
			}
		}
		return wart;
	}
	/**
	 * Judges if this player has any revealed Rumour card.
	 * @param cardName {@link RumourCardName}
	 * @return {@code true} if this player has a revealed Rumour card, {@code false} otherwise
	 */
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
	/**
	 * In this player's turn, he performs a series of actions.
	 * <p>
	 * This player can choose to accuse another player of being witch
	 * or use Hunt! effect of a Rumour card.
	 * @param game reference of {@link Game}
	 */
	public void playTurn(Game game) {

		String[] actions = {"accuse","use Hunt!"};
		int choice = JOptionPane.showOptionDialog(null, "You choose to", "It's your turn",1, 3, null, actions, actions[0]);
		
		switch (choice) {
		case 0: {
			
			ArrayList<Integer> idList = new ArrayList<Integer>();
			for(int i = 1; i < game.getPlayerList().size(); i++) {
				Player player = game.getPlayerList().get(i);
				if (player.getEvilEye() == 0) {
					idList.add(player.getPlayerId());
				}
			}
			Object[] options = idList.toArray();
			int chosenId = (int) JOptionPane.showInputDialog(null, "player", "You accuse", 1, null, options, options[0]);
			System.out.printf("You accuse player %d of being a witch\n", chosenId);
			Player chosenPlayer = game.findPlayer(chosenId);
			game.getAccuse()[0] = game.getCurrentPlayer().getPlayerId();
			game.getAccuse()[1] = chosenId;
			chosenPlayer.beAccused(game);
			break;
		}
		case 1:{
			ArrayList<String> cardList = new ArrayList<String>();
			for (RumourCard card: hand) {
				cardList.add(card.getCardName().toString());
			}
			Object[] options = cardList.toArray();
			String chosenCarName = (String)JOptionPane.showInputDialog(null, "You choose", "You have these cards", 1, null, options, options[0]);
			RumourCard chosenCard = null;
			for(RumourCard card: hand) {
				if(card.getCardName().toString() == chosenCarName) {
					chosenCard = card;
				}
			}
			System.out.printf("You choose to use %s\n",chosenCard.getCardName().toString());
			chosenCard.huntEffect(game);
			if (chosenCard.getIsUsed() == true) {
			this.getHand().remove(chosenCard);
			//after using Black Cat, discard it
			if (chosenCard.getCardName() == RumourCardName.Black_Cat) {
				game.discardPile.add(chosenCard);
			}
			else {
				this.getRevealedCards().add(chosenCard);
			}
			game.getGamePane().invalidate();
			break;
			}
		}
		
		}
	}
}
