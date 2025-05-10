package com.senok.user.adapter.out.persistence.repository;

import com.senok.user.adapter.out.persistence.entity.MemberEntity;
import org.springframework.data.repository.Repository;

import java.util.Optional;

public interface MemberRepository extends Repository<MemberEntity, Integer> {

    // 조인으로 가져오자
    Optional<MemberEntity> findByEmail(String email);

    MemberEntity save(MemberEntity memberEntity);
}
