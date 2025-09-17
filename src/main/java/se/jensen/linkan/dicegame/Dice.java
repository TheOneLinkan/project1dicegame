package se.jensen.linkan.dicegame;

import java.util.Random;

public class Dice {
    private Random random = new Random();

    public int roll() {
        return random.nextInt(6) + 1; // 1–6
    }
}
