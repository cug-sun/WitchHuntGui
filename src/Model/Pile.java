package Model;

import java.util.ArrayList;
import java.util.Collections;

import RumourCards.AngryMob;
import RumourCards.BlackCat;
import RumourCards.Broomstick;
import RumourCards.DuckingStool;
import RumourCards.EvilEye;
import RumourCards.HookedNose;
import RumourCards.Pauldron;
import RumourCards.PetNewt;
import RumourCards.PointedHat;
import RumourCards.RumourCard;
import RumourCards.TheInquisition;
import RumourCards.Toad;
import RumourCards.Wart;

public class Pile {
	private ArrayList<RumourCard> pile;
	
	private ArrayList<RumourCard> discardPile;
	
	
	public Pile() {
		// TODO 自动生成的构造函数存根
		//Initialize the pile
		pile = new ArrayList<RumourCard>();
		//Initialize the discard pile
		discardPile = new ArrayList<RumourCard>();
		RumourCard angryMob = new AngryMob();
		RumourCard blackCat = new BlackCat();
		RumourCard broomstick = new Broomstick();
		RumourCard pauldron = new Pauldron();
		RumourCard duckingStool = new DuckingStool();
		RumourCard evilEye = new EvilEye();
		RumourCard hookedNose = new HookedNose();
		RumourCard petNewt = new PetNewt();
		RumourCard pointedHat = new PointedHat();
		RumourCard theInquisition = new TheInquisition();
		RumourCard toad = new Toad();
		RumourCard wart = new Wart();
		pile.add(angryMob);
		pile.add(blackCat);
		pile.add(broomstick);
		pile.add(pauldron);
		pile.add(duckingStool);
		pile.add(evilEye);
		pile.add(hookedNose);
		pile.add(petNewt);
		pile.add(pointedHat);
		pile.add(theInquisition);
		pile.add(toad);
		pile.add(wart);
	}

	public void shuffle() {
		System.out.println("Shuffle rumour cards...");
		Collections.shuffle(pile);
	}

	public ArrayList<RumourCard> getPile() {
		return pile;
	}

	public ArrayList<RumourCard> getDiscardPile() {
		return discardPile;
	}
}
