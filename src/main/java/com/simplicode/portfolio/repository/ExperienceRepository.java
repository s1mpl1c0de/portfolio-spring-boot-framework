package com.simplicode.portfolio.repository;

import com.simplicode.portfolio.exception.BadRequestException;
import com.simplicode.portfolio.mapper.ExperienceMapper;
import com.simplicode.portfolio.model.Experience;
import com.simplicode.portfolio.model.Page;
import com.simplicode.portfolio.query.ExperienceQuery;
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
public class ExperienceRepository {

    private final JdbcTemplate jdbcTemplate;

    public void save(Experience experience) {
        try {
            jdbcTemplate.update(
               ExperienceQuery.INSERT,
               experience.getJobTitle(),
               experience.getCompanyName(),
               experience.getStartedMonth(),
               experience.getStartedYear(),
               experience.getEndedMonth(),
               experience.getEndedYear(),
               experience.getIsStillInRole(),
               experience.getDescription(),
               experience.getCreatedDate(),
               experience.getLastModifiedDate(),
               experience.getUserId()
            );
        } catch (DataAccessException exception) {
            log.error("Failed to save Experience: {}", exception.getMessage());
            throw new BadRequestException();
        }
    }

    public Integer countAllByUserId(Long userId) {
        try {
            String sql = ExperienceQuery.COUNT_ALL_BY_USER_ID;
            Class<Integer> requiredType = Integer.class;
            return jdbcTemplate.queryForObject(sql, requiredType, userId);
        } catch (DataAccessException exception) {
            log.error("Failed to count Experiences by userId {}: {}", userId, exception.getMessage());
            throw new BadRequestException();
        }
    }

    public List<Experience> findAllByUserId(Long userId, Page page) {
        try {
            String sql = ExperienceQuery.FIND_ALL_BY_USER_ID;
            ExperienceMapper rowMapper = new ExperienceMapper();
            Object[] args = new Object[]{userId, page.getLimit(), page.getOffset()};
            return jdbcTemplate.query(sql, rowMapper, args);
        } catch (DataAccessException exception) {
            log.error("Failed to find Experiences by userId {}: {}", userId, exception.getMessage());
            throw new BadRequestException();
        }
    }

    public Optional<Experience> findById(Long id) {
        try {
            String sql = ExperienceQuery.FIND_BY_ID;
            ExperienceMapper rowMapper = new ExperienceMapper();
            return jdbcTemplate.query(sql, rowMapper, id).stream().findFirst();
        } catch (DataAccessException exception) {
            log.error("Failed to find Experiences by id {}: {}", id, exception.getMessage());
            throw new BadRequestException();
        }
    }

    public void updateById(Long id, Experience experience) {
        try {
            jdbcTemplate.update(
               ExperienceQuery.UPDATE_BY_ID,
               experience.getJobTitle(),
               experience.getCompanyName(),
               experience.getStartedMonth(),
               experience.getStartedYear(),
               experience.getEndedMonth(),
               experience.getEndedYear(),
               experience.getIsStillInRole(),
               experience.getDescription(),
               experience.getLastModifiedDate(),
               id
            );
        } catch (DataAccessException exception) {
            log.error("Failed to update Experiences by id {}: {}", id, exception.getMessage());
            throw new BadRequestException();
        }
    }

    public void deleteById(Long id) {
        try {
            jdbcTemplate.update(ExperienceQuery.DELETE_BY_ID, id);
        } catch (DataAccessException exception) {
            log.error("Failed to delete Experiences by id {}: {}", id, exception.getMessage());
            throw new BadRequestException();
        }
    }

}
