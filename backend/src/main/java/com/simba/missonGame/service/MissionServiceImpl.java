package com.simba.missonGame.service;

import com.simba.missonGame.db.entity.Mission;
import com.simba.missonGame.db.repository.MissionRepository;
import com.simba.missonGame.dto.mission.GetOneMissionRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MissionServiceImpl implements MissionService{

    @Autowired
    MissionRepository missionRepository;

    public GetOneMissionRes getOneMission(){
        List<Mission> missionList = missionRepository.findAll();

        return new GetOneMissionRes(missionList.get(0));
    }

}
