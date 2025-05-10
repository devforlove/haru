package com.senok.user.config.auth;

import com.senok.user.adapter.out.persistence.entity.MemberEntity;
import com.senok.user.adapter.out.persistence.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Transactional
    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        // 1. accessToken 으로 유저 정보(attributes) 가져오기
        Map<String, Object> oAuth2UserAttributes = super.loadUser(userRequest).getAttributes();

        // 2. resistrationId 가져오기 (third-party id)
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        // 3. userNameAttributeName 가져오기
        String userNameAttributeName = userRequest.getClientRegistration().getProviderDetails()
                .getUserInfoEndpoint().getUserNameAttributeName();

        // 4. 유저 정보 dto 생성
        OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfo.of(registrationId, oAuth2UserAttributes);

        // 5. 회원가입 및 로그인
        MemberEntity member = getOrSave(oAuth2UserInfo);

        List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList();
        // 6. OAuth2User로 반환
        return new DefaultOAuth2User(authorityList, oAuth2UserAttributes, userNameAttributeName);
    }

    private MemberEntity getOrSave(OAuth2UserInfo oAuth2UserInfo) {
        return memberRepository.findByEmail(oAuth2UserInfo.email())
                .orElseGet(() -> {
                    MemberEntity memberEntity = oAuth2UserInfo.toEntity();
                    return memberRepository.save(memberEntity);
                });
    }
}
