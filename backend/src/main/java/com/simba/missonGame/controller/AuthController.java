package com.simba.missonGame.controller;

import com.simba.missonGame.db.entity.Kakaomember;
import com.simba.missonGame.dto.Kakaomember.CreateKakaomemReq;
import com.simba.missonGame.exception.CustomException;
import com.simba.missonGame.service.JsonService;
import com.simba.missonGame.service.KakaoAPIService;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AuthController {

    @Autowired
    KakaoAPIService kakaoAPIService;

    @Autowired
    JsonService jsonService;

    //인가 코드를 받아서 access token을 요청함.
    //access token 요청하고 이를 이용해 회원 정보를 요청함.
    //이미 가입된 회원인지, 새로운 회원인지 확인 후 jwtFakeToken을 반환함.
    @GetMapping("/code")
    public ResponseEntity<String> getAccessTokenUseAuthorizationCode(@RequestParam(value = "auth_code") String authorizationCode) throws ParseException, CustomException {
        System.out.println("Auth code : " + authorizationCode);

        //인가 코드로 accessTokenRes 받기
        JSONObject accessTokenRes = kakaoAPIService.convertAuthorizationCodeToAccessToken(authorizationCode);

        //accessTokenRes로 이미 가입된 회원인지 확인
        Kakaomember thisKakaoMember = kakaoAPIService.isKakaoMemberByAccessToken(accessTokenRes);

        String jwtFakeToken = null;

        //새로운 회원이다.
        if(thisKakaoMember == null)
        {
            System.out.println("new user");
            String newJwtFakeToken = kakaoAPIService.createKakaomember(accessTokenRes);

            jwtFakeToken = newJwtFakeToken;
        }
        else{ // 있는 놈임.
            System.out.println(" hello. prev user.");
            jwtFakeToken = thisKakaoMember.getJwtFakeToken();
        }

        //로그인 성공했으니 토큰을 생성해 주자.
        //jwt 토큰으로 해야겠지만. 임시용으로 가볍게 만들어서 하자.

        return ResponseEntity.ok(jwtFakeToken);

        //accessToken 얻었으니 저장하고 클라이언트만 리다이렉션 해주면 된다.


//        HttpHeaders redirectHeaders = new HttpHeaders();
//        //redirectHeaders.set("custom", "custom");
//        redirectHeaders.setLocation(URI.create("http://localhost:8080/"));
//        redirectHeaders.set("Access-Control-Allow-Origin","*");
////        headers.set("Access-Control-Allow-Methods","OPTIONS, POST, GET");
//
//        res.setHeader("Access-Control-Allow-Origin","*");
//        res.setHeader("ff", "fff");
//        res.sendRedirect("http://localhost:8080");

        //return new ResponseEntity<>(redirectHeaders, HttpStatus.FOUND);

        //return ResponseEntity.ok("oh");
    }

}
