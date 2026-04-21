package app;

import java.util.Scanner;

public class Menu {
	
	private Scanner kb = new Scanner(System.in);
	private ConsoleInput console;
	
	public Menu() {
		console = new ConsoleInput(kb);
	}
	
	public void startGame() {
		String option="";
		
		do {
			console.showMessage("");
			showCover();
			console.showMessage("-----------------------");
			console.showMessage("- 1. Dos jugadores ----");
			console.showMessage("- 2. Tres jugadores ---");
			console.showMessage("- 3. Cuatro jugadores -");
			console.showMessage("- 4. Salir ------------");
			console.showMessage("");
			
			option = console.readString();
			
			switch (option) {
			case "1" -> {
				//iniciar partida (Match debe crear una LessPlayer)
			}
			case "2" -> {
				//iniciar partida (Match debe crear un MorePlayers de 3 jugadores)
			}
			case "3" ->{
				//iniciar partida (Match debe crera un MorePlayers de 4 jugadores)
			}
			case "4" ->{
				console.showMessage("Saliendo...");
			}
			default -> console.showMessage("Opción no válida");
			}
			
		}while (!option.equals("4"));
	}
	
	private void showCover() {
		console.showMessage("----------------------------------");
		console.showMessage("             Chinchón             ");
		console.showMessage("Jorge González Burgos - 1ºDAM");
		console.showMessage("----------------------------------");
	}
	
}
