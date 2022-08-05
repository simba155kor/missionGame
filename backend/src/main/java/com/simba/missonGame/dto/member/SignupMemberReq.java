package com.simba.missonGame.dto.member;


import com.simba.missonGame.db.entity.Member;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SignupMemberReq {

    String id;
    String pwd;

}
