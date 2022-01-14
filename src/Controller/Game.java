package Controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

import Model.Bot;
import Model.Player;
import Model.Identity;
import Model.Card.AngryMob;
import Model.Card.BlackCat;
import Model.Card.Broomstick;
import Model.Card.DuckingStool;
import Model.Card.EvilEye;
import Model.Card.HookedNose;
import Model.Card.Pauldron;
import Model.Card.PetNewt;
import Model.Card.PointedHat;
import Model.Card.TheInquisition;
import Model.Card.Toad;
import Model.Card.Wart;
import Model.CardModel.RumourCard;
import View.GamePane;


/**
 * Game control class.
 * <p>
 * Controls card distributing and game process.
 * @author SUN Sun
 *
 */
public class Game {
	/**
	 * Card pile.
	 */
	private ArrayList<RumourCard> cardPile;
	/**
	 * Discard pile.
	 */
	public ArrayList<RumourCard> discardPile;
	/**
	 * Player number, initialized at the start of game.
	 */
	private int nPlayer;
	/**
	 * Default Rumour card number {@value #nRumourCards}
	 */
	public static final int nRumourCards = 12;
	/**
	 * Container of players in a game.
	 * <p>
	 * At the end of each turn, it is updated.
	 */
	private ArrayList<Player> playerList;
	/**
	 * Container of player who is out of game.
	 * <p>
	 * At the end of each turn, it is updated.
	 */
	private ArrayList<Player> outPlayerList;
	/**
	 * Reference of the player who is playing current turn.
	 */
	private Player currentPlayer;
	/**
	 * An array to save the accuse relation.
	 * <p>
	 * {@code accuse[0]} is the {@code playerId} of player who accuses,
	 * {@code accuse[1]} is the {@code playerId} of player who is accused.
	 */
	private int[] accuse = new int[2];
	/**
	 * Game UI transmitted to {@code Controller}.
	 */
	private GamePane gamePane;
	/**
	 * Initializes the game.
	 * <ul>
	 * <li> Sets {@code nPlayer}
	 * <li> Sets player's {@code identity}
	 * <li> Initializes the card pile
	 * </ul>
	 */
	public Game() {
		System.out.println("*****************Witch Hunt*****************");
		System.out.println("Choose the number of players (3-6)");
		setPlayers();
		setIdentity();
		initPile();
		distribute();
		
	}
	
	
	/**
	 * Shuffles card pile using {@code Collections.shuffle()}.
	 */
	public void shuffleCard() {
		System.out.println("Shuffle rumour cards...");
		Collections.shuffle(cardPile);
	}
	/**
	 * Initializes card pile.
	 * <p>
	 * Initializes {@code cardPile} and {@code discardPlie}.
	 * <p>
	 * Instantiates all {@code Rumour cards}, then add them to {@code cardPile}.
	 * <p>
	 * Calls {@code shuffleCard()} to shuffle {@code cardPile}.
	 */
	public void initPile() {
		//Initialize the pile
		cardPile = new ArrayList<RumourCard>();
		//Initialize the discard pile
		discardPile = new ArrayList<RumourCard>();
		RumourCard angryMob = new AngryMob();
		RumourCard blackCat = new BlackCat();
		RumourCard broomstick = new Broomstick();
		RumourCard pauldron = new Pauldron();
		RumourCard duckingStool = new DuckingStool();
		RumourCard evilEye = new EvilEye();
		RumourCard hookedNose = new HookedNose();
		RumourCard petNewt = new PetNewt();
		RumourCard pointedHat = new PointedHat();
		RumourCard theInquisition = new TheInquisition();
		RumourCard toad = new Toad();
		RumourCard wart = new Wart();
		cardPile.add(angryMob);
		cardPile.add(blackCat);
		cardPile.add(broomstick);
		cardPile.add(pauldron);
		cardPile.add(duckingStool);
		cardPile.add(evilEye);
		cardPile.add(hookedNose);
		cardPile.add(petNewt);
		cardPile.add(pointedHat);
		cardPile.add(theInquisition);
		cardPile.add(toad);
		cardPile.add(wart);
		shuffleCard();
	}
	/**
	 * Initializes human player and bot players at the begin of game.
	 * <p>
	 * Instantiates all the players, sets their identities, then add them to {@code playerList}.
	 * <p>
	 * Randomly chooses the start player.
	 */
	public void initPlayer() {
		playerList = new ArrayList<Player>();
		outPlayerList = new ArrayList<Player>();
		for(int i = 1; i <= nPlayer; i++) {
			if(i == 1) {
				Player newPlayer = new Player(i);
				playerList.add(newPlayer);
				continue;
			}
			Player newPlayer = new Bot(i);
			playerList.add(newPlayer);
		}
		chooseIdentity();
		//Randomly select start player
		currentPlayer = playerList.get((int)(Math.random() * (nPlayer)));
		System.out.println("Start from player " + (currentPlayer.getPlayerId()));
		
		
	}
	
