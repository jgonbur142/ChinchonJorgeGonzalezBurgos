package app;

import resources.Player;

public interface IMatch {

	/**
	 * Hace los preparativos para empezar una nueva partida.
	 */
	void start();
	
	/**
	 * Reparte las cartas iniciales a cada jugador
	 */
	void distributeCards();
	
	/**
	 * Comienza una rondad nueva, incrementa el contador de rondas.
	 * En una ronda se juegan varios turnos.
	 */
	void playNewRound();
	
	/**
	 * Gestiona lo que puede hacer un jugador cuando le toca jugar.
	 * Un jugador roba una carta durante su turno y descarta una carta de su mano.
	 * Un jugador puede decidir cerrar ronda en su turno si tiene una combinación de cartas que lo permita.
	 * @param player jugador al que le corresponde el turno
	 * @param turn número de turno actual
	 * @return true si el jugador cierra la ronda, false en caso contrario
	 */
	boolean playTurn(Player player,Integer turn);
	
	/**
	 * Elimina de la partida a los jugadores que han alcanzado o superado la puntuación máxima.
	 */
	void eliminatePlayer();

	/**
	 * Muestra los resultados de todos los jugadores al terminar una ronda.
	 * @param closer jugador que ha cerrado la ronda
	 * @param closerScore puntuación obtenida por el jugador que cierra
	 */
	void showRoundResults(Player closer, int closerScore);
	
}
