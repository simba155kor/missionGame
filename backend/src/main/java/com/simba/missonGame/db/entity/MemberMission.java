package com.simba.missonGame.db.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@RequiredArgsConstructor
@Table(name="membermission")
public class MemberMission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "kakaomember_no")
    Kakaomember kakaomember;

    @ManyToOne
    @JoinColumn(name = "mission_no")
    Mission mission;

    public MemberMission(Kakaomember kakaomember, Mission mission){
        this.kakaomember = kakaomember;
        this.mission = mission;
    }


}
