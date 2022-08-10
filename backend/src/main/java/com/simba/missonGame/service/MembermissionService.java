package com.simba.missonGame.service;

import com.simba.missonGame.db.entity.Kakaomember;
import com.simba.missonGame.db.entity.MemberMission;
import com.simba.missonGame.db.entity.Mission;
import com.simba.missonGame.dto.membermission.CreateMemberMissionReq;

import java.util.Optional;

public interface MembermissionService {

    public Mission getMyMission(Long myId);

    public MemberMission createMyMission(CreateMemberMissionReq createMemberMissionReq);
}
