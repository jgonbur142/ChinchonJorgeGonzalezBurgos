package resourceTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import resources.Card;
import resources.Number;
import resources.Player;
import resources.Suit;

/**
 * Pruebas unitarias de los métodos de lógica de juego de la clase Player.
 * Se comprueban: isChinchon(), canClose(), calculateCloseScore() y getLeftoverScore().
 */
class PlayerTest {
	
	/**
	 * 7 cartas del mismo palo con ordinales consecutivos debe devolver true.
	 */
	@Test
	void isChinchon_sevenConsecutiveSameSuit_returnTrue() {
		Player player = new Player("Test");
        player.draw(new Card(Number.UNO,    Suit.COINS));
        player.draw(new Card(Number.DOS,    Suit.COINS));
        player.draw(new Card(Number.TRES,   Suit.COINS));
        player.draw(new Card(Number.CUATRO, Suit.COINS));
        player.draw(new Card(Number.CINCO,  Suit.COINS));
        player.draw(new Card(Number.SEIS,   Suit.COINS));
        player.draw(new Card(Number.SIETE,  Suit.COINS));

        assertTrue(player.isChinchon());
	}
	
	/**
	 * 7 cartas consecutivas pero con un palo diferente debe devolver false.
	 */
	@Test
    void isChinchon_sevenConsecutiveDifferentSuit_returnFalse() {
        Player player = new Player("Test");
        player.draw(new Card(Number.UNO,    Suit.COINS));
        player.draw(new Card(Number.DOS,    Suit.SWORD)); 
        player.draw(new Card(Number.TRES,   Suit.COINS));
        player.draw(new Card(Number.CUATRO, Suit.COINS));
        player.draw(new Card(Number.CINCO,  Suit.COINS));
        player.draw(new Card(Number.SEIS,   Suit.COINS));
        player.draw(new Card(Number.SIETE,  Suit.COINS));

        assertFalse(player.isChinchon());
    }
	
	/**
	 * 7 cartas del mismo palo pero con un salto de ordinal (SEIS, SOTA) debe devolver false.
	 */
	@Test
    void isChinchon_sevenNotConsecutiveSameSuit_returnFalse() {
        Player player = new Player("Test");
        player.draw(new Card(Number.UNO,    Suit.COINS));
        player.draw(new Card(Number.DOS,    Suit.COINS));
        player.draw(new Card(Number.TRES,   Suit.COINS));
        player.draw(new Card(Number.CUATRO, Suit.COINS));
        player.draw(new Card(Number.CINCO,  Suit.COINS));
        player.draw(new Card(Number.SEIS,   Suit.COINS));
        player.draw(new Card(Number.SOTA,   Suit.COINS));

        assertFalse(player.isChinchon());
    }
	
	/**
	 * Menos de 7 cartas en mano debe devolver false.
	 */
	@Test
    void isChinchon_lessThanSevenCards_returnFalse() {
        Player player = new Player("Test");
        player.draw(new Card(Number.UNO,  Suit.COINS));
        player.draw(new Card(Number.DOS,  Suit.COINS));
        player.draw(new Card(Number.TRES, Suit.COINS));

        assertFalse(player.isChinchon());
    }
	
	
	/**
	 * Mano de chinchón debe devolver true.
	 */
	@Test
    void canClose_chinchon_returnTrue() {
        Player player = new Player("Test");
        player.draw(new Card(Number.UNO,    Suit.COINS));
        player.draw(new Card(Number.DOS,    Suit.COINS));
        player.draw(new Card(Number.TRES,   Suit.COINS));
        player.draw(new Card(Number.CUATRO, Suit.COINS));
        player.draw(new Card(Number.CINCO,  Suit.COINS));
        player.draw(new Card(Number.SEIS,   Suit.COINS));
        player.draw(new Card(Number.SIETE,  Suit.COINS));

        assertTrue(player.canClose());
    }
	
	/**
	 * 6 cartas combinadas y 1 sobrante de valor 2 (<= 5) debe devolver true.
	 */
	@Test
    void canClose_sixCombinatedLeftLessOrEqualThanFive_returnTrue() {
        Player player = new Player("Test");
        player.draw(new Card(Number.UNO,    Suit.COINS));
        player.draw(new Card(Number.DOS,    Suit.COINS));
        player.draw(new Card(Number.TRES,   Suit.COINS));
        player.draw(new Card(Number.CUATRO, Suit.COINS));
        player.draw(new Card(Number.CUATRO, Suit.SWORD));
        player.draw(new Card(Number.CUATRO, Suit.CUP));
        player.draw(new Card(Number.DOS,    Suit.SWORD)); 

        assertTrue(player.canClose());
    }
	
	/**
	 * 6 cartas combinadas y 1 sobrante de valor 7 (> 5) debe devolver false.
	 */
	@Test
    void canClose_sixCombinatedLeftGreaterThanFive_returnFalse() {
        Player player = new Player("Test");
        player.draw(new Card(Number.UNO,    Suit.COINS));
        player.draw(new Card(Number.DOS,    Suit.COINS));
        player.draw(new Card(Number.TRES,   Suit.COINS));
        player.draw(new Card(Number.CUATRO, Suit.COINS));
        player.draw(new Card(Number.CUATRO, Suit.SWORD));
        player.draw(new Card(Number.CUATRO, Suit.CUP));
        player.draw(new Card(Number.SIETE,  Suit.SWORD)); 

        assertFalse(player.canClose());
    }
	
