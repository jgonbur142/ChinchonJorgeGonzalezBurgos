package resources;

public class IA extends Player{
	
	public IA() {
		super("IA");
	}
	
	
	/**
	 * Decide si cierra o no la ronda en función de la puntuación
	 * @return True si cierra la ronda, False si continúa la ronda
	 */
	/*
	public boolean mustEndRound() {
		
	}
	*/
	
	//creo que no hace falta sobrescribir cómo se calcula el score
	@Override
	public void calculateScore() {
		
	}
	
	// hay que sobrescribir la lógica con la que la IA descarta, para que juegue con estrategia
	@Override
	public void discard(Card card) {
		
	}
}
