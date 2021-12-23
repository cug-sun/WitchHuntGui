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
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.imageio.ImageIO;
import javax.swing.JLabel;
import javax.swing.JPanel;

import RumourCards.RumourCard;
import WitchHunt.Player;



public class GamePane extends JPanel {
	private ArrayList<Player> playerList;
	
	private HashMap<RumourCard, Rectangle> mapCards;
	
	private RumourCard selected;
	
	public GamePane(ArrayList<Player> playerList) {
		// TODO 自动生成的构造函数存根
		this.playerList = playerList;
		mapCards = new HashMap<>(playerList.size() * 5);
		
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
		
		JLabel jLabel = new JLabel("12345");
		this.add(jLabel);
	}
	
	@Override
	public Dimension getPreferredSize() {
		return new Dimension(400,400);
	}
	
	 @Override
     public void invalidate() {
         super.invalidate();
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
     }
	 
	 protected void paintComponent(Graphics g) {
         super.paintComponent(g);
         Graphics2D g2d = (Graphics2D) g.create();
         ArrayList<RumourCard> hand = playerList.get(0).getHand();;
         for (RumourCard card : hand) {
             Rectangle bounds = mapCards.get(card);
             System.out.println(bounds);
             if (bounds != null) {
                 g2d.setColor(Color.WHITE);
                 g2d.fill(bounds);
                 g2d.setColor(Color.BLACK);
                 g2d.draw(bounds);
                 //
                 Image image = null;
                 try {
         			image = ImageIO.read(new File("./image/card/AngryMolo.png"));
         		} catch (IOException e) {
         			// TODO 自动生成的 catch 块
         			e.printStackTrace();
         		}
         		image = image.getScaledInstance(bounds.width, bounds.height, Image.SCALE_SMOOTH);
                 g2d.drawImage(image, bounds.x, bounds.y, null);
                 //
                 g2d.setFont(new Font("Bradley Hand ITC", Font.BOLD, 16));
                 g2d.drawString("Witch Hunt", 50,50);
                 //
                 Graphics2D copy = (Graphics2D) g2d.create();
                 paintCard(copy, card, bounds);
                 copy.dispose();
             }
         }
         g2d.dispose();
     }

     protected void paintCard(Graphics2D g2d, RumourCard card, Rectangle bounds) {
         g2d.translate(bounds.x + 5, bounds.y + 5);
         g2d.setClip(0, 0, bounds.width - 5, bounds.height - 5);
         
         String text = card.getCardName().toString();
         FontMetrics fm = g2d.getFontMetrics();
         g2d.drawString(text, 0, fm.getAscent());
         
         //
		
     }
}
