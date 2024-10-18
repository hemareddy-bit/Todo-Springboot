package com.example.Todo.DAL;

import com.example.Todo.Model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class MongoTodoRep implements DbRepository {

    @Autowired
    private MongoRep mongoRep;

    @Override
    public List<Todo> gettodo() {
        return mongoRep.findAll();
    }

    @Override
    public Todo createtodo(Todo todo) {
        return mongoRep.save(todo);
    }

    @Override
    public Todo updateTodo(String id, Todo newTodo) {
        if (!mongoRep.existsById(id)) {
            throw new RuntimeException("Todo not found");
        }
        newTodo.setId(id);
        return mongoRep.save(newTodo);
    }

    @Override
    public void deleteById(String id) {
        if (!mongoRep.existsById(id)) {
            throw new RuntimeException("Todo not found");
        }
        mongoRep.deleteById(id);
    }

    @Override
    public Optional<Todo> getTodoById(String id) {
        return mongoRep.findById(id); // Updated to return Optional
    }
}
