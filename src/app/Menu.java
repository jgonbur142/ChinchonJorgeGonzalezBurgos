package app;

/**
 * Clase que gestiona el menú principal de la aplicación.
 * Permite al usuario seleccionar el tipo de partida y la inicia.
 */
public class Menu {
	
	private ConsoleInput console;
	
	/**
	 * Crea una instancia de Menu obteniendo la consola compartida.
	 */
	public Menu() {
		console = ConsoleInput.getInstance();
	}
	
	/**
	 * Muestra el menú principal en bucle, crea la partida elegida y la inicia.
	 * El bucle continúa hasta que el usuario selecciona la opción de salir.
	 */
	public void startGame() {
		Match match = new Match();
		IMatch current;
		int option;
		
		do {
			console.showMessage("");
			showCover();
			console.showMessage("--------------------------------");
			console.showMessage("- 1. Un jugador ----------------"); 
			console.showMessage("- 2. Dos jugadores (sin IA) ----"); 
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
	
	/**
	 * Muestra la cabecera del juego con el título y el nombre del autor.
	 */
	private void showCover() {
		console.showMessage("----------------------------------");
		console.showMessage("             Chinchón             ");
		console.showMessage("Jorge González Burgos - 1ºDAM");
		console.showMessage("----------------------------------");
	}
	
}
