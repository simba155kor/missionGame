package com.simba.missonGame.db.repository;

import com.simba.missonGame.db.entity.Kakaomember;
import com.simba.missonGame.db.entity.MemberMission;
import com.simba.missonGame.db.entity.Predict;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PredictRepository extends JpaRepository<Predict, Long> {

    Optional<Predict> findByMyMemberNoAndHisMemberNo(Long myId, Long hisId);

    Optional<List<Predict>> findByMyMemberNo(Long myId);
}
