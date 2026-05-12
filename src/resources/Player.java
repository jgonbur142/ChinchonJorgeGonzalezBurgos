package resources;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Representa a un jugador con el nombre, cartas en mano y puntuación
 * que tendrá a lo largo de una partida
 */
public class Player implements IPlayer{

	private String name;
	private List<Card> hand;
	private int score;
	
	/**
	 * Crea un jugador con el nombre indicado, mano vacía y puntuación 0.
	 * @param name nombre del jugador
	 */
	public Player(String name) {
		this.name=name;
		this.hand = new ArrayList<>();
		score=0;
	}
	
	@Override
	public void draw(Card card) {
		if (card != null) {
			hand.add(card);
		}
	}

	@Override
	public void discard(Card card) {
		if (card != null) {
			hand.remove(card);
		}		
	}

	@Override
	public void calculateScore() {
		int points=0;
		
		for (Card c : hand) {
			points += c.getNumber().getValue();
		}
		
		score += points;
	}
	
	/**
	 * Comprueba si una lista de cartas forma una escalera válida:
	 * mínimo 3 cartas, mismo palo y valores ordinales consecutivos.
	 * @param cards lista de cartas a comprobar
	 * @return {@code true} si forman escalera válida, {@code false} en caso contrario
	 */
	private boolean isStraight(List<Card> cards) {
		
		if (cards.size()<3) {
			return false;
		}
		
		for (int i = 0;i<cards.size()-1;i++) {
			if (cards.get(i).getSuit()!=cards.get(i+1).getSuit()) {
				return false;
			}
			if (cards.get(i).getNumber().ordinal()+1!=cards.get(i+1).getNumber().ordinal()) {
				return false;
			}
		}
		
		return true;
		
	}
	
	/**
	 * Comprueba si una lista de cartas forma un grupo de iguales válido:
	 * mínimo 3 cartas con el mismo valor numérico.
	 * @param cards lista de cartas a comprobar
	 * @return {@code true} si todas tienen el mismo valor, {@code false} en caso contrario
	 */
	private boolean isSameGroup(List<Card> cards) {
		if (cards.size()<3) {
			return false;
		}
		
		for (Card card : cards) {
			if (card.getNumber().getValue()!=cards.get(0).getNumber().getValue()) {
				return false;
			}
		}
		
		return true;
	}
	
	@Override
	public boolean isChinchon() {
		List<Card> sorted = new ArrayList<>(hand);
		
		if (hand.size()!=7) {
			return false;
		}
		
		sorted.sort(Comparator.comparingInt(card -> card.getNumber().ordinal()));
		
		return isStraight(sorted);
		
	}
	
	/**
	 * Algoritmo recursivo que busca la combinación óptima de cartas en la mano,
	 * minimizando el valor de las cartas que sobran sin combinar.
	 * Actualiza {@code bestResult} cuando encuentra un resultado mejor.
	 * @param remaining  cartas que aún no han sido asignadas a ninguna combinación
	 * @param bestResult lista de un elemento que almacena el mejor sobrante encontrado hasta ahora
	 */
	private void findBestCombination(List<Card> remaining, List<List<Card>> bestResult) {
		boolean foundCombination = false;
		List<List<Card>> subsets;
		List<Card> next;
		
		//caso base, no quedan cartas: return
		if (remaining.isEmpty()) {
			bestResult.set(0, new ArrayList<>());
			return;
		}
		
		//caso base, quedan 1 o 2 cartas que no se pueden combinar
		if (remaining.size()<3) {
			if (bestResult.get(0) == null || sumValues(remaining)<sumValues(bestResult.get(0))) {
				bestResult.set(0,new ArrayList<>(remaining));
			}
			return;
		}
		
		//si en el mejor resultado sobran 0 cartas: return
		if (bestResult.get(0)!=null && bestResult.get(0).isEmpty()) {
			return;
		}
		
		//con las cartas que sobren, se buscan las combinaciones posibles
		for (int size=remaining.size();size>=3;size--) {
			
			subsets = generateSubsets(remaining, size);
			
			for (List<Card> subset : subsets) {
				if (isStraight(subset) || isSameGroup(subset)) {
					next = new ArrayList<>(remaining);
					next.removeAll(subset);
					findBestCombination(next, bestResult);
					
					if (bestResult.get(0)!=null && bestResult.get(0).isEmpty()) {
						return;
					}
				}
			}
		}
		
		if (!foundCombination) {
			if (bestResult.get(0) == null || sumValues(remaining) < sumValues(bestResult.get(0))) {
				bestResult.set(0,new ArrayList<>(remaining));
			}
		}
	}
	
