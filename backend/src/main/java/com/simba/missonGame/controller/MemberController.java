package com.simba.missonGame.controller;

import com.simba.missonGame.dto.member.LoginMemberReq;
import com.simba.missonGame.dto.member.LoginMemberRes;
import com.simba.missonGame.dto.member.SignupMemberReq;
import com.simba.missonGame.dto.member.SignupMemberRes;
import com.simba.missonGame.exception.CustomException;
import com.simba.missonGame.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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


}
