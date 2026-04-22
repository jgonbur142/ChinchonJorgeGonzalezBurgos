package app;

import resources.Deck;
import resources.Player;

public class IAMatch extends Match{

	// constructor para 1 jugador + IA
	public IAMatch(String name1) {
		deck = new Deck();
		players.add(new Player(name1));
		players.add(new Player("IA"));
	}
	
	// constructor para 2 jugadores + IA
	public IAMatch(String name1, String name2) {
		deck = new Deck();
		players.add(new Player(name1));
		players.add(new Player(name2));
		players.add(new Player("IA"));
	}
	
	// constructor para 3 jugadores + IA
	public IAMatch(String name1,String name2,String name3) {
		deck = new Deck();
		players.add(new Player(name1));
		players.add(new Player(name2));
		players.add(new Player(name3));
		players.add(new Player("IA"));
	}
	
	// constructor para 4 jugadores + IA
	public IAMatch(String name1,String name2,String name3,String name4) {
		deck = new Deck();
		players.add(new Player(name1));
		players.add(new Player(name2));
		players.add(new Player(name3));
		players.add(new Player(name4));
		players.add(new Player("IA"));
	}
			
	
	
	
}
