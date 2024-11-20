package com.example.todoapi.friend;

import com.example.todoapi.common.exception.BadDataAccessException;
import com.example.todoapi.common.exception.BadRequestException;
import com.example.todoapi.common.message.ErrorMessage;
import com.example.todoapi.member.Member;
import com.example.todoapi.member.MemberRepository;
import com.example.todoapi.todo.Todo;
import com.example.todoapi.todo.TodoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class FriendService {

    private final FriendRepository friendRepository;
    private final MemberRepository memberRepository;
    private final TodoRepository todoRepository;

    // 친구 요청 (친구 요청 상태로 Friend 관계 생성하여 db 저장)
    @Transactional
    public Long requestForFriend(Long senderId, Long receiverId) throws BadRequestException {
        Member sender = memberRepository.findById(senderId);
        Member receiver = memberRepository.findById(receiverId);

        if (sender == null) {
            throw new BadRequestException(ErrorMessage.MEMBER_NOT_EXISTS);
        }
        if (receiver == null) {
            throw new BadRequestException(ErrorMessage.RECEIVER_NOT_EXISTS);
        }

        Friend friend = new Friend(sender, receiver);
        friendRepository.save(friend);

        return friend.getId();
    }

    // 친구 수락(id : 친구 관계 id, senderId : 친구 요청자 id)
    @Transactional
    public void updateAccepted(Long id, Long receiverId) throws Exception {
        Friend friend = friendRepository.findOneById(id);

        if (friend == null) {
            throw new BadRequestException(ErrorMessage.REQUEST_FOR_FRIEND_NOT_EXISTS);
        }

        if (!Objects.equals(friend.getReceiver().getId(), receiverId)) {
            throw new BadDataAccessException(ErrorMessage.ONLY_RECEIVER_CAN_ACCEPT_REQUEST_FOR_FRIEND);
        }
        else if (friend.getStatus().equals("accepted")) {
            throw new BadDataAccessException(ErrorMessage.REQUEST_FOR_FRIEND_ALREADY_ACCEPTED);
        }

        friend.updateAccepted();
    }

    // 유저의 승인 상태 친구 목록 조회
    public List<Friend> getAllFriend(Long senderId) {
        Member sender = memberRepository.findById(senderId);
        return friendRepository.findAllFriend(sender);
    }

    // 유저가 받은 친구 요청 목록 조회
    public List<Friend> getReceivedRequestList(Long senderId) {
        Member sender = memberRepository.findById(senderId);
        return friendRepository.findReceivedRequestList(sender);
    }

    // delete(친구 삭제)
    public void deleteFriend(Long id, Long senderId) throws Exception {
        Member member = memberRepository.findById(senderId);
        Friend friend = friendRepository.findOneById(id);

        if (member == null) {
            throw new BadRequestException(ErrorMessage.MEMBER_NOT_EXISTS);
        }
        if (friend == null) {
            throw new BadRequestException(ErrorMessage.FRIEND_RELATIONSHIP_NOT_EXISTS);
        }
        friendRepository.deleteById(id);
    }

    // 친구 유저 할 일 조회
    @Transactional
    public List<Todo> getTodoOfFriend(Long id, Long senderId) throws Exception{
        Member sender = memberRepository.findById(senderId);
        Friend friend = friendRepository.findOneById(id);

        if (sender == null) {
            throw new BadRequestException(ErrorMessage.MEMBER_NOT_EXISTS);
        }
        if (friend == null) {
            throw new BadRequestException(ErrorMessage.RECEIVER_NOT_EXISTS);
        }
        if (friend.getStatus().equals("pending")) {
            throw new BadDataAccessException(ErrorMessage.FRIEND_RELATIONSHIP_NOT_EXISTS);
        }

        Member receiver = friend.getReceiver();
        return todoRepository.findAllByMember(receiver);
    }

    // 친구 거절은 필요시 구현할 예정(우선 보류 상태(pending)로 대신함)
}
