package resources;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * Representa una baraja con las cartas de la baraja española, menos los 8 y 9
 */
public class Deck {
	private List<Card> cards;
	
	public Deck() {
		cards = new ArrayList<>();
		fillDeck();
	}

	/**
	 * Genera la baraja creando cartas con todos los valores de Suit y Number
	 */
	private void fillDeck() {
		for (Suit s : Suit.values()) {
			for (Number n : Number.values()) {
				cards.add(new Card(n, s));
			}
		}

	}
	
	/**
	 * Mezcla las cartas que están dentro del ArrayList
	 */
	public void shuffle() {
		Collections.shuffle(cards);
	}
	
	public void giveCard() {
		/*
		 * reparte las cartas 
		 * (no puedo simplemente crear una baraja nueva porque tengo que tener en cuenta las cartas que tienen los jugadores en la mano)
		 * (esas no pueden volver a estar en la baraja)
		 */
	}

	public List<Card> getCards() {
		return cards;
	}	
	
	
}
