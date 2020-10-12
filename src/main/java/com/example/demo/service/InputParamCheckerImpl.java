package com.example.demo.service;

import com.example.demo.model.Scoreboard;
import org.springframework.stereotype.Service;

@Service
public class InputParamCheckerImpl implements InputParamChecker {
    private static final int MAX_HIT_AMOUNT = 10;
    private static final int MIN_HIT_AMOUNT = 0;
    private static final String ERROR_MESSAGE = "Wrong number of pins provided, please provide number not higher than ";
    @Override
    public void checkInputHitParam(int hit) {
        if (hit < MIN_HIT_AMOUNT || hit > MAX_HIT_AMOUNT) {
            throw new IllegalArgumentException("Wrong number of pins provided, please provide number between 0 and 10");
        }
    }

    @Override
    public void checkInputHitParam(int firstHit, int hit) {
        if (firstHit + hit > MAX_HIT_AMOUNT) {
            throw new IllegalArgumentException(ERROR_MESSAGE + (MAX_HIT_AMOUNT - firstHit));
        }
    }

    @Override
    public void checkInputHitParam(int firstHit, int secondHit, int hit) {
        if (firstHit < MAX_HIT_AMOUNT && secondHit + hit > MAX_HIT_AMOUNT) {
            throw new IllegalArgumentException(ERROR_MESSAGE + (MAX_HIT_AMOUNT - firstHit));
        }
    }

    @Override
    public void checkInputHitParamForFinalFrame(int secondHit, int firstHit, int hit) {
        if (secondHit < MAX_HIT_AMOUNT && firstHit + hit > MAX_HIT_AMOUNT) {
            throw new IllegalArgumentException(ERROR_MESSAGE + (MAX_HIT_AMOUNT - secondHit));
        }
    }

    @Override
    public Scoreboard checkCurrentScoreboardSession(Scoreboard scoreboard) {
        if (scoreboard.isFinished()) {
            return new Scoreboard();
        } else {
            return scoreboard;
        }
    }
}
