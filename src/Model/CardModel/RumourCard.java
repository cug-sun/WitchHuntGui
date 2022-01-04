package Model.CardModel;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public abstract class RumourCard implements cardAbility {
	//if this card is used successfully
	protected boolean isUsed;
	protected Image cardImage;
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
