package Controller;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.text.PlainDocument;

import org.ietf.jgss.Oid;

import Model.Bot;
import Model.Player;
import Model.Identity;
import RumourCards.AngryMob;
import RumourCards.BlackCat;
import RumourCards.Broomstick;
import RumourCards.Pauldron;
import RumourCards.DuckingStool;
import RumourCards.EvilEye;
import RumourCards.HookedNose;
import RumourCards.PetNewt;
import RumourCards.PointedHat;
import RumourCards.RumourCard;
import RumourCards.RumourCardName;
import RumourCards.TheInquisition;
import RumourCards.Toad;
import RumourCards.Wart;
import View.GamePane;



public class Game {
	//Pile
	public ArrayList<RumourCard> cardPile;
	//discard pile
	public ArrayList<RumourCard> discardPile;
	//The number of players
	private int nPlayer;
	//There are 12 RumourCards
	public static final int nHumourCards = 12;
	ArrayList<Player> playerList;
	ArrayList<Player> outPlayerList;
	//index of current player in playerList
	private Player currentPlayer;
	//an array to save the accuse relation, accuse[0] is the playerId of player who accuses, accuse[1] is the playerId of accused player
	private int[] accuse = new int[2];
	
	private GamePane gamePane;
	
	public Game() {
		System.out.println("*****************Witch Hunt*****************");
		System.out.println("Choose the number of players (3-6)");
//		Scanner scanner = new Scanner(System.in);
//		//set the number of players
//		while (true) {
//			try {
//				nPlayer = scanner.nextInt();
//				if (nPlayer > 6 || nPlayer < 3 ) {
//					System.out.println("Invalide input! Input again");
//					scanner = new Scanner(System.in);
//				}
//				else {
//					break;
//				}
//			}
//			catch (Exception e) {
//				// TODO: handle exception
//				System.out.println("Invalide input! Input again");
//				scanner = new Scanner(System.in);
//			}
//		}	
//		System.out.println(this.nPlayer + " players");
//		System.out.println("Each player will have " + Game.nHumourCards/this.nPlayer + " Rumour Cards" );
//		initPlayer();
		setPlayers();
		setIdentity();
		initPile();
		distribute();
		
	}
	
	public void test() {
		gamePane.getInfoLabel().setText("good luck");
		currentPlayer.setMessageLabel("fuck you");
	}
	
