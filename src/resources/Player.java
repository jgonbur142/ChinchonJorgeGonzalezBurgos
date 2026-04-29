package resources;

import java.util.ArrayList;
import java.util.List;

/**
 * Representa a un jugador con el nombre, cartas en mano y puntuación que tendrá a lo largo de una partida
 */
public class Player implements IPlayer{

	private String name;
	private List<Card> hand;
	private List<Card> handCopy;
	private List<Card> combinationHand;
	private int score;
	
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

	public void calculateScore() {
		int points=0;
		
		for (Card c : hand) {
			points += c.getNumber().getValue();
		}
		
		score += points;
	}
	/**
	 * TODO:
	 * Cómo calcular combinaciones ¿?
	 * 1º Ordenar mano
	 * 2º Buscar cartas iguales -> Si hay 3 o más iguales, combinación válida
	 * 3º Buscar cartas consecutivas (mismo palo)-> Si hay 3 o más consecutivas, combinación válida -> Si hay 7 consecutivas, chinchón = win
	 * 
	 * cuando encuentre las combinaciones:
	 * 1º Las cartas que formen la combinación se mueven a una mano temporal y se quitan de la mano principal
	 * 2º Se suman los valores de las cartas que queden en la mano a la puntuación.
	 * IMPORTENTE -> En la mano después de combinar solo pueden quedar o 1 o 2 cartas, si no, no es un cierre válido.
	 */
	public void calculateCardCombinations() {

		//primero ordena las cartas
		sortCardsInHand();
		
		//busca si tiene escalera en la mano
		straightCombination();
		
		//busca si tiene iguales en la mano
		sameCombination();
		
		
	}
	
	/**
	 * Busca cartas consecutivas del mismo palo.
	 * Mueve las cartas consecutivas a la mano de combinación.
	 * Elimina las cartas de la mano original
	 */
	public void straightCombination() {
		
		for (int i=0;i<hand.size()-1;i++) {			
			//Si la actual y la siguiente son del mismo palo y son consecutivos, false
			if (hand.get(i).getSuit() == hand.get(i+1).getSuit() && hand.get(i).getNumber().ordinal()+1 == hand.get(i+1).getNumber().ordinal()) {
				combinationHand.add(hand.get(i));
			}
		}
		
		for (int i=0;i<hand.size()-1;i++) {
			handCopy.add(hand.get(i));
			//si la carta combinada está en la mano, se borra (utilizo handcopy para que no salte excepción por modificar y recorrer la lista a la vez)
			if (combinationHand.contains(handCopy.get(i))) {
				handCopy.remove(i);				
			}
		}
	}

	/**
	 * Busca cartas iguales del mismo numero.
	 * Mueve las cartas iguales a la mano de combinación.
	 * Elimina las cartas de la mano original
	 */
	public void sameCombination() {
		for (int i=0;i<hand.size()-1;i++) {
			//si la actual y la siguiente son del mismo número
			if (hand.get(i).getNumber().getValue()==hand.get(i+1).getNumber().getValue()) {//¿esto no va a dar IndexOutOfBounds?
				combinationHand.add(hand.get(i));
			}
		}
		
		for (int i=0;i<hand.size()-1;i++) {
			handCopy.add(hand.get(i));
			//si la carta combinada está en la mano, se borra
			if (combinationHand.contains(handCopy.get(i))) {
				handCopy.remove(i);
			}
		}
	}
	
	public void sortCardsInHand() {
		hand.sort((c1,c2) -> Integer.compare(c1.getNumber().getValue(), c2.getNumber().getValue()));

	}

	@Override
	public void cleanHand() {
		hand.clear();
	}

	@Override
	public List<Card> getHand() {
		return hand;
	}
	
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
