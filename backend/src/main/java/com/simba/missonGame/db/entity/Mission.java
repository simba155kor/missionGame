package com.simba.missonGame.db.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@RequiredArgsConstructor
@Table(name="mission")
public class Mission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mission_no")
    Long missionNo;

    @Column(name = "mission_name")
    String missionName;

    @Column(name="mission_content")
    String missionContent;

    @Column(name= "mission_point")
    Integer missionPoint;

    @Column(name= "mission_level")
    Integer missionLevel;

}
