package com.example.todoapi.friend;

import com.example.todoapi.friend.dto.FriendInfoRequest;
import com.example.todoapi.friend.dto.TodoOfFriendRequest;
import com.example.todoapi.todo.Todo;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/friend")
public class FriendController {

    private final FriendService friendService;

    // create : 친구 관계 생성(친구 요청 상태임, 승인된 친구 관계 아님.)
    @PostMapping
    public ResponseEntity<Void> requestForFriend(@RequestBody FriendInfoRequest request) throws Exception{
        Long friendId = friendService.requestForFriend(request.getSenderId(), request.getReceiverId());
        return ResponseEntity.created(URI.create("/friend"+friendId)).build();
    }

    // read : 받은 친구 신청 목록 조회(유저가 받은 친구 요청을 리스트 형태로 리턴)
    @GetMapping("/requestedList")
    public ResponseEntity<List<Friend>> getReceivedRequestList(@RequestBody Long memberId) {
        List<Friend> requestedList = friendService.getReceivedRequestList(memberId);
        return ResponseEntity.ok().body(requestedList);
    }
    // read : 친구 목록 조회
    @GetMapping("/friendList")
    public ResponseEntity<List<Friend>> getFriendList(@RequestBody Long memberId) {
        List<Friend> friendList = friendService.getAllFriend(memberId);
        return ResponseEntity.ok().body(friendList);
    }
    // read : 친구의 할 일 조회
    @GetMapping("/todoOfFriend")
    public ResponseEntity<List<Todo>> getTodoOfFriend(@RequestBody TodoOfFriendRequest request) throws Exception{
        List<Todo> todoList = friendService.getTodoOfFriend(request.getFriendId(), request.getMemberId());
        return ResponseEntity.ok().body(todoList);
    }

    // update : 친구 요청 수락
    @PatchMapping("/{friendId}")
    public ResponseEntity<Void> acceptFriendRequest(@PathVariable Long friendId, @RequestBody Long memberId) throws Exception {
        friendService.updateAccepted(friendId, memberId);
        return ResponseEntity.ok().build();
    }

    // delete : 친구 삭제
    @DeleteMapping("/{friendId}")
    public ResponseEntity<Void> deleteFriend(@PathVariable Long friendId, @RequestBody Long memberId) throws Exception {
        friendService.deleteFriend(friendId, memberId);
        return ResponseEntity.noContent().build();
    }
}
