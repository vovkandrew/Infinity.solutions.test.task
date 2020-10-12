package com.example.demo.controller;

import com.example.demo.dto.ScoreboardDto;
import com.example.demo.model.Scoreboard;
import com.example.demo.service.Game;
import com.example.demo.service.InputParamChecker;
import com.example.demo.service.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/bowling")
@Scope("session")
public class GameController {
    private Scoreboard scoreboard = new Scoreboard();
    private final Game game;
    private final Mapper mapper;
    private final InputParamChecker checker;

    @Autowired
    public GameController(Game game, Mapper mapper, InputParamChecker checker) {
        this.game = game;
        this.mapper = mapper;
        this.checker = checker;
    }

    @PostMapping
    @RequestMapping("/play")
    public ScoreboardDto play(@RequestParam int hit) {
        scoreboard = checker.checkCurrentScoreboardSession(scoreboard);
        return mapper.toScoreboardDto(game.playGame(hit, scoreboard));
    }
}
