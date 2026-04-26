package app;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import resources.Card;
import resources.Deck;
import resources.IA;
import resources.Player;
import resources.Suit;

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
		
		configureMaxScore();
		
		//mientras haya jugadores y nadie haya sacado chinchón
		while (players.size() > 1 && !chinchonWin) {
			playNewRound();
			eliminatePlayer();
		}
		
		if (chinchonWin) {
			console.showFormattedMessage("¡Ha salido chinchón! El ganador es: %s\n", winner.getName());
		}else {
			winner = players.get(0);
			console.showFormattedMessage("El ganador es: %s\n", winner.getName());
		}
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
				checkIfDeckIsEmpty();
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
			for (Player p : players) {
				playTurn(p, turnCount);
				
				if (turnCount>1 && canEndRound(p)) {
					winner = p;
					endRound = true;
				}
			}
			turnCount++;
		
		}
		//cuando alguien haya cerrado, se muestran los resultados
		showRoundResults();
		
	}
	
	@Override
	public boolean playTurn(Player player,Integer turn) {
		
		if (player instanceof IA) {
			return playTurnIA(player,turn);
		}else {
			return playTurnPerson(player,turn);
		}
	}
	
	private void checkIfDeckIsEmpty() {
		Card topDiscard;
		
		if (deck.getCards().isEmpty()) {
			topDiscard = discardPile.remove(discardPile.size()-1);
			deck.getCards().addAll(discardPile);
			deck.shuffle();
			discardPile.clear();
			discardPile.add(topDiscard);
			console.showMessage("No quedan cartas en el mazo, se han barajado del descarte.");
		}
		
	}
	
	//la IA por ahora solo roba la carta del mazo y se descarta la primera carta, nunca cierra
	private boolean playTurnIA(Player ia, Integer turn) {
		Card toDiscard;
		int random = (int) (Math.random()*ia.getHand().size());
		
		console.showMessage("Turno de la IA");
		
		//decisón de robar:
		ia.draw(deck.getCards().remove(0));
		console.showMessage("La IA ha robado una carta del mazo");
		
		//decisión de descarte:
		toDiscard = ia.getHand().get(random);
		ia.discard(toDiscard);
		discardPile.add(toDiscard);
		
		console.showFormattedMessage("La IA ha descartado: %s\n", toDiscard);
		
		return false;
	}

	private boolean playTurnPerson(Player player,Integer turn) {
		int option;
		boolean choise;
		Card toDiscard, drawCard; //la carta que se quiere descartar
		boolean hasClosed = false;
		
		console.showFormattedMessage("Turno de %s\n", player.getName());
		player.showHand();
		
		//primera acción del turno: robar
		console.showFormattedMessage("1. Robar del mazo || 2. Robar del descarte( %s )\n",discardPile.getLast());
		option = console.readIntInRange(1, 2);
		
		if (option==1) {//robar del mazo
			checkIfDeckIsEmpty();
			drawCard = deck.getCards().remove(0);
			player.draw(drawCard);
			console.showFormattedMessage("Carta robada del mazo: %s\n",drawCard);

		}else {//robar del descarte
			player.draw(discardPile.removeLast());

		}
		
		//segunda acción del turno: cerrar (si puede)
		if (turn>1) {
			console.showMessage("¿Quieres cerrar la ronda? (S/N)");
			choise = console.readBooleanUsingChar('s', 'n');
			
			if (choise) {
				hasClosed = canEndRound(player);
			}
		}
		
		if (!hasClosed) {
			player.showHand();
			
			console.showMessage("Elige la carta que quieres descartar (0-7)");
			
			toDiscard = player.getHand().get(console.readIntInRange(0, 7));
			player.discard(toDiscard);
			discardPile.add(toDiscard);
			
		}	
		
		return hasClosed;
		
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
		
		boolean attempt = true, close = false;
		sevenClosed=false;
		chinchonWin=false;
		leftCard=null;
		int combine, index=0;
		List<Card> selected = new ArrayList<>();
		List<Card> remaining = new ArrayList<>(player.getHand());
		Card discard;
		
		while (!close && attempt) {
				console.showMessage("¿Cierras con 6 o 7 cartas?");
				combine = console.readIntInRange(6, 7);
				
				for (int i=0;i<combine;i++) {
					player.showHand();
					console.showFormattedMessage("Elige la %dº carta (0-6)", i+1);
					index=console.readIntInRange(0, 6);
					selected.add(remaining.remove(index));
					
				}
				
				if (isValidCombination(selected)) {
					
					if (combine == 7) {				
						
						sevenClosed=true;
						chinchonWin = isSequence(selected);
						close = true;
						player.discard(remaining.get(0));
						discardPile.add(remaining.get(0));
						
					}else {//si se combina con 6
						
						if (remaining.get(0).getNumber().getValue() <= 5) {
							
							console.showFormattedMessage("Cierre de 6 válido. Elige qué carta descartar (0-1) (%s - %s)\\n", remaining.get(0),remaining.get(1));
							index = console.readIntInRange(0, 1);
							discard = remaining.remove(index);
							leftCard = remaining.get(0);
							
							if (leftCard.getNumber().getValue()<=5) {
								player.discard(discard);
								discardPile.add(discard);
								close = true;
							}else {
								console.showMessage("ERROR: La carta que queda al intentar cerrar debe ser menor o igual que 5");
							}
							
						}
						remaining = new ArrayList<>(player.getHand());
						remaining.removeAll(selected);
						leftCard = remaining.get(0);
						
						if (leftCard.getNumber().getValue()<=5) {
							close = true;				
						}else {
							console.showMessage("No puedes cerrar si la carta que sobra no es menor o igual que 5");
						}
					}
						
					}else {
						console.showMessage("Combinación no válida. Deben tener el mismo número (3 o más) o ser una escalera válida del mismo palo (3 o más)");
					}
		}
		
		return close;
		
	}
	
	@Override
	public void eliminatePlayer() {//si ha superado la puntuación máxima, se elimina
		players.removeIf(p -> p.getScore()>=maxScore);
	}

	@Override
	public void showRoundResults() {//se muestran las puntuaciones
		int points=0;
		
		console.showMessage("----- RESULTADOS DE LA RONDA -----");
		
		for (Player p : players) {
			if (p == winner) {
				if (sevenClosed) {
					//si ha hecho una combinación de 7 cartas
					p.setScore(p.getScore()-10);
					if (p.getScore()<0) {
						p.setScore(0);
					}
					console.showFormattedMessage("%s ha cerrado con 7 cartas (-10 puntos). Puntuación: %d\n", p.getName(),p.getScore());
					
				}else if(leftCard != null){
					points = leftCard.getNumber().getValue();
					p.setScore(p.getScore()+points);
					console.showFormattedMessage("%s ha cerrado con 6 cartas (+%d puntos). Puntuación: %d\n", p.getName(),points,p.getScore());
					
				}
			}else {
				points = calculatePlayerScore(p);
				p.setScore(p.getScore()+points);
				console.showFormattedMessage("%s: %d puntos\n", p.getName(),p.getScore());
				
			}
			
		}
		
	}
	
	private int calculatePlayerScore(Player player) {
		List<Card> remaining = new ArrayList<>(player.getHand());
		List<Card> selectedCards;
		List<Integer> selectedIndex;
		boolean combine = true;
		int count, index, sum;
		
		//primero compruebo si el jugdor es la IA
		if (player instanceof IA) {
			return calculateIAScore(player);
		}
		
		console.showFormattedMessage("%s, combina las cartas que puedas en tu mano\n", player.getName());
		
		while (combine && remaining.size()>=3){
			console.showFormattedMessage("Cartas en mano: %s\n", remaining);
			console.showMessage("¿Puedes hacer alguna combinación? (S/N)");
			
			if (console.readBooleanUsingChar('s', 'n')) {
				console.showFormattedMessage("¿Cuántas cartas están en la combinación? (3-%d)\n", remaining.size());
				count = console.readIntInRange(3, remaining.size());
				
				selectedCards = new ArrayList<>();
				selectedIndex = new ArrayList<>();
				
				for (int i = 1; i<=count; i++) {
					index=0;
					do {
						console.showFormattedMessage("Elige el índice de la carta %d (0-%d)\n",i,remaining.size()-1);
						index = console.readIntInRange(0, remaining.size()-1);
						if (selectedIndex.contains(index)) {
							console.showMessage("Esa carta ya la has elegido antes");
						}
					}while (selectedIndex.contains(index));
					
					selectedIndex.add(index);
					selectedCards.add(remaining.get(index));
				}
				
				if (isValidCombination(selectedCards)) {
					
					console.showMessage("Combinación válida");
					selectedIndex.sort((a,b) -> Integer.compare(b, a)); //las ordeno al revés para borrar las últimas cartas y no estropear el índice
					for (int cardPosition : selectedIndex) {
						remaining.remove(cardPosition);
					}
				}else {
					console.showMessage("Combinación no válida. Prueba otra combinación o pulsa 'N' para terminar");
				}
			}else {
				combine = false;
			}
		}
		
		sum = 0;
		for (Card c : remaining) {
			sum += c.getNumber().getValue();
		}
		return sum;
		
	}
	
	private int calculateIAScore(Player player) {
		List<Card> hand = new ArrayList<>(player.getHand()),sameValueCards,suitCards;
		int points=0;
		
		//la IA busca combinaciones de iguales en su mano
		for (int i=0;i<hand.size();i++) {
			int val = hand.get(i).getNumber().getValue(); //tengo que declarar val dentro del for porque necesita ser eficazmente final
			sameValueCards = hand.stream()
					.filter(card -> card.getNumber().getValue() == val)
					.collect(Collectors.toList());
			
			if (sameValueCards.size() >= 3) {
				hand.removeAll(sameValueCards);
			}
		}
		
		//la IA busca escaleras en su mano
		for (Suit suit : Suit.values()) {
			suitCards = hand.stream()
					.filter(card -> card.getSuit() == suit)
					.sorted((card1,card2) -> Integer.compare(card1.getNumber().ordinal(), card2.getNumber().ordinal()))
					.collect(Collectors.toList());
			
			if (suitCards.size() >= 3) {
				if (isSequence(suitCards)) {
					hand.removeAll(suitCards);
				}
			}
		}
		
		for (Card c : hand) {
			points += c.getNumber().getValue();
		}
		
		return points;
		
	}
	/**
	 * TODO: 
	 * Refactorizar 
	 * Optimizar
	 * Actualizar UML
	 * 
	 * Que se puedan hacer combinaciones válidas de cartas iguales (no solo combinaciones de una escalera de 6 o 7)
	 * Corregir flujo: me dice cuantas quiero descartar despues de negar por segunda vez que no quiero cerrar
	 * 
	 */
	
}
