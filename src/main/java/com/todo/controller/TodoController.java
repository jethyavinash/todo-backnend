package com.todo.controller;

import com.todo.dto.TodoDto;
import com.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/todos")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @PostMapping("/save")
    public ResponseEntity<TodoDto> saveTodo(@RequestBody TodoDto todoDto){
        TodoDto savedTodo = todoService.addTodo(todoDto);
        return new ResponseEntity<>(savedTodo, HttpStatus.CREATED);
    }

    @GetMapping("/get/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<TodoDto> getTodo(@PathVariable Long id){
        TodoDto todoDto = todoService.getTodo(id);
        return new ResponseEntity<>(todoDto,HttpStatus.OK);
    }

    @GetMapping("/get/all")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<List<TodoDto>> getAllTodos(){
        List<TodoDto> todoDtos = todoService.getAllTodos();
        return ResponseEntity.ok(todoDtos);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<TodoDto> updateTodo(@RequestBody TodoDto todoDto,@PathVariable Long id){
        TodoDto updatedTodo = todoService.updateTodo(todoDto,id);
        return ResponseEntity.ok(updatedTodo);
    }

    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> deleteTodo(@PathVariable Long id){
        todoService.deleteTodo(id);
        return new ResponseEntity<>("Todo deleted successfully", HttpStatus.OK);
    }

    @PatchMapping("/markComplete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<TodoDto> markComplete(@PathVariable Long id){
        TodoDto completeTodo = todoService.completeTodo(id);
        return ResponseEntity.ok(completeTodo);
    }

    @PatchMapping("/markInComplete/{id}")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public ResponseEntity<TodoDto> markInComplete(@PathVariable Long id){
        TodoDto inCompleteTodo = todoService.inCompleteTodo(id);
        return ResponseEntity.ok(inCompleteTodo);
    }
}
