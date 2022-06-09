// OOP med Java, -vt2022
// Uppgift 4
// Namn: Malla Gr�nqvist
// E-mail: mallagronqvist@gmail.com
//*****************************************************************************

package NapoleonsTomb;

public class DiscardPile extends Pile {

    protected DiscardPile(int x, int y, String name) {
        super(x, y, name);
    }

    @Override
    public boolean canPlaceCard(Card card) {
    	if(isInPile(card)) {
    		 card.setPile(name);
             insertCard(card, true);
    		return true;
    	} else {
    		return false;
    	}
    }
}
