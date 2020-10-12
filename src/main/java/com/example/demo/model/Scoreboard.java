package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
public class Scoreboard {
    private final List<Frame> frames = Arrays.asList(new Frame(), new Frame(), new Frame(), new Frame(), new Frame(),
                                                    new Frame(), new Frame(), new Frame(), new Frame());
    private FinalFrame finalFrame = new FinalFrame();
    private boolean isFinished = false;
}
