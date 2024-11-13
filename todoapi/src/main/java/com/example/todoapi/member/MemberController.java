package com.example.todoapi.member;


import com.example.todoapi.member.dto.MemberLoginRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/login")
public class MemberController {

    private final MemberService memberService;

    // 로그인 후 member_id 리턴
    @GetMapping
    public ResponseEntity<Long> memberLogin(@RequestBody MemberLoginRequest request) throws Exception {
        Member member = memberService.memberLogin(request.getLoginId(), request.getPassword());
        return ResponseEntity.ok().body(member.getId());
    }

}
