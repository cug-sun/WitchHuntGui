package witchHuntView;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;



import RumourCards.RumourCard;
import WitchHunt.Game;
import WitchHunt.Player;



public class GamePane extends JPanel {
	private Game model;
	
	private ArrayList<Player> playerList;
	
	private HashMap<RumourCard, Rectangle> mapCards;
	
	private HashMap<Player, Rectangle> mapPlayers;
	
	private RumourCard selected;
	
	private JLabel infoLabel;
	
	public GamePane(Game model) {
		// TODO 自动生成的构造函数存根
		this.model = model;
		this.playerList = model.getPlayerList();
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
		infoLabel = new JLabel();
		this.add(infoLabel, getWidth()/2, getHeight()/2);
		
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
         
        
         int playerAreaHeight = getHeight()/5;
         int playerAreaWidth = (int) (playerAreaHeight * 0.7);
         
         int playerXPos = getWidth()/2 - playerAreaWidth * ((playerList.size() + 1) / 2);
         for(int i = 1 ; i < playerList.size() ; i++) {
        	 Rectangle playerArea = new Rectangle(playerXPos, 10, playerAreaWidth, playerAreaHeight);
        	 mapPlayers.put(playerList.get(i), playerArea);
        	 playerXPos += playerAreaWidth + 70;
         }
     }
	 @Override
	 protected void paintComponent(Graphics g) {
         super.paintComponent(g);
         Graphics2D g2d = (Graphics2D) g.create();
         ArrayList<RumourCard> hand = playerList.get(0).getHand();;
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
        		 g2d.setColor(Color.WHITE);
        		 g2d.fill(playerBounds);
        		 g2d.setColor(Color.WHITE);
        		 g2d.draw(playerBounds);
        		 Graphics2D copy = (Graphics2D) g2d.create();
        		 paintPlayer(copy, player, playerBounds);
        		 copy.dispose();
        		 
        		 
        	 }
         }
//         paintAccuse(g2d);
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
       
//         g2d.setFont(new Font("Bradley Hand ITC", Font.BOLD, 16));
//         g2d.drawString("Witch Hunt", 50,50);
//         g2d.drawImage(image,getWidth()/2,getHeight()/2,null);
     }
     protected void paintPlayer(Graphics2D g2d, Player player, Rectangle bounds) {
    	 Image identity = null;
  		try {
  			identity = ImageIO.read(new File("./image/identity/unknown1.png"));
  		} catch (IOException e) {
  			// TODO 自动生成的 catch 块
  			e.printStackTrace();
  		}
//  		Image scaledImage = identity.getScaledInstance(bounds.width, bounds.height, Image.SCALE_SMOOTH);
  		g2d.drawImage(identity, bounds.x, bounds.y, null);
     }
     
     public void paintAccuse(Graphics2D g2d) {
		Player accusePlayer = model.findPlayer(model.getAccuse()[0]);
		Player accusedPlayer = model.findPlayer(model.getAccuse()[1]);
		Rectangle dialog = new Rectangle((int)mapPlayers.get(accusePlayer).getX(), (int)mapPlayers.get(accusePlayer).getY(), 50, 20);
		g2d.drawString("123456", (int)mapPlayers.get(accusePlayer).getX(), (int)mapPlayers.get(accusePlayer).getY());
		
	}
     
     public JLabel getInfoLabel() {
    	 return infoLabel;
     }
}
