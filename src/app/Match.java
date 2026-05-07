package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import resources.Card;
import resources.Deck;
import resources.IA;
import resources.Player;

public class Match implements IMatch{
	
	protected Deck deck;
	protected List<Card> discardPile;
	protected List<Player> players = new ArrayList<>();
	
	private String name1,name2,name3,name4,name5;
	private int maxScore;
	private Player winner;
	private boolean chinchonWin;
	
	private Scanner kb = new Scanner(System.in);
	private ConsoleInput console = new ConsoleInput(kb);
	
	public IMatch createMatch(int option) {	//factory
		
			switch (option) {
			case 1 -> {//partida con 1 jugador (+ IA)
				console.showMessage("Introduce el nombre del jugador:");
				return new IAMatch(console.readString());
			}
			case 2 ->{//partida con 2 jugadores (sin IA)				
				console.showMessage("Introduce el nombre del jugador 1:");
				name1=console.readString();
				console.showMessage("Introduce el nombre del jugador 2:");
				name2=console.readString();
				
				return new NoIAMatch(name1,name2);
			}
			case 3 ->{//partida con 2 jugadores (+ IA)
				console.showMessage("Introduce el nombre del jugador 1:");
				name1=console.readString();
				console.showMessage("Introduce el nombre del jugador 2:");
				name2=console.readString();
				
				return new IAMatch(name1,name2);
				
			}
			case 4 ->{//partida con 3 jugadores (sin IA)
				console.showMessage("Introduce el nombre del jugador 1:");
				name1=console.readString();
				console.showMessage("Introduce el nombre del jugador 2:");
				name2=console.readString();
				console.showMessage("Introduce el nombre del jugador 3:");
				name3=console.readString();
				
				return new NoIAMatch(name1, name2, name3);
			}
			case 5 ->{//partida con 3 jugadores (+ IA)
				console.showMessage("Introduce el nombre del jugador 1:");
				name1=console.readString();
				console.showMessage("Introduce el nombre del jugador 2:");
				name2=console.readString();
				console.showMessage("Introduce el nombre del jugador 3:");
				name3=console.readString();
				
				return new IAMatch(name1,name2,name3);
				
			}
			case 6 ->{//partida con 4 jugadores (sin IA)
				console.showMessage("Introduce el nombre del jugador 1:");
				name1=console.readString();
				console.showMessage("Introduce el nombre del jugador 2:");
				name2=console.readString();
				console.showMessage("Introduce el nombre del jugador 3:");
				name3=console.readString();
				console.showMessage("Introduce el nombre del jugador 4:");
				name4=console.readString();
				
				return new NoIAMatch(name1,name2,name3,name4);
			}
			case 7 ->{//partida con 4 jugadores (+ IA)
				console.showMessage("Introduce el nombre del jugador 1:");
				name1=console.readString();
				console.showMessage("Introduce el nombre del jugador 2:");
				name2=console.readString();
				console.showMessage("Introduce el nombre del jugador 3:");
				name3=console.readString();
				console.showMessage("Introduce el nombre del jugador 4:");
				name4=console.readString();
				
				return new IAMatch(name1,name2,name3,name4);
			}
			case 8 ->{//partida con 5 jugadores (sin IA)
				console.showMessage("Introduce el nombre del jugador 1:");
				name1=console.readString();
				console.showMessage("Introduce el nombre del jugador 2:");
				name2=console.readString();
				console.showMessage("Introduce el nombre del jugador 3:");
				name3=console.readString();
				console.showMessage("Introduce el nombre del jugador 4:");
				name4=console.readString();
				console.showMessage("Introduce el nombre del jugador 5:");
				name5=console.readString();
				
				return new NoIAMatch(name1,name2,name3,name4,name5);
				
			}
			default ->{
				console.showMessage("Saliendo...");
				return null;
			}
			}	
			
		}
	
	private void configureMaxScore() {
		console.showMessage("Introduce la puntuación máxima de la partida: ");
		maxScore = console.readInt();
	}

	@Override
	public void start() {
		while (players.size()>1 && !chinchonWin) {
			playNewRound();
			eliminatePlayer();
		}
		
		if (chinchonWin) {
			console.showFormattedMessage("CHINCHÓN... El ganador es %s\n", winner.getName());
		}else {
			winner = players.get(0);
			console.showFormattedMessage("El ganador es %s\n", winner.getName());
		}
	}
	
	@Override
	public void distributeCards() {
		deck = new Deck();
		deck.shuffle();
		
		for (Player player : players) {
			player.cleanHand();
		}
		
		for (int i=0;i<7;i++) {
			for (Player player : players) {
				checkIfDeckIsEmpty();
				player.draw(deck.getCards().remove(0));
			}
			
		}
		
		discardPile.add(deck.getCards().remove(0));
	}
	
	@Override
	public void playNewRound() {
		boolean endRound = false,closeRound;
		int roundScore, turnNumber = 1;
		discardPile = new ArrayList<>();
		Player current;
		
		distributeCards();
		
		while (!endRound) {
			console.showFormattedMessage("\n===== Turno %d =====\n",turnNumber);
			
			for (int i=0;i<players.size() && !endRound;i++) {
				current = players.get(i);
				closeRound = playTurn(current,turnNumber);
				
				if (closeRound) {
					endRound = true;
					
					roundScore = current.calculateCloseScore();
					
					//en Player, la puntuación MIN_VALUE equivale a chinchón
					if (roundScore == Integer.MIN_VALUE) {
						chinchonWin = true;
						winner = current;
						showRoundResults(current,roundScore);
					}else {
						winner = current;
						current.setScore(current.getScore()+roundScore);
						showRoundResults(current,roundScore);
					}
				}
			}
			
			turnNumber++;
		}
	}
	
