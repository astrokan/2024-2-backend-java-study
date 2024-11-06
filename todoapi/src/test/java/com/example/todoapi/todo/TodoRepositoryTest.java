package com.example.todoapi.todo;

import com.example.todoapi.member.Member;
import com.example.todoapi.member.MemberRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class TodoRepositoryTest {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Test
    @Transactional
    @Rollback(value = false)
    void todoCreateTest() {
        // given
        Todo todo = new Todo("content", false, null);

        // when
        todoRepository.save(todo);

        // then
        assertThat(todo.getId()).isNotNull();
    }

    @Test
    @Transactional
    void todoFindOneTest() {
        // given
        Todo todo = new Todo("content", false, null);
        todoRepository.save(todo);

        todoRepository.flushAndClear();

        // when
        Todo findTodo = todoRepository.findById(todo.getId());

        // then
        assertThat(todo).isNotNull();
        System.out.println(todo.getId());
        System.out.println(todo.getContent());
    }

    @Test
    @Transactional
    void todoFindAllTest() {
        // given
        todoRepository.save(new Todo("content1", false, null));
        todoRepository.save(new Todo("content2", false, null));
        todoRepository.save(new Todo("content3", false, null));

        // when
        List<Todo> todos = todoRepository.findAll();

        // then
        assertThat(todos).hasSize(3);
    }
    @Test
    @Transactional
    void todoFindAllByMemberTest() {
        // given
        Member member1 = new Member();
        Member member2 = new Member();
        memberRepository.save(member1);
        memberRepository.save(member2);

        todoRepository.save(new Todo("content1", false, member1));
        todoRepository.save(new Todo("content2", false, member1));
        todoRepository.save(new Todo("content3", false, member2));


        // when
        List<Todo> member1TodoList = todoRepository.findAllByMember(member1);
        List<Todo> member2TodoList = todoRepository.findAllByMember(member2);

        // then
        assertThat(member1TodoList).hasSize(2);
        assertThat(member2TodoList).hasSize(1);

    }

    @Test
    @Transactional
    @Rollback(value = false)
    void updateTodo() {
        // given
        Todo todo = new Todo("content", false, null);
        todoRepository.save(todo);

        todoRepository.flushAndClear();
        Todo findTodo = todoRepository.findById(todo.getId());

        // when
        findTodo.updateContent("newContent");
    }

    @Test
    @Transactional
    @Rollback(value = false)
    void todoDeleteTest() {
        // given
        Todo todo = new Todo("to remove todo", false, null);
        todoRepository.save(todo);
        todoRepository.save(new Todo("content1", false, null));
        todoRepository.save(new Todo("content2", false, null));

        todoRepository.flushAndClear();
        // when
        todoRepository.deleteById(todo.getId());
    }
    // in memory database
    @AfterAll
    public static void test() {
        System.out.println("test finish!");
        while (true) {
        }
    }

}
