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
	
	/**
	 * 
	 */
	void calculateScore();
	
	/**
	 * comprueba si hay chinchón en la mano
	 * @return
	 */
	boolean isChinchon();
	
	/**
	 * 
	 * @return
	 */
	int getLeftoverScore();
	
	/**
	 * comprobar si el jugador puede cerrar
	 * @return
	 */
	boolean canClose();
	
	/**
	 * calcular la puntuación al cerrar el jugador
	 * @return
	 */
	int calculateCloseScore();	
	
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
