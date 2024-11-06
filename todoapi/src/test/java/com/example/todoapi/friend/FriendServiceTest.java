package com.example.todoapi.friend;

import com.example.todoapi.member.Member;
import com.example.todoapi.member.MemberRepository;
import com.example.todoapi.todo.Todo;
import com.example.todoapi.todo.TodoRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

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

        given(memberRepository.findById(anyLong())).willReturn(new Member());

        friendService.requestForFriend(1L, 2L);

        verify(friendRepository, times(1)).save(any(Friend.class));


    }
    @Test
    void testFriendAccepted_Valid() throws Exception {

        Long friendId = 1L;
        Friend friend = mock(Friend.class);
        given(friend.getStatus()).willReturn("accepted");
        given(friendRepository.findOneById(friendId)).willReturn(friend);

        // when
        friendService.updateAccepted(friendId);

        // then
        verify(friend).updateAccepted();

    }
    @Test
    void testGetAllFriends() throws Exception {
        // given
        Long senderId = 1L;
        Member sender = mock(Member.class);
        Friend friend1 = new Friend();
        Friend friend2 = new Friend();
        List<Friend> expectedFriends = Arrays.asList(friend1, friend2);

        given(memberRepository.findById(senderId)).willReturn(sender);
        given(friendRepository.findAllReceiver(sender)).willReturn(expectedFriends);

        // when
        List<Friend> result = friendService.getAllFriend(senderId);

        // then
        Assertions.assertThat(result).isEqualTo(expectedFriends);
        Assertions.assertThat(result).hasSize(2);
        Assertions.assertThat(result).containsExactly(friend1, friend2);
    }
    @Test
    void testDeleteFriend() throws Exception {
        // given
        Long id = 1L;
        Long senderId = 2L;
        Member member = mock(Member.class);
        Friend friend = mock(Friend.class);

        given(memberRepository.findById(senderId)).willReturn(member);
        given(friendRepository.findOneById(id)).willReturn(friend);
        given(friend.getStatus()).willReturn("accepted");

        // when
        friendService.deleteFriend(id, senderId);

        // then
        verify(friendRepository).deleteById(id);
    }
    @Test
    void testGetTodoOfFriend() throws Exception {
        // given
        Long id = 1L;
        Long senderId = 2L;

        Member sender = mock(Member.class);
        Friend friend = mock(Friend.class);
        Member receiver = mock(Member.class);
        Todo todo1 = mock(Todo.class);
        Todo todo2 = mock(Todo.class);
        List<Todo> expectedTodos = Arrays.asList(todo1, todo2);

        given(memberRepository.findById(senderId)).willReturn(sender);
        given(friendRepository.findOneById(id)).willReturn(friend);
        given(friend.getStatus()).willReturn("accepted");
        given(friend.getReceiver()).willReturn(receiver);
        given(todoRepository.findAllByMember(receiver)).willReturn(expectedTodos);

        // when
        List<Todo> result = friendService.getTodoOfFriend(id, senderId);

        // then
        Assertions.assertThat(result).isEqualTo(expectedTodos);
        Assertions.assertThat(result).hasSize(2);
        Assertions.assertThat(result).containsExactly(todo1, todo2);
    }

}
