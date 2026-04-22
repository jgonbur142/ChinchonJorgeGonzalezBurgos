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
	 */
	boolean playTurn(Player player,Integer turn);
	
	boolean canEndRound(Player player);
	
	void eliminatePlayer();

	/**
	 * Muestra los resultados de todos los jugadores al terminar una ronda
	 */
	void showRoundResults();
	
}
