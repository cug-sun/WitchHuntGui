package View;

import java.awt.Color;
import java.awt.Dimension;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



import Model.CardModel.RumourCard;
import Controller.Game;

import Model.Player;


/**
 * The main component of {@link View.GameFrame}.
 * <p>
 * Inherits the super class {@link JPanel}.
 * @author SUN Sun 
 */
public class GamePane extends JPanel {
	/**
	 * Model {@link Game}
	 */
	private Game game;
	
	private ArrayList<Player> playerList;
	/**
	 * Each entry contains a reference of hand and its location.
	 * <p>
	 * Calculates locations based on window size and number of hands
	 */
	private HashMap<RumourCard, Rectangle> mapCards;
	/**
	 * Each entry contains a reference of player and its location.
	 * <p>
	 * Calculates locations based on window size and number of hands
	 */
	private HashMap<Player, Rectangle> mapPlayers;
	
	private JLabel infoLabel;
	
	private JButton startButton;
	
	/**
	 * Initializes the {@code GamePane}.
	 * 
	 * @param game The controller {@link Controller.Game}
	 */
	public GamePane(Game game) {
		// TODO 自动生成的构造函数存根
		
		this.game = game;
		this.playerList = game.getPlayerList();
		mapCards = new HashMap<>(playerList.get(0).getHand().size()*2);
		mapPlayers = new HashMap<>(playerList.size() * 2);
		initComponent();
	}
	/**
	 * Initializes the components
	 */
	public void initComponent() {
		this.setLayout(null);
		startButton = new JButton("start");
		infoLabel = new JLabel();
		startButton.setBounds(100, 500, 100, 30);
		infoLabel.setBounds(700, 300, 200, 30);
		
		this.add(startButton);
		this.add(infoLabel);
		//set human player UI
		game.getPlayerList().get(0).identityLabel.setBounds(300, 600, 117, 168);
		this.add(game.getPlayerList().get(0).identityLabel);
		game.getPlayerList().get(0).scoreLabel.setBounds(300, 500, 50, 100);
		this.add(game.getPlayerList().get(0).scoreLabel);

		
		startButton.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO 自动生成的方法存根
				startButton.setEnabled(false);
				JOptionPane.showMessageDialog(null, String.format("Start from player %d", game.getCurrentPlayer().getPlayerId()));
				game.playBot();
				
				
			}
		});
		
		
		
		game.setGamePane(this);
	}
	
	
	/**
	 * Initializes the fields {@code mapCards} and {@code mapPlayers}.
	 */
	 @Override
     public void invalidate() {
         super.invalidate();
         mapCards.clear();
         mapPlayers.clear();
         ArrayList<RumourCard> hand = playerList.get(0).getHand();
         int cardHeight = (getHeight() - 20) / 3;
         int cardWidth = (int) (cardHeight * 0.7);
         int xDelta = cardWidth+5;
         int xPos = (int) ((getWidth() / 2) - (cardWidth * (hand.size() / 4.0)));
         int yPos = (getHeight() - 20) - cardHeight;
         for (RumourCard card : hand) {
             Rectangle bounds = new Rectangle(xPos, yPos, cardWidth, cardHeight);
             mapCards.put(card, bounds);
             xPos += xDelta;
         }
         
        if(game.getnPlayer() == game.getPlayerList().size()) {
        	int playerAreaHeight = getHeight()/5;
            int playerAreaWidth = (int) (playerAreaHeight * 0.7);
            
            int playerXPos = getWidth()/2 - playerAreaWidth * ((playerList.size() + 1) / 2);
            for(int i = 1 ; i < playerList.size() ; i++) {
           	 Rectangle playerArea = new Rectangle(playerXPos, 10, playerAreaWidth, playerAreaHeight);
           	 mapPlayers.put(playerList.get(i), playerArea);
           	 playerXPos += playerAreaWidth + 70;
            }
        }
         
     }
	 /**
	  * Paints the card and player areas with {@link Graphics}.
	  */
	 @Override
	 protected void paintComponent(Graphics g) {
		 super.paintComponent(g);
		 mapCards.clear();
		 ArrayList<RumourCard> hand = playerList.get(0).getHand();
		 int cardHeight = (getHeight() - 20) / 3;
         int cardWidth = (int) (cardHeight * 0.7);
         int xDelta = cardWidth+5;
         int xPos = (int) ((getWidth() / 2) - (cardWidth * (hand.size() / 4.0)));
         int yPos = (getHeight() - 20) - cardHeight;
		 for (RumourCard card : hand) {
             Rectangle bounds = new Rectangle(xPos, yPos, cardWidth, cardHeight);
             mapCards.put(card, bounds);
             xPos += xDelta;
         }
         
         Graphics2D g2d = (Graphics2D) g.create();
         for (RumourCard card : hand) {
             Rectangle bounds = mapCards.get(card);

             if (bounds != null) {

                 g2d.setColor(Color.WHITE);
                 g2d.draw(bounds);
                 Graphics2D copy = (Graphics2D) g2d.create();
                 paintCard(copy, card, bounds);
                 copy.dispose();
             }
         }
         
         
         for(Player player : playerList) {
        	 Rectangle playerBounds = mapPlayers.get(player);
        	 if(playerBounds != null) {

        		 paintPlayer(player, playerBounds);        		 
        	 }
         }

         g2d.dispose();
     }
	 /**
	  * Paint player's hands in the {@code panel}.
	  * <p>
	  * Scales back the image of {@code Rumour card} and call {@code drawImage()} to draw it.
	  * @param g2d reference of {@link Graphics2D} of this {@code panel}
	  * @param card reference of {@link RumourCard}
	  * @param bounds object of {@link Rectangle} indicating the location of this card
	  */
     protected void paintCard(Graphics2D g2d, RumourCard card, Rectangle bounds) {

         //draw card image
         Image image = card.getCardImage();

         Image scaledImage = image.getScaledInstance(bounds.width, bounds.height, Image.SCALE_SMOOTH);
         g2d.drawImage(scaledImage, bounds.x, bounds.y, null);

     }
     /**
      * Paint player's area in the {@code panel}.
      * @param player reference of a {@code Player} object
      * @param bounds location of this player area in the {@code panel}
      */
     protected void paintPlayer(Player player, Rectangle bounds) {
    	 
    	 player.identityLabel.setBounds(bounds);

    	 this.add(player.identityLabel);
  		 player.messageLabel.setBounds(bounds.x, bounds.y + bounds.height, 200, 50);
  		 player.scoreLabel.setBounds(bounds.x, bounds.y + bounds.height + 50, 50, 50);
  		 this.add(player.scoreLabel);
  		 this.add(player.messageLabel);
     }
     
     
     
     /**
      * Gets the {@code infoLabel}.
      * @return reference of {@code infoLabel}
      */
     public JLabel getInfoLabel() {
    	 return infoLabel;
     }
     /**
      * Sets the {@code infoLabel}.
      * @param text the string text to display
      */
     public void setInfoLabel(String text) {
    	 infoLabel.setText(text);
     }
}
