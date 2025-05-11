package com.senok.user.adapter.out.persistence;

import com.senok.user.adapter.out.persistence.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberPersistenceAdapter {

    private final MemberRepository memberRepository;
}
