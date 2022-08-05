package com.simba.missonGame.service;


import com.simba.missonGame.db.entity.Mission;
import com.simba.missonGame.dto.mission.GetOneMissionRes;

public interface MissionService {

    //새로운 질문 뽑기
    public GetOneMissionRes getOneMission();

}
