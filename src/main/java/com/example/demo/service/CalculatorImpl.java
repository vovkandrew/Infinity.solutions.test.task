package com.example.demo.service;

import com.example.demo.model.FinalFrame;
import com.example.demo.model.Frame;
import com.example.demo.model.Scoreboard;
import java.util.List;
import org.springframework.stereotype.Service;


@Service
public class CalculatorImpl implements Calculator{
    private static final int STRIKE_AND_SPARE_AMOUNT = 10;
    private static final int FIRST = 0;
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int LAST_BUT_ONE = 7;
    private static final int LAST = 8;

    @Override
    public Scoreboard calculatePoints(Scoreboard scoreboard) {
        List<Frame> frames = scoreboard.getFrames();
        Frame emptyPrevious = new Frame();
        Frame previous, current, next, afterNext;
        for (int i = FIRST; i < frames.size(); i++) {
            current = frames.get(i);
            if (i == FIRST) {
                next = frames.get(i + ONE);
                afterNext = frames.get(i + TWO);
                setTotal(emptyPrevious, current, next, afterNext);
            } else if (i == LAST) {
                previous = frames.get(i - ONE);
                next = scoreboard.getFinalFrame();
                if (current.getFirstHit() == STRIKE_AND_SPARE_AMOUNT) {
                    setTotalForOneStrikeInRow(previous, current, next);
                } else if (current.getFirstHit() + current.getSecondHit() == STRIKE_AND_SPARE_AMOUNT){
                    setTotalForSpare(previous, current, next);
                } else {
                    setTotalForUsualHit(previous, current);
                }
            } else if (i == LAST_BUT_ONE){
                previous = frames.get(i - ONE);
                next = frames.get(i + ONE);
                afterNext = scoreboard.getFinalFrame();
                setTotal(previous, current, next, afterNext);
            } else {
                previous = frames.get(i - ONE);
                next = frames.get(i + ONE);
                afterNext = frames.get(i + TWO);
                setTotal(previous, current, next, afterNext);
            }
        }
        FinalFrame finalFrame = scoreboard.getFinalFrame();
        finalFrame.setTotal(frames.get(frames.size() - ONE).getTotal() + finalFrame.getFirstHit()
                + finalFrame.getSecondHit() + finalFrame.getThirdHit());
        return scoreboard;
    }

    private void setTotal(Frame previous, Frame current, Frame next, Frame afterNext) {
        if (current.getFirstHit() == STRIKE_AND_SPARE_AMOUNT) {
            if (next.getFirstHit() == STRIKE_AND_SPARE_AMOUNT) {
                setTotalForTwoStrikesInRow(previous, current, next, afterNext);
            } else {
                setTotalForOneStrikeInRow(previous, current, next);
            }
        } else if (current.getFirstHit() + current.getSecondHit() == STRIKE_AND_SPARE_AMOUNT){
            setTotalForSpare(previous, current, next);
        } else {
            setTotalForUsualHit(previous, current);
        }
    }

    private void setTotalForTwoStrikesInRow(Frame previous, Frame current, Frame next, Frame afterNext) {
        current.setTotal(previous.getTotal() + current.getFirstHit() + next.getFirstHit() + afterNext.getFirstHit());
    }

    private void setTotalForOneStrikeInRow(Frame previous, Frame current, Frame next) {
        current.setTotal(previous.getTotal() + current.getFirstHit() + next.getFirstHit() + next.getSecondHit());
    }

    private void setTotalForSpare(Frame previous, Frame current, Frame next) {
        current.setTotal(previous.getTotal() + current.getFirstHit() + current.getSecondHit() + next.getFirstHit());
    }

    private void setTotalForUsualHit(Frame previous, Frame current) {
        current.setTotal(previous.getTotal() + current.getFirstHit() + current.getSecondHit());
    }
}
