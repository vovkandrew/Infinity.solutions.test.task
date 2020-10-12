package com.example.demo;

import com.example.demo.dto.FinalFrameDto;
import com.example.demo.dto.FrameDto;
import com.example.demo.model.FinalFrame;
import com.example.demo.model.Frame;
import com.example.demo.service.Mapper;
import com.example.demo.service.MapperImpl;
import org.junit.Assert;
import org.junit.Test;

public class MapperTest {
    private final Mapper mapper = new MapperImpl();
    private final Frame frame = new Frame();
    private final FrameDto expected = new FrameDto();
    private final FinalFrame finalFrame = new FinalFrame();
    private final FinalFrameDto expectedFinal = new FinalFrameDto();


    @Test
    public void testFinalFrameWithStrike() {
        finalFrame.setFirstHit(10);
        finalFrame.setNumOfHits(1);
        finalFrame.setTotal(10);
        expectedFinal.setFirstHit("X");
        expectedFinal.setTotal("10");
        FinalFrameDto actual = mapper.toFinalFrameDto(finalFrame);
        Assert.assertEquals(expectedFinal, actual);
    }

    @Test
    public void testFinalFrameWithMiss() {
        finalFrame.setFirstHit(10);
        finalFrame.setSecondHit(0);
        finalFrame.setNumOfHits(2);
        finalFrame.setTotal(10);
        expectedFinal.setFirstHit("X");
        expectedFinal.setSecondHit("—");
        expectedFinal.setTotal("10");
        FinalFrameDto actual = mapper.toFinalFrameDto(finalFrame);
        Assert.assertEquals(expectedFinal, actual);
    }

    @Test
    public void testFinalFrameWithSpare() {
        finalFrame.setFirstHit(5);
        finalFrame.setSecondHit(5);
        finalFrame.setThirdHit(5);
        finalFrame.setNumOfHits(3);
        finalFrame.setTotal(15);
        finalFrame.setFinished(true);
        expectedFinal.setFirstHit("5");
        expectedFinal.setSecondHit("/");
        expectedFinal.setThirdHit("5");
        expectedFinal.setTotal("15");
        FinalFrameDto actual = mapper.toFinalFrameDto(finalFrame);
        Assert.assertEquals(expectedFinal, actual);
    }

    @Test
    public void testFinalFrameWithStrikeAndMiss() {
        finalFrame.setFirstHit(10);
        finalFrame.setSecondHit(0);
        finalFrame.setThirdHit(5);
        finalFrame.setNumOfHits(3);
        finalFrame.setTotal(15);
        finalFrame.setFinished(true);
        expectedFinal.setFirstHit("X");
        expectedFinal.setSecondHit("—");
        expectedFinal.setThirdHit("5");
        expectedFinal.setTotal("15");
        FinalFrameDto actual = mapper.toFinalFrameDto(finalFrame);
        Assert.assertEquals(expectedFinal, actual);
    }

    @Test
    public void testFinalFrameWithStrikeAndSpare() {
        finalFrame.setFirstHit(10);
        finalFrame.setSecondHit(5);
        finalFrame.setThirdHit(5);
        finalFrame.setNumOfHits(3);
        finalFrame.setTotal(20);
        finalFrame.setFinished(true);
        expectedFinal.setFirstHit("X");
        expectedFinal.setSecondHit("5");
        expectedFinal.setThirdHit("/");
        expectedFinal.setTotal("20");
        FinalFrameDto actual = mapper.toFinalFrameDto(finalFrame);
        Assert.assertEquals(expectedFinal, actual);
    }

    @Test
    public void testFinalFrameWithTwoStrikes() {
        finalFrame.setFirstHit(10);
        finalFrame.setSecondHit(10);
        finalFrame.setNumOfHits(2);
        finalFrame.setTotal(20);
        expectedFinal.setFirstHit("X");
        expectedFinal.setSecondHit("X");
        expectedFinal.setTotal("20");
        FinalFrameDto actual = mapper.toFinalFrameDto(finalFrame);
        Assert.assertEquals(expectedFinal, actual);
    }

    @Test
    public void testFinalFrameWithThreeStrikes() {
        finalFrame.setFirstHit(10);
        finalFrame.setSecondHit(10);
        finalFrame.setThirdHit(10);
        finalFrame.setNumOfHits(3);
        finalFrame.setTotal(30);
        finalFrame.setFinished(true);
        expectedFinal.setFirstHit("X");
        expectedFinal.setSecondHit("X");
        expectedFinal.setThirdHit("X");
        expectedFinal.setTotal("30");
        FinalFrameDto actual = mapper.toFinalFrameDto(finalFrame);
        Assert.assertEquals(expectedFinal, actual);
    }

    @Test
    public void testFrameWithStrike() {
        frame.setFirstHit(10);
        frame.setFinished(true);
        frame.setNumOfHits(1);
        frame.setTotal(10);
        expected.setFirstHit("");
        expected.setSecondHit("X");
        expected.setTotal("10");
        FrameDto actual = mapper.toFrameDto(frame);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testFrameWithSpare() {
        frame.setFirstHit(5);
        frame.setSecondHit(5);
        frame.setFinished(true);
        frame.setNumOfHits(2);
        frame.setTotal(10);
        expected.setFirstHit("5");
        expected.setSecondHit("/");
        expected.setTotal("10");
        FrameDto actual = mapper.toFrameDto(frame);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testFrameGeneral() {
        frame.setFirstHit(2);
        frame.setSecondHit(7);
        frame.setFinished(true);
        frame.setNumOfHits(2);
        frame.setTotal(9);
        expected.setFirstHit("2");
        expected.setSecondHit("7");
        expected.setTotal("9");
        FrameDto actual = mapper.toFrameDto(frame);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testFrameWithMiss() {
        frame.setSecondHit(7);
        frame.setFinished(true);
        frame.setNumOfHits(2);
        frame.setTotal(7);
        expected.setFirstHit("—");
        expected.setSecondHit("7");
        expected.setTotal("7");
        FrameDto actual = mapper.toFrameDto(frame);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void testFrameWithTwoMisses() {
        frame.setFinished(true);
        frame.setNumOfHits(2);
        frame.setTotal(0);
        expected.setFirstHit("—");
        expected.setSecondHit("—");
        expected.setTotal("0");
        FrameDto actual = mapper.toFrameDto(frame);
        Assert.assertEquals(expected, actual);
    }
}
