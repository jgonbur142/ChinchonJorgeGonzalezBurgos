package resources;
/**
 * Representa una carta con número y palo
 */
public class Card {
	private Number number;
	private Suit suit;
	
	/**
	 * Crea una carta con el número y palo indicados.
	 * Visibilidad de paquete: las cartas solo las crea la baraja, pero public para los test.
	 * @param number número de la carta
	 * @param suit   palo de la carta
	 */
	public Card(Number number, Suit suit){
		this.number=number;
		this.suit=suit;
	}
	
	/**
	 * Devuelve el valor numérico de la carta.
	 * @return valor entero de la carta
	 */
	public int cardValue() {
		return number.getValue();
	}

	/**
	 * Devuelve el número (enum) de la carta.
	 * @return número de la carta
	 */
	public Number getNumber() {
		return number;
	}

	/**
	 * Devuelve el palo (enum) de la carta.
	 * @return palo de la carta
	 */
	public Suit getSuit() {
		return suit;
	}

	/**
	 * Representación de la carta como cadena de texto con su valor y símbolo de palo.
	 * @return cadena con el valor numérico y el símbolo de color del palo
	 */
	@Override
	public String toString() {
		return String.format("%d %s",number.getValue(),suit.getColoredSymbol());
	}
	
	
	
	
}
