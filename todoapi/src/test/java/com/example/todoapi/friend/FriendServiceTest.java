package com.example.todoapi.friend;

import com.example.todoapi.member.MemberRepository;
import com.example.todoapi.todo.TodoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
public class FriendServiceTest {
    @Mock
    private FriendRepository friendRepository;

    @Mock
    private MemberRepository memberRepository;

    @Mock
    private TodoRepository todoRepository;

    @InjectMocks
    private FriendService friendService;

    @Test
    void testRequestFriend() throws Exception {

    }
    @Test
    void testFriendAccepted() throws Exception {

    }
    @Test
    void testGetAllFriends() throws Exception {

    }
    @Test
    void testDeleteFriend() throws Exception {

    }
    @Test
    void testGetTodoOfFriend() throws Exception {

    }

}
