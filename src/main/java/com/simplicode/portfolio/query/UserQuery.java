package com.simplicode.portfolio.query;

public class UserQuery {

    private static final String USERS = "users";
    private static final String ID = "id";
    private static final String USERNAME = "username";
    private static final String FIRST_NAME = "first_name";
    private static final String LAST_NAME = "last_name";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private static final String IS_ENABLED = "is_enabled";

    public static final String INSERT = String.format(
       "INSERT INTO %s (%s, %s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?, ?)",
       USERS, USERNAME, FIRST_NAME, LAST_NAME, EMAIL, PASSWORD, IS_ENABLED
    );

    public static final String COUNT_ALL = String.format("SELECT COUNT(*) FROM %s", USERS);

    public static final String FIND_ALL = String.format(
       "SELECT * FROM %s ORDER BY %s DESC LIMIT ? OFFSET ?", USERS, ID
    );

    public static final String FIND_BY_ID = String.format("SELECT * FROM %s WHERE %s = ? LIMIT 1", USERS, ID);

    public static final String FIND_BY_USERNAME = String.format(
       "SELECT * FROM %s WHERE %s = ? LIMIT 1", USERS, USERNAME
    );

    public static final String UPDATE_BY_ID = String.format(
       "UPDATE %s SET %s = ?, %s = ?, %s = ? WHERE %s = ?",
       USERS, FIRST_NAME, LAST_NAME, EMAIL, ID
    );

    public static final String DELETE_BY_ID = String.format("DELETE FROM %s WHERE %s = ?", USERS, ID);

}
