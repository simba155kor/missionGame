package com.simba.missonGame.service;


import com.simba.missonGame.db.entity.Predict;
import com.simba.missonGame.dto.Predict.SetPredictReq;
import com.simba.missonGame.dto.mission.GetOneMissionRes;

import java.util.List;

public interface PredictService {

    public Predict getPredict(Long myId, Long hisId);

    public List<Predict> getPredict(Long myId);

    public Predict setPredict(SetPredictReq setPredictReq);


}
