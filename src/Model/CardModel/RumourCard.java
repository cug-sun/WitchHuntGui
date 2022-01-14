package Model.CardModel;

import java.awt.Image;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 * <p>Contient toutes les cartes de rumour.</p>
 * <p>
 * Déterminez si une carte a été utilisée avec l'attribut isUsed.
 * Les effets de Witch et de village sont réécrit dans la classe de chaque carte.</p>
 * @author Sun sun
 * @author ZHANG xiao
 */

public abstract class RumourCard implements cardAbility {
	//if this card is used successfully
	protected boolean isUsed;
	protected Image cardImage;
	
	/**
	 * Obtenir le nom de la carte
	 * 
	 */
	public RumourCard() {
		this.isUsed = false;
	}
	
	/**
	 *pour distinguer une carte a été utilisée
	 *
	 */
	public void setIsUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}
	/**
	 * détecter si une carte a été utilisée
	 * @return isUsed
	 */
	public boolean getIsUsed() {
		return this.isUsed;
	}
	
	/**
	 * Remplir la graph de carte
	 * @return image
	 */
	public Image getCardImage() {
		Image image = null;
		try {
 			image = ImageIO.read(new File(String.format("./image/card/%s.png", this.getCardName().toString())));
 		} catch (IOException e) {
 			// TODO 自动生成的 catch 块
 			e.printStackTrace();
 		}
		return image;
	}
}
