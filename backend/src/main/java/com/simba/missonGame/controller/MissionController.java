package com.simba.missonGame.controller;

import com.simba.missonGame.dto.mission.GetOneMissionRes;
import com.simba.missonGame.service.MissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mission")
public class MissionController {

    @Autowired
    MissionService missionService;

    @GetMapping("/choiceone")
    ResponseEntity<GetOneMissionRes> getOneMission(){
        return ResponseEntity.ok(missionService.getOneMission());
    }

}
