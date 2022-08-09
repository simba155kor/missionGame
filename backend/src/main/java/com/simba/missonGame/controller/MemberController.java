package com.simba.missonGame.controller;

import com.simba.missonGame.dto.member.LoginMemberReq;
import com.simba.missonGame.dto.member.LoginMemberRes;
import com.simba.missonGame.dto.member.SignupMemberReq;
import com.simba.missonGame.dto.member.SignupMemberRes;
import com.simba.missonGame.exception.CustomException;
import com.simba.missonGame.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URI;
import java.util.Map;

@RestController
@RequestMapping("/member")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MemberController {

    @Autowired
    MemberService memberService;
    @PostMapping("/signup")
    ResponseEntity<SignupMemberRes> signup(@RequestBody SignupMemberReq signupMemberReq) throws CustomException{
        SignupMemberRes signupMemberRes = memberService.signup(signupMemberReq);
        return ResponseEntity.ok(signupMemberRes);
    }

    @PostMapping("/login")
    ResponseEntity<LoginMemberRes> login(@RequestBody LoginMemberReq loginMemberReq) throws CustomException {
        LoginMemberRes loginMemberRes = memberService.login(loginMemberReq);
        return ResponseEntity.ok(loginMemberRes);
    }

    //https://m.blog.naver.com/fbfbf1/222682991444
    //https://shanepark.tistory.com/370
//    @GetMapping("/redi2")
//    ResponseEntity<?> redierct(){
//        HttpHeaders headers = new HttpHeaders();
//        headers.setLocation(URI.create("http://me.go.kr"));
//        //headers.set("Access-Control-Allow-Origin","*");
//        headers.set("Access-Control-Allow-Methods","OPTIONS, POST, GET");
//        System.out.println(headers.toString());
//
//        return new ResponseEntity<>(headers, HttpStatus.FOUND);
//    }
//
//    @GetMapping("/redi")
//    public void oauthLogin2(HttpServletResponse response) throws IOException {
//        String redirect_uri="http://me.go.kr";
//        response.setHeader("Access-Control-Allow-Origin","*");
//        response.setHeader("Access-Control-Allow-Methods","OPTIONS, POST, GET");
//        response.sendRedirect(redirect_uri);
//    }
//
//    @GetMapping("/redi3")
//    public ResponseEntity<?> oauthLogin3(HttpServletResponse response) throws IOException {
////        String redirect_uri="http://localhost:8080/choice";
////        response.setHeader("Access-Control-Allow-Origin","*");
////        response.setHeader("Access-Control-Allow-Methods","OPTIONS, POST, GET");
////        response.sendRedirect(redirect_uri);
//        HttpHeaders headers = new HttpHeaders();
//        headers.set("custom", "custom");
//        headers.setLocation(URI.create("http://localhost:8080/choice"));
//        //headers.set("Access-Control-Allow-Origin","*");
////        headers.set("Access-Control-Allow-Methods","OPTIONS, POST, GET");
//        System.out.println(headers.toString());
//
//        return new ResponseEntity<>(headers, HttpStatus.OK);
//    }

//
//    @GetMapping("/oauthlogin")
//    ResponseEntity<?> oauthlogin(){
//
//        String url = "https://kauth.kakao.com/oauth/authorize";
//        UriComponents uri = UriComponentsBuilder.fromHttpUrl(url).queryParam("client_id", "ecde35cd36b20c027a0aa72422bdc288")
//                .queryParam("redirect_uri","http://localhost:8080/choice").queryParam("response_type", "code").build(false);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.setLocation(URI.create(uri.toString()));
////        headers.setAccessControlAllowOrigin("*abc");
////        System.out.println(headers.toString());
////        if(!headers.containsKey("Access-Control-Allow-Origin")){
////
////            headers.set("Access-Control-Allow-Origin","*");
////        }
////        System.out.println(headers.toString());
//        return new ResponseEntity<>(headers, HttpStatus.FOUND);
//
//
////        RestTemplate restTemplate = new RestTemplate();
////
////        // 3. header 설정을 위해 HttpHeader 클래스를 생성한 후 HttpEntity 객체에 넣어줍니다.
////        HttpHeaders header = new HttpHeaders();
////        HttpEntity<String> entity = new HttpEntity<String>(header);
////
////        // 4. 요청 URL을 정의해줍니다.
////        String url = "https://kauth.kakao.com/oauth/authorize";
////        UriComponents uri = UriComponentsBuilder.fromHttpUrl(url).queryParam("client_id", "ecde35cd36b20c027a0aa72422bdc288")
////                .queryParam("redirect_uri","http://localhost:8080/about").queryParam("response_type", "code").build(false);
////
////        // 5. exchange() 메소드로 api를 호출합니다.
////        ResponseEntity<String> response = restTemplate.exchange(uri.toString(), HttpMethod.GET, entity, String.class);
////
//////        System.out.println(response.toString());
//////
////        return response;
//    }


}
