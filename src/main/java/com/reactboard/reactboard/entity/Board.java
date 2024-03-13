package com.reactboard.reactboard.entity;

import com.reactboard.reactboard.dto.BoardSelectRespDto;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class Board {
    private int boardId;
    private String boardTitle;
    private String boardContent;
    private LocalDateTime createDate;

    public BoardSelectRespDto toDto() {
        return BoardSelectRespDto.builder()
                .boardId(boardId)
                .boardTitle(boardTitle)
                .boardContent(boardContent)
                .createDate(createDate)
                .build();
    }
}
