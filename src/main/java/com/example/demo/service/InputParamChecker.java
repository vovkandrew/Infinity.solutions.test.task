package com.example.demo.service;

import com.example.demo.model.Scoreboard;

public interface InputParamChecker {
    void checkInputHitParam(int hit);
    void checkInputHitParam(int firstHit, int hit);
    void checkInputHitParam(int firstHit, int secondHit, int hit);
    void checkInputHitParamForFinalFrame(int secondHit, int firstHit, int hit);
    Scoreboard checkCurrentScoreboardSession(Scoreboard scoreboard);
}
