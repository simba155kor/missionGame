package com.simba.missonGame.service;

import com.simba.missonGame.db.entity.Kakaomember;
import com.simba.missonGame.db.repository.KakaomemberRepository;
import com.simba.missonGame.dto.Kakaomember.CreateKakaomemReq;
import com.simba.missonGame.exception.CustomException;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class KakaoAPIService {

    @Autowired
    KakaomemberRepository kakaomemberRepository;
    @Autowired
    JsonService jsonService;

    //DB에 없을 시 새로 가입시킴.
    //닉네임, 프로필 이미지, 엑세스토큰, 리프레시토큰, jwtFake토큰, 카카오아이디 저장함.
    public Kakaomember createKakaomember(CreateKakaomemReq createKakaomemReq){
        return kakaomemberRepository.save(new Kakaomember(createKakaomemReq));
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

    //카카오 ID로 DB에 조회해서 있는지 확인
    public Kakaomember findKakaomemberByKakaoId(String kakaoId) throws CustomException {
        //return kakaomemberRepository.findByJwtFakeToken(jwtFakeToken).orElseThrow(() -> new CustomException("멤버 조회중 오류."));
        return kakaomemberRepository.findByKakaoId(kakaoId).orElse(null);
    }

//    public Kakaomember findKakaomemberByJwtFakeToken(String jwtFakeToken) throws CustomException {
//        //return kakaomemberRepository.findByJwtFakeToken(jwtFakeToken).orElseThrow(() -> new CustomException("멤버 조회중 오류."));
//        return kakaomemberRepository.findByJwtFakeToken(jwtFakeToken).orElse(null);
//    }

    public String createJwtFake(String kakaoId, String nickname){
        return kakaoId + nickname;
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

        JSONObject jsonObj = jsonService.strToJson(response.getBody());

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

        return resultMap;
    }


}
