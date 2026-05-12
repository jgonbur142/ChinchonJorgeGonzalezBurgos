package app;

import resources.Deck;
import resources.IA;
import resources.Player;

/**
 * Partida que incluye una IA como jugador adicional junto a los jugadores humanos.
 */
public class IAMatch extends Match{

	/**
	 * Crea una partida con 1 jugador humano y una IA.
	 * @param name1 nombre del jugador humano
	 */
	public IAMatch(String name1) {
		deck = new Deck();
		players.add(new Player(name1));
		players.add(new IA());
	}
	
	/**
	 * Crea una partida con 2 jugadores humanos y una IA.
	 * @param name1 nombre del jugador 1
	 * @param name2 nombre del jugador 2
	 */
	public IAMatch(String name1, String name2) {
		deck = new Deck();
		players.add(new Player(name1));
		players.add(new Player(name2));
		players.add(new IA());
	}
	
	/**
	 * Crea una partida con 3 jugadores humanos y una IA.
	 * @param name1 nombre del jugador 1
	 * @param name2 nombre del jugador 2
	 * @param name3 nombre del jugador 3
	 */
	public IAMatch(String name1,String name2,String name3) {
		deck = new Deck();
		players.add(new Player(name1));
		players.add(new Player(name2));
		players.add(new Player(name3));
		players.add(new IA());
	}
	
	/**
	 * Crea una partida con 4 jugadores humanos y una IA.
	 * @param name1 nombre del jugador 1
	 * @param name2 nombre del jugador 2
	 * @param name3 nombre del jugador 3
	 * @param name4 nombre del jugador 4
	 */
	public IAMatch(String name1,String name2,String name3,String name4) {
		deck = new Deck();
		players.add(new Player(name1));
		players.add(new Player(name2));
		players.add(new Player(name3));
		players.add(new Player(name4));
		players.add(new IA());
	}
			
	
	
	
}
