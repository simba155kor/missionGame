package com.simba.missonGame.service;

import com.simba.missonGame.db.entity.Board;
import com.simba.missonGame.db.entity.Kakaomember;
import com.simba.missonGame.db.repository.BoardRepository;
import com.simba.missonGame.db.repository.KakaomemberRepository;
import com.simba.missonGame.dto.Board.CreateBoardReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class BoardServiceImpl implements BoardService{

    @Autowired
    KakaoAPIService kakaoAPIService;

    @Autowired
    KakaomemberRepository kakaoMemberRepository;

    @Autowired
    BoardRepository boardRepository;

    public Board createBoard(CreateBoardReq createBoardReq){
        String kakaoId = kakaoAPIService.convertJwtFakeTokenTokakaoId(createBoardReq.getJwtFakeToken());
        Optional<Kakaomember> kakaoMember = kakaoMemberRepository.findByKakaoId(kakaoId);

        System.out.println(kakaoId);

        if(kakaoMember.isPresent()){
            Long memberNo = kakaoMember.get().getId();
            if(isBoardByMemberNo(memberNo) == null){
                Board board = new Board(memberNo);
                return boardRepository.save(board);
            }
            else System.out.println("already registerd.");
        }

        return null;
    }

    public Board isBoardByMemberNo(Long memberNo){
        return boardRepository.findByMemberNo(memberNo).orElse(null);
    }

    public List<Map<String, String>> getBoardList(){
        List<Board> boardList = boardRepository.findAll();
        List<Map<String, String>> userList = new ArrayList<>();
        for (Board board : boardList) {
            Kakaomember kakaomember = kakaoMemberRepository.findById(board.getMemberNo()).get();
            Map<String, String> tempMap = new HashMap<>();
            tempMap.put("nickname", kakaomember.getNickname());
            tempMap.put("profileImage", kakaomember.getProfileImage());
            userList.add(tempMap);
        }
        return userList;
    }
}
