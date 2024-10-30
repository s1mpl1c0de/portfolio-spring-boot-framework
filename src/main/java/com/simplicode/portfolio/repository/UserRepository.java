package com.simplicode.portfolio.repository;

import com.simplicode.portfolio.mapper.UserMapper;
import com.simplicode.portfolio.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;

@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public void save(User user) {
        String sql = new StringJoiner(
           " ",
           "INSERT INTO users (",
           ") VALUES (?, ?, ?, ?, ?, ?)"
        )
           .add("username,")
           .add("first_name,")
           .add("last_name,")
           .add("email,")
           .add("password,")
           .add("is_enabled")
           .toString();

        Object[] args = new Object[]{
           user.getUsername(),
           user.getFirstName(),
           user.getLastName(),
           user.getEmail(),
           user.getPassword(),
           user.getIsEnabled()
        };

        jdbcTemplate.update(sql, args);
    }

    public List<User> findAll() {
        String sql = "SELECT * FROM users ORDER BY id DESC";
        return jdbcTemplate.query(sql, new UserMapper());
    }

    public Optional<User> findById(Long id) {
        String sql = "SELECT * FROM users WHERE id = ? LIMIT 1";
        return jdbcTemplate.query(sql, new UserMapper(), id).stream().findFirst();
    }

    public Optional<User> findByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ? LIMIT 1";
        return jdbcTemplate.query(sql, new UserMapper(), username).stream().findFirst();
    }

    public void updateById(Long id, User user) {
        String sql = "UPDATE users SET first_name = ?, last_name = ?, email = ? WHERE id = ?";
        Object[] args = new Object[]{user.getFirstName(), user.getLastName(), user.getEmail(), id};
        jdbcTemplate.update(sql, args);
    }

    public void deleteById(Long id) {
        String sql = "DELETE FROM users WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

}
