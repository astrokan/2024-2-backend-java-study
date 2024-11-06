package com.example.todoapi.friend;

import com.example.todoapi.member.Member;
import com.example.todoapi.member.MemberRepository;
import com.example.todoapi.todo.Todo;
import com.example.todoapi.todo.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FriendService {

    private final FriendRepository friendRepository;
    private final MemberRepository memberRepository;
    private final TodoRepository todoRepository;

    // 친구 요청 (친구 요청 상태로 Friend 관계 생성하여 db 저장)
    @Transactional
    public void requestForFriend(Long senderId, Long receiverId) throws Exception {
        Member sender = memberRepository.findById(senderId);
        Member receiver = memberRepository.findById(receiverId);

        if (sender == null || receiver == null) {
            throw new Exception("유저 또는 친구가 존재하지 않음.");
        }
        Friend friend = new Friend(sender, receiver);
        friendRepository.save(friend);
    }

    // 친구 수락
    @Transactional
    public void updateAccepted(Long friendId) throws Exception {
        Friend friend = friendRepository.findOneById(friendId);

        if (friend == null || friend.getStatus().equals("pending")) {
            throw new Exception("친구 관계가 존재하지 않습니다.");
        }
        friend.updateAccepted();
    }

    // 유저의 친구 목록 조회
    public List<Friend> getAllFriend(Long senderId) {
        Member sender = memberRepository.findById(senderId);
        return friendRepository.findAllReceiver(sender);
    }

    // delete(친구 삭제)
    public void deleteFriend(Long id, Long senderId) throws Exception {
        Member member = memberRepository.findById(senderId);
        Friend friend = friendRepository.findOneById(id);

        if (member == null) {
            throw new Exception("존재하지 않는 회원입니다.");
        }
        if (friend == null || friend.getStatus().equals("pending")) {
            throw new Exception("존재하지 않는 친구 관계입니다.");
        }
        friendRepository.deleteById(id);
    }

    // 친구 유저 할 일 조회
    @Transactional
    public List<Todo> getTodoOfFriend(Long id, Long senderId) throws Exception{
        Member sender = memberRepository.findById(senderId);
        Friend friend = friendRepository.findOneById(id);

        if (sender == null) {
            throw new Exception("존재하지 않는 회원입니다.");
        }

        if (friend == null || friend.getStatus().equals("pending")) {
            throw new Exception("존재하지 않는 친구 관계입니다.");
        }
        Member receiver = friend.getReceiver();
        return todoRepository.findAllByMember(receiver);
    }

    // 친구 거절은 필요시 구현할 예정(우선 보류 상태(pending)로 대신함)
}
