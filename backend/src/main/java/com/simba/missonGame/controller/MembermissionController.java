package com.simba.missonGame.controller;

import com.simba.missonGame.db.entity.Mission;
import com.simba.missonGame.dto.membermission.CreateMemberMissionReq;
import com.simba.missonGame.service.MembermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/membermission")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class MembermissionController {

    @Autowired
    MembermissionService membermissionService;

    @GetMapping("/{myId}")
    ResponseEntity<?> getMyMission(@PathVariable(value = "myId") Long myId){

        Mission missionContent = membermissionService.getMyMission(myId);

        return ResponseEntity.ok(missionContent);
    }

    @PostMapping("/createmission")
    ResponseEntity<?> createMyMission(@RequestBody CreateMemberMissionReq createMemberMissionReq){
        return ResponseEntity.ok(membermissionService.createMyMission(createMemberMissionReq));
    }

}
