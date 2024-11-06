package com.example.todoapi.member;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class MemberService {

    private final MemberRepository memberRepository;

    // 회원 가입
    @Transactional
    public void createMember(String loginId, String password) {
        
        Member member = new Member(loginId, password);
        memberRepository.save(member);
    }

    // 로그인
    @Transactional
    public Member memberLogin(String loginId, String password) throws Exception {
        Member member = memberRepository.findByLoginId(loginId);

        // id 검증
        if (member == null) {
            throw new Exception("존재하지 않는 회원입니다.");
        }
        // pw 검증
        if (!member.getPassword().equals(password)) {
            throw new Exception("틀린 비밀번호입니다.");
        }

        return member;
    }


}
