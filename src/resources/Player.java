package resources;

import java.util.List;

public class Player implements IPlayer{

	private String name;
	private List<Card> hand;
	
	public Player(String name) {
		this.name=name;
	}
	
	@Override
	public void draw(Card card) {
		if (card != null) {
			hand.add(card);
		}
	}

	@Override
	public void discard(Card card) {
		if (card != null) {
			hand.remove(card);
		}		
	}

	@Override
	public void calculateScore() {
		/*
		 * calcular puntuación
		 * (hay que tener en cuenta las combinaciones 
		 * en la mano, cuál puntúa menos y elegirla)
		 */
	}

	@Override
	public void cleanHand() {
		hand.clear();
	}

	@Override
	public List<Card> getHand() {
		return hand;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return String.format("Nombre: %s\n Mano: %s",name,hand);
	}
	
	
	

}
