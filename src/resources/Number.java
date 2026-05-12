package resources;

/**
 * Representa los números que puede tener una carta de la baraja española, excluyendo el 8 y el 9.
 */
public enum Number {
	UNO(1),
	DOS(2),
	TRES(3),
	CUATRO(4),
	CINCO(5),
	SEIS(6),
	SIETE(7),
	SOTA(10),
	CABALLO(11),
	REY(12);
	
	private final int value;
	
	/**
	 * Crea un valor de carta con su equivalencia numérica.
	 * @param value valor entero de la carta
	 */
	Number(int value){
		this.value=value;
	}

	/**
	 * Devuelve el valor numérico asociado a este número de carta.
	 * @return valor entero de la carta
	 */
	public int getValue() {
		return value;
	}
	
}
