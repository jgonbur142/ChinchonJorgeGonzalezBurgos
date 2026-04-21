package app;

public class Main {
	
	public void turnOn() {
		Menu menu = new Menu();
		menu.startGame();
	}

	public static void main(String[] args) {
		new Main().turnOn();
	}

}
