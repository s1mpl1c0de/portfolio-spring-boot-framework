package com.simplicode.portfolio.mapper;

import com.simplicode.portfolio.model.Experience;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ExperienceMapperTest {

    private static final long EXPERIENCE_ID = 1L;
    private static final String JOB_TITLE = "Software Engineer";
    private static final String COMPANY_NAME = "Tech Corp";
    private static final short STARTED_MONTH = 1;
    private static final short STARTED_YEAR = 2020;
    private static final short ENDED_MONTH = 0;
    private static final short ENDED_YEAR = 0;
    private static final boolean IS_STILL_IN_ROLE = true;
    private static final String DESCRIPTION = "Development team.";

    private static final Timestamp CREATED_DATE = Timestamp.valueOf(
       LocalDateTime.of(2023, 1, 1, 10, 0)
    );

    private static final Timestamp LAST_MODIFIED_DATE = Timestamp.valueOf(
       LocalDateTime.of(2023, 5, 1, 15, 0)
    );

    private ResultSet resultSet;
    private ExperienceMapper experienceMapper;

    @BeforeEach
    void setUp() {
        resultSet = mock(ResultSet.class);
        experienceMapper = new ExperienceMapper();
    }

    @Test
    void testMapRow() throws SQLException {
        when(resultSet.getLong("id")).thenReturn(EXPERIENCE_ID);
        when(resultSet.getString("job_title")).thenReturn(JOB_TITLE);
        when(resultSet.getString("company_name")).thenReturn(COMPANY_NAME);
        when(resultSet.getShort("started_month")).thenReturn(STARTED_MONTH);
        when(resultSet.getShort("started_year")).thenReturn(STARTED_YEAR);
        when(resultSet.getShort("ended_month")).thenReturn(ENDED_MONTH);
        when(resultSet.getShort("ended_year")).thenReturn(ENDED_YEAR);
        when(resultSet.getBoolean("is_still_in_role")).thenReturn(IS_STILL_IN_ROLE);
        when(resultSet.getString("description")).thenReturn(DESCRIPTION);
        when(resultSet.getTimestamp("created_date")).thenReturn(CREATED_DATE);
        when(resultSet.getTimestamp("last_modified_date")).thenReturn(LAST_MODIFIED_DATE);

        Experience experience = experienceMapper.mapRow(resultSet, 1);

        assertNotNull(experience);
        assertEquals(EXPERIENCE_ID, experience.getId());
        assertEquals(JOB_TITLE, experience.getJobTitle());
        assertEquals(COMPANY_NAME, experience.getCompanyName());
        assertEquals(STARTED_MONTH, experience.getStartedMonth());
        assertEquals(STARTED_YEAR, experience.getStartedYear());
        assertNull(experience.getEndedMonth());
        assertNull(experience.getEndedYear());
        assertEquals(true, experience.getIsStillInRole());
        assertEquals(DESCRIPTION, experience.getDescription());
        assertEquals(CREATED_DATE.toLocalDateTime(), experience.getCreatedDate());
        assertEquals(LAST_MODIFIED_DATE.toLocalDateTime(), experience.getLastModifiedDate());
    }

}
