package app;

import java.util.Scanner;

import resources.Deck;
import resources.Player;

public class Match implements IMatch{
	
	protected Deck deck;
	protected Player player1,player2,player3,player4,player5;
	private String name1,name2,name3,name4,name5;
	private int round;
	
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

	
	//TODO: ¿Como hago el flujo de partida si tengo que tener en cuenta el número de jugadores?
	/**
	 * Empieza en ronda 1. 
	 * Cada ronda se baraja el (o los) mazo(s).
	 * Se reparten 7 cartas a cada jugador.
	 * Se pone una carta inicial en el centro de la mesa como descarte.
	 * El resto de cartas se dejan en el mazo como mazo de robar.
	 * 
	 * En cada turno (una ronda tiene varios turnos):
	 * El jugador elige si robar del mazo o coger la que está boca arriba en el centro.
	 * El jugador decide si descartar carta o si cerrar la partida (solo si tiene alguna combinación válida)
	 * El jugador siempre debe terminar su turno con 7 cartas en su mano.
	 * Pasa el turno al siguiente jugador
	 * 
	 * Combinaciones:
	 * --Iguales: 3 cartas o más del mismo número
	 * --Escalera: 3 cartas o más consecutivas del mismo palo
	 * --Chinchón: 7 cartas consecutivas del mismo palo
	 * 
	 * Cierre de ronda:
	 * 
	 * 
	 * Puntuación:
	 * 
	 * 
	 * 
	 */
	@Override
	public void start() {
		
	}

	@Override
	public void distributeCards() {
	}

	@Override
	public void playNewRound() {
	}

	@Override
	public void playTurn() {
	}

	@Override
	public void showRoundResults() {
	}	
	
	
}
