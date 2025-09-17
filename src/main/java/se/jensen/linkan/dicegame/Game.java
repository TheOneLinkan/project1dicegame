package se.jensen.linkan.dicegame;

import javax.swing.JOptionPane;
import javax.swing.ImageIcon;

public class Game {
    private Player player1;
    private Player player2;
    private Dice dice;
    private String gameName = "Tärningsspelet";
    private ImageIcon gameIcon;


    public Game() {
        dice = new Dice();
        gameIcon = new ImageIcon(getClass().getResource("/images/diceicon.png"));
        setupPlayers();
    }

    private void setupPlayers() {
        player1 = readPlayer(1);
        player2 = readPlayer(2);
    }

    private Player readPlayer(int number) {
        Player p = new Player();
        while (true) {
            try {
                String firstName = (String) JOptionPane.showInputDialog(
                null, "Spelare " + number + ", förnamn:", gameName,
                JOptionPane.PLAIN_MESSAGE, gameIcon, null, "");
                if (firstName == null) System.exit(0);
                p.setFirstName(firstName);

                String lastName = (String) JOptionPane.showInputDialog(
                null, "Spelare " + number + ", efternamn:", gameName,
                JOptionPane.PLAIN_MESSAGE, gameIcon, null, "");
                if (lastName == null) System.exit(0);
                p.setLastName(lastName);

                return p;
            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null,
                        "Fel: " + e.getMessage() + " Försök igen.");
            }
        }
    }

    public void play() {
        while (true) {
            String[] options = {"play", "quit"};
            int choice = JOptionPane.showOptionDialog(
                    null,
                    "Välj ett alternativ:",
                    gameName,
                    JOptionPane.DEFAULT_OPTION,
                    JOptionPane.PLAIN_MESSAGE,
                    gameIcon,
                    options,
                    options[0]
            );

            if (choice == 0) { // play
                player1.resetScore();
                player2.resetScore();
                playMatch();
            } else if (choice == 1 || choice == JOptionPane.CLOSED_OPTION) { // Avsluta
                JOptionPane.showMessageDialog(null, "Programmet avslutas.");
                return;
            }
        }
    }

    private void playMatch() {
        StringBuilder sb = new StringBuilder();
        sb.append("Matchen är igång!\n\n");

        String[] diceOrder = {"första", "andra"};

        for (String order : diceOrder) {
            int roll1 = dice.roll();
            int roll2 = dice.roll();

            sb.append(player1.getFirstName()).append(" slår ")
                    .append(order).append(" tärningen: ").append(roll1).append("\n");
            sb.append(player2.getFirstName()).append(" slår ")
                    .append(order).append(" tärningen: ").append(roll2).append("\n\n");

            player1.addToScore(roll1);
            player2.addToScore(roll2);
        }

        sb.append("Poäng:\n");
        sb.append(player1.getFirstName()).append(" - ").append(player1.getScore()).append("\n");
        sb.append(player2.getFirstName()).append(" - ").append(player2.getScore()).append("\n\n");

        if (player1.getScore() > player2.getScore()) {
            sb.append("Vinnaren är: ").append(player1.getFullName()).append("!");
        } else if (player2.getScore() > player1.getScore()) {
            sb.append("Vinnaren är: ").append(player2.getFullName()).append("!");
        } else {
            sb.append("Matchen blev oavgjord!");
        }

        JOptionPane.showMessageDialog(null, sb.toString(), gameName,
        JOptionPane.PLAIN_MESSAGE, gameIcon);
    }
}

