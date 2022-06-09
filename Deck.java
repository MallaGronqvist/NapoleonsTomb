// OOP med Java, -vt2022
// Uppgift 4
// Namn: Malla Grönqvist
// E-mail: mallagronqvist@gmail.com
//*****************************************************************************

package NapoleonsTomb;

import java.util.ArrayList;
import java.util.Collections;

public class Deck extends Pile {

    public Deck(int x, int y, String name) {
        super(x, y, name);
    }
    
    public Deck(Deck copiedDeck) {
    	super(copiedDeck.x, copiedDeck.y, copiedDeck.name);
    	this.cards = new ArrayList<Card>();
    	for (Card card : copiedDeck.cards) {
    		this.cards.add(new Card(card));
    	}
    }

    @Override
    public boolean canPlaceCard(Card card) {
        return false;
    }

    public void prepareCards() {
    	
    	int suits = 4;
    	int values = 13;

        for (int suit = 0; suit < suits; suit++) {
            for (int value = 0; value < values; value++) {
                Card card = new Card(x, y, suit, value, "deck");
                cards.add(card);
            }
        }
        Collections.shuffle(cards);  
    }
}