	/**
	 * 5 cartas combinadas y 2 sobrantes debe devolver false (más de 1 sobrante).
	 */
	@Test
    void canClose_twoLeft_returnFalse() {
        Player player = new Player("Test");
        player.draw(new Card(Number.UNO,     Suit.COINS));
        player.draw(new Card(Number.DOS,     Suit.COINS));
        player.draw(new Card(Number.TRES,    Suit.COINS));
        player.draw(new Card(Number.CUATRO,  Suit.COINS));
        player.draw(new Card(Number.CINCO,   Suit.COINS));
        player.draw(new Card(Number.REY,     Suit.SWORD));   
        player.draw(new Card(Number.CABALLO, Suit.CUP));     

        assertFalse(player.canClose());
    }
	
	
	/**
	 * Chinchón debe devolver Integer.MIN_VALUE (señal de chinchón en Match).
	 */
	@Test
    void calculateCloseScore_chinchon_returnMinValue() {
        Player player = new Player("Test");
        player.draw(new Card(Number.UNO,    Suit.COINS));
        player.draw(new Card(Number.DOS,    Suit.COINS));
        player.draw(new Card(Number.TRES,   Suit.COINS));
        player.draw(new Card(Number.CUATRO, Suit.COINS));
        player.draw(new Card(Number.CINCO,  Suit.COINS));
        player.draw(new Card(Number.SEIS,   Suit.COINS));
        player.draw(new Card(Number.SIETE,  Suit.COINS));

        assertEquals(Integer.MIN_VALUE, player.calculateCloseScore());
    }
	
	/**
	 * Las 7 cartas forman combinaciones (sin sobrante) debe devolver -10.
	 */
	@Test
    void calculateCloseScore_sevenCombinated_returnMinusTen() {
        Player player = new Player("Test");
        player.draw(new Card(Number.UNO,    Suit.COINS));
        player.draw(new Card(Number.DOS,    Suit.COINS));
        player.draw(new Card(Number.TRES,   Suit.COINS));
        player.draw(new Card(Number.CUATRO, Suit.COINS));
        player.draw(new Card(Number.CINCO,  Suit.COINS));
        player.draw(new Card(Number.CINCO,  Suit.SWORD));
        player.draw(new Card(Number.CINCO,  Suit.CUP));

        assertEquals(-10, player.calculateCloseScore());
    }
	
	/**
	 * 6 cartas combinadas y 1 sobrante de valor 2 debe devolver 2.
	 */
	@Test
    void calculateCloseScore_oneLeft_returnLeftValue() {
        Player player = new Player("Test");
        player.draw(new Card(Number.UNO,    Suit.COINS));
        player.draw(new Card(Number.DOS,    Suit.COINS));
        player.draw(new Card(Number.TRES,   Suit.COINS));
        player.draw(new Card(Number.CUATRO, Suit.COINS));
        player.draw(new Card(Number.CUATRO, Suit.SWORD));
        player.draw(new Card(Number.CUATRO, Suit.CUP));
        player.draw(new Card(Number.DOS,    Suit.SWORD)); 
        
        assertEquals(2, player.calculateCloseScore());
    }
	
	/**
	 * El jugador no tiene combinaciones suficientes para cerrar debe lanzar IllegalStateException.
	 */
	@Test
    void calculateCloseScore_cannotClose_throwllegalStateException() {
        Player player = new Player("Test");
        player.draw(new Card(Number.UNO,     Suit.COINS));
        player.draw(new Card(Number.REY,     Suit.SWORD));
        player.draw(new Card(Number.CABALLO, Suit.CUP));
        player.draw(new Card(Number.SIETE,   Suit.CLUBS));
        player.draw(new Card(Number.SOTA,    Suit.COINS));
        player.draw(new Card(Number.SEIS,    Suit.SWORD));
        player.draw(new Card(Number.DOS,     Suit.CUP));

        assertThrows(IllegalStateException.class, () -> player.calculateCloseScore());
    }
	
	
	/**
	 * Todas las cartas forman combinaciones debe devolver 0.
	 */
	 @Test
	    void getLeftoverScore_allCombinated_returnZero() {
	        Player player = new Player("Test");
	        player.draw(new Card(Number.UNO,    Suit.COINS));
	        player.draw(new Card(Number.DOS,    Suit.COINS));
	        player.draw(new Card(Number.TRES,   Suit.COINS));
	        player.draw(new Card(Number.CUATRO, Suit.COINS));
	        player.draw(new Card(Number.CINCO,  Suit.COINS));
	        player.draw(new Card(Number.CINCO,  Suit.SWORD));
	        player.draw(new Card(Number.CINCO,  Suit.CUP));

	        assertEquals(0, player.getLeftoverScore());
	    }
	 
	 /**
	  * Ninguna carta forma combinación debe devolver la suma de todas (1+12+5=18).
	  */
	 @Test
	    void getLeftoverScore_noCombinations_returnSumAll() {
	        Player player = new Player("Test");
	        player.draw(new Card(Number.UNO,   Suit.COINS)); 
	        player.draw(new Card(Number.REY,   Suit.SWORD)); 
	        player.draw(new Card(Number.CINCO, Suit.CUP));   

	        assertEquals(18, player.getLeftoverScore());
	    }
	 
	 /**
	  * Combinación parcial: escalera de 3 y 2 sobrantes (REY=12, CABALLO=11) debe devolver 23.
	  */
	 @Test
	    void getLeftoverScore_partialCombination_returnLeftSum() {
	        Player player = new Player("Test");
	        player.draw(new Card(Number.UNO,     Suit.COINS));
	        player.draw(new Card(Number.DOS,     Suit.COINS));
	        player.draw(new Card(Number.TRES,    Suit.COINS));
	        player.draw(new Card(Number.REY,     Suit.SWORD));   
	        player.draw(new Card(Number.CABALLO, Suit.CUP));     

	        assertEquals(23, player.getLeftoverScore());
	    }

}
