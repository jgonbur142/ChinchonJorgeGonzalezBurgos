package app;

/**
 * Clase principal de la aplicación. Punto de entrada del programa.
 */
public class Main {
	
	/**
	 * Inicializa y arranca la aplicación mostrando el menú principal.
	 */
	public void turnOn() {
		Menu menu = new Menu();
		menu.startGame();
	}

	/**
	 * Método principal. Crea una instancia de Main y arranca la aplicación.
	 * @param args argumentos de la línea de comandos
	 */
	public static void main(String[] args) {
		new Main().turnOn();
	}

}
