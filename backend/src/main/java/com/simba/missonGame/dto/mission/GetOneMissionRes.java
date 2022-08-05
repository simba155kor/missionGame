package com.simba.missonGame.dto.mission;


import com.simba.missonGame.db.entity.Mission;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@RequiredArgsConstructor
public class GetOneMissionRes {

    Long missionNo;

    String missionName;

    String missionContent;

    Integer missionPoint;

    Integer missionLevel;

    public GetOneMissionRes(Mission mission){
        this.missionNo = mission.getMissionNo();
        this.missionName = mission.getMissionName();
        this.missionContent = mission.getMissionContent();
        this.missionPoint = mission.getMissionPoint();
        this.missionLevel = mission.getMissionLevel();
    }
}
