package com.simba.missonGame.db.entity;

import com.simba.missonGame.dto.member.SignupMemberReq;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@RequiredArgsConstructor
@Table(name = "member")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_no")
    private Long userNo;

    @Column(name = "id")
    String id;

    @Column(name = "pwd")
    String pwd;

    public Member(SignupMemberReq signupMemberReq){
        this.id = signupMemberReq.getId();
        this.pwd = signupMemberReq.getPwd();
    }

}
