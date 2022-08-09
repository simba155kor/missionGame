package com.simba.missonGame.service;

import com.simba.missonGame.db.entity.Kakaomember;
import com.simba.missonGame.dto.Kakaomember.CreateKakaomemReq;
import com.simba.missonGame.exception.CustomException;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.ParseException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public interface KakaoAPIService {
    public String getAccessTokenByAccessTokenRes(JSONObject accessTokenRes);

    public String getRefreshTokenByAccessTokenRes(JSONObject accessTokenRes);


    ///////////////////////////////////////////////////////////////////
    //내 서비스의 DB 이용하는 것
    ///////////////////////////////////////////////////////////////////

    //요청받은 Access Token으로 카카오 요청보내 회원정보 받고 이미 가입되어있는지 확인
    public Kakaomember isKakaoMemberByAccessToken(JSONObject accessTokenRes) throws ParseException, CustomException;
    //DB에 없을 시 새로 가입시킴.
    //닉네임, 프로필 이미지, 엑세스토큰, 리프레시토큰, jwtFake토큰, 카카오아이디 저장함.
    public String createKakaomember(JSONObject accessTokenRes) throws ParseException;

    //jwtFakeToken으로 DB에 저장된 access_token 바꿔줌.
    public String convertJwtFakeTokenToAccessToken(String jwtFakeToken);

    //jwtFakeToken으로 DB에 저장된 kakaoId 로 바꿔줌.
    public String convertJwtFakeTokenTokakaoId(String jwtFakeToken);

    //카카오 ID로 DB에 조회해서 있는지 확인
    public Kakaomember findKakaomemberByKakaoId(String kakaoId) throws CustomException;





    ////////////////////////////////////////////////////////////////////////////
    //카카오 API 사용하는 것.
    ////////////////////////////////////////////////////////////////////////////


    //인가 코드 받아서 Access Token 요청하기.
    //accessToken, refreshToken이 담긴 Json객체로 리턴
    public JSONObject convertAuthorizationCodeToAccessToken(String authorizationCode) throws ParseException;

    //RestTemplate으로 카카오 회원 정보 요청 API 사용.
    //DB에 있는 accessToken 사용함.
    public Map<String, String> getUserInfo(String accessToken) throws ParseException;



    ////////////////////////////////////////////////////////////////////////////
    //accessToken 만료되었는지 확인, 만료되었다면 재발급 받기
    ////////////////////////////////////////////////////////////////////////////

    //카카오 api 사용하기 전에 반드시 해야함.
    //사용자에게 있는 jwtFakeToken으로 kakaoId 조회.
    //kakaoId로 accessToken 만료되었는지 확인후, 만료되었다면 새로 발급받아 db에 저장
    public void checkAccessTokenExpiredAndRenew(String jwtFakeToken) throws ParseException;

    //accessToken 만료되었다면 false 리턴, 만료 안되었으면 true 리턴
    public boolean isExpiredAccessToken(Kakaomember kakaomember);

    //Refresh token 이용해서 만료된 access token 새로 발급 받기
    //새로 발급 받고 db에 저장.
    public void renewAccessToken(Kakaomember prevKakaomember) throws ParseException;



    ////////////////////////////////////////////////////////////////////////////
    //기타
    ////////////////////////////////////////////////////////////////////////////
    public String createJwtFake(String kakaoId, String nickname);
}
