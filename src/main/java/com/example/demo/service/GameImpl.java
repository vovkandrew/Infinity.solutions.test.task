package com.example.demo.service;

import com.example.demo.model.FinalFrame;
import com.example.demo.model.Frame;
import com.example.demo.model.Scoreboard;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GameImpl implements Game{
    private static final int STRIKE_AND_SPARE_AMOUNT = 10;
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final boolean FINISHED = true;
    private static final int LAST = 8;
    private final Calculator calculator;
    private final InputParamChecker checker;

    @Autowired
    public GameImpl(Calculator calculator, InputParamChecker checker) {
        this.calculator = calculator;
        this.checker = checker;
    }

    @Override
    public Scoreboard playGame(int hit, Scoreboard scoreboard) {
        checker.checkInputHitParam(hit);
        if (scoreboard.getFrames().get(LAST).isFinished()) {
            FinalFrame frame = scoreboard.getFinalFrame();
            if (frame.getNumOfHits() == ZERO) {
                setFirstHitAndIncrement(frame, hit);
            } else {
                if (frame.getNumOfHits() == ONE) {
                    checker.checkInputHitParam(frame.getFirstHit(), frame.getSecondHit(), hit);
                    setSecondHitAndIncrement(frame, hit);
                    if (frame.getFirstHit() + frame.getSecondHit() < STRIKE_AND_SPARE_AMOUNT) {
                        setThirdHitAndIncrement(frame, ZERO);
                        frame.setFinished(true);
                        scoreboard.setFinished(FINISHED);
                    }
                } else {
                    checker.checkInputHitParamForFinalFrame(frame.getSecondHit(), frame.getSecondHit(), hit);
                    setThirdHitAndIncrement(frame, hit);
                    frame.setFinished(true);
                    scoreboard.setFinished(FINISHED);
                }
            }
        } else {
            Frame frame = scoreboard.getFrames().stream()
                    .filter(f -> !f.isFinished()).findFirst().get();
            if (frame.getNumOfHits() == ZERO) {
                if (hit == STRIKE_AND_SPARE_AMOUNT) {
                    setFirstHitAndIncrement(frame, hit);
                    frame.setFinished(FINISHED);
                } else {
                    setFirstHitAndIncrement(frame, hit);
                }
            } else {
                checker.checkInputHitParam(frame.getFirstHit(), hit);
                setSecondHitAndIncrement(frame, hit);
                frame.setFinished(FINISHED);
            }
        }
        calculator.calculatePoints(scoreboard);
        return scoreboard;
    }

    private void setFirstHitAndIncrement(Frame frame, int hit) {
        frame.setFirstHit(hit);
        frame.incrementNumOfHits();
    }

    private void setSecondHitAndIncrement(Frame frame, int hit) {
        frame.setSecondHit(hit);
        frame.incrementNumOfHits();
    }

    private void setThirdHitAndIncrement(FinalFrame frame, int hit) {
        frame.setThirdHit(hit);
        frame.incrementNumOfHits();
    }
}
