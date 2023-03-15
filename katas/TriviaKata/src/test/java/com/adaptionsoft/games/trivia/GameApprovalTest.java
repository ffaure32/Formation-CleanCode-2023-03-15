package com.adaptionsoft.games.trivia;

import com.adaptionsoft.games.uglytrivia.Game;
import org.approvaltests.Approvals;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

public class GameApprovalTest {

    @Test
    void game_with_several_players_approval_test() {
        Game aGame = simulateGame();
        Approvals.verify(aGame);
    }

    @Test
    void game_with_several_players_sysout_test() {
        ByteArrayOutputStream fakeoutput = new ByteArrayOutputStream();
        System.setOut(new PrintStream(fakeoutput));

        simulateGame();

        Approvals.verify(fakeoutput.toString());
    }

    private static Game simulateGame() {
        Game aGame = new Game();

        aGame.add("Chet");
        aGame.add("Pat");
        aGame.add("Sue");
        aGame.add("Hellen");
        aGame.add("Kevin");

        Random rand = new Random(123);
        boolean notAWinner;
        do {

            aGame.roll(rand.nextInt(5) + 1);

            if (rand.nextInt(9) == 7) {
                notAWinner = aGame.wrongAnswer();
            } else {
                notAWinner = aGame.wasCorrectlyAnswered();
            }



        } while (notAWinner);
        return aGame;
    }
}
