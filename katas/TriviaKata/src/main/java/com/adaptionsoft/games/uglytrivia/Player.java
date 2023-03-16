package com.adaptionsoft.games.uglytrivia;

public class Player {
    public static final int POINTS_TO_WIN = 6;
    public static final int BOARD_SIZE = 12;
    private final String name;
    private int points = 0;

    private int position = 0;

    private boolean inPenaltyBox = false;

    public Player(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }


    public int getPoints() {
        return points;
    }

    public void score() {
        this.points++;
    }

    public boolean isWinner() {
        return this.points == POINTS_TO_WIN;
    }

    public int getPosition() {
        return position;
    }

    public void move(int roll) {
        position = (position+roll) % BOARD_SIZE;
    }

    public void sendToJail() {
        inPenaltyBox = true;
    }

    public void free() {
        inPenaltyBox = false;
    }

    public boolean isInPenaltyBox() {
        return inPenaltyBox;
    }
}
