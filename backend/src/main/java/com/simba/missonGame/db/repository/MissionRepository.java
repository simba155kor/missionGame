package com.simba.missonGame.db.repository;

import com.simba.missonGame.db.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionRepository extends JpaRepository<Mission, Long> {


}
