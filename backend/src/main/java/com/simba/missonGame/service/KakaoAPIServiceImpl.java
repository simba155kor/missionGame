package com.simba.missonGame.service;

import com.simba.missonGame.db.entity.Kakaomember;
import com.simba.missonGame.db.repository.KakaomemberRepository;
import com.simba.missonGame.dto.Kakaomember.CreateKakaomemReq;
import com.simba.missonGame.exception.CustomException;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class KakaoAPIServiceImpl implements KakaoAPIService{

    @Autowired
    KakaomemberRepository kakaomemberRepository;
    @Autowired
    JsonService jsonServiceImpl;



    public String getAccessTokenByAccessTokenRes(JSONObject accessTokenRes){
        return accessTokenRes.get("access_token").toString();
    }

    public String getRefreshTokenByAccessTokenRes(JSONObject accessTokenRes){
        return accessTokenRes.get("refresh_token").toString();
    }


    ///////////////////////////////////////////////////////////////////
    //내 서비스의 DB 이용하는 것
    ///////////////////////////////////////////////////////////////////

    //요청받은 Access Token으로 카카오 요청보내 회원정보 받고 이미 가입되어있는지 확인
    public Kakaomember isKakaoMemberByAccessToken(JSONObject accessTokenRes) throws ParseException, CustomException {
        Map<String, String> userInfo = getUserInfo(accessTokenRes.get("access_token").toString());
        String kakaoId = userInfo.get("kakaoId");

        return findKakaomemberByKakaoId(kakaoId);
    }

    //DB에 없을 시 새로 가입시킴.
    //닉네임, 프로필 이미지, 엑세스토큰, 리프레시토큰, jwtFake토큰, 카카오아이디 저장함.
    public String createKakaomember(JSONObject accessTokenRes) throws ParseException {

        Map<String, String> userInfo = getUserInfo(accessTokenRes.get("access_token").toString());

        String accessToken = getAccessTokenByAccessTokenRes(accessTokenRes);
        String refreshToken = getRefreshTokenByAccessTokenRes(accessTokenRes);

        String nickname = userInfo.get("nickname");
        String profileImage = userInfo.get("profile_image");
        String kakaoId = userInfo.get("kakaoId");

        String newJwtFakeToken = createJwtFake(kakaoId, nickname);
        CreateKakaomemReq createKakaomemReq = CreateKakaomemReq.builder().accessToken(accessToken).refreshToken(refreshToken)
                .nickname(nickname).profileImage(profileImage).kakaoId(kakaoId).jwtFakeToken(newJwtFakeToken)
                .build();

        kakaomemberRepository.save(new Kakaomember(createKakaomemReq));

        return newJwtFakeToken;
    }

    //jwtFakeToken으로 DB에 저장된 access_token 바꿔줌.
    public String convertJwtFakeTokenToAccessToken(String jwtFakeToken){
        Optional<Kakaomember> kakaomember = kakaomemberRepository.findByJwtFakeToken(jwtFakeToken);

        String accessToken = null;
        if(kakaomember.isPresent()){
            accessToken = kakaomember.get().getAccessToken();
        }

        return accessToken;
//        return kakaomemberRepository.findByJwtFakeToken(jwtFakeToken).get().getAccessToken().orel
    }

    //jwtFakeToken으로 DB에 저장된 kakaoId 로 바꿔줌.
    public String convertJwtFakeTokenTokakaoId(String jwtFakeToken){
        Optional<Kakaomember> kakaomember = kakaomemberRepository.findByJwtFakeToken(jwtFakeToken);

        String kakaoId = null;
        if(kakaomember.isPresent()){
            kakaoId = kakaomember.get().getKakaoId();
        }

        return kakaoId;
//        return kakaomemberRepository.findByJwtFakeToken(jwtFakeToken).get().getAccessToken().orel
    }

    //카카오 ID로 DB에 조회해서 있는지 확인
    public Kakaomember findKakaomemberByKakaoId(String kakaoId) throws CustomException {
        //return kakaomemberRepository.findByJwtFakeToken(jwtFakeToken).orElseThrow(() -> new CustomException("멤버 조회중 오류."));
        return kakaomemberRepository.findByKakaoId(kakaoId).orElse(null);
    }





    ////////////////////////////////////////////////////////////////////////////
    //카카오 API 사용하는 것.
    ////////////////////////////////////////////////////////////////////////////


    //인가 코드 받아서 Access Token 요청하기.
    //accessToken, refreshToken이 담긴 Json객체로 리턴
    public JSONObject convertAuthorizationCodeToAccessToken(String authorizationCode) throws ParseException {
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

        JSONObject accessTokenRes = jsonServiceImpl.strToJson(response.getBody());

        return accessTokenRes;
    }

    //RestTemplate으로 카카오 회원 정보 요청 API 사용.
    //DB에 있는 accessToken 사용함.
    public Map<String, String> getUserInfo(String accessToken) throws ParseException {

        //인가 코드 넘겨 받았으니,
        RestTemplate restTemplate = new RestTemplate();

        // 3. header 설정을 위해 HttpHeader 클래스를 생성한 후 HttpEntity 객체에 넣어줍니다.
        HttpHeaders header = new HttpHeaders();
        String token = "Bearer " + accessToken;
        header.set("Authorization", token);

        HttpEntity<MultiValueMap<String, String>> requestData = new HttpEntity<>(header);

        // 4. 요청 URL을 정의해줍니다.
        String url = "https://kapi.kakao.com/v2/user/me";

        // 5. exchange() 메소드로 api를 호출합니다.
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestData, String.class);
        System.out.println(response.toString());
        System.out.println(response.getBody());

        Map<String, String> resultMap = new HashMap<>();

        JSONObject jsonObj = jsonServiceImpl.strToJson(response.getBody());

        System.out.println("!!");
        System.out.println(jsonObj.get("id"));
        Long kakaoIdLong = (Long) jsonObj.get("id");
        String kakaoId = kakaoIdLong.toString();
        resultMap.put("kakaoId", kakaoId);

        System.out.println(jsonObj.get("properties"));
        System.out.println(jsonObj.get("properties").getClass());
        JSONObject jsonObjProperties = (JSONObject) jsonObj.get("properties");
        String nickname = jsonObjProperties.get("nickname").toString();
        String profile_image = jsonObjProperties.get("profile_image").toString();

        resultMap.put("nickname", nickname);
        resultMap.put("profile_image", profile_image);

        //멤버의 DB 번호 조회
        Kakaomember kakaomember = kakaomemberRepository.findByKakaoId(kakaoId).get();
        resultMap.put("memberNo", kakaomember.getId().toString());


        return resultMap;
    }



    ////////////////////////////////////////////////////////////////////////////
    //accessToken 만료되었는지 확인, 만료되었다면 재발급 받기
    ////////////////////////////////////////////////////////////////////////////

    //카카오 api 사용하기 전에 반드시 해야함.
    //사용자에게 있는 jwtFakeToken으로 kakaoId 조회.
    //kakaoId로 accessToken 만료되었는지 확인후, 만료되었다면 새로 발급받아 db에 저장
    public void checkAccessTokenExpiredAndRenew(String jwtFakeToken) throws ParseException {
        String kakaoId = convertJwtFakeTokenTokakaoId(jwtFakeToken);

        Kakaomember kakaomember = kakaomemberRepository.findByKakaoId(kakaoId).get();

        if(!isExpiredAccessToken(kakaomember)){
            System.out.println("Expired. renew access_token!");
            renewAccessToken(kakaomember);
        }
    }

    //accessToken 만료되었다면 false 리턴, 만료 안되었으면 true 리턴
    public boolean isExpiredAccessToken(Kakaomember kakaomember){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders header = new HttpHeaders();

        HttpEntity<MultiValueMap<String, String>> requestData = new HttpEntity<>(header);

        String token = "Bearer " + kakaomember.getAccessToken();
        header.set("Authorization", token);

        String url = "https://kapi.kakao.com/v1/user/access_token_info";

        try{
            ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestData, String.class);
        }
        catch (HttpClientErrorException e){
            //        if(response.getStatusCode() == HttpStatus.UNAUTHORIZED)
            return false;
        }

        return true;
    }

    //Refresh token 이용해서 만료된 access token 새로 발급 받기
    //새로 발급 받고 db에 저장.
    public void renewAccessToken(Kakaomember prevKakaomember) throws ParseException {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders header = new HttpHeaders();

        String restAppKey = "e95cf558fa89b9adf46a58d47cfc4df7";

        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
        params.add("grant_type", "refresh_token");
        params.add("client_id", restAppKey);
        params.add("refresh_token", prevKakaomember.getRefreshToken());
        //params.put("client_secret", client_secret);

        HttpEntity<MultiValueMap<String, String>> requestData = new HttpEntity<>(params, header);

        String url = "https://kauth.kakao.com/oauth/token";

        // 5. exchange() 메소드로 api를 호출합니다.
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestData, String.class);

        JSONObject accessTokenRes = jsonServiceImpl.strToJson(response.getBody());

        System.out.println(accessTokenRes.toString());

        String accessToken = accessTokenRes.get("access_token").toString();
        String refreshToken = null;
        if(accessTokenRes.get("refresh_token") != null) refreshToken = accessTokenRes.get("refresh_token").toString();

        Kakaomember kakaomember = kakaomemberRepository.findByKakaoId(prevKakaomember.getKakaoId()).get();
        kakaomember.renewAccessToken(accessToken, refreshToken);
        kakaomemberRepository.save(kakaomember);

    }



    ////////////////////////////////////////////////////////////////////////////
    //기타
    ////////////////////////////////////////////////////////////////////////////

    public String createJwtFake(String kakaoId, String nickname){
        return kakaoId + nickname;
    }
}
