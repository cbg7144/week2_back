package jpadb.demo.service;


import jakarta.transaction.Transactional;
import jpadb.demo.dto.KakaoResponseInfo;
import jpadb.demo.dto.KakaoUserInfo;

import jpadb.demo.entity.Member;
import jpadb.demo.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberService {
    private final WebClient webClient;
    private final String KAKAO_USER_INFO_URI = "https://kapi.kakao.com/v2/user/me";
    private final MemberRepository memberRepository;

    public KakaoUserInfo getKakaoInfoByToken(String token) {
        return webClient.get()
                .uri(KAKAO_USER_INFO_URI)
                .header("Authorization", "Bearer " + token)
                .header("Content-type", "application/x-www-form-urlencoded;charset=utf-8")
                .retrieve()
                .bodyToMono(KakaoUserInfo.class)
                .block();
    }

    public KakaoResponseInfo getKakaoResponseInfo(KakaoUserInfo.KakaoAccount kakaoAccount) {
        Optional<Member> optionalUser = memberRepository.findByEmail(kakaoAccount.getEmail());
        String email = kakaoAccount.getEmail();
        String nickname = kakaoAccount.getProfile().getNickname();
        String profileImg = kakaoAccount.getProfile().getProfile_image_url();

        Member member;

        if (optionalUser.isEmpty()) {
            member = Member.builder()
                    .email(email)
                    .nickname(nickname)
                    .profileimage(profileImg)
                    .build();
            memberRepository.save(member);
        } else {
            member = optionalUser.get();
        }

        return KakaoResponseInfo.builder()
                .email(email)
                .nickname(nickname)
                .profileImage(profileImg)
                .build();
    }
}