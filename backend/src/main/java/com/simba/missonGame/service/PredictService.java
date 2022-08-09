package com.simba.missonGame.service;


import com.simba.missonGame.db.entity.Predict;
import com.simba.missonGame.dto.Predict.SetPredictReq;
import com.simba.missonGame.dto.mission.GetOneMissionRes;

public interface PredictService {

    public Predict getPredict(Long myId, Long hisId);

    public Predict setPredict(SetPredictReq setPredictReq);


}
