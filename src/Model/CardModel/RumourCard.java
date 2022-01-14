package Model.CardModel;

import java.awt.Image;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
/**
 * Contient toutes les cartes de rumour.
 * <p>
 * Déterminez si une carte a été utilisée avec l'attribut isUsed.
 * Les effets de Witch et de village sont réécrit dans la classe de chaque carte.
 * @author Sun sun
 * @author ZHANG xiao
 */

public abstract class RumourCard implements cardAbility {
	//if this card is used successfully
	protected boolean isUsed;
	protected Image cardImage;
	
	/**
	 * Obtenir le nom de la carte
	 * @return cardName
	 */
	public RumourCard() {
		this.isUsed = false;
	}
	public void setIsUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}
	public boolean getIsUsed() {
		return this.isUsed;
	}
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
