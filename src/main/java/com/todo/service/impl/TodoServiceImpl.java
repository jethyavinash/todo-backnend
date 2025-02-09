package com.todo.service.impl;

import com.todo.dto.TodoDto;
import com.todo.entity.Todo;
import com.todo.exception.ResourceNotFoundException;
import com.todo.repository.TodoRepository;
import com.todo.service.TodoService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TodoServiceImpl implements TodoService {

    @Autowired
    private TodoRepository todoRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public TodoDto addTodo(TodoDto todoDto) {
        Todo todo = modelMapper.map(todoDto, Todo.class);
        Todo savedTodo = todoRepository.save(todo);
        TodoDto savedTodoDto = modelMapper.map(savedTodo, TodoDto.class);
        return savedTodoDto;
    }

    @Override
    public TodoDto getTodo(Long id) {
        Todo todo = todoRepository.findById(id)
                .orElseThrow( () -> new ResourceNotFoundException("Todo not foud with id: "+id));
        return modelMapper.map(todo,TodoDto.class);
    }

    @Override
    public List<TodoDto> getAllTodos() {
        List<Todo> todos = todoRepository.findAll();
        return todos.stream().map(todo -> modelMapper.map(todo,TodoDto.class)).collect(Collectors.toList());
    }

    @Override
    public TodoDto updateTodo(TodoDto todoDto,Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: "+id));
        todo.setTitle(todoDto.getTitle());
        todo.setDescription(todoDto.getDescription());
        todo.setCompleted(todoDto.isCompleted());
        Todo updatedTodo = todoRepository.save(todo);
        return modelMapper.map(updatedTodo,TodoDto.class);
    }

    @Override
    public void deleteTodo(Long id) {
        todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo not found for delete with id: "+id));
        todoRepository.deleteById(id);
    }

    @Override
    public TodoDto completeTodo(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow( () -> new ResourceNotFoundException("Todo not found with id: "+id));
        todo.setCompleted(true);
        Todo updatedTodo = todoRepository.save(todo);
        return modelMapper.map(updatedTodo, TodoDto.class);
    }

    @Override
    public TodoDto inCompleteTodo(Long id) {
        Todo todo = todoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Todo not found with id: "+id));
        todo.setCompleted(false);
        Todo updatedTodo = todoRepository.save(todo);
        return modelMapper.map(updatedTodo,TodoDto.class);
    }

}
