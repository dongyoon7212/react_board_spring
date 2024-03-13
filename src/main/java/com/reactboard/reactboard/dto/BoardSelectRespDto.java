package com.reactboard.reactboard.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BoardSelectRespDto {
    private int boardId;
    private String boardTitle;
    private String boardContent;
    private LocalDateTime createDate;
}
