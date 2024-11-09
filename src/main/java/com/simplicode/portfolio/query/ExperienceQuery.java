package com.simplicode.portfolio.query;

public class ExperienceQuery {

    private static final String EXPERIENCES = "experiences";
    private static final String ID = "id";
    private static final String JOB_TITLE = "job_title";
    private static final String COMPANY_NAME = "company_name";
    private static final String STARTED_MONTH = "started_month";
    private static final String STARTED_YEAR = "started_year";
    private static final String ENDED_MONTH = "ended_month";
    private static final String ENDED_YEAR = "ended_year";
    private static final String IS_STILL_IN_ROLE = "is_still_in_role";
    private static final String DESCRIPTION = "description";
    private static final String CREATED_DATE = "created_date";
    private static final String LAST_MODIFIED_DATE = "last_modified_date";
    private static final String USER_ID = "user_id";

    public static final String INSERT = String.format(
       "INSERT INTO %s (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s, %s) " +
       "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
       EXPERIENCES, JOB_TITLE, COMPANY_NAME, STARTED_MONTH, STARTED_YEAR, ENDED_MONTH,
       ENDED_YEAR, IS_STILL_IN_ROLE, DESCRIPTION, CREATED_DATE, LAST_MODIFIED_DATE, USER_ID
    );

    public static final String COUNT_ALL_BY_USER_ID = String.format(
       "SELECT COUNT(*) FROM %s WHERE %s = ?",
       EXPERIENCES, USER_ID
    );

    public static final String FIND_ALL_BY_USER_ID = String.format(
       "SELECT * FROM %s WHERE %s = ? " +
       "ORDER BY %s DESC, %s DESC, %s DESC LIMIT ? OFFSET ?",
       EXPERIENCES, USER_ID, IS_STILL_IN_ROLE, STARTED_YEAR, STARTED_MONTH
    );

    public static final String FIND_BY_ID = String.format(
       "SELECT * FROM %s WHERE %s = ? LIMIT 1",
       EXPERIENCES, ID
    );

    public static final String UPDATE_BY_ID = String.format(
       "UPDATE %s SET %s = ?, %s = ?, %s = ?, %s = ?, " +
       "%s = ?, %s = ?, %s = ?, %s = ?, %s = ? WHERE id = ?",
       EXPERIENCES, JOB_TITLE, COMPANY_NAME, STARTED_MONTH, STARTED_YEAR, ENDED_MONTH,
       ENDED_YEAR, IS_STILL_IN_ROLE, DESCRIPTION, LAST_MODIFIED_DATE
    );

    public static final String DELETE_BY_ID = String.format(
       "DELETE FROM %s WHERE %s = ?",
       EXPERIENCES, ID
    );

}
