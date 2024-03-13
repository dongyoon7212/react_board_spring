package com.reactboard.reactboard.repository;

import com.reactboard.reactboard.entity.Board;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BoardRepository {
    public int insertBoard(Board board);
    public List<Board> selectBoardAll();
}
