package com.simba.missonGame.service;

import com.simba.missonGame.db.entity.Mission;
import com.simba.missonGame.db.repository.MissionRepository;
import com.simba.missonGame.dto.mission.GetOneMissionRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
public class MissionServiceImpl implements MissionService{

    @Autowired
    MissionRepository missionRepository;

    public GetOneMissionRes getOneMission(){
        List<Mission> missionList = missionRepository.findAll();

        int len = missionList.size();
        Random rand = new Random();

        int target = rand.nextInt(len);

        return new GetOneMissionRes(missionList.get(target));
    }

}
