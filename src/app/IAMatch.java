package app;

import resources.Deck;
import resources.Player;

public class IAMatch extends Match{

	// constructor para 1 jugador + IA
	public IAMatch(String namePlayer1) {
		deck = new Deck();
		player1 = new Player(namePlayer1);
		player2 = new Player("IA");
	}
	
	// constructor para 2 jugadores + IA
	public IAMatch(String namePlayer1, String namePlayer2) {
		deck = new Deck();
		player1 = new Player(namePlayer1);
		player2 = new Player(namePlayer2);
		player3 = new Player("IA");
	}
	
	// constructor para 3 jugadores + IA
	public IAMatch(String namePlayer1,String namePlayer2,String namePlayer3) {
		deck = new Deck();
		player1 = new Player(namePlayer1);
		player2 = new Player(namePlayer2);
		player3 = new Player(namePlayer3);
		player4 = new Player("IA");
	}
	
	// constructor para 4 jugadores + IA
	public IAMatch(String namePlayer1,String namePlayer2,String namePlayer3,String namePlayer4) {
		deck = new Deck();
		player1 = new Player(namePlayer1);
		player2 = new Player(namePlayer2);
		player3 = new Player(namePlayer3);
		player4 = new Player(namePlayer4);
		player5 = new Player("IA");
	}

	public Deck getDeck() {
		return deck;
	}

	public Player getPlayer1() {
		return player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public Player getPlayer3() {
		return player3;
	}

	public Player getPlayer4() {
		return player4;
	}
			
	
	
	
}
