package resources;

import java.util.ArrayList;
import java.util.List;

public class Player implements IPlayer{

	private String name;
	private List<Card> hand;
	private int score;
	
	public Player(String name) {
		this.name=name;
		this.hand = new ArrayList<>();
		score=0;
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

	//TODO: calcular puntuación
	@Override
	public void calculateScore() {
		int points=0;
		
		for (Card c : hand) {
			points += c.getNumber().getValue();
		}
		
		score += points;
	}

	@Override
	public void cleanHand() {
		hand.clear();
	}

	@Override
	public List<Card> getHand() {
		return hand;
	}
	
	public void showHand() {
		for (int i = 0;i<hand.size();i++) {
			System.out.printf("[%d] %s\n",i,hand.get(i));
		}
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getScore() {
		return score;
	}

	@Override
	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return String.format("Nombre: %s\n Mano: %s",name,hand);
	}
	
	
	

}
