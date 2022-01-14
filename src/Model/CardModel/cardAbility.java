package Model.CardModel;

import Controller.Game;
/**
 * <p>projectname: ${WitchHunt!} </p> 
 * <p>Cette classe est utilisée pour contrôler les effets des cartes.</p> 
 *
 * @author SUN Sun 
 * @author ZHANG xiao
 * @version v1.0
 * 
 */

public interface cardAbility {
	RumourCardName getCardName();
	
	/**
	 * Déclarer l'effet de Witch? de la carte.
	 * @param game
	 */
	void witchEffect(Game game);
	
	/**
	 * Déclarer l'effet de village de la carte.
	 * @param game
	 */
	void huntEffect(Game game);
	
	/**
	 * Déclarer l'effet de witch de la carte,quand le robot joue.
	 * @param game
	 */
	void robotWitchEffect(Game game);
	
	/**
	 * Déclarer l'effet de village de la carte,quand le robot joue.
	 * @param game
	 */
	void robotHuntEffect(Game game);
}
