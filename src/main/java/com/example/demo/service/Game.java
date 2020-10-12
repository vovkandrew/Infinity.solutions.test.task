package com.example.demo.service;

import com.example.demo.model.Scoreboard;

public interface Game {
    Scoreboard playGame(int hit, Scoreboard scoreboard);
}
