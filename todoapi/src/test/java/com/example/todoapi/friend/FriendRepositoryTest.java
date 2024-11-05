package com.example.todoapi.friend;

import com.example.todoapi.member.Member;
import com.example.todoapi.member.MemberRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class FriendRepositoryTest {

    @Autowired
    private FriendRepository friendRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(false)
    void friendCreateTest() {
        Member member1 = new Member("1", "1");
        Member member2 = new Member("2", "2");
        memberRepository.save(member1);
        memberRepository.save(member2);

        Friend friend = new Friend(member1, member2);
        friendRepository.save(friend);

        assertThat(friend.getId()).isNotNull();
    }
    void friendFindAllByMemberTest() {
        Member member1 = new Member("1", "1");
        Member member2 = new Member("2", "2");
        Member member3 = new Member("3", "3");
        Member member4 = new Member("4", "4");



    }
}
