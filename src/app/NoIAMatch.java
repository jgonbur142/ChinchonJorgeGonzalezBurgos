package app;

import resources.Deck;
import resources.Player;
/**
 * Partida sin IA, compuesta únicamente por jugadores humanos.
 * Soporta entre 2 y 5 jugadores.
 */
public class NoIAMatch extends Match{
	
	/**
	 * Crea una partida con 2 jugadores humanos.
	 * @param name1 nombre del jugador 1
	 * @param name2 nombre del jugador 2
	 */
	public NoIAMatch(String name1, String name2) {
		deck = new Deck();
		players.add(new Player(name1));
		players.add(new Player(name2));
	}
	
	/**
	 * Crea una partida con 3 jugadores humanos.
	 * @param name1 nombre del jugador 1
	 * @param name2 nombre del jugador 2
	 * @param name3 nombre del jugador 3
	 */
	public NoIAMatch(String name1,String name2,String name3) {
		deck = new Deck();
		players.add(new Player(name1));
		players.add(new Player(name2));
		players.add(new Player(name3));
	}
	
	/**
	 * Crea una partida con 4 jugadores humanos.
	 * @param name1 nombre del jugador 1
	 * @param name2 nombre del jugador 2
	 * @param name3 nombre del jugador 3
	 * @param name4 nombre del jugador 4
	 */
	public NoIAMatch(String name1,String name2,String name3,String name4) {
		deck = new Deck();
		players.add(new Player(name1));
		players.add(new Player(name2));
		players.add(new Player(name3));
		players.add(new Player(name4));
	}
	
	/**
	 * Crea una partida con 5 jugadores humanos.
	 * @param name1 nombre del jugador 1
	 * @param name2 nombre del jugador 2
	 * @param name3 nombre del jugador 3
	 * @param name4 nombre del jugador 4
	 * @param name5 nombre del jugador 5
	 */
	public NoIAMatch(String name1,String name2,String name3,String name4,String name5) {
		deck = new Deck();
		players.add(new Player(name1));
		players.add(new Player(name2));
		players.add(new Player(name3));
		players.add(new Player(name4));
		players.add(new Player(name5));
	}
	
	
}
