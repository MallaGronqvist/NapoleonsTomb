// OOP med Java, -vt2022
// Uppgift 4
// Namn: Malla Grönqvist
// E-mail: mallagronqvist@gmail.com
//*****************************************************************************

package NapoleonsTomb;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class Card {

    private int x;
    private int y;
    private final int value;
    private String name;
    private String pile;
    public static final int width = 71;
    public static final int height = 96;
    private final Image frontImage, backImage;
    private boolean faceUp = false;

    public Card(int x, int y, int suit, int value, String pile) {
    	
        this.x = x;
        this.y = y;
        this.value = value + 1;
        this.pile = pile;
        
        String[] suits = {"c", "d", "h", "s"};
        String[] values = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "j", "q", "k"};
 
        name = suits[suit] + values[value];
        
        frontImage = readImage("cards/" + name + ".gif");
		backImage = readImage("cards/" + "b2fv.gif");		
    }

    public Card(Card card) {
		this.x = card.x;
		this.y = card.y;
		this.value = card.value;
		this.name = card.name;
		this.pile = card.pile;
		
		frontImage = readImage("cards/" + name + ".gif");
		backImage = readImage("cards/" + "b2fv.gif");		
	}

	private Image readImage(String imageFile) {
    	Image image;
    	try {
    		image = ImageIO.read(getClass().getResource(imageFile));
        } catch (IOException e) {
        	throw new RuntimeException(e);
        }   
       return image;
    }

    public void draw(Graphics g, java.awt.image.ImageObserver observer) {
    	
        if (faceUp) {
            g.drawImage(frontImage, x, y, observer);
        } else {
            g.drawImage(backImage, x, y, observer);
        }
    }

    public int getValue() {
        return value;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setFaceUp(boolean faceUp) {
        this.faceUp = faceUp;
    }
    
    public boolean getFaceUp() {
    	return faceUp;
    }

    public String getPile() {
        return pile;
    }

    public void setPile(String pile) {
        this.pile = pile;
    }
    
    public boolean isInCard(int mouseX, int mouseY) {
        return mouseX > x && mouseX <= x + width && mouseY > y && mouseY <= y + height;
    }

    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }
}