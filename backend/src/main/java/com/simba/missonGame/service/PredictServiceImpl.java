package com.simba.missonGame.service;

import com.simba.missonGame.db.entity.Mission;
import com.simba.missonGame.db.entity.Predict;
import com.simba.missonGame.db.repository.MissionRepository;
import com.simba.missonGame.db.repository.PredictRepository;
import com.simba.missonGame.dto.Predict.SetPredictReq;
import com.simba.missonGame.dto.mission.GetOneMissionRes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PredictServiceImpl implements PredictService{

    @Autowired
    PredictRepository predictRepository;

    public Predict getPredict(Long myId, Long hisId){
       return predictRepository.findByMyMemberNoAndHisMemberNo(myId, hisId).orElse(null);
    }

    public List<Predict> getPredict(Long myId){
        return predictRepository.findByMyMemberNo(myId).orElse(null);
    }
    public Predict setPredict(SetPredictReq setPredictReq){
       Predict predict = getPredict(setPredictReq.getMyMemberNo(), setPredictReq.getHisMemberNo());
        if(predict == null) return predictRepository.save(new Predict(setPredictReq));
        else {
            predict.updatePredict(setPredictReq);
            return predictRepository.save(predict);
        }
    }

}
