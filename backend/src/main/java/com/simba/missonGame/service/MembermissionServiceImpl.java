package com.simba.missonGame.service;

import com.simba.missonGame.db.entity.Kakaomember;
import com.simba.missonGame.db.entity.MemberMission;
import com.simba.missonGame.db.entity.Mission;
import com.simba.missonGame.db.repository.KakaomemberRepository;
import com.simba.missonGame.db.repository.MembermissionRepository;
import com.simba.missonGame.db.repository.MissionRepository;
import com.simba.missonGame.dto.membermission.CreateMemberMissionReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MembermissionServiceImpl implements MembermissionService{

    @Autowired
    MembermissionRepository membermissionRepository;

    @Autowired
    MissionRepository missionRepository;

    @Autowired
    KakaomemberRepository kakaomemberRepository;

    public String getMyMission(Long myId){

        Optional<Kakaomember> kakaomember = kakaomemberRepository.findById(myId);

        Optional<MemberMission> memberMission = membermissionRepository.findByKakaomember(kakaomember.get());

        if(!memberMission.isPresent()) return null;
        Long missionNo = memberMission.get().getMission().getMissionNo();

        Mission mission = missionRepository.findByMissionNo(missionNo).get();

        return mission.getMissionContent();
    }

    public MemberMission createMyMission(CreateMemberMissionReq createMemberMissionReq){
        Optional<Kakaomember> kakaomember = kakaomemberRepository.findById(createMemberMissionReq.getKakaomemberNo());
        Optional<Mission> mission = missionRepository.findByMissionNo(createMemberMissionReq.getMissionNo());

        return membermissionRepository.save(new MemberMission(kakaomember.get(), mission.get()));
    }

}
