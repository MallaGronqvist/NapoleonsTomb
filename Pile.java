// OOP med Java, -vt2022
// Uppgift 4
// Namn: Malla Grönqvist
// E-mail: mallagronqvist@gmail.com
//*****************************************************************************

package NapoleonsTomb;

import java.awt.*;
import java.util.ArrayList;

import static java.lang.Math.abs;

abstract class Pile {

    protected int x, y;
    protected final String name;
    protected ArrayList<Card> cards;

    protected Pile(int x, int y, String name) {
        this.x = x;
        this.y = y;
        this.name = name;
        cards = new ArrayList<Card>();
    }

    protected void draw(Graphics g, java.awt.image.ImageObserver observer) {
        if (!isEmpty()) {
            getTopCard().draw(g, observer);
        } else {
        	g.setColor(new Color(0, 0, 150));
        	g.fillRect(x, y, Card.width, Card.height);
        }
    }

    public void clearCards() {
        cards.clear();
    }

    public Card getTopCard() {
        return cards.get(cards.size() - 1);
    }

    public void removeTopCard() {
        cards.remove(cards.size() - 1);
    }

    public boolean isEmpty() {
        return cards.isEmpty();
    }

    public void insertCard(Card card, boolean faceUp) {
        card.setX(x);
        card.setY(y);
        card.setFaceUp(faceUp);
        cards.add(card);
    }

    protected boolean isInPile(Card card) {
        int widthScope = Card.width / 2;
        int heightScope = Card.height / 2;
        int dx = abs(card.getX() - x);
        int dy = abs(card.getY() - y);
        return dx < widthScope && dy < heightScope;
    }

    public String getName() {
        return name;
    }

    abstract public boolean canPlaceCard(Card card);
}
