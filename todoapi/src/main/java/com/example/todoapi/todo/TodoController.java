package com.example.todoapi.todo;

import com.example.todoapi.todo.dto.TodoCreateRequest;
import com.example.todoapi.todo.dto.TodoUpdateContentRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<Void> createTodo(@RequestBody @Valid TodoCreateRequest request) throws Exception {
        Long todoId = todoService.createTodo(request.getContent(), request.getMemberId());
        return ResponseEntity.created(URI.create("todo/"+todoId)).build();
    }
    @GetMapping("/list")
    public ResponseEntity<List<Todo>> getTodoList(@RequestBody Long memberId) throws Exception {
        List<Todo> todoList = todoService.getAllTodo(memberId);
        return ResponseEntity.ok().body(todoList);
    }
    // Patch(내용 수정)
    @PatchMapping("/{todoId}")
    public ResponseEntity<Void> updateTodoContent(@PathVariable Long todoId, @RequestBody @Valid TodoUpdateContentRequest request) throws Exception {
        todoService.updateTodoContent(todoId, request.getUpdateContent(), request.getMemberId());
        return ResponseEntity.ok().build();
    }

    // Delete
    @DeleteMapping("/{todoId}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long todoId) throws Exception {
        todoService.deleteTodo(todoId);
        return ResponseEntity.noContent().build(); // 204 no content
    }

}
