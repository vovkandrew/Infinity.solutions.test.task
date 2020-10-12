package com.example.demo.service;

import com.example.demo.dto.FinalFrameDto;
import com.example.demo.dto.FrameDto;
import com.example.demo.dto.ScoreboardDto;
import com.example.demo.model.FinalFrame;
import com.example.demo.model.Frame;
import com.example.demo.model.Scoreboard;

public interface Mapper {
    FrameDto toFrameDto(Frame frame);

    FinalFrameDto toFinalFrameDto(FinalFrame frame);

    ScoreboardDto toScoreboardDto(Scoreboard scoreboard);
}
