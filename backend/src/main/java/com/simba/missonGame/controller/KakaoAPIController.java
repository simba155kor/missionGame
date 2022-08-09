package com.simba.missonGame.controller;

import com.simba.missonGame.service.KakaoAPIService;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/kakaoAPI")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class KakaoAPIController {

    @Autowired
    KakaoAPIService kakaoAPIService;

    //카카오 유저정보 요청하기 위한 요청
    //jwtFakeToken 받아서 accessToken으로 바꾸고 이걸로 회원 정보 요청함.
    @GetMapping("/userinfo")
    ResponseEntity<Map<String, String>> getUserInfo(@RequestParam(value = "jwtFakeToken") String jwtFakeToken) throws ParseException {
        kakaoAPIService.checkAccessTokenExpiredAndRenew(jwtFakeToken);

        String accessToken = kakaoAPIService.convertJwtFakeTokenToAccessToken(jwtFakeToken);
        Map<String, String> userInfo = kakaoAPIService.getUserInfo(accessToken);
        return ResponseEntity.ok(userInfo);
    }

}
