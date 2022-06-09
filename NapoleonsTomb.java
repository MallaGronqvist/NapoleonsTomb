// OOP med Java, -vt2022
// Uppgift 4
// Namn: Malla Grönqvist
// E-mail: mallagronqvist@gmail.com
//*****************************************************************************

package NapoleonsTomb;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class NapoleonsTomb extends JFrame implements ActionListener {

    private final JButton newGameButton;
    private final JButton fixedGameButton;
    private final JButton exitButton;
    private final GameBoard gameBoard;
    private static final int windowWidth = 800;
    private static final int windowHeight = 600;
    private static final int distance = 150;

    public NapoleonsTomb() {
        super("Welcome to Napoleon's Tomb");

        newGameButton = new JButton("New Game");
        fixedGameButton = new JButton("New Game with Fixed Order");
        exitButton = new JButton("Exit Game");

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(newGameButton);
        buttonPanel.add(fixedGameButton);
        buttonPanel.add(exitButton);
        buttonPanel.setBackground(new Color(0,0,120));
        add(buttonPanel, BorderLayout.NORTH);

        gameBoard = new GameBoard();
        add(gameBoard, BorderLayout.CENTER);

        setBounds(distance, distance, windowWidth, windowHeight);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);

        newGameButton.addActionListener(this);
        fixedGameButton.addActionListener(this);
        exitButton.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == newGameButton) {
            gameBoard.dealCards();
        } else if (e.getSource() == fixedGameButton) {
            gameBoard.newFixedGame();
        } else if (e.getSource() == exitButton) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        NapoleonsTomb napoleonsTomb = new NapoleonsTomb();
    }
}

