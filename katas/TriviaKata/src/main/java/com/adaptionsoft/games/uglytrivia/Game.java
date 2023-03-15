package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Game {
    private final int max = 6;
    private List<String> players = new ArrayList<>();
    private int[] places = new int[max];
    private int[] purses = new int[max];
    private boolean[] inPenaltyBox = new boolean[max];

    private LinkedList<String> q1 = new LinkedList<>();
    private LinkedList<String> q2 = new LinkedList<>();
    private LinkedList<String> q3 = new LinkedList<>();
    private LinkedList<String> q4 = new LinkedList<>();

    private int currentPlayer = 0;
    private boolean isGettingOutOfPenaltyBox;

    public Game() {
        for (int i = 0; i < 50; i++) {
            q1.addLast("Pop Question " + i);
            q2.addLast(("Science Question " + i));
            q3.addLast(("Sports Question " + i));
            q4.addLast("Rock Question " + i);
        }
    }

    public boolean add(String playerName) {
        players.add(playerName);
        places[howManyPlayers()] = 0;
        purses[howManyPlayers()] = 0;
        inPenaltyBox[howManyPlayers()] = false;

        log(playerName + " was added");
        log("They are player number " + players.size());
        return true;
    }

    public int howManyPlayers() {
        return players.size();
    }

    public void roll(int roll) {
        log(players.get(currentPlayer) + " is the current player");
        log("They have rolled a " + roll);

        if (inPenaltyBox[currentPlayer]) {
            if (roll % 2 != 0) {
                //User is getting out of penalty box
                isGettingOutOfPenaltyBox = true;

                //Write that user is getting out
                log(players.get(currentPlayer) + " is getting out of the penalty box");
                // add roll to place
                places[currentPlayer] = places[currentPlayer] + roll;
                if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;

                log(players.get(currentPlayer)
                        + "'s new location is "
                        + places[currentPlayer]);
                log("The category is " + currentCategory());
                askQuestion();
            } else {
                log(players.get(currentPlayer) + " is not getting out of the penalty box");
                isGettingOutOfPenaltyBox = false;
            }
        } else {
            places[currentPlayer] = places[currentPlayer] + roll;
            if (places[currentPlayer] > 11) places[currentPlayer] = places[currentPlayer] - 12;

            log(players.get(currentPlayer)
                    + "'s new location is "
                    + places[currentPlayer]);
            log("The category is " + currentCategory());
            askQuestion();
        }
    }

    private void askQuestion() {
        LinkedList<String> questionToAsk = switch (currentCategory()) {
            case "Pop" -> q1;
            case "Science" -> q2;
            case "Sports" -> q3;
            case "Rock" -> q4;
            default -> throw new IllegalStateException();
        };
        log(questionToAsk.removeFirst());
    }

    private String currentCategory() {
        return switch (places[currentPlayer] % 4) {
            case 0 -> "Pop";
            case 1 -> "Science";
            case 2 -> "Sports";
            case 3 -> "Rock";
            default -> throw new IllegalStateException();
        };
    }

    /**
     * To call when the answer is right
     *
     * @return
     */
    public boolean wasCorrectlyAnswered() {
        if (inPenaltyBox[currentPlayer]) {
            if (isGettingOutOfPenaltyBox) {
                return getWinner();
            } else {
                currentPlayer++;
                if (currentPlayer == players.size()) currentPlayer = 0;
                return true;
            }
        } else {
            return getWinner();
        }
    }

    private boolean getWinner() {
        log("Answer was correct!!!!");
        purses[currentPlayer]++;
        log(players.get(currentPlayer)
                + " now has "
                + purses[currentPlayer]
                + " Gold Coins.");

        boolean winner = !(purses[currentPlayer] == 6);
        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;

        return winner;
    }

    /**
     * To call when the answer is wrong
     *
     * @return
     */
    public boolean wrongAnswer() {
        log("Question was incorrectly answered");
        log(players.get(currentPlayer) + " was sent to the penalty box");
        inPenaltyBox[currentPlayer] = true;

        currentPlayer++;
        if (currentPlayer == players.size()) currentPlayer = 0;
        return true;
    }

    private void log(String toLog) {
        System.out.println(toLog);
    }
    @Override
    public String toString() {
        return "Game{" +
                "MAX_FIVE=" + max +
                ", players=" + players +
                ", places=" + Arrays.toString(places) +
                ", purses=" + Arrays.toString(purses) +
                ", inPenaltyBox=" + Arrays.toString(inPenaltyBox) +
                ", _q1=" + q1 +
                ", q2=" + q2 +
                ", currentPlayer=" + currentPlayer +
                ", isGettingOutOfPenaltyBox=" + isGettingOutOfPenaltyBox +
                ", q3=" + q3 +
                ", Q5=" + q4 +
                '}';
    }
}