	@Override
	public boolean playTurn(Player player, Integer turn) {
		if (player instanceof IA) {
			playTurnIA(player,turn);
			return false;
		}
		
		return playTurnPerson(player,turn);
	}
	
	//true si el jugador cierra ronda este turno
	private boolean playTurnPerson(Player player, int turn) {
		int option;
		Card drawn, toDiscard;
		int discardIndex;
		
		console.showFormattedMessage("\n--- Turno de %s ---\n",player.getName());
		console.showFormattedMessage("Carta en el descarte: %s\n",discardPile.getLast());
		player.showHand();
		
		//robar
		console.showFormattedMessage("¿Robas del (1)MAZO o del (2)DESCARTE (%s)?\n", discardPile.getLast());
		option = console.readIntInRange(1, 2);
		
		if (option == 1) {
			checkIfDeckIsEmpty();
			drawn = deck.getCards().remove(0);
			player.draw(drawn);
			console.showFormattedMessage("%s roba del MAZO\n",player.getName());
		}else {
			drawn = discardPile.removeLast();
			player.draw(drawn);
			console.showFormattedMessage("%s roba el %s del DESCARTE\n",player.getName(),drawn);
		}
		
		//descartar
		console.showMessage("Elige el índice de la carta que quieres descartar: ");
		player.showHand();
		discardIndex = console.readIntInRange(0, player.getHand().size()-1);
		
		toDiscard = player.getHand().get(discardIndex);
		player.discard(toDiscard);
		discardPile.add(toDiscard);
		console.showFormattedMessage("%s descarta el %s\n",player.getName(),toDiscard);
		
		//cerrar ronda
		if (turn>1 && player.canClose()) {
			console.showMessage("Puedes cerrar ronda, ¿quieres hacerlo? (S/N)");
			if (console.readBooleanUsingChar('s', 'n')) {
				return true;
			}
		}else if (turn > 1 && !player.canClose()) {
			console.showMessage("No tienes combinaciones suficientes para cerrar ronda");
		}
		
		return false;		
		
	}
	
	private void playTurnIA(Player ia, int turn) {
		int random = (int) (Math.random()*ia.getHand().size());
		Card toDiscard;
		
		console.showFormattedMessage("\n--- Turno de la IA ---\n");
		checkIfDeckIsEmpty();
		
		//IA roba
		ia.draw(deck.getCards().remove(0));
		console.showMessage("La IA roba del MAZO");
		
		//IA descarta
		toDiscard = ia.getHand().get(random);
		ia.discard(toDiscard);
		discardPile.add(toDiscard);
		console.showFormattedMessage("La IA descarta: %s\n",toDiscard);
		
	}
	
	@Override
	public void showRoundResults(Player closer, int closerScore) {
		int points, leftoverPoints;
		
		console.showMessage("===== RESULTADOS DE LA RONDA =====");
		
		//en Player MIN_VALUE significa chinchón
		if (closerScore == Integer.MIN_VALUE) {
			console.showFormattedMessage("CHINCHÓN... El ganador es %s.\n", closer.getName());
		}else if (closerScore == -10) {
			console.showFormattedMessage("%s ha cerrado con 7 cartas combinadas (-10 puntos). Puntuación: %d\n", closer.getName(),closer.getScore());
	
		}else {
			console.showFormattedMessage("%s cierra la ronda (+%d puntos). Puntuación: %d\n", closer.getName(),closerScore,closer.getScore());
		}
		
		if (closerScore != Integer.MIN_VALUE) {
			for (Player player : players) {
				if (player != closer) {
					points = player.calculateCloseScore();
					leftoverPoints = player.getLeftoverScore();
					player.setScore(player.getScore()+leftoverPoints);
					console.showFormattedMessage("%s suma %d puntos por cartas no combinadas. Puntuación total: %d\n", player.getName(),leftoverPoints,player.getScore());
				}
			}
		}
		
		console.showFormattedMessage("Puntuación límite: %d\n", maxScore);
		console.showMessage("==================================");
	}
	
	@Override
	public void eliminatePlayer() {
		List<Player> eliminated = new ArrayList<>();
		
		for (Player player : players) {
			if (player.getScore()>=maxScore) {
				eliminated.add(player);
				console.showFormattedMessage("%s ha sido eliminado con %d puntos.\n", player.getName(),player.getScore());
			}
		}
		
		players.removeAll(eliminated);
	}
	
	private void checkIfDeckIsEmpty() {
		Card topDiscard;
		
		if (deck.getCards().isEmpty()) {
			topDiscard = discardPile.removeLast();
			deck.getCards().addAll(discardPile);
			deck.shuffle();
			discardPile.clear();
			discardPile.add(topDiscard);
			console.showMessage("El mazo estaba vacío, se ha barajado con las del descarte.");
		}
	}
}
