package com.simplicode.portfolio.mapper;

import com.simplicode.portfolio.model.User;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class UserMapper implements RowMapper<User> {

    @Override
    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new User()
           .setId(rs.getLong("id"))
           .setUsername(rs.getString("username"))
           .setFirstName(rs.getString("first_name"))
           .setLastName(rs.getString("last_name"))
           .setEmail(rs.getString("email"))
           .setPassword(rs.getString("password"))
           .setIsEnabled(rs.getBoolean("is_enabled"));
    }

}
