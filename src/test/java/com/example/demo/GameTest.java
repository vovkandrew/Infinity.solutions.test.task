package com.example.demo;

import com.example.demo.model.Scoreboard;
import com.example.demo.service.Calculator;
import com.example.demo.service.CalculatorImpl;
import com.example.demo.service.Game;
import com.example.demo.service.GameImpl;
import com.example.demo.service.InputParamChecker;
import com.example.demo.service.InputParamCheckerImpl;
import org.junit.Assert;
import org.junit.Test;

public class GameTest {
    private final InputParamChecker checker = new InputParamCheckerImpl();
    private final Calculator calculator = new CalculatorImpl();
    private final Game game = new GameImpl(calculator, checker);
    private final Scoreboard scoreboard = new Scoreboard();

    @Test
    public void generalGameTest() {
        game.playGame(5, scoreboard);
        int expectedFirstHit = 5;
        int actualFirstHit = scoreboard.getFrames().get(0).getFirstHit();
        int expectedTotal = 5;
        int actualTotal = scoreboard.getFrames().get(0).getTotal();
        int expectedNumOfHits = 1;
        int actualNumOfHits = scoreboard.getFrames().get(0).getNumOfHits();
        Assert.assertEquals(expectedFirstHit, actualFirstHit);
        Assert.assertEquals(expectedTotal, actualTotal);
        Assert.assertEquals(expectedNumOfHits, actualNumOfHits);
    }

    @Test
    public void generalGameTestSecond() {
        game.playGame(5, scoreboard);
        game.playGame(4, scoreboard);
        int expectedSecondHit = 4;
        int actualSecondHit = scoreboard.getFrames().get(0).getSecondHit();
        int expectedTotal = 9;
        int actualTotal = scoreboard.getFrames().get(0).getTotal();
        int expectedNumOfHits = 2;
        int actualNumOfHits = scoreboard.getFrames().get(0).getNumOfHits();
        boolean actualFinished = scoreboard.getFrames().get(0).isFinished();
        Assert.assertEquals(expectedSecondHit, actualSecondHit);
        Assert.assertEquals(expectedTotal, actualTotal);
        Assert.assertEquals(expectedNumOfHits, actualNumOfHits);
        Assert.assertTrue(actualFinished);
    }
}
