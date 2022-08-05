package com.simba.missonGame.dto.member;


import com.simba.missonGame.db.entity.Member;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignupMemberRes {

    Long userNo;
    String id;
    String pwd;

    public SignupMemberRes(Member member) {
        this.userNo = member.getUserNo();
        this.id = member.getId();
        this.pwd = member.getPwd();
    }


}
