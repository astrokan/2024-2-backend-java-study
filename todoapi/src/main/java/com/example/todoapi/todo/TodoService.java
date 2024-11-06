package com.example.todoapi.todo;

import com.example.todoapi.member.Member;
import com.example.todoapi.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoService {

    private final TodoRepository todoRepository;
    private final MemberRepository memberRepository;

    // todo 생성
    @Transactional
    public void createTodo (String content, Long memberId) throws Exception {
        Member member = memberRepository.findById(memberId);

        if (member == null) {
            throw new Exception("Member not found");
        }
        Todo todo = new Todo(content, member);
        todoRepository.save(todo);
    }

    // todo 조회(특정 멤버의 모든 할 일 조회)
    @Transactional(readOnly = true)
    public List<Todo> getAllTodo(Long memberId) throws Exception {
        Member member = memberRepository.findById(memberId);
        if (member == null) {
            throw new Exception("Member not found");
        }

        return todoRepository.findAllByMember(member);
    }

    // todo 수정
    public void updateTodoContent(Long todoId, String newContent, Long memberId) throws Exception {
        Member member = memberRepository.findById(memberId);
        Todo todo = todoRepository.findById(todoId);

        if (member == null) {
            throw new Exception("Member not found");
        }

        if (todo == null) {
            throw new Exception("Todo not found");
        }

        if (todo.getMember() != member) {
            throw new Exception("Cannot update a todo of another member");
        }

        todo.updateContent(newContent);
    }

    // todo 체크 토클
    @Transactional
    public void updateTodoCheckedToggle(Long todoId, Long memberId) throws Exception {
        Member member = memberRepository.findById(memberId);
        Todo todo = todoRepository.findById(todoId);

        if (member == null) {
            throw new Exception("Member not found");
        }

        if (todo == null) {
            throw new Exception("Todo not found");
        }

        todo.setIsCheckedToggle();
    }

    // todo 삭제
    @Transactional
    public void deleteTodo(Long todoId, Long memberId) throws Exception {
        Member member = memberRepository.findById(memberId);
        Todo todo = todoRepository.findById(todoId);

        if (member == null) {
            throw new Exception("Member not found");
        }

        if (todo == null) {
            throw new Exception("Todo not found");
        }

        if (todo.getMember() != member) {
            throw new Exception("Cannot update a todo of another member");
        }

        todoRepository.delete(todo);
    }

}
