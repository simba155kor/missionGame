package com.simba.missonGame.db.entity;

import com.simba.missonGame.dto.Kakaomember.CreateKakaomemReq;
import com.simba.missonGame.dto.member.SignupMemberReq;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@RequiredArgsConstructor
@Table(name="kakaomember")
public class Kakaomember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "nickname")
    String nickname;

    @Column(name = "profile_image")
    String profileImage;

    @Column(name = "access_token")
    String accessToken;

    @Column(name = "refresh_token")
    String refreshToken;

    @Column(name = "jwt_fake_token")
    String jwtFakeToken;

    @Column(name= "kakao_id")
    String kakaoId;


    public Kakaomember(CreateKakaomemReq createKakaomemReq){
        this.nickname = createKakaomemReq.getNickname();
        this.profileImage = createKakaomemReq.getProfileImage();
        this.accessToken = createKakaomemReq.getAccessToken();
        this.refreshToken = createKakaomemReq.getRefreshToken();
        this.jwtFakeToken = createKakaomemReq.getJwtFakeToken();
        this.kakaoId = createKakaomemReq.getKakaoId();
    }

}
