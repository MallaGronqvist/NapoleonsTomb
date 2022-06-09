// OOP med Java, -vt2022
// Uppgift 4
// Namn: Malla Grönqvist
// E-mail: mallagronqvist@gmail.com
//*****************************************************************************

package NapoleonsTomb;

public class SevenPile extends Pile {

    protected SevenPile(int x, int y, String name) {
        super(x, y, name);
    }

    @Override
    public boolean canPlaceCard(Card card) {
        if (isInPile(card)) {

            if (isEmpty() && card.getValue() == 7) {
                card.setPile(name);
                insertCard(card, true);
                return true;
                
            } else if (!isEmpty() && card.getValue() == getTopCard().getValue() + 1) {
                card.setPile(name);
                insertCard(card, true);
                return true;
                
            } else if (!isEmpty() && getTopCard().getValue() == 13) {
                return false;
            }
        }
        return false;   
    }
}
