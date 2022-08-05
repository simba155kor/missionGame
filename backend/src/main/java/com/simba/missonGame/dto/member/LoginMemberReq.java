package com.simba.missonGame.dto.member;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class LoginMemberReq {

    String id;
    String pwd;

    @Override
    public String toString() {
        return "LoginMemberReq{" +
                "id='" + id + '\'' +
                ", pwd='" + pwd + '\'' +
                '}';
    }
}
