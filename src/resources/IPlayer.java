package resources;

import java.util.List;

public interface IPlayer {
	
	void drawCard(Card card);
	
	void discardCard(Card card);
	
	void calculateScore();
	
	void cleanHand();
	
	List<Card> getHand();
	
	String getName();
	
}
