package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Frame {
    private int firstHit = 0;
    private int secondHit = 0;
    private int total = 0;
    private int numOfHits = 0;
    private boolean isFinished = false;

    public void incrementNumOfHits() {
        this.numOfHits++;
    }
}
