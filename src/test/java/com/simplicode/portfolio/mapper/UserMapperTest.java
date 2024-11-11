package com.simplicode.portfolio.mapper;

import com.simplicode.portfolio.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class UserMapperTest {

    private static final long USER_ID = 1L;
    private static final String USERNAME = "johndoe";
    private static final String FIRST_NAME = "John";
    private static final String LAST_NAME = "Doe";
    private static final String EMAIL = "johndoe@example.com";
    private static final String PASSWORD = "Iy#Z63K+Kp~H";
    private static final boolean IS_ENABLED = true;

    private ResultSet resultSet;
    private UserMapper userMapper;

    @BeforeEach
    void setUp() {
        resultSet = Mockito.mock(ResultSet.class);
        userMapper = new UserMapper();
    }

    @Test
    void testMapRow() throws SQLException {
        Mockito.when(resultSet.getLong("id")).thenReturn(1L);
        Mockito.when(resultSet.getString("username")).thenReturn(USERNAME);
        Mockito.when(resultSet.getString("first_name")).thenReturn(FIRST_NAME);
        Mockito.when(resultSet.getString("last_name")).thenReturn(LAST_NAME);
        Mockito.when(resultSet.getString("email")).thenReturn(EMAIL);
        Mockito.when(resultSet.getString("password")).thenReturn(PASSWORD);
        Mockito.when(resultSet.getBoolean("is_enabled")).thenReturn(IS_ENABLED);

        User user = userMapper.mapRow(resultSet, 1);

        assertNotNull(user);
        assertEquals(USER_ID, user.getId());
        assertEquals(USERNAME, user.getUsername());
        assertEquals(FIRST_NAME, user.getFirstName());
        assertEquals(LAST_NAME, user.getLastName());
        assertEquals(EMAIL, user.getEmail());
        assertEquals(PASSWORD, user.getPassword());
        assertEquals(IS_ENABLED, user.getIsEnabled());
    }

}
