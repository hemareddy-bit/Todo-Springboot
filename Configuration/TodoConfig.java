package com.example.Todo.Configuration;

import com.example.Todo.DAL.DbRepository;
import com.example.Todo.DAL.InMemory;
import com.example.Todo.DAL.MongoTodoRep;
import com.example.Todo.DAL.PostgresTodoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TodoConfig {

    @Value("${db.type}")
    private String dbType;

    @Autowired
    private MongoTodoRep mongoRep;

    @Autowired
    private InMemory inmemory;

    @Autowired
    private PostgresTodoRepository postgresTodoRepository;

    public TodoConfig(MongoTodoRep mongoRep, InMemory inmemory, PostgresTodoRepository postgresTodoRepository) {
        this.mongoRep = mongoRep;
        this.inmemory = inmemory;
        this.postgresTodoRepository = postgresTodoRepository;
    }

    @Bean(name = "todorepository")
    public DbRepository TodoRep() {
        if ("inMemory".equalsIgnoreCase(dbType)) {
            return inmemory;
        } else if ("mongo".equalsIgnoreCase(dbType)) {
            return mongoRep;
        } else {
            return postgresTodoRepository;
        }
    }
}
