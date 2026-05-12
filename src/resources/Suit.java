package resources;

/**
 * Representa el palo que puede tener una carta de la baraja española.
 * Cada palo tiene un símbolo emoji y un color ANSI asociado para la consola.
 */
public enum Suit {
	COINS("📀", "\u001B[33m"),
	SWORD("🗡️", "\u001B[36m"),
	CUP("🍷", "\u001B[31m"),
	CLUBS("🍾", "\u001B[32m");
	
	private final String symbol;
	private final String color;
	private static final String RESET = "\u001B[0m";
	
	/**
	 * Crea un palo con su símbolo y su código de color ANSI.
	 * @param symbol emoji representativo del palo
	 * @param color  código de color ANSI para la consola
	 */
	Suit(String symbol,String color){
		this.symbol=symbol;
		this.color=color;
	}
	
	/**
	 * Devuelve el símbolo del palo con su color ANSI aplicado.
	 * @return símbolo coloreado para mostrar en consola
	 */
	public String getColoredSymbol() {
		return color+symbol+RESET;
	}
	
	/**
	 * Devuelve el símbolo emoji del palo sin color.
	 * @return símbolo del palo
	 */
	public String getSymbol() {
		return symbol;
	}
	
}
