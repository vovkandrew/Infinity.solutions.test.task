package com.example.demo;

import com.example.demo.model.Scoreboard;
import com.example.demo.service.Calculator;
import com.example.demo.service.CalculatorImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CalculatorTest {
    private final Calculator calculator = new CalculatorImpl();
    private final Scoreboard scoreboard = new Scoreboard();

    @Before
    public void init() {
        scoreboard.getFrames().get(0).setFirstHit(10);
        scoreboard.getFrames().get(0).setFinished(true);
        scoreboard.getFrames().get(0).setNumOfHits(1);
        scoreboard.getFrames().get(1).setFirstHit(1);
        scoreboard.getFrames().get(1).setSecondHit(8);
        scoreboard.getFrames().get(1).setFinished(true);
        scoreboard.getFrames().get(1).setNumOfHits(2);
        scoreboard.getFrames().get(2).setFirstHit(0);
        scoreboard.getFrames().get(2).setSecondHit(10);
        scoreboard.getFrames().get(2).setFinished(true);
        scoreboard.getFrames().get(2).setNumOfHits(2);
        scoreboard.getFrames().get(3).setFirstHit(7);
        scoreboard.getFrames().get(3).setSecondHit(2);
        scoreboard.getFrames().get(3).setFinished(true);
        scoreboard.getFrames().get(3).setNumOfHits(2);
        scoreboard.getFrames().forEach(frame -> frame.setNumOfHits(2));
        scoreboard.getFrames().forEach(frame -> frame.setFinished(true));
        scoreboard.getFinalFrame().setFirstHit(10);
        scoreboard.getFinalFrame().setSecondHit(5);
        scoreboard.getFinalFrame().setThirdHit(0);
        scoreboard.getFinalFrame().setFinished(true);
        scoreboard.getFinalFrame().setNumOfHits(3);
        calculator.calculatePoints(scoreboard);
    }

    @Test
    public void testGeneral() {
        int expected = 19;
        int actual = scoreboard.getFrames().get(0).getTotal();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testStrike() {
        int expected = 28;
        int actual = scoreboard.getFrames().get(1).getTotal();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testMiss() {
        int expected = 45;
        int actual = scoreboard.getFrames().get(2).getTotal();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testSpare() {
        int expected = 54;
        int actual = scoreboard.getFrames().get(3).getTotal();
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testFinalFrame() {
        int expected = 69;
        int actual = scoreboard.getFinalFrame().getTotal();
        Assert.assertEquals(expected, actual);
    }
}
