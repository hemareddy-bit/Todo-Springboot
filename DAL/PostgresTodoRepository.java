package com.example.Todo.DAL;

import com.example.Todo.Model.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

@Repository
public class PostgresTodoRepository implements DbRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final RowMapper<Todo> rowMapper = new RowMapper<>() {
        @Override
        public Todo mapRow(ResultSet rs, int rowNum) throws SQLException {
            Todo todo = new Todo();
            todo.setId(String.valueOf(rs.getLong("id")));
            todo.setTitle(rs.getString("title"));
            todo.setDescription(rs.getString("description"));
            return todo;
        }
    };

    @Override
    public List<Todo> gettodo() {
        return jdbcTemplate.query("SELECT * FROM \"todo\"", rowMapper);
    }

    @Override
    public Optional<Todo> getTodoById(String id) {
        String querySql = "SELECT * FROM \"todo\" WHERE id = ?";
        List<Todo> results = jdbcTemplate.query(querySql, rowMapper, Long.parseLong(id));
        return results.stream().findFirst();
    }

    @Override
    public Todo createtodo(Todo todo) {
        String insertSql = "INSERT INTO todo (title, description) VALUES (?, ?) RETURNING id";
        Long newId = jdbcTemplate.queryForObject(insertSql, new Object[]{todo.getTitle(), todo.getDescription()}, Long.class);
        todo.setId(String.valueOf(newId));
        return todo;
    }

    @Override
    public void deleteById(String id) {
        jdbcTemplate.update("DELETE FROM \"todo\" WHERE id = ?", Long.parseLong(id));
    }

    @Override
    public Todo updateTodo(String id, Todo todo) {
        String updateSql = "UPDATE \"todo\" SET title = ?, description = ? WHERE id = ?";
        jdbcTemplate.update(updateSql, todo.getTitle(), todo.getDescription(), Long.parseLong(id));
        return getTodoById(id).orElse(null);
    }
}
