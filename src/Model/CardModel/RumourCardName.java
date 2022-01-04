package Model.CardModel;

public enum RumourCardName {
	Angry_Mob("Angry Mob"), The_Inquisition("The Inquisition"), Pointed_Hat("Pointed Hat"), Hooked_Nose("Hooked Nose"),Broomstick("Broomstick"), Wart("Wart"),
	Ducking_Stool("Ducking Stool"), Pauldron("Pauldron"), Evil_Eye("Evil Eye"), Toad("Toad"), Black_Cat("Black Cat"), Pet_Newt("Pet Newt");
	
	private final String cardName;
	private RumourCardName(String cardName) {
		this.cardName = cardName;
	}
	@Override
	public String toString() {
		return this.cardName;
	}
	
}
