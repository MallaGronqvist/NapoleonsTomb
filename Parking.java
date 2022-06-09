// OOP med Java, -vt2022
// Uppgift 4
// Namn: Malla Grönqvist
// E-mail: mallagronqvist@gmail.com
//*****************************************************************************

package NapoleonsTomb;

public class Parking extends Pile {
	
    protected Parking(int x, int y,String name) {
        super(x, y, name);
    }

    @Override
    public boolean canPlaceCard(Card card) {
        if (isInPile(card) && card.getValue() == 6) {
            card.setPile(name);
            insertCard(card, true);
            return true;
        }
        return false;
    }
}
