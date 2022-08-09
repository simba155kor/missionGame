package com.simba.missonGame.db.repository;

import com.simba.missonGame.db.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MissionRepository extends JpaRepository<Mission, Long> {

    public Optional<Mission> findByMissionNo(Long missionNo);
}
