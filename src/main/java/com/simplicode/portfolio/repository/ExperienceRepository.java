package com.simplicode.portfolio.repository;

import com.simplicode.portfolio.mapper.ExperienceMapper;
import com.simplicode.portfolio.model.Experience;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;

@Repository
@RequiredArgsConstructor
public class ExperienceRepository {

    private final JdbcTemplate jdbcTemplate;

    public void save(Experience experience) {
        String sql = new StringJoiner(
           " ",
           "INSERT INTO experiences (",
           ") VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"
        )
           .add("job_title,")
           .add("company_name,")
           .add("started_month,")
           .add("started_year,")
           .add("ended_month,")
           .add("ended_year,")
           .add("is_still_in_role,")
           .add("description,")
           .add("created_date,")
           .add("last_modified_date")
           .toString();

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
        String sql = new StringJoiner(
           " ",
           "SELECT * FROM experiences ORDER BY ",
           "LIMIT 30"
        )
           .add("is_still_in_role DESC,")
           .add("started_year DESC,")
           .add("started_month DESC ")
           .toString();

        return jdbcTemplate.query(sql, new ExperienceMapper());
    }

    public Optional<Experience> findById(Long id) {
        String sql = "SELECT * FROM experiences WHERE id = ? LIMIT 1";
        return jdbcTemplate.query(sql, new ExperienceMapper(), id).stream().findFirst();
    }

    public void updateById(Long id, Experience experience) {
        String sql = new StringJoiner(
           " ",
           "UPDATE experiences SET ",
           "WHERE id = ?"
        )
           .add("job_title = ?,")
           .add("company_name = ?,")
           .add("started_month = ?,")
           .add("started_year = ?,")
           .add("ended_month = ?,")
           .add("ended_year = ?,")
           .add("is_still_in_role = ?,")
           .add("description = ?,")
           .add("last_modified_date = ? ")
           .toString();

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
        String sql = "DELETE FROM experiences WHERE id = ?";
        jdbcTemplate.update(sql, id);
    }

}
