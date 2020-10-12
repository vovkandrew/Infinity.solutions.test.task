package com.example.demo.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.List;

@Data
@NoArgsConstructor
public class ScoreboardDto {
    private List<FrameDto> frames;
    private FinalFrameDto finalFrame;
}