	/**
	 * Genera todos los subconjuntos de tamaño {@code size} a partir de {@code cards}.
	 * @param cards lista de cartas origen
	 * @param size  tamaño de cada subconjunto
	 * @return lista de todos los subconjuntos posibles
	 */
	private List<List<Card>> generateSubsets(List<Card> cards, int size){
		List<List<Card>> result = new ArrayList<>();
		
		generateSubsetAux(cards,size,0,new ArrayList<>(),result);
		return result;
	}
	
	/**
	 * Método auxiliar recursivo para la generación de subconjuntos por backtracking.
	 * @param cards   lista de cartas origen
	 * @param size    tamaño objetivo del subconjunto
	 * @param start   índice desde el que se empieza a explorar en cada llamada recursiva
	 * @param current subconjunto que se está construyendo en este momento
	 * @param result  lista acumuladora donde se añaden los subconjuntos completos
	 */
	private void generateSubsetAux(List<Card> cards, int size, int start, List<Card> current, List<List<Card>> result) {
		if (current.size() == size) {
			result.add(new ArrayList<>(current));
			return;
		}
		
		for (int i=start;i<cards.size();i++) {
			current.add(cards.get(i));
			generateSubsetAux(cards, size, i+1, current, result);
			current.remove(current.size()-1);
		}
	}
	
	/**
	 * Suma los valores numéricos de una lista de cartas.
	 * @param cards lista de cartas a sumar
	 * @return suma total de los valores
	 */
	private int sumValues(List<Card> cards) {
		int total=0;
		for (Card card : cards) {
			total += card.getNumber().getValue();
		}
		return total;
	}
	
	/**
	 * Calcula y devuelve las cartas de la mano que no pertenecen a ninguna
	 * combinación válida, minimizando su valor total.
	 * @return lista de cartas sobrantes con el menor valor posible
	 */
	private List<Card> obtainLeftOverCards(){
		List<Card> sorted = new ArrayList<>(hand);
		List<List<Card>> bestResult = new ArrayList<>();
		
		sorted.sort(Comparator.comparingInt(card -> card.getNumber().ordinal()));
		bestResult.add(null);
		
		findBestCombination(sorted, bestResult);
		
		return bestResult.get(0) != null ? bestResult.get(0) : new ArrayList<>(sorted);		
		
	}
	
	@Override
	public int getLeftoverScore() {
		return sumValues(obtainLeftOverCards());
	}
	
	@Override
	public boolean canClose() {
		List<Card> leftovers = obtainLeftOverCards();				
		
		if (hand.size() != 7) {
			return false;
		}
		
		if (isChinchon()) {
			return true;
		}
		
		if (leftovers.size() > 1) {
			return false;
		}
		
		if (leftovers.size() == 1 && leftovers.get(0).getNumber().getValue()>5) {
			return false;
		}
		
		return true;	
		
	}
	
	@Override
	public int calculateCloseScore() {
		List<Card> leftovers = obtainLeftOverCards();
		
		if (!canClose()) {
			throw new IllegalStateException(String.format("%s no puede cerrar ronda con la mano actual.\n",name));
		}
		
		//utilizo el MIN_VALUE para luego en Match detectar fácilmente el chinchón
		if (isChinchon()) {
			return Integer.MIN_VALUE;
		}
		
		//se ha cerrado ronda con 7 combinadas, -10 puntos
		if (leftovers.isEmpty()) {
			return -10;
		}
		
		return leftovers.get(0).getNumber().getValue();
	}

	@Override
	public void cleanHand() {
		hand.clear();
	}

	@Override
	public List<Card> getHand() {
		return hand;
	}
	
	/**
	 * Muestra por consola las cartas de la mano del jugador con su índice,
	 * para que pueda elegir cuál descartar.
	 */
	public void showHand() {
		for (int i = 0;i<hand.size();i++) {
			System.out.printf("[%d] %s\n",i,hand.get(i));
		}
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public int getScore() {
		return score;
	}

	@Override
	public void setScore(int score) {
		this.score = score;
	}

	@Override
	public String toString() {
		return String.format("Nombre: %s\n Mano: %s",name,hand);
	}
	
	
	

}
