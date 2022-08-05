package com.simba.missonGame.dto.member;


import com.simba.missonGame.db.entity.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class LoginMemberRes {

    String id;
    String pwd;

    public LoginMemberRes(Member member){
        this.id = member.getId();
        this.pwd = member.getPwd();
    }

}
