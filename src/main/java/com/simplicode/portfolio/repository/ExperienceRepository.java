package com.simplicode.portfolio.repository;

import com.simplicode.portfolio.mapper.ExperienceMapper;
import com.simplicode.portfolio.model.Experience;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@RequiredArgsConstructor
public class ExperienceRepository {

    private final JdbcTemplate jdbcTemplate;
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

    public void save(Experience experience) {
        String sql = String.format(
           "INSERT INTO %s (%s, %s, %s, %s, %s, %s, %s, %s, %s, %s) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
           EXPERIENCES, JOB_TITLE, COMPANY_NAME, STARTED_MONTH, STARTED_YEAR, ENDED_MONTH,
           ENDED_YEAR, IS_STILL_IN_ROLE, DESCRIPTION, CREATED_DATE, LAST_MODIFIED_DATE
        );

        Object[] args = new Object[]{
           experience.getJobTitle(),
           experience.getCompanyName(),
           experience.getStartedMonth(),
           experience.getStartedYear(),
           experience.getEndedMonth(),
           experience.getEndedYear(),
           experience.getIsStillInRole(),
           experience.getDescription(),
           LocalDateTime.now(),
           null
        };

        jdbcTemplate.update(sql, args);
    }

    public List<Experience> findAll() {
        String sql = String.format("SELECT * FROM %s ORDER BY %s DESC", EXPERIENCES, CREATED_DATE);
        return jdbcTemplate.query(sql, new ExperienceMapper());
    }

    public Optional<Experience> findById(Long id) {
        String sql = String.format("SELECT * FROM %s WHERE %s = ? LIMIT 1", EXPERIENCES, ID);
        return jdbcTemplate.query(sql, new ExperienceMapper(), id).stream().findFirst();
    }

    public void updateById(Long id, Experience experience) {
        String sql = String.format(
           "UPDATE %s SET %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ?, %s = ? WHERE %s = ?",
           EXPERIENCES, JOB_TITLE, COMPANY_NAME, STARTED_MONTH, STARTED_YEAR, ENDED_MONTH,
           ENDED_YEAR, IS_STILL_IN_ROLE, DESCRIPTION, LAST_MODIFIED_DATE, ID
        );

        Object[] args = new Object[]{
           experience.getJobTitle(),
           experience.getCompanyName(),
           experience.getStartedMonth(),
           experience.getStartedYear(),
           experience.getEndedMonth(),
           experience.getEndedYear(),
           experience.getIsStillInRole(),
           experience.getDescription(),
           LocalDateTime.now(),
           id
        };

        jdbcTemplate.update(sql, args);
    }

    public void deleteById(Long id) {
        String sql = String.format("DELETE FROM %s WHERE %s = ?", EXPERIENCES, ID);
        jdbcTemplate.update(sql, id);
    }

}
