package com.simba.missonGame.controller;

import com.simba.missonGame.dto.Board.CreateBoardReq;
import com.simba.missonGame.service.BoardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/board")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class BoardController {

    @Autowired
    BoardService boardService;

    @GetMapping
    ResponseEntity<?> get1(){
        return ResponseEntity.ok("good");
    }


    @PostMapping("/newboard")
//    ResponseEntity<?> addNewUser(@RequestParam(value= "jwtFakeToken") String jwtFakeToken){
    ResponseEntity<?> addNewUser(@RequestBody CreateBoardReq createBoardReq){
        System.out.println(createBoardReq.getJwtFakeToken());
        return ResponseEntity.ok(boardService.createBoard(createBoardReq));
    }

    @GetMapping("/allboard")
    ResponseEntity<?> readAllBoard(){
        return ResponseEntity.ok(boardService.getBoardList());
    }
}
