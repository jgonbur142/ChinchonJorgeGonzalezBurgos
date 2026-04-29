package resources;

/**
 * Representa el palo que puede tener una carta
 */
public enum Suit {
	COINS("📀"),
	SWORD("🗡️"),
	CUP("🍷"),
	CLUBS("🍾");
	
	private final String symbol;
	
	Suit(String symbol){
		this.symbol=symbol;
	}

	public String getSymbol() {
		return symbol;
	}
	
}
