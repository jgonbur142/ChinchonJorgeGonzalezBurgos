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

	public Number getNumber() {
		return number;
	}

	public Suit getSuit() {
		return suit;
	}

	@Override
	public String toString() {
		return String.format("Número: %d, Palo: %s",number.getValue(),suit.getSymbol());
	}
	
	
	
	
}
