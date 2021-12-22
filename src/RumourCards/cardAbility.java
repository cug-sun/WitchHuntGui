package RumourCards;

import WitchHunt.Game;

public interface cardAbility {
	RumourCardName getCardName();
	void witchEffect(Game game);
	void huntEffect(Game game);
}
