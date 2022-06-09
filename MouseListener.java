// OOP med Java, -vt2022
// Uppgift 4
// Namn: Malla Grönqvist
// E-mail: mallagronqvist@gmail.com
//*****************************************************************************

package NapoleonsTomb;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;


class MouseListener extends MouseAdapter implements MouseMotionListener {

    private final GameBoard gameBoard;
    private Card selectedCard;
    private int mx, my;
    private int dx, dy;

    public MouseListener (GameBoard gameBoard) {
        this.gameBoard = gameBoard;
    }

    public void mousePressed (MouseEvent e) {
        mx = e.getX();
        my = e.getY();

        selectedCard = gameBoard.getCard(mx, my);

        if (selectedCard != null) {
        	if (selectedCard.getPile().equals("deck") && !gameBoard.tableauCorrectlyFilled()) {
        		selectedCard = null;
        		return;
        	} else {
            dx = selectedCard.getX() - mx;
            dy = selectedCard.getY() - my;
            gameBoard.getPile(selectedCard.getPile()).removeTopCard();
            gameBoard.moveCard(selectedCard, mx + dx, my + dy);
        	}
        }
    }

    public void mouseDragged (MouseEvent e) {
        mx = e.getX();
        my = e.getY();
        if (selectedCard != null) {
            gameBoard.moveCard(selectedCard, mx + dx, my + dy);
        }
    }

    public void mouseClicked(MouseEvent e) {
        int mx = e.getX();
        int my = e.getY();
        gameBoard.deckEvent(mx, my);
    }

    public void mouseReleased(MouseEvent e) {
        gameBoard.placeCard();
    }
}