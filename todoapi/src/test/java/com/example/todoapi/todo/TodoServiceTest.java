//package com.example.todoapi.todo;
//
//import com.example.todoapi.member.Member;
//import com.example.todoapi.member.MemberRepository;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.util.List;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyLong;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.times;
//import static org.mockito.Mockito.verify;
//
//@ExtendWith(MockitoExtension.class)
//public class TodoServiceTest {
//    @Mock
//    private TodoRepository todoRepository;
//
//    @Mock
//    private MemberRepository memberRepository;
//
//    @InjectMocks
//    private TodoService todoService;
//
//    @Test
//    void testTodoCreate() throws Exception {
//        // given
//        given(memberRepository.findById(anyLong())).willReturn(new Member());
//
//        // when
//        todoService.createTodo("content", 1L);
//
//        // then
//        verify(todoRepository, times(1)).save(any(Todo.class));
//
//    }
//    @Test
//    void testTodoCreate_fail_WhenUserNotExists() throws Exception {
//        // given
//        given(memberRepository.findById(anyLong())).willReturn(null);
//
//        // when & then
//        Assertions.assertThatThrownBy(() -> todoService.createTodo("content", 1L))
//                .isInstanceOf(Exception.class)
//                .hasMessage("존재하지 않는 유저 ID 입니다.");
//
//    }
//
//    @Test
//    void testTodoGetAll() throws Exception {
//        // given
//        given(memberRepository.findById(anyLong())).willReturn(new Member());
//        // when
//        List<Todo> todoList = todoService.getAllTodo(1L);
//        // then
//        verify(todoRepository, times(1)).findAllByMember(any(Member.class));
//    }
//
//    @Test
//    void testTodoUpdateContent() throws Exception {
//        given(memberRepository.findById(anyLong())).willReturn(new Member());
//        given(todoRepository.findById(anyLong())).willReturn(new Todo());
//
//        todoService.updateTodoContent(1L, "", 1L);
//
//
//    }
//    @Test
//    void testTodoUpdateChecked() throws Exception {
//        given(memberRepository.findById(anyLong())).willReturn(new Member());
//        given(todoRepository.findById(anyLong())).willReturn(new Todo());
//
//        todoService.updateTodoCheckedToggle(1L, 1L);
//
//    }
//
//    @Test
//    void testTodoDelete() throws Exception {
//        given(memberRepository.findById(anyLong())).willReturn(new Member());
//        given(todoRepository.findById(anyLong())).willReturn(new Todo());
//
//        todoService.deleteTodo(1L, 1L);
//
//    }
//
//}
