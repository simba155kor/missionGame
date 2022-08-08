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

    //jwtFakeToken을 줌.
    @GetMapping("/code")
    public ResponseEntity<String> getAccessTokenUseAuthorizationCode(@RequestParam(value = "auth_code") String authorizationCode, HttpServletResponse res) throws IOException, ParseException, CustomException {
        System.out.println("Auth code : " + authorizationCode);

        //인가 코드 넘겨 받았으니,
        RestTemplate restTemplate = new RestTemplate();

        // 3. header 설정을 위해 HttpHeader 클래스를 생성한 후 HttpEntity 객체에 넣어줍니다.
        HttpHeaders header = new HttpHeaders();

        String restAppKey = "e95cf558fa89b9adf46a58d47cfc4df7";
        String redirect_uri = "http://localhost:8080/oauthloginpage";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("grant_type", "authorization_code");
        params.add("client_id", restAppKey);
        params.add("redirect_uri", redirect_uri);
        params.add("code", authorizationCode);
        //params.put("client_secret", client_secret);

        HttpEntity<MultiValueMap<String, String>> requestData = new HttpEntity<>(params, header);

        // 4. 요청 URL을 정의해줍니다.
        String url = "https://kauth.kakao.com/oauth/token";

        // 5. exchange() 메소드로 api를 호출합니다.
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestData, String.class);

        JSONObject accessTokenRes = jsonService.strToJson(response.getBody());

        String accessToken = accessTokenRes.get("access_token").toString();
        String refreshToken = accessTokenRes.get("refresh_token").toString();

        //회원가입한적 있는지 확인해야함.
        //이를 위해 회원 정보 조회
        Map<String, String> userInfo = kakaoAPIService.getUserInfo(accessTokenRes.get("access_token").toString());

        String nickname = userInfo.get("nickname");
        String profileImage = userInfo.get("profile_image");
        String kakaoId = userInfo.get("kakaoId");

        String jwtFakeToken = null;

        Kakaomember thisKakaoMember = kakaoAPIService.findKakaomemberByKakaoId(kakaoId);
        //새로운 회원이다.
        if(thisKakaoMember == null)
        {
            System.out.println("new user");
            String newJwtFakeToken = kakaoAPIService.createJwtFake(kakaoId, nickname);
            CreateKakaomemReq createKakaomemReq = CreateKakaomemReq.builder().accessToken(accessToken).refreshToken(refreshToken)
                    .nickname(nickname).profileImage(profileImage).kakaoId(kakaoId).jwtFakeToken(newJwtFakeToken)
                    .build();
            kakaoAPIService.createKakaomember(createKakaomemReq);

            jwtFakeToken = newJwtFakeToken;
        }
        else{ // 있는 놈임.
            System.out.println(" hello.");
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
