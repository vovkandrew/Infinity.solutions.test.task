package com.example.demo.service;

import com.example.demo.dto.FinalFrameDto;
import com.example.demo.dto.FrameDto;
import com.example.demo.dto.ScoreboardDto;
import com.example.demo.model.FinalFrame;
import com.example.demo.model.Frame;
import com.example.demo.model.Scoreboard;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;

@Service
public class MapperImpl implements Mapper{
    private static final String STRIKE = "X";
    private static final String SPARE = "/";
    private static final String MISS = "—";
    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int TWO = 2;
    private static final int TEN = 10;

    @Override
    public FrameDto toFrameDto(Frame frame) {
        FrameDto dto = new FrameDto();
        if (!frame.isFinished()) {
            if (frame.getNumOfHits() == ONE) {
                setFirstHitForFrameDto(frame, dto);
                dto.setTotal(String.valueOf(frame.getTotal()));
            }
            return dto;
        }
        if (frame.getFirstHit() == TEN) {
            dto.setSecondHit(STRIKE);
            dto.setTotal(String.valueOf(frame.getTotal()));
        } else if (frame.getFirstHit() + frame.getSecondHit() == TEN) {
            setFirstHitForFrameDto(frame, dto);
            dto.setSecondHit(SPARE);
            dto.setTotal(String.valueOf(frame.getTotal()));
        } else {
            if (frame.getFirstHit() == ZERO) {
                dto.setFirstHit(MISS);
            } else {
                dto.setFirstHit(String.valueOf(frame.getFirstHit()));
            }
            if (frame.getSecondHit() == ZERO) {
                dto.setSecondHit(MISS);
            } else {
                dto.setSecondHit(String.valueOf(frame.getSecondHit()));
            }
            dto.setTotal(String.valueOf(frame.getTotal()));
        }
        return dto;
    }

    @Override
    public FinalFrameDto toFinalFrameDto(FinalFrame frame) {
        FinalFrameDto dto = new FinalFrameDto();
        if (!frame.isFinished()) {
            if (frame.getNumOfHits() == ONE) {
                if (frame.getFirstHit() == TEN) {
                    dto.setFirstHit(STRIKE);
                } else if (frame.getFirstHit() == ZERO) {
                    dto.setFirstHit(MISS);
                } else {
                    dto.setFirstHit(String.valueOf(frame.getFirstHit()));
                }
                dto.setTotal(String.valueOf(frame.getTotal()));
            } else if (frame.getNumOfHits() == TWO) {
                if (frame.getFirstHit() == TEN) {
                    dto.setFirstHit(STRIKE);
                    if (frame.getSecondHit() == TEN) {
                        dto.setSecondHit(STRIKE);
                    } else if (frame.getSecondHit() > ZERO) {
                        dto.setSecondHit(String.valueOf(frame.getSecondHit()));
                    } else {
                        dto.setSecondHit(MISS);
                    }
                } else if (frame.getFirstHit() == ZERO) {
                    dto.setFirstHit(MISS);
                    if (frame.getSecondHit() == TEN) {
                        dto.setSecondHit(SPARE);
                    } else {
                        dto.setSecondHit(String.valueOf(frame.getSecondHit()));
                    }
                } else {
                    dto.setFirstHit(String.valueOf(frame.getFirstHit()));
                    if (frame.getFirstHit() + frame.getSecondHit() == TEN) {
                        dto.setSecondHit(SPARE);
                    }
                }
                dto.setTotal(String.valueOf(frame.getTotal()));
            }
            return dto;
        }
        if (frame.getFirstHit() == TEN) {
            dto.setFirstHit(STRIKE);
            if (frame.getSecondHit() == TEN) {
                dto.setSecondHit(STRIKE);
                setThirdHit(dto, frame);
            } else if (frame.getSecondHit() > ZERO) {
                dto.setSecondHit(String.valueOf(frame.getSecondHit()));
                if (frame.getSecondHit() + frame.getThirdHit() == TEN) {
                    dto.setThirdHit(SPARE);
                } else if (frame.getThirdHit() == ZERO) {
                    dto.setThirdHit(MISS);
                } else {
                    dto.setThirdHit(String.valueOf(frame.getThirdHit()));
                }
            } else {
                dto.setSecondHit(MISS);
                setThirdHit(dto, frame);
            }
        } else if (frame.getFirstHit() > ZERO) {
            dto.setFirstHit(String.valueOf(frame.getFirstHit()));
            if (frame.getFirstHit() + frame.getSecondHit() == TEN) {
                dto.setSecondHit(SPARE);
                setThirdHit(dto, frame);
            } else {
                dto.setFirstHit(String.valueOf(frame.getFirstHit()));
                setSecondHitForFinalFrameDto(frame, dto);
            }
        } else {
            dto.setFirstHit(MISS);
            if (frame.getSecondHit() == TEN) {
                dto.setSecondHit(SPARE);
                setThirdHit(dto, frame);
            }
            setSecondHitForFinalFrameDto(frame, dto);
        }
        dto.setTotal(String.valueOf(frame.getTotal()));
        return dto;
    }

    @Override
    public ScoreboardDto toScoreboardDto(Scoreboard scoreboard) {
        ScoreboardDto dto = new ScoreboardDto();
        List<FrameDto> frameDtos = scoreboard.getFrames()
                .stream()
                .map(this::toFrameDto)
                .collect(Collectors.toList());
        FinalFrameDto finalFrameDto = toFinalFrameDto(scoreboard.getFinalFrame());
        dto.setFrames(frameDtos);
        dto.setFinalFrame(finalFrameDto);
        return dto;
    }

    private void setThirdHit(FinalFrameDto dto, FinalFrame frame) {
        if (frame.getThirdHit() == TEN) {
            dto.setThirdHit(STRIKE);
        } else if (frame.getThirdHit() > ZERO) {
            dto.setThirdHit(String.valueOf(frame.getThirdHit()));
        } else {
            dto.setThirdHit(MISS);
        }
    }

    private void setFirstHitForFrameDto(Frame frame, FrameDto dto) {
        if (frame.getFirstHit() == ZERO) {
            dto.setFirstHit(MISS);
        } else {
            dto.setFirstHit(String.valueOf(frame.getFirstHit()));
        }
    }

    private void setSecondHitForFinalFrameDto(FinalFrame frame, FinalFrameDto dto) {
        if (frame.getSecondHit() > ZERO) {
            dto.setSecondHit(String.valueOf(frame.getSecondHit()));
        } else {
            dto.setSecondHit(MISS);
        }
    }
}