	/**
	 * Distributes {@code Rumour cards} to the players in {@code playerList}.
	 * <p>
	 * Additionally, in a 5-player game treat the 2 cards placed beside the 
	 * play area at the start of the game as discarded cards.
	 */
	public void distribute() {
		System.out.println("Distribute rumour cards...");
		int nHand = nRumourCards/nPlayer;
		Iterator<RumourCard> it = cardPile.iterator();
		for (Player player : playerList) {
			for(int i = 0; i < nHand; i++) {
				player.addHand(it.next());
				it.remove();
			}
		}
		if(nPlayer == 5) {
			System.out.println("In a 5-player game, treat the 2 cards placed"
					+ " beside the play area at the start of the game as"
					+ " discarded cards");
			System.out.println("Discarded cards:");
			while (it.hasNext()) {
				RumourCard card = it.next();
				discardPile.add(card);
				System.out.println(card.getCardName().toString());
			}
		}
	}
	
	/**
	 * Sets bot player's identity
	 */
	public void chooseIdentity() {
		for (Player player : playerList) {
			if ((player instanceof Bot) == false) {
				continue;
			}
			player.chooseIdentity();
		}
	}
	
	/**
	 * Gets {@code nPlayer}.
	 * @return {@code nPlayer}
	 */
	public int getnPlayer() {
		return nPlayer;
	}
	
	/**
	 * Sets {@code nPlayer}.
	 * @param nPlayer player number
	 */
	public void setnPlayer(int nPlayer) {
		this.nPlayer = nPlayer;
	}
	/**
	 * Gets {@code playerList}.
	 * @return {@code playerList}
	 */
	public ArrayList<Player> getPlayerList(){
		return this.playerList;
	}
	/**
	 * Gets reference of {@code currentPlayer}.
	 * @return {@code currentPlayer}
	 */
	public Player getCurrentPlayer() {
		return this.currentPlayer;
	}
	/**
	 * Sets {@code currentPlayer}
	 * @param player reference of a player
	 */
	public void setCurrentPlayer(Player player) {
		this.currentPlayer = player;
	}
	/**
	 * Gets the accuse relation.
	 * @return array with {@code playerId} of two players
	 */
	public int[] getAccuse() {
		return this.accuse;
	}
	/**
	 * Gets discard pile.
	 * @return {@code discardPile}
	 */
	public ArrayList<RumourCard> getDiscardPile(){
		return this.discardPile;
	}
	
