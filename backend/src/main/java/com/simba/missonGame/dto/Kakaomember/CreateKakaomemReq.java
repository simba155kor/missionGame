package com.simba.missonGame.dto.Kakaomember;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Builder
public class CreateKakaomemReq {

    String nickname;
    String profileImage;
    String accessToken;
    String refreshToken;
    String jwtFakeToken;
    String kakaoId;

    CreateKakaomemReq(CreateKakaomemReqBuilder builder){
        this.nickname = builder.nickname;
        this.profileImage = builder.profileImage;
        this.accessToken = builder.accessToken;
        this.refreshToken = builder.refreshToken;
        this.jwtFakeToken = builder.jwtFakeToken;
        this.kakaoId = builder.kakaoId;
    }
}
