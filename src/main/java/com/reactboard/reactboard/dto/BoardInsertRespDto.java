package com.reactboard.reactboard.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Builder
@Data
public class BoardInsertRespDto {
    private int boardId;
    private String boardTitle;
    private String boardContent;
    private LocalDateTime createDate;
    private boolean successStatus;
    private int successCount;
}