	/**
	 * Gets game panel.
	 * @return {@code gamePane}
	 */
	public GamePane getGamePane() {
		return gamePane;
	}
	/**
	 * Sets game panel.
	 * @param gamePane reference of {@link GamePane}
	 */
	public void setGamePane(GamePane gamePane) {
		this.gamePane = gamePane;
	}
	/**
	 * Play game with a human player and several bot players.
	 * <p>
	 * Players take turns for rounds, choosing to accuse another player or use 
	 * Witch! effect of a {@code Rumour card}. This method also updates {@link View.GamePane} and {@link Model.Player}.
	 * <p>
	 * If a round is end, then start a new round by resetting the states of game, players and cards.
	 * <p>
	 * If one player has enough points to win, then the game ends.
	 */
	public void playBot() {
		
		while(true) {
			currentPlayer.playTurn(this);
			gamePane.repaint();
			hangOn();
			
			
			outOfGame();
			if (isRoundEnd()) {
				playerList.addAll(outPlayerList);
				outPlayerList.clear();
				if(isGameEnd()) {
					break;
				}
				else {
					//reset
					
					Collections.sort(playerList, new Comparator<Player>() {
						public int compare(Player p1, Player p2) {
						    return Integer.compare(p1.getPlayerId(), p2.getPlayerId());
						};
					});	 
					//score board
					scoreBoard();
					System.out.println("\nStart new round...\n");
					gamePane.setInfoLabel("Start new round...");
					JOptionPane.showMessageDialog(null, "Start new round...");
					chooseIdentity();
					System.out.printf("Start from player %d, who was last to reveal his/her identity in the previous round\n",currentPlayer.getPlayerId());
					JOptionPane.showMessageDialog(null, String.format("Start from player %d, who was last to reveal his/her identity in the previous round\n",currentPlayer.getPlayerId()), null, 1);
					for (Player player : playerList) {
						player.getHand().clear();
						player.getRevealedCards().clear();
						player.setEvilEye(0);
						player.setIsRevealed(false);
						player.chooseIdentity();
						
						player.identityLabel.setIcon(new ImageIcon(Player.unknownImage));
						
					
					}
					initPile();
					distribute();
					gamePane.repaint();
				}
				
			}
		} 
	}
	
	
	/**
	 * Judges if a player is out of game.
	 * <p>
	 * If a player is revealed as {@code Witch}, then he is out of game.
	 */
	public void outOfGame() {
		for(Iterator<Player> it = playerList.iterator();it.hasNext();) {
			Player player = it.next();
			if(player.isRevealed() == true && player.getIdentity() == Identity.Witch) {
				System.out.printf("Player %d is a Witch, he/she is out of game\n", player.getPlayerId());
				JOptionPane.showMessageDialog(null, String.format("Player %d is out of game", player.getPlayerId()));
				it.remove();
				outPlayerList.add(player);
				
			}
		}
	}
	/**
	 * Judges if this round is end.
	 * <p>
	 * If there is only one player with a unrevealed identity card, then the round ends.
	 * <p>
	 * If this player is a {@code Witch}, then he gets 2 points, if this player is a 
	 * {@code Villager}, then he gets 1 point.
	 * @return {@code true} if this round ends,
	 * 			{@code false} otherwise.
	 */
	public boolean isRoundEnd() {
		int nUnrevealedPlayer = 0;
		Player roundWinner = null;
		for (Player player : playerList) {
			if(player.isRevealed() == false) {
				nUnrevealedPlayer ++;
				roundWinner = player;
			}
		}
		if (nUnrevealedPlayer == 1) {
			System.out.printf("This round ends, player %d remains with a unrevealed identity card, he/she wins the round\n",roundWinner.getPlayerId());
			JOptionPane.showMessageDialog(null, String.format("This round ends, player %d remains with a unrevealed identity card, he/she wins the round\n", roundWinner.getPlayerId()), null, 1);
			if(roundWinner.getIdentity() == Identity.Villager) {
				System.out.println("He/She is a Villager, gains 1 point");
				roundWinner.updatePoints(1);
				gamePane.repaint();
			}
			else if (roundWinner.getIdentity() == Identity.Witch) {
				System.out.println("He/She is a Witch, gains 2 point");
				roundWinner.updatePoints(2);
				gamePane.repaint();
			}
			setCurrentPlayer(roundWinner);
			return true;
		}
		else {
			return false;
		}
	}
	
	
	/**
	 * Judges if this game is end.
	 * <p>
	 * If there is a player who reaches 5 points, then this game ends.
	 * <p>
	 * If the game is tied, then layers will have a high-stakes “monkey knife fight” and duel to the 
	 * death. The survivor wins.
	 * @return {@code true} if the game is end, {@code false} otherwise
	 */
	public boolean isGameEnd() {
		Player max = playerList.stream().max(Comparator.comparing(player -> player.getPoint())).get();
		if(max.getPoint() >= 5) {
			//if this player is the only one
			ArrayList<Player> winner = new ArrayList<Player>();
			for(Player player: playerList) {
				if (player.getPoint() == max.getPoint()) {
					winner.add(player);
				}
			}
			if(winner.size() == 1) {
				System.out.printf("Game ends, player %d wins, he/she has %d points\n",max.getPlayerId(),max.getPoint());
				JOptionPane.showMessageDialog(null, String.format("Game ends, player %d wins, he/she has %d points\n", max.getPlayerId(),max.getPoint()), null, 1);
			}
			else {
				System.out.print("Game ends, but it is tied, then players will have a high-stakes “monkey knife fight” and duel to the death. The survivor wins.\n");
				for (Player player : winner) {
					System.out.printf("player %d ",player.getPlayerId());
				}
				System.out.println("will play a monkey knife fight!");
				System.out.println("Playing...");
				Player survivor = winner.get((int)(Math.random() * (winner.size())));
				System.out.printf("Survivor is player %d! he/she wins, he/she has %d points\n",survivor.getPlayerId(),survivor.getPoint());
				JOptionPane.showMessageDialog(null, String.format("Game ends, player %d wins, he/she has %d points\n", max.getPlayerId(),max.getPoint()), null, 1);
			}
			
			
			return true;
		}
		else {
			return false;
		}
	}
	/**
	 * Prints every player's points in command line.
	 */
	public void scoreBoard() {
		System.out.println("Scoreboard:");
		for (Player player : playerList) {
			System.out.printf("Player %d: %d points\n", player.getPlayerId(),player.getPoint());
		}
	}
	/**
	 * Prints player list in command line except current player.
	 * <p>
	 * This method is used in {@link DuckingStool#huntEffect(Game)}
	 */
	//display all the players except current player
	public void displayPlayers() {
		for (Player player : playerList) {
			if (player == currentPlayer) {
				continue;
			}
			else {
				System.out.printf("Player %d\n", player.getPlayerId());
			}
		}
	}
	/**
	 * Prints the players who haven't been accused to reveal identity card
	 * in command line.
	 */
	public void displayUnaccusedPlayers() {
		for (Player player : playerList) {
			if(player == currentPlayer) {
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
	
	/**
	 * Finds a player in {@code playerList} by his id.
	 * @param id target player's id
	 * @return reference of this player, {@code null} if not found.
	 */
	public Player findPlayer(int id) {
		Player target = null;
		boolean find = false;
		Iterator<Player> iterator = playerList.iterator();
		while (iterator.hasNext()) {
			target = iterator.next();
			if (target.getPlayerId()==id) {
				find = true;
				break;
			}
		}
		if (find) {
			return target;
			
		}
		else {
			return null;
		}
		
	}
	/**
	 * Prints every player's state in command line.
	 * <p>
	 * State:
	 * <ul>
	 * <li> number of hand cards
	 * <li> number of revealed Rumour cards
	 * <li> state of identity
	 * <li> points 
	 * </ul>
	 */
	public void displayStatus() {
		System.out.print("\n");
		System.out.format("\t\t%5s\t%14s\t%8s\t%5s\n","Cards","Revealed Cards","Identity","Score");
		for (Player player : playerList) {
			if (player.isRevealed() == false) {
				System.out.format("Player %d\t%5d\t%14d\t%8s\t%5d\n",
						player.getPlayerId(),player.getHand().size(),player.getRevealedCards().size(),"Unrevealed",player.getPoint());
			}
			else {
				System.out.format("Player %d\t%5d\t%14d\t%8s\t%5d\n",
						player.getPlayerId(),player.getHand().size(),player.getRevealedCards().size(),player.getIdentity().toString(),player.getPoint());
			}
		}
		if (!discardPile.isEmpty()) {
			System.out.print("Discard pile: ");
			for (RumourCard card : discardPile) {
				System.out.print(card.getCardName().toString()+"\t");
			}
		}
		System.out.print("\n\n");
	}
	/**
	 * User chooses his identity via dialog.
	 */
	public void setIdentity() {
		String[] options = {"Witch","Villager"};
		String identity = (String)JOptionPane.showInputDialog(null,"You choose to be", "Identity",JOptionPane.INFORMATION_MESSAGE, null,options,options[0] );
		if(identity == "Villager") {
			this.getPlayerList().get(0).setIdentity(Identity.Villager);
		}
		else {
			this.getPlayerList().get(0).setIdentity(Identity.Witch);
		}
		
	}
	/**
	 * User sets {@code nPlayer} via dialog
	 */
	public void setPlayers() {
		Integer[] options = {3,4,5,6};
		int nPlayer = (int)JOptionPane.showInputDialog(null,"Number of players", "Initialize",JOptionPane.INFORMATION_MESSAGE, null,options, options[0]);
		String mes = "Each player has " + 12/nPlayer + " rumour cards";
		JOptionPane.showMessageDialog(null, mes);
		this.setnPlayer(nPlayer);
		this.initPlayer();
	}
	
	/**
	 * Causes application to sleep 1 second in order to wait UI to refresh.
	 */
	public void hangOn() {
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
}
