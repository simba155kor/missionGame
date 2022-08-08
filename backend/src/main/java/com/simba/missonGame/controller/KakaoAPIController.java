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

    @GetMapping("/userinfo")
    ResponseEntity<Map<String, String>> getUserInfo(@RequestParam(value = "jwtFakeToken") String jwtFakeToken) throws ParseException {
        String accessToken = kakaoAPIService.convertJwtFakeTokenToAccessToken(jwtFakeToken);
        Map<String, String> userInfo = kakaoAPIService.getUserInfo(accessToken);
        return ResponseEntity.ok(userInfo);
    }

}
