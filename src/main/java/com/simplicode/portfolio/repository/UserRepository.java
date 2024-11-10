package com.simplicode.portfolio.repository;

import com.simplicode.portfolio.exception.BadRequestException;
import com.simplicode.portfolio.mapper.UserMapper;
import com.simplicode.portfolio.model.Page;
import com.simplicode.portfolio.model.User;
import com.simplicode.portfolio.query.UserQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Slf4j
@Repository
@RequiredArgsConstructor
public class UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public void save(User user) {
        try {
            jdbcTemplate.update(
               UserQuery.INSERT,
               user.getUsername(),
               user.getFirstName(),
               user.getLastName(),
               user.getEmail(),
               user.getPassword(),
               user.getIsEnabled()
            );
        } catch (DataAccessException exception) {
            log.error("Failed to save User: {}", exception.getMessage());
            throw new BadRequestException();
        }
    }

    public Integer countAll() {
        try {
            return jdbcTemplate.queryForObject(UserQuery.COUNT_ALL, Integer.class);
        } catch (DataAccessException exception) {
            log.error("Failed to count all Users: {}", exception.getMessage());
            throw new BadRequestException();
        }
    }

    public List<User> findAll(Page page) {
        try {
            String sql = UserQuery.FIND_ALL;
            UserMapper rowMapper = new UserMapper();
            Object[] args = new Object[]{page.getLimit(), page.getOffset()};
            return jdbcTemplate.query(sql, rowMapper, args);
        } catch (DataAccessException exception) {
            log.error("Failed to find all Users: {}", exception.getMessage());
            throw new BadRequestException();
        }
    }

    public Optional<User> findById(Long id) {
        try {
            String sql = UserQuery.FIND_BY_ID;
            UserMapper rowMapper = new UserMapper();
            return jdbcTemplate.query(sql, rowMapper, id).stream().findFirst();
        } catch (DataAccessException exception) {
            log.error("Failed to find User by id {}: {}", id, exception.getMessage());
            throw new BadRequestException();
        }
    }

    public Optional<User> findByUsername(String username) {
        try {
            String sql = UserQuery.FIND_BY_USERNAME;
            UserMapper rowMapper = new UserMapper();
            return jdbcTemplate.query(sql, rowMapper, username).stream().findFirst();
        } catch (DataAccessException exception) {
            log.error("Failed to find User by username {}: {}", username, exception.getMessage());
            throw new BadRequestException();
        }
    }

    public void updateById(Long id, User user) {
        try {
            jdbcTemplate.update(
               UserQuery.UPDATE_BY_ID,
               user.getFirstName(),
               user.getLastName(),
               user.getEmail(),
               id
            );
        } catch (DataAccessException exception) {
            log.error("Failed to update User by id {}: {}", id, exception.getMessage());
            throw new BadRequestException();
        }
    }

    public void deleteById(Long id) {
        try {
            jdbcTemplate.update(UserQuery.DELETE_BY_ID, id);
        } catch (DataAccessException exception) {
            log.error("Failed to delete User by id {}: {}", id, exception.getMessage());
            throw new BadRequestException();
        }
    }

}
