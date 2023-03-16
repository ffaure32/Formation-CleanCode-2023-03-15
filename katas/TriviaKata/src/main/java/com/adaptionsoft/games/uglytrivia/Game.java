package com.adaptionsoft.games.uglytrivia;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Game {
    private List<Player> players = new ArrayList<>();

    private LinkedList<String> q1 = new LinkedList<>();
    private LinkedList<String> q2 = new LinkedList<>();
    private LinkedList<String> q3 = new LinkedList<>();
    private LinkedList<String> q4 = new LinkedList<>();

    private int currentPlayerIndex = 0;

    public Game() {
        for (int i = 0; i < 50; i++) {
            q1.addLast("Pop Question " + i);
            q2.addLast(("Science Question " + i));
            q3.addLast(("Sports Question " + i));
            q4.addLast("Rock Question " + i);
        }
    }

    public boolean add(String playerName) {
        players.add(new Player(playerName));

        log(playerName + " was added");
        log("They are player number " + players.size());
        return true;
    }

    public int howManyPlayers() {
        return players.size();
    }

    public void roll(int roll) {
        Player currentPlayer = players.get(currentPlayerIndex);
        log(currentPlayer + " is the current player");
        log("They have rolled a " + roll);

        if (currentPlayer.isInPenaltyBox()) {
            if (roll % 2 != 0) {
                //User is getting out of penalty box
                currentPlayer.free();

                //Write that user is getting out
                log(currentPlayer + " is getting out of the penalty box");
                // add roll to place
                currentPlayer.move(roll);

                log(currentPlayer
                        + "'s new location is "
                        + currentPlayer.getPosition());
                log("The category is " + currentCategory());
                askQuestion();
            } else {
                log(currentPlayer + " is not getting out of the penalty box");
            }
        } else {
            currentPlayer.move(roll);

            log(currentPlayer
                    + "'s new location is "
                    + currentPlayer.getPosition());
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
        return switch (players.get(currentPlayerIndex).getPosition() % 4) {
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
    public boolean correctAnswer() {
        if (players.get(currentPlayerIndex).isInPenaltyBox()) {
            nextPlayer();
            return true;
        } else {
            return scorePointsChangePlayerAndVerifyIfLoser();
        }
    }

    private void nextPlayer() {
        currentPlayerIndex++;
        if (currentPlayerIndex == players.size()) currentPlayerIndex = 0;
    }

    private boolean scorePointsChangePlayerAndVerifyIfLoser() {
        scorePoints();
        Player currentPlayer = players.get(this.currentPlayerIndex);
        boolean notAWinner = !currentPlayer.isWinner();
        nextPlayer();

        return notAWinner;
    }

    private void scorePoints() {
        Player currentPlayer = players.get(this.currentPlayerIndex);
        log("Answer was correct!!!!");
        currentPlayer.score();
        log(currentPlayer
                + " now has "
                + currentPlayer.getPoints()
                + " Gold Coins.");
    }

    /**
     * To call when the answer is wrong
     *
     * @return
     */
    public boolean wrongAnswer() {
        Player currentPlayer = players.get(currentPlayerIndex);
        log("Question was incorrectly answered");
        log(currentPlayer + " was sent to the penalty box");
        currentPlayer.sendToJail();

        nextPlayer();
        return true;
    }

    private void log(String toLog) {
        System.out.println(toLog);
    }
    @Override
    public String toString() {
        return "Game{" +
                ", players=" + players +
                ", _q1=" + q1 +
                ", q2=" + q2 +
                ", currentPlayer=" + currentPlayerIndex +
                ", q3=" + q3 +
                ", Q5=" + q4 +
                '}';
    }
}
