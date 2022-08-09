package com.simba.missonGame.db.entity;

import com.simba.missonGame.dto.Predict.SetPredictReq;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Getter
@RequiredArgsConstructor
@Table(name="predict")
//@IdClass(Predict.class)
public class Predict implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    //@Id
    @Column(name = "my_member_no")
    Long myMemberNo;

    //@Id
    @Column(name = "his_member_no")
    Long hisMemberNo;

    @Column(name = "predict_content")
    String predictContent;

    public Predict(SetPredictReq setPredictReq){
        this.myMemberNo = setPredictReq.getMyMemberNo();
        this.hisMemberNo = setPredictReq.getHisMemberNo();
        this.predictContent = setPredictReq.getPredictContent();
    }

    public void updatePredict(SetPredictReq setPredictReq){
        this.myMemberNo = setPredictReq.getMyMemberNo();
        this.hisMemberNo = setPredictReq.getHisMemberNo();
        this.predictContent = setPredictReq.getPredictContent();
    }

    public void updateContent(String predictContent){
        this.predictContent = predictContent;
    }

}
