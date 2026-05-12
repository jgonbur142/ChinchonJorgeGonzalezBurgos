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
	 * Suma a la puntuación acumulada el valor de todas las cartas en mano
	 */
	void calculateScore();
	
	/**
	 * Comprueba si hay chinchón en la mano (7 cartas de valor consecutivo y mismo palo)
	 * @return {@code true} si hay chinchón, {@code false} en caso contrario
	 */
	boolean isChinchon();
	
	/**
	 * Calcula la suma de los valores de las cartas que no forman ninguna combinación
	 * @return puntuación de las cartas sobrantes
	 */
	int getLeftoverScore();
	
	/**
	 * Comprueba si el jugador puede cerrar ronda con su mano actual:
	 * debe tener al menos 6 cartas combinadas y la sobrante (si existe) no puede superar el valor 5
	 * @return {@code true} si puede cerrar, {@code false} en caso contrario
	 */
	boolean canClose();
	
	/**
	 * Calcula la puntuación que obtiene el jugador al cerrar ronda.
	 * Devuelve {@link Integer#MIN_VALUE} si hay chinchón, -10 si combina las 7 cartas
	 * o el valor de la carta sobrante en cualquier otro caso.
	 * @return puntuación de cierre
	 * @throws IllegalStateException si el jugador no tiene combinaciones suficientes para cerrar
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
	 * @param score nueva puntuación
	 */
	void setScore(int score);
	
}
