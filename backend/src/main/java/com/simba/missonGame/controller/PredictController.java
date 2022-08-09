package com.simba.missonGame.controller;

import com.simba.missonGame.db.repository.PredictRepository;
import com.simba.missonGame.dto.Predict.SetPredictReq;
import com.simba.missonGame.service.PredictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/predict")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PredictController {

    @Autowired
    PredictService predictService;

    @GetMapping("/{myId}/{yourId}")
    ResponseEntity<?> readMyPredictForYou(@PathVariable(value = "myId") Long myId, @PathVariable(value="yourId") Long yourId){

        System.out.println(myId + yourId);

        return ResponseEntity.ok(predictService.getPredict(myId, yourId));
    }

    @PostMapping("/setpredict")
    ResponseEntity<?> setPredictForYou(@RequestBody SetPredictReq setPredictReq){
        return ResponseEntity.ok(predictService.setPredict(setPredictReq));
    }

}
