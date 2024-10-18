package com.example.Todo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "todos")
@Data // Lombok annotation to generate getters, setters, toString, equals, hashCode, etc.
@NoArgsConstructor // Generates the no-argument constructor
@AllArgsConstructor // Generates the constructor with all arguments
public class Todo {

    @Id
    private String id;
    private String title;
    private String description;
}
