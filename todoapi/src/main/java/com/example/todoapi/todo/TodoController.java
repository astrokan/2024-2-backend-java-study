package com.example.todoapi.todo;

import com.example.todoapi.todo.dto.TodoCreateRequest;
import com.example.todoapi.todo.dto.TodoUpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/todo")
public class TodoController {

    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<Void> createTodo(@RequestBody TodoCreateRequest request) throws Exception{
        Long todoId = todoService.createTodo(request.getContent(), request.getMemberId());
        return ResponseEntity.created(URI.create("todo/"+todoId)).build();
    }
    @GetMapping(path="/list")
    public ResponseEntity<List<Todo>> getTodoList(@RequestBody Long memberId) throws Exception{
        List<Todo> todoList = todoService.getAllTodo(memberId);
        return ResponseEntity.ok().body(todoList);
    }
    // Delete
    @DeleteMapping("/{todoId}")
    public ResponseEntity<Void> deleteTodo(@PathVariable Long todoId) throws Exception {
        // todoService.deleteTodo(todoId);
        return ResponseEntity.noContent().build(); // 204 no content
    }
    // Patch
    @PatchMapping("/{todoId}")
    public ResponseEntity<Void> updateTodo(@PathVariable Long todoId, @RequestBody TodoUpdateRequest request) throws Exception {
        // todoService.updateTodo(todoId, request.getMemberId(), request.getUpdateContent());
        return ResponseEntity.ok().build();
    }
//    public String test() {
//        return "hello";
//    }
}
