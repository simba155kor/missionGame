package com.simba.missonGame.service;


import com.simba.missonGame.db.entity.Member;
import com.simba.missonGame.db.repository.MemberRepository;
import com.simba.missonGame.dto.member.LoginMemberReq;
import com.simba.missonGame.dto.member.LoginMemberRes;
import com.simba.missonGame.dto.member.SignupMemberReq;
import com.simba.missonGame.dto.member.SignupMemberRes;
import com.simba.missonGame.exception.CustomException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService{

    @Autowired
    MemberRepository memberRepository;

    public SignupMemberRes signup(SignupMemberReq signupMemberReq){
        Member member = memberRepository.save(new Member(signupMemberReq));

        return new SignupMemberRes(member);
    }


    public LoginMemberRes login(LoginMemberReq loginMemberReq) throws CustomException{
        Optional<Member> member = memberRepository.findById(loginMemberReq.getId());

        return new LoginMemberRes(member.orElseThrow(() -> new CustomException("가입 안된 놈.")));
    }

}
