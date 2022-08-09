package com.simba.missonGame.service;

import com.simba.missonGame.db.entity.Board;
import com.simba.missonGame.dto.Board.CreateBoardReq;
import com.simba.missonGame.dto.Board.CreateBoardRes;

import java.util.List;
import java.util.Map;

public interface BoardService {

    public Board createBoard(CreateBoardReq createBoardReq);

    public Board isBoardByMemberNo(Long memberNo);

    public List<Map<String, String>>  getBoardList();

}
