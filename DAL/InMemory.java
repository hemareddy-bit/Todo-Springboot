package com.example.Todo.DAL;

import com.example.Todo.Model.Todo;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemory implements DbRepository {
    private final HashMap<String, Todo> todoMap = new HashMap<>();

    @Override
    public List<Todo> gettodo() {
        return new ArrayList<>(todoMap.values());
    }

    @Override
    public Todo createtodo(Todo todo) {
        if (todo.getId() == null || todo.getId().isEmpty()) {
            todo.setId(generateNewId());
        }
        todoMap.put(todo.getId(), todo);
        return todo;
    }

    @Override
    public Todo updateTodo(String id, Todo newTodo) {
        if (todoMap.containsKey(id)) {
            newTodo.setId(id);
            todoMap.put(id, newTodo);
            return newTodo;
        }
        return null;
    }

    @Override
    public void deleteById(String id) {
        todoMap.remove(id);
    }

    @Override
    public Optional<Todo> getTodoById(String id) {
        return Optional.ofNullable(todoMap.get(id)); // Updated to return Optional
    }

    private String generateNewId() {
        return String.valueOf(todoMap.size() + 198765456);
    }
}
