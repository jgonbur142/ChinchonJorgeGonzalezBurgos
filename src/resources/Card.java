package resources;

public class Card {
	private Number number;
	private Suit suit;
	
	Card(Number number, Suit suit){
		this.number=number;
		this.suit=suit;
	}
	
	public int cardValue() {
		return number.getValue();
	}
}
