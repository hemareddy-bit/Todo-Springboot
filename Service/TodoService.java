package com.example.Todo.Service;

import com.example.Todo.DAL.DbRepository;
import com.example.Todo.Model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoService {

    private final DbRepository DbRepository;

    @Autowired
    public TodoService(@Qualifier("todorepository") DbRepository DbRepository) {
        this.DbRepository = DbRepository;
    }

    public List<Todo> getAllTodos() {
        return DbRepository.gettodo();
    }

    public Todo createTodo(Todo todo) {
        return DbRepository.createtodo(todo);
    }

    public Todo updateTodo(String id, Todo newTodo) {
        return DbRepository.updateTodo(id, newTodo);
    }

    public void deleteTodoById(String id) {
        DbRepository.deleteById(id);
    }
}
