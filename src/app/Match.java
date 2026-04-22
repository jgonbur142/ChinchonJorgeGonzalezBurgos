package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import resources.Card;
import resources.Deck;
import resources.Player;

public class Match implements IMatch{
	
	protected Deck deck;
	protected List<Card> discardPile;
	protected List<Player> players = new ArrayList<>();
	
	private String name1,name2,name3,name4,name5;
	private int maxScore;
	
	private Player winner;
	private boolean sevenClosed;
	private boolean chinchonWin;
	private Card leftCard;
	
	private Scanner kb = new Scanner(System.in);
	private ConsoleInput console;
	
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
		
		configureMaxScore();
		
		//mientras haya jugadores
		while (players.size() > 1 && !chinchonWin) {
			playNewRound();
			eliminatePlayer();
		}
		
		winner = chinchonWin ? winner : players.get(0);
		console.showFormattedMessage("El ganador es: %s\n", winner.getName());
	}

	@Override
	public void distributeCards() {
		deck = new Deck();
		deck.shuffle();
		
		for (Player p : players) {//limpiar las manos de los jugadores
			p.cleanHand();
		}
		
		for (int i=0;i<7;i++) {//7 cartas para cada jugador
			for (Player p : players) {
				p.draw(deck.getCards().remove(0)); //roba la carta de arriba del mazo
			}
		}
		
		discardPile.add(deck.getCards().remove(0));//la carta de descarte inicial (boca arriba en el centro de la mesa)
	}

	@Override
	public void playNewRound() {//una ronda tiene varios turnos
		
		boolean endRound = false;
		int turnCount = 1;
		winner = null;
		sevenClosed = false;
		chinchonWin = false;
		leftCard = null;
		discardPile = new ArrayList<>();
		
		distributeCards();
		
		while(!endRound) {
			int i = 0;
			
			while (i<players.size() && !endRound) {
				
				endRound = playTurn(players.get(i),turnCount);
				i++;
			}
			turnCount++;
		
		}
		//cuando alguien haya cerrado, se muestran los resultados
		showRoundResults();
		
	}

	@Override
	public boolean playTurn(Player player,Integer turn) {
		int option;
		Card toDiscard; //la carta que se quiere descartar
		
		console.showFormattedMessage("Turno de %s\n", player.getName());
		
		//primera acción del turno: robar
		console.showFormattedMessage("1. Robar del mazo || 2. Robar del descarte( %s )\n",discardPile.getLast());
		option = console.readIntInRange(1, 2);
		
		if (option==1) {
			player.draw(deck.getCards().remove(0));
		}else {
			player.draw(discardPile.removeLast());
		}
		
		//segunda acción del turno: cerrar (si puede)
		if (turn>1 && canEndRound(player)) {
			console.showMessage("¿Quieres cerrar la ronda? (S/N)");
			if (console.readBooleanUsingChar('s', 'n')) {
				winner = player;
				
				if (sevenClosed) {
					toDiscard = player.getHand().get(0);
				}else {
					toDiscard = player.getHand().get(0).equals(leftCard) ? player.getHand().get(1) : player.getHand().get(0);
				}
				
				player.discard(toDiscard);
				discardPile.add(toDiscard);
				
				return true;
			}
		}
		
		//tercera acción: descartar (si no ha cerrado)
		console.showMessage("Elige la carta que quieres descartar (0-7)");
		
		toDiscard = player.getHand().get(console.readIntInRange(0, 7));
		player.discard(toDiscard);
		discardPile.add(toDiscard);
		
		return false;
		
	}
	
	//valido por separado si es una escalera para reciclar el codigo a la hora de comprobar si el jugador tiene chinchón
	private boolean isSequence(List<Card> cards) {
		if (cards.size()<3) {
			return false;
		}
		
		//ordeno las cartas para comprobar luego más fácil si es una escalera
		cards.sort((c1,c2) -> Integer.compare(c1.getNumber().getValue(), c2.getNumber().getValue()));
		
		for (int i=0;i<cards.size()-1;i++) {
			
			//si los palos de las cartas no son los mismos
			if (cards.get(i).getSuit() != cards.get(i+1).getSuit()) {
				return false;
			}
			
			//si los números no son consecutivos
			if (cards.get(i).getNumber().ordinal()+1 != cards.get(i+1).getNumber().ordinal()) {//.ordinal porque si no, 7 y 10 no cuentan como consecutivos, asi uso el orden del enum
				return false;
			}			
		}
		return true;
	}
	
	private boolean isValidCombination(List<Card> cards) { //falta comprobar si el jugador tiene chinchón
		boolean sameNumber = true;
		int firstNum = cards.get(0).getNumber().getValue();
		
		if (cards.size()<3) {
			return false;
		}
		
		//compruebo si son el mismo número
		for (Card c : cards) {
			if (c.getNumber().getValue() != firstNum) {
				sameNumber = false;
			}
		}
		
		return sameNumber || isSequence(cards);
		
		
	}
	
	@Override
	public boolean canEndRound(Player player) {// true si el jugador reune las condiciones para cerrar ronda
		
		sevenClosed=false;
		chinchonWin=false;
		leftCard=null;
		
		int combine, index=0, stayIndex;
		List<Card> selected = new ArrayList<>();
		List<Card> remaining = new ArrayList<>(player.getHand());
		Card toDiscard, stay;
		
		console.showFormattedMessage("Mano actual de %s: %s\n",player.getName(),player.getHand());
		
		console.showMessage("Cuántas cartas quieres combinar (6-7)");
		combine=console.readIntInRange(6, 7);
		
		for (int i=0;i<combine;i++) {
			
			console.showFormattedMessage("Elige el índice de la carta para combinar (0-%d)\n", remaining.size()-1);
			index = console.readIntInRange(0, remaining.size()-1);
			selected.add(remaining.remove(index));
			
		}
		
		if (isValidCombination(selected)) {
			
			if (combine == 7) {
				
				sevenClosed=true;
				
				if(isSequence(selected)) {
					
					chinchonWin = true;
					console.showMessage("Has ganado con un CHINCHÓN");
					
				}else {
					
					console.showMessage("Has cerrado con 7 cartas");
					
				}
				
				/*
				toDiscard = remaining.remove(0);
				player.discard(toDiscard);
				discardPile.add(toDiscard);
				*/
				
				return true;
				
			}else {//si se combina con 6
				console.showMessage("Te sobran dos cartas, ¿cuál quieres quedarte?");
				for (int i=0;i<remaining.size();i++) {
					console.showFormattedMessage("%d: %s\n", i,remaining.get(i));
				}
				
				stayIndex = console.readIntInRange(0, 1);
				leftCard = remaining.get(stayIndex);
				stay = remaining.get(stayIndex);
				toDiscard = remaining.get(stayIndex == 0 ? 1 : 0);

				if (stay.getNumber().getValue() <= 5) {
					
					leftCard = stay;
					/*
					player.discard(toDiscard);
					discardPile.add(toDiscard);
					*/
					
					return true;
				}else {
					console.showMessage("No se puede cerrar: la carta que te quede debe tener valor entre 1 y 5");
				}
			}
		}else {
			console.showMessage("Combinación no válida. Deben tener el mismo número (3+ o más) o ser una escalera válida del mismo palo (3 o más)");
		}
		return false;
		
	}
	
	@Override
	public void eliminatePlayer() {//si ha superado la puntuación máxima, se elimina
		players.removeIf(p -> p.getScore()>=maxScore);
	}

	@Override
	public void showRoundResults() {//se muestran las puntuaciones
		
		console.showMessage("----- RESULTADOS DE LA RONDA -----");
		
		for (Player p : players) {
			if (p == winner) {
				if (sevenClosed) {
					//restar 10 puntos a la puntuación
				}else {
					//añadirle a la puntuación el valor de las cartas que sobran
				}
			}else {
				p.calculateScore();
			}
			
			console.showFormattedMessage("%s: %d puntos", p.getName(),p.getScore());
		}
		
	}	
	
	
}
