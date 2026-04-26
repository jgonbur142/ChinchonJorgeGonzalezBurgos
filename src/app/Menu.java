package app;

import java.util.Scanner;

public class Menu {
	
	private Scanner kb = new Scanner(System.in);
	private ConsoleInput console;
	
	public Menu() {
		console = new ConsoleInput(kb);
	}
	
	public void startGame() {
		Match match = new Match();
		IMatch current;
		int option;
		
		do {
			console.showMessage("");
			showCover();
			console.showMessage("--------------------------------");
			console.showMessage("- 1. Un jugador ----------------"); //1 jugador + IA
			console.showMessage("- 2. Dos jugadores (sin IA) ----"); //2 jugadores o 2 jugadores + IA
			console.showMessage("- 3. Dos jugadores (con IA) ----");
			console.showMessage("- 4. Tres jugadores (sin IA) ---");
			console.showMessage("- 5. Tres jugadores (con IA) ---");
			console.showMessage("- 6. Cuatro jugadores (sin IA) -");
			console.showMessage("- 7. Cuatro jugadores (con IA) -");
			console.showMessage("- 8. Cinco jugadores (sin IA) --");
			console.showMessage("- 9. Salir ---------------------");
			console.showMessage("");
			
			option = console.readInt();
			current = match.createMatch(option);
			
			if (current != null) {
				current.start();
			}
			
		}while (option!=9);
	}
	
	private void showCover() {
		console.showMessage("----------------------------------");
		console.showMessage("             Chinchón             ");
		console.showMessage("Jorge González Burgos - 1ºDAM");
		console.showMessage("----------------------------------");
	}
	
}
