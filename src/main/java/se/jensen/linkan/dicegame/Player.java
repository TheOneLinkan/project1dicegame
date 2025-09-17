package se.jensen.linkan.dicegame;

public class Player {
    private String firstName;
    private String lastName;
    private int score;

    public Player() {
        this.score = 0;
    }

    public void setFirstName(String firstName) {
        if (firstName == null || firstName.trim().isEmpty()) {
            throw new IllegalArgumentException("Förnamn får inte vara tomt.");
        }
        this.firstName = firstName.trim();
    }

    public void setLastName(String lastName) {
        if (lastName == null || lastName.trim().isEmpty()) {
            throw new IllegalArgumentException("Efternamn får inte vara tomt.");
        }
        this.lastName = lastName.trim();
    }

    public String getFirstName() {
        return firstName;
    }

    public String getFullName() {
        return firstName + " " + lastName;
    }

    public int getScore() {
        return score;
    }

    public void addToScore(int points) {
        score += points;
    }

    public void resetScore() {
        score = 0;
    }
}

