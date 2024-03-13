package com.reactboard.reactboard.controller;

import com.reactboard.reactboard.aop.annotation.ParamsAspect;
import com.reactboard.reactboard.dto.BoardInsertReqDto;
import com.reactboard.reactboard.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BoardController {

    @Autowired
    private BoardService boardService;

    @ParamsAspect
    @PostMapping("/board")
    public ResponseEntity<?> boardInsert(@RequestBody BoardInsertReqDto boardInsertReqDto) {

        return ResponseEntity.ok(boardService.saveBoard(boardInsertReqDto));
    }

    @GetMapping("/board")
    public ResponseEntity<?> boardListAll() {

        return ResponseEntity.ok(boardService.getBoardAll());
    }
}
