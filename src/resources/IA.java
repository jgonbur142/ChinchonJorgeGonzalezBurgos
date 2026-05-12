package resources;
/**
 * Representa al jugador controlado por la máquina.
 * Hereda toda la lógica de {@link Player}; la única diferencia es
 * que siempre se llama "IA" y su turno lo gestiona {@code Match} de forma automática.
 */
public class IA extends Player{
	
	/**
	 * Crea el jugador IA con el nombre predeterminado "IA".
	 */
	public IA() {
		super("IA");
	}
	
}
