package resources;

/**
 * Representa el palo que puede tener una carta
 */
public enum Suit {
	COINS("📀", "\u001B[33m"),
	SWORD("🗡️", "\u001B[36m"),
	CUP("🍷", "\u001B[31m"),
	CLUBS("🍾", "\u001B[32m");
	
	private final String symbol;
	private final String color;
	private static final String RESET = "\u001B[0m";
	
	Suit(String symbol,String color){
		this.symbol=symbol;
		this.color=color;
	}
	
	public String getColoredSymbol() {
		return color+symbol+RESET;
	}
	
	public String getSymbol() {
		return symbol;
	}
	
}
