package app;

import resources.Deck;
import resources.Player;
/**
 * Partida con:
 * - 2 jugadores
 * - 1 Baraja
 */
public class NoIAMatch extends Match{
	
	// constructor para 2 jugadores sin IA
	public NoIAMatch(String name1, String name2) {
		deck = new Deck();
		players.add(new Player(name1));
		players.add(new Player(name2));
	}
	
	// constructor para 3 jugadores sin IA
	public NoIAMatch(String name1,String name2,String name3) {
		deck = new Deck();
		players.add(new Player(name1));
		players.add(new Player(name2));
		players.add(new Player(name3));
	}
	
	// constructor para 4 jugadores sin IA
	public NoIAMatch(String name1,String name2,String name3,String name4) {
		deck = new Deck();
		players.add(new Player(name1));
		players.add(new Player(name2));
		players.add(new Player(name3));
		players.add(new Player(name4));
	}
	
	// constructor para 5 jugadores sin IA
	public NoIAMatch(String name1,String name2,String name3,String name4,String name5) {
		deck = new Deck();
		players.add(new Player(name1));
		players.add(new Player(name2));
		players.add(new Player(name3));
		players.add(new Player(name4));
		players.add(new Player(name5));
	}

	public Deck getDeck() {
		return deck;
	}
	
	
}
