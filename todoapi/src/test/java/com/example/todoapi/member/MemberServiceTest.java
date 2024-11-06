package com.example.todoapi.member;

import com.example.todoapi.todo.Todo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class MemberServiceTest {
    @Mock
    private MemberRepository memberRepository;

    @InjectMocks
    private MemberService memberService;

    @Test
    void testCreateMember() throws Exception {

        // given, when
        memberService.createMember("123", "234");
        // then
        verify(memberRepository, times(1)).save(any(Member.class));
    }

    @Test
    void testMemberLogin() throws Exception {

        String loginId = "id";
        String password = "pw";
        Member member = new Member();
        member.setLoginId(loginId);
        member.setPassword(password);

        given(memberRepository.findByLoginId(loginId)).willReturn(member);

        // when
        Member result = memberService.memberLogin(loginId, password);

        // then
        Assertions.assertEquals(member, result);

    }
}
