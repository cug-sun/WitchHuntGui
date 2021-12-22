package RumourCards;
public abstract class RumourCard implements cardAbility {
	//if this card is used successfully
	protected boolean isUsed;
	public RumourCard() {
		this.isUsed = false;
	}
	public void setIsUsed(boolean isUsed) {
		this.isUsed = isUsed;
	}
	public boolean getIsUsed() {
		return this.isUsed;
	}
	
}
