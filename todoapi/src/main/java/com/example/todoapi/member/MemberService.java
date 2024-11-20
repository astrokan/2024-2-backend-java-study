package com.example.todoapi.member;

import com.example.todoapi.common.exception.BadRequestException;
import com.example.todoapi.common.message.ErrorMessage;
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
    public Member memberLogin(String loginId, String password) throws BadRequestException {
        Member member = memberRepository.findByLoginId(loginId);

        // id 검증
        if (member == null) {
            throw new BadRequestException(ErrorMessage.MEMBER_NOT_EXISTS);
        }
        // pw 검증
        if (!member.getPassword().equals(password)) {
            throw new BadRequestException(ErrorMessage.WRONG_PASSWORD);
        }

        return member;
    }


}
