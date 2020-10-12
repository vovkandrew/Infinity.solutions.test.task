package com.example.demo;

import com.example.demo.model.Scoreboard;
import com.example.demo.service.InputParamChecker;
import com.example.demo.service.InputParamCheckerImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class InputParamCheckerTest {
    private final InputParamChecker checker = new InputParamCheckerImpl();
    private final Scoreboard expectedFull = new Scoreboard();
    private final Scoreboard expectedEmpty = new Scoreboard();

    @Before
    public void init() {
        expectedFull.setFinished(true);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkInputHitParamWithNegValue() {
        checker.checkInputHitParam(-1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkInputHitParamWithValueBiggerThanTen() {
        checker.checkInputHitParam(100);
    }

    @Test
    public void checkInputHitParamWithProperValue() {
        checker.checkInputHitParam(5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkInputHitParamWithHigherSumOfHits() {
        checker.checkInputHitParam(10, 5);
    }

    @Test
    public void checkInputHitParamWithProperSumOfHits() {
        checker.checkInputHitParam(5, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkInputHitParamForFrame() {
        checker.checkInputHitParam(6, 6, 5);
    }

    @Test
    public void checkInputHitParamForFrameWithProperArguments() {
        checker.checkInputHitParam(5, 3, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void checkInputHitParamForFinalFrame() {
        checker.checkInputHitParamForFinalFrame(8, 6, 9);
    }

    @Test
    public void checkInputHitParamForFinalFrameWithProperArguments() {
        checker.checkInputHitParamForFinalFrame(3, 5, 2);
    }

    @Test
    public void checkCurrentScoreboardWithFullScoreboard() {
        Scoreboard actual = checker.checkCurrentScoreboardSession(expectedFull);
        Assert.assertEquals(expectedEmpty, actual);
    }

    @Test
    public void checkCurrentScoreboardWithEmptyScoreboard() {
        expectedEmpty.getFinalFrame().setFirstHit(10);
        Scoreboard actual = checker.checkCurrentScoreboardSession(expectedEmpty);
        Assert.assertEquals(expectedEmpty, actual);
    }

}
