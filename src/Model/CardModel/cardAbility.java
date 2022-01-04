package Model.CardModel;

import Controller.Game;

public interface cardAbility {
	RumourCardName getCardName();
	void witchEffect(Game game);
	void huntEffect(Game game);
	void robotWitchEffect(Game game);
	void robotHuntEffect(Game game);
}
