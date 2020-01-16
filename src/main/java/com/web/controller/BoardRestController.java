package com.web.controller;

import com.web.annotation.SocialUser;
import com.web.domain.Board;
import com.web.domain.User;
import com.web.repository.BoardRepository;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
public class BoardRestController {

    private BoardRepository boardRepository;

    public BoardRestController(BoardRepository boardRepository) {
        this.boardRepository = boardRepository;
    }

    @GetMapping("/api/boards")
    public String list(@PageableDefault Pageable pageable, @SocialUser User user, Model model) {
        model.addAttribute("boardList", boardRepository.findAll(pageable));
        return "/board/list";
    }


    @PostMapping("/api/boards")
    public ResponseEntity<?> postBoard(@RequestBody Board board) {
        board.setCreatedDateNow();
        boardRepository.save(board);
        return new ResponseEntity<>("{}", HttpStatus.CREATED);
    }

    @PutMapping("/api/boards/{idx}")
    public ResponseEntity<?> putBoard(@PathVariable("idx") Long idx, @RequestBody Board board) {
        Board persistBoard = boardRepository.getOne(idx);
        persistBoard.update(board);
        boardRepository.save(persistBoard);

        return new ResponseEntity<>("{}", HttpStatus.OK);
    }

    @DeleteMapping("/api/boards/{idx}")
    public ResponseEntity<?> deleteBoard(@PathVariable("idx") Long idx) {
        boardRepository.deleteById(idx);

        return new ResponseEntity<>("{}", HttpStatus.OK);
    }
}
