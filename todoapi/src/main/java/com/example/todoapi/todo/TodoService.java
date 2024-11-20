package com.example.todoapi.todo;

import com.example.todoapi.common.exception.BadDataAccessException;
import com.example.todoapi.common.exception.BadRequestException;
import com.example.todoapi.common.message.ErrorMessage;
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
    public Long createTodo (String content, Long memberId) throws BadRequestException {
        Member member = memberRepository.findById(memberId);

        if (member == null) {
            throw new BadRequestException(ErrorMessage.MEMBER_NOT_EXISTS);
        }
        Todo todo = new Todo(content, member);
        todoRepository.save(todo);

        return todo.getId();
    }

    // todo 조회(특정 멤버의 모든 할 일 조회)
    @Transactional(readOnly = true)
    public List<Todo> getAllTodo(Long memberId) throws BadRequestException {
        Member member = memberRepository.findById(memberId);
        if (member == null) {
            throw new BadRequestException(ErrorMessage.MEMBER_NOT_EXISTS);
        }

        return todoRepository.findAllByMember(member);
    }

    // todo 수정
    public void updateTodoContent(Long todoId, String newContent, Long memberId) throws BadRequestException {
        Todo todo = todoRepository.findById(todoId);
        Member member = memberRepository.findById(memberId);

        if (member == null) {
            throw new BadRequestException(ErrorMessage.MEMBER_NOT_EXISTS);
        }

        if (todo == null) {
            throw new BadRequestException(ErrorMessage.TODO_NOT_EXISTS);
        }

        if (todo.getMember() != member) {
            throw new BadDataAccessException(ErrorMessage.CANNOT_ACCESS_TODO_OF_ANOTHER_MEMBER);
        }

        todo.updateContent(newContent);
    }

    // todo 체크 토클
    @Transactional
    public void updateTodoCheckedToggle(Long todoId, Long memberId) throws Exception {
        Member member = memberRepository.findById(memberId);
        Todo todo = todoRepository.findById(todoId);

        if (member == null) {
            throw new BadRequestException(ErrorMessage.MEMBER_NOT_EXISTS);
        }

        if (todo == null) {
            throw new BadRequestException(ErrorMessage.TODO_NOT_EXISTS);
        }

        todo.setIsCheckedToggle();
    }

    // todo 삭제
    @Transactional
    public void deleteTodo(Long todoId, Long memberId) throws BadRequestException {
        Member member = memberRepository.findById(memberId);
        Todo todo = todoRepository.findById(todoId);

        if (member == null) {
            throw new BadRequestException(ErrorMessage.MEMBER_NOT_EXISTS);
        }

        if (todo == null) {
            throw new BadRequestException(ErrorMessage.TODO_NOT_EXISTS);
        }

        if (todo.getMember() != member) {
            throw new BadDataAccessException(ErrorMessage.CANNOT_ACCESS_TODO_OF_ANOTHER_MEMBER);
        }

        todoRepository.delete(todo);
    }
    @Transactional
    public void deleteTodo(Long todoId) throws Exception {
        Todo todo = todoRepository.findById(todoId);

        if (todo == null) {
            throw new Exception(ErrorMessage.TODO_NOT_EXISTS);
        }
        todoRepository.delete(todo);
    }
}
