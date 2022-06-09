// OOP med Java, -vt2022
// Uppgift 4
// Namn: Malla Grönqvist
// E-mail: mallagronqvist@gmail.com
//*****************************************************************************

package NapoleonsTomb;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;

public class GameBoard extends JPanel {
	
    private final LinkedHashMap<String, Pile> piles = new LinkedHashMap<String, Pile>();
    private int roundCount;
    private Card selectedCard;
    MouseListener mouseListener;
    Deck fixedGameDeck;
    private static final int space = Card.width / 2;
    private static final int discardPileXY[] = {(8 * Card.width - space), (space)};
    private static final int deckXY[] = {9 * Card.width, space};
    private static final int parkingXY[] = {9 * Card.width, 3 * Card.height + 2 * space};
    private static final int sixPileXY[] = {3 * Card.width + space, 2* Card.height + space};
    private static final int sevenPileLeftTopXY[] = {2 * Card.width - space/4, Card.height - space/4};
    private static final int sevenPileRightTopXY[] = {5 * Card.width + space/4, Card.height -space/4};
    private static final int sevenPileLeftBottomXY[] = {2 * Card.width - space/4, 3 * Card.height + 2 * space + space/4};
    private static final int sevenPileRightBottomXY[] ={5 * Card.width + space/4, 3 * Card.height + 2 * space + space/4};
    private static final int tableauTopXY[] = {3 * Card.width + space, Card.height};
    private static final int tableauLeftXY[] = {2 * Card.width, 2* Card.height + space};
    private static final int tableauRightXY[] = {5 * Card.width, 2* Card.height + space};
    private static final int tableauBottomXY[] = {3 * Card.width + space, 3 * Card.height + 2 * space};
    
    public GameBoard() {
        setBackground(new Color(0,0,100));
        mouseListener = new MouseListener(this);
        addMouseListener(mouseListener);
        addMouseMotionListener(mouseListener);
        
        preparePiles();
        dealCards();
    }

    public void paintComponent(Graphics g) {     
        super.paintComponent(g);

        for (Pile pile : piles.values()) {
            pile.draw(g, this);
        }

        if (selectedCard != null) {
            selectedCard.draw(g, this);
        }
    }
    
    public void preparePiles() {
        piles.put("deck", new Deck(deckXY[0], deckXY[1], "deck"));
        piles.put("discardPile", new DiscardPile(discardPileXY[0], discardPileXY[1], "discardPile"));
        piles.put("parking", new Parking(parkingXY[0], parkingXY[1], "parking"));
        piles.put("sixPile", new SixPile(sixPileXY[0], sixPileXY[1], "sixPile"));
        piles.put("sevenPileLeftTop", new SevenPile(sevenPileLeftTopXY[0], sevenPileLeftTopXY[1], "sevenPileLeftTop"));
        piles.put("sevenPileRightTop", new SevenPile(sevenPileRightTopXY[0], sevenPileRightTopXY[1], "sevenPileRightTop"));
        piles.put("sevenPileLeftBottom", new SevenPile(sevenPileLeftBottomXY[0], sevenPileLeftBottomXY[1], "sevenPileLeftBottom"));
        piles.put("sevenPileRightBottom", new SevenPile(sevenPileRightBottomXY[0], sevenPileRightBottomXY[1], "sevenPileRightBottom"));        
        piles.put("tableauTop", new Tableau(tableauTopXY[0], tableauTopXY[1], "tableauTop"));
        piles.put("tableauLeft", new Tableau(tableauLeftXY[0], tableauLeftXY[1], "tableauLeft"));
        piles.put("tableauRight", new Tableau(tableauRightXY[0], tableauRightXY[1], "tableauRight"));
        piles.put("tableauBottom", new Tableau(tableauBottomXY[0], tableauBottomXY[1], "tableauBottom"));   
    }
    
    public void dealCards() {   
        for (Pile pile : piles.values()) {
            pile.clearCards();
        }
        
        ((Deck) piles.get("deck")).prepareCards();
        fixedGameDeck = new Deck((Deck)piles.get("deck"));

        for (Pile pile : piles.values()) {
        	if (pile.getName().contains("tableau")) {
        		Card movedCard = piles.get("deck").getTopCard();
        		piles.get("deck").removeTopCard();
        		movedCard.setPile(pile.getName());
        		piles.get(pile.getName()).insertCard(movedCard, true);
        	}
        }     
        roundCount = 1; 
        repaint();
    }
    
    public void newFixedGame() {
    	for (Pile pile : piles.values()) {
    		pile.clearCards();  
    	}
    	
    	piles.replace("deck", new Deck(fixedGameDeck));
    	for (Pile pile : piles.values()) {
        	if (pile.getName().contains("tableau")) {
        		Card movedCard = piles.get("deck").getTopCard();
        		piles.get("deck").removeTopCard();
        		movedCard.setPile(pile.getName());
        		piles.get(pile.getName()).insertCard(movedCard, true);
        	}
        }     
    	roundCount = 1;
    	repaint();
    }

    public void deckEvent(int mouseX, int mouseY) {
        if (!piles.get("discardPile").isEmpty() && !tableauCorrectlyFilled()) {
            return;
        }

        if (!piles.get("deck").isEmpty() && piles.get("deck").getTopCard().isInCard(mouseX, mouseY)) {
            Card movedCard = piles.get("deck").getTopCard();
            movedCard.setPile("discardPile");
            piles.get("deck").removeTopCard();
            piles.get("discardPile").insertCard(movedCard, true);
            repaint();
        } else if (piles.get("deck").isEmpty()) {
        	if (roundCount == 1) {
               roundCount =- 1;
               while (!piles.get("discardPile").isEmpty()) {
                    Card movedCard = piles.get("discardPile").getTopCard();
                    movedCard.setPile("deck");
                    piles.get("discardPile").removeTopCard();                 
                    piles.get("deck").insertCard(movedCard, false);
                }
            }             
        }
    }
    
    public boolean tableauCorrectlyFilled() {
        int emptyTableaus = 0;   
        for (Pile pile : piles.values()) {
        	if (pile.getName().contains("tableau") && pile.isEmpty()){
					emptyTableaus += 1;
				}
        }
        return emptyTableaus <= 1;
    }

    public Card getCard(int mouseX, int mouseY) {
        for (Pile pile : piles.values()) {
            if (!pile.isEmpty()) {
                if (pile.getTopCard().isInCard(mouseX, mouseY)) {
                    return pile.getTopCard();
                }
            }
        }
        return null;
    }
    
    public Pile getPile(String name) {
        return piles.get(name);
    }

    public void moveCard(Card card, int mouseX, int mouseY) {
            selectedCard = card;
            card.move(mouseX, mouseY);
            repaint();
    }

    public void placeCard() {
        if (selectedCard == null) {
            return;
        }

        for (Pile pile : piles.values()) {
            if (pile.canPlaceCard(selectedCard)) {
                selectedCard = null;
                repaint();
                return;
            }
        }       
        piles.get(selectedCard.getPile()).insertCard(selectedCard, selectedCard.getFaceUp());
        selectedCard = null;
        repaint();
    }
}
