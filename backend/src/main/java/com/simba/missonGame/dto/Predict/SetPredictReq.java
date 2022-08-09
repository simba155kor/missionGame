package com.simba.missonGame.dto.Predict;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SetPredictReq {
    Long myMemberNo;

    Long hisMemberNo;

    String predictContent;

}
