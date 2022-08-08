package com.simba.missonGame.db.repository;

import com.simba.missonGame.db.entity.Kakaomember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface KakaomemberRepository extends JpaRepository<Kakaomember, Long> {

    Optional<Kakaomember> findByJwtFakeToken(String jwtFakeToken);

    Optional<Kakaomember> findByKakaoId(String kakaoId);

}
