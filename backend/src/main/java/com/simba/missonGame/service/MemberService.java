package com.simba.missonGame.service;

import com.simba.missonGame.dto.member.LoginMemberReq;
import com.simba.missonGame.dto.member.LoginMemberRes;
import com.simba.missonGame.dto.member.SignupMemberReq;
import com.simba.missonGame.dto.member.SignupMemberRes;
import com.simba.missonGame.exception.CustomException;
import org.springframework.stereotype.Service;

public interface MemberService {

    SignupMemberRes signup(SignupMemberReq signupMemberReq) throws CustomException;
    LoginMemberRes login(LoginMemberReq loginMemberReq) throws CustomException;

}
