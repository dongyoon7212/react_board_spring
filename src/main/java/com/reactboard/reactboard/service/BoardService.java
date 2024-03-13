package com.reactboard.reactboard.service;

import com.reactboard.reactboard.dto.BoardInsertReqDto;
import com.reactboard.reactboard.dto.BoardInsertRespDto;
import com.reactboard.reactboard.dto.BoardSelectRespDto;
import com.reactboard.reactboard.entity.Board;
import com.reactboard.reactboard.repository.BoardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    public BoardInsertRespDto saveBoard(BoardInsertReqDto boardInsertReqDto) {
        Board board = Board.builder()
                .boardTitle(boardInsertReqDto.getBoardTitle())
                .boardContent(boardInsertReqDto.getBoardContent())
                .build();

        int succcessCount = boardRepository.insertBoard(board);

        BoardInsertRespDto boardInsertRespDto = BoardInsertRespDto.builder()
                .boardId(board.getBoardId())
                .boardTitle(board.getBoardTitle())
                .boardContent(board.getBoardContent())
                .createDate(board.getCreateDate())
                .successStatus(succcessCount > 0)
                .successCount(succcessCount)
                .build();

        return boardInsertRespDto;
    }

    public List<BoardSelectRespDto> getBoardAll() {
        List<BoardSelectRespDto> boardDtoList = new ArrayList<>();
        List<Board> boardList = boardRepository.selectBoardAll();

        for (Board board : boardList) {
            boardDtoList.add(board.toDto());
        }

        return boardDtoList;
    }


}
