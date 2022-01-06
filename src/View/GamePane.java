package View;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;



import RumourCards.RumourCard;
import Controller.Game;
import Model.Identity;
import Model.Player;



public class GamePane extends JPanel {
	private Game game;
	
	private ArrayList<Player> playerList;
	
	private HashMap<RumourCard, Rectangle> mapCards;
	
	private HashMap<Player, Rectangle> mapPlayers;
	
	private RumourCard selected;
	
	private JLabel infoLabel;
	
	private JButton startButton;
	
	public GamePane(Game game) {
		// TODO 自动生成的构造函数存根
		
		this.game = game;
		this.playerList = game.getPlayerList();
		mapCards = new HashMap<>(playerList.get(0).getHand().size()*2);
		mapPlayers = new HashMap<>(playerList.size() * 2);
		
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (selected != null) {
                    Rectangle bounds = mapCards.get(selected);
                    bounds.y += 20;
                    repaint();
                }
				
				selected = null;
				
				for (RumourCard card : playerList.get(0).getHand()) {
                    Rectangle bounds = mapCards.get(card);
                    if (bounds.contains(e.getPoint())) {
                        selected = card;
                        bounds.y -= 20;
                        repaint();
                        break;
                    }
                }
			}
		});
		
		initComponent();
	}
	
	public void initComponent() {
		this.setLayout(null);
		startButton = new JButton("start");
		infoLabel = new JLabel();
		startButton.setBounds(100, 500, 100, 30);
		infoLabel.setBounds(700, 300, 200, 30);
		
		this.add(startButton);
		this.add(infoLabel);
//		System.out.println(mapPlayers.isEmpty());
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
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(400,400);
	}
	
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
	 @Override
	 protected void paintComponent(Graphics g) {
		 super.paintComponent(g);
		 
		 //paint background
//		 ImageIcon img = new ImageIcon("./image/background/background.png");
//		 img.paintIcon(this, g, 0, 0);
		 
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
//             System.out.println(bounds);
             if (bounds != null) {
//                 g2d.setColor(Color.WHITE);
//                 g2d.fill(bounds);
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
//        		 g2d.setColor(Color.WHITE);
//        		 g2d.fill(playerBounds);
//        		 g2d.setColor(Color.WHITE);
//        		 g2d.draw(playerBounds);
//        		 Graphics2D copy = (Graphics2D) g2d.create();
        		 paintPlayer(player, playerBounds);
//        		 copy.dispose();
        		 
        		 
        	 }
         }

         g2d.dispose();
     }

     protected void paintCard(Graphics2D g2d, RumourCard card, Rectangle bounds) {
         //Translates the origin of the graphics context to the point (x, y) in the current coordinate system
//    	 g2d.translate(bounds.x + 5, bounds.y + 5);
//         g2d.setClip(0, 0, bounds.width - 5, bounds.height - 5);
//         
//         String text = card.getCardName().toString();
//         FontMetrics fm = g2d.getFontMetrics();
//         g2d.drawString(text, 0, fm.getAscent());
         
         //draw card image
         Image image = card.getCardImage();
//         BufferedImage buffered = new BufferedImage(image.getWidth(null), image.getHeight(null), BufferedImage.TYPE_INT_ARGB);
//         Graphics2D g = buffered.createGraphics();
//         g.drawImage(image, 0, 0, null);
         
         Image scaledImage = image.getScaledInstance(bounds.width, bounds.height, Image.SCALE_SMOOTH);
         g2d.drawImage(scaledImage, bounds.x, bounds.y, null);

     }
     protected void paintPlayer(Player player, Rectangle bounds) {
    	 
    	 player.identityLabel.setBounds(bounds);

    	 this.add(player.identityLabel);
  		 player.messageLabel.setBounds(bounds.x, bounds.y + bounds.height, 200, 50);
  		 player.scoreLabel.setBounds(bounds.x, bounds.y + bounds.height + 50, 50, 50);
  		 this.add(player.scoreLabel);
  		 this.add(player.messageLabel);
     }
     
     
     
     
     public JLabel getInfoLabel() {
    	 return infoLabel;
     }
     
     public void setInfoLabel(String text) {
    	 infoLabel.setText(text);
     }
}