	public void shuffleCard() {
		System.out.println("Shuffle rumour cards...");
		Collections.shuffle(cardPile);
	}
	//initialize the rumours cards,add them to pile
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
//		currentPlayer = playerList.get((int)(Math.random() * (nPlayer)));
		currentPlayer = playerList.get(0);
		System.out.println("Start from player " + (currentPlayer.getPlayerId()));
	}
	
	
	public void distribute() {
		System.out.println("Distribute rumour cards...");
		int nHand = nHumourCards/nPlayer;
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
	
	
	public void chooseIdentity() {
		for (Player player : playerList) {
			if ((player instanceof Bot) == false) {
				continue;
			}
			player.chooseIdentity();
		}
	}
	
	
	public int getnPlayer() {
		return nPlayer;
	}

	public void setnPlayer(int nPlayer) {
		this.nPlayer = nPlayer;
	}

	public ArrayList<Player> getPlayerList(){
		return this.playerList;
	}
	
	public Player getCurrentPlayer() {
		return this.currentPlayer;
	}
	
	public void setCurrentPlayer(Player player) {
		this.currentPlayer = player;
	}
	
	public int[] getAccuse() {
		return this.accuse;
	}
	
	public ArrayList<RumourCard> getDiscardPile(){
		return this.discardPile;
	}
	
	
	
	public GamePane getGamePane() {
		return gamePane;
	}

	public void setGamePane(GamePane gamePane) {
		this.gamePane = gamePane;
	}

	public void playBot() {
		initPile();
		distribute();
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
					chooseIdentity();
					System.out.printf("Start from player %d, who was last to reveal his/her identity in the previous round\n",currentPlayer.getPlayerId());
					for (Player player : playerList) {
						player.getHand().clear();
						player.getRevealedCards().clear();
						player.setEvilEye(0);
						player.setIsRevealed(false);
					}
					initPile();
					distribute();
				}
				
			}
		} 
	}
	
	public void playGame() {
		initPile();
		distribute();
		while(true) {
			playTurn();
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
					chooseIdentity();
					System.out.printf("Start from player %d, who was last to reveal his/her identity in the previous round\n",currentPlayer.getPlayerId());
					for (Player player : playerList) {
						player.getHand().clear();
						player.getRevealedCards().clear();
						player.setEvilEye(0);
						player.setIsRevealed(false);
					}
					initPile();
					distribute();
				}
				
			}
		} 
		
		
	}
	
	public void playRound() {
		initPile();
		distribute();
		currentPlayer.playTurn(this);
	}
	
	public void playTurn() {
//		/*
//		 * if this player is chosen by Evil Eye,he must accuse a player other than the player who accuses him
//		 */
//		displayStatus();
//		if (currentPlayer.getEvilEye() != 0) {
//			System.out.printf("Player %d, you are chosen by Evil Eye, you must accuse a player other than player %d if possible\n", 
//					currentPlayer.getPlayerId(), currentPlayer.getEvilEye());
//			System.out.println("You can accuse another player of being a Witch\nWhich player ?");
//			if (playerList.size() > 2) {
//				for (Player player : playerList) {
//					if(player == currentPlayer) {
//						continue;
//					}
//					else if (player.getPlayerId() == currentPlayer.getEvilEye()) {
//						continue;
//					}
//					else {
//						if (player.isRevealed() == false) {
//							System.out.printf("Player %d\n", player.getPlayerId());
//						}
//						else {
//							System.out.printf("Player %d has been revealed as a %s\n", player.getPlayerId(),player.getIdentity().toString());
//						}
//					}
//				}
//			}
//			else {
//				//if there are only 2 players, then must accuse the player who used Evil Eye
//				System.out.println("There are only 2 players...");
//				for (Player player : playerList) {
//					if(player == currentPlayer) {
//						continue;
//					}
//					else {
//						if (player.isRevealed() == false) {
//							System.out.printf("Player %d\n", player.getPlayerId());
//						}
//						else {
//							System.out.printf("Player %d has been revealed as a %s\n", player.getPlayerId(),player.getIdentity().toString());
//						}
//					}
//				}
//			}
//			currentPlayer.setEvilEye(0);
//			Scanner scanner = new Scanner(System.in);
//			int chosenId = scanner.nextInt();
//			Player accusedPlayer = findPlayer(chosenId);
//			
//			//whether this player can be accused
//			if(accusedPlayer.isRevealed() == true) {
//				System.out.println("This player's identity is revealed, you can't accuse him/her");
//				setCurrentPlayer(currentPlayer);
//			}
//			else {
//			accuse[0] = currentPlayer.getPlayerId();
//			accuse[1] = accusedPlayer.getPlayerId();
//			//the accused player acts
//			accusedPlayer.beAccused(this);
//			//setCurrentPlayer(accusedPlayer);
//			}
//		}
//		
//		
//		else {
//			System.out.printf("Player %d, it's your turn\n", currentPlayer.getPlayerId());
//			System.out.println("You have these cards:");
//			currentPlayer.displayHand();
//			System.out.println("you must either:\n" +
//					"1.Accuse another player of being a Witch.\nor\n"
//					+ "2.Reveal a Rumour card from your hand and play it face up in front of yourself, resolving its Hunt! effect.");
//			Scanner scanner = new Scanner(System.in);
//			switch (scanner.nextInt()) {
//			case 1: {
//				//Accuse another player of being a Witch
//				System.out.println("You choose to accuse another player of being a Witch\nWhich player ?");
//				displayUnaccusedPlayers();
//				int chosenId = scanner.nextInt();
//				Player accusedPlayer = findPlayer(chosenId);
//				
//				//this player can or can't be accused
//				if(accusedPlayer.isRevealed() == true) {
//					System.out.println("this player'identity is revealed, you can't accuse him/her");
//					setCurrentPlayer(currentPlayer);
//				}
//				else {
//				accuse[0] = currentPlayer.getPlayerId();
//				accuse[1] = accusedPlayer.getPlayerId();
//				//the accused player acts
//				accusedPlayer.beAccused(this);
//				//setCurrentPlayer(accusedPlayer);
//				}
//				break;
//				
//			}
//			case 2: {
//				Player player = currentPlayer;
//				//Reveal a Rumour card from hand, resolving its Hunt! effect
//				if(!currentPlayer.getHand().isEmpty()) {
//					System.out.println("You have these Rumour cards:");
//					currentPlayer.displayHand();
//					System.out.println("Which card do you want to use ?");
//					RumourCard choosedCard = currentPlayer.getHand().get(scanner.nextInt()-1);
//					System.out.printf("You choose to use %s\n",choosedCard.getCardName().toString());
//					//current player may have been changed
//					choosedCard.huntEffect(this);
//					if (choosedCard.getIsUsed() == true) {
//						player.getHand().remove(choosedCard);
//						//after using Black Cat, discard it
//						if (choosedCard.getCardName() == RumourCardName.Black_Cat) {
//							discardPile.add(choosedCard);
//						}
//						else {
//							player.getRevealedCards().add(choosedCard);
//						}
//					}
//					break;
//				}
//				else {
//					System.out.println("You don't have any card in hand! You must accuse another player of being a witch");
//					setCurrentPlayer(currentPlayer);
//					break;
//				}
//				
//				
//			}
//			default:
////				throw new IllegalArgumentException("Unexpected value: " + scanner);
//				System.out.println("Invalide input! Input again");
//			}
//		}
//		
//		
	}
	//if there is a player out of game
	public void outOfGame() {
		for(Iterator<Player> it = playerList.iterator();it.hasNext();) {
			Player player = it.next();
			if(player.isRevealed() == true && player.getIdentity() == Identity.Witch) {
				System.out.printf("Player %d is a Witch, he/she is out of game\n", player.getPlayerId());
				it.remove();
				outPlayerList.add(player);
				
			}
		}
	}
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
			if(roundWinner.getIdentity() == Identity.Villager) {
				System.out.println("He/She is a Villager, gains 1 point");
				roundWinner.updatePoints(1);
			}
			else if (roundWinner.getIdentity() == Identity.Witch) {
				System.out.println("He/She is a Witch, gains 2 point");
				roundWinner.updatePoints(2);
			}
			setCurrentPlayer(roundWinner);
			return true;
		}
		else {
			return false;
		}
	}
	
	
	//if the game is end
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
			}
			
			
			return true;
		}
		else {
			return false;
		}
	}
	
	public void scoreBoard() {
		System.out.println("Scoreboard:");
		for (Player player : playerList) {
			System.out.printf("Player %d: %d points\n", player.getPlayerId(),player.getPoint());
		}
	}
	
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
	//display the players who haven't been accused to reveal identity card
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
	
	//find a player by his playerId
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
	public void setPlayers() {
		Integer[] options = {3,4,5,6};
		int nPlayer = (int)JOptionPane.showInputDialog(null,"Number of players", "Initialize",JOptionPane.INFORMATION_MESSAGE, null,options, options[0]);
		String mes = "Each player has " + 12/nPlayer + " rumour cards";
		JOptionPane.showMessageDialog(null, mes);
		this.setnPlayer(nPlayer);
		this.initPlayer();
	}
	
	public void hangOn() {
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
	}
	
}
