package resources;

import java.util.List;

public interface IPlayer {
	/**
	 * Roba la primera carta del mazo de robar
	 * @param card la carta que ha robado del mazo
	 */
	void draw(Card card);
	
	/**
	 * Descarta la carta elegida de la mano
	 * @param card la carta en mano que quiere descartar
	 */
	void discard(Card card);
	   
//	
//	/**
//	 * Comprueba las combinaciones en la mano y la puntuación que se consigue con cada combinación
//	 */
//	void calculateScore();
//	
//	/**
//	 * Calcula las combinaciones posibles en la mano del jugador
//	 */
//	void calculateCardCombinations();
//	
//	/**
//	 * Busca cartas consecutivas del mismo palo.
//	 * Mueve las cartas consecutivas a la mano de combinación.
//	 * Elimina las cartas de la mano original
//	 */
//	void straightCombination();
//	
//	/**
//	 * Busca cartas iguales en el mazo, mueve a la mano temporal las que encuentre y las elimina de la principal
//	 */
//	void sameCombination();
//	
//	/**
//	 * Se encarga solo de ordenar las cartas en la mano por el número
//	 */
//	void sortCardsInHand();
//	
	
	
	/**
	 * Vacía de cartas la mano del jugador
	 */
	void cleanHand();
	
	/**
	 * Comprueba la mano del jugador
	 * @return List<Card> Lista de cartas que tiene el jugador en la mano
	 */
	List<Card> getHand();

	/**
	 * Comprueba el nombre del jugador
	 * @return nombre del jugador
	 */
	String getName();
	
	/**
	 * Comprueba la puntuación del jugador
	 * @return puntuación del jugador
	 */
	int getScore();
	
	/**
	 * Configura la puntuación del jugador
	 */
	void setScore(int score);
	
}
