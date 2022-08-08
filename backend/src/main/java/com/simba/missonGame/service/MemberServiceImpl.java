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

    public SignupMemberRes signup(SignupMemberReq signupMemberReq) throws CustomException{
        Optional<Member> member = memberRepository.findById(signupMemberReq.getId());

        if(member.isPresent()) throw new CustomException("이미 가입 된 놈.");

        return new SignupMemberRes(memberRepository.save(new Member(signupMemberReq)));
    }


    public LoginMemberRes login(LoginMemberReq loginMemberReq) throws CustomException{
        System.out.println(loginMemberReq.toString());
        Optional<Member> member = memberRepository.findById(loginMemberReq.getId());

        if(!member.isPresent()) throw new CustomException("가입 안 된 놈.");

        Member memberObj = member.get();

        if(memberObj.getPwd().equals(loginMemberReq.getPwd())) return new LoginMemberRes(memberObj);
        else throw new CustomException("비밀번호 불일치.");
    }


}
