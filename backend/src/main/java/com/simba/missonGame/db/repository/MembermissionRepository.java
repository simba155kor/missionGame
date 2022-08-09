package com.simba.missonGame.db.repository;

import com.simba.missonGame.db.entity.Kakaomember;
import com.simba.missonGame.db.entity.MemberMission;
import com.simba.missonGame.db.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MembermissionRepository extends JpaRepository<MemberMission, Long> {

    Optional<MemberMission> findByKakaomember(Kakaomember kakaomember);

}